import utilities.SqlParser;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


//        String s = "E:\\TestFile\\Camila Cabello - Havana (Audio) ft. Young Thug.mp3.001\n";
//        System.out.println(s.substring(s.length()-5));
//        System.out.println(destFileName);
//
//        System.out.println(s.substring(0, s.lastIndexOf('.')).equals(destFileName));
        String fileName = "E:\\TestFile\\Camila Cabello - Havana (Audio) ft. Young Thug.mp3.001";
        String file = "E:\\TestFile\\Ed Sheeran - Shape Of You ( cover by J.Fla ).mp3";
        String destFileName = fileName.substring(0, fileName.lastIndexOf('.'));//remove .{number}

        System.out.println(fileName.substring(fileName.length()-4).trim());
        if(fileName.substring(fileName.length()-4).trim().matches( "[.]\\d+")
                && fileName.substring(0, fileName.lastIndexOf('.')).equals(destFileName))
        {
            System.out.println("Istrue");
        }
    }
}
