# Recommender System
* Recommendation
  * [Machine Learning for Recommender systems - part1](https://medium.com/recombee-blog/machine-learning-for-recommender-systems-part-1-algorithms-evaluation-and-cold-start-6f696683d0ed)
    * algorithms
    * evaluation, cold start
  * [Deep Recomendation, Sequential prediction, AutoML, Reinforcement](https://medium.com/recombee-blog/machine-learning-for-recommender-systems-part-2-deep-recommendation-sequence-prediction-automl-f134bc79d66b)
  * [추천시스템이 word2vec을 만났을 때](https://www.youtube.com/watch?v=iutEgQg7yws)
    * [Word2vec이란](https://ratsgo.github.io/natural%20language%20processing/2017/03/08/word2vec/)
  * [이영석 교수님강의 Slide](https://sites.google.com/cs-cnu.org/datascience/%EC%B6%94%EC%B2%9C?authuser=0)
* Word2vec
  * [More](https://shuuki4.wordpress.com/2016/01/27/word2vec-%EA%B4%80%EB%A0%A8-%EC%9D%B4%EB%A1%A0-%EC%A0%95%EB%A6%AC/)
  * [Word2vec skip-gram model](http://mccormickml.com/2016/04/19/word2vec-tutorial-the-skip-gram-model/)
  * [Amazone recommendationn item-to-item collaborative filtering](https://www.cs.umd.edu/~samir/498/Amazon-Recommendations.pdf)

#### Types of recommender system
* Implicit and Explicit Rating
  * Explicict Rating
    * star reviews
  * Implicit Rating
    * Implicit data give much more information
    * Implicit rating example
      * things to click on - is not always related with positive response, susceptible to fraud
      * things you purchase - great to use
      * things you consume - ex) youtube

#### Top-N recommander
* individual intertests - > cadidate generation < - > item similarities
* candidate gernerating - > cadndidate ranking -> filtering -> show top-N

#### Evaluating Recommendation System
* Train/Test and K-fold cross validation
* Top N Hit Rate
  * leave one out cross validation
  * average reciprocal hit rate (ARHR) - it accounts for top-N recommenders
  * cumlative hit rate (cHR)
  * rating hit rate (rHR)

* Offline Mettrics
  * Coverage : % of \(user, item\) pairs that can be predicted
  * Diversity : \(1-S\), S is average simiarylity between pairs
  * Novelity : popularity rank of items
  * Accuracy : metric - [RMSE, MAE](https://medium.com/human-in-a-machine-world/mae-and-rmse-which-metric-is-better-e60ac3bde13d)
    * RMSE - netflix prize

* Churn : How often recommenadation change?
  * Novelity, Diviersity vs Churn - trade off
* Responsiveness : How often new user behavior influence your recommendation
  * Simpleness vs Responsiveness - trade off
  * Complex system is diifficult to maintain
* A/B Test - matters more than anything
* Perceived rating - thumbs up/down

#### Contents-based filtering
* Contents-based recommendation
* Cosine Similarity
* KNN, Contents Recsys
  * Similarity scores between this items and all others ther user rated ->(sort) Top40 nearest movies ->(weighted average) rating prediction


#### Neighborhood-based collaborative filtering
* individual interests -> candidate generation <-> item simiarities
* candidate generation -> candidate ranking -> filtering -> items

* Similarity metrics
  * Cosine
  * Adjusted cosine =  sum\(\(\_x - xi\)\(\_y-yi\) /  \(sqrt\(sum\(xi-\_x\)\)+sqrt\(sum\(yi-\_y\)\)\)
    * \_x : mean of xi
    * \_y : mean of yi
  * Pearson
  * Spearman
  * Mean square difference
  * Jaccard similiarity

* User-based collaborative filtering
  * user -> item rating matrix
  * user -> user similarity matrix
  * look up similar users
  * candidate generation
  * candidate scoring
  * candidate filtering

* Item-based collaborative filtering
  * item -> item rating of users
  * item and item cosine silmilarity

* User-based KNN
  * find the k most similar users
  * compute mean sim socre weighted by ratings
  * ratings prediction

* Item-base KNN
  * find the k most similar itmes
  * compute mean sim socre wieghted by ratings
  * ratings prediction

* Result
  * user-based
    * pearson > cosine > msd
  * item-based
    * KNNWithZScore > KNNWithMeans > KNNWithBaseLine

### Matrix Factorization
* [Matrix Factorization?](http://sanghyukchun.github.io/73/)
* PCA
* SVD
* SVD++
* Stochastic gradient descent <- ALS
* SLIM

#### Deep Learning with Recommand System
* [모두를 위한 딥러닝](https://hunkim.github.io/ml/)
* [Deep Learning overview](https://arxiv.org/pdf/1404.7828v4.pdf)
* [Cross entropy, softmax, cost](http://neuralnetworksanddeeplearning.com/chap3.html)
* [CNN](http://ufldl.stanford.edu/tutorial/supervised/ConvolutionalNeuralNetwork/)
* [RBM](https://www.cs.toronto.edu/~rsalakhu/papers/rbmcf.pdf)
  * [Introduction to Restricted Boltzmann Machines](http://blog.echen.me/2011/07/18/introduction-to-restricted-boltzmann-machines/)

* Auto-Encoder
* Click Stream Recommandatiaon with RNN
  * [Session-Based Recommendation with Recurrent neural networks](https://arxiv.org/pdf/1511.06939.pdf)
    * GRU -> GRU4REC(gated recurrent unit)
	  * session-paralle mini-batches
	  * sampling the output
	  * ranking loss
	  * BPR\(Bayesian Personalized Rankina\)
	* [GRU4Rec github](https://github.com/Songweiping/GRU4Rec_TensorFlow)

* [Translated recommendation problem](https://sites.google.com/view/ruining-he/)
* ALS
  * [ALS Collaborative Filtering](https://medium.com/radon-dev/als-implicit-collaborative-filtering-5ed653ba39fe)
  * [The alternating least squares algorithm](https://www.infofarm.be/articles/alternating-least-squares-algorithm-recommenderlab)

* Bleeding Edge 
  * [DeepFM\(Deep Factorization Machine\)](https://arxiv.org/pdf/1703.04247.pdf)
    * higher feature interations
  * Recommendation with Word2vec
  * 3D cnn's for session based recsys
    * specific situationn : click stream 

* When you use Deep Learning, Consideration
  * Speed and Accuracy
  * Hardware cost
    * memory usage
	* search speed
  * Rendering time in web
#### Scalable Recommand System

* Spark
* AWS
  * [Generating Recommendations at Amazon Scale with Apache Spark and Amazon DSSTNE](https://aws.amazon.com/ko/blogs/big-data/generating-recommendations-at-amazon-scale-with-apache-spark-and-amazon-dsstne/)
  * build -> train -> deploy
  * sagemaker
  * load ml-1m ratings ->  one-hot encode user & momvie -> build binary label vector -> conver to protobuf & write to s3 -> train, delpoy, predict

#### Consideration in Real World

* Stoplist
  * list that should not be contained.
    * ex) illegal contents, dult-oriented content, drug use, religion, terror
* Filter bubble
* Transparency and Trust
* Outlier
* Click Stream is unreliable
* You should be careful about using personal information
* Value-aware recommendation

* Case Study
  * [Facebook Group Recommender system](https://www.researchgate.net/publication/220830049_Group_Recommendation_System_for_Facebook)
  * [Facebook Collaborative Filtering](https://code.fb.com/core-data/recommending-items-to-more-than-a-billion-people/)
  * [An Online News Recommender System for Social Network](https://pdfs.semanticscholar.org/f521/98651a339ca89408b300e777e98ee08f49dd.pdf)
  * [Deep Neural Networks for Youtube Recommendation](https://static.googleusercontent.com/media/research.google.com/ko//pubs/archive/45530.pdf)
  * [Netflix Recsys paper 2018](https://beta.vu.nl/nl/Images/werkstuk-fernandez_tcm235-874624.pdf)
  * [Amazon Recsys paper](https://www.cs.umd.edu/~samir/498/Amazon-Recommendations.pdf)

* Hybrid Approaches
  * ensemble approaches
    * 107 different algorithms
	* RBM and SVD\+\+
	* GRU4Rec(gated recurrent unit)
	* RBM and ContentKNN
	* etc

* [Netflix prize summary](http://blog.echen.me/2011/10/24/winning-the-netflix-prize-a-summary/)

* [추천시스템 기초](https://datascienceschool.net/view-notebook/fcd3550f11ac4537acec8d18136f2066/)

### Reference
[Recommender system with Machine Learning](https://www.udemy.com/building-recommender-systems-with-machine-learning-and-ai/learn/v4/content)
