package com.nadali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReadData {

	public static ObservableList<UserEntity> readMe() {

		Connection conn = null;
		Statement stmt = null;
		ObservableList<UserEntity> allUsers = FXCollections.observableArrayList();

		try {
			Class.forName(DatabasesData.JDBC_DRIVER);

			conn = DriverManager.getConnection(DatabasesData.DB_URL, DatabasesData.USER, DatabasesData.PASS);

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, first_name, last_name, sing_in, index_num, city FROM Students");
			while (rs.next()) {
				int id = rs.getInt("id"); 
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String singIn = rs.getString("sing_in");
				String indexNum = rs.getString("index_num");
				String city = rs.getString("city");

				UserEntity ue = new UserEntity(id, firstName, lastName, singIn, indexNum, city);
				allUsers.add(ue);
			}

			rs.close();
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
		return allUsers;
	}
}
