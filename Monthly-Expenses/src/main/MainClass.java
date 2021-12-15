package main;

import application.DbStatement;
import dbUtils.DbConnection;

public class MainClass {
	
public static void main(String[] args) {
		
		DbConnection dbConnection = new DbConnection();
		DbStatement dbStatement = new DbStatement(); 
		
		try {
			dbConnection.getConnection();
			System.out.println("Connected.");
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		try {
			dbStatement.createTable();
			System.out.println("A new table has been created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			dbStatement.setInitialValues();
			System.out.println("A new category has been added.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			dbStatement.updateValue();
			System.out.println("Category has been updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			dbStatement.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			dbStatement.getAllData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
