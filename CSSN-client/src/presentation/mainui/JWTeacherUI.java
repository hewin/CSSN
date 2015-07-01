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
		super("�Ͼ���ѧѡ��ϵͳ��ӭ��!");
		int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		int panelWidth=screenWidth/2;
		int panelHeight=screenHeight*2/3;
		setBounds(screenWidth/4, screenHeight/6, panelWidth, panelHeight);
		setUndecorated(true);
		setResizable(false);// ������󻯲�����
		setLayout(null);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,panelWidth,panelHeight);
		getContentPane().add(panel);

		Image i = null;// ����ͼ��
		try {
			i = ImageIO.read(new File("image/��ʦ.jpg"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setIconImage(i);

		jl = new JLabel("�Ͼ���ѧ����");
		jl.setFont(new Font("������κ", Font.PLAIN, 30));
		jl.setBounds(0,0, panelWidth/4,panelHeight/10 );
		panel.add(jl);

		jl2 = new JLabel(" �� ��");
		jl2.setFont(new Font("������κ", Font.PLAIN, 25));
		jl2.setBounds(675, 40, 65, 40);
		jl2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "ȷ���˳���", "ϵͳ��Ϣ",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
					((Window) jl2.getTopLevelAncestor()).dispose();
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

		jl4 = new JLabel("�޸�����");
		jl4.setFont(new Font("������κ", Font.PLAIN, 25));
		jl4.setBounds(510, 40, 120, 40);
		jl4.addMouseListener(new MouseAdapter() {

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
								RMIFactory factory= Client.getFactory();
								UserBLService uq = factory.getUQService();
								String newPassword = new String(jpw1
										.getPassword());
								uq.updatePassword(id, newPassword);
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
				jl4.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl4.setText("�޸�����");
				jl4.setFont(new Font("������κ", Font.PLAIN, 28));
			}

			public void mouseExited(MouseEvent e) {
				jl4.setText(" �޸�����");
				jl4.setFont(new Font("������κ", Font.PLAIN, 25));
			}

		});
		
		panel.add(jl4);
		
		ImageIcon changePassword = new ImageIcon("image/�޸�����.jpg");
		jl5 = new JLabel(changePassword);
		jl5.setBounds(465, 40, 40, 40);
		panel.add(jl5);

		jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		jtp.setFont(new Font("����", Font.PLAIN, 18));
		jtp.setBounds(0,panelHeight/8,panelWidth,panelHeight*7/8);//screenWidth/12, 200, screenWidth*(1/2-1/12), screenWidth*(2/3-2/3/8));
		//jtp.setTabLayoutPolicy(50);

		// ==============�����ܲ���======================
		JWFramePanel jp1 = new JWFramePanel(panelWidth, panelHeight*7/8);
		jp1.setBounds(0, panelHeight/8, panelWidth, panelHeight*7/8);
		//jp1.setBounds(screenWidth/12, screenHeight*2/3/8, screenWidth*(1/2-1/12), screenWidth*(2/3-2/3/8));
		//jp1.setBackground(Color.black);
		jtp.add("�����ܲ���", jp1);

		// ==============Ժϵ��ѧ�ƻ�======================
		JWPlanPanel jp2 = new JWPlanPanel();
		jp2.setBounds(0, 200, 200, 100);
		//jp2.setBounds(0, 0, 580, 360);
		jtp.add("Ժϵ��ѧ�ƻ�", jp2);

		// =============ͳ���������======================
		JWStatisticsPanel jp3 = new JWStatisticsPanel();
		jp3.setBounds(0, 200, 200, 100);
		jtp.add("ͳ������", jp3);

		// ============�����γ����==========================
		JWCoursePanel jp4 = new JWCoursePanel();
		jp4.setBounds(0, 200, 200, 100);
		jtp.add("�����γ�", jp4);

		// =============ѡ�ι������========================
		JWCourseSelectionPanel jp5 = new JWCourseSelectionPanel();
		jp5.setBounds(0, 200, 200, 100);
		jtp.add("ѡ�ι���", jp5);

		//add(jtp);
		jtp.setFont(new Font("����", Font.BOLD, 18));
		panel.add(jtp);
		setVisible(true);
	}

}
