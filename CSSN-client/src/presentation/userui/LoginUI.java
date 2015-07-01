package presentation.userui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presentation.mainui.JWTeacherUI;
import presentation.mainui.RKTeacherUI;
import presentation.mainui.StudentUI;
import presentation.mainui.YXTeacherUI;
import vo.user.Identity;
import vo.user.login.LoginVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.userblservice.login.LoginBLService;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel jp;
	JPasswordField jpw;// �����
	JComboBox<String> jc, jtjc;// �Զ���ȫ
	DefaultComboBoxModel<String> dftcbm;
	JTextField jt;
	JButton jb1;
	JRadioButton jrb,jb2;

	public LoginUI() {
		super("�Ͼ���ѧѡ��ϵͳ");
		int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setBounds(screenWidth/2-277, screenHeight/2-217, 555, 435);
		this.setLayout(null);
		setResizable(false);// ������󻯲�����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);// �رմ���ֹͣ��������

		jp = new JPanel();
		jp.setBounds(0, 0, 500, 400);
		setIcon();
		setComp();
		setPicture();
		add(jp);
		setVisible(true);
	}

	public void setIcon() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image i = kit.getImage("image/��¼.jpg");
		setIconImage(i);

	}

	public void setPicture() {

		ImageIcon bacgrd = new ImageIcon("image/1.jpg");
		JLabel imLabel = new JLabel(bacgrd);

		this.getLayeredPane().add(imLabel, new Integer(Integer.MIN_VALUE));

		final Container container1 = this.getContentPane();
		container1.setLayout(new BorderLayout());
		((JPanel) container1).setOpaque(false);
		 imLabel.setBounds(20,60,150,200);
		 container1.add(imLabel);
		 
	}

	public void setComp() {

		this.setLayout(null);

		final Container container1 = this.getContentPane();
		JLabel tag = new JLabel("�û���");
		tag.setBounds(200, 130, 100, 30);
		tag.setFont(new Font("����", Font.BOLD, 18));
		jp.add(tag);
		container1.add(tag);

		JLabel tag2 = new JLabel("����");
		tag2.setBounds(200, 180, 100, 30);
		tag2.setFont(new Font("����", Font.BOLD, 18));
		jp.add(tag2);                    
		container1.add(tag2);

		JLabel tag3 = new JLabel("���");
		tag3.setBounds(200, 80, 100, 30);
		tag3.setFont(new Font("����", Font.BOLD, 18));
		jp.add(tag3);
		container1.add(tag3);

		jt = new JTextField();
		jt.setBounds(280, 130, 155, 30);
		jt.setForeground(Color.black);
		jt.setFont(new Font("����", Font.BOLD, 25));
		dftcbm = new DefaultComboBoxModel<String>();
		jtjc = new JComboBox<String>(dftcbm) {
			private static final long serialVersionUID = 1L;

			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};

		jt.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {// ����
				autofinish();
			}

			public void removeUpdate(DocumentEvent e) {// �Ƴ�
				autofinish();
			}

			public void changedUpdate(DocumentEvent e) {
				autofinish();
			}

		});

		jt.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
					e.setSource(jtjc);
					jtjc.dispatchEvent(e);
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						jt.setText(jtjc.getSelectedItem().toString());
						jtjc.setPopupVisible(false);
						String userName = jt.getText();// �û���
						String id = (String) jc.getSelectedItem();
						String password = readFile(userName, id, "file/account.txt");
						if (!password.equals("")) {
							jpw.setText(password);
							jrb.setSelected(true);
							jpw.requestFocus();
						}
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					jtjc.setPopupVisible(false);
				}
			}
		});
		
		jt.add(jtjc, BorderLayout.SOUTH);
		jtjc.setBounds(0, 30, 155, 0);

		jt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				enterButton();
			}

		});
		
		jtjc.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			}
			
		});
		jp.add(jt);

		container1.add(jt);

		jpw = new JPasswordField();// �����
		jpw.setBounds(280, 180, 155, 30);
		jpw.setEchoChar('*');
		jpw.setFont(new Font("����", Font.BOLD, 25));
		jpw.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {
				String userName = jt.getText();// �û���
				String id = (String) jc.getSelectedItem();
				String password = readFile(userName, id, "file/account.txt");
				if (!password.equals("")) {
					jpw.setText(password);
					jrb.setSelected(true);
				}
			}

		});
		jpw.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				enterButton();
			}

		});
		jp.add(jpw);
		container1.add(jpw);

		String[] id = { "����", "Ժϵ����", "�ον�ʦ", "ѧ��", "����Ա" };
		jc = new JComboBox<String>(id);
		jc.setBounds(280, 80, 155, 30);
		jc.setFont(new Font("����", Font.PLAIN, 18));
		jc.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					enterButton();
				}
			}

		});
		jp.add(jc);
		container1.add(jc);

		jb1 = new JButton("��¼");
		jb1.setBounds(200, 280, 235, 35);
		jb1.setFont(new Font("����", Font.BOLD, 18));
		jb1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				enterButton();
			}

		});
		jp.add(jb1);
		container1.add(jb1);
		
		jb2=new JRadioButton("�Զ���¼");
		jb2.setBounds(310, 230, 110, 30);
		jb2.setFont(new Font("����", Font.PLAIN, 15));
		//jp.add(jb2);
		container1.add(jb2);
