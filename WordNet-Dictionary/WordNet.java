package wordnet-dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*WordNet组织方式：词义为节点，边表示意思
 * 寻找两个节点之间的距离：广度优先遍历图，寻找最短路径
 * 取得words数组，分别取出每一个建立到hashmap(WW表)中：word+wordlist
 * 然后读取下一行的每一个单词，不重复就新建一行，把其它所有的复制到它的wordlist中*/

public class WordNet{
	public static void Distance(String filePath){//读入文件；词义用HashMap+包含的单词用String List结构
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
            		HashMap<String,List> MW=new HashMap<String,List>();//大的哈希表<词，词表>
            		//int i=0;//控制输出次数
                    while(((lineTxt = bufferedReader.readLine()) != null)){//读取文件中的一行
                        String [] line=lineTxt.split("\\t");
                        String [] wordlist=line[4].replaceAll("#\\d+", "").split(" ");//取出一行中的第5个值，也就是一个词义下对应的全部词汇，并且把它们建立到词数组中
                        List list=java.util.Arrays.asList(wordlist);//将词数组转变为List类型
                        for(int j=0;j<wordlist.length;j++){//循环读取词数组中的word
                        	if(MW.get(wordlist[j])==null){//如果新的word在哈希表中找不到
                        		for(int k=0;k<wordlist.length;k++){//在已有的词的wordlist中添加不存在的词
                            		if((MW.get(wordlist[k])!=null)&&(MW.get(wordlist[j])!=null)){
                            			MW.get(wordlist[k]).add(wordlist[j]);
                            		}
                        		}
                        		List<Integer> arrayList=new ArrayList<Integer>(list);
                        		arrayList.remove(wordlist[j]);//排除自身值
                        		MW.put(wordlist[j], arrayList);//在哈希表中追加新行
                        	}
                        	}
                        //生成word-word表在txt文件中
                        /*Iterator iterator=MW.keySet().iterator();
                        OutputStream fileOut = new FileOutputStream("/Users/admin1/Desktop/demo3.txt");
                        OutputStreamWriter write = new OutputStreamWriter(fileOut);
                        BufferedWriter bufferedWriter = new BufferedWriter(write);
                        while(iterator.hasNext()){
                        	String word=(String)iterator.next();    
                            bufferedWriter.write("节点单词:"+ word +", 距离为1的单词:" + MW.get(word)+"\n");
                            }
                        bufferedWriter.flush();  
                        bufferedWriter.close();
                        //i++;*/
                    }
                    /*以广度优先遍历输出距离
                     *从起始单词开始往外遍历，先查找距离为1的单词，若有目的单词则输出
                     *没有则距离加1，在list2中保存距离为1的全部单词，再循环查找与这些单词距离为1的单词(距起始单词为2)，先保存在list3中
                     *之后再将list3中全部距离为2的单词放在list2，循环
                     *节约资源：50跳以外不继续查找*/
                    System.out.println("请输入起始单词：");
                    Scanner instart=new Scanner(System.in);                    
                    String str1=instart.next();
                    String str4=str1;
                    System.out.println("请输入目的单词：");
                    Scanner inend=new Scanner(System.in);
                    String str2=inend.next();
                    String str3=str2;//将目的单词固定存储下来
                    List<String> list2=new ArrayList<String>();//存放当前跳数中所有词汇
                    List<String> list3=new ArrayList<String>();//存放下一个跳数中所有词汇
                    int distance=1;
                    list2.add(str1);
                    if((MW.get(str4)!=null)&&(MW.get(str3)!=null)){
                        int j=0;
                    while((list2!=null)&&(distance<50)&&(j==0)){//当前词表不为空时
                    for(int i1=0;i1<list2.size();i1++){
                        str1=list2.get(i1);
                    	if(MW.get(str1).equals(str3)){//哈希表中读取起始单词的距离为1的词表，判断是否有目的单词;
                    		System.out.println("起始单词:"+str4+"和目的单词："+str3+"的距离为："+distance);
                    		j=1;
                    	}else{//没有str2时，在list3中更新str1的词表，作为下一次查询的起点
                    		for(int k=0;k<MW.get(str1).size();k++){//没有时，把该词下一跳词存在list3中
                    			List<String> map=new ArrayList<String>();
                    			map.clear();
                    			map.add((String)MW.get(str1).get(k));
                    			list3.addAll(map);
                    		}
                    	}
                    }//循环list2，直到当前列表中的下一跳都被查找完成
                    list2.addAll(list3);//用list3中的内容覆盖list2中的词表
                	distance++;//list2中词汇都循环一遍后，跳数+1	
                	if(distance==50){
                	System.out.println("起始单词："+str4+"和目的单词："+str3+"在50跳以内不可达");
                	}
                	}
                    }else{
                    System.out.println("输入单词有误！");
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
	public static void main(String argv[]){
		String filepath="/Users/admin1/Desktop/SentiWordNet.txt";
		Distance(filepath);
	}
}
