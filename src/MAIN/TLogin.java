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
	 Statement stmt2 = null; // stmt2 ����
	 String url =null;
	private JPanel contentPane;
	 JTextField TI; // ���̵� �Է� ���� �ؽ�Ʈ �ʵ�
	 JPasswordField TP; // ��й�ȣ �Է� ���� �ؽ�Ʈ �ʵ�
    private ResultSet rs2 =null; // ���� ����� ���� ����
	
	public TLogin() {
		setTitle("TLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
	
		
		
		JLabel TId = new JLabel("ID"); // ���̵� ���̺�
		TId.setBounds(500, 80, 250, 40);
		contentPane.add(TId);
		
		JLabel TPass = new JLabel("PASS"); // ��й�ȣ ���̺�
		TPass.setBounds(500, 150, 250, 40);
		contentPane.add(TPass);
		
		TI = new JTextField(); // ���̵� �Է� �ؽ�Ʈ �ʵ�
		TI.setBounds(600, 80, 250, 40);
		contentPane.add(TI);
		TI.setColumns(20);
		
		TP = new JPasswordField(); // ��й�ȣ �Է� �ʵ�
		TP.setBounds(600, 150, 250, 40);
		contentPane.add(TP);
		
		JButton Tbtn = new JButton("Login"); // �α��� ��ư
		Tbtn.setBounds(900, 80, 150, 110);
		contentPane.add(Tbtn);
		
		Tbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try { // ����̹� �ε��ϱ�
					Class.forName("oracle.jdbc.driver.OracleDriver");
					System.out.println("����̹� �ε� ����");
					try { // �����ͺ��̽� �ε��ϱ�
						url = "jdbc:oracle:thin:@localhost:1521:xe";
						conn = DriverManager.getConnection(url,"swim","abc123");
						System.out.println("�����ͺ��̽� �ε� ����");
						stmt2 = conn.createStatement(); // �����ͺ��̽� ���� ��ü 
				
						String TFTI, TFTP; // �ؽ�Ʈ �ʵ� �Է� �� �޾ƿ� ����
						String pass2 = null; // TEACHER ���̺��� ���̵� �´� ��й�ȣ �޾ƿ� ����
						
						TFTI = TI.getText().trim(); // TI �ؽ�Ʈ �ʵ�κ��� �ؽ�Ʈ �޾ƿ�
						TFTP = new String(TP.getPassword()); // TP �н����� �ʵ�κ��� �н����� �޾ƿ�
						try {
							
							rs2 = stmt2.executeQuery("SELECT T_pw FROM TEACHER WHERE T_num ='"+TFTI+"'"); // TEACHER ���̺� �ؽ�Ʈ�ʵ忡�� �޾ƿ� ���̵� ���� ��й�ȣ ��ȸ
							while(rs2.next()) {
								pass2 = rs2.getString("T_pw"); // pass2�� String ���·� ����
							}
							if(TFTP.equals(pass2)) { //�����ϰ��
								new TMain(); // ���� ���� ������ �̵�
								dispose(); // â �ݱ�
							}else {JOptionPane.showMessageDialog(null, "��ϵ� ���簡 �ƴϰų� ��й�ȣ�� Ʋ�Ƚ��ϴ�!","Message",JOptionPane.ERROR_MESSAGE);} // �ƴҰ�� ���â
							
							
						} catch (SQLException e) {
							// TODO: handle exception
						}
						
						stmt2.close(); // stmt2 �ݱ�
						conn.close(); //conn ���� �ݱ�
						
						
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
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(10, 10, 400, 400);
		contentPane.add(lblNewLabel);
			
	}
	
}