//		jb2.addActionListener(new ActionListener(){
//
//			public void actionPerformed(ActionEvent e) {
//				jt.setText("");
//				jpw.setText("");
//				jrb.setSelected(false);
//			}
//			
//		});
		
		//container1.add(jb2);

		jrb = new JRadioButton("��ס����");
		jrb.setFont(new Font("����", Font.PLAIN, 15));
		jrb.setBounds(200, 230, 110, 30);
		jp.add(jrb);
		container1.add(jrb);

	}

	public void enterButton() {

		String userName = jt.getText();
		String password = new String(jpw.getPassword());

		if (userName.equals("")) {
			JOptionPane.showMessageDialog(null, "�������û���", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "����������", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			RMIFactory factory = Client.getFactory();
			
			try {
				if(factory!=null){
					LoginBLService lgservice = factory.getLoginService();
					String result = "";
					switch ((String) jc.getSelectedItem()) {
					case "����":
						result = lgservice.login(new LoginVO(userName, password,
								Identity.JWTeacher));
						break;
					case "Ժϵ����":
						result = lgservice.login(new LoginVO(userName, password,
								Identity.YXTeacher));
						break;
					case "�ον�ʦ":
						result = lgservice.login(new LoginVO(userName, password,
								Identity.RKTeacher));
						break;
					case "ѧ��":
						result = lgservice.login(new LoginVO(userName, password,
								Identity.Student));
						break;
					case "����Ա":
						result = lgservice.login(new LoginVO(userName, password,
								Identity.Administrator));
						break;
					}
					if (result.equals("-1")) {// �û���������
						JOptionPane.showMessageDialog(null, "�û���������", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("-2")) {
						JOptionPane.showMessageDialog(null, "�������", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					} else {// ��¼�ɹ�
						if (jrb.isSelected()) {
							writeFile(userName, password,(String) jc.getSelectedItem(),"file/account.txt");
							writeFile(userName, password,(String) jc.getSelectedItem(),"file/account2.txt");
						} else {// �Ѽ�ס������ɾ��
							deleteRecord(userName, (String) jc.getSelectedItem());
						}
						switch ((int) jc.getSelectedIndex()) {
						case 0:
							new JWTeacherUI(result, userName);
							break;
						case 1:
							new YXTeacherUI(result.split(";")[0],
									result.split(";")[1], userName);
							break;
						case 2:
							new RKTeacherUI(result, userName);
							break;
						case 3:
							new StudentUI(result, userName);
							break;
						case 4:
							new AdministratorUI(result, userName);// �������˺�
							break;
						}
						((Window) jb1.getTopLevelAncestor()).dispose();
				}
				}
			} catch (RemoteException e1) {
				JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	public void writeFile(String account, String password, String identity,
			String fileName) {
		File file = new File(fileName);
		try {
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter bufr = new BufferedWriter(fr);
			if (readFile(account, identity, fileName).equals("")) {
				bufr.write(account + "%" + password + "%" + identity);
				bufr.newLine();
				bufr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFile(String account, String identity, String fileName) {
		File file = new File(fileName);
		String password = "";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			String record = null;
			while ((record = bufr.readLine()) != null) {
				if (record.split("%")[0].equals(account)
						&& record.split("%")[2].equals(identity)) {
					password = record.split("%")[1];
					break;
				}
			}
			bufr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return password;
	}

	public void deleteRecord(String userName, String identity) {
		File file = new File("file/account.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);

			String record = null;
			ArrayList<String> list = new ArrayList<String>();
			while ((record = bufr.readLine()) != null) {
				if (!record.split("%")[0].equals(userName)
						&& record.split("%")[2].equals(identity)) {
					list.add(record);
				}
			}
			bufr.close();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bufw = new BufferedWriter(fw);
			for (int i = 0; i < list.size(); i++) {
				bufw.write(list.get(i));
				bufw.newLine();
				bufw.flush();
			}
			bufw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateList() {
		boolean vis = false;
		dftcbm.removeAllElements();// ��ɾ�ټ�
		String input = jt.getText();
		ArrayList<String> list = new ArrayList<String>();
		File file = new File("file/account2.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			String record = null;
			while ((record = bufr.readLine()) != null) {
				if (record.startsWith(input)
						&& record.split("%")[2].equals(jc.getSelectedItem())) {
					list.add(record.split("%")[0]);
				}
			}
			if (list.size() > 0) {
				vis = true;
			}
			bufr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!input.isEmpty()) {
			for (String record : list) {// �������Ҫ���
				dftcbm.addElement(record);
				jtjc.setModel(dftcbm);
			}
		} else {
			vis = false;
		}
		return vis;
	}

	public void autofinish() {
		if (updateList()) {
			jtjc.setPopupVisible(true);
		} else {
			jtjc.setPopupVisible(false);
		}
	}

}