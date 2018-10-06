# Inverted Index

## version1
* Map
```{map}
public static calss Map extends Mapper<Text, Text, Text, Text> {
   private final static LongWritable one = new LongWriatble(1);
   private Text word = new Text();

   public void map(Text docId, Text value, Context context) throws IOException, Interrupted Exception {
      String line = value.toString();
	  StringTokenizer tokenizer = new StringTokenizer(line, "\t\r\n\f|,.()<>");
	  while(tokenizer.hasMoreTokens()) {
	     word.set(tokenizer.nextToken().toLowerCase());
		 context.write(word, docID);
	  }
   }
}
```
* reduce
```{reduce}
public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
   try {
      StringBuffer toReturn  = new StringBuffer();
	  boolean first = true;
	  for(Text val : values) {
	   if (!first){
	      toreturn append(",");
	   } else {
	      first = false;
	   }
	   toReturn.append(val.toString());
	  }
	  context.write(key, new Text(toReturn.toString()));
   } catch (Exception e) {
      context.getCounter("Error", "Reducer Exception:" + key.toString()).increment(1);
   }
}
```
Problem - one word and a doucment could be come out repeatedly  
Solution - increase JVM memory  
in mapred-site.xml
```{setting}
<property>
	<name>mapred.child.java.opts</name>
	<value>-Xmx1024m</value>
</property>
```

## version2

```{HashSet}
HashSet<String> words = new HashSet<String>();
public void map(Text docID, Text value, Context context) throws
IOExcpetion, IntteruptedException {
   Stirng line = value.toString();
   StringTokenizer tokenizer = new StringTokenizer(line, "\t\r\n\f|,!#\"$.'%$&=+-_^@'~:?<>(){}[];*/");
   words.clear();
   while(tokenizer.hasMoreTokens()){
     words.add(toeknizer.nextToken().toLowerCase());
   }
   Iterator it = words.Iterator();
   while(it.hasNext()) {
      String v = (String) it.next();
	  word.set(v);
	  context.write(word, docID);
   }
}
```

Problem - the size of map is lower than first version. But, it has a problem if the size of  HashSet is bigger than memory

## version3
Double Sorting  
* Define Type - WordID
```{type}

public class WordID implements Writable Comparable<WordID> {
   private String word;
   private Long docID;

   constructor WordID

   public String toString() {
      return (new StringBuilder())
	  			.append("{")
				.append(word)
				.append(",")
				.append(docID)
				.append("}")
				.toString();
   }

   public void readFields(DataInput in) throws IOException {
      word = WritableUtils.readString(in);
	  docID = in.readLong();
   }

   public void write(DataOutput out) throws IOException {
      WritableUtils.writeString(out, word);
	  out.writeLong(docID);
   }

   public int compareTo(WordID o) {
      int result = word.compareTo(o.word);
	  if( 0  == result ) {
	     result = (int) (docID - o.docID);
	  }
	  return result;
   }

   getset word, docID
}
```
* Custom Partitioner

```{Partitioner} 
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Partitioner;

import java.io.IOException;

public class WordIDPartitioner extends Partitioner<WordID, Text> {
   protected WordIDPartitioner() {
   }
   public int getPartition(WordID key, Text val, int numPartitions) {
      return (key.getWord().hashCode & Integer.MAX_VALUE) % numPartitions;
   }
}
```

* GroupingComparater
```{Comparator}
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.partitioner;

import java.io.IOException;

public class WordIDGroupingComparator extends Writable Comparator {
   protected WordIDGroupingComparator() {
      super(WrodID.class, ture);
   }

   public int compare(WritableComparable w1, WriatableComparable w2) {
      WordID k1 = (WordID) w1;
	  WordID k2 = (WordID) w2;
	  return k1.getWord().compareTo(k2.getWord());
   }
}
```

* SortComparator
```{sortCompartor}

import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import java.io.IOException;

public class WordIDSortCompartor extends WritableComparator {
   protected WordIDSortedComparator() {
      super(wordID.class, true);
   }

   public int compare(WritableComparable w1, WritableComparable w2) {
      WordID k1 = (WordID) w1;
	  WordID k2 = (WordID) w2;

	  int result = k1.compareTo(k2);
	  return result;
   }
}
```
* main
```{main}
...
job.setPartitionserClass(WordIDPartitioner.class);
job.setGroupComparatorClass(WordIDGropuingComparator.class);
job.setSortComparatorClass(WordIDSortComparator.class);
...
```

* Map
```{map}
public static class Map extends Mapper<Text, Text, WordID, Text> {
   private Text word = new Text();
   private WordID wordID = new WordID();
   public void main (Text docID, Text value, Context context) thorws IOException, InterruptedExcepetion { 
      String line = value.toString();
	  StringTokenizer tokenizer = new StringTokenizer(line, "\t\r\n\f|,!#\"$.'%&=+-_^~:?<>(){}[];*/");
	  while(tokenizer.hasMoreTokens()) {
	     wordID.setWord(tokenizer.nexToken().toLowerCase());
		 wordID.setDocID(Long.parseLong(docID.toString()));
		 context.write(wordID, docID);
	  }
   }
}
```
* Reduce
```{reduce}
public static class Reduce extends Reducer<WordID, Text, Text, Text> {
   public void reduce(WordID key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      try {
	     String word = key.getWord();
		 StringBuilder toReturn = new StringBuilder();
		 boolean first = true;
		 String prevDocID = "";
		 for(Text val: values) {
		    String curDocID = val.toString();
			if(!curDocID.equals(prevDocID)) {
			   if(!first) {
			      toReturn.append(",");
			   } else { 
			      first = false;
			   }
			}
		 }
		 context.write(new Text(word), new TExt(toReturn.toString()));
	  } catch (Exception e) {
	    ...
	  }
   }
}
```
There is low possiblity to generate error in memory  
But, Problem: There is much computation in network btw Map tasks and Reduce tasks

* So, use serialization framework Thrift, Avro, Protocol Buffer
* Compression of map output record
  * In mapred-site.xml
```{setting}
<property>
   <name>mapred.compress.map.output</name>
   <value>true</value>
</property>
<property>
   <name>mapred.map.output.compression.codec</name>
   <value>org.apache.hadoop.compress.GzipCodec</value>
</property>
```

## Reference
Do it Hadoop Programming
