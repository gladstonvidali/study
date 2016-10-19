package com.caveofprogramming.designpatterns.demo1.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * One DAO class person table or view
 * CRUD - Create, retrieve, update, delete
 */


public class PersonDAO {

	public void addPerson(Person person) throws SQLException {
		
		Connection conn = Database.getInstance().getConnection();
		int id = 0;
		
		String sql = "select count(id) as id from person";
		Statement selectStatement = conn.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		if (results.next()){ id = results.getInt("id") + 1; }
		
		PreparedStatement p = conn.prepareStatement("insert into person (id, username, password) values ("+id+",?,?)");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		
		p.executeUpdate();
		
		p.close();
		
	}
	
	public Person getPerson(int id) {
		return null;
	}
	
	public List<Person> getPeople() throws SQLException {
		
		List<Person> people = new ArrayList<Person>();
		
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select username, password from person order by id";
		Statement selectStatement = conn.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);

		
		while(results.next()) {
			String name = results.getString("username");
			String password = results.getString("password");
			
			Person person = new Person(name, password);
			people.add(person);
		}
		
		results.close();
		selectStatement.close();
		
		return people;
	}
	
	public void updatePerson(Person person) {
		
	}
	
	public void deletePerson(int id) {
		
	}
}
