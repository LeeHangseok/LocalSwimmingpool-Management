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
	 ImageIcon icon = new ImageIcon("images/me.jpg"); // 패널에 수영장 이미지 부착
		Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			g.drawImage(img, 0, 0, this); // 0,0 지점에 원 크기로
			JButton MemBtn = new JButton("회원"); // 회원 로그인 버튼
			MemBtn.setSize(250, 40);
			MemBtn.setLocation(500, 100);
			
			add(MemBtn);
			
			
			JButton TeaBtn = new JButton("강사"); //강사 로그인 버튼
			TeaBtn.setSize(250,40);
			TeaBtn.setLocation(500,200);
			add(TeaBtn);
			
			
			MemBtn.addActionListener(new ActionListener() {  // 회원 로그인 버튼 누를시
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new MLogin(); // 회원 로그인 프레임 생성
					dispose(); // 메인프레임 소거
					
				}
			});
			
			TeaBtn.addActionListener(new ActionListener() { // 강사 로그인 버튼 누를시
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new TLogin(); // 강사 로그인 프레임 생성 
					dispose(); // 메인 프레임 소거
				}
			});
		}
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  new Main();
	}

}
