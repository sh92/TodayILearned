# SparkStreaming

## Architecture and Abstraction
* DataStreams -> [Receivers - RDDs:Batches of data] -> Transform & output other System
* SparkSteaming use "Micro batch" architecture  
* Discretized stream or DStream, which is a sequence of RDDs  
* In Driver Program, StreamingContext use SparkContext, and SparkContext send data to tasks to process in Worker Node's Executor  
* SparkStreaming offers the same fault-tolerance properties for DStreams
* Spark Streaming also includes a mechanism called checkpointing that save sate periodically

* Transforamtion
  * In stateless transformations the processing of each batch does not depend on the data of its previous batches 
    * map, flatmap, filter, reduceByKey, repartition, groupByKey
  * In stateful transformations, ues data or intermediate results from previous batches to compute the result of the current batch
    * two main types are windowed operations and updateStateByKey()
	* setting up checkpoint : ssc.checkpoint("hdfs://...")
	* Windowed transformations
	  * window
	  * slide
```{example}
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds

val ssc = new StreamingContext(conf, Seconds(1))
val lines = ssc.socketTextStreaem("localhost", 7777)
val errorLines = lines.filter(_.contain("error"))
errorLines.print()
```
##Stateless
```{stateless}
val accessLogDstream = logData.map(line => ApacheAcessLog.parseFromLogLine(line))
val ipDstream = accessLogDStream.map(entry => (entry.getIpAddress(), 1))
val ipCountDStream = ipDStream.reduceByKey((x,y) => x+y)

val ipBytesDstream = accessLogDstream.map(entry => (entry.getIpAddress(), entry.getContentSize()))
val ipBytesSumDstream = ipBytesDstream.reduceByKey((x,y) => x+y)
val ipBytessRequestsCountDStream = ipCountsDStream.join(ipBytesSumDStream)

val outlieerDStream = accessLogsDStream.transform { rdd => extractOutlier(rdd) }
```
##Stateful
```{stateful}
val accessLogWindow = accessLogsDStream.window(Seconds(30), Seconds(10))
val windowCounts = accessLogWindow.count()

val ipDStream = accessLogsDStream.map(logEntry => (logEntry.getIpAddress(), 1))
val ipCountDStream = ipDStream.reduceByKeyAndWindow( {(x,y) => x+y}, {(x,y) =>x-y}, Seconds(30), Seconds(10))
val ipAddressRequestCount = ipDStream.countByValueAndWindow(Seconds(30), Seconds(10))
val requestCount = accessLogsDStream.countByWindow(Seconds(30), Seconds(10))
```
* UpdateByKey
```{updateByKey}
def updateRunningSum(values: Seq[Long], state: Option[Long]) = {
  Some(state.getOrElse(0L) + values.size)
}
val responseCodeDStream = accessLogsDStream.map(log => (log.getResponseCode(), 1L))
val responseCodeCountDStream = responseCodeDStream.updateStateByKey(updateRunningSum _)
```

## Output Operations
* Text
```{Text}
ipAddressRequestCOunt.saveAsTextFiles("outputDir", "txt")
```
* SequenceFiles
```{SequenceFile}
val writableIpAddressRequestCount = ipAddressRequestCount.map {
(ip, count) => (new Text(ip), new LongWritable(count)) }
writableIpAddressRequestCount.saveAsHadoopFiles[SequenceFileOutputFormat[Text, LongWritable]]("outputDir", "txt")
```
* Saving data with foreachRDD
```{foreach}
ipAddressRequestCount.foreachRDD { rdd =>
   rdd.foreachPartition { partition =>
      partition.foreach { item =>
	  ...
	  }
	...
   }
}
```

## Input Sources
* text
```{input Sources}
val logData = ssc.textFileStream(logDirectory)
```
* sequence
```{sequcne}
ssc.fileStream[LongWritable, IntWritable, SequenceFileInputFormat[LongWritable, IntWritable]](inputDirectory).map { case(x,y) => (x.get(), y.get()) }
```

## Reference
Learning Spark
