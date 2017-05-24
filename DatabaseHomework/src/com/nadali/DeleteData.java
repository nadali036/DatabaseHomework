package com.nadali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {

	public static boolean deleteUser(int id) {

		Connection conn = null;
		Statement stmt = null;

		boolean userdeleted = false;
		try {
			Class.forName(DatabasesData.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabasesData.DB_URL, DatabasesData.USER, DatabasesData.PASS);

			stmt = conn.createStatement();

			String sql = "DELETE FROM Students " + "WHERE id =" + id;
			stmt.executeUpdate(sql);
			
			userdeleted = true;
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
		
		return userdeleted;
	}
}
