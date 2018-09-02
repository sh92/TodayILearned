# ElasticSearch

## Intro
* The schema for document
  * index
  * type
  * dcoument

* purpose of inverted indices 
  * search in reverse order
  * Quickly map search term to document
  * Load balancing search request across your cluster

* Elasticsearch is built only for full-text search of documents

* [Shard and Replica](http://guruble.com/elasticsearch-2-shard-replica/)
  * shard
    * it could let contents volume to be split horizontally
	* it distributes jobs in serveral shards.It could accelate performence/throughput by parallelizing.
  * replica
    * High Availability
	* It could search in parallel. it accelates performence/throughput.

## Search
* curl
  * curl setting
    * $HOME/bin/curl
	  * !bin/bash
	  * chmod a+x curl
	  * logout -> login
  * Insult
    * curl -XPUT HOST:PORT?PATH -d '{json foramt}'
  * Select
    * curl -XGET HOST:PORT/Path/\_search?pretty
  * Update
    * curl -XPOST HOST:PORT/Path/\_update -d '{json}'
  * DELETE 
    * curl -XDELETE HOST:PORT/Path?pretty
  * REST API END point 
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
  * \+ : %2B
  * ':' : %3A
  * '>' : %3E
  * cryptic and tough to debug
  * fragile

user fileter when you can they faster and cacheable.

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
  ```
  * slop
    * How far you're willing to let a term move to statisfy a phrase 
	  * "Hello Diseny world" would bmatch "Hello world" with a slope of 1
  * proximity queries - results are stored by relevance

* Pagination Syntax
  * curl -XGET HOST:PORT/PATH/\_search?size=2?&from=2&pretty'
  * ```{pagination}
  curl -XGET HOST:PATH/\_search?pretty -d 
  {
  	"from" : 1,
	"size : 3,
	"query" : { "match":{"genre": "Sci-Fi"}}
  }
  ```

* sort
  * curl -XGET HOST:PORT/Path/\_search?sort=year&pretty
  * if string
    * text fields analyzed
    * ```{map an unanalyzed copy using the keyword type}
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
	}
	```
  * unanalyzed "raw" keyword fileds
    * curl -XGET HOST:PORT/Path/\_search?sort=title.raw&pretty
    * Change X
      * Delete, re-index

* Another filter
  * [must, must\_not, filter etc](https://www.elastic.co/guide/en/elasticsearch/reference/5.5/query-filter-context.html)

* fuzzy matches
  * a way to account for typos and mispellings
  * levenshtein edit distance
    * Substitutions of characters
    * Insertion of characters
	* Deletion of characters
  * funzziness parameter
    * in query : ```{fuzzy}
	"fuzzy": {
		"title":{"value":"Super Mario", "funzziness":2}
	}
  * funzziness: AUTO
    * 0 for 1-2 character strings
	* 1 for 3-5 character strings
	* 2 for anything else

* Partial Matching
  * ```{partial}
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
    * ```{wildecard in Query}
	"wildcard": {
		"year": "2*"
	}
	```
* Search as you type
  * ```{in Query}
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
  * indexing n-gram
    1. create an "autocomplete analyzer" 
	  * ```{indexing n-gram}
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
	2. map your filed with it 
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
	}
	```
    3. only use n-gram on the index side
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
	}
	```	
## Reference
[Elastic Search 6 and Elastic Stack - In Depth and Hands On!](https://www.udemy.com/elasticsearch-6-and-elastic-stack-in-depth-and-hands-on/learn/v4/content)
