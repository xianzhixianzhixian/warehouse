package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;

public class UserMainFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainFrm frame = new UserMainFrm();
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
	public UserMainFrm() {
setExtendedState(MAXIMIZED_BOTH);//×î´ó»¯
		
        setTitle("\u4ED3\u5E93\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
			Maintainer_ManageFrm mpf=new Maintainer_ManageFrm();
				mpf.setVisible(true);
				getContentPane().add(mpf);

			}
		});
		menu_2.add(jmi_Maintain);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("\u7269\u8D44\u4FDD\u4FEE\u4FE1\u606F\u7EF4\u62A4");
		checkBoxMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GM_ManageFrm gmm=new GM_ManageFrm();
				gmm.setVisible(true);
				getContentPane().add(gmm);
				
			}
		});
		menu_3.add(checkBoxMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(1358, 695, -1362, -696);
		contentPane.add(desktopPane);
	
		
	}
}
