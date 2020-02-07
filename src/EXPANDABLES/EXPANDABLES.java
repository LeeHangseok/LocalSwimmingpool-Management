package EXPANDABLES;

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

public class EXPANDABLES extends JPanel{
	 private static final long serialVersionUID = 1L;
	 private JButton EAddBtn = null; // 한줄 추가 버튼
	 private JButton EEditBtn = null;
	 private JButton ESaveBtn = null;
	 private JButton EDelBtn = null;
	 
	 
	 private JTable table;
	 private JScrollPane scrollPane;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String colNames[] = {"소모품번","소모품명","수량"};
	private DefaultTableModel model2 = new DefaultTableModel(colNames, 0);

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null;
  
     
     public EXPANDABLES() {
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
    	 String query = "SELECT * FROM EXPANDABLES";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery();
    		 
    		 while(rs.next()) {
    			 model2.addRow(new Object[]{rs.getString("E_num"),rs.getString("E_name"),rs.getString("E_quantity")});	  			 
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
    	 EAddBtn = new JButton();
    	 EAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 =(DefaultTableModel)table.getModel();
				model3.addRow(new String[] {"","",""});
				
				
			}
		});
    	 EAddBtn.setBounds(30,222,120,25);
    	 EAddBtn.setText("추가");
    	 add(EAddBtn);
    	 
    	 //테이블 새로 저장하기
    	 ESaveBtn = new JButton();
    	 ESaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "INSERT INTO EXPANDABLES VALUES(?,?,?)";
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "swim", "abc123");
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, (String) model3.getValueAt(row, 0));
					pstmt.setString(2, (String) model3.getValueAt(row, 1));
					pstmt.setString(3, (String) model3.getValueAt(row, 2));
					
					
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
    	 ESaveBtn.setBounds(182,222,120,25);
    	 ESaveBtn.setText("저장");
    	 add(ESaveBtn);
    	 
    	 //선택된 테이블 한줄 수정
    	 EEditBtn = new JButton();
    	 EEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
    	 
    	 DefaultTableModel model3 = (DefaultTableModel)table.getModel();
    	 int row = table.getSelectedRow();
    	 if(row<0) return;
    	 
    	 String query = "UPDATE EXPANDABLES SET E_num = ?, E_name = ?, E_quantity = ? WHERE E_num = ?";
    	 
    	 try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url,"swim","abc123");
    		pstmt = con.prepareStatement(query);
    		
    		pstmt.setString(1, (String) model3.getValueAt(row, 0));
    		pstmt.setString(2, (String) model3.getValueAt(row, 1));
    		pstmt.setString(3, (String) model3.getValueAt(row, 2));
    		pstmt.setString(4, (String) model3.getValueAt(row, 0));
    	
    		
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
    	 model3.setRowCount(0); //전체 테이블 화면 지움
    	 select();
			}
		});
    	 EEditBtn.setBounds(182,270,120,25);
    	 EEditBtn.setText("수정");
    	 add(EEditBtn);
    	 
    	 
    	 
    	 EDelBtn = new JButton();
    	 EDelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "DELETE FROM EXPANDABLES WHERE M_num=?";
				
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
    	 EDelBtn.setBounds(30,270,120,25);
    	 EDelBtn.setText("삭제");
    	 add(EDelBtn);
     }
     
     
     
    
    
     
     
     }
