# Top N hadoop

* Item Freq Class
```{Freq}
public class ItemFreq {
   private String item;
   pritvate Long freq;

   constructer item and freq
   getset - item and freq
}
```

*  PriorityQueue
```{PriorityQueue}
public static class ItemFreqComparator implements Comaparator<ItemFreq>
{
	...
}
```
``` { Comprator instance }
Comparator<ItemFreq> Comparator = new ItemFreqComparator();
PriorityQueue<ItemFreq> queue =  new PriorityQueue<ItemFreq>(10, comparator);
```
* main 
``` { main}
 main method  {
   Configuration conf = new Configuration();
   String[] otherArgs = new GenericOptionParser(conf, args).getRemainingArgs();
   Job job = new Job(conf, "TopN");

   job.setJarByClass(TopN.class);
   job.setOutputKeyClass(Text.class);
   job.setOutputValueClass(LongWritable.class);

   job.setMapperClass(Map.class);
   job.setReducerClass(Reduce.class);
   job.setNumReduceTasks(1);

   job.setInputFormatClass(KeyValueTextInputFormat.class);
   job.setOutputFOrmatClass(TextOutputFormat.class);

   FileInputFormat.addInputPath(job, new Path(args[0]));
   FileOutputFormat.setOutputPath(job, new Path(args[1]));
   job.getConfiguration().setInt("TopN", Integer.parseInt(args[2]));

   job.waitForCompletion(true);
 }
```

* setUp
```{setUp}
int topN = 10;
@Override
protected void setup(Context context) throws IOException, InterruptedException {
   topN = context.getConfiguration().getInt("topN", 10);
}
```


* Insertion in PQ
```{PQ}
public static void insert(PrioritiyQueue, queue, String item, Long lVlaue, int topN) {
   ItemFreq head = (ItemFreq) queue.peek();
   if(queue.size() < topN || head.getFreq() < lValue) {
      if (queue.size() < topN || head getFreq < lValue) {
	     ItemFreq itemFreq = new ITemFreq(item, lValue);
		 queue.add(itemFreq);
	  }
	  if(queue.size() > topN) {
	     queue.remove()
	  }
   }
}
```

* map
```{map}
public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
  Long lValue = (long) Integer.parseInt(value.toString());
  insert(queue, key.toString(), lValue, topN);
}
```

* reduce
```{reduce}
public void reduce(Text key Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
   long sum = 0;
   for(Longwritable val : values) {
      sum+=val.get();
   }
   insert(queue, key.toString(), sum, topN);
}
```

* cleanup
```{cleanUp}
protected void cleanup(Context context) throws IOException, InterruptedException {
   while(queue.size() != 0) {
      ItemFreq item = (ItemFreq) queue.remove();
	  context.write(new Text(item.getItem()), new LongWritable(item.getFreq()));
   }
}
```

## Reference
Do it Hadoop Programming
