package utils;

import objects.Customer;
import org.h2.tools.Csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Util file for File operations
 */
public class FileUtils {

    public static String scanSqlFile(String fileName) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = br.readLine()) != null){
                result = result + " " + line;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
