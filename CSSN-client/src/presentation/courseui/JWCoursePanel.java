package presentation.courseui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.userblservice.UserBLService;

import vo.course.CourseVO;
import vo.user.UserVO;

public class JWCoursePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	ArrayList<UserVO> upList;

	public JWCoursePanel() {
		super();
		setLayout(null);
		initPanel();
	}
	
	public void initPanel(){
		removeAll();
		
		//��Ϣ��ʾ��
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("����", Font.PLAIN, 18));
		
		// -----------------------��һҳ---------------------
		final JLabel jp4jl1 = new JLabel("�γ̺�");
		jp4jl1.setBounds(5, 22, 100, 35);
		jp4jl1.setFont(new Font("����", Font.PLAIN, 16));
		// ���ñ�ǩ�����ֵĶ��뷽ʽ
		jp4jl1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl1);

		final JTextField jp4jf1 = new JTextField();
		jp4jf1.setBounds(105, 25, 100, 30);
		jp4jf1.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf1);

		final JLabel jp4jl2 = new JLabel("�γ���");
		jp4jl2.setBounds(175, 22, 100, 35);
		jp4jl2.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl2);

		final JTextField jp4jf2 = new JTextField();
		jp4jf2.setBounds(275, 25, 100, 30);
		jp4jf2.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf2);
		
		final JLabel jp4jl3 = new JLabel("�γ�����");
		jp4jl3.setBounds(345, 22, 100, 35);
		jp4jl3.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl3.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl3);
		
		String[] type3 = {"ѡ��","����"};
		final JComboBox<String> jp4jf3 = new JComboBox<String>(type3);
		jp4jf3.setBounds(445, 25, 100, 30);
		jp4jf3.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf3);
		
		final JLabel jp4jl4 = new JLabel("ѧ��");
		jp4jl4.setBounds(5, 72, 100, 35);
		jp4jl4.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl4.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl4);

		final JTextField jp4jf4 = new JTextField();
		jp4jf4.setBounds(105, 75, 100, 30);
		jp4jf4.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf4);

		final JLabel jp4jl5 = new JLabel("��ʱ");
		jp4jl5.setBounds(175, 72, 100, 35);
		jp4jl5.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl5.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl5);

		final JTextField jp4jf5 = new JTextField();
		jp4jf5.setBounds(275, 75, 100, 30);
		jp4jf5.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf5);
		
		final JLabel jp4jl6 = new JLabel("����ģ��");
		jp4jl6.setBounds(345, 72, 100, 35);
		jp4jl6.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl6.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl6);
		
		final JTextField jp4jf6 = new JTextField();
		jp4jf6.setBounds(445, 75, 100, 30);
		jp4jf6.setFont(new Font("����", Font.PLAIN, 16));
		jp4jf6.setText("ͨʶͨ��");
		jp4jf6.setEditable(false);
		add(jp4jf6);
		
		ImageIcon image1 = new ImageIcon("image/����.PNG");
		final JLabel jl2 = new JLabel(image1);
		jl2.setBounds(45, 122, 30, 30);
		add(jl2);
		
		final JLabel jp4jl7 = new JLabel("����");
		jp4jl7.setBounds(5, 122, 100, 35);
		jp4jl7.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl7.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl7);

		final JComboBox<String> jp4jf7 = new JComboBox<String>();
		jp4jf7.setBounds(105, 125, 100, 30);
		jp4jf7.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf7);
		
		final JLabel jp4jl8 = new JLabel("�ο���ʦ");
		jp4jl8.setBounds(175, 122, 100, 35);
		jp4jl8.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl8.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl8);

		final JTextField jp4jf8 = new JTextField();
		jp4jf8.setBounds(275, 125, 100, 30);
		jp4jf8.setFont(new Font("����", Font.PLAIN, 16));
		jp4jf8.setEditable(false);
		add(jp4jf8);
		
		jp4jl7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					RMIFactory factory = Client.getFactory();
					UserBLService ubservice = factory.getUQService();
					upList = new ArrayList<UserVO>();
					upList = ubservice.getAllTeacher();
					String[] list = new String[upList.size()+1];
					list[0] = "��ѡ��";
					for (int i = 0; i < upList.size(); i++) {
						list[i+1] = upList.get(i).getIdNum();
					}
					jp4jf7.setModel(new DefaultComboBoxModel<String>(list));
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
			
			public void mouseEntered(MouseEvent arg0) {
				jp4jl7.setFont(new Font("����", Font.BOLD, 18));
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				jp4jl7.setFont(new Font("����", Font.PLAIN, 16));
				setCursor(Cursor.getDefaultCursor());
			}
			
		});

		jp4jf7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	
				String s=(String)jp4jf7.getSelectedItem();
				for(int i = 0; i < upList.size(); i++){
					if(upList.get(i).getIdNum().equals(s)){
						jp4jf8.setText(upList.get(i).getUserName());
						jp4jf8.setEditable(false);
					}
				}		
			}	
		});
		
		final JLabel jp4jl9 = new JLabel("����ѧ��");
		jp4jl9.setBounds(345, 122, 100, 35);
		jp4jl9.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl9.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl9);
		
		String[] type9 = {"1", "2", "3", "4", "5", "6", "7", "8","All"};
		final JComboBox<String> jp4jf9 = new JComboBox<String>(type9);
		jp4jf9.setBounds(445, 125, 100, 30);
		jp4jf9.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf9);
		
		final JLabel jp4jl10 = new JLabel("�����꼶");
		jp4jl10.setBounds(5, 172, 100, 35);
		jp4jl10.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl10.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl10);
		
		String[] type10 = {"2013", "2012", "2011", "2010"};
		final JComboBox<String> jp4jf10 = new JComboBox<String>(type10);
		jp4jf10.setBounds(105, 175, 100, 30);
		jp4jf10.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf10);
		
		final JLabel jp4jl11 = new JLabel("����Ժϵ");
		jp4jl11.setBounds(175, 222, 100, 35);
		jp4jl11.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl11.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl11);

		String[] type11 = {"���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
				"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
				"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
				"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "��ѧϵ","��ʷѧԺ","������ѧѧԺ","All"};
		final JComboBox<String> jp4jf11 = new JComboBox<String>(type11);
		jp4jf11.setBounds(275, 225, 100, 30);
		jp4jf11.setFont(new Font("����", Font.PLAIN, 13));
		add(jp4jf11);
		
		final JLabel jp4jl12 = new JLabel("��������");
		jp4jl12.setBounds(5, 222, 100, 35);
		jp4jl12.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl12.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl12);

		final JTextField jp4jf12 = new JTextField();
		jp4jf12.setBounds(105, 225, 100, 30);
		jp4jf12.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf12);
		
		final JLabel jp4jl13 = new JLabel("�Ͽ�ʱ��");
		jp4jl13.setBounds(175, 172, 100, 35);
		jp4jl13.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl13.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl13);	
		
		final JTextField jp4jf13 = new JTextField();
		jp4jf13.setBounds(275, 175, 100, 30);
		jp4jf13.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf13);
		
		final JLabel jp4jl14 = new JLabel("�Ͽεص�");
		jp4jl14.setBounds(345, 172, 100, 35);
		jp4jl14.setFont(new Font("����", Font.PLAIN, 16));
		jp4jl14.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp4jl14);

		final JTextField jp4jf14 = new JTextField();
		jp4jf14.setBounds(445, 175, 100, 30);
		jp4jf14.setFont(new Font("����", Font.PLAIN, 16));
		add(jp4jf14);

		final JButton jp4jb1 = new JButton("��һҳ");
		jp4jb1.setBounds(170, 273, 80, 32);
		jp4jb1.setFont(new Font("����", Font.PLAIN, 18));
		add(jp4jb1);
		
		final JButton jp4jb2 = new JButton("ȡ��");
		jp4jb2.setBounds(310, 273, 80, 32);
		jp4jb2.setFont(new Font("����", Font.PLAIN, 18));
		add(jp4jb2);
		
		jp4jb2.addActionListener(new ActionListener() {   //ȡ��

			public void actionPerformed(ActionEvent e) {
				removeAll();
				
				jp4jf1.setText("");
				jp4jf2.setText("");
				jp4jf4.setText("");
				jp4jf5.setText("");
				jp4jf8.setText("");
				jp4jf12.setText("");
				jp4jf13.setText("");
				jp4jf14.setText("");
				
				add(jp4jl1);
				add(jp4jf1);
				add(jp4jl2);
				add(jp4jf2);
				add(jp4jl3);
				add(jp4jf3);
				add(jp4jl4);
				add(jp4jf4);
				add(jp4jl5);
				add(jp4jf5);
				add(jp4jl6);
				add(jp4jf6);
				add(jp4jl7);
				add(jl2);
				add(jp4jf7);
				add(jp4jl8);
				add(jp4jf8);
				add(jp4jl9);
				add(jp4jf9);
				add(jp4jl10);
				add(jp4jf10);
				add(jp4jl11);
				add(jp4jf11);
				add(jp4jl12);
				add(jp4jf12);
				add(jp4jl13);
				add(jp4jf13);
				add(jp4jl14);
				add(jp4jf14);
				add(jp4jb1);
				add(jp4jb2);
				repaint();
			}
		});

		// -----------------------�ڶ�ҳ---------------------
		final JLabel label = new JLabel("�γ�����");
		label.setFont(new Font("����", Font.PLAIN, 16));
		label.setBounds(100, 2, 84, 25);

		final JTextArea textArea = new JTextArea(12, 10);
		textArea.setFont(new Font("����", Font.BOLD, 18));
		final JScrollPane jsp1 = new JScrollPane(textArea);
		jsp1.setBounds(25, 32, 240, 255);

		final JLabel label_1 = new JLabel("�̲�Ŀ¼���ο���Ŀ");
		label_1.setFont(new Font("����", Font.PLAIN, 16));
		label_1.setBounds(360, 2, 200, 25);
		
		ImageIcon icon1 = new ImageIcon("image/�̲�.jpg");
		final JLabel jsp2jl1 = new JLabel(icon1);
		jsp2jl1.setBounds(295, 52, 30, 75);

		final JTextArea textArea_1 = new JTextArea(10, 5);
		textArea_1.setFont(new Font("����", Font.BOLD, 18));
		final JScrollPane jsp2 = new JScrollPane(textArea_1);
		jsp2.setBounds(325, 32, 240, 120);
		
		ImageIcon icon2 = new ImageIcon("image/�ο���.jpg");
		final JLabel jsp2jl2 = new JLabel(icon2);
		jsp2jl2.setBounds(295, 178, 30, 100);

		final JTextArea textArea_2 = new JTextArea(10,5);
		textArea_2.setFont(new Font("����", Font.BOLD, 18));
		final JScrollPane jsp3 = new JScrollPane(textArea_2);
		jsp3.setBounds(325, 162, 240, 120);

		final JButton button_1 = new JButton("����");
		button_1.setFont(new Font("����", Font.PLAIN, 18));
		button_1.setBounds(162, 294, 78, 32);

		final JButton button_2 = new JButton("����");
		button_2.setFont(new Font("����", Font.PLAIN, 18));
		button_2.setBounds(346, 294, 78, 32);
		
		jp4jb1.addActionListener(new ActionListener() {   //��һҳ

			public void actionPerformed(ActionEvent e) {
				
					removeAll();
					add(label);
					add(jsp2jl1);
					add(jsp1);
					add(label_1);
					add(jsp2);
					add(jsp2jl2);
					add(jsp3);
					add(button_1);
					add(button_2);
					repaint();
			}

		});

		button_1.addActionListener(new ActionListener() {//����

			public void actionPerformed(ActionEvent e) {
				if (jp4jf1.getText().equals("")
						||jp4jf2.getText().equals("")
						||jp4jf4.getText().equals("")
						||jp4jf5.getText().equals("")
						||jp4jf7.getSelectedIndex()==0
						||jp4jf8.getText().equals("")
						||jp4jf12.getText().equals("")
						||jp4jf13.getText().equals("")
						||jp4jf14.getText().equals("")
						
						||((textArea.getText()).equals(""))
						|| ((textArea_1.getText()).equals(""))
						|| ((textArea_2.getText()).equals(""))) {
					jdjll.setText("�뽫������Ϣ��д������");
				} else if (!isNumber(jp4jf4.getText())||Integer.parseInt(jp4jf4.getText()) <= 0) {
					jdjll.setText("�����ѧ�ֲ�����Ҫ��,ӦΪ0-5֮�����������");
				} else if (!isNumber(jp4jf5.getText())||Integer.parseInt(jp4jf5.getText()) <= 0) {
					jdjll.setText("����Ŀ�ʱ������Ҫ��,ӦΪ0-50֮�����������");
				} else if (!isNumber(jp4jf12.getText())||Integer.parseInt(jp4jf12.getText()) <= 0) {
					jdjll.setText("����Ŀ�������������Ҫ��,ӦΪ��������");
				}else if (Integer.parseInt(jp4jf4.getText()) > 10) {
					jdjll.setText("����ĵ��ſγ̵�ѧ�ֹ���,ӦΪ1-10");
				} else if (Integer.parseInt(jp4jf5.getText()) > 50) {
					jdjll.setText("����ĵ��ſγ̵Ŀ�ʱ����");
				}else {
					
					RMIFactory factory = Client.getFactory();
					try {
						CourseBLService cqservice = factory.getCQService();
						boolean isCompulsory = ((String)jp4jf3.getSelectedItem()=="����")?true:false;
						CourseVO vo = new CourseVO(jp4jf1.getText(),jp4jf2.getText(),isCompulsory,
								jp4jf4.getText(),jp4jf5.getText(), (String)jp4jf6.getText(),
								(String)jp4jf7.getSelectedItem(),jp4jf8.getText(),(String)jp4jf9.getSelectedItem(),
								(String)jp4jf10.getSelectedItem(),(String)jp4jf11.getSelectedItem(),jp4jf12.getText(),
								jp4jf13.getText(),jp4jf14.getText(),textArea.getText(),
								textArea_1.getText(),textArea_2.getText());
						
						int result = cqservice.publishJWCourse(vo);
						
						switch (result) {
						case -1:
							jdjll.setText("�γ̺Ż�γ����Ѵ��ڣ�");
							break;
						case 1:
							if((String)jp4jf3.getSelectedItem()=="����"){
								jdjll.setText("�����ɹ����ÿγ�Ϊ���޿Σ�ϵͳ���Զ���Ӹÿγ���"+(String)jp4jf11.getSelectedItem()+"����ѧ����ѡ���б��У�");
							}
							else{
							    jdjll.setText("�����ɹ���");
							}
							break;
						case 0:
							jdjll.setText("ģ����ѧ���Ѿ������ƻ�������");
							break;
						}
					}catch (RemoteException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
					}
				}
				JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		button_2.addActionListener(new ActionListener() { // ����

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jp4jl1);
				add(jp4jf1);
				add(jp4jl2);
				add(jp4jf2);
				add(jp4jl3);
				add(jp4jf3);
				add(jp4jl4);
				add(jp4jf4);
				add(jp4jl5);
				add(jp4jf5);
				add(jp4jl6);
				add(jp4jf6);
				add(jp4jl7);
				add(jl2);
				add(jp4jf7);
				add(jp4jl8);
				add(jp4jf8);
				add(jp4jl9);
				add(jp4jf9);
				add(jp4jl10);
				add(jp4jf10);
				add(jp4jl11);
				add(jp4jf11);
				add(jp4jl12);
				add(jp4jf12);
				add(jp4jl13);
				add(jp4jf13);
				add(jp4jl14);
				add(jp4jf14);
				add(jp4jb1);
				add(jp4jb2);
				repaint();
			}

		});		
	}
	
	public boolean isNumber(String s) {
		boolean isNumber = true;
		char ch[] = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] < '0' || ch[i] > '9') {
				isNumber = false;
			}
		}
		return isNumber;
	}

}
