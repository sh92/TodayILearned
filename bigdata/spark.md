# Spark
* [Spark Shuffle](https://swalloow.github.io/spark-shuffling)
* [rdd](https://www.quora.com/Why-is-RDD-immutable-in-Spark)
  * resilient
    * it make sure that if one goes down it can recover.
  * distributed
  * dataset
  * transforming rdd
    * map, flatmap
    * filter, ditinct, sample, union, intersection, subset, catesian
  * action
    * collect
    * count, countByValue
    * take
    * top
    * reduce
	* [aggregate](http://whereami80.tistory.com/105)
    * etc
  * lazy evaluation
* map vs flatmap
  * [map vs flatmap - Korean](https://smlee729.wordpress.com/2016/06/29/spark-map-flatmap-operations/)
  * [map vs flatmap - English](https://data-flair.training/blogs/apache-spark-map-vs-flatmap/)
* [Spark 개념 한글로 잘 정리된 사이트](http://helloino.tistory.com/20)
* [Dataframe](http://12bme.tistory.com/307)
  * [Databricks dataframes](https://docs.databricks.com/spark/latest/dataframes-datasets/introduction-to-dataframes-python.html)
* persist vs cache
  * [RDD Persistence](http://bcho.tistory.com/1029)
  * Most mllib algorithms are iterative
    * cache() your input datasets before passing the to mllib
	* If doe not fit in memory try persist(StorageLeve.DISK\ONLY)
	* In Python, mllib autommatically caches RDD on JAVA side
	* In Scala and Java, it depends on the usage of cache for developer

* Pair RDD
  * Aggregate
    * reduceBykey
    * foldByKey
    * combineByKey
  * if it is a value we have seen before while processing that partition, it will instead use the provided function, mergeValue
  * Wordcount
    * input.flatMap(x => x.split(" ")).countByValue()

* Pararallelism
  * sc.parallelize(data).reduceByKey(lambda x,y:x+y)
* [RDD partitioning](http://ourcstory.tistory.com/156)
  * [Partitions with Coalesce and Repartition](https://hackernoon.com/managing-spark-partitions-with-coalesce-and-repartition-4050c57ad5c4)
* [Spark Lineage — Logical Execution Plan](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-rdd-lineage.html)
  * To See
    * use toDebugString method
	* /bin/spark-shell --conf spark.logLineage=true

* Grouping Data
  * groubByKey
     * [K, Iterable[V]]
  * cogroup
* Joins
  * right and left outer join
  * cross join
  * inner join

* spark 1.0 operations partitioning
  * cogroup, groupWith, join, leftOuterJoin, rightOUterJoin, groupByKey, reduceBuKey, combinByKey, lookup
  * need lots of I/O -> Partitioner

* Partitioner 
  * [Understanding Partitioning in Spark](https://acadgild.com/blog/partitioning-in-spark)
  * HashPartitioner
  * RangePartitioner
  * CustomPartitioner

* Shared Variable
  * accumlators
  * broadcast

* Pipe to External Programs
  * combine the R

* Memory Management
  * RDD storage
  * Shuffle and aggregation buffer
  * User Code


* Loading and Saving Data
  * JSON
  * Hive
  * Parquet
    * column-oriented storage format
  * From RDD
  * JDBC/ODBC

* Spark SQL UDF

* ALS
  * [weitght lamda regulization 이해](https://www.slideshare.net/madvirus/als-ws)
  * rank
    * size of feature vectors to use; larger ranks can lead to better models but ar more expensive to compute -  default - 10
  * iterations
    * number of iterations to run deafault - 10
  * lambda
    * Regularization parameter - 0.01
  * alpha
    * A constant used for computing confidence in implicit ALS - 1.0
  * numUserBlocks, numProductBlock
    * Number of blocks to diide user and product, to contorl parallellism


* RDD Graph
  * sc.textFile()
    * HadoopRDD
    * MaperRDD
  * .map(...)
    * MapperRDD
  * filter(...)
    * filteredRDD
  * reduceyByKey(...)
    * ShuffledRDD(...)

* Spark mllib
 * [spark ml package](http://12bme.tistory.com/310)
   * transformer
   * estimater
   * pipline
 * [MLlib로 해보는 머신러닝 아꿈사 박주희](https://www.slideshare.net/juhuipark37/11-554943180)
   * Classification
     * mllib.classification
       * Naive Bayes
       * SVM
   * Regression
     * mllib.regression
       * SGD
	   * LBGFS
   * Clustering
     * Find the Exception
	 * Validate the data
	 * [Kmeans and GMM](http://sanghyukchun.github.io/69/)
	     * [EM-Expectation Maximization algorithms](http://sanghyukchun.github.io/70/)
   * 병렬화 수준
     * 입력RDD의 파티션개수 : 최소 코어 개수 -> 병렬화 최대
	 * Block마다 파티션을 만들며 블록의 크기는 64MB
	 * 파티션 개수를 변경하려면 최소 파티션 개수를 SparkContext.textFile에 넘김
	 * RDD에서repartition(numpartitions)를 호출하려 numpartitions 만큼 파티셔닝
   * 파이프라인 API
     * 파이프라인은 데이터셋을 변환하는 알고리즘들의 모음
	 * SchemaRDD로 일관되게 표현 - Dataframe과 유사
	   * [Spark ML로 파이프라인 만들기](https://docs.microsoft.com/ko-kr/azure/hdinsight/spark/apache-spark-creating-ml-pipelines)
     * HasingTF
       * This function is Hashing trick transformer
	     * It is recommended the size of the power of two
   * Demension Reduction
	 * [Demension Redcution Tutorial](https://spark.apache.org/docs/2.2.0/mllib-dimensionality-reduction.html)
     * PCA
     * SVD - Singular value decomposition
       * SVD factrization  m*n matrix A three matrices  A := UsV_tans
	     * U is orthonormal matrix, left singular vectors
	     * s is a dignal matrix with nonnegative diagonals in decending order, singular values
	     * V is orthonomal matrix, right singular value

* Evaluation
 * precision, recall, ROC
* Recommended Site
  * [Learning Spark](https://d2.naver.com/news/8818403)
  * [Mastering Spark](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-overview.html)
  * [Introduction to AmpLab Spark Internals](https://www.youtube.com/watch?v=49Hr5xZyTEA&feature=youtu.be)
  * [Netflix - Productionizing Spark On Yarn For ETL At Petabyte Scale](https://www.slideshare.net/JenAman/netflix-productionizing-spark-on-yarn-for-etl-at-petabyte-scale)
  * [Apache Spark에서 컬럼 기반 저장 포맷 Parquet(파케이) 제대로 활용하기](http://engineering.vcnc.co.kr/2018/05/parquet-and-spark/)
* Recommended Book
  * Learning Spark
  * Mastering Spark
  * Advanced analytics with Spark
* Recommended Video
  * [RDDs, DataFrames and Datasets in Apache Spark - NE Scala 2016](https://www.youtube.com/watch?v=pZQsDloGB4w)
    * RDDs are type-safe, But problems is
	  * It cannot be optimized by Spark
	  * They're slow on non-JVM languages like python
	  * It's too easy to build an inefficient RDD transformation chain
	    * ex) filter is better to be placed in the front
	* Dataframes are fatster than RDD
	  * Because Dataframe queries are optimized
	  * But, we've lost type safety
	    * trait Row extends Serializable
	* Compile-time type safety with optimizations catalyst
	* Datasets - an extension to the dataframe API
	  * Dataframe is just a DataSet[Row]
	  * Inclination to Use less memory
	    * The generated code can operate on the Tungsten compact format
  * [Deep Learning and Streaming in Apache Spark](http://engineering.vcnc.co.kr/2017/12/spark-summit-eu-2017/)

