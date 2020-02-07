package MEMBER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MEMBER extends JPanel{
	 private static final long serialVersionUID = 1L;
	 private JButton MAddBtn = null; // ���� �߰� ��ư
	 private JButton MEditBtn = null; // ���ڵ� ���� ��ư
	 private JButton MSaveBtn = null; // ���ڵ� ���� ��ư
	 private JButton MDelBtn = null; // ���ڵ� ���� ��ư
	 private JButton MSBtn = null;
	 
	 
	 private JTable table;
	 private JScrollPane scrollPane; // ���̺� ��ũ�ѹ� ����
	
	private String driver = "oracle.jdbc.driver.OracleDriver"; // ����̹� �ּ�
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ȣ��Ʈ �� ��Ʈ
	private String colNames[] = {"ȸ�� ��ȣ","��й�ȣ","�̸�","����","��ȭ��ȣ"}; // ���̺� �÷� ��
	private DefaultTableModel model2 = new DefaultTableModel(colNames, 0); // ���̺� ������ �� ��ü ����
	String M_number;

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null; // ���Ϲ��� ��ü ����
  
     
     public MEMBER() {
    	 setLayout(null); // ��ġ������ ���� 
      	table = new JTable(model2); // �𵨰�ü ���̺� ���̱�
      	table.addMouseListener(new JTableMouseListener()); // ���콺 ������ �����ϱ�
      	scrollPane = new JScrollPane(table); // ���̺� ��ũ�� ���̱�
      	scrollPane.setSize(750,200);
      	add(scrollPane);
      	initialize();
      	select();

}
     
     private class JTableMouseListener implements MouseListener{ // ���콺 ��ġ�� Ȯ��
    	 public void mouseClicked(java.awt.event.MouseEvent e) { // ���� ��ġ�� ���
    		 JTable jtable = (JTable)e.getSource();
    		 int row = jtable.getSelectedRow();  // ���õ� ���ڵ��� ��
    		 int col = jtable.getSelectedColumn(); // ���ڵ��� ��
    		 
    		 System.out.println(model2.getValueAt(row, col)); // ��ġ �� ����
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
     private void select() { // ���̺� ��ȸ
    	 String query = "SELECT * FROM MEMBER";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery(); // ���Ϲ޾� ����� ��ü ����
    		 
    		 while(rs.next()) { // ���� ���� ����
    			 model2.addRow(new Object[]{rs.getString("M_num"),rs.getString("M_pw"),rs.getString("M_name"),rs.getString("M_age"),rs.getString("M_phone")});	  			 
    		 }			
		} catch (Exception e) {
			// TODO: handle exceptions
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // ��ü ������ �ݴ�� �ݱ�
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    	 
     }
     
     
     
     private void initialize() { // �׼� �̺�Ʈ ��ư ������Ʈ ����
    	 MAddBtn = new JButton();
    	 MAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // ���õ� ��ư �ؽ�Ʈ�� ���
				
				DefaultTableModel model3 =(DefaultTableModel)table.getModel();
				model3.addRow(new String[] {"","","","",""}); // �� ���̺� �ʱⰪ
				
				
			}
		});
    	 MAddBtn.setBounds(30,222,120,25);
    	 MAddBtn.setText("�߰�");
    	 add(MAddBtn);
    	 
    	 //���̺� ���� �����ϱ�
    	 MSaveBtn = new JButton();
    	 MSaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // ��ư �ؽ�Ʈ �� ����ϱ�
				
				DefaultTableModel model3 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // ���� ���� ���� ��� -1 ����
				String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?)"; // �߰� ������ �ۼ�
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "swim", "abc123");
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, (String) model3.getValueAt(row, 0)); // �� �Է��ϱ�
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
    	 
    	 
   
    	 MSaveBtn.setBounds(182,222,120,25);
    	 MSaveBtn.setText("����");
    	 add(MSaveBtn);
    	 
    	 //���õ� ���̺� ���� ����
    	 MEditBtn = new JButton();
    	 MEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
    	 
    	 DefaultTableModel model3 = (DefaultTableModel)table.getModel(); // ���õ� ��ư�� �ؽ�Ʈ�� ���
    	 int row = table.getSelectedRow();
    	 if(row<0) return;
    	 
    	 String query = "UPDATE MEMBER SET M_num = ?, M_pw = ?, M_name = ?, M_age = ?, M_phone = ?"+"WHERE M_num = ?"; // ���ڵ� ���� ������
    	 
    	 try {
    		Class.forName(driver); // ����̹� �ε�
    		con = DriverManager.getConnection(url,"swim","abc123");
    		pstmt = con.prepareStatement(query); //�����ͺ��̽� ����
    		
    		pstmt.setString(1, (String) model3.getValueAt(row, 0)); // �� �� �Է�
    		pstmt.setString(2, (String) model3.getValueAt(row, 1));
    		pstmt.setString(3, (String) model3.getValueAt(row, 2));
    		pstmt.setString(4, (String) model3.getValueAt(row, 3));
    		pstmt.setString(5, (String) model3.getValueAt(row, 4));
    		pstmt.setString(6, (String) model3.getValueAt(row, 0));
    		
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
    	 MEditBtn.setBounds(182,270,120,25);
    	 MEditBtn.setText("����");
    	 add(MEditBtn);
    	 
    	 
    	 
    	 
    	 // ������ư �����
    	 MDelBtn = new JButton();
    	 MDelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				
				DefaultTableModel model3 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // ���� �ȵ��� ��� -1 ����
				String query = "DELETE FROM MEMBER WHERE M_num=?"; //ȸ����ȣ ���� ���ڵ� ����
				
				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url,"swim","abc123"); // �����ͺ��̽� ����
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
				model3.removeRow(row); // �������� ���̺� ���� ����
				
			}
		});
    	 
    	 MDelBtn.setBounds(30,270,120,25);
    	 MDelBtn.setText("����");
    	 add(MDelBtn);
    	 
     
    	 // �˻�
    	 JLabel m_num = new JLabel("ȸ�� ��ȣ"); // ȸ����ȣ ���̺� 
 		 m_num.setBounds(350, 225, 80, 25);
 		 add(m_num);
 		
 		 JTextField Mn = new JTextField(); // ȸ����ȣ �Է� �ؽ�Ʈ �ʵ�
		Mn.setBounds(450, 225, 120, 25);
		add(Mn);
		Mn.setColumns(20);
		
		
		

		
		
		MSBtn = new JButton();
		MSBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 DefaultTableModel model3 = (DefaultTableModel)table.getModel(); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				model3.setRowCount(0);

			
				String M_number = Mn.getText();
						String query = "SELECT * FROM MEMBER WHERE M_num ='"+M_number+"'";
						try {
				    		 Class.forName(driver);
				    		 con = DriverManager.getConnection(url,"swim","abc123");
				    		 pstmt = con.prepareStatement(query);
				    		 rs = pstmt.executeQuery(); // ���Ϲ޾� ����� ��ü ����
				    		 
				    		 while(rs.next()) { // ���� ���� ����
				    			 model2.addRow(new Object[]{rs.getString("M_num"),rs.getString("M_pw"),rs.getString("M_name"),rs.getString("M_age"),rs.getString("M_phone")});	  			 
				    		 }			
						} catch (Exception e) {
							// TODO: handle exceptions
							System.out.println(e.getMessage());
						}finally {
							try {
								rs.close();
								pstmt.close();
								con.close(); // ��ü ������ �ݴ�� �ݱ�
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
				
				
				
				
				
				
				
				
			}
		});
		MSBtn.setBounds(600,225,100,25);
   	 MSBtn.setText("�˻�");
   	 add(MSBtn);
     }
     
   
     
   
    
     
     
     }
