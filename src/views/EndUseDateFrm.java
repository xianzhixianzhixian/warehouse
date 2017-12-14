package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.GMDao;
import Dao.MaintainerDao;
import model.Goods_Maintainer;
import util.DateButton;
import util.Dbutil;
import util.StringUtil;

public class EndUseDateFrm extends JInternalFrame {
	private JTable GM_infTable;
	private JTextField DateTxt;
	
	Dbutil dbutil=new Dbutil();
	GMDao gmdao=new GMDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndUseDateFrm frame = new EndUseDateFrm();
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
	public EndUseDateFrm() {
		setTitle("\u4FDD\u4FEE\u5230\u671F\u67E5\u8BE2");
		setBounds(100, 100, 450, 300);
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
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   	
	}
	void init(){
		setBounds(100, 100, 618, 410);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F53\u524D\u65E5\u671F:");
		label.setBounds(73, 51, 69, 15);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 79, 504, 254);
		getContentPane().add(scrollPane);
		
		GM_infTable = new JTable();
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
		
		DateTxt = new JTextField();
		DateTxt.setEditable(false);
		DateTxt.setBounds(152, 48, 91, 21);
		getContentPane().add(DateTxt);
		DateTxt.setColumns(10);
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		DateTxt.setText(format.format(date).toString());
		
		JButton jb_select = new JButton("\u67E5\u8BE2");
		jb_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Goods_Maintainer gm=new Goods_Maintainer();
				String date=DateTxt.getText();
				gm.setGoods_EndMaintainDate(date);
				fillTable(gm);
			}
				
		});
		jb_select.setBounds(274, 47, 69, 23);
		getContentPane().add(jb_select);
		
	}
	

}
