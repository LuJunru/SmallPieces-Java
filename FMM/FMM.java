import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FMM {
	private static final List<String> DIC=new ArrayList<String>(); 
    private static int MAX_LENGTH;    
    static{  
        try {
        	int max=1;
            List<String> lines = Files.readAllLines(Paths.get("wordlist.txt"), Charset.forName("utf-8"));  
            for(String line : lines){  
                DIC.add(line);  
                if(line.length()>max){  
                    max=line.length();  
                }  
            }  
            MAX_LENGTH = max;  
        	System.out.println("当前最大分词长度："+MAX_LENGTH);
        } catch (IOException ex) {  
            System.err.println("词典装载失败:"+ex.getMessage());  
        }  
          
    }
    /*正向分词算法*/ 
    public static List forwardSeg(String text){ 
        List result=new ArrayList(); 
        while(text.length()>0){ 
            int len=MAX_LENGTH;       
            if(text.length()<MAX_LENGTH){ 
                len=text.length(); 
            } //取指定的最大长度，文本去字典中匹配 
            String tryWord=text.substring(0, len); 
            while(!DIC.contains(tryWord)){//如果词典中不包含该段文本 
                //如果长度为1的话，且没有在字典中匹配，返回 
                if(tryWord.length()==1){ 
                    break; 
                } 
                //如果匹配不到，则长度减1，继续匹配
                //把最右边的词去掉一个，继续循环        
                tryWord=tryWord.substring(0, tryWord.length()-1);                             
            } 
            result.add(tryWord); 
            //移除该次tryWord，继续循环 
            text=text.substring(tryWord.length()); 
        } 
        return result; 
    } 
    private static int Input() {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) { 
        // TODO Auto-generated method stub 
        List<String> lst=new ArrayList(); 
        lst.add("阿拉伯人孟孔明千秋万代一统江湖"); 
        lst.add("我从未见过有如此厚颜无耻之人");
        lst.add("想你，想花胆信给你");
        lst.add("和尚未");
        for(String str:lst){ 
            List<String> list=forwardSeg(str); 
            String word=""; 
            for(String s:list){ 
                s+="|"; 
                word+=s; 
            } 
            System.out.println(word); 
        } 
      }
    }
