package util;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import javax.swing.border.*;

import java.util.*;


public class DateButton extends JButton implements ActionListener
{
	private Calendar calendar;
	private DateChooser dateChooser;
	public DateButton()
	{
		this(Calendar.getInstance());
	}
	public DateButton(Calendar calendar)
	{
		this.calendar=calendar;
		flushCaption();
		setBorder(null);
		super.addActionListener(this);
	}
	//���Ǹ���ķ�����ʹ֮��Ч
	//ʹ���߲����ٸ�����Ķ������ͬ����������
	@Override
	public void addActionListener(ActionListener l){}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(dateChooser==null) dateChooser=new DateChooser();
		Point p=getLocationOnScreen();
		p.y+=getHeight();
		dateChooser.showDateChooser(p);
	}
	//����ӿ�
	public Calendar getCalendar()
	{
		return (Calendar)calendar.clone();
	}
	@SuppressWarnings("deprecation")
	public String GetDate(){
		String date=calendar.get(Calendar.YEAR)+"-"+
		(calendar.get(Calendar.MONTH)+1)+"-"+
		calendar.get(Calendar.DAY_OF_MONTH);
		return date;
	}
	
	//ˢ���ı���ʾ
	public void flushCaption()
	{
		if(calendar==null) return;
		String dateInf=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+
					calendar.get(Calendar.DAY_OF_MONTH);

		setText(dateInf);
	}
	//�ڲ��࣬��ʾ�������
	private class DateChooser extends JPanel implements ActionListener,ChangeListener,WindowListener
	{

		private  int WIDTH=190,HEIGHT=184;
		JSpinner spnYear;
		JSpinner spnMonth;

		JDialog dialog=null;
		
		int startYear=1970;
		int endYear=2050;
		
		JButton[][] btnDays=new JButton[6][7];	//����ѡ��ť
		
		DateChooser()
		{
			JPanel pnlTop=getTopPanel();
			add(pnlTop,BorderLayout.NORTH);
			JPanel pnlBottom=getBottomPanel();
			add(pnlBottom,BorderLayout.CENTER);
			setBorder(new LineBorder(Color.orange,2));
		}
		private JPanel getTopPanel()
		{
			int currentYear=calendar.get(Calendar.YEAR);
			int currentMonth=calendar.get(Calendar.MONTH)+1;
			int currentDay=calendar.get(Calendar.DAY_OF_MONTH);
			JPanel panel=new JPanel();
			panel.setBackground(Color.pink);
			panel.setLayout(new FlowLayout());
			spnYear=new JSpinner(new SpinnerNumberModel(currentYear,startYear,endYear,1));
			spnYear.setEditor(new JSpinner.NumberEditor(spnYear,"####"));
			spnYear.setPreferredSize(new Dimension(48,20));
			spnYear.addChangeListener(this);
			panel.add(spnYear);
			JLabel lblYear=new JLabel("��");
			lblYear.setForeground(Color.white);
			panel.add(lblYear);
			
			spnMonth=new JSpinner(new SpinnerNumberModel(currentMonth,1,12,1));
			spnMonth.setPreferredSize(new Dimension(35,20));
			spnMonth.addChangeListener(this);
			panel.add(spnMonth);
			JLabel lblMonth=new JLabel("��");
			lblMonth.setForeground(Color.white);
			panel.add(lblMonth);
			
			return panel;
		}
		private JPanel getBottomPanel()
		{
			String columns[]={"��","һ","��","��","��","��","��"};
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(7,7));
			panel.setBackground(Color.white);
			panel.setFont(new Font("����",Font.PLAIN,14));
			for(int i=0;i<7;i++)
			{
				JLabel cell=new JLabel(" "+columns[i]);
				if(i==0||i==6)
				{
					cell.setForeground(Color.red);
				}
				else
				{
					cell.setForeground(Color.blue);
				}
				cell.setFont(new Font("����",Font.PLAIN,17));
				panel.add(cell);
			}
			
			int actionCommandId=0;
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<7;j++)
				{
					JButton button=new JButton();
					button.setBorder(null);
					button.setActionCommand(String.valueOf(actionCommandId));
					button.setHorizontalAlignment(SwingConstants.RIGHT);
					button.addActionListener(this);
					button.setBackground(Color.white);
					if(j==0 || j==6) button.setForeground(Color.red);
					else button.setForeground(Color.black);
					btnDays[i][j]=button;
					panel.add(button);
					actionCommandId++;
				}
			}
			
			return panel;
		}
		//��ʾ����ѡ�����Ի���
		public void showDateChooser(Point position)
		{
			if(dialog==null) dialog=createDialog();
			dialog.setLocation(position);
			flushDateChooser();//��ȷ��ʾ����������
			setDayColor();
			dialog.setVisible(true);
		}
		private JDialog createDialog()
		{
			Frame owner=(Frame)SwingUtilities.getWindowAncestor(DateButton.this);
			JDialog dialog=new JDialog();//(owner,false);
			dialog.setUndecorated(true);
			dialog.add(this,BorderLayout.CENTER);
			dialog.pack();
			dialog.setSize(WIDTH,HEIGHT);
			dialog.addWindowListener(this);
			return dialog;
		}

		//ʹ���ڰ���ȷ������˳������
		private void flushDateChooser()
		{
			Calendar c=(Calendar)calendar.clone();
			c.set(Calendar.DAY_OF_MONTH,1);
			int maxDayNo=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int dayNo=2-c.get(Calendar.DAY_OF_WEEK);
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<7;j++)
				{
					String caption="";
					if(dayNo>=1 && dayNo<=maxDayNo) caption=String.valueOf(dayNo);
					btnDays[i][j].setText(caption);
					dayNo++;
				}
			}
		}
		//���������ı���ɫ
		private void setDayColor()
		{
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<7;j++)
				{
					if(btnDays[i][j].getText().length()==0) continue;
					if(j==0 || j==6) btnDays[i][j].setForeground(Color.red);
					else btnDays[i][j].setForeground(Color.black);
					int dayNo=calendar.get(Calendar.DAY_OF_MONTH);
					if(dayNo==Integer.parseInt(btnDays[i][j].getText())) btnDays[i][j].setForeground(Color.yellow);
				}
			}
		}

		/*
		* �������������
		* ����ԭ��������1��31��
		* �ı��·�Ϊ2�£���ʱ���ڻ���3��3�ջ�3��2��
		* ��ȷ�����ǣ�ʹ���Ϊ2��28��29���� 
		*/
		private void checkDate()
		{
			int month1=((Integer)spnMonth.getValue()).intValue();
			int month2=calendar.get(Calendar.MONTH)+1;
			if(month1==month2) return;
			int dayDifference=0-calendar.get(Calendar.DAY_OF_MONTH);
			calendar.add(Calendar.DAY_OF_YEAR,dayDifference);
		}

		@Override
		public void stateChanged(ChangeEvent ce) 
		{
			JSpinner spinner=(JSpinner)ce.getSource();
			Integer number=(Integer)spinner.getValue();
			if(spinner==spnYear)
			{
				calendar.set(Calendar.YEAR,number.intValue());
				checkDate();
			}
			else if(spinner==spnMonth)
			{
				calendar.set(Calendar.MONTH,number.intValue()-1);
				checkDate();
			}

			flushCaption();		//��������ʾ��ť���ı�
			flushDateChooser();	//�������ڣ���Ӧ����
			setDayColor();		//ʹѡ������ڱ�ɫ��δѡ������ڻָ�ԭɫ
		}

		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			JButton button=(JButton)ae.getSource();
			String day=button.getText();
			if(day.length()==0) return;
			int dayNo=Integer.parseInt(day);
			calendar.set(Calendar.DAY_OF_MONTH,dayNo);
			flushCaption();
			setDayColor();
		}
		@Override
		public void windowDeactivated(WindowEvent e)
		{
			dialog.setVisible(false);
		}

		@Override
		public void windowClosed(WindowEvent e)
		{}
		@Override
		public void windowClosing(WindowEvent e)
		{}
		@Override
		public void windowOpened(WindowEvent e)
		{}
		@Override
		public void windowIconified(WindowEvent e)
		{}
		@Override
		public void windowDeiconified(WindowEvent e)
		{}
		@Override
		public void windowActivated(WindowEvent e)
		{}
	}
}
