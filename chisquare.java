import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class chisquare{
	public static void chisquare(String filepath3){    
		HashMap<String, Integer> hashMap=new HashMap<String, Integer>();     
            try {  
                BufferedReader br=new BufferedReader(new FileReader(filepath3));  
                String value;  
                while((value=br.readLine())!=null){  
                    value=value.replaceAll(filepath3, " ");  
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
                double chisquare;
                System.out.println("卡方值：");
                for(Entry<String, Integer> entry : hashMap.entrySet()){//如何实现连续字？
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
                                  double w1w2Abs = 252-w1w2Num;
                                  chisquare=(w1Num+w2Num+w1w2Num+w1w2Abs)*Math.pow(w1w2Num*w1w2Abs-w1Num*w2Num,2)/((w1w2Num+w1Num)*(w1w2Num+w2Num)*(w1w2Abs+w1Num)*(w1w2Abs+w2Num));
                                  System.out.println("“"+word1+"”和“"+word2+"”的卡方值为："+chisquare);
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
		chisquare("/Users/admin1/Desktop/wordlist.txt");
    } 
}