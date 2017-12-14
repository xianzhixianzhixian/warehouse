package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.MaintainerDao;
import model.Maintainer;
import util.Dbutil;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Maintainer_ManageFrm extends JInternalFrame {


	private JTable MaintainerTable;
	private JTextField KeyTxt;
	private JTextField NameTxt;
	private JTextField JobTxt;
	private JTextField IdTxt;
	private JTextField CompanyTxt;
	private JTextField TelTxt;
	Dbutil dbutil=new Dbutil();
	MaintainerDao maintainerdao=new MaintainerDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maintainer_ManageFrm frame = new Maintainer_ManageFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Maintainer_ManageFrm() {
		init();
		fillTable(null);
		

	}
	
	void fillTable(String GoodsName){
		DefaultTableModel dtm=(DefaultTableModel)MaintainerTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbutil.getCon();
			ResultSet rs= maintainerdao.maintainerList(con,GoodsName);
			while(rs.next()){
				Vector v=new Vector();
				v.addElement(rs.getString("MaintainerId"));
				v.addElement(rs.getString("MaintainerName"));
				v.addElement(rs.getString("MaintainerJob"));
				v.addElement(rs.getString("MaintainerCompany"));
				v.addElement(rs.getString("MaintainerPhone"));
				v.addElement(rs.getString("GoodsName"));
				dtm.addRow(v);
		} 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reset();
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
		fillTable();
	}
	
	void fillTable(){
		DefaultTableModel dtm=(DefaultTableModel)MaintainerTable.getModel();
		Connection con=null;
		try {
			con=dbutil.getCon();
			ResultSet rs= maintainerdao.maintainerList(con);
			while(rs.next()){
				Vector v=new Vector();
				v.addElement(rs.getString("MaintainerId"));
				v.addElement(rs.getString("MaintainerName"));
				v.addElement(rs.getString("MaintainerJob"));
				v.addElement(rs.getString("MaintainerCompany"));
				v.addElement(rs.getString("MaintainerPhone"));
				v.addElement(null);
				dtm.addRow(v);
		} 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reset();
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 	
		
	}
	
	void reset(){
		IdTxt.setText(null);
		NameTxt.setText(null);
		JobTxt.setText(null);
		CompanyTxt.setText(null);
		TelTxt.setText(null);
	}
	
	void init(){
		setTitle("\u4FDD\u4FEE\u4EBA\u5458\u7EF4\u62A4");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 590, 435);

		getContentPane().setLayout(null);
		
		JScrollPane GoodsTable = new JScrollPane();
		GoodsTable.setBounds(39, 84, 491, 153);
		getContentPane().add(GoodsTable);
		
		MaintainerTable = new JTable();
		MaintainerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row=MaintainerTable.getSelectedRow();
				IdTxt.setText((String)MaintainerTable.getValueAt(row, 0));
				NameTxt.setText((String)MaintainerTable.getValueAt(row, 1));
				JobTxt.setText((String)MaintainerTable.getValueAt(row, 2));
				CompanyTxt.setText((String)MaintainerTable.getValueAt(row, 3));
				TelTxt.setText((String)MaintainerTable.getValueAt(row, 4));
			}
		});
	
		MaintainerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u804C\u52A1", "\u516C\u53F8", "\u8054\u7CFB\u7535\u8BDD", "\u7EF4\u4FDD\u7269\u54C1\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		MaintainerTable.getColumnModel().getColumn(4).setPreferredWidth(95);
		MaintainerTable.getColumnModel().getColumn(5).setPreferredWidth(119);
		GoodsTable.setViewportView(MaintainerTable);
		
		JLabel lable = new JLabel("\u4FDD\u4FEE\u7269\u54C1\uFF1A");
		lable.setBounds(66, 49, 70, 25);
		getContentPane().add(lable);
		
		KeyTxt = new JTextField();
		KeyTxt.setBounds(146, 51, 97, 21);
		getContentPane().add(KeyTxt);
		KeyTxt.setColumns(10);
		
		JButton bt_select = new JButton("\u67E5\u8BE2");
		bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String key=KeyTxt.getText();
				fillTable(key);
			}
		});
		bt_select.setBounds(386, 51, 75, 23);
		getContentPane().add(bt_select);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 247, 491, 140);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setBounds(264, 22, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u804C\u52A1\uFF1A");
		label_2.setBounds(24, 50, 54, 15);
		panel.add(label_2);
		
		NameTxt = new JTextField();
		NameTxt.setBounds(313, 22, 103, 21);
		panel.add(NameTxt);
		NameTxt.setColumns(10);
		
		JButton bt_modify = new JButton("\u786E\u8BA4\u4FEE\u6539");
		bt_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(IdTxt.getText());
				String name=NameTxt.getText();
				String job=JobTxt.getText();
				String company=CompanyTxt.getText();
				String tel=TelTxt.getText();
				Maintainer m=new Maintainer(id,name,job,company,tel);
				Connection con=null;
				try {
					con=dbutil.getCon();
					int n=maintainerdao.ModifyMaintainer(con, m);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦");
						reset();
						fillTable(null);
					}else
						JOptionPane.showMessageDialog(null, "ÐÞ¸ÄÊ§°Ü");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		bt_modify.setBounds(118, 106, 93, 23);
		panel.add(bt_modify);
		
		JButton bt_delete = new JButton("\u5220\u9664\u4FE1\u606F");
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(IdTxt.getText());
				Connection con=null;
				
				try {
					con=dbutil.getCon();
					int n=maintainerdao.DeleteMaintainer(con, id);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "É¾³ý³É¹¦");
						reset();
						fillTable(null);
					}else
						JOptionPane.showMessageDialog(null, "É¾³ýÊ§°Ü");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		bt_delete.setBounds(274, 106, 93, 23);
		panel.add(bt_delete);
		
		JobTxt = new JTextField();
		JobTxt.setColumns(10);
		JobTxt.setBounds(108, 47, 103, 21);
		panel.add(JobTxt);
		
		JLabel label_3 = new JLabel("\u7F16\u53F7\uFF1A");
		label_3.setBounds(24, 22, 54, 15);
		panel.add(label_3);
		
		IdTxt = new JTextField();
		IdTxt.setEditable(false);
		IdTxt.setColumns(10);
		IdTxt.setBounds(108, 22, 103, 21);
		panel.add(IdTxt);
		
		JLabel label_4 = new JLabel("\u516C\u53F8\uFF1A");
		label_4.setBounds(264, 47, 54, 15);
		panel.add(label_4);
		
		CompanyTxt = new JTextField();
		CompanyTxt.setColumns(10);
		CompanyTxt.setBounds(313, 47, 103, 21);
		panel.add(CompanyTxt);
		
		JLabel label_5 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_5.setBounds(24, 78, 74, 15);
		panel.add(label_5);
		
		TelTxt = new JTextField();
		TelTxt.setColumns(10);
		TelTxt.setBounds(108, 75, 103, 21);
		panel.add(TelTxt);
	}

}
