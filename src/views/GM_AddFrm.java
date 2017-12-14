package views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.GMDao;
import model.Goods_Maintainer;
import util.DateButton;
import util.Dbutil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GM_AddFrm extends JInternalFrame {
	private JTextField GidTxt;
	private JTextField MidTxt;
	private DateButton db_StartMaintainDate;
	private DateButton db_EndMaintainDate;
	GMDao GMdao=new GMDao();
	Dbutil dbutil=new Dbutil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GM_AddFrm frame = new GM_AddFrm();
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
	public GM_AddFrm() {
		setTitle("\u7269\u8D44\u4FDD\u4FEE\u660E\u7EC6\u6DFB\u52A0");
		setMaximizable(true);
		setClosable(true);
		init();

	}
	void reset(){
		GidTxt.setText(null);
		MidTxt.setText(null);
		db_StartMaintainDate.setText(null);
	    db_EndMaintainDate.setText(null);
	}
	void init(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7269\u8D44\u7F16\u53F7\uFF1A");
		label.setBounds(66, 61, 69, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u62A5\u4FEE\u4EBA\u7F16\u53F7\uFF1A");
		label_1.setBounds(235, 61, 79, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u7EF4\u4FDD\u542F\u7528\u65E5\u671F\uFF1A");
		label_2.setBounds(66, 133, 95, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u7EF4\u4FDD\u5230\u671F\u65E5\u671F\uFF1A");
		label_3.setBounds(236, 133, 95, 15);
		getContentPane().add(label_3);
		
		GidTxt = new JTextField();
		GidTxt.setBounds(109, 88, 100, 21);
		getContentPane().add(GidTxt);
		GidTxt.setColumns(10);
		
		MidTxt = new JTextField();
		MidTxt.setColumns(10);
		MidTxt.setBounds(278, 86, 100, 21);
		getContentPane().add(MidTxt);
		
		db_StartMaintainDate = new DateButton();
		db_StartMaintainDate.setBounds(109, 158, 100, 23);
		getContentPane().add(db_StartMaintainDate);
		
		db_EndMaintainDate = new DateButton();
		db_EndMaintainDate.setBounds(278, 158, 100, 23);
		getContentPane().add(db_EndMaintainDate);
		
		JButton jb_add = new JButton("\u6DFB\u52A0");
		jb_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int gid=Integer.parseInt(GidTxt.getText());
				int mid=Integer.parseInt(MidTxt.getText());
				String smd=db_StartMaintainDate.getText();
				String emd=db_EndMaintainDate.getText();
				
				Goods_Maintainer gm=new Goods_Maintainer(gid,mid,smd,emd);
				Connection con=null;
				
				try {
					con=dbutil.getCon();
					int n=GMdao.GM_Add(con, gm);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "ÃÌº”≥…π¶");
						reset();
					}
					else
						JOptionPane.showMessageDialog(null, "ÃÌº”¥ÌŒÛ");
				} catch (SQLException ep) {
					// TODO Auto-generated catch block
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÃÌº” ß∞‹");
				}catch (Exception ep) {
					// TODO Auto-generated catch block
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÃÌº” ß∞‹");
				}
				finally{
					try {
						dbutil.closeCon(con);
					} catch (Exception ep) {
						// TODO Auto-generated catch block
						ep.printStackTrace();
					}
				}
				
				
			}
		});
		jb_add.setBounds(127, 219, 93, 23);
		getContentPane().add(jb_add);
		
		JButton jb_reset = new JButton("\u91CD\u7F6E");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		jb_reset.setBounds(253, 219, 93, 23);
		getContentPane().add(jb_reset);
	}
}
