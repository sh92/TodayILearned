# Machine Learning


* [Lecture Site](https://wikidocs.net/book/587)
* [Andrew ng 강의 정리](https://www.slideshare.net/freepsw/coursera-machine-learning-by-andrew-ng)
  * Logistic Regression
  * Neural Network
  * Support Vector Machine
  * Unsupervised Learning
  * Dimensionality Reduction
  * Anomaly Detection
  * Recommender Systems
  * Large Scale Machine Learning
  * Photo OCR

## Recommender system
* Recommendation
  * [Machine Learning for Recommender systems - part1](https://medium.com/recombee-blog/machine-learning-for-recommender-systems-part-1-algorithms-evaluation-and-cold-start-6f696683d0ed)
    * algorithms
	* evaluation, cold start
  * [Deep Recomendation, Sequential prediction, AutoML, Reinforcement](https://medium.com/recombee-blog/machine-learning-for-recommender-systems-part-2-deep-recommendation-sequence-prediction-automl-f134bc79d66b)
  * [추천시스템이 word2vec을 만났을 때](https://www.youtube.com/watch?v=iutEgQg7yws)
  * [이영석 교수님강의 Slide](https://sites.google.com/cs-cnu.org/datascience/%EC%B6%94%EC%B2%9C?authuser=0)
* Word2vec
  * [More](https://shuuki4.wordpress.com/2016/01/27/word2vec-%EA%B4%80%EB%A0%A8-%EC%9D%B4%EB%A1%A0-%EC%A0%95%EB%A6%AC/)
  * [Word2vec skip-gram model](http://mccormickml.com/2016/04/19/word2vec-tutorial-the-skip-gram-model/)
  * [Amazone recommendationn item-to-item collaborative filtering](https://www.cs.umd.edu/~samir/498/Amazon-Recommendations.pdf)

* Types of recommender system
  * Implicit and Explicit Rating
    * Explicict Rating
	  * star reviews
    * Implicit data give much more information
    * Implicit rating example
	  * things to click on - is not always related with positive response, susceptible to fraud
	  * things you purchase - great to use
	  * things you consume - ex) youtube
* Top-N recommander
  * individual intertests - > cadidate generation < - > item similarities
  * candidate gernerating - > cadndidate ranking -> filtering -> show top-N

* Evaluating Recommendation System
  * Train/Test and K-fold cross validation
  * Accuracy metric - [RMSE, MAE](https://medium.com/human-in-a-machine-world/mae-and-rmse-which-metric-is-better-e60ac3bde13d)
  * Top N Heat Rate
    * leave one out cross validation
	* average reciprocal hit rate (ARHR)
	* cumlative hit rate (cHR)
	* rating hit rate (rHR)
* Coverage : % of \(user, item\) pairs that can be predicted
* Diversity : \(1-S\), S is average simiarylity between pairs
* Novelity : popularity rank of items

* Churn : How often recommenadation change?
  * Novelity, Diviersity vs Churn - trade off
* Responsiveness : How often new user behavior influence your recommendation
  * Simpleness vs Responsiveness - trade off
