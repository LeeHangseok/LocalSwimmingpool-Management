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
		setTitle("���� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		JTabbedPane pane = createTabbedPane();
		contentPane.add(pane);
		setSize(800,500);
		setVisible(true);
		
	}
	
	
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("ȸ������", new MEMBER());
		pane.addTab("�Ҹ�ǰ����", new EXPANDABLES());
		pane.addTab("����", new TEACH_T());
		pane.addTab("��ǰ��ü",new DISTRIBUTOR());
		pane.addTab("�ü�", new FACILITY());
		pane.addTab("�������", new TEACHER());
		pane.addTab("������", new Pprice());
		pane.addTab("���ű��", new Buy_date());
		pane.addTab("�뿩 ����", new RENT_S());
		pane.addTab("�뿩��", new RENT_P());
		pane.addTab("������", new ASCENTER());
		
		return pane;
	}
	
	
}
