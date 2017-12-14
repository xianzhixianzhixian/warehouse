package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Color;
import java.awt.SystemColor;


public class AdminMainFrm extends JFrame {

	private JPanel jmi_endUseDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrm frame = new AdminMainFrm();
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
	public AdminMainFrm() {
		
		setExtendedState(MAXIMIZED_BOTH);//×î´ó»¯
		
        setTitle("\u4ED3\u5E93\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu);
		
		JCheckBoxMenuItem jmi_userManage = new JCheckBoxMenuItem("\u7528\u6237\u7BA1\u7406");
		jmi_userManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_ManageFrm umf = new User_ManageFrm();
				umf.setVisible(true);
				getContentPane().add(umf);
			}
		});
		menu.add(jmi_userManage);
		
		JMenu menu_1 = new JMenu("\u7269\u8D44\u7BA1\u7406");
		menuBar.add(menu_1);
		
		JCheckBoxMenuItem jmi_goodsAdd = new JCheckBoxMenuItem("\u7269\u8D44\u589E\u52A0");
		jmi_goodsAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods_AddFrm gaf= new Goods_AddFrm();
				gaf.setVisible(true);
				getContentPane().add(gaf);
			}
		});
		menu_1.add(jmi_goodsAdd);
		
		JCheckBoxMenuItem jmi_goodsMaintain = new JCheckBoxMenuItem("\u7269\u8D44\u7EF4\u62A4");
		jmi_goodsMaintain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods_ManageFrm mmf=new Goods_ManageFrm();
				mmf.setVisible(true);
				getContentPane().add(mmf);
			}
		});
		menu_1.add(jmi_goodsMaintain);
		
		JMenu menu_2 = new JMenu("\u4FDD\u4FEE\u8054\u7CFB\u4EBA");
		menuBar.add(menu_2);
		
		JCheckBoxMenuItem jmi_Maintain = new JCheckBoxMenuItem("\u4FDD\u4FEE\u4EBA\u5458\u7EF4\u62A4");
		jmi_Maintain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Maintainer_ManageFrm mmf=new Maintainer_ManageFrm();
				mmf.setVisible(true);
				getContentPane().add(mmf);

			}
		});
		
		JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem("\u6DFB\u52A0\u62A5\u4FEE\u4EBA\u5458");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Maintainer_AddFrm maf=new Maintainer_AddFrm();
				maf.setVisible(true);
				getContentPane().add(maf);
				
			}
		});
		menu_2.add(menuItem);
		menu_2.add(jmi_Maintain);
		
		JMenu menu_3 = new JMenu("\u7269\u8D44\u4FDD\u4FEE\u660E\u7EC6");
		menuBar.add(menu_3);
		
		JCheckBoxMenuItem jmi_gmAdd = new JCheckBoxMenuItem("\u6DFB\u52A0\u7269\u8D44\u4FDD\u4FEE\u4FE1\u606F");
		jmi_gmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GM_AddFrm gma=new GM_AddFrm();
				gma.setVisible(true);
				getContentPane().add(gma);
				
			}
		});
		menu_3.add(jmi_gmAdd);
		
		JCheckBoxMenuItem jmi_gmManage = new JCheckBoxMenuItem("\u7269\u8D44\u4FDD\u4FEE\u4FE1\u606F\u7EF4\u62A4");
		jmi_gmManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GM_ManageFrm gmm=new GM_ManageFrm();
				gmm.setVisible(true);
				getContentPane().add(gmm);
				
			}
		});
		menu_3.add(jmi_gmManage);
		
		JMenu menu_4 = new JMenu("\u4F7F\u7528\u5230\u671F\u67E5\u8BE2");
		menuBar.add(menu_4);
		
		JCheckBoxMenuItem EndUseDate = new JCheckBoxMenuItem("\u4F7F\u7528\u5230\u671F\u67E5\u8BE2");
		EndUseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EndUseDateFrm eudf=new EndUseDateFrm();
				eudf.setVisible(true);
				getContentPane().add(eudf);
			}
		});
		menu_4.add(EndUseDate);
		
		jmi_endUseDate = new JPanel();
		jmi_endUseDate.setBackground(SystemColor.activeCaption);
		jmi_endUseDate.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jmi_endUseDate);
		jmi_endUseDate.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(1358, 695, -1362, -696);
		jmi_endUseDate.add(desktopPane);
		
		
	}
}
