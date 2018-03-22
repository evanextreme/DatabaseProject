package database;

import utils.FileUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * File that contains create and populate functions for database tables
 *
 * @author Theodora Bendlin
 */
public abstract class DatabaseTable {

    /**
     * Creates the table using the given connection and file
     * containing the sql specification of the table
     */
    public static void createTable(Connection connection, String fileName) {
        try {
            String createStatement = FileUtils.scanSqlFile(fileName);
            Statement statement = connection.createStatement();
            statement.execute(createStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Makes a query to the given table
     * with given columns and conditions
     */
    public static ResultSet queryTable(Connection conn, String tableName,
                                             ArrayList<String> columns,
                                             ArrayList<String> whereClauses){
        StringBuilder sb = new StringBuilder();


        sb.append("SELECT ");

        if(columns.isEmpty()){
            sb.append("* ");
        }
        else{
            for(int i = 0; i < columns.size(); i++){
                if(i != columns.size() - 1){
                    sb.append(columns.get(i)).append(", ");
                }
                else{
                    sb.append(columns.get(i)).append(" ");
                }
            }
        }

        sb.append("FROM ").append(tableName).append(" ");

        if(!whereClauses.isEmpty()){
            sb.append("WHERE ");
            for(int i = 0; i < whereClauses.size(); i++){
                if(i != whereClauses.size() -1){
                    sb.append(whereClauses.get(i)).append(" AND ");
                }
                else{
                    sb.append(whereClauses.get(i));
                }
            }
        }

        sb.append(";");

        //Print it out to verify it made it right
        System.out.println("Query: " + sb.toString());
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
