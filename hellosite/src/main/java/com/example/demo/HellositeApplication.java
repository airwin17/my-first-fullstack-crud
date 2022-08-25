package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pays;

@SpringBootApplication

public class HellositeApplication {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(HellositeApplication.class, args);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:Mysql://localhost:3306","root","nonono");
			Statement st=con.createStatement();
			st.addBatch("USE paysdata");
			st.executeBatch();
			ResultSet rs=st.executeQuery("SELECT * from pays");
			
			ResultSetMetaData rsmd=rs.getMetaData();
			while (rs.next()) { 
				var p=new Pays(rs.getString(1),rs.getString(2),rs.getString(3));
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if (i > 1) System.out.print(",  ");
					System.out.print(rs.getString(i) + " " + rsmd.getColumnName(i));
				} 
			       System.out.println();
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
