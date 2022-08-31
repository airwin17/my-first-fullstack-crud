package com.example.demo.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Message;
import com.example.demo.model.Pays;

@Controller
public class Control {
	
	List<Pays> lpays=new LinkedList<>();
	@SuppressWarnings("deprecation")
	@GetMapping("/tab")
	public String ShowDatabase(Model model) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
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
		model.addAttribute("pays",lpays);
		model.addAttribute("addpays",new Pays("","",""));
		
		return "tab";
		
	}
	@PostMapping("/addpays")
	@ResponseBody
	public Message addPays(@RequestBody Pays pay) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:Mysql://localhost:3306","root","nonono");
		Statement st=con.createStatement();
		System.out.println(pay.name);
		System.out.println(pay.capitale);
		System.out.println(pay.population);
		st.addBatch("USE paysdata");
		st.addBatch("INSERT INTO pays "+"VALUES ('"+pay.name+"','"+pay.capitale+"','"+pay.population+"');");
		Message message=new Message("succes");
		st.executeBatch();
		return message;
	}
	@DeleteMapping("/deletepays")
	@ResponseBody
	public Message deletePays(@RequestBody Pays pay) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println(151);
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:Mysql://localhost:3306","root","nonono");
		Statement st=con.createStatement();
		System.out.println(pay.name);
		System.out.println(pay.capitale);
		System.out.println(pay.population);
		st.addBatch("USE paysdata");
		st.addBatch("DELETE FROM pays WHERE name='"+pay.name+"';");
		st.executeBatch();
		Message message=new Message("succes");
		return message;
		
	}

}
