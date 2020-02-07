package MAIN;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TEACH.TEACH;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MLogin extends JFrame {

	 private Connection conn = null; //connection 변수 설정
	 Statement stmt1 = null; 
	 String url =null;
	private JPanel contentPane; // contentpane
	 JTextField MI; // 아이디 입력 텍스트 필드
	 JPasswordField MP; // 비밀번호 입력 패스워드 필드
    private ResultSet rs1 =null; // 결과 값 저장할 변수
    String TFMI, TFMP; // 텍스트필드와 패스워드 필드의 값 받을 String 변수
	String pass1 = null; // MEMBER 테이블의  비밀번호 받아올 변수
	public MLogin() {
		setTitle("회원 로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		
		
		
		JLabel MId = new JLabel("ID"); // 아이디 레이블 
		MId.setBounds(500, 80, 250, 40);
		contentPane.add(MId);
		
		JLabel MPass = new JLabel("PASS"); // 비밀번호 레이블
		MPass.setBounds(500, 150, 250, 40);
		contentPane.add(MPass);
		
		MI = new JTextField(); // 아이디 입력 텍스트 필드
		MI.setBounds(600, 80, 250, 40);
		contentPane.add(MI);
		MI.setColumns(20);
		
		MP = new JPasswordField(); // 비밀번호 입력 필드
		MP.setBounds(600, 150, 250, 40);
		contentPane.add(MP);
		
		JButton Mbtn = new JButton("Login"); // 로그인 버튼
		Mbtn.setBounds(900, 80, 150, 110);
		contentPane.add(Mbtn);
		
		Mbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 주소
					System.out.println("드라이버 로드 성공");
					try {
						url = "jdbc:oracle:thin:@localhost:1521:xe"; // url 주소
						conn = DriverManager.getConnection(url,"swim","abc123"); // 데이터베이스 로드하기 위한 url, 계정, 비밀번호
						System.out.println("데이터베이스 로드 성공");
						stmt1 = conn.createStatement(); // 데이터베이스 접속
				
						TFMI = MI.getText().trim(); // MI 텍스트필드로부터 String 값 받아옴
						TFMP = new String(MP.getPassword()); // MP 패스워드 필드로부터 String값 받아옴
						try {
							
							rs1 = stmt1.executeQuery("SELECT M_pw FROM MEMBER WHERE M_num ='"+TFMI+"'"); // 데이터베이스 내의 멤버테이블에서 텍스트필드 값의 아이디를 가진
																											//레코드의 비밀번호값 조회
							while(rs1.next()) { // 데이터베이스 내의 회원 비밀번호값 받아오기
								pass1 = rs1.getString("M_pw");  
							}
							if(TFMP.equals(pass1)) {// 비밀번호가 같을 경우
								
								// JOptionPane.showMessageDialog(null, "회원입니다.");
							
						    new MMain(); //회원 메인 창으로 이동
						    dispose(); // 창닫기
							}else {JOptionPane.showMessageDialog(null, "회원이 아니거나 비밀번호를 틀렸습니다!","Message",JOptionPane.ERROR_MESSAGE);} // 비밀번호가 다를 경우
							
							
						} catch (SQLException e) {
							// TODO: handle exception
						}
						
						stmt1.close(); // stmt1 종료
						conn.close(); // 연결 종료
					
						
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
		JLabel lblNewLabel = new JLabel(icon); // 레이블에 사진 붙이기
		lblNewLabel.setBounds(10, 10, 400, 400); // 레이블(사진크기) 설정
		contentPane.add(lblNewLabel); // 레이블 붙이기
			
	}
	
}
