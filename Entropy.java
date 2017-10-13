import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class Entropy {
        public static void Entropy(String filepath3){  
            //用HashMap存放<单词:词频>这样一个映射关系  
            HashMap<String, Integer> hashMap=new HashMap<String, Integer>();     
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
                double Entropy=0.0;
                double sum=0.0;
                double probability=0.0;
                for(Entry<String, Integer> entry : hashMap.entrySet()){
                    sum+=entry.getValue();
                    probability=entry.getValue()/sum;
                    Entropy+=probability*(Math.log(probability)/Math.log(2));
                }
                System.out.println("文档熵为："+(-Entropy));
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
}   
	public static void main(String argv[]){
		Entropy("/Users/admin1/Desktop/demo2.txt");
    } 
}