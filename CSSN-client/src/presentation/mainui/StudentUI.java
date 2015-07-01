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
		super("�Ͼ���ѧѡ��ϵͳ��ӭ��!");
		setBounds(260, 90,830, 605);
		setResizable(false);// ������󻯲�����
		setUndecorated(true);
		setLayout(null);
		
		JPanel stuPanel = new JPanel();
		stuPanel.setBounds(0, 0, 810, 545);
	
		stuPanel.setLayout(null);

		Toolkit kit=Toolkit.getDefaultToolkit();
		Image icon=kit.getImage("image/student.png");
		setIconImage(icon);
		
		JLabel jl = new JLabel("��ӭ" + stuName + "ͬѧ��");
		jl.setFont(new Font("������κ", Font.PLAIN, 30));
		jl.setBounds(80, 20, 800, 85);
		stuPanel.add(jl);
		
		jl2 = new JLabel(" �� ��");
		jl2.setFont(new Font("������κ", Font.PLAIN, 25));
		jl2.setBounds(705, 40, 65, 40);
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
		stuPanel.add(jl2);

		ImageIcon exit = new ImageIcon("image/�˳�.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(665, 40, 40, 40);//20
		add(jl3);

		jl3 = new JLabel(" �޸�����");
		jl3.setFont(new Font("������κ", Font.PLAIN, 25));
		jl3.setBounds(540, 40, 120, 40);
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
								RMIFactory factory=Client.getFactory();
								UserBLService uq = factory.getUQService();
								String newPassword = new String(jpw1
										.getPassword());
								uq.updatePassword(stuNO, newPassword);
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
				jl3.getTopLevelAncestor().setCursor(Cursor.getDefaultCursor());
			}

		});
		stuPanel.add(jl3);
		
		ImageIcon changePassword = new ImageIcon("image/�޸�����.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(495, 40, 40, 40);
		stuPanel.add(jl4);
		
		jtp=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.VERTICAL);
		jtp.setFont(new Font("����", Font.PLAIN, 18));
		jtp.setBounds(125, 100, 580, 450);
		
		myCoursePanel = new StuMyCoursePanel(stuNO);
		courseInfoPanel = new StuCourseInfoPanel();
		chooseCoursePanel = new StuChooseCoursePanel(stuNO);
		quitCoursePanel = new StuQuitCoursePanel(stuNO);
		scorePanel = new StuScorePanel(stuNO);
		statisticsPanel = new StuStatisticsPanel(stuNO);
	
		jtp.add("�ҵĿγ�",myCoursePanel);
		jtp.add("�γ���Ϣ",courseInfoPanel);
		jtp.add("ѡ��γ�",chooseCoursePanel);
		jtp.add("��ѡ�γ�",quitCoursePanel);
		jtp.add("�ҵĳɼ�",scorePanel);
		jtp.add("�γ�ͳ��",statisticsPanel);
		
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
