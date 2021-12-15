package application;

import java.io.*;
import java.sql.*;

import dbUtils.DbConnection;

public class DbStatement {

	private final String TABLE_NAME = "monthly_expenses";
	private final String COLUMN_ID = "id"; 
	private final String COLUMN_CATEGORY = "category";
	private final String COLUMN_EXPENSES = "expenses";
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	public void createTable() throws SQLException {
		
		String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +  
				"(" + 
				  COLUMN_ID + " INTEGER PRIMARY KEY, "
				+ COLUMN_CATEGORY + " TEXT NOT NULL, "
				+ COLUMN_EXPENSES + " REAL NOT NULL "
				+")";
		
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(createTable);
			
			if (preparedStatement != null) {
				preparedStatement.execute();
			}
			
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			connection.close();
		}
		
	}
	
	public void setInitialValues() throws IOException, SQLException {
		
		String setInitialValues = "INSERT INTO " + TABLE_NAME +
		"(" + COLUMN_EXPENSES +  ", " + COLUMN_CATEGORY + " )" +
				" VALUES(?, ?) ";
		
		System.out.println("Add category...");
		String category = reader.readLine();
		System.out.println("Add initial expenses...");
		Double expenses = Double.parseDouble(reader.readLine());
		
		executeQuery(setInitialValues, category, expenses);
		
	}
	
	public void updateValue() throws IOException, SQLException {
		
		String updateValue = "UPDATE " + TABLE_NAME + " SET " + COLUMN_EXPENSES + 
				" = " + COLUMN_EXPENSES + " + " + " ? " + " WHERE " + COLUMN_CATEGORY + " = " + " ? ";

		
		System.out.println("Which category do you want to update?");
		String category = reader.readLine();
		System.out.println("Please enter the new amount...");
		double value = Double.parseDouble(reader.readLine());
		
		executeQuery(updateValue, category, value);
		
	}
	
	public void executeQuery(String query, String category, double expenses) throws SQLException {
		
		
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(2, category);
			preparedStatement.setDouble(1, expenses);
			
			if (preparedStatement != null) {
				preparedStatement.executeUpdate();
			}
			
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}connection.close();
		}
	}
	
	
	public void getData() throws NumberFormatException, IOException, SQLException {
		
		System.out.println("Dou you want to know monthly expenses or specific category? Choose 1 for all or 2 for a category");
		
		int choose = Integer.parseInt(reader.readLine());
		
		switch (choose) {
		case 1:
			getAllExpenses();
			break;
		case 2:
			getCategoryExpenses();
			break;
		}
		
	}
	
	public void getAllExpenses() throws SQLException {
		
		String getAllExpenses= "SELECT " + "SUM(expenses)" + " FROM " + TABLE_NAME;
		
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(getAllExpenses);
			
			if (preparedStatement != null) {
				resultSet= preparedStatement.executeQuery();
				
				System.out.println("All expenses: " + resultSet.getDouble(1) + " Euro" );
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			} connection.close();
		}
	}
	
	public void getCategoryExpenses() throws IOException, SQLException {
		
		System.out.println("Which category? Petrol, supermarket, extras or food delivery?");
		String category= reader.readLine();
		
		String getCategoryExpenses = "SELECT " + COLUMN_EXPENSES + " FROM " + TABLE_NAME + " WHERE " + 
		COLUMN_CATEGORY + " = " + "? ";
		
		
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(getCategoryExpenses);
			preparedStatement.setString(1, category);
			
			if (preparedStatement != null) {
				resultSet= preparedStatement.executeQuery();
				
				System.out.println("Expenses for " + category + " " + resultSet.getDouble(1) + " Euro" );
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			} connection.close();
		}
		
	}
	
	public void getAllData() throws SQLException {
		
		String getAllData= "SELECT * " + " FROM " + TABLE_NAME;
		System.out.println(getAllData);
		
		
		try {
			connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement(getAllData);
			
			if (preparedStatement != null) {
				resultSet= preparedStatement.executeQuery();
				
			while (resultSet.next()) {
				System.out.println(resultSet.getString(COLUMN_CATEGORY) + " " + resultSet.getDouble(COLUMN_EXPENSES));
			}
			
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			} connection.close();
		}
		
	
	}

}
