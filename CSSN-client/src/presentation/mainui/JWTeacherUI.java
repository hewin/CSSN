package presentation.mainui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
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

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.userblservice.UserBLService;
import presentation.courseselectionui.JWCourseSelectionPanel;
import presentation.courseui.JWCoursePanel;
import presentation.frameui.JWFramePanel;
import presentation.planui.JWPlanPanel;
import presentation.statisticsui.JWStatisticsPanel;
import presentation.userui.LoginUI;

public class JWTeacherUI extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane jtp;
	JLabel jl, jl2, jl3,jl4,jl5;
	JPanel panel;
	
	public JWTeacherUI(String userName, final String id) {
		super("南京大学选课系统欢迎您!");
		int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		int panelWidth=screenWidth/2;
		int panelHeight=screenHeight*2/3;
		setBounds(screenWidth/4, screenHeight/6, panelWidth, panelHeight);
		setUndecorated(true);
		setResizable(false);// 设置最大化不可用
		setLayout(null);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,panelWidth,panelHeight);
		getContentPane().add(panel);

		Image i = null;// 更改图标
		try {
			i = ImageIO.read(new File("image/教师.jpg"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setIconImage(i);

		jl = new JLabel("南京大学教务处");
		jl.setFont(new Font("华文新魏", Font.PLAIN, 30));
		jl.setBounds(0,0, panelWidth/4,panelHeight/10 );
		panel.add(jl);

		jl2 = new JLabel(" 退 出");
		jl2.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl2.setBounds(675, 40, 65, 40);
		jl2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定退出？", "系统信息",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
					((Window) jl2.getTopLevelAncestor()).dispose();
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
		panel.add(jl2);

		ImageIcon exit = new ImageIcon("image/退出.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(635, 40, 40, 40);
		panel.add(jl3);

		jl4 = new JLabel("修改密码");
		jl4.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl4.setBounds(510, 40, 120, 40);
		jl4.addMouseListener(new MouseAdapter() {

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
								RMIFactory factory= Client.getFactory();
								UserBLService uq = factory.getUQService();
								String newPassword = new String(jpw1
										.getPassword());
								uq.updatePassword(id, newPassword);
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
				jl4.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl4.setText("修改密码");
				jl4.setFont(new Font("华文新魏", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl4.setText(" 修改密码");
				jl4.setFont(new Font("华文新魏", Font.PLAIN, 25));
			}

		});
		
		panel.add(jl4);
		
		ImageIcon changePassword = new ImageIcon("image/修改密码.jpg");
		jl5 = new JLabel(changePassword);
		jl5.setBounds(465, 40, 40, 40);
		panel.add(jl5);

		jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		jtp.setFont(new Font("楷体", Font.PLAIN, 18));
		jtp.setBounds(0,panelHeight/8,panelWidth,panelHeight*7/8);//screenWidth/12, 200, screenWidth*(1/2-1/12), screenWidth*(2/3-2/3/8));
		//jtp.setTabLayoutPolicy(50);

		// ==============整体框架策略======================
		JWFramePanel jp1 = new JWFramePanel(panelWidth, panelHeight*7/8);
		jp1.setBounds(0, panelHeight/8, panelWidth, panelHeight*7/8);
		//jp1.setBounds(screenWidth/12, screenHeight*2/3/8, screenWidth*(1/2-1/12), screenWidth*(2/3-2/3/8));
		//jp1.setBackground(Color.black);
		jtp.add("整体框架策略", jp1);

		// ==============院系教学计划======================
		JWPlanPanel jp2 = new JWPlanPanel();
		jp2.setBounds(0, 200, 200, 100);
		//jp2.setBounds(0, 0, 580, 360);
		jtp.add("院系教学计划", jp2);

		// =============统计数据面板======================
		JWStatisticsPanel jp3 = new JWStatisticsPanel();
		jp3.setBounds(0, 200, 200, 100);
		jtp.add("统计数据", jp3);

		// ============发布课程面板==========================
		JWCoursePanel jp4 = new JWCoursePanel();
		jp4.setBounds(0, 200, 200, 100);
		jtp.add("发布课程", jp4);

		// =============选课管理面板========================
		JWCourseSelectionPanel jp5 = new JWCourseSelectionPanel();
		jp5.setBounds(0, 200, 200, 100);
		jtp.add("选课管理", jp5);

		//add(jtp);
		jtp.setFont(new Font("楷体", Font.BOLD, 18));
		panel.add(jtp);
		setVisible(true);
	}

}
