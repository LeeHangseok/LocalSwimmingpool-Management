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
	 private JButton MAddBtn = null; // 한줄 추가 버튼
	 private JButton MEditBtn = null; // 레코드 저장 버튼
	 private JButton MSaveBtn = null; // 레코드 변경 버튼
	 private JButton MDelBtn = null; // 레코드 삭제 버튼
	 private JButton MSBtn = null;
	 
	 
	 private JTable table;
	 private JScrollPane scrollPane; // 테이블 스크롤바 생성
	
	private String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버 주소
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 호스트 및 포트
	private String colNames[] = {"회원 번호","비밀번호","이름","나이","전화번호"}; // 테이블 컬럼 값
	private DefaultTableModel model2 = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성
	String M_number;

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null; // 리턴받을 객체 생성
  
     
     public MEMBER() {
    	 setLayout(null); // 배치관리자 삭제 
      	table = new JTable(model2); // 모델객체 테이블 붙이기
      	table.addMouseListener(new JTableMouseListener()); // 마우스 리스너 연결하기
      	scrollPane = new JScrollPane(table); // 테이블 스크롤 붙이기
      	scrollPane.setSize(750,200);
      	add(scrollPane);
      	initialize();
      	select();

}
     
     private class JTableMouseListener implements MouseListener{ // 마우스 위치값 확인
    	 public void mouseClicked(java.awt.event.MouseEvent e) { // 선택 위치값 출력
    		 JTable jtable = (JTable)e.getSource();
    		 int row = jtable.getSelectedRow();  // 선택된 레코드의 행
    		 int col = jtable.getSelectedColumn(); // 레코드의 열
    		 
    		 System.out.println(model2.getValueAt(row, col)); // 위치 값 얻어내기
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
     private void select() { // 테이블 조회
    	 String query = "SELECT * FROM MEMBER";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery(); // 리턴받아 사용할 객체 생성
    		 
    		 while(rs.next()) { // 리턴 받을 값들
    			 model2.addRow(new Object[]{rs.getString("M_num"),rs.getString("M_pw"),rs.getString("M_name"),rs.getString("M_age"),rs.getString("M_phone")});	  			 
    		 }			
		} catch (Exception e) {
			// TODO: handle exceptions
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // 객체 생성의 반대순 닫기
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    	 
     }
     
     
     
     private void initialize() { // 액션 이벤트 버튼 컴포넌트 설정
    	 MAddBtn = new JButton();
    	 MAddBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // 선택된 버튼 텍스트값 출력
				
				DefaultTableModel model3 =(DefaultTableModel)table.getModel();
				model3.addRow(new String[] {"","","","",""}); // 새 테이블 초기값
				
				
			}
		});
    	 MAddBtn.setBounds(30,222,120,25);
    	 MAddBtn.setText("추가");
    	 add(MAddBtn);
    	 
    	 //테이블 새로 저장하기
    	 MSaveBtn = new JButton();
    	 MSaveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // 버튼 텍스트 값 출력하기
				
				DefaultTableModel model3 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // 선택 되지 않을 경우 -1 리턴
				String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?)"; // 추가 쿼리문 작성
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "swim", "abc123");
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, (String) model3.getValueAt(row, 0)); // 값 입력하기
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
    	 MSaveBtn.setText("저장");
    	 add(MSaveBtn);
    	 
    	 //선택된 테이블 한줄 수정
    	 MEditBtn = new JButton();
    	 MEditBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
    	 
    	 DefaultTableModel model3 = (DefaultTableModel)table.getModel(); // 선택된 버튼의 텍스트값 출력
    	 int row = table.getSelectedRow();
    	 if(row<0) return;
    	 
    	 String query = "UPDATE MEMBER SET M_num = ?, M_pw = ?, M_name = ?, M_age = ?, M_phone = ?"+"WHERE M_num = ?"; // 레코드 변경 쿼리문
    	 
    	 try {
    		Class.forName(driver); // 드라이버 로딩
    		con = DriverManager.getConnection(url,"swim","abc123");
    		pstmt = con.prepareStatement(query); //데이터베이스 연결
    		
    		pstmt.setString(1, (String) model3.getValueAt(row, 0)); // 각 값 입력
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
    	 model3.setRowCount(0); //전체 테이블 화면 지움
    	 select();
			}
		});
    	 MEditBtn.setBounds(182,270,120,25);
    	 MEditBtn.setText("수정");
    	 add(MEditBtn);
    	 
    	 
    	 
    	 
    	 // 삭제버튼 만들기
    	 MDelBtn = new JButton();
    	 MDelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				
				DefaultTableModel model3 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // 선택 안됐을 경우 -1 리턴
				String query = "DELETE FROM MEMBER WHERE M_num=?"; //회원번호 기준 레코드 삭제
				
				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url,"swim","abc123"); // 데이터베이스 접속
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
				model3.removeRow(row); // 보여지는 테이블 한줄 삭제
				
			}
		});
    	 
    	 MDelBtn.setBounds(30,270,120,25);
    	 MDelBtn.setText("삭제");
    	 add(MDelBtn);
    	 
     
    	 // 검색
    	 JLabel m_num = new JLabel("회원 번호"); // 회원번호 레이블 
 		 m_num.setBounds(350, 225, 80, 25);
 		 add(m_num);
 		
 		 JTextField Mn = new JTextField(); // 회원번호 입력 텍스트 필드
		Mn.setBounds(450, 225, 120, 25);
		add(Mn);
		Mn.setColumns(20);
		
		
		

		
		
		MSBtn = new JButton();
		MSBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 DefaultTableModel model3 = (DefaultTableModel)table.getModel(); // 선택된 버튼의 텍스트값 출력
				model3.setRowCount(0);

			
				String M_number = Mn.getText();
						String query = "SELECT * FROM MEMBER WHERE M_num ='"+M_number+"'";
						try {
				    		 Class.forName(driver);
				    		 con = DriverManager.getConnection(url,"swim","abc123");
				    		 pstmt = con.prepareStatement(query);
				    		 rs = pstmt.executeQuery(); // 리턴받아 사용할 객체 생성
				    		 
				    		 while(rs.next()) { // 리턴 받을 값들
				    			 model2.addRow(new Object[]{rs.getString("M_num"),rs.getString("M_pw"),rs.getString("M_name"),rs.getString("M_age"),rs.getString("M_phone")});	  			 
				    		 }			
						} catch (Exception e) {
							// TODO: handle exceptions
							System.out.println(e.getMessage());
						}finally {
							try {
								rs.close();
								pstmt.close();
								con.close(); // 객체 생성의 반대순 닫기
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
				
				
				
				
				
				
				
				
			}
		});
		MSBtn.setBounds(600,225,100,25);
   	 MSBtn.setText("검색");
   	 add(MSBtn);
     }
     
   
     
   
    
     
     
     }
