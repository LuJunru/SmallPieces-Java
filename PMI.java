import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class PMI {
        public static void PMIcalculation(String filepath3){  
            HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
            HashMap<String, Integer> hashLine=new HashMap<String, Integer>();
            try {  
                //读取要处理的文件  
                BufferedReader br=new BufferedReader(new FileReader(filepath3));  
                String value;  
                while((value=br.readLine())!=null){  
                    value=value.replaceAll(filepath3, " ");  
                    //使用StringTokenizer来分词  
                    StringTokenizer tokenizer = new StringTokenizer(value);  
                    while(tokenizer.hasMoreTokens()){  
                        String word=tokenizer.nextToken();  
                        if(!hashMap.containsKey(word)){    
                            hashMap.put(word, new Integer(1));    
                        }else{    
                            int k=hashMap.get(word).intValue()+1;    
                            hashMap.put(word, new Integer(k));    
                        }    
                    }  
                }  
                double PMI;
                System.out.println("互信息PMI：");
                for(Entry<String, Integer> entry : hashMap.entrySet()){//互信息PMI
                    String word1=entry.getKey();
                    double w2Num=entry.getValue();
                    for(Entry<String, Integer> entry1 : hashMap.entrySet()){
                        String word2=entry1.getKey();
                    	if(word1!=word2){
                          double w1Num=entry1.getValue();
                          BufferedReader br1=new BufferedReader(new FileReader(filepath3));  
                          String value1;
                          double w1w2Num=0.0;
                          while((value1=br1.readLine())!=null){  
                              value1=value1.replaceAll(filepath3, " ");
                              if(value1.contains(word1)&&value1.contains(word2)){
                            	  w1w2Num++;
                            	  PMI=Math.log(filepath3.length() * w1w2Num / (w1Num * w2Num)) / Math.log(2);
                                  System.out.println("“"+word1+"”和“"+word2+"”的PMI为："+PMI);
                              }}
                          }                          
                          }
                    	}
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
                }
}   
	public static void main(String argv[]){
		PMIcalculation("/Users/admin1/Desktop/demo2.txt");
    } 
}