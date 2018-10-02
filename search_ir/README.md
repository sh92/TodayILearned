# Search
* Search Engine
  * [검색엔진 라이브러리](http://khanrc.tistory.com/entry/%EA%B2%80%EC%83%89%EC%97%94%EC%A7%84-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC)
  * [검색엔진의 원리](http://www.seo-korea.com/%EA%B2%80%EC%83%89%EC%97%94%EC%A7%84%EC%9D%98-%EC%9B%90%EB%A6%AC/)
  * [검색엔진의 작동원리](http://www.aun-korea.com/%E3%80%90%EC%B9%BC%EB%9F%BC02%E3%80%91seo%EA%B8%B0%EC%B4%88%EB%B6%80%ED%84%B0-%ED%95%9C%EA%B1%B8%EC%9D%8C%EC%94%A9-%EA%B2%80%EC%83%89%EC%97%94%EC%A7%84%EC%9D%98-%EC%9E%91%EB%8F%99%EC%9B%90%EB%A6%AC/)
  * [검색엔진 구조](http://librarians.tistory.com/210)
  * [검색엔진의 종류](http://www.zinicap.kr/archives/5273)
  * [Lucene](http://brownbears.tistory.com/6)
  * [Lucene](http://linuxism.tistory.com/898)
  * [Solr](http://mohini.tistory.com/1)
  * [Solr](http://blog.naver.com/PostView.nhn?blogId=parkjy76&logNo=30165989259)
  * [ElastiSearch](https://17billion.github.io/elastic/2017/07/14/elastic_search_overview.html)
  * [Elastic Search vs Solr](http://hochul.net/blog/%EC%98%A4%ED%94%88%EC%86%8C%EC%8A%A4-%EA%B2%80%EC%83%89%EC%97%94%EC%A7%84-%EB%B9%84%EA%B5%90-solr-vs-elasticsearch/)

* [Partitioning vs Sharding](http://theeye.pe.kr/archives/1917)
  * [Shading](https://nesoy.github.io/articles/2018-05/Database-Shard)
  * [Sharding](http://genesis8.tistory.com/211)
  * [Sharding은 어떻게 동작하는가?](http://eminentstar.tistory.com/54)

* PageRank
  * [쉽게 설명한 페이지 랭크 알고리즘](https://sungmooncho.com/2012/08/26/pagerank/)

* [Bag of words](https://springloops.github.io/archivers/Bag_of_words-28BoW-29-Natural_Language_processing_%EB%B0%9C%EB%B2%88%EC%97%AD)

## Information Retrieval
* [awesome-IR]( https://github.com/harpribot/awesome-information-retrieval)
* [Textbook](http://ciir.cs.umass.edu/downloads/SEIRiP.pdf)
* [tinyIR](https://github.com/tonybeltramelli/Information-Retrieval-System)
SearchEngin :  QueryEngine + Document Classfier
Document classifiers에 사용되는 알고리즘들
* Naive Bayes
* Logistic Regression
* Support Vector Machines

* [tf idf란](https://thinkwarelab.wordpress.com/2016/11/14/ir-tf-idf-%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B4%85%EC%8B%9C%EB%8B%A4/)

* [검색엔진을 위한 역색인 설명](https://blog.lael.be/post/3056)
* [역색인](http://giyatto.tistory.com/2)

* 문서 분류 방법
  * knn
  * tf\-idf
  * [naive bayesian](http://bcho.tistory.com/1010)

* Metric
  * [MAP](https://inspace4u.github.io/dllab/lecture/2017/11/07/Mean_Average_Precision.html)
  * [NDCG](http://freesearch.pe.kr/archives/1574)
  * [NDCG vs RMSE](https://datascience.stackexchange.com/questions/369/difference-between-using-rmse-and-ndcg-to-evaluate-recommender-systems)
    * NDCG is proper way if you use implicit rating data such as user consumed
	* RMSE is proper way if you use explicit data
  * [RMSE vs MAE](https://medium.com/human-in-a-machine-world/mae-and-rmse-which-metric-is-better-e60ac3bde13d)
    * RMSE has tendency to be increasing as test samples increase
	  * So, it is important to compare the result on the same test size
	  * RMSE has advantage that it avoid absolute value. So, it could be used other cacluation. 
* [검색시스템의 평가](https://www.slideshare.net/MinsubYim/evaluation-in-ir-system)
* [검색시스템의 평가2](https://www.slideshare.net/ghcho80/information-retrieval-evaluation-52412108?qid=cd07b560-b9b9-4c25-b38a-1be91d1810e4&v=&b=&from_search=1)
