package client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Autentificate{
	
	public Autentificate() {
		
	}
	
	public void insert(String name, String password) {
        String sql = "INSERT INTO users(name,password) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public Connection connect() {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch(Exception e) {
			System.out.println(e);
		}
		return c;
	}
	
	public void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	name text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + ");";
    //    String sql = "DELETE FROM users";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public List<String> selectAll(){
        String sql = "SELECT name, password FROM users";
        //String sql = "DROP * FROM users";
        
        List<String> data = new ArrayList<>();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t" +
                                   rs.getString("password"));
                data.add(rs.getString("name") + " " + rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
	}
}
