package TEACH;
import MAIN.MLogin;
import java.awt.Container;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TEACH extends JPanel{
	 private static final long serialVersionUID = 1L;
	 private JTable table;
	 private JScrollPane scrollPane;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String colNames[] = {"ȸ�� ��ȣ","������","�����ȣ"};
	private DefaultTableModel model1 = new DefaultTableModel(colNames, 0);

	private Connection con = null;
	private PreparedStatement pstmt = null;
     private ResultSet rs = null;
  
     
     public TEACH() {
    	 setLayout(null);
      	table = new JTable(model1);
      	scrollPane = new JScrollPane(table);
      	scrollPane.setSize(500,200);
      	add(scrollPane);
      	select();
     }

  
    
     private void select() {
    	 String query = "SELECT * FROM TEACH";
    	 try {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,"swim","abc123");
    		 pstmt = con.prepareStatement(query);
    		 rs = pstmt.executeQuery();
    		 
    		 while(rs.next()) {
    			 model1.addRow(new Object[]{rs.getString("M_number"),rs.getString("T_date"),rs.getString("T_num")});	  			 
    		 }			
		} catch (Exception e) {
			// TODO: handle exceptions
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    	 
     }
}

