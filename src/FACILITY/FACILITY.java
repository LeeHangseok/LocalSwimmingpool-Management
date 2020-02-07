package FACILITY;

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

public class FACILITY extends JPanel{
	 private static final long serialVersionUID = 1L;
	 private JButton FAddBtn = null; // 한줄 추가 버튼
	 private JButton FEditBtn = null;
	 private JButton FSaveBtn = null;
	 private JButton FDelBtn = null;
	 
	 
	 private JTable table;
	 private JScrollPane scrollPane;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String colNames[] = {"시설 번호","시설명","상태"};
	private DefaultTableModel model2 = new DefaultTableModel(colNames, 0);

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null;
  
     
     public FACILITY() {
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
    	 String query = "SELECT * FROM FACILITY";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery();
    		 
    		 while(rs.next()) {
    			 model2.addRow(new Object[]{rs.getString("F_num"),rs.getString("F_name"),rs.getString("F_status")});	  			 
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
    	 FAddBtn = new JButton();
    	 FAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 =(DefaultTableModel)table.getModel();
				model3.addRow(new String[] {"","",""});
				
				
			}
		});
    	 FAddBtn.setBounds(30,222,120,25);
    	 FAddBtn.setText("추가");
    	 add(FAddBtn);
    	 
    	 //테이블 새로 저장하기
    	 FSaveBtn = new JButton();
    	 FSaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "INSERT INTO FACILITY VALUES(?,?,?)";
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
    	 FSaveBtn.setBounds(182,222,120,25);
    	 FSaveBtn.setText("저장");
    	 add(FSaveBtn);
    	 
    	 //선택된 테이블 한줄 수정
    	 FEditBtn = new JButton();
    	 FEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
    	 
    	 DefaultTableModel model3 = (DefaultTableModel)table.getModel();
    	 int row = table.getSelectedRow();
    	 if(row<0) return;
    	 
    	 String query = "UPDATE FACILITY SET F_num = ?, F_name = ?, F_status = ?"+"WHERE F_num = ?";
    	 
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
    	 FEditBtn.setBounds(182,270,120,25);
    	 FEditBtn.setText("수정");
    	 add(FEditBtn);
    	 
    	 
    	 
    	 FDelBtn = new JButton();
    	 FDelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				
				DefaultTableModel model3 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "DELETE FROM FACILITY WHERE F_num=?";
				
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
    	 FDelBtn.setBounds(30,270,120,25);
    	 FDelBtn.setText("삭제");
    	 add(FDelBtn);
     }
     
     
     
    
    
     
     
     }
