import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static common.FileHandler.getFileNameWoExt;
import static common.FileHandler.getFilesByKeywords;

public class dateTimeCovertor {



    public static void main(String[] args) {
        List<String> tableNames =new ArrayList<>();

        List<File> files =getFilesByKeywords("C:\\Users\\defaultuser\\Desktop\\backup","");

        for (File f: files) {
//        File f = new File("C:\\Users\\defaultuser\\Desktop\\AA.txt");
            if (FilenameUtils.getExtension(f.getName()).equals("txt")) {

//                File log= new File("log.txt");
                String search = "下午";
                String search2 = "上午";
                String replace = "";
                StringBuffer stringBuffer = new StringBuffer();
                String totalStr = "";
                try {

                    System.out.println(" running :" + f.getName());
                    ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(f.toPath()));
                    CharBuffer cb = Charset.forName("Big5-HKSCS").decode(bb);
                    bb = Charset.forName("UTF-8").encode(cb);
                    Files.write(f.toPath(), bb.array());

//                    FileReader fr = new FileReader(f);
//                    String s;
//
//                    Reader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "US-ASCII"));
                    Reader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
//                    Reader reader = new InputStreamReader(new FileInputStream(f), StandardCharsets.);
                    char[] buff = new char[500];
                    for (int charsRead; (charsRead = reader.read(buff)) != -1; ) {
                        stringBuffer.append(buff, 0, charsRead);
                    }
                    totalStr = stringBuffer.toString();
//                    System.out.println(totalStr);


                    while (totalStr.indexOf((search)) != -1) {
                        int search2_beginIndex = totalStr.indexOf(search);
                        int beginTimeIndex = search2_beginIndex + 3;
                        int lastTimeIndex = beginTimeIndex + 2;
                        String temp = totalStr.substring(beginTimeIndex, lastTimeIndex);
                        int newTime = Integer.valueOf(temp) + 12;
                        String updateStr = totalStr.substring(0, search2_beginIndex) + +newTime + totalStr.substring(search2_beginIndex + 5);

                        totalStr = updateStr;

                    }

                    while (totalStr.indexOf((search2) ) != -1){
                        int search2_beginIndex = totalStr.indexOf(search2);
                        int beginTimeIndex = search2_beginIndex + 3;
                        int lastTimeIndex = beginTimeIndex + 2;
                        String temp = totalStr.substring(beginTimeIndex, lastTimeIndex);

                        String updateStr = totalStr.substring(0, search2_beginIndex)  + totalStr.substring(search2_beginIndex + 3);

                        totalStr = updateStr;

                    }
                    //if ()
//                        totalStr = totalStr.replaceAll(search, replace);
//                    FileWriter fw = new FileWriter(f);
                    BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));

                    fw.write(totalStr);
                    fw.close();




                }
catch (OutOfMemoryError e) {


}
               catch(Exception ee){
                   System.out.println("error when running :" + f.getName());
                    ee.printStackTrace();
                }

            }


        }
}
}
