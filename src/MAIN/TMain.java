package MAIN;
import MEMBER.MEMBER;
import EXPANDABLES.*;
import TEACH.TEACH_T;
import DISTRIBUTOR.DISTRIBUTOR;
import FACILITY.FACILITY;
import TEACHER.TEACHER;
import CONVENIENCE.Pprice;
import CONVENIENCE.Buy_date;
import RENTAL.RENT_S;
import RENTAL.RENT_P;
import ASCENTER.ASCENTER;



import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TMain extends JFrame{
	Container contentPane;
	public TMain() {
		// TODO Auto-generated constructor stub
		setTitle("강사 윈도우");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		JTabbedPane pane = createTabbedPane();
		contentPane.add(pane);
		setSize(800,500);
		setVisible(true);
		
	}
	
	
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("회원관리", new MEMBER());
		pane.addTab("소모품관리", new EXPANDABLES());
		pane.addTab("수강", new TEACH_T());
		pane.addTab("납품업체",new DISTRIBUTOR());
		pane.addTab("시설", new FACILITY());
		pane.addTab("강사관리", new TEACHER());
		pane.addTab("편의점", new Pprice());
		pane.addTab("구매기록", new Buy_date());
		pane.addTab("대여 상태", new RENT_S());
		pane.addTab("대여료", new RENT_P());
		pane.addTab("수리점", new ASCENTER());
		
		return pane;
	}
	
	
}
