import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import static common.FileHandler.getFileNameWoExt;
import static common.FileHandler.getFilesByKeywords;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/labware_admin";

    static final String USER = "root";
    static final String PASS = "tanya";
    static final String QUERY = "SELECT id, first, last, age FROM Employees";


    public static void main(String[] args) {
        List<String> tableNames =new ArrayList<>();

        List<File> files =getFilesByKeywords("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Data\\labware_admin\\","");
for (File f: files){
    if (FilenameUtils.getExtension(f.getName()).equals("txt")){
        tableNames .add (getFileNameWoExt(f.getName()));
    }


        }

        // Open a connection
        try {

                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            for (String tableName : tableNames) {
//                String sql_0 ="ALTER TABLE "+ tableName+" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
                String sql =
                        "LOAD DATA  INFILE '" +
                                tableName +".txt' INTO TABLE  labware_admin."+ tableName + "\nFIELDS TERMINATED BY ','" +

                                "\n LINES TERMINATED BY '\\r\\n'" +
                                "\n IGNORE 1 LINES;";
                System.out.println(sql);
//                System.out.println(sql_0);
                stmt.execute(sql);
                System.out.println("Database created successfully...");
            }
        }catch (ClassNotFoundException|SQLException e) {
                e.printStackTrace();
            }

        }


}


/*

LOAD DATA LOCAL INFILE 'C:\ProgramData\MySQL\MySQL Server 8.0\ACCESS_ENTRY.txt' INTO TABLE  labware_admin.ACCESS_ENTRY
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
 */