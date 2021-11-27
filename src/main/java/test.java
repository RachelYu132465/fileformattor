import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\defaultuser\\Desktop\\AA.txt");


//            PrintWriter out = new PrintWriter(file, "UTF-8");
//            out.print(FileUtils.readFileToString(file, "utf-8"));
//            out.close();
//            Path p = Paths.get("file.txt");

            ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(file.toPath()));
            CharBuffer cb = Charset.forName("Big5-HKSCS").decode(bb);
            bb = Charset.forName("UTF-8").encode(cb);
            Files.write(file.toPath(), bb.array());


//        FileInputStream in = new FileInputStream(file);
//        byte[] b = new byte[3];
//        in.read(b);
//        String code = "GBK";
//        if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
//            code = "UTF-8";
//        }
//
//
//        InputStreamReader inputStreamReader = new InputStreamReader(in, code);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String str = bufferedReader.readLine();
//        String data = "";
//        while (str != null) {
//            data = data + str + "\r\n";
//            str = bufferedReader.readLine();
//        }
//        in.close();
//        bufferedReader.close();
//        System.out.println(code);
//        System.out.println(data);

    }

               catch(Exception e){
        e.printStackTrace();
    }
}
}