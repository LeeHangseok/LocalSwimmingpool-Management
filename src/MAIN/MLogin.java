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

	 private Connection conn = null; //connection ���� ����
	 Statement stmt1 = null; 
	 String url =null;
	private JPanel contentPane; // contentpane
	 JTextField MI; // ���̵� �Է� �ؽ�Ʈ �ʵ�
	 JPasswordField MP; // ��й�ȣ �Է� �н����� �ʵ�
    private ResultSet rs1 =null; // ��� �� ������ ����
    String TFMI, TFMP; // �ؽ�Ʈ�ʵ�� �н����� �ʵ��� �� ���� String ����
	String pass1 = null; // MEMBER ���̺���  ��й�ȣ �޾ƿ� ����
	public MLogin() {
		setTitle("ȸ�� �α���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		
		
		
		JLabel MId = new JLabel("ID"); // ���̵� ���̺� 
		MId.setBounds(500, 80, 250, 40);
		contentPane.add(MId);
		
		JLabel MPass = new JLabel("PASS"); // ��й�ȣ ���̺�
		MPass.setBounds(500, 150, 250, 40);
		contentPane.add(MPass);
		
		MI = new JTextField(); // ���̵� �Է� �ؽ�Ʈ �ʵ�
		MI.setBounds(600, 80, 250, 40);
		contentPane.add(MI);
		MI.setColumns(20);
		
		MP = new JPasswordField(); // ��й�ȣ �Է� �ʵ�
		MP.setBounds(600, 150, 250, 40);
		contentPane.add(MP);
		
		JButton Mbtn = new JButton("Login"); // �α��� ��ư
		Mbtn.setBounds(900, 80, 150, 110);
		contentPane.add(Mbtn);
		
		Mbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̹� �ּ�
					System.out.println("����̹� �ε� ����");
					try {
						url = "jdbc:oracle:thin:@localhost:1521:xe"; // url �ּ�
						conn = DriverManager.getConnection(url,"swim","abc123"); // �����ͺ��̽� �ε��ϱ� ���� url, ����, ��й�ȣ
						System.out.println("�����ͺ��̽� �ε� ����");
						stmt1 = conn.createStatement(); // �����ͺ��̽� ����
				
						TFMI = MI.getText().trim(); // MI �ؽ�Ʈ�ʵ�κ��� String �� �޾ƿ�
						TFMP = new String(MP.getPassword()); // MP �н����� �ʵ�κ��� String�� �޾ƿ�
						try {
							
							rs1 = stmt1.executeQuery("SELECT M_pw FROM MEMBER WHERE M_num ='"+TFMI+"'"); // �����ͺ��̽� ���� ������̺��� �ؽ�Ʈ�ʵ� ���� ���̵� ����
																											//���ڵ��� ��й�ȣ�� ��ȸ
							while(rs1.next()) { // �����ͺ��̽� ���� ȸ�� ��й�ȣ�� �޾ƿ���
								pass1 = rs1.getString("M_pw");  
							}
							if(TFMP.equals(pass1)) {// ��й�ȣ�� ���� ���
								
								// JOptionPane.showMessageDialog(null, "ȸ���Դϴ�.");
							
						    new MMain(); //ȸ�� ���� â���� �̵�
						    dispose(); // â�ݱ�
							}else {JOptionPane.showMessageDialog(null, "ȸ���� �ƴϰų� ��й�ȣ�� Ʋ�Ƚ��ϴ�!","Message",JOptionPane.ERROR_MESSAGE);} // ��й�ȣ�� �ٸ� ���
							
							
						} catch (SQLException e) {
							// TODO: handle exception
						}
						
						stmt1.close(); // stmt1 ����
						conn.close(); // ���� ����
					
						
					} catch (SQLException e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "�����ͺ��̽��� ã�� �� �����ϴ�!","Message",JOptionPane.ERROR_MESSAGE);
					
					}
				
				} catch (ClassNotFoundException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "����̹��� ã�� �� �����ϴ�!","Message",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
				
			}
		});
		
		ImageIcon icon = new ImageIcon("C:\\Users\\lhs\\Desktop\\Oracle\\FInal\\images\\me.jpg"); // ������ ���� ���
		JLabel lblNewLabel = new JLabel(icon); // ���̺� ���� ���̱�
		lblNewLabel.setBounds(10, 10, 400, 400); // ���̺�(����ũ��) ����
		contentPane.add(lblNewLabel); // ���̺� ���̱�
			
	}
	
}
