package com.example.demo.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Pays;

@Controller
public class Control {
	List<Pays> lpays=new LinkedList<>();
	@SuppressWarnings("deprecation")
	@GetMapping("/tab")
	public String ShowDatabase(Model model) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		System.out.println(1111);
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:Mysql://localhost:3306","root","nonono");
		Statement st=con.createStatement();
		st.addBatch("USE paysdata");
		st.executeBatch();
		ResultSet rs=st.executeQuery("SELECT * from pays");
		lpays.clear();
		while (rs.next()) { 
			lpays.add(new Pays(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
		lpays.toArray();
		model.addAttribute("pay",lpays);
		System.out.println(1222);
		
		return "tab";
		
	}

}
