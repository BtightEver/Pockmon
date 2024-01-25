import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class qimo {
    public static void main(String args[]) throws IOException,NumberFormatException {
        FileReader fr=new FileReader("22.txt");
        System.out.print("句子一：");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        String[] input2=input.split(" ");
        System.out.println(input2[0]+"-------"+input2[1]);
        int i=0;
        int j=0;
        for(;i<input.length();i++){
            if(input.charAt(i)<='Z'&&input.charAt(i)>='A'){
             j++;
            }
        }
        System.out.println("字符串长度"+Integer.toString(input.length()));
        System.out.println("句子二"+Integer.toString(j));
        /*ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("张三");
        arrayList.get(0);
        arrayList.size();
        BufferedReader buf= new BufferedReader(new InputStreamReader(System.in));
        String str =buf.readLine();
       System.out.println("啊哈哈"+str);*/
    }
}
