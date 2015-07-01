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

import businesslogicservice.userblservice.UserBLService;
import presentation.courseui.YXInstitutionCoursePanel;
import presentation.courseui.YXModifyInfoPanel;
import presentation.courseui.YXPublishCoursePanel;
import presentation.planui.YXInputPlanPanel;
import presentation.statisticsui.XYStatisticsPanel;
import presentation.userui.LoginUI;
import JavaRMI.Client;
import JavaRMI.RMIFactory;

public class YXTeacherUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	RMIFactory factory = Client.getFactory();
	JTabbedPane jtp;
	JPanel panel;
	JLabel jl;
	JLabel jl2;
	JLabel jl3;
	JLabel jl4;
	
	public YXTeacherUI(String userName, final String institution,
			final String userID) {
		super("�Ͼ���ѧѡ��ϵͳ��ӭ��!");
		setBounds(260, 90, 830, 605);
		setResizable(false);// ������󻯲�����
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 810, 545);
		getContentPane().add(panel);

		Image i = null;// ����ͼ��
		try {
			i = ImageIO.read(new File("image/��ʦ.jpg"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setIconImage(i);

		jl = new JLabel("�Ͼ���ѧ���ѧԺ����");
		jl.setFont(new Font("������κ", Font.PLAIN, 30));
		jl.setBounds(50, 20, 800, 85);
		panel.add(jl);

		jl2 = new JLabel("�˳�");
		jl2.setFont(new Font("������κ", Font.PLAIN, 25));
		jl2.setForeground(Color.black);
		jl2.setBounds(675, 20, 800, 85);
		jl2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "ȷ���˳���", "ϵͳ��Ϣ",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					((Window) jl3.getTopLevelAncestor()).dispose();
					new LoginUI();
				}
			}

			public void mouseEntered(MouseEvent e) {
				jl2.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl2.setText("�� ��");
				jl2.setFont(new Font("������κ", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl2.setText(" �� ��");
				jl2.setFont(new Font("������κ", Font.PLAIN, 25));
				jl2.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		panel.add(jl2);

		ImageIcon exit = new ImageIcon("image/�˳�.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(635, 40, 40, 40);
		panel.add(jl3);

		jl3 = new JLabel("�޸�����");
		jl3.setFont(new Font("������κ", Font.PLAIN, 25));
		jl3.setForeground(Color.black);
		jl3.setBounds(510, 20, 800, 85);
		jl3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				final JDialog jb1jd = new JDialog((Frame) jl3
						.getTopLevelAncestor(), "�޸�����");
				jb1jd.setLayout(null);
				jb1jd.setBounds(480, 225, 410, 300);
				jb1jd.setVisible(true);

				JLabel jb1jl = new JLabel("������");
				jb1jl.setBounds(50, 50, 100, 30);
				jb1jl.setFont(new Font("����", Font.PLAIN, 20));
				jb1jd.add(jb1jl);

				final JPasswordField jpw1 = new JPasswordField();
				jpw1.setBounds(150, 50, 110, 30);
				jb1jd.add(jpw1);

				final JLabel jb1jl2 = new JLabel("�ٴ�ȷ��");
				jb1jl2.setBounds(40, 110, 100, 30);
				jb1jl2.setFont(new Font("����", Font.PLAIN, 20));
				jb1jd.add(jb1jl2);

				final JPasswordField jpw2 = new JPasswordField();
				jpw2.setBounds(150, 110, 110, 30);
				jb1jd.add(jpw2);

				JButton jb1jb = new JButton("ȷ��");
				jb1jb.setBounds(160, 170, 70, 30);
				jb1jb.setFont(new Font("����", Font.PLAIN, 20));
				jb1jd.add(jb1jb);

				final JLabel jdjl = new JLabel();
				jdjl.setHorizontalAlignment(SwingConstants.CENTER);
				jdjl.setBounds(30, 20, 70, 35);
				jdjl.setFont(new Font("����", Font.PLAIN, 18));
				jb1jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String pw1 = new String(jpw1.getPassword());
						String pw2 = new String(jpw2.getPassword());
						if (jpw1.getPassword().length == 0) {
							jdjl.setText("������������");
							JOptionPane.showMessageDialog(null, jdjl, "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						} else if (jpw2.getPassword().length == 0) {
							jdjl.setText("���ٴ�����ȷ��");
							JOptionPane.showMessageDialog(null, jdjl, "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						} else if (!pw1.equals(pw2)) {
							jdjl.setText("�����������벻ƥ��");
							JOptionPane.showMessageDialog(null, jdjl, "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							try {
								UserBLService uq = factory.getUQService();
								String newPassword = new String(jpw1
										.getPassword());
								uq.updatePassword(userID, newPassword);// bug
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
							jdjl.setText("�޸ĳɹ�");
							JOptionPane.showMessageDialog(null, jdjl, "ϵͳ��Ϣ",
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
				jl3.setText("�޸�����");
				jl3.setFont(new Font("������κ", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl3.setText(" �޸�����");
				jl3.setFont(new Font("������κ", Font.PLAIN, 25));
			}

		});

		panel.add(jl3);

		ImageIcon changePassword = new ImageIcon("image/�޸�����.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(465, 40, 40, 40);
		panel.add(jl4);

		final JLabel jdjl = new JLabel();// ������Ϣ���ר�ñ�ǩ
		jdjl.setHorizontalAlignment(SwingConstants.CENTER);
		jdjl.setBounds(30, 20, 70, 35);
		jdjl.setFont(new Font("����", Font.PLAIN, 18));

		jtp = new JTabbedPane();
		jtp.setFont(new Font("����", Font.PLAIN, 18));
		jtp.setBounds(100, 100, 580, 380);	
		
		JPanel inputPlanPanel=new YXInputPlanPanel(institution);
		JPanel publishCoursePanel=new YXPublishCoursePanel(institution);
		JPanel modifyInfo=new YXModifyInfoPanel(institution);
		JPanel institutionCourse=new YXInstitutionCoursePanel(institution);
		JPanel statistics=new XYStatisticsPanel();
		
		inputPlanPanel.setBounds(0, 0, 580, 360);
		publishCoursePanel.setBounds(0, 0, 580, 360);
		modifyInfo.setBounds(0, 0, 580, 360);
		institutionCourse.setBounds(0, 0, 580, 360);
		
		jtp.add("�����ѧ�ƻ�", inputPlanPanel);
		jtp.add("�����γ�", publishCoursePanel);
		jtp.add("�޸Ŀγ���Ϣ", modifyInfo);
		jtp.add("ȫԺ�γ�", institutionCourse);
		jtp.add("ͳ������",statistics);
		
		
		
		panel.add(jtp);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
