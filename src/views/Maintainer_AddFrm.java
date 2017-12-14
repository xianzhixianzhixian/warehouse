package views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.MaintainerDao;
import model.Maintainer;
import util.Dbutil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Maintainer_AddFrm extends JInternalFrame {
	private JTextField nameTxt;
	private JTextField jobTxt;
	private JTextField CompanyTxt;
	private JTextField TelTxt;
	Dbutil dbutil=new Dbutil();
	MaintainerDao mdao=new MaintainerDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maintainer_AddFrm frame = new Maintainer_AddFrm();
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
	public Maintainer_AddFrm() {
		setTitle("\u6DFB\u52A0\u7EF4\u4FEE\u4EBA\u5458");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 500, 245);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(46, 41, 53, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u804C\u52A1\uFF1A");
		label_1.setBounds(229, 41, 61, 15);
		getContentPane().add(label_1);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(109, 41, 77, 21);
		getContentPane().add(nameTxt);
		
		jobTxt = new JTextField();
		jobTxt.setColumns(10);
		jobTxt.setBounds(316, 41, 77, 21);
		getContentPane().add(jobTxt);
		
		JLabel label_2 = new JLabel("\u516C\u53F8\uFF1A");
		label_2.setBounds(46, 91, 53, 15);
		getContentPane().add(label_2);
		
		CompanyTxt = new JTextField();
		CompanyTxt.setColumns(10);
		CompanyTxt.setBounds(109, 91, 77, 21);
		getContentPane().add(CompanyTxt);
		
		JLabel label_3 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setBounds(229, 91, 61, 15);
		getContentPane().add(label_3);
		
		TelTxt = new JTextField();
		TelTxt.setColumns(10);
		TelTxt.setBounds(316, 91, 77, 21);
		getContentPane().add(TelTxt);
		
		JButton button = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name=nameTxt.getText();
				String job=jobTxt.getText();
				String company=CompanyTxt.getText();
				String tel=TelTxt.getText();
				
				System.out.println(name+","+job+","+company+","+tel);
				Maintainer m=new Maintainer(name,job,company,tel);
				Connection con=null;
				try {
					con=dbutil.getCon();
					int n=mdao.AddMaintainer(con, m);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "添加成功");
						reset();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "添加失败");
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
		});
		button.setBounds(126, 156, 93, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				reset();
			}
		});
		button_1.setBounds(239, 156, 93, 23);
		getContentPane().add(button_1);

	}
	void reset(){
		nameTxt.setText(null);
		jobTxt.setText(null);
		CompanyTxt.setText(null);
		TelTxt.setText(null);
	}
}
