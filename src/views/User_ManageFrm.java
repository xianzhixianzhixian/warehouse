package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import Dao.UserDao;
import model.User;
import util.Dbutil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class User_ManageFrm extends JInternalFrame {
	private JTable UserTable;
	private JTextField S_usernameTxt;
	private JTextField UserIdTxt;
	private JTextField UsernameTxt;
	private JTextField PasswordTxt;
	private JComboBox UserType;
	
	Dbutil dbutil=new Dbutil();
	UserDao userdao=new UserDao();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_ManageFrm frame = new User_ManageFrm();
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
	public User_ManageFrm() {
		initFrm();
		fillTable(null);
		
	}
	void fillTable(String name){
		DefaultTableModel dtm=(DefaultTableModel)UserTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbutil.getCon();
			ResultSet rs= userdao.userList(con,name);
			while(rs.next()){
				Vector v=new Vector();
				v.addElement(rs.getString("UserId"));
				v.addElement(rs.getString("UserName"));
				v.addElement(rs.getString("UserPassword"));
				v.addElement(rs.getString("UserType"));
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
	void reset(){
		UserIdTxt.setText(null);
		UsernameTxt.setText(null);
		PasswordTxt.setText(null);
		UserType.setSelectedIndex(-1);
	}
	void initFrm(){
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setTitle("\u7528\u6237\u7BA1\u7406");
		setBounds(100, 100, 518, 426);
		getContentPane().setLayout(null);
		
		JScrollPane Table = new JScrollPane();
		Table.setBounds(39, 84, 411, 153);
		getContentPane().add(Table);
		
		UserTable = new JTable();
		UserTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row=UserTable.getSelectedRow();
				UserIdTxt.setText((String)UserTable.getValueAt(row, 0));
				UsernameTxt.setText((String)UserTable.getValueAt(row, 1));
				PasswordTxt.setText((String)UserTable.getValueAt(row, 2));
				
				int n=UserType.getItemCount();
				for(int i=0;i<n;i++){
					String item=(String)UserType.getItemAt(i);
					if(UserTable.getValueAt(row, 3).equals(item)){
						UserType.setSelectedIndex(i);
					}
				}
			}
		});
		UserTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u7F16\u53F7", "\u7528\u6237\u540D", "\u5BC6\u7801", "\u8EAB\u4EFD"
			}
		));
		UserTable.getColumnModel().getColumn(3).setPreferredWidth(98);
		Table.setViewportView(UserTable);
		
		JLabel label = new JLabel("\u7528\u6237\u59D3\u540D\uFF1A");
		label.setBounds(39, 51, 70, 25);
		getContentPane().add(label);
		
		S_usernameTxt = new JTextField();
		S_usernameTxt.setBounds(119, 53, 120, 21);
		getContentPane().add(S_usernameTxt);
		S_usernameTxt.setColumns(10);
		
		JButton Bt_select = new JButton("\u67E5\u8BE2");
		Bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=S_usernameTxt.getText();
				fillTable(username);
			}
		});
		Bt_select.setBounds(310, 52, 93, 23);
		getContentPane().add(Bt_select);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 247, 411, 139);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setBounds(188, 40, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(17, 73, 54, 15);
		panel.add(label_2);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(252, 37, 103, 21);
		panel.add(UsernameTxt);
		UsernameTxt.setColumns(10);
		
		PasswordTxt = new JTextField();
		PasswordTxt.setBounds(85, 73, 90, 21);
		panel.add(PasswordTxt);
		PasswordTxt.setColumns(10);
		
		JButton jb_modify = new JButton("\u786E\u8BA4\u4FEE\u6539");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int uid=Integer.parseInt((UserIdTxt.getText()));
				String name=UsernameTxt.getText();
				String pw=PasswordTxt.getText();
				String ut=(String) UserType.getSelectedItem();
				
				User user=new User(uid,name,pw,ut);
				Connection con=null;
				try {
					con=dbutil.getCon();
					int n=userdao.ModifyUser(con, user);
					if(n!=0){
						JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦");
						reset();
						fillTable(name);
					}else
						JOptionPane.showMessageDialog(null, "ÐÞ¸ÄÊ§°Ü");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		jb_modify.setBounds(107, 106, 93, 23);
		panel.add(jb_modify);
		
		JButton jb_delete = new JButton("\u5220\u9664\u4FE1\u606F");
		jb_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int uid=Integer.parseInt((UserIdTxt.getText()));
				Connection con=null;
				
				try {
					con=dbutil.getCon();
					int n=userdao.DeleteUser(con, uid);
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
		jb_delete.setBounds(223, 106, 93, 23);
		panel.add(jb_delete);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		label_3.setBounds(15, 40, 66, 15);
		panel.add(label_3);
		
		UserIdTxt = new JTextField();
		UserIdTxt.setEditable(false);
		UserIdTxt.setBounds(84, 40, 91, 21);
		panel.add(UserIdTxt);
		UserIdTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("\u8EAB\u4EFD\uFF1A");
		label_4.setBounds(188, 73, 54, 15);
		panel.add(label_4);
		
		UserType = new JComboBox();
		UserType.setModel(new DefaultComboBoxModel(new String[] {"user", "admin"}));
		UserType.setBounds(252, 70, 103, 21);
		panel.add(UserType);
		UserType.setSelectedIndex(-1);
	

	}
}
