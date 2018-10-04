#Hdoop and Mapreduce

* [Mapreduce paper](https://ai.google/research/pubs/pub62)

* [Hadoop 설명](https://www.joinc.co.kr/w/man/12/aws/bigdata/Fundamentals3)
* [Hadoop v1 vs Hadoop v2](http://ecycle.tistory.com/6)
* Map + reduce
* [Shuffling](http://develop.sunshiny.co.kr/897)
* [Combiner](https://data-flair.training/blogs/hadoop-combiner-tutorial/)
* [Partitioner](https://data-flair.training/blogs/hadoop-partitioner-tutorial/)
* [Zookeeper](http://over153cm.tistory.com/category/-----%20Big%20Data%20----/Hadoop)
* [Shuffling 세부단계](http://develop.sunshiny.co.kr/897)
* [Docker 컨테이너 기반의 하둡 클러스터 실행](http://blog.naver.com/PostView.nhn?blogId=alice_k106&logNo=220408254436)

* Hadoop vs RDBMS

* Mapreduce
  * Map reduce is good for batch fashion, particulary ad hoc analysis  
  * Written once and read many times  
  * Schema on read - works well unstructured or semi-structured data
  * Scalling : Linear

* RDBMS
  * RDBMS is good for point quries or updates
  * Batch and interactive
  * dataset that are continually updated
  * Schema on write

* HDFS
  * BLOCK's default size is 128MB
    * to minimize the cost of seeks
	* Having a block abstraction for dfs has 3 benefit
	  * It doesn't require to store same disk
	  * Simplify the storage  subsystem
	  * blocks fit well with replication for providing fault tolerance and availability
	* hflush
	  * all buffers to be flushed to the datanode via the datanode
	  * hflush doesn't guarantee that the datanodes have written the data to disk
	* hsync
	  * synchronizing

* NameNode - 2 mechanism
  * backup the files to make up the persistent state of the filesystem
  * secondary Namenode
    * periodically merge the namespace image with the edit log 

* Block Caching
* DataNode
* HDFS HA

  * the combination of replication anmenode metadata and secondary namenode to create checkpoints protects against data lost.

* Failover Controller
   * The transition from active namenode to the standby is managed
   * default implementation is Zookeepr

* graceful failover
  * by an administartor failover is manually initiated
* In case of ungraceful
  * It is impossible to be sure that the failed namenode has stopped running

### CLI
* hadoop fs -copyFromLocal [LOCALFILE] [FILE_IN_HDFS]

### HDFS
* Direct access
  1. the embedded web servers in the namenode and datanodes act as WEBHDFS endporints
  2. One or more standalone proxy servers(The proxies are stateless, so they can run behind a standard load balancer)
    * firewall
	* limitng bandwidth-policies

### JAVA Interface
1. Reading Data from Hadoop URL
2. Reading Data Using the filesystem API

## Data Flow
### anatommy of File Read
1. HDFS client ->\(open Distributed File System\) ->
2. \(get block locations\) -> NameNode
3. HDFS client ->\(read from FSData InputStream\) 
4. FSData inputStream -> \(read from Data Node\)
5. close FSData InputStream

### anatommy of File Write
1. HDFS client -> \(open DFS\)
2. \(create RPC call\) to create a new file in the filesystem's namepsace -> Name Node
  * name node checks existence of file or permissions
3. FSData OutputStream\(handle coomunicate with DN and NN\) ->\(splits it into packets\) -> \(write data to ineternal queue called data queue.\) 
4. FSData OutputStream -> write packet to DataNode\(pipelines of data nodes\) -> \(write packet and ack packet\)
5. FSData OutputStream is wating for ack by Datanode
6. close FSData OutputStream if client finish to write data

### Network Topology and Hadoop
the bandwidth available for each of the following scenarios be progressively less
* Process on the same node
* Differnet nodes on the same rack
* Node on different racks in the same data center
* Nodes in different centers
Network distance on Hadoop
distance(d1/r1/n1, d1/r1/n1)  = 0: example of distance

### Replica PLACEMENT
Which datanode store replicas on Namenode?
* trade-off btw relibaility and write bandwidth and read bandwitdth
* Hadoop's defalult strategy is
  * to place the first replica on the same node
  * The second replica is placed on a different rack from the first (off-rack), chosen at random
  * The third replica is placed on the same rack as second rack
  * Further replica is placed at random node in the cluster
* The system tries to avoid placing many replicas on the same node
### Parallel Copying with distcp
* hadoop distcp dir1 dir2 = hadoop fs -cp
* hadoop distcp -update -delete -p hdfs://namenode1/foo hdfs://namenode:foo2

if incompatible versions of HDFS then
* hadoop distcp webhdfs://namenode1:50070/foo webhdfs://namenode2:50070/foo

#YARN
* it proviedes APIs for requesting and working with cluster resources

1. App client -> \(submit Yarn APP\) RM 
2. RM ->\(start container\) [NM ->\(launch\) Container]
3. [Container]->\(allocate resources heart beat\) RM
4. Container->\(start container\) [NM -> \(launch) Container]

* Comparision MRv1 vs YARN
  * jobTracker vs RM, AM, timeline server
  * Tasktracker vs Node Manager
  * Slot vs Container
* Availbitiy
  * HA - by replicating the state needed for another daemon to take over the work    
  * However, the large amount of rapidly changing complex state in the jobtracker's memory
* Utilization
  * MR1, each tasktracker is configured with static allocation of fix-size "slot", which are divided into map slot and reduce slots at configuration time
  * In Yarn, MR1, node manager manages a pool of resourcess, rather than map slot designated
    * MR running on YARN will not hit the situation where a reduce task has to wait because only map slot are available on the cluster
* Multitenancy
  * It is possible for user to run different version of MR on the same YARN cluster

### Secheduling in YARN

* FIFO
  * simple to understand
  * not needing configuration
* Capacity
  * A seperate dedicated queue allows to the small job to start ASAP
* Fair
  * There is no need to reserve a set amount of capacity
  * Since it will dynamically balance resources btw all running jobs
* Delaying Scheduling
  * Every node manager in a YARN cluster periodically sends a heartbeat request
  * if an app requests a node, there are chance that other containers are running on it at the time of the request. it has been observed in practice that waiting a short time that can dramatically increase the efficiency of the cluster 

## Hadoop I/O
### Data Integrity
#### Data Integrity in HDFS
error detecting code  CRC-32 -> Hadoop's checksumFileSystem  
hadoop fs -checsum  
* LocalFileSystem
  * client-side
  * metadata .crc - hidden file
    * the default chunks size is 512
* ChecksumFileSystem
  * Error is detected by ChecksumFileSystem when reading a file -> report checksumFailure method

### Compression
* Deflate is compression algorithm whos standard implementation is zlib
* ex\) gzip -1 file  => -1 is optimal
* Snappy and LZO is faster than LZO for decopmression
* Splitable Compression format are especially suitable for MR

#### Codec
* Code is the implementation of compression-decomporession algorithm
  * Interface -  CompressionCodec  <- CompressionCodecFactory
* which compression format shoul I use?
  * Use a container file format such as a sequence file
    * Avro, Parquet file
	* LZO, LZ4 or Snappy is generally good choice for fast compression
  * Use a compression format that supports splitting such as bzip2 or LZO
  * Split the file intro chunks in app, and compress the chunks seperately
    * You should choose the chunk size that the compresseed are approximately the size of HDFS block

### Serialization
* the process of turning structured objects into a byte stream for transmission over network or for writing to persistent sotrage
  * Deserialization is the reverse process

* In Hadoop, interprocess btw node is implemented using RPC
* RPC serialization Format
  * Compact
    * makes the best use of network bandwidth
  * Fast
    * IPC forms the backbone to perform little overhead using serialization and deserialization process
  * Extensible
    * Protocols change over time to meet new reqiuirements, so it should be straighforward to evolve the protocol to use btw clients and servers 
  * Interoperable
    * support clients that are written in different languages to the server, so the format needs to be designed to make this possible
* Hadoop uses its own serialization format - Writable
  * DataOuput
  * DataInput
  * fixed-length: InWritable, LongWritable - when the distribution of values is fairly uniform  - ex) hash function
  * Most numeric variables tned to have nonuniform distribution , though the varibale-lenght encoding will save space
  * Advantage - Can switch VIntWritable to Vwritable 

* Text
  * Text is a Writable for UTF-8 sequence
* Indexing
  * Indexing for the Text class is in terms of position in the encoded byte sequence 
* Unicode
  * there is the difference btw Text and String
* the length of a String is the number of char code

## Anatomy of a Mapreduce job run
* five independent entities
  * the client submits the MR jobs
  * YARN resource manager, which cordinate the allocation of computer resources on the cluster
  * The YARN node managers, which launch and moniter the compute containers on machinees in the cluster
  * MR app master, which coordinating the tasks running the MR job. The application master and the MR tasks run in containers that are scheduled by the resource manager and managed by the node managers
  * DFS, shareing job files btw the other entities
## Shuffle
* Shuffle
  * the input to every reducer is sorted by key
    * the process by which the system performs the sort and transfers the map outputs to the reducers as inputs
* Map Side
  * the circular memory buffer that it writes the output to.
    * the buffer size is 100MB default
	* when th buffer reach a cetain threshold size
	  * a background thread will start to spill the content to disk
	  * if buffer fills up during this time, the map will block until the spill is complete
	* Spils are written in round-robin fashion
  * Before it write to disk the thread divide the data into partition.
  * Within each partition, the background thread performs an in-memory sort by key
  * running a combiner function makes for more compact map output 
  * Each time the memory buffer reaches the spill threshold, a new spill file is created.
  * It is good idea to compress the map output
  
* Reducer Side
  * Map task may fisinsh at differnt time, so the reduce task start copying their data of their task
  * If reaches a thresold number of map ouputs, it is merged them into larger, sorted files
  * As the copies accumulate on disk, a background thread merges them into larger, sorted files
  * When all the map ouputs have been copied, the reduce task moves into the sort phase, which merge the map outputs, and maintain their sort ordering
  * if there are 50 map output and merge factor was 10 default, controlled by the mapreduce.task.io.sort.factor.property
  * Each round merge 10 files to 1, so the end there would be 5 intermediate files

### Mapreduce Type
* map : (k1, v1) -> list(k2, v2)
* combiner : (k2, list(v2)) -> list(k2,v2)
* reduce : (k2, list(v2)) -> list(k3, v3)

## Recommended Books and Reference
Hadoop definite Guide
