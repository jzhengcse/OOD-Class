/**
 * This is intended to demonstrate how to embed Derby engine into a NetBeans project.
 * 
 * The same concept should be applicable to projects that doesn't use NetBeans
 * 
 * To run this project in the NetBeans environment, derby.jar must be added to this project.
 * 
 * The script of initial SQL statement cannot contain any comments.
 * 
 * This program will create a database named demoDB in the current folder and
 *      output a list of courses in the output window (or command line.)
 * 
 * References
 *     Apache Derby Project, Step 3 Embedded Derby
 *              http://db.apache.org/derby/papers/DerbyTut/embedded_intro.html
 * 
 * Date: 12/4/2012
 */
package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * This class doens't do anything. It's just a container for the main method.
 * @author Kuo-Chuan (Martin) Yeh
 */
public class EmbeddedDerbyDemo {
    
    
    public static void main(String[] args) {
        // declare variables we need
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Connection dbConn=null;
        Statement stmt;
        ResultSet results;
        String sql;
        try {
            // start Derby engine
            Class.forName(driver).newInstance();
            
            // get a jdbc connection
            String protocol = "jdbc:derby:";
            String dbName = "demoDB";   // the default database name
            dbConn = DriverManager.getConnection(protocol+dbName+";create=true");
            stmt = dbConn.createStatement();

            // test to see if we need to run the initial sql script
            DatabaseMetaData dbMeta = dbConn.getMetaData();
            String[] tableTypes = {"TABLE"};
            results = dbMeta.getTables(null, null, "%", tableTypes );
            
            if ( !results.next() ) {
                // no table found, we need to execut the initial sql statements
                // first read the sql script
                InputStream is = EmbeddedDerbyDemo.class.getResourceAsStream("initSQL.sql");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    if (line.contains(";")) {
                        sql = sb.toString();
                        sb = new StringBuilder();
                        // executeQuery cannot process ; so remove it from sql
                        sql = sql.substring(0, sql.indexOf(';'));
                        // run sql here
                        if (sql.toUpperCase().contains("SELECT")) {
                            // SELECT statement
                            stmt.executeQuery(sql);
                        }
                        else {
                            // All ohter type of statements
                            stmt.execute(sql);
                        }
                    }
                    line = br.readLine();
                }
            }
            
            // test
            sql = "SELECT * from COURSES";
            results = stmt.executeQuery(sql);
            while (results.next()) {
                System.out.println(results.getString("name"));
            }
            results.close();
            stmt.close();
            dbConn.close();
        }
        // don't have much about exception handling yet
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // could not start Derby engine
            e.printStackTrace();
        }
        catch (SQLException e) {
            // could not connect to Derby
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }      
    }
}
