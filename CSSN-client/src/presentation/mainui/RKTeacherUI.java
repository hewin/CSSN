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
	 * ���췽��
	 * 
	 * @param userName
	 *            �û���
	 * @param id
	 *            ����
	 */
	public RKTeacherUI(String userName, final String id) {
		super("�Ͼ���ѧѡ��ϵͳ��ӭ��!");
		setBounds(260, 90, 830, 605);
		setUndecorated(true);
		setResizable(false);// ������󻯲�����
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//�رմ���ֹͣ��������
		factory = Client.getFactory();

		Toolkit kit=Toolkit.getDefaultToolkit();
		Image icon=kit.getImage("image/��ʦ.jpg");// ����ͼ��
		setIconImage(icon);
		jp = new JPanel();// ���������µ���壬�����������֮���ٽ�jp��ӵ�Frame
		jp.setBounds(0, 0, 810, 545);
		jp.setLayout(null);

		jl = new JLabel("��ӭ" + userName + "��ʦ");
		jl.setFont(new Font("������κ", Font.PLAIN, 30));
		jl.setBounds(50, 20, 800, 85);
		jp.add(jl);

		jl2 = new JLabel(" �� ��");
		jl2.setFont(new Font("������κ", Font.PLAIN, 25));
		jl2.setBounds(675, 40, 65, 40);
		jl2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "ȷ���˳���", "ϵͳ��Ϣ",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
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
		jp.add(jl2);

		ImageIcon exit = new ImageIcon("image/�˳�.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(635, 40, 40, 40);
		jp.add(jl3);

		jl3 = new JLabel(" �޸�����");
		jl3.setFont(new Font("������κ", Font.PLAIN, 25));
		jl3.setBounds(510, 40, 120, 40);
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
				jl3.getTopLevelAncestor().setCursor(
						new Cursor(Cursor.HAND_CURSOR));
				jl3.setText("�޸�����");
				jl3.setFont(new Font("������κ", Font.PLAIN, 28));
				
			}

			public void mouseExited(MouseEvent e) {
				jl3.setText(" �޸�����");
				jl3.setFont(new Font("������κ", Font.PLAIN, 25));
				jl2.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		jp.add(jl3);

		ImageIcon changePassword = new ImageIcon("image/�޸�����.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(465, 40, 40, 40);
		jp.add(jl4);

		jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.VERTICAL);// ȥ��ѡ�������
		jtp.setFont(new Font("����", Font.PLAIN, 21));// 18
		jtp.setBounds(100, 100, 580, 400);

		jp1 = new RKFinishCourInfoPanel(factory,id).getpanel();
		jp2 = new RKCheckStuPanel(factory,id).getpanel();
		jp3 = new RKRecordScorePanel(factory,id).getpanel();
		
		jtp.add(" �ҵ�ѧ���б� ", jp2);
		jtp.add(" �Ǽǿγ̳ɼ�", jp3);
		jtp.add(" ��д�γ���Ϣ ", jp1);
		
		jp.add(jtp);
		add(jp);
		
		setVisible(true);
	}

}
