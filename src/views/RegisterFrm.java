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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField NameTxt;
	private JPasswordField PasswordTxt;
	private JPasswordField RepasswordTxt;
	Dbutil dbutil=new Dbutil();
	UserDao userdao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrm frame = new RegisterFrm();
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
	public RegisterFrm() {
		setTitle("\u4FE1\u606F\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(71, 54, 63, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(71, 79, 74, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_2.setBounds(71, 104, 74, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u662F\u5426\u4E3A\u7BA1\u7406\u5458\uFF1A");
		label_3.setBounds(71, 139, 93, 23);
		contentPane.add(label_3);
		
		JRadioButton jrb_t = new JRadioButton("\u662F");
		jrb_t.setBounds(170, 139, 48, 23);
		contentPane.add(jrb_t);
		
		JRadioButton jrb_f = new JRadioButton("\u5426");
		jrb_f.setBounds(220, 139, 48, 23);
		contentPane.add(jrb_f);
		jrb_f.setSelected(true);
		
		ButtonGroup  buttonGroup=new ButtonGroup();
		buttonGroup.add(jrb_t);
		buttonGroup.add(jrb_f);
		
		JButton jb_reset = new JButton("\u91CD\u586B");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NameTxt.setText(null);
				PasswordTxt.setText(null);
				RepasswordTxt.setText(null);
				jrb_t.setSelected(false);
				jrb_f.setSelected(true);
			}
		});
		jb_reset.setBounds(71, 178, 93, 23);
		contentPane.add(jb_reset);
		
		JButton jb_register = new JButton("\u6CE8\u518C");
		jb_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=NameTxt.getText();
				String pw=PasswordTxt.getText();
				String rpw=RepasswordTxt.getText();
				String type="user";
				if(jrb_t.isSelected())
					type="admin";
				if(StringUtil.isEmpty(name)){
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return;
				}
				if(StringUtil.isEmpty(pw)){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				if(StringUtil.isEmpty(rpw)){
					JOptionPane.showMessageDialog(null, "确认密码不能为空");
					return;
				}
				if(!pw.equals(rpw)){
					JOptionPane.showMessageDialog(null, "两次密码不一致");
					return;
				}
				Connection con=null;
				User user=new User(name,pw,type);
				
				try {
					con=dbutil.getCon();
					int n=userdao.register(con, user);
					if(n!=0){
						dispose();//销毁当前页面
						new LoginFrm().setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "注册错误");
				} catch (SQLException ep) {
					// TODO Auto-generated catch block
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "注册失败");
				}catch (Exception ep) {
					// TODO Auto-generated catch block
					ep.printStackTrace();
					JOptionPane.showMessageDialog(null, "注册失败");
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
		jb_register.setBounds(195, 178, 93, 23);
		contentPane.add(jb_register);
		
		NameTxt = new JTextField();
		NameTxt.setBounds(130, 51, 138, 21);
		contentPane.add(NameTxt);
		NameTxt.setColumns(10);
		
		PasswordTxt = new JPasswordField();
		PasswordTxt.setBounds(130, 76, 138, 21);
		contentPane.add(PasswordTxt);
		
		RepasswordTxt = new JPasswordField();
		RepasswordTxt.setBounds(130, 101, 138, 21);
		contentPane.add(RepasswordTxt);
	}
}
