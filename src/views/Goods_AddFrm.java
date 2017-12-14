package views;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.GoodsDao;
import model.Goods;
import util.DateButton;
import util.Dbutil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Goods_AddFrm extends JInternalFrame {
	private JTextField GNameTxt;
	private JTextField UseYearTxt;
	private JTextField GnumTxt;
	private DateButton db_BuyDate;
	private DateButton db_StartUseDate ;
	private DateButton db_EndUseDate;
	private JTextField TypeTxt;
	
	Dbutil dbutil=new Dbutil();
	GoodsDao goodsdao=new GoodsDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Goods_AddFrm frame = new Goods_AddFrm();
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
	public Goods_AddFrm() {
		setTitle("\u8BBE\u5907\u6DFB\u52A0");
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 506, 319);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BBE\u5907\u7F16\u53F7\uFF1A");
		label.setBounds(32, 50, 67, 28);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8BBE\u5907\u540D\u79F0\uFF1A");
		label_1.setBounds(32, 96, 67, 28);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u8D2D\u7F6E\u65E5\u671F\uFF1A");
		label_2.setBounds(32, 134, 67, 28);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u542F\u7528\u65E5\u671F\uFF1A");
		label_3.setBounds(254, 134, 67, 28);
		getContentPane().add(label_3);
		
		JLabel label_5 = new JLabel("\u4F7F\u7528\u5230\u671F\u65E5\u671F\uFF1A");
		label_5.setBounds(32, 172, 98, 28);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u5141\u8BB8\u4F7F\u7528\u5E74\u9650\uFF1A");
		label_6.setBounds(254, 96, 98, 28);
		getContentPane().add(label_6);
		
		JLabel label_8 = new JLabel("\u8BBE\u5907\u7C7B\u578B\uFF1A");
		label_8.setBounds(254, 50, 67, 28);
		getContentPane().add(label_8);
		
		GNameTxt = new JTextField();
		GNameTxt.setColumns(10);
		GNameTxt.setBounds(119, 96, 100, 21);
		getContentPane().add(GNameTxt);
		
		JButton jb_add = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		jb_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String goods_Num=GnumTxt.getText();
				String goods_Name=GNameTxt.getText();
				String goods_BuyDate=db_BuyDate.GetDate();
				String goods_StartUseDate=db_StartUseDate.GetDate();
				String goods_EndUseDate=db_EndUseDate.GetDate();
				int goods_UseYear=Integer.parseInt(UseYearTxt.getText());
				String goods_Type=TypeTxt.getText();
				

				Goods g=new Goods(goods_Num,   goods_Name,  goods_BuyDate,  goods_StartUseDate,
						goods_EndUseDate,  goods_UseYear, goods_Type);

				
				Connection con=null;
				
				try {
					con=dbutil.getCon();
					int n=goodsdao.GoodsAdd(con, g);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "ÃÌº”≥…π¶");
						reset();
					}
					else
						JOptionPane.showMessageDialog(null, "ÃÌº”¥ÌŒÛ");
				} catch (SQLException ep) {
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÃÌº” ß∞‹");
				}catch (Exception ep) {
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÃÌº” ß∞‹");
				}
				finally{
					try {
						dbutil.closeCon(con);
					} catch (Exception ep) {
						ep.printStackTrace();
					}
				}
				
				
				
			}
		});
		jb_add.setBounds(119, 239, 93, 23);
		getContentPane().add(jb_add);
		
		JButton jb_reset = new JButton("\u91CD\u7F6E");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				reset();
			}
		});
		jb_reset.setBounds(252, 239, 93, 23);
		getContentPane().add(jb_reset);
		
		db_BuyDate=new DateButton();
		db_BuyDate.setBounds(119, 134, 100, 23);
		getContentPane().add(db_BuyDate);
		
		db_StartUseDate = new DateButton();
		db_StartUseDate.setBounds(348, 137, 100, 23);
		getContentPane().add(db_StartUseDate);
		
		db_EndUseDate = new DateButton();
		db_EndUseDate.setBounds(119, 172, 100, 23);
		getContentPane().add(db_EndUseDate);
		
		UseYearTxt = new JTextField();
		UseYearTxt.setBounds(347, 100, 100, 21);
		getContentPane().add(UseYearTxt);
		UseYearTxt.setColumns(10);
		
		GnumTxt = new JTextField();
		GnumTxt.setBounds(119, 54, 100, 21);
		getContentPane().add(GnumTxt);
		GnumTxt.setColumns(10);
		
		TypeTxt = new JTextField();
		TypeTxt.setBounds(349, 54, 98, 21);
		getContentPane().add(TypeTxt);
		TypeTxt.setColumns(10);


	}
	
	void reset(){

		GnumTxt.setText(null);
		GNameTxt.setText(null);
		db_BuyDate.setText(null);
		db_StartUseDate.setText(null);
		db_EndUseDate.setText(null);
		UseYearTxt.setText(null);
		TypeTxt.setText(null);
	
		
	}

}
