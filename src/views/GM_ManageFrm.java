package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.GMDao;
import model.Goods_Maintainer;
import util.DateButton;
import util.Dbutil;
import util.StringUtil;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GM_ManageFrm extends JInternalFrame {
	private JTable GM_infTable;
	private JTextField GIdTxt;
	private JTextField MIdTxt;
	private JTextField GNameTxt;
	private JTextField MNameTxt;
	private DateButton db_StartMaintainDate;
	private DateButton db_EndMaintainDate;
	Dbutil dbutil=new Dbutil();
	GMDao gmdao=new GMDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GM_ManageFrm frame = new GM_ManageFrm();
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
	public GM_ManageFrm() {
		setTitle("\u7269\u8D44\u4FDD\u4FEE\u660E\u7EC6\u7EF4\u62A4");
		setClosable(true);
		setMaximizable(true);
		init();
		fillTable(new Goods_Maintainer());
	}
	
	void fillTable(Goods_Maintainer gm){
		DefaultTableModel dtm=(DefaultTableModel)GM_infTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbutil.getCon();
			ResultSet rs= gmdao.gmList(con,gm);
			while(rs.next()){
				Vector v=new Vector();
				v.addElement(rs.getString("GoodId"));
				v.addElement(rs.getString("GoodsName"));
				v.addElement(rs.getString("MaintainerId"));
				v.addElement(rs.getString("MaintainerName"));
				v.addElement(rs.getString("StartMaintainDate"));
				v.addElement(rs.getString("EndMaintainDate"));
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
		GIdTxt.setText(null);
		MIdTxt.setText(null);
		GNameTxt.setText(null);
		MNameTxt.setText(null);
		db_StartMaintainDate.setText(null);
		db_EndMaintainDate.setText(null);
	}
	
	void init(){
		setBounds(100, 100, 618, 410);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7269\u54C1\u7F16\u53F7\uFF1A");
		label.setBounds(50, 33, 69, 15);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 64, 504, 114);
		getContentPane().add(scrollPane);
		
		GM_infTable = new JTable();
		GM_infTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				int row=GM_infTable.getSelectedRow();
				GNameTxt.setText((String)GM_infTable.getValueAt(row, 1));
				MNameTxt.setText((String)GM_infTable.getValueAt(row, 3));
				db_StartMaintainDate.setText((String)GM_infTable.getValueAt(row, 4));
				db_EndMaintainDate.setText((String)GM_infTable.getValueAt(row, 5));
				
			}
		});
		GM_infTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7269\u54C1\u7F16\u53F7", "\u7269\u54C1\u540D\u79F0", "\u7EF4\u62A4\u4EBA\u7F16\u53F7", "\u7EF4\u62A4\u4EBA\u540D\u79F0", "\u7EF4\u62A4\u5F00\u59CB\u65F6\u95F4", "\u7EF4\u62A4\u7ED3\u675F\u65F6\u95F4"
			}
		));
		GM_infTable.getColumnModel().getColumn(4).setPreferredWidth(96);
		GM_infTable.getColumnModel().getColumn(5).setPreferredWidth(93);
		scrollPane.setViewportView(GM_infTable);
		
		MIdTxt = new JTextField();
		MIdTxt.setBounds(350, 30, 72, 21);
		getContentPane().add(MIdTxt);
		MIdTxt.setColumns(10);
		
		GIdTxt = new JTextField();
		GIdTxt.setBounds(129, 30, 88, 21);
		getContentPane().add(GIdTxt);
		GIdTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7EF4\u62A4\u4EBA\u7F16\u53F7\uFF1A");
		label_1.setBounds(252, 33, 88, 15);
		getContentPane().add(label_1);
		
		JButton jb_select = new JButton("\u67E5\u8BE2");
		jb_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Goods_Maintainer gm=new Goods_Maintainer();
				int gid,mid;

				if( StringUtil.isNotEmpty(GIdTxt.getText()) && StringUtil.isEmpty(MIdTxt.getText())){
					
					 gid=Integer.parseInt(GIdTxt.getText());
					 gm.setGoods_Id(gid);
					
				}
				else if(StringUtil.isEmpty(GIdTxt.getText()) && StringUtil.isNotEmpty(MIdTxt.getText())){
					
					 mid=Integer.parseInt(MIdTxt.getText());	
					 gm.setMaintainer_id(mid);
					 
				}else if( StringUtil.isNotEmpty(GIdTxt.getText()) && StringUtil.isNotEmpty(GIdTxt.getText())){
				
					gid=Integer.parseInt(GIdTxt.getText());
					mid=Integer.parseInt(MIdTxt.getText());	
					gm.setGoods_Id(gid);
					gm.setMaintainer_id(mid);
				}
				fillTable(gm);
			}
				
		});
		jb_select.setBounds(485, 29, 69, 23);
		getContentPane().add(jb_select);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(50, 188, 504, 159);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u8D27\u7269\u540D\u79F0\uFF1A");
		label_2.setBounds(30, 30, 70, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u7EF4\u62A4\u4EBA\u5458\u540D\u79F0\uFF1A");
		label_3.setBounds(227, 30, 104, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u7EF4\u62A4\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_4.setBounds(22, 76, 97, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u7EF4\u62A4\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_5.setBounds(227, 76, 104, 15);
		panel.add(label_5);
		
		GNameTxt = new JTextField();
		GNameTxt.setEditable(false);
		GNameTxt.setBounds(120, 30, 97, 21);
		panel.add(GNameTxt);
		GNameTxt.setColumns(10);
		
		MNameTxt = new JTextField();
		MNameTxt.setEditable(false);
		MNameTxt.setColumns(10);
		MNameTxt.setBounds(341, 30, 98, 21);
		panel.add(MNameTxt);
		
		db_EndMaintainDate = new DateButton();
		db_EndMaintainDate.setBounds(341, 76, 100, 23);
		panel.add(db_EndMaintainDate);
		
		db_StartMaintainDate = new DateButton();
		db_StartMaintainDate.setBounds(120, 76, 100, 23);
		panel.add(db_StartMaintainDate);
		
		JButton jb_modify = new JButton("\u4FEE\u6539");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con=null;
				String gname=GNameTxt.getText();
				String mname=MNameTxt.getText();
				String start=db_StartMaintainDate.getText();
				String end=db_EndMaintainDate.getText();
				try {
					con=dbutil.getCon();
					int n=gmdao.GM_modify(con,gname,mname,start,end);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦");
						reset();
						fillTable(new Goods_Maintainer());
					}else{
						JOptionPane.showMessageDialog(null, "ÐÞ¸Ä´íÎó");
					}					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÐÞ¸ÄÊ§°Ü");
				}finally{
					try {
						dbutil.closeCon(con);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		jb_modify.setBounds(161, 114, 63, 23);
		panel.add(jb_modify);
		
		JButton jb_delete = new JButton("\u5220\u9664");
		jb_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				String gname=GNameTxt.getText();
				String mname=MNameTxt.getText();
				try {
					con=dbutil.getCon();
					int n=gmdao.GM_delete(con,gname,mname);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "É¾³ý³É¹¦");
						reset();
						fillTable(new Goods_Maintainer());
					}else{
						JOptionPane.showMessageDialog(null, "É¾³ý´íÎó");
					}					
				} catch (SQLException ed) {
					ed.printStackTrace();
					JOptionPane.showMessageDialog(null, "É¾³ýÊ§°Ü");
				} catch (Exception ed) {
					ed.printStackTrace();
					JOptionPane.showMessageDialog(null, "É¾³ýÊ§°Ü");
				}finally{
					try {
						dbutil.closeCon(con);
					} catch (Exception ed) {
						// TODO Auto-generated catch block
						ed.printStackTrace();
					}
				}
				
			}
		});
		jb_delete.setBounds(268, 114, 63, 23);
		panel.add(jb_delete);
	}
}
