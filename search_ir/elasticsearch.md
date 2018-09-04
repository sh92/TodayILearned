# ElasticSearch

## Install ElasticSearch 6
sudo apt-get install default-jdk  
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -  
sudo apt-get install apt-transport-https  
echo "deb https://artifacts.elastic.co/packages/6.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-6.x.list  
sudo apt-get update && sudo apt-get install elasticsearch 
  
sudo vi /etc/elasticsearch/elasticsearch.yml  
change network.host 0.0.0.0
  
sudo /bin/systemctl daemon-reload  
sudo /bin/systemctl enable elasticsearch  
sudo /bin/systemctl start elasticsearch  

## Intro
* The schema for document
  * index
  * type
  * dcoument

* Purpose of inverted indices 
  * search in reverse order
  * Quickly map search term to document
  * Load balancing search request across your cluster

* Elasticsearch is built only for full-text search of documents

* [Shard and Replica](http://guruble.com/elasticsearch-2-shard-replica/)
  * Shard
    * it could let contents volume to be split horizontally
	* it distributes jobs in serveral shards.It could accelate performence/throughput by parallelizing.
  * Replica
    * High Availability
	* It could search in parallel. it accelates performence/throughput.

## Search
* Curl
  * Curl setting
    * $HOME/bin/curl
	  * !/bin/bash
	    * /usr/bin/curl -H "Content-Type: application/json" "$@"
	  * chmod a+x curl
	  * logout -> login
  * Insult
    * curl -XPUT HOST:PORT?PATH -d '{json foramt}'
	  ```{create mapping}
	  curl -XPUT HOST:PORT/movies -d '
	  {
	  	"mappings":{
			"movie":{
				"properties":{
					"year":{"type":"date"}
				}
			}
		}
	  }'
	  ```
	* curl -XPUT HOST:PORT/\_bulk --data-binary @filename.json
  * Select
    * curl -XGET HOST:PORT/Path/\_search?pretty
	* curl -XGET HOST:port/\_cat/indices
  * Update
    * curl -XPOST HOST:PORT/Path/\_update -d '{json}'
  * Delete
    * curl -XDELETE HOST:PORT/Path?pretty
  * REST API End Point 
    * \_search
    * \_update
	* [\_bulk](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html)
  * Concurrency
    * if current version is 4
	  * curl -XPUT HOST:PORT/Path?version=3 -d '{json}'
	    * error
    * curl -XPOST HOST:PORT/Path?/\_update?retry\_on\_conflict=5? -d '{json}'

* Query DSL
  * [Document Site](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query-phrase.html)

* Analyzer
  * use keyword
  * use text type
  * case-insensitive, stemmed, stopwords, removed

* Query String Search
  * HOST:PORT/Path\_search?q=+year:>2010+title:trek&pretty
  * '\+': %2B
  * ':' : %3A
  * '>' : %3E
  * cryptic and tough to debug
  * fragile

* JSON Search
  * filter : ask a yes/no quesiton of your data
    * Type
	  * term, terms, range, exists, mssing, bool etc
  * queries : return data in terms of relevance
    * Type
	  * match_all
	  * match
	  * multi_match
	  * bool

* Phrase Search
  * phrase matching
  ```{phrase matching}
    { 
		"query": {
			"match_phrase": {
			"title" : "blabla"
			}
		}
	}
  ```
  * slop
    * How far you're willing to let a term move to statisfy a phrase 
	  * "Hello Diseny world" would match "Hello world" with a slope of 1
  * proximity queries - results are stored by relevance

* Pagination Syntax
  * curl -XGET 'HOST:PORT/PATH/\_search?size=2?&from=2&pretty'
  ```{pagination}
  curl -XGET HOST:PATH/\_search?pretty -d '
  {
    "from" : 1,
	"size : 3,
	"query" : { "match":{"genre": "Sci-Fi"}}
  }'
  ```

* Sort
  * curl -XGET HOST:PORT/Path/\_search?sort=year&pretty
  * if string
    * text fields are analyzed
    ```{map an unanalyzed copy using the keyword type}
	curl -XPUT HOST:PORT/Path -d '
	{
		"mappings": {
			"movie": { 
				"properties": {
					"title": {
						"type": "text"
						fields": {
							"raw": { 
								"type": "keyword",
							}
						}
					}
				}
			}
		}
	}'
	```
  * unanalyzed "raw" keyword fileds
    * curl -XGET HOST:PORT/Path/\_search?sort=title.raw&pretty
    * Change X
      * Delete, re-index

* Another filter
  * [must, must\_not, filter etc](https://www.elastic.co/guide/en/elasticsearch/reference/5.5/query-filter-context.html)

* Fuzzy matches
  * a way to account for typos and mispellings
  * levenshtein edit distance
    * Substitutions of characters
    * Insertion of characters
	* Deletion of characters
  * funzziness parameter
    * In query 
    ```{fuzzy}
    "fuzzy": {
  	  "title":{"value":"Super Mario", "funzziness":2}
    }
    ```
  * funzziness: AUTO
    * 0 for 1-2 character strings
	* 1 for 3-5 character strings
	* 2 for anything else

* Partial Matching
  * partial
  ```{partial}
  curl -XGET HOST:PORT/_search?pretty' -d'
  {
  	"query": {
		"prefix": {
			"year": "201"
		}
	}
  }'
  ```
  * wildcard quries
  ```{wildecard in Query}
  "wildcard": {
		"year": "2*"
  }
  ```
* Search as you type
  * In query
  ```{in Query}
  "match_phrase_prefix":{
  	"title": {
		"query": "Hello world",
		"slop" : 5
	}
  }
  ```
  * Hello
    * unigram: [H,e,l,l,o]
	* bigram: [He, el, ll, lo]
	* trigram, 4-gram, N-gram
  * Indexing n-gram
    1. Create an "autocomplete analyzer" 
	```{indexing n-gram}
	"settings": {
  		"analysis": {
			"filter": {
				"autucomplete_filter": {
					"type": "edge_ngram",
					"min_gram": 1,
					"max_gram": 20
				}
			},
			"analyzer": {
				"autocomplete" {
					"type": "custom",
					"tokenizer": "standard",
					"filter": [
						"lowercase",
						"autocomplete_filter"
					]
				}
			}
		}
	}
	```
	2. Map your field with it 
	```{properties}
	curl -XPUT 'HOST:PORT/PATH?pretty' -d '
	{
		"movie": {
			"properties": {
				"title": {
					"type":"string",
					"analyzer":"autocomplete"
				}
			}
		}
	}'
	```
    3. Only use n-gram on the index side
	```
	curl -XGET HOST:PORT/PATH/_search?pretty -d '
	{
		"query": {
			"match: {
				"title": {
					"query": "war",
					"analyzer: "standard"
				}
			}
		}
	}'
	```	
## Importing Data into your index 
1. stand-alone scripts via REST API
2. logstash and beats can stream data from logs, s3, databases
3. AWS systems can stream in data via lambda or kinesis firehose
4. kafka, spark and more have Elasticsearch integration add-ons

1. Script
  * java - elastic.co
  * python - elasticsearch package
  * scala - Many ways
  * perl - elasticsearch.pm
2. Loastash 
  * Usage
    1. files, beats, s3, kafka ->
    2. logstash ->
    3. elastic search, aws, hadoop, mongodb
  * parses, tranforms and filters data
  * It can anonymize personer data 
  * It can do geo-location lookups
  * Various Input and Output
  * sudo vi /etc/losgstash/conf.d/{filename}.conf
    * [Logstash Tutorial: How to Get Started](https://logz.io/blog/logstash-tutorial/)
    * [example](https://gist.github.com/cgswong/95c86e0b16e2c6fb67210)
  * Running logstash
    * cd /usr/share/logstash/
	* sudo bin/logstash -f /etc/logstash/conf.d/logstash.conf
* [Logstash basic example](https://m.blog.naver.com/PostView.nhn?blogId=kbh3983&logNo=221063092376&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)
```{logstash config file}
input {
	file {
		path => "$HOME/{logfile}"
		start_posistion => "beginning"
		ignore_older => 0
	}
}
filter {
        grok {
                match => { "message" => "%{COMBINEDAPACHELOG}"}
        }
        date {
                match => [ "timestamp", "dd/MMM/yyyy:HH:mm:ss Z" ]
        }
}

output {
        elasticsearch {
                hosts => [ "127.0.0.1:9200"]
        }
        stdout {
                codec => rubydebug
        }
}

```

3. MySQL
```{mysql}
sudo apt-get install mysql-server
mysql -u root -p
CREATAE DABASE movielens{TABLENAME}
CREATE TABLE movielens.movies {
movieID INT PRIMARY KEY NOT NULL,
title TEXT,
releaseDate DATE
};
LOAD DATA LOCAL INFILE 'ml-10k/u.item' INTO TABLE movielens.movies FIELDS TERMINATED BY'|'
(movieID, title, @var3)
set releaseDate = STR_TO_DATE(@var3, '%d-%M-%Y')
sudo bin/logstash -f /etc/logstash/conf.d/mysql.conf
```
```{mysql file}
input {
        jdbc {
                jdbc_connection_string => "jdbc:mysql://127.0.0.1:3306/movielens"
                jdbc_user => "root"
                jdbc_password => "123"
                jdbc_driver_library => "/home/elastic/mysql-connector-java-5.1.42/mysql-connector-java-5.1.42-bin.jar"
                jdbc_driver_class => "com.mysql.jdbc.Driver"
                statement => "SELECT * FROM movies"
        }
}

output {
        stdout { codec => json_lines }
        elasticsearch {
                "hosts" => "127.0.0.1:9200"
                "index" => "movielens-sql"
                "document_type" => "data"
        }
}
```
```
curl -XGET 'localhost:9200/movielens-sql/_search?q=title:Star&pretty'
```
4. S3
5. Kafka
  * sudo apt-get install zookeeperd
  * wget {kafka download}
  * tar -zxvf {file}
  * cd {file}
  * terminal1 - start kafka : sudo bin/kafka-server-start.sh config/server.properties
  * terminal2 : sudo bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-log
  * sudo vi /etc/logstash/conf.d/logstash.conf
    ```{kafka}
	input {
		kafka {
			bootstrap_servers => "localhost:9092"
			topics => ["kafka-logs"]
		}
	}
	```
  * cd /usr/share/logstash
  * sudo bin/logstash -f /etc/logstash/conf.d/logstash.conf
  * terminal3 : sudo bin/kafka-console-producer.sh --broker-list localhost:9092 --topic kafka-logs < ../access\_log
6. Spark
```{spark}
wget http://mirror.apache-kr.org/spark/spark-2.3.1/spark-2.3.1-bin-hadoop2.7.tgz
get data file
wget {spark download}
curl -XGET localhost:9200  -> find elasticsearch version
maven repository -> search keyword 'org.elsasticsearch' => spark 2.x => find a proper version with elasticsearch
./spark-2.3.1-bin-hadoop2.7/bin/spark-shell --packages org.elasticsearch:elasticsearch-spark-20_2.11:6.4.0
./spark-2.3.1-bin-hadoop2.7/bin/spark-shell --packages org.elasticsearch:elasticsearch-spark-{spark-elsatic version}:{elasticsearch version}

spark shell
import org.elasticsearch.spark.sql._
case class Rating(uerID:Int, movieID:Int, rating:Float, timestamp:Int)
def mapper(line:String): Rating= {
	val fields = line.split(',')
	val rating:Rating = Rating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toInt)
	return rating 
 }
import spark.implicits._
val lines = spark.sparkContext.textFile("ml-latest-small/ratings.csv")
val header = lines.first()
var data = lines.filter(row => row!=header)
val ratings  = data.map(mapper).toDF()
ratings.saveToEs("spark/ratings")
:quit
curl -XGET 127.0.0.1:9200/spark/_search?pretty
``` 
7. Flink

# Aggregation
* metric
  * average, stats, min/max, percentile etc
* buckets
  * histogram, ranges, distance, significant, terms etc
* pipellines
  * moving average, average bucket, cumulative eum, etc
* matrix
  * matrix statsA

* bucket by rating value
```{bucket by rating value}
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"aggs": {
		"ratings": {
					"terms": {
						"field": "rating"
					}
			}
	}
}'
```
* count only 5-star ratings
```{count 5-star}
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"query":{
		"match":{
			"rating":5.0
		}
	},
	"aggs":{
		"ratings":{
				"terms":{
						"field":"rating"
				}
		}
	}
}'
```
* average rating for Star Wars
```{average rating}
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"query":{
		"match_phrase":{
			"title":"Star Wars"
		}
	},
	"aggs":{
		"avg_rating":{
			"avg":{
				"field":"rating"
			}
		}
	}
}'
```

* 1.0 rating intervals histogram
```{histogram}
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"aggs": {
		"whole_ratings":{
			"histogram":{
				"field": "rating",
				"interval": 1.0
			}
		}
	}
}'
```
* count up movies from each decade
```{count up}
curl -XGET '127.0.0.1:9200/movies/movie/_search?size=0&pretty' -d '
{
	"aggs":{
		"release":{
			"histogram":{
				"field":"year",
				"interval":10
			}
		}
	}
}'
```
* break down website hits by hour
```{hits}
curl -XGET '127.0.0.1:9200/logstash-2017.04.30/_search?size=0&pretty' -d '
{
	"aggs":{
		"timestamp":{
			"date_histogram":{
				"field": "@timestamp",
				"interval": "hour"
			}
		}
	}
}'
```
* when does google scrap me?
```{when google}
curl -XGET '127.0.0.1:9200/logstash-2017.04.30/_search?size=0&pretty' -d '
{
	"query":{
		"match":{
			"agent":"Googlebot"
		}
	},
    "aggs":{
        "timestamp":{
            "date_histogram":{
                "field": "@timestamp",
                "interval": "hour"
            }
        }
    }
}'
```

* when did my site go down on 2017.04.30
```{when go down}
curl -XGET '127.0.0.1:9200/logstash-2017.04.30/_search?size=0&pretty' -d '
{
    "query":{
        "match":{
            "response": "500"
        }
    },
    "aggs":{
        "timestamp":{
            "date_histogram":{
                "field": "@timestamp",
                "interval": "minute"
            }
        }
    }
}'
```
* nested aggregation
```{nested}
curl -XPUT '127.0.0.1:9200/ratings/_mapping/rating?pretty' -d '
{
	"properties": {
		"title": {
			"type":	"text",
			"fielddata": true
		}
	}
}'
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"query":{
		"match_phrase":{
			"title": "Star Wars"
		}
	},
	"aggs":{
		"titles":{
			"terms":{
				"field":"title"
			},
			"aggs":{
				"avg_rating":{
					"avg":{
						"field":"rating"
					}
				}
			}
		}
	}
}'
```
* reindex
```{reindex}
curl -XDELETE 127.0.0.1:9200/ratings
curl -XPUT 127.0.0.1:9200/ratings -d '
{
	"mappings":	{
		"rating": {
			"properties": 	{
				"title": {
					"type": "text",
					"fielddata": true,
					"fields": {
						"raw":	{
							"type":	"keyword"
						}
					}
				}	
			}
		}
	}
}'
python3 IndexRatings.py
curl -XGET 127.0.0.1:9200/ratings/_mapping?prettyA
curl -XGET '127.0.0.1:9200/ratings/rating/_search?size=0&pretty' -d '
{
	"query":{
		"match_phrase": {"title": "Star Wars"} 
	},
	"aggs": {
		"titles":{
			"terms":{
				"field": "title.raw"
			},
			"aggs":{
				"avg_rating":{
					"avg":{"field":"rating"}
				}
			}
		}
	}
}'
```

## Kibana

* install
```{install
sudo apt-get install kibana
sudo vi /etc/kibana/kibana.yml
change server.host 0.0.0.0
sudo /bin/systemctl daemon-reload
sudo /bin/systemctl enable kibana
sudo /bin/systemctl start kibana
kibana default port 5601
```
* get the shakespeare data in [this site](https://www.elastic.co/guide/en/kibana/3.0/import-some-data.html)
* create index and analyze
## ELK Stack
logs -> filebeat -> logstash -> elasticsearch -> kibana
ELK Stack : Elasticsearch, Logstash, Kibana

* filebeate and logstash
  * low overload in pipeline
  * flexibility and scalability

* filebeat
```{filebeat install}
sudo apt-get update && apt-get install filebeat
cd /usr/share/elasticsearch
sudo bin/elasticsearch-plugin install ingest-geoip
sudo bin/elasticsearch-plugin install ingest-user-agent
sudo /bin/systemctl stop elasticsearch
sudo /bin/systemctl start elasticsearch
cd /usr/share/filebeat/bin
sudo filebeat setup --dashboards
sudo cd /etc/filebeat/modules.d/
sudo mv apache2.yml.disabled apache2.yml
sudo vi apache2.yml
change access and error log paths to 
["$HOME/logs/access*"]
["$HOME/logs/error*"]

touch $HOME/logs
cd 
wget - download filebeat
sudo /bin/systemctl start filebeat
```

## Elastic Operation
* choosing the number of shards
write - primary -> secondary
read - primary or any replica
* it is not possible to add more shards later without re-indexing
* But, replica shard can be added without reindexing
* consider scaling out in phases, so you have time to re-index before you hit the next phase

* PUT /new\_index
{
	"settings": {
		"number_of_shards": 10,
		"number_of_rplicas":1
	}
}

* multiple indices to hold new data
* search both indices
* use index aliases to make this easy to do

* multiple indices as a scaling strategy
  * with time-base data, you can have one index per time frame
  * current data
  * use index aliases

* alias
```{alias rotation example}
POST /_aliases
{
	"actions": [
		{"add":	{"alias": "log_current", "index": "logs_2017_06"}},
		{"remove": {"alias: "log_current", "index": "logs_2017_05"}}
		{"add": {"alias": "log_last_3_months, "index": log2017_06"}}
		{"remove": {"alias: "log_last_3_months", "index": "logs_2017_03"}}
	]
}

DELET /log_2017_03
```
* Hardware
  * RAM is likely your bottleneck
    * 64GB per machine is the best choice in 64GB OS
  * fast disks are better
  * RAIT0 - if your cluster is already redundant
  * cpu not that importante
  * need a fast network
  * don't use NAS
  * use medium to large considerations: not too big, small

* heap size
  * default heap size is only 1GB
  * half or less of your physical memory should be allocated to elasticsearch
    * the outher half can be used by lucene for caching
```
export ES_HEAP_SIZE=10g
or
ES_JAVA_OPTS="-Xms10g -Xms10g" ./bin/elasticsearch
```

## Recomended Site
* http://www.elastic.com/learn

## Reference
[Elastic Search 6 and Elastic Stack - In Depth and Hands On!](https://www.udemy.com/elasticsearch-6-and-elastic-stack-in-depth-and-hands-on/learn/v4/content)
