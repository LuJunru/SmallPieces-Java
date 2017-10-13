import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class WordFrequency {
	public static void HtmlToTxt(String filePath){
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
                return;
                } else {
                	for (File file2 : files) {
                		if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        HtmlToTxt(file2.getAbsolutePath());
                        } else {
                        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
                        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
                        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
                        String regEx_num="00[^>][^>]"; //定义标签中数字的正则表达式
                        String regEx_space = "\\s*|\t|\r|\n[a-zA-Z]";//定义空格回车换行字母的正则表达式
                        InputStreamReader read;
						try {
							read = new InputStreamReader(new FileInputStream(file2),"UTF8");//考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
                            String lineTxt = null;
                            while((lineTxt = bufferedReader.readLine()) != null){
                            	lineTxt=lineTxt.replaceAll(regEx_script, "");
                                lineTxt=lineTxt.replaceAll(regEx_style, "");
                                lineTxt=lineTxt.replaceAll(regEx_html, "");
                                lineTxt=lineTxt.replaceAll(regEx_space, "");
                                lineTxt=lineTxt.replaceAll(regEx_num, "");
                                FileWriter writer = new FileWriter("/Users/admin1/Desktop/demo.txt", true);
                                writer.write(lineTxt+"\n");
                                try {     
					                if(writer != null){  
					                    writer.close();     
					                }  
					            } catch (IOException e) {     
					                e.printStackTrace();     
					                }
                                }
                            read.close();
                            } catch (IOException e) {
                            	// TODO Auto-generated catch block
								e.printStackTrace();
								}
						}
                		}
                	}
			} else {
				System.out.println("文件不存在!");
				}     
		}  
	public static void IKSegment(String filePath2){  
		String news=new String();  
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath2), "UTF8")); 
            String str;  
            while ((str = in.readLine()) != null) {  
            	news+=str;  
            	}  
            in.close();    
            OutputStream fileOut = new FileOutputStream("/Users/admin1/Desktop/demo2.txt");
            OutputStreamWriter write = new OutputStreamWriter(fileOut);
            BufferedWriter bufferedWriter = new BufferedWriter(write);  
            StringReader re = new StringReader(news);  
            IKSegmenter ik = new IKSegmenter(re,true);  
            Lexeme lex = null;  
            while((lex=ik.next())!=null){
               bufferedWriter.write(lex.getLexemeText()+"\n");
               }
            bufferedWriter.flush();  
            bufferedWriter.close();
            }catch (IOException e) {
            	//TODO Auto-generated catch block
            	e.printStackTrace();
            	}  
		}
	public static void WordFrequency(String filepath3){  
		//用HashMap存放<单词:词频>这样一个映射关系  
		HashMap<String, Integer> hashMap=new HashMap<String, Integer>();     
        try {  
        	//读取要处理的文件  
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
                //遍历HashMap,输出结果  
        	Iterator iterator=hashMap.keySet().iterator();
        	OutputStream fileOut = new FileOutputStream("/Users/admin1/Desktop/demo3.txt");
        	OutputStreamWriter write = new OutputStreamWriter(fileOut);
            BufferedWriter bufferedWriter = new BufferedWriter(write);
            bufferedWriter.write("词语"+"    "+"词频"+"\n");
            while(iterator.hasNext()){
            String word=(String) iterator.next();    
            bufferedWriter.write(word+":\t"+hashMap.get(word)+"\n");
            }
            bufferedWriter.flush();  
            bufferedWriter.close();
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
                }
        }   
	public static void main(String argv[]){
		HtmlToTxt("/Users/admin1/Desktop/FormalRun");
        IKSegment("/Users/admin1/Desktop/demo.txt");
        WordFrequency("/Users/admin1/Desktop/wordlist.txt");
        System.out.println("Over!");//提示是否完成运行
        } 
	}