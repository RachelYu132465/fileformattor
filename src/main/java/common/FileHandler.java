package common;


import org.apache.commons.io.FilenameUtils;


import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class FileHandler {
    public static final String proj_path = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/";

    public static boolean moveFileToSubFolder(String path, String subfolderName, File f) {
        File theDir = new File(path + "/" + subfolderName);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        if (!f.isFile()){
            System.out.println("!f.isFile()");
        }
        if (f.exists() && f.isFile()) {
            f.renameTo(new File(path + "/" + subfolderName+ "/"+f.getName()));
//            f.renameTo(new File("C:\\Users\\tina.yu\\Desktop\\biotin_and_Eng\\test1.xlsx"));

            System.out.println("move file to "+path + "/" + subfolderName+ "/"+f.getName());
            return true;
        }
        return false;
    }




    public static List<File> getFilesByKeywords(String dir, String keyword) {
        File f = new File(dir);
        List<File> matchingFiles = new ArrayList();
        if (f.exists() && f.isDirectory()) {
            File arr[] = f.listFiles();
            for (File filesInNestedDir : arr) {
                matchingFiles.addAll(getFilesByKeywords(filesInNestedDir.getAbsolutePath(), keyword));
            }
        } else {
            if (f.getName().contains(keyword)) {
                matchingFiles.add(f);
                return matchingFiles;
            }
        }
        return matchingFiles;
    }


    public static String getFileNameWoExt(String FileName) {
        return FileName.split("\\.")[0];
    }

    public static File create_save(String fileAbsolutePath, String content) {
        try {
            File theDir = new File(fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf("/")));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File file = new File(fileAbsolutePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return  file;
        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
       return null;
    }

    public static String readFromFile(String path) {

        File file = new File(path);
        return readFromFile(file);
    }

    public static String readFromFile(File file) {
        StringBuilder result = new StringBuilder();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
//                result.append(myReader.nextLine().replace(" ",""));
                result.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result.toString();
    }
}
