
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiz5118
 */
public class JDBC {

    private Connection dbConnection;
    private Statement dbStatement;
    private ResultSet dbResultSet;
    private String sql;
    private int menuChoice;
    private Scanner input = new Scanner(System.in); //call system.in to get input
    private int courseID;
    private String department;
    private int number;
    private int credits;
    private String name;

    public JDBC() {
        try {
            dbConnection = null;
            dbStatement = null;
            dbResultSet = null;
            menuChoice = 0;
            courseID = 1300;

            String driver = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(driver).newInstance();
            String protocol = "jdbc:derby:";
            String dbName = "//localhost:1527/Sections";
            dbConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Sections");

            dbStatement = dbConnection.createStatement();

            while (menuChoice != 3) {
                displayMenu();
                menuChoice = input.nextInt();
                input.nextLine();
                if (menuChoice == 1) {
                    addCourse();
                } else if (menuChoice == 2) {
                    viewCourses();
                } 
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("close batabase");
            closeDataBaseObejcts();
        }
    }

    // PRE:
    // POST: display the menu
    public void displayMenu() {
        System.out.printf("Menu: \nChoice 1: Add a courses \nChoice 2: View all existting coruses\nChoice 3: Exit \n\n");
        System.out.printf("Please make a select:\n");
    }
    // PRE:
    // POST: display each course with the course department, number, name and number of credits in the database.

    public void viewCourses() {
        try {
            sql = "select * from courses";
            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {
                System.out.println("Department:" + dbResultSet.getString("dept"));
                System.out.println("Number:" + dbResultSet.getInt("number"));
                System.out.println("Name:" + dbResultSet.getString("name"));
                System.out.println("Credit:" + dbResultSet.getInt("credits"));
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // PRE:
    // POST: adding a course to the database

    private void addCourse() {
        try {
            System.out.println("Enter the department:");
            department = input.nextLine();
            System.out.println("Enter the course number:");
            number = input.nextInt();
            input.nextLine();
            System.out.println("Enter the course name:");
            name = input.nextLine();
            System.out.println("Enter the number of credit:");
            credits = input.nextInt();
            sql = String.format("insert into courses (course_id,dept,number,name,credits) values (%d,'%s',%d,'%s',%d)", courseID, department, number, name, credits);
            dbStatement.execute(sql);
            courseID++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // PRE:
    // POST: close the database objects
    public void closeDataBaseObejcts() {
        try {
            if (dbResultSet != null) {
                dbResultSet.close();
            }
            if (dbStatement != null) {
                dbStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBC app = new JDBC();

    }
}
