package MAIN;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TLogin extends JFrame {

	 private Connection conn = null; 
	 Statement stmt2 = null; // stmt2 선언
	 String url =null;
	private JPanel contentPane;
	 JTextField TI; // 아이디 입력 받을 텍스트 필드
	 JPasswordField TP; // 비밀번호 입력 받을 텍스트 필드
    private ResultSet rs2 =null; // 쿼리 결과값 받을 변수
	
	public TLogin() {
		setTitle("TLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
	
		
		
		JLabel TId = new JLabel("ID"); // 아이디 레이블
		TId.setBounds(500, 80, 250, 40);
		contentPane.add(TId);
		
		JLabel TPass = new JLabel("PASS"); // 비밀번호 레이블
		TPass.setBounds(500, 150, 250, 40);
		contentPane.add(TPass);
		
		TI = new JTextField(); // 아이디 입력 텍스트 필드
		TI.setBounds(600, 80, 250, 40);
		contentPane.add(TI);
		TI.setColumns(20);
		
		TP = new JPasswordField(); // 비밀번호 입력 필드
		TP.setBounds(600, 150, 250, 40);
		contentPane.add(TP);
		
		JButton Tbtn = new JButton("Login"); // 로그인 버튼
		Tbtn.setBounds(900, 80, 150, 110);
		contentPane.add(Tbtn);
		
		Tbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try { // 드라이버 로드하기
					Class.forName("oracle.jdbc.driver.OracleDriver");
					System.out.println("드라이버 로드 성공");
					try { // 데이터베이스 로드하기
						url = "jdbc:oracle:thin:@localhost:1521:xe";
						conn = DriverManager.getConnection(url,"swim","abc123");
						System.out.println("데이터베이스 로드 성공");
						stmt2 = conn.createStatement(); // 데이터베이스 접속 객체 
				
						String TFTI, TFTP; // 텍스트 필드 입력 값 받아올 변수
						String pass2 = null; // TEACHER 테이블의 아이디에 맞는 비밀번호 받아올 변수
						
						TFTI = TI.getText().trim(); // TI 텍스트 필드로부터 텍스트 받아옴
						TFTP = new String(TP.getPassword()); // TP 패스워드 필드로부터 패스워드 받아옴
						try {
							
							rs2 = stmt2.executeQuery("SELECT T_pw FROM TEACHER WHERE T_num ='"+TFTI+"'"); // TEACHER 테이블에 텍스트필드에서 받아온 아이디에 대한 비밀번호 조회
							while(rs2.next()) {
								pass2 = rs2.getString("T_pw"); // pass2에 String 형태로 저장
							}
							if(TFTP.equals(pass2)) { //강사일경우
								new TMain(); // 강사 메인 윈도우 이동
								dispose(); // 창 닫기
							}else {JOptionPane.showMessageDialog(null, "등록된 강사가 아니거나 비밀번호를 틀렸습니다!","Message",JOptionPane.ERROR_MESSAGE);} // 아닐경우 경고창
							
							
						} catch (SQLException e) {
							// TODO: handle exception
						}
						
						stmt2.close(); // stmt2 닫기
						conn.close(); //conn 연결 닫기
						
						
					} catch (SQLException e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "데이터베이스를 찾을 수 없습니다!","Message",JOptionPane.ERROR_MESSAGE);
					}
				
				} catch (ClassNotFoundException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "드라이버를 찾을 수 없습니다!","Message",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}
		});
		
		ImageIcon icon = new ImageIcon("C:\\Users\\lhs\\Desktop\\Oracle\\FInal\\images\\me.jpg"); // 수영장 사진 경로
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(10, 10, 400, 400);
		contentPane.add(lblNewLabel);
			
	}
	
}
