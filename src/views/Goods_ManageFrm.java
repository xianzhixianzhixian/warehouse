package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.GoodsDao;
import model.Goods;
import model.Goods_Maintainer;
import util.DateButton;
import util.Dbutil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Goods_ManageFrm extends JInternalFrame {
	private JTable GoodsInfTable;
	private JTextField keyTxt;
	private JTextField GidTxt;
	private JTextField GnumTxt;
	private JTextField GnameTxt;
	private JTextField UseYearTxt;
	private JTextField GoodsTypeTxt;
	private DateButton db_BuyDate;
	private DateButton db_StartUseDate; 
	private DateButton db_EndUseDate;
	private JComboBox jcb_keyType;
	Dbutil dbutil=new Dbutil();
	GoodsDao goodsdao=new GoodsDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Goods_ManageFrm frame = new Goods_ManageFrm();
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
	public Goods_ManageFrm() {
		setClosable(true);
		setMaximizable(true);
		init();
		fillTable(new Goods());
	}
	void fillTable(Goods g){
		DefaultTableModel dtm=(DefaultTableModel)GoodsInfTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbutil.getCon();
			ResultSet rs= goodsdao.goodsList(con,g);
			while(rs.next()){
				Vector v=new Vector();
				v.addElement(rs.getString("GoodsId"));
				v.addElement(rs.getString("GoodsNum"));
				v.addElement(rs.getString("GoodsName"));
				v.addElement(rs.getString("GoodsType"));
				v.addElement(rs.getString("BuyDate"));
				v.addElement(rs.getString("StartUseDate"));
				v.addElement(rs.getString("EndUseDate"));
				v.addElement(rs.getString("Useyear"));
		//		v.addElement(rs.getString("StartMaintainDate"));
		//		v.addElement(rs.getString("EndMaintainDate"));
		//		v.addElement(rs.getString("MaintainerName"));
				dtm.addRow(v);
		} 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			//	reset();
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   	
	}
	void reset(){
		 keyTxt.setText(null);
		 GidTxt.setText(null);
		 GnumTxt.setText(null);
		 GnameTxt.setText(null);
		 UseYearTxt.setText(null);
		 GoodsTypeTxt.setText(null);
		 db_BuyDate.setText(null);
		 db_StartUseDate.setText(null); 
		 db_EndUseDate.setText(null);
		 jcb_keyType.setSelectedIndex(-1);
	}
	void init(){
		setTitle("\u8BBE\u5907\u7EF4\u62A4");
		setBounds(100, 100, 926, 522);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 55, 858, 235);
		getContentPane().add(scrollPane);
		
		GoodsInfTable = new JTable();
		GoodsInfTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=GoodsInfTable.getSelectedRow();
				GidTxt.setText((String)GoodsInfTable.getValueAt(row, 0));
				GnumTxt.setText((String)GoodsInfTable.getValueAt(row, 1));
				GnameTxt.setText((String)GoodsInfTable.getValueAt(row,2));
				GoodsTypeTxt.setText((String)GoodsInfTable.getValueAt(row, 3));
				db_BuyDate.setText((String)GoodsInfTable.getValueAt(row, 4));
				db_StartUseDate.setText((String)GoodsInfTable.getValueAt(row, 5));
				db_EndUseDate.setText((String)GoodsInfTable.getValueAt(row, 6));
				UseYearTxt.setText((String)GoodsInfTable.getValueAt(row, 7));
			}
		});
		GoodsInfTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BBE\u5907\u5E8F\u53F7", "\u8BBE\u5907\u7F16\u53F7", "\u8BBE\u5907\u540D\u79F0", "\u8BBE\u5907\u7C7B\u578B", "\u8D2D\u7F6E\u65E5\u671F", "\u542F\u7528\u65E5\u671F", "\u4F7F\u7528\u5230\u671F\u65E5\u671F", "\u5141\u8BB8\u4F7F\u7528\u5E74\u9650"
			}
		));
		GoodsInfTable.getColumnModel().getColumn(6).setPreferredWidth(85);
		GoodsInfTable.getColumnModel().getColumn(7).setPreferredWidth(93);
		scrollPane.setViewportView(GoodsInfTable);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");
		label.setBounds(157, 24, 83, 15);
		getContentPane().add(label);
		
		jcb_keyType = new JComboBox();
		jcb_keyType.setModel(new DefaultComboBoxModel(new String[] {"", "\u8BBE\u5907\u5E8F\u53F7", "\u8BBE\u5907\u7F16\u53F7", "\u8BBE\u5907\u540D\u79F0", "\u8BBE\u5907\u7C7B\u578B", "\u8D2D\u7F6E\u65E5\u671F", "\u542F\u7528\u65E5\u671F", "\u4F7F\u7528\u5230\u671F\u65E5\u671F", "\u4F7F\u7528\u5E74\u9650"}));
		jcb_keyType.setBounds(250, 21, 109, 21);
		getContentPane().add(jcb_keyType);
		
		JLabel label_1 = new JLabel("\u5173\u952E\u5B57\uFF1A");
		label_1.setBounds(433, 27, 54, 15);
		getContentPane().add(label_1);
		
		keyTxt = new JTextField();
		keyTxt.setBounds(509, 24, 109, 21);
		getContentPane().add(keyTxt);
		keyTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods g=new Goods();
				String keyType=(String) jcb_keyType.getSelectedItem();
				String key=keyTxt.getText();
				switch(keyType){
					case("设备序号"):
						g.setGoods_Id(Integer.parseInt(key));
						break;
					case("设备编号"):
						g.setGoods_Num(key);
						break;
					case("设备名称")	:
						g.setGoods_Name(key);
						break;
					case("设备类型"):
						g.setGoods_Type(key);
						break;
					case("购置日期")	:
						g.setGoods_BuyDate(key);
						break;
					case("启用日期")	:
						g.setGoods_StartUseDate(key);
						break;
					case("使用到期日期"):
						g.setGoods_EndUseDate(key);
						break;
					case("使用年限")	:
						g.setGoods_UseYear(Integer.parseInt(key));
						break;
				}
				
				fillTable(g);
			}
		});
		button.setBounds(715, 22, 93, 23);
		getContentPane().add(button);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 301, 858, 174);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u8BBE\u5907\u5E8F\u53F7\uFF1A");
		label_2.setBounds(27, 32, 66, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u8BBE\u5907\u7F16\u53F7\uFF1A");
		label_3.setBounds(27, 69, 66, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u8BBE\u5907\u540D\u79F0\uFF1A");
		label_4.setBounds(223, 32, 66, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u8D2D\u7F6E\u65E5\u671F\uFF1A");
		label_5.setBounds(223, 69, 66, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u542F\u7528\u65E5\u671F\uFF1A");
		label_6.setBounds(409, 32, 66, 15);
		panel.add(label_6);
		
		JLabel label_8 = new JLabel("\u4F7F\u7528\u5230\u671F\u65E5\u671F\uFF1A");
		label_8.setBounds(409, 69, 95, 15);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("\u5141\u8BB8\u4F7F\u7528\u5E74\u9650\uFF1A");
		label_9.setBounds(648, 38, 95, 15);
		panel.add(label_9);
		
		JLabel label_12 = new JLabel("\u8BBE\u5907\u7C7B\u578B\uFF1A");
		label_12.setBounds(648, 75, 95, 15);
		panel.add(label_12);
		
		GidTxt = new JTextField();
		GidTxt.setEditable(false);
		GidTxt.setBounds(103, 32, 83, 21);
		panel.add(GidTxt);
		GidTxt.setColumns(10);
		
		GnumTxt = new JTextField();
		GnumTxt.setColumns(10);
		GnumTxt.setBounds(103, 69, 83, 21);
		panel.add(GnumTxt);
		
		GnameTxt = new JTextField();
		GnameTxt.setColumns(10);
		GnameTxt.setBounds(299, 32, 83, 21);
		panel.add(GnameTxt);
		
		UseYearTxt = new JTextField();
		UseYearTxt.setColumns(10);
		UseYearTxt.setBounds(748, 32, 100, 21);
		panel.add(UseYearTxt);
		
		GoodsTypeTxt = new JTextField();
		GoodsTypeTxt.setColumns(10);
		GoodsTypeTxt.setBounds(748, 69, 100, 21);
		panel.add(GoodsTypeTxt);
		
		db_BuyDate=new DateButton();
		db_BuyDate.setBounds(299, 69, 100, 23);
		panel.add(db_BuyDate);
		
		db_StartUseDate = new DateButton();
		db_StartUseDate.setBounds(508, 32, 100, 23);
		panel.add(db_StartUseDate);
		
		db_EndUseDate = new DateButton();
		db_EndUseDate.setBounds(508, 69, 100, 23);
		panel.add(db_EndUseDate);
		
		JButton jb_modify = new JButton("\u786E\u8BA4\u4FEE\u6539");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int gid=Integer.parseInt(GidTxt.getText());
				String gnum=GnumTxt.getText();
				String gname=GnameTxt.getText();
				String gtype=GoodsTypeTxt.getText();
				String buydate=db_BuyDate.getText();
				String sud=db_StartUseDate.getText();
				String eud=db_EndUseDate.getText();
				int year=Integer.parseInt(UseYearTxt.getText());
				Goods g=new Goods(gid,gnum,gname,gtype,buydate,sud,eud,year);
				Connection con=null;
				try {
					con=dbutil.getCon();
					int n=goodsdao.Goods_Modify(con,g);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "修改成功");
						reset();
						fillTable(new Goods());
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "修改失败");
				}
			}
		});
		jb_modify.setBounds(242, 120, 93, 23);
		panel.add(jb_modify);
		
		JButton jb_delete = new JButton("\u5220\u9664");
		jb_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int gid=Integer.parseInt(GidTxt.getText());
				Connection con=null;
				try {
					con=dbutil.getCon();
					int n=goodsdao.Goods_delete(con,gid);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "删除成功");
						reset();
						fillTable(new Goods());
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		});
		jb_delete.setBounds(439, 120, 93, 23);
		panel.add(jb_delete);
		

	}
}
