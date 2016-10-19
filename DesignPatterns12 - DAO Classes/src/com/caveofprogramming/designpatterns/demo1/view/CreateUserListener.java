package com.caveofprogramming.designpatterns.demo1.view;

import java.sql.SQLException;

public interface CreateUserListener {
	public void userCreated(CreateUserEvent event) throws SQLException;
}
