package Basic_A0;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Connection conn = null;
        //Statement stmt = null;
        try{
            //STEP 2: Check if JDBC driver is available
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Java_JavaCourse" +
                            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "moony#1423");
            //STEP 4: Execute a query

            String sql = "SELECT * FROM customer WHERE id BETWEEN ? AND ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, 5);

            ResultSet result = ps.executeQuery();

            while(result.next()) {

                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                System.out.println(id + ") Name: " + name + " " + surname);

            }
            //STEP 6: Clean-up environment
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
} 