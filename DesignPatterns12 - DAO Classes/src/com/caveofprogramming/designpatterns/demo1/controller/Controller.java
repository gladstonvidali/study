package com.caveofprogramming.designpatterns.demo1.controller;

import java.sql.SQLException;
import java.util.List;

import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.model.Person;
import com.caveofprogramming.designpatterns.demo1.model.PersonDAO;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserEvent;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserListener;
import com.caveofprogramming.designpatterns.demo1.view.View;

public class Controller implements CreateUserListener {

	private View view;
	private Model model;

	private PersonDAO personDAO = new PersonDAO();

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void userCreated(CreateUserEvent event) throws SQLException {

		if ("login".equals(event.getType())) {
			processLogin(event);
		} else if ("create".equals(event.getType()))

		{
			processCreate(event);
		}

	}
	
	private void processLogin(CreateUserEvent event) throws SQLException{
		try {
			System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
			List<Person> person = personDAO.getPeople();
			for (Person p : person) {
				if (p.getName().equals(event.getName())) {
					System.out.println("Logged In!!");
					return;
				}
			}
			System.out.println("Not Logged In!!");

		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	private void processCreate(CreateUserEvent event) throws SQLException{
		try {
			System.out.println("Create event received: " + event.getName() + "; " + event.getPassword());
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}

}
