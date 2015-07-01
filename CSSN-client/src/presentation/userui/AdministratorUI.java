package presentation.userui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;









import vo.user.Identity;
import vo.user.UserVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import Util.CreateSimpleExcelToDisk;
import businesslogicservice.userblservice.UserBLService;

public class AdministratorUI extends JFrame {

	RMIFactory factory;
	UserBLService uqservice;
	JTabbedPane jtp;
	JLabel jl, jl2, jl3, jl4;
	JPanel jp, jp1, jp2, jp3, jp4;
	ArrayList<UserVO> userList;
	DefaultTableModel jp1dft1, jp3dft, jp4dft;
	String[][] jsp1list1;
	String[] jp1title1 = { "���", "���", "�û���", "����", "Ժϵ", "�꼶" };// �����û���null��;
	String ID;
	JComboBox<String> jdjc1,jdjc2,jdjc3;
	int selectedRow;
	JTable jp1jt, jp3jt, jp4jt;
	JTextField jdjt1;
	JScrollPane jp4jsp;
	JDialog jp4jd;
	String userType;
	String[] mytitle={ "���", "���", "�û���", "����", "Ժϵ", "�꼶" };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministratorUI(String name,final String idNum) {
		
		super("�Ͼ���ѧѡ��ϵͳ��ӭ��!");
	
		factory = Client.getFactory();
		 try {
			uqservice= factory.getUQService();
		} catch (RemoteException e3) {
			e3.printStackTrace();
		}
		setBounds(260, 90, 830, 605);
		setUndecorated(true);
		setResizable(false);// ������󻯲�����
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//�رմ���ֹͣ��������
		Toolkit kit=Toolkit.getDefaultToolkit();
		Image icon=kit.getImage("image/����Ա.jpg");// ����ͼ��
		setIconImage(icon);
		jp = new JPanel();
		jp.setBounds(0, 0, 810, 545);
		jp.setLayout(null);

		jl = new JLabel("��ӭ"+name+"����Ա");
		jl.setFont(new Font("������κ", Font.PLAIN, 30));
		jl.setBounds(50, 20, 800, 85);
		jp.add(jl);

		jl2 = new JLabel("�� ��");
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
			}

		});
		jp.add(jl2);

		ImageIcon exit = new ImageIcon("image/�˳�.jpg");
		jl3 = new JLabel(exit);
		jl3.setBounds(635, 40, 40, 40);
		jp.add(jl3);

		jl3 = new JLabel("�޸�����");
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
								uq.updatePassword(idNum, newPassword);
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
		jp.add(jl3);

		ImageIcon changePassword = new ImageIcon("image/�޸�����.jpg");
		jl4 = new JLabel(changePassword);
		jl4.setBounds(465, 40, 40, 40);
		jp.add(jl4);

		jtp = new JTabbedPane();
		jtp.setFont(new Font("����", Font.BOLD, 21));
		jtp.setBounds(100, 100, 570, 400);

		
		jp1 = new JPanel();
		jp1.setBounds(0, 0, 580, 360);
		jp1.setLayout(null);

		JLabel jp1jl1 = new JLabel("�û�����:");
		jp1jl1.setFont(new Font("����", Font.BOLD, 20));
		jp1jl1.setBounds(0, 300, 170, 30);

		jp1.add(jp1jl1);

		final String[] id = { "ȫ���û�", "������ʦ", "Ժϵ������ʦ", "�ον�ʦ", "ѧ��" };
		userType="ȫ���û�";
		final JComboBox<String> jp1jc = new JComboBox<String>(id);
		jp1jc.setBounds(100, 300, 150, 30);
		jp1jc.setFont(new Font("����", Font.PLAIN, 20));
		jp1.add(jp1jc);

		try {
			userList = uqservice.getUserList();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		setdft();

		jp1dft1 = new DefaultTableModel(jsp1list1, jp1title1);
		jp1jt = new JTable(jp1dft1) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		jp1jt.setFont(new Font("����", Font.BOLD, 15));
		jp1jt.getTableHeader().setFont(new Font("����", Font.BOLD, 17));

		JScrollPane jsp1 = new JScrollPane(jp1jt);
		jsp1.setBounds(0, 0, 555, 271);
		jp1.add(jsp1);

		JButton jp1jb1 = new JButton("�鿴");
		jp1jb1.setFont(new Font("����", Font.PLAIN, 20));
		jp1jb1.setBounds(270, 300, 70, 30);
		jp1jb1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int r = jp1jc.getSelectedIndex();
				userType=id[r];
				int num = userList.size();
				switch (r - 1) {
				case -1:
					jp1jt.setModel(jp1dft1);
					jp1jt.repaint();
					break;
				case 0:// ȥ����������ʦ֮��������
					setdft();
					jp1jt.setModel(jp1dft1);
					for (int i = num - 1; i >= 0; i--) {
						if (!((String) jp1dft1.getValueAt(i, 1))
								.equals("JWTeacher")) {
							jp1dft1.removeRow(i);
						}
					}
					removeClm(5);
					removeClm(4);
					jp1jt.setModel(jp1dft1);
					setNum(jp1dft1);
					jp1jt.repaint();
					break;
				case 1:
					setdft();
					jp1jt.setModel(jp1dft1);
					for (int i = num - 1; i >= 0; i--) {
						if (!(jp1dft1.getValueAt(i, 1).equals("YXTeacher"))) {
							jp1dft1.removeRow(i);
						}
					}
					removeClm(5);
					jp1jt.setModel(jp1dft1);
					setNum(jp1dft1);
					jp1jt.repaint();
					break;
				case 2:
					setdft();
					jp1jt.setModel(jp1dft1);
					for (int i = num - 1; i >= 0; i--) {
						if (!(jp1dft1.getValueAt(i, 1).equals("RKTeacher"))) {
							jp1dft1.removeRow(i);
						}
					}
					removeClm(5);
					removeClm(4);
					jp1jt.setModel(jp1dft1);
					setNum(jp1dft1);
					jp1jt.repaint();
					break;
				case 3:
					setdft();
					jp1jt.setModel(jp1dft1);
					for (int i = num - 1; i >= 0; i--) {
						if (!(jp1dft1.getValueAt(i, 1).equals("Student"))) {
							jp1dft1.removeRow(i);
						}
					}
					jp1jt.setModel(jp1dft1);
					setNum(jp1dft1);
					jp1jt.repaint();
					break;
				}

			}

		});
		
		
		JButton export = new JButton("�����û�");
		export.setFont(new Font("����", Font.PLAIN, 20));
		export.setBounds(430, 300, 120, 30);
		export.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					CreateSimpleExcelToDisk exportExcel=new CreateSimpleExcelToDisk(userType,jp1title1,jsp1list1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jp1.add(export);
		jp1.add(jp1jb1);

		jtp.add(jp1, "�鿴�û�");

		
		jp2 = new JPanel();
		jp2.setLayout(null);

		JLabel jp2jl1 = new JLabel("���");
		jp2jl1.setFont(new Font("����", Font.PLAIN, 22));
		jp2jl1.setBounds(50, 40, 60, 30);
		jp2.add(jp2jl1);

		String[] jp2id = { "������ʦ", "Ժϵ������ʦ", "�ον�ʦ", "ѧ��" };
		final JComboBox<String> jp2jc = new JComboBox<String>(jp2id);
		jp2jc.setBounds(120, 40, 130, 30);
		jp2jc.setFont(new Font("����", Font.PLAIN, 16));
		jp2.add(jp2jc);

		JLabel jp2jl2 = new JLabel("�˺�");
		jp2jl2.setFont(new Font("����", Font.PLAIN, 22));
		jp2jl2.setBounds(310, 40, 60, 30);
		jp2.add(jp2jl2);

		final JTextField jp2jt1 = new JTextField();
		jp2jt1.setFont(new Font("����", Font.PLAIN, 20));
		jp2jt1.setBounds(380, 40, 120, 30);
		jp2.add(jp2jt1);

		JLabel jp2jl3 = new JLabel("����");
		jp2jl3.setFont(new Font("����", Font.PLAIN, 22));
		jp2jl3.setBounds(50, 130, 60, 30);
		jp2.add(jp2jl3);

		final JTextField jp2jt2 = new JTextField();
		jp2jt2.setFont(new Font("����", Font.PLAIN, 20));
		jp2jt2.setBounds(120, 130, 120, 30);
		jp2.add(jp2jt2);

		JLabel jp2jl4 = new JLabel("����");
		jp2jl4.setFont(new Font("����", Font.PLAIN, 22));
		jp2jl4.setBounds(310, 130, 120, 30);
		jp2.add(jp2jl4);

		final JTextField jp2jt3 = new JTextField();
		jp2jt3.setFont(new Font("����", Font.PLAIN, 20));
		jp2jt3.setBounds(380, 130, 120, 30);
		jp2.add(jp2jt3);

		String[] jp2id2 = { "���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
				"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
				"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
				"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "������ѧѧԺ", "��ѧϵ","��ʷѧԺ","null" };
		final JComboBox<String> jp2jc2 = new JComboBox<String>(jp2id2);
		jp2jc2.setBounds(120, 230, 120, 30);
		jp2jc2.setFont(new Font("����", Font.PLAIN, 16));
		jp2.add(jp2jc2);

		JLabel jp2jl5 = new JLabel("Ժϵ");
		jp2jl5.setFont(new Font("����", Font.PLAIN, 22));
		jp2jl5.setBounds(50, 230, 120, 30);
		jp2.add(jp2jl5);

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		String[] jp2id3 = { year - 4 + "", year - 3 + "", year - 2 + "",
				year - 1 + "", year + "", "null" };
		final JComboBox<String> jp2jc3 = new JComboBox<String>(jp2id3);
		jp2jc3.setBounds(380, 230, 120, 30);
		jp2jc3.setFont(new Font("����", Font.PLAIN, 18));
		jp2.add(jp2jc3);

		JLabel jp2jl6 = new JLabel("�꼶");
		jp2jl6.setFont(new Font("����", Font.PLAIN, 20));
		jp2jl6.setBounds(310, 230, 120, 30);
		jp2.add(jp2jl6);

		JButton jp2jb = new JButton("���");
		jp2jb.setFont(new Font("����", Font.PLAIN, 20));
		jp2jb.setBounds(140, 290, 100, 30);
		jp2jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (jp2jt1.getText().equals("") || jp2jt2.getText().equals("")
						|| jp2jt3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�û���Ϣ��δ��д����", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try {
						String idNum = jp2jt1.getText();
						String passWord = jp2jt2.getText();
						String name = jp2jt3.getText();
						Identity identity = null;
						String grade = (String) jp2jc3.getSelectedItem();
						String institute = (String) jp2jc2
								.getSelectedItem();
						UserVO vo = null;
						switch (jp2jc.getSelectedIndex()) {
						case 0:
							identity = Identity.JWTeacher;
							vo = new UserVO(idNum, name, passWord,
									identity, "null", "null");
							break;
						case 1:
							identity = Identity.YXTeacher;
							vo = new UserVO(idNum, name, passWord,
									identity, institute, "null");
							break;
						case 2:
							identity = Identity.RKTeacher;
							vo = new UserVO(idNum, name, passWord,
									identity, "null", "null");
							break;
						case 3:
							identity = Identity.Student;
							vo = new UserVO(idNum, name, passWord,
									identity, institute, grade);
							break;
						}
						if(uqservice.addUser(vo)){
							userList.add(vo);
							setdft();
							jp1jt.setModel(jp1dft1);
							jp1jt.repaint();
							String[] newRow={userList.size()+"",identity.toString(),idNum,name,institute,grade};
							jp3dft.addRow(newRow);
							jp4dft.addRow(newRow);
							JOptionPane.showMessageDialog(null, "��ӳɹ�", "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "���ʧ��,�˺��Ѵ���", "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "���������쳣�����ʧ��", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		});
		
		JButton addBatch = new JButton("�������");
		addBatch.setFont(new Font("����", Font.PLAIN, 20));
		addBatch.setBounds(300, 290, 100, 30);
		addBatch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
			}
		});
		jp2.add(addBatch);
		jp2.add(jp2jb);

		jtp.add(jp2, "����û�");
		
		
		
		jp3 = new JPanel();
		jp3.setLayout(null);

		String[][] jsp3list = setdft();
		jp3dft = new DefaultTableModel(jsp3list, jp1title1);
		jp3jt = new JTable(jp3dft) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		jp3jt.setFont(new Font("����", Font.BOLD, 15));
		jp3jt.getTableHeader().setFont(new Font("����", Font.BOLD, 17));
		
		jp3jt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
		        if (e.getButton() == 3) {
		        	JPopupMenu popmenu = new JPopupMenu();
		        	JMenuItem item = new JMenuItem("ɾ �� ");
		        	item.setFont(new Font("����", Font.BOLD, 15));
		        	item.addActionListener(new ActionListener(){

		        		public void actionPerformed(ActionEvent e) {
		        			deleteUser();
		        		}

		        	});
		        	popmenu.add(item);
		        	if(jp3jt.getSelectedRow()!=-1){
		        		popmenu.show(jp3jt, e.getX(), e.getY());
		        	}
		        }

		    }
		});
		
		jp3jt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_DELETE){
					deleteUser();
				}
			}
		});

		JLabel jp3jl = new JLabel("���б���ѡ��Ҫɾ���ļ�¼�Ҽ�����");
		jp3jl.setBounds(60, 300, 330, 30);
		jp3jl.setFont(new Font("����", Font.BOLD, 17));
		jp3.add(jp3jl);

		JScrollPane jp3jsp = new JScrollPane(jp3jt);
		jp3jsp.setBounds(0, 0, 555, 271);
		jp3.add(jp3jsp);

		final JButton jp3jb = new JButton("ɾ��");
		jp3jb.setFont(new Font("����", Font.BOLD, 17));
		jp3jb.setBounds(380, 300, 80, 30);
		jp3jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}

		});
		jp3.add(jp3jb);

		jtp.add(jp3, "ɾ���û�");

		
		jp4 = new JPanel();
		jp4.setLayout(null);

		jtp.add(jp4, "�޸��û���Ϣ");
		jp.add(jtp);

		jp4jd=new JDialog(this,"�޸��û���Ϣ");
		jp4jd.setBounds(460, 225, 430, 300);
		jp4jd.setLayout(null);
		
		JLabel jdjl1=new JLabel("���");
		jdjl1.setBounds(20,40,60,30);
		jdjl1.setFont(new Font("����",Font.BOLD,16));
		jp4jd.add(jdjl1);
		
		String [] jdjcstr={"������ʦ", "Ժϵ������ʦ", "�ον�ʦ", "ѧ��","����Ա" };
		jdjc1=new JComboBox<String>(jdjcstr);
		jdjc1.setBounds(70,40,120,30);
		jp4jd.add(jdjc1);
		
		JLabel jdjl2=new JLabel("����");
		jdjl2.setBounds(210,40,60,30);
		jdjl2.setFont(new Font("����",Font.BOLD,16));
		jp4jd.add(jdjl2);
		
		jdjt1=new JTextField();
		jdjt1.setBounds(260,40,100,30);
		jdjt1.setFont(new Font("����",Font.BOLD,16));
		jp4jd.add(jdjt1);
		
		JLabel jdjl3=new JLabel("Ժϵ");
		jdjl3.setBounds(20,100,60,30);
		jdjl3.setFont(new Font("����",Font.BOLD,16));
		jp4jd.add(jdjl3);
		
		String [] jdjcstr2={ "���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
				"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
				"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
				"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "������ѧѧԺ", "��ѧϵ","��ʷѧԺ","null" };
		jdjc2=new JComboBox<String>(jdjcstr2);
		jdjc2.setBounds(70,100,120,30);
		jp4jd.add(jdjc2);
		
		JLabel jdjl4=new JLabel("�꼶");
		jdjl4.setBounds(210,100,60,30);
		jdjl4.setFont(new Font("����",Font.BOLD,16));
		jp4jd.add(jdjl4);
		
		jdjc3=new JComboBox<String>(jp2id3);
		jdjc3.setBounds(260,100,100,30);
		jp4jd.add(jdjc3);
		
		JButton jdjb=new JButton("����޸�");
		jdjb.setBounds(170,155,90,30);
		jdjb.setFont(new Font("����",Font.BOLD,16));
		jdjb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				modifyUserInfo();
			}
		});
		jp4jd.add(jdjb);
		
		String[][] jsp4list = setdft();
		jp4dft = new DefaultTableModel(jsp4list, jp1title1);
	
		jp4jt = new JTable(jp4dft) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		// ͬʱֻ����һ�б�ѡ��
		jp4jt.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		jp4jt.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					chooseModify();
				}
			}
		});
		
		jp4jt.setFont(new Font("����", Font.BOLD, 15));
		jp4jt.getTableHeader().setFont(new Font("����", Font.BOLD, 17));

		jp4jsp = new JScrollPane(jp4jt);
		jp4jsp.setBounds(0, 0, 555, 271);
		jp4.add(jp4jsp);

		JLabel jp4jl = new JLabel("˫���û����޸Ļ���");
		jp4jl.setBounds(130, 300, 220, 30);
		jp4jl.setFont(new Font("����", Font.BOLD, 17));
		jp4.add(jp4jl);

		JButton jp4jb = new JButton("�޸�");
		jp4jb.setFont(new Font("����", Font.BOLD, 17));
		jp4jb.setBounds(320, 300, 80, 30);
		jp4jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooseModify();
			}
		});
		jp4.add(jp4jb);

		this.add(jp);
		this.setVisible(true);
	}

	public String[][] setdft() {
		String[][] jsplist = new String[userList.size()][6];
		jsp1list1 = new String[userList.size()][6];
		for (int i1 = 0; i1 < userList.size(); i1++) {
			jsp1list1[i1][0] = i1 + 1 + "";
			jsp1list1[i1][1] = userList.get(i1).getIdentity().toString();
			jsp1list1[i1][2] = userList.get(i1).getIdNum();
			jsp1list1[i1][3] = userList.get(i1).getUserName();
			jsp1list1[i1][4] = userList.get(i1).getInstitute();
			jsp1list1[i1][5] = userList.get(i1).getGrade();
			jsplist[i1][0] = i1 + 1 + "";
			jsplist[i1][1] = userList.get(i1).getIdentity().toString();
			jsplist[i1][2] = userList.get(i1).getIdNum();
			jsplist[i1][3] = userList.get(i1).getUserName();
			jsplist[i1][4] = userList.get(i1).getInstitute();
			jsplist[i1][5] = userList.get(i1).getGrade();
		}
		jp1dft1 = new DefaultTableModel(jsp1list1, jp1title1);
		return jsplist;
	}

	public void removeClm(int c) {
		TableColumnModel columnModel = jp1jt.getColumnModel();
		TableColumn tableColumn = columnModel.getColumn(c);
		columnModel.removeColumn(tableColumn);
		int c2 = jp1dft1.getColumnCount();
		jp1dft1.setColumnCount(c2 - 1);
	}

	public void setNum(DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i + 1 + "", i, 0);
		}
	}

	public void modifyUserInfo(){
		if(jdjt1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "��������Ϊ��", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			Identity identity = null;
			UserVO vo=null;
			switch( jdjc1.getSelectedIndex()){
			case 0://������ʦ
				identity = Identity.JWTeacher;
				vo=new UserVO(ID,jdjt1.getText(),null,identity);
				jp4jt.setValueAt("JWTeacher", selectedRow, 1);
				jp4jt.setValueAt("null", selectedRow, 4);
				jp4jt.setValueAt("null", selectedRow, 5);
				jp3jt.setValueAt("JWTeacher", selectedRow, 1);
				jp3jt.setValueAt("null", selectedRow, 4);
				jp3jt.setValueAt("null", selectedRow, 5);
				userList.get(selectedRow).setInstitute("null");
				userList.get(selectedRow).setGrade("null");
				break;
			case 1:
				identity = Identity.YXTeacher;
				vo=new UserVO(ID,jdjt1.getText(),null,identity,(String) jdjc2.getSelectedItem());
				jp4jt.setValueAt("YXTeacher", selectedRow, 1);
				jp4jt.setValueAt((String) jdjc2.getSelectedItem(), selectedRow, 4);
				jp4jt.setValueAt("null", selectedRow, 5);
				jp3jt.setValueAt("YXTeacher", selectedRow, 1);
				jp3jt.setValueAt((String) jdjc2.getSelectedItem(), selectedRow, 4);
				jp3jt.setValueAt("null", selectedRow, 5);
				userList.get(selectedRow).setInstitute((String) jdjc3.getSelectedItem());
				userList.get(selectedRow).setGrade(null);
				break;
			case 2:
				identity = Identity.RKTeacher;
				vo=new UserVO(ID,jdjt1.getText(),null,identity);
				jp4jt.setValueAt("RKTeacher", selectedRow, 1);
				jp4jt.setValueAt("null", selectedRow, 4);
				jp4jt.setValueAt("null", selectedRow, 5);
				jp3jt.setValueAt("RKTeacher", selectedRow, 1);
				jp3jt.setValueAt("null", selectedRow, 4);
				jp3jt.setValueAt("null", selectedRow, 5);
				userList.get(selectedRow).setInstitute("null");
				userList.get(selectedRow).setGrade("null");
				break;
			case 3:
				identity = Identity.Student;
				vo=new UserVO(ID,jdjt1.getText(),null,identity,(String) jdjc2.getSelectedItem(),(String) jdjc3.getSelectedItem());
				jp4jt.setValueAt("Student", selectedRow, 1);
				jp4jt.setValueAt((String) jdjc2.getSelectedItem(), selectedRow, 4);
				jp4jt.setValueAt((String) jdjc3.getSelectedItem(), selectedRow, 5);
				jp3jt.setValueAt("Student", selectedRow, 1);
				jp3jt.setValueAt((String) jdjc2.getSelectedItem(), selectedRow, 4);
				jp3jt.setValueAt((String) jdjc3.getSelectedItem(), selectedRow, 5);
				userList.get(selectedRow).setIdentity(identity);
				userList.get(selectedRow).setUserName(jdjt1.getText());
				userList.get(selectedRow).setInstitute((String) jdjc3.getSelectedItem());
				userList.get(selectedRow).setGrade((String) jdjc3.getSelectedItem());
				break;
			}
			try {
				uqservice.modifyUserInfo(vo);
				jp4jt.setValueAt(jdjt1.getText(), selectedRow, 3);
				jp3jt.setValueAt(jdjt1.getText(), selectedRow, 3);
				userList.get(selectedRow).setIdentity(identity);
				userList.get(selectedRow).setUserName(jdjt1.getText());
				setdft();
				jp1jt.setModel(jp1dft1);
				jp1.repaint();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				jp4jd.setVisible(false);
			} catch (RemoteException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "���������쳣���޸�ʧ��", "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void chooseModify(){
		if(jp4jt.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(null, "��δѡ��Ҫ�޸ĵļ�¼", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			ID=(String) jp4jt.getValueAt(jp4jt.getSelectedRow(),2);
			selectedRow=jp4jt.getSelectedRow();
			//����δ�string�õ�����ö��������ʱ���ڵ�λ��
			jdjc1.setSelectedIndex(Identity.valueOf((String) jp4jt.getValueAt(selectedRow,1)).ordinal());
			jdjc2.setSelectedItem((String) jp4jt.getValueAt(selectedRow,4));
			jdjc3.setSelectedItem((String) jp4jt.getValueAt(selectedRow,5));
			jdjt1.setText((String) jp4jt.getValueAt(selectedRow,3));
			jp4jd.setVisible(true);
		}
	}
	
	public void deleteUser(){
		int[] r = jp3jt.getSelectedRows();
		if (r.length == 0) {
			JOptionPane.showMessageDialog(null,"δѡ���κμ�¼", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ����(��ctrl����ѡ����ͬʱɾ��)", "ϵͳ��Ϣ",
					JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
				for (int i = r.length - 1; i >= 0; i--) {
					try {
						uqservice.deleteUser((String) jp3dft.getValueAt(r[i], 2));
						jp3dft.removeRow(r[i]);
						userList.remove(r[i]);
						setdft();
						jp1jt.setModel(jp1dft1);
						jp1.repaint();
						jp4dft.removeRow(r[i]);
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "���������쳣��ɾ��ʧ��", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
	
}
