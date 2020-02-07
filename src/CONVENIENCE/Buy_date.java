package CONVENIENCE;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Buy_date extends JPanel{
	 private static final long serialVersionUID = 1L;
	 private JButton BAddBtn = null; // ���� �߰� ��ư
	 private JButton BEditBtn = null;
	 private JButton BSaveBtn = null;
	 private JButton BDelBtn = null;
	 
	 
	 private JTable table;
	 private JScrollPane scrollPane;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String colNames[] = {"��ǰ��ȣ","������","����"};
	private DefaultTableModel model2 = new DefaultTableModel(colNames, 0);

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null;
  
     
     public Buy_date() {
    	 setLayout(null);
      	table = new JTable(model2);
      	table.addMouseListener(new JTableMouseListener());
      	scrollPane = new JScrollPane(table);
      	scrollPane.setSize(750,200);
      	add(scrollPane);
      	initialize();
      	select();

}
     
     private class JTableMouseListener implements MouseListener{
    	 public void mouseClicked(java.awt.event.MouseEvent e) {
    		 JTable jtable = (JTable)e.getSource();
    		 int row = jtable.getSelectedRow();
    		 int col = jtable.getSelectedColumn();
    		 
    		 System.out.println(model2.getValueAt(row, col));
    	 }
    	 public void mouseEnterd(java.awt.event.MouseEvent e) {}
    	 public void mouseExited(java.awt.event.MouseEvent e) {}
    	 public void mousePressed(java.awt.event.MouseEvent e) {}
    	 public void mouseReleased(java.awt.event.MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	 
    	 }
     private void select() {
    	 String query = "SELECT * FROM Buy_date";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery();
    		 
    		 while(rs.next()) {
    			 model2.addRow(new Object[]{rs.getString("C_num"),rs.getString("C_contents"),rs.getString("C_quantity")});	  			 
    		 }			
		} catch (Exception e) {
			// TODO: handle exceptions
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    	 
     }
     
     
     
     private void initialize() {
    	 BAddBtn = new JButton();
    	 BAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 =(DefaultTableModel)table.getModel();
				model3.addRow(new String[] {"","",""});
				
				
			}
		});
    	 BAddBtn.setBounds(30,222,120,25);
    	 BAddBtn.setText("�߰�");
    	 add(BAddBtn);
    	 
    	 //���̺� ���� �����ϱ�
    	 BSaveBtn = new JButton();
    	 BSaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "INSERT INTO Buy_date VALUES(?,?,?)";
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "swim", "abc123");
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, (String) model3.getValueAt(row, 0));
					pstmt.setString(2, (String) model3.getValueAt(row, 1));
					pstmt.setString(3, (String) model3.getValueAt(row, 2));
					pstmt.setString(4, (String) model3.getValueAt(row, 3));
					pstmt.setString(5, (String) model3.getValueAt(row, 4));
					
					int cnt = pstmt.executeUpdate();
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e3) {
						// TODO: handle exception
					}
				}
				model3.setRowCount(0);
				select();
			}
		});
    	 BSaveBtn.setBounds(182,222,120,25);
    	 BSaveBtn.setText("����");
    	 add(BSaveBtn);
    	 
    	 //���õ� ���̺� ���� ����
    	 BEditBtn = new JButton();
    	 BEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
    	 
    	 DefaultTableModel model3 = (DefaultTableModel)table.getModel();
    	 int row = table.getSelectedRow();
    	 if(row<0) return;
    	 
    	 String query = "UPDATE Buy_date SET C_num = ?, C_contents = ?, C_quantity = ?"+"WHERE C_num = ?";
    	 
    	 try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url,"swim","abc123");
    		pstmt = con.prepareStatement(query);
    		
    		pstmt.setString(1, (String) model3.getValueAt(row, 0));
    		pstmt.setString(2, (String) model3.getValueAt(row, 1));
    		pstmt.setString(3, (String) model3.getValueAt(row, 2));
    		pstmt.setString(4, (String) model3.getValueAt(row, 3));
    		pstmt.setString(5, (String) model3.getValueAt(row, 0));
    		
    		int cnt = pstmt.executeUpdate();
    		
    	 }catch (Exception e2) {
			// TODO: handle exception
    		 System.out.println(e2.getMessage());
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}
    	 model3.setRowCount(0); //��ü ���̺� ȭ�� ����
    	 select();
			}
		});
    	 BEditBtn.setBounds(182,270,120,25);
    	 BEditBtn.setText("����");
    	 add(BEditBtn);
    	 
    	 
    	 
    	 BDelBtn = new JButton();
    	 BDelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "DELETE FROM Buy_date WHERE C_num=?";
				
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,"swim","abc123");
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, (String)model3.getValueAt(row, 0));
					int cnt = pstmt.executeUpdate();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e3) {
						// TODO: handle exception
					}
				}
				model3.removeRow(row);
				
			}
		});
    	 BDelBtn.setBounds(30,270,120,25);
    	 BDelBtn.setText("����");
    	 add(BDelBtn);
     }
     
     
     
    
    
     
     
     }
