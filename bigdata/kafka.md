# Message Queue
* MQ
  * [Message Queue란?](http://zzong.net/post/3)
  * [Message Queue는 왜 사용해야 하는가?](http://www.icelancer.com/2016/12/message-queue.html)

## Kafka
### Kafka Theory
* Topics
  * partitions
    * Each message get an incremental id, called offset
	* the data 
	  * is written to a partition, it cant'be cahnged\(immutability\)
	  * is assigned randomly to a partition unless key is providied

* Brokers
  * Each broker is identified with its ID
  * Each broker has certain topic partitions
  * After connecting to any broker, connect to entire cluster

* Replication
  * Topics should have a replication factor
  * One broker can be a leader for given partition
  * Only that leader can receive and serve data for a partition
  * Each partition has one leader and multiple ISR\(in-sync replica\)
  * The other brokers will synchronize the data

* Producers write data to topics
  * Producers automatically know to which broker and partition to write to
  * In cas of Broker failures, Producers will automatically recover
  * acks = 0, Producer won't wait for acknowledgment 
  * acks = 1, Producer will wait for leader acknowledgment
  * acks = all, Leader + replicas acknowledgement


### Kafka Study Site
* [Kafka 이해하기](https://medium.com/@umanking/%EC%B9%B4%ED%94%84%EC%B9%B4%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%9D%B4%EC%95%BC%EA%B8%B0-%ED%95%98%EA%B8%B0%EC%A0%84%EC%97%90-%EB%A8%BC%EC%A0%80-data%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%9D%B4%EC%95%BC%EA%B8%B0%ED%95%B4%EB%B3%B4%EC%9E%90-d2e3ca2f3c2)
* [Kafka](http://epicdevs.com/17)
* [Streaming Platform으로써의 Apache Kafka](http://readme.skplanet.com/?p=13802)
  * Kafka Microservice로 커플링을 없애고 단순한 구조로 만들자
