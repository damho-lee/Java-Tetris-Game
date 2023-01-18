package com.zetcode;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Point extends JFrame{
	private Connection conn = null;
	private String[] colname = { "Name", "Point" };
	private DefaultTableModel model = new DefaultTableModel(colname, 0);
	private JTable table = new JTable(model);
	private String row[] = new String[2];
	private Statement stmt = null;
	private ResultSet rset = null;
	private int min;
	public void Score_sel() {
		try {
			String sql = "select * from (select * from point order by point desc, name) where ROWNUM <=20 order by point desc"; //점수 내림차순, 그 이후 중복 값들은 이름 오름차순
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				for(int i=0; i<2; i++) {
					row[i] = rset.getString(i+1);
				}
				model.addRow(row);
			}
			min = Integer.parseInt(row[1]);
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
	
	public void repaint_score() {
		model.setNumRows(0);
		Score_sel();
	}
	
	public Point() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "damho_tetris", "1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB 오류" + e.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류" + e.toString());
		}
		Score_sel();
		ScoreBoard();
		if (Board.get_point() > min) {
			new Point_Save(this, conn, stmt, rset);
		}
	}

	public static void main(String[] args) {
		new Point();
	}
	
	class Point_Save extends JDialog {
		JTextField tf = new JTextField(3);
		JLabel label = new JLabel();
		private Connection conn;
		private Statement stmt;
		private ResultSet rset;
		private String name;
		private int point;
		private JButton btn = new JButton("SAVE");
		private void Save() {
			this.name = tf.getText();
			this.point = Board.get_point();
			String sql = "insert into point values ('" + this.name + "', '" + this.point + "')";
			try {
				rset = stmt.executeQuery(sql);
				conn.commit();
			}
			catch (SQLException e) {
				System.out.println(e);
			}
		}
		public Point_Save(JFrame parent, Connection conn, Statement stmt, ResultSet rset) {
			this.conn = conn;
			this.stmt = stmt;
			this.rset = rset;
			setVisible(true);
			setSize(100,100);
			setLocationRelativeTo(null);
			tf.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent ke){
					JTextField src = (JTextField)ke.getSource();
					if(src.getText().length() >= 3) {
						ke.consume();
					}
				} 
			});
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Save();
					setVisible(false);
					repaint_score();
				}
			});
			setLayout(new FlowLayout());
			add(tf);
			add(btn);
		}
	}
}

