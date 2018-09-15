package com.ComeOut.driver;

public class OracleDriver implements DBdriver{

	@Override
	public void getConnection() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.driver");
	}

}
