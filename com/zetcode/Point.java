package com.zetcode;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Point extends JFrame{
	private Connection conn = null;
	private String[] colname = { "Name", "Point" };
	private DefaultTableModel model = new DefaultTableModel(colname, 0);
	private JTable table = new JTable(model);
	private String row[] = new String[2];
	
	public void Score_sel() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from point");
			while(rset.next()) {
				for(int i=0; i<2; i++) {
					row[i] = rset.getString(i+1);
				}
				model.addRow(row);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void ScoreBoard() {
		setTitle("ScoreBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new JScrollPane(table), BorderLayout.CENTER);
		setSize(300,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Point() {
		try {
			// DB 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "damho_tetris", "1234");
			System.out.println("성공적연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB 연결실패" + e.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결실패" + e.toString());
		}
		Score_sel();
		ScoreBoard();
	}
	
	public static void main(String[] args) {
		new Point();
	}

}
