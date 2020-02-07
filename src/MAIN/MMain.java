
package MAIN;
import TEACH.TEACH;
import USE.USE;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MMain extends JFrame{
	Container contentPane;
	public MMain() {
		// TODO Auto-generated constructor stub
		setTitle("회원 윈도우"); // 윈도우 타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		JTabbedPane pane = createTabbedPane();
		contentPane.add(pane);
		setSize(540,400);
		setVisible(true);
		
	}
	
	
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(); //수강기록 탭과 시설 이용기록 탭 붙이기
		pane.addTab("수강 기록", new TEACH()); 
		pane.addTab("시설 이용기록", new USE());
		return pane;
	}
	
	
}
