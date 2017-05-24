package com.nadali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveData {

	public static boolean saveData(String firstName, String lastName, String singIn, String indexNum, String city) {

		Connection conn = null;
		Statement stmt = null;
		boolean studentAddedNormal = false;
		try {
			Class.forName(DatabasesData.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabasesData.DB_URL, DatabasesData.USER, DatabasesData.PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select MAX(id) as maxId from Students");
			int maxId = 0;
			if (rs.next()) {
				maxId = rs.getInt("maxId");
			}

			maxId++;

			String query = "INSERT INTO Students (id, first_name, last_name, sing_in, index_num, city)" 
					+ " VALUES (" + maxId + ", '" + firstName + "', '" + lastName + "', '" + singIn + "', '" + indexNum + "', '" 
					+ city +"')";

			stmt.executeUpdate(query);

			studentAddedNormal = true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return studentAddedNormal;
	}
}
