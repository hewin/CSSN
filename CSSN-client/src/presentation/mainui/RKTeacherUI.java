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

import businesslogicservice.userblservice.UserBLService;
import presentation.courseselectionui.RKCheckStuPanel;
import presentation.courseui.RKFinishCourInfoPanel;
import presentation.scoreui.RKRecordScorePanel;
import presentation.userui.LoginUI;
import JavaRMI.Client;
import JavaRMI.RMIFactory;

public class RKTeacherUI extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane jtp;
	JLabel jl, jl2, jl3, jl4;
	JPanel jp, jp1, jp2, jp3;

	RMIFactory factory;

	/**
	 * 构造方法
	 * 
	 * @param userName
	 *            用户名
	 * @param id
	 *            工号
	 */
	public RKTeacherUI(String userName, final String id) {
		super("南京大学选课系统欢迎您!");
		setBounds(260, 90, 830, 605);
		setUndecorated(true);
		setResizable(false);// 设置最大化不可用
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭窗口停止程序运行
		factory = Client.getFactory();

		Toolkit kit=Toolkit.getDefaultToolkit();
		Image icon=kit.getImage("image/教师.jpg");// 更改图标
		setIconImage(icon);
		jp = new JPanel();// 这个是最底下的面板，所有组件加入之后再将jp添加到Frame
		jp.setBounds(0, 0, 810, 545);
		jp.setLayout(null);

		jl = new JLabel("欢迎" + userName + "老师");
		jl.setFont(new Font("华文新魏", Font.PLAIN, 30));
		jl.setBounds(50, 20, 800, 85);
		jp.add(jl);

		jl2 = new JLabel(" 退 出");
		jl2.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl2.setBounds(675, 40, 65, 40);
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
		jp.add(jl2);

		ImageIcon exit = new ImageIcon("image/退出.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(635, 40, 40, 40);
		jp.add(jl3);

		jl3 = new JLabel(" 修改密码");
		jl3.setFont(new Font("华文新魏", Font.PLAIN, 25));
		jl3.setBounds(510, 40, 120, 40);
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
				jl3.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl3.setText("修改密码");
				jl3.setFont(new Font("华文新魏", Font.PLAIN, 28));
				
			}

			public void mouseExited(MouseEvent e) {
				jl3.setText(" 修改密码");
				jl3.setFont(new Font("华文新魏", Font.PLAIN, 25));
				jl2.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		jp.add(jl3);

		ImageIcon changePassword = new ImageIcon("image/修改密码.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(465, 40, 40, 40);
		jp.add(jl4);

		jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.VERTICAL);// 去掉选项卡面板横线
		jtp.setFont(new Font("楷体", Font.PLAIN, 21));// 18
		jtp.setBounds(100, 100, 580, 400);

		jp1 = new RKFinishCourInfoPanel(factory,id).getpanel();
		jp2 = new RKCheckStuPanel(factory,id).getpanel();
		jp3 = new RKRecordScorePanel(factory,id).getpanel();
		
		jtp.add(" 我的学生列表 ", jp2);
		jtp.add(" 登记课程成绩", jp3);
		jtp.add(" 填写课程信息 ", jp1);
		
		jp.add(jtp);
		add(jp);
		
		setVisible(true);
	}

}
