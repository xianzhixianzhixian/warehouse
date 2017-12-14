package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.UserDao;
import model.User;
import util.Dbutil;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTxt;
	private JPasswordField PasswordTxt;
	Dbutil dbutil=new Dbutil();
	UserDao userdao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setTitle("\u4ED3\u5E93\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D:");
		label.setBounds(113, 68, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setBounds(113, 103, 54, 15);
		contentPane.add(label_1);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(163, 65, 164, 21);
		contentPane.add(UsernameTxt);
		UsernameTxt.setColumns(10);
		
		PasswordTxt = new JPasswordField();
		PasswordTxt.setBounds(163, 100, 164, 21);
		contentPane.add(PasswordTxt);
		
		JCheckBox CheckUserType = new JCheckBox("\u7BA1\u7406\u5458\u767B\u5F55");
		CheckUserType.setBounds(241, 139, 123, 23);
		contentPane.add(CheckUserType);
		
		JButton jb_login = new JButton("\u767B\u5F55");
		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username=UsernameTxt.getText();
				String password=PasswordTxt.getText();
				String usertype="user";
				if(CheckUserType.isSelected())
					usertype="admin";
				if(StringUtil.isEmpty(username)){
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return;
				}
				if(StringUtil.isEmpty(password)){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				
				Connection con=null;
				User user=new User(username,password,usertype);
				
				try {
					con=dbutil.getCon();
					User currentuser=userdao.login(con, user);
					if(currentuser!=null){
						dispose();//销毁当前页面
						if(currentuser.getUserType().equals("admin"))   //管理员界面
							new AdminMainFrm().setVisible(true);
						else    //用户界面
							new UserMainFrm().setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "用户名或密码错误");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "登录失败");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "登录失败");
				}
				finally{
					try {
						dbutil.closeCon(con);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		jb_login.setBounds(133, 165, 93, 23);
		contentPane.add(jb_login);
		
		JButton jb_register = new JButton("\u6CE8\u518C");
		jb_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();//销毁当前页面
				new RegisterFrm().setVisible(true);
			}
		});
		jb_register.setBounds(251, 165, 93, 23);
		contentPane.add(jb_register);
		
		JLabel label_2 = new JLabel("\u4ED3\u5E93\u7BA1\u7406\u7CFB\u7EDF\u6B22\u8FCE\u4F60\uFF01");
		label_2.setFont(new Font("楷体", Font.PLAIN, 22));
		label_2.setBounds(113, 10, 231, 33);
		contentPane.add(label_2);
	}
}
