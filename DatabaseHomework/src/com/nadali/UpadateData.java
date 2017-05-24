package com.nadali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpadateData {

	public static boolean upadateData(UserEntity user) {

		Connection conn = null;
		Statement stmt = null;
		boolean updateStudent = false;
		try {
			Class.forName(DatabasesData.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabasesData.DB_URL, DatabasesData.USER, DatabasesData.PASS);
			stmt = conn.createStatement();

			String query = "UPDATE Students SET first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName() + "', sing_in = '"
					+ user.getSingIn() + "', index_num = '" + user.getIndexNum() + "', city = '" + user.getCity() + "' WHERE id = " + user.getId().get();

			stmt.executeUpdate(query);

			updateStudent = true;
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
		return updateStudent;
	}
}
