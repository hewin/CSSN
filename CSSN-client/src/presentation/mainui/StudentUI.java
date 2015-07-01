package presentation.mainui;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import presentation.courseselectionui.StuChooseCoursePanel;
import presentation.courseselectionui.StuMyCoursePanel;
import presentation.courseselectionui.StuQuitCoursePanel;
import presentation.courseui.StuCourseInfoPanel;
import presentation.scoreui.StuScorePanel;
import presentation.statisticsui.StuStatisticsPanel;
import presentation.userui.LoginUI;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.userblservice.UserBLService;

public class StudentUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTabbedPane jtp;
	JLabel jl2;
	JLabel jl3;
	JLabel jl4;
	JPanel myCoursePanel;
	JPanel courseInfoPanel;
	JPanel chooseCoursePanel;
	JPanel quitCoursePanel;
	JPanel scorePanel;
	JPanel statisticsPanel;
	
	public StudentUI(String stuName,final String stuNO) {
		super("南京大学选课系统欢迎您!");
		setBounds(260, 90,830, 605);
		setResizable(false);// 设置最大化不可用
		setUndecorated(true);
		setLayout(null);
		
		JPanel stuPanel = new JPanel();
		stuPanel.setBounds(0, 0, 810, 545);
	
		stuPanel.setLayout(null);

		Toolkit kit=Toolkit.getDefaultToolkit();
		Image icon=kit.getImage("image/student.png");
		setIconImage(icon);
		
		JLabel jl = new JLabel("欢迎" + stuName + "同学！");
		jl.setFont(new Font("华文新魏", Font.PLAIN, 30));
		jl.setBounds(80, 20, 800, 85);
		stuPanel.add(jl);
		
		jl2 = new JLabel(" 退 出");
		jl2.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl2.setBounds(705, 40, 65, 40);
		jl2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定退出？", "系统信息",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
					((Window) jl3.getTopLevelAncestor()).dispose();
					new LoginUI();
				}
			}

			public void mouseEntered(MouseEvent e) {
				jl2.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl2.setText("退 出");
				jl2.setFont(new Font("华文新魏", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl2.setText(" 退 出");
				jl2.setFont(new Font("华文新魏", Font.PLAIN, 25));
				jl2.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		stuPanel.add(jl2);

		ImageIcon exit = new ImageIcon("image/退出.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(665, 40, 40, 40);//20
		add(jl3);

		jl3 = new JLabel(" 修改密码");
		jl3.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl3.setBounds(540, 40, 120, 40);
		jl3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				final JDialog jb1jd = new JDialog((Frame) jl3
						.getTopLevelAncestor(), "修改密码");
				jb1jd.setLayout(null);
				jb1jd.setBounds(480, 225, 410, 300);
				jb1jd.setVisible(true);

				JLabel jb1jl = new JLabel("新密码");
				jb1jl.setBounds(50, 50, 100, 30);
				jb1jl.setFont(new Font("楷体", Font.PLAIN, 20));
				jb1jd.add(jb1jl);

				final JPasswordField jpw1 = new JPasswordField();
				jpw1.setBounds(150, 50, 110, 30);
				jb1jd.add(jpw1);

				final JLabel jb1jl2 = new JLabel("再次确认");
				jb1jl2.setBounds(40, 110, 100, 30);
				jb1jl2.setFont(new Font("楷体", Font.PLAIN, 20));
				jb1jd.add(jb1jl2);

				final JPasswordField jpw2 = new JPasswordField();
				jpw2.setBounds(150, 110, 110, 30);
				jb1jd.add(jpw2);

				JButton jb1jb = new JButton("确定");
				jb1jb.setBounds(160, 170, 70, 30);
				jb1jb.setFont(new Font("楷体", Font.PLAIN, 20));
				jb1jd.add(jb1jb);

				final JLabel jdjl = new JLabel();
				jdjl.setHorizontalAlignment(SwingConstants.CENTER);
				jdjl.setBounds(30, 20, 70, 35);
				jdjl.setFont(new Font("楷体", Font.PLAIN, 18));
				jb1jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String pw1 = new String(jpw1.getPassword());
						String pw2 = new String(jpw2.getPassword());
						if (jpw1.getPassword().length == 0) {
							jdjl.setText("请输入新密码");
							JOptionPane.showMessageDialog(null, jdjl, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
						} else if (jpw2.getPassword().length == 0) {
							jdjl.setText("请再次输入确认");
							JOptionPane.showMessageDialog(null, jdjl, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
						} else if (!pw1.equals(pw2)) {
							jdjl.setText("两次输入密码不匹配");
							JOptionPane.showMessageDialog(null, jdjl, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							try {
								RMIFactory factory=Client.getFactory();
								UserBLService uq = factory.getUQService();
								String newPassword = new String(jpw1
										.getPassword());
								uq.updatePassword(stuNO, newPassword);
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
							jdjl.setText("修改成功");
							JOptionPane.showMessageDialog(null, jdjl, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
							jb1jd.setVisible(false);
						}
					}
				});

				jb1jd.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				jl3.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl3.setText("修改密码");
				jl3.setFont(new Font("华文新魏", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl3.setText(" 修改密码");
				jl3.setFont(new Font("华文新魏", Font.PLAIN, 25));
				jl3.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		stuPanel.add(jl3);
		
		ImageIcon changePassword = new ImageIcon("image/修改密码.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(495, 40, 40, 40);
		stuPanel.add(jl4);
		
		jtp=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.VERTICAL);
		jtp.setFont(new Font("楷体", Font.PLAIN, 18));
		jtp.setBounds(125, 100, 580, 450);
		
		myCoursePanel = new StuMyCoursePanel(stuNO);
		courseInfoPanel = new StuCourseInfoPanel();
		chooseCoursePanel = new StuChooseCoursePanel(stuNO);
		quitCoursePanel = new StuQuitCoursePanel(stuNO);
		scorePanel = new StuScorePanel(stuNO);
		statisticsPanel = new StuStatisticsPanel(stuNO);
	
		jtp.add("我的课程",myCoursePanel);
		jtp.add("课程信息",courseInfoPanel);
		jtp.add("选择课程",chooseCoursePanel);
		jtp.add("退选课程",quitCoursePanel);
		jtp.add("我的成绩",scorePanel);
		jtp.add("课程统计",statisticsPanel);
		
		jtp.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				int index = jtp.getSelectedIndex();
				
				if(index == 0){
					myCoursePanel = new StuMyCoursePanel(stuNO);
					jtp.setComponentAt(0, myCoursePanel);
				}else if(index == 1){
					courseInfoPanel = new StuCourseInfoPanel();
					jtp.setComponentAt(1, courseInfoPanel);
				}else if(index == 2){
					chooseCoursePanel = new StuChooseCoursePanel(stuNO);
					jtp.setComponentAt(2, chooseCoursePanel);
				}else if(index == 3){
					quitCoursePanel = new StuQuitCoursePanel(stuNO);
					jtp.setComponentAt(3, quitCoursePanel);
				}else if(index == 4){
					scorePanel = new StuScorePanel(stuNO);
					jtp.setComponentAt(4, scorePanel);
				}else if(index == 5){
					statisticsPanel = new StuStatisticsPanel(stuNO);
					jtp.setComponentAt(5, statisticsPanel);
				}
				
			}

		});
		
		stuPanel.add(jtp);
	
		add(stuPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
