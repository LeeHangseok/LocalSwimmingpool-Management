package MAIN;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
 public Main() {
	    setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MainPanel());
		setSize(900,400);
		setVisible(true);
 }
 
 public class MainPanel extends JPanel{
	 ImageIcon icon = new ImageIcon("images/me.jpg"); // �гο� ������ �̹��� ����
		Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			g.drawImage(img, 0, 0, this); // 0,0 ������ �� ũ���
			JButton MemBtn = new JButton("ȸ��"); // ȸ�� �α��� ��ư
			MemBtn.setSize(250, 40);
			MemBtn.setLocation(500, 100);
			
			add(MemBtn);
			
			
			JButton TeaBtn = new JButton("����"); //���� �α��� ��ư
			TeaBtn.setSize(250,40);
			TeaBtn.setLocation(500,200);
			add(TeaBtn);
			
			
			MemBtn.addActionListener(new ActionListener() {  // ȸ�� �α��� ��ư ������
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new MLogin(); // ȸ�� �α��� ������ ����
					dispose(); // ���������� �Ұ�
					
				}
			});
			
			TeaBtn.addActionListener(new ActionListener() { // ���� �α��� ��ư ������
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new TLogin(); // ���� �α��� ������ ���� 
					dispose(); // ���� ������ �Ұ�
				}
			});
		}
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  new Main();
	}

}
