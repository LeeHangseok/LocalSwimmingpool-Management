
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
		setTitle("ȸ�� ������"); // ������ Ÿ��Ʋ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		JTabbedPane pane = createTabbedPane();
		contentPane.add(pane);
		setSize(540,400);
		setVisible(true);
		
	}
	
	
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(); //������� �ǰ� �ü� �̿��� �� ���̱�
		pane.addTab("���� ���", new TEACH()); 
		pane.addTab("�ü� �̿���", new USE());
		return pane;
	}
	
	
}
