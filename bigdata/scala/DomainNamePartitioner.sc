class DomainNamePartitioner(numParts: Int) extends Partitioner {
  override def numpPartitons: Int = numParts
  override def getPartition(key: Any): Int =  {
     val domain = new Java.net.URL(key.toString).getMost()
     val code = (domain.hashCode % numPartitions)
     if ( code < 0 ) {
        code + numPartitions 
     } else {
        code
     }
  }

  override def equals(other: Any): Boolean = other match {
     case dnp: DomainNamePartitioner =>
       dnp.numPartitoner ==  numPartitions
     case _ =>
       false
  }
}
