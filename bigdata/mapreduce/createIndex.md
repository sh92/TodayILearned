# Create Index in ElasticSearch

* map
```{Mapper}
public static class MyMapper extends Mapper<Text, Text, Text, Text> {
   public void setup(Context context) {
      String []hosts = context.getConfiguration().getStrings("ESServer", "localhost");
	  baseUrl = "http://"+host[0]+":9200/wikipedia/doc/";
   }
}

public void map(Text docID, Text value, Context context) throws IOException, InterruptedException {
   String line = value.toString();
   line = line.replace("\\","\\\\");
   line = line.replace("\"", "\\\"");
   URL url = new URL(baseURL+docID.toString());
   HttpURLConnectionhttpCon = (HttpURLConnection) url.openConnection();
   httpCon.setDoOutput(true);
   httpCon.setRequestMethod("PUT");

   String content = "{\"body\": \" "+ line +"\"}";
   OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
   out.write(content);
   out.close();

   String inputLine = "", inputLines="";
   BufferReader in = new BufferReader(new InputStreamReader(httpCon.getInputStream()));
   while((inputLine = in.readLine()) != null) {
      inputLines += inputLine;
      if(inputLines.indexOf("\"ok\":true") < 0) {
         context.getCounter("Stats", "Error Docs").increment(1);
      } else {
         context.getCounter("Stats", "Success").increment(1);
      }
      in.close();
      httpCon.disconnect();
   }
}
```

* main
```{main}
public stati void main(String[] args) throw Exception {
   Configuration conf = new Configuration();
   Job job =  new Job(conf, "CreateESIndex");
   job.setJarByClass(CreateESIndex.class);
   
   job.setOutputKeyClass(Text.class);
   job.setOUtputValueClass(Text.class);

   job.setMapperClass(MyMapper.class);

   job.setInputFormatClass(KeyValueTextInputFomat.class);
   job.setOutputFormatClass(TextOutputFormat.class);
   job.setNumReduceTasks(0);

   job.getConfiguration.setStrings("ESServer", args[2]);

   job.waitForCompletion(true);
}
```

## Reference
Do it Hadoop programming
