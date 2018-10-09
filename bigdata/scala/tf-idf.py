from pyspark.mlib.feature import HashingTF, IDF
rdd = sc.wholeTextFiles("data").map(lambda (name, text): text.split())
tf = HashingTF()
tfVectors = tf.transform(rdd).cache()

idf = IDF()
idfModel = idf.fit(tfVectors)
tfIdfVectors  = idfModel.transform(tfVectors)
