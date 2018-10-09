val links = sc.obectFile[(String, Seq[String])]("links").partitionBy(new HashMapPartitioner(100)).persist()

var ranks = links.mapValues(v => 1.0)
for ( i <- 0 until 10) { 
   val contributions = links.join(ranks).flatmap {
      case (pageId, (lins, rank)) =>
         links.map(dest => (dest, rank/links.size))
   }
   ranks = countributions.reduceByKey((x,y) => x+y).mapValues(v => 0.15 + 0.85*v)
}

ranks.saveAsTextFile("ranks")
