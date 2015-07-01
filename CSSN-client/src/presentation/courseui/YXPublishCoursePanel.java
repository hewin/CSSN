package presentation.courseui;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.userblservice.UserBLService;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import vo.course.CourseVO;
import vo.user.UserVO;

public class YXPublishCoursePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	RMIFactory factory = Client.getFactory();
	ArrayList<UserVO> upList;

	JTextField jtf4_1;
	JTextField jtf4_2;
	JComboBox<String> jcb4_3;
	JTextField jtf4_4;
	JTextField jtf4_5;
	JComboBox<String> jcb4_6;
	JComboBox<String> jcb4_7;
	JTextField jtf4_8;
	JComboBox<String> jcb4_9;
	JComboBox<String> jcb4_10;
	JTextField jtf4_11;
	JTextField jtf4_12;
	JTextField jtf4_13;
	JTextField jtf4_14;

	public YXPublishCoursePanel(final String institution) {
		setLayout(null);

		// �γ̺�
		final JLabel jl4_1 = new JLabel("�γ̺�");
		jl4_1.setBounds(0, 10, 70, 35);
		jl4_1.setFont(new Font("����", Font.PLAIN, 15));
		jl4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jl4_1);

		jtf4_1 = new JTextField();
		jtf4_1.setBounds(80, 15, 91, 30);
		jtf4_1.setFont(new Font("����", Font.PLAIN, 13));
		add(jtf4_1);

		// �γ���
		final JLabel jl4_2 = new JLabel("�γ���");
		jl4_2.setBounds(185, 10, 70, 35);
		jl4_2.setFont(new Font("����", Font.PLAIN, 15));
		jl4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jl4_2);

		jtf4_2 = new JTextField();
		jtf4_2.setBounds(265, 15, 80, 30);
		jtf4_2.setFont(new Font("����", Font.PLAIN, 13));
		add(jtf4_2);

		// �γ�����
		final JLabel jl4_3 = new JLabel("�γ�����");
		jl4_3.setBounds(366, 12, 80, 30);
		jl4_3.setFont(new Font("����", Font.PLAIN, 15));
		add(jl4_3);

		jcb4_3 = new JComboBox<String>();
		jcb4_3.setBounds(436, 12, 112, 30);
		jcb4_3.setFont(new Font("����", Font.PLAIN, 15));
		jcb4_3.setModel(new DefaultComboBoxModel<String>(new String[] {"ѡ��", "����"}));
		add(jcb4_3);

		// ѧ��
		final JLabel jl4_4 = new JLabel("ѧ��");
		jl4_4.setBounds(10, 55, 53, 30);
		jl4_4.setFont(new Font("����", Font.PLAIN, 15));
		jl4_4.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jl4_4);

		jtf4_4 = new JTextField();
		jtf4_4.setBounds(80, 59, 91, 30);
		jtf4_4.setFont(new Font("����", Font.PLAIN, 13));
		add(jtf4_4);

		// ��ʱ
		JLabel jl4_5 = new JLabel(" ��ʱ");
		jl4_5.setFont(new Font("����", Font.PLAIN, 15));
		jl4_5.setBounds(209, 60, 46, 21);
		add(jl4_5);

		jtf4_5 = new JTextField();
		jtf4_5.setFont(new Font("����", Font.PLAIN, 13));
		jtf4_5.setBounds(265, 59, 80, 30);
		jtf4_5.setColumns(10);
		add(jtf4_5);

		// ����ģ��
		JLabel jl4_6 = new JLabel("����ģ��");
		jl4_6.setHorizontalAlignment(SwingConstants.RIGHT);
		jl4_6.setFont(new Font("����", Font.PLAIN, 15));
		jl4_6.setBounds(344, 59, 80, 30);
		add(jl4_6);

		jcb4_6 = new JComboBox<String>();
		jcb4_6.setModel(new DefaultComboBoxModel<String>(new String[] { "רҵ����",
				"����ѡ��" }));
		jcb4_6.setFont(new Font("����", Font.PLAIN, 15));
		jcb4_6.setBounds(436, 55, 112, 30);
		add(jcb4_6);

		// �ον�ʦ
		final JLabel jl4_8 = new JLabel("�ον�ʦ");
		jl4_8.setBounds(199, 102, 80, 30);
		jl4_8.setFont(new Font("����", Font.PLAIN, 15));
		add(jl4_8);

		jtf4_8 = new JTextField();
		jtf4_8.setBounds(265, 102, 80, 30);
		jtf4_8.setFont(new Font("����", Font.PLAIN, 13));
		jtf4_8.setEditable(false);
		add(jtf4_8);
		
		// ��ʦ����
		jcb4_7 = new JComboBox<String>();
		jcb4_7.setFont(new Font("����", Font.PLAIN, 13));
		jcb4_7.setBounds(80, 103, 91, 29);
		add(jcb4_7);
		
		ImageIcon image1 = new ImageIcon("image/����.PNG");
		final JLabel jl2 = new JLabel(image1);
		jl2.setBounds(5, 103, 30, 30);
		add(jl2);
		

		final JLabel jb4_7 = new JLabel("����");
		jb4_7.setFont(new Font("����", Font.PLAIN, 15));
		jb4_7.setBounds(15, 103, 53,30);
		jb4_7.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jb4_7);
		
		jb4_7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					RMIFactory factory = Client.getFactory();
					UserBLService ubservice = factory.getUQService();
					upList = new ArrayList<UserVO>();
					upList = ubservice.getAllTeacher();
					String[] list = new String[upList.size()+1];
					list[0]="��ѡ��";
					for (int i = 1; i < upList.size()+1; i++) {
						list[i] = upList.get(i-1).getIdNum();
					}
					jcb4_7.setModel(new DefaultComboBoxModel<String>(list));
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
			
			public void mouseEntered(MouseEvent arg0) {
				jb4_7.setForeground(Color.red);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				jb4_7.setForeground(Color.BLACK);
				setCursor(Cursor.getDefaultCursor());
			}
			
		});

		jcb4_7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	
				String s=(String)jcb4_7.getSelectedItem();
				for(int i = 0; i < upList.size(); i++){
					if(upList.get(i).getIdNum().equals(s)){
						jtf4_8.setText(upList.get(i).getUserName());
						jtf4_8.setEditable(false);
					}
				}		
			}	
		});
		

		// ����ѧ��
		JLabel jl4_9 = new JLabel("����ѧ��");
		jl4_9.setFont(new Font("����", Font.PLAIN, 15));
		jl4_9.setBounds(366, 102, 70, 30);
		add(jl4_9);

		jcb4_9 = new JComboBox<String>();
		jcb4_9.setModel(new DefaultComboBoxModel<String>(new String[] { "1",
				"2", "3", "4", "5", "6", "7", "8" }));
		jcb4_9.setFont(new Font("����", Font.PLAIN, 15));
		jcb4_9.setBounds(436, 104, 112, 30);
		add(jcb4_9);

		// �����꼶
		JLabel jl4_10 = new JLabel("�����꼶");
		jl4_10.setFont(new Font("����", Font.PLAIN, 15));
		jl4_10.setBounds(10, 142, 70, 30);
		add(jl4_10);

		jcb4_10 = new JComboBox<String>();
		jcb4_10.setModel(new DefaultComboBoxModel<String>(new String[] {
				"2013", "2012", "2011", "2010" }));
		jcb4_10.setFont(new Font("����", Font.PLAIN, 15));
		jcb4_10.setBounds(80, 143, 91, 30);
		add(jcb4_10);

		// ����Ժϵ
		JLabel jl4_11 = new JLabel("����Ժϵ");
		jl4_11.setFont(new Font("����", Font.PLAIN, 15));
		jl4_11.setBounds(199, 142, 70, 30);
		add(jl4_11);

		 
		jtf4_11 = new JTextField();
		jtf4_11.setFont(new Font("����", Font.PLAIN, 15));
		jtf4_11.setBounds(265, 142, 80, 30);
		jtf4_11.setEditable(false);
		add(jtf4_11);
		jtf4_11.setText(institution);

		// ��������
		JLabel jl4_12 = new JLabel("��������");
		jl4_12.setFont(new Font("����", Font.PLAIN, 15));
		jl4_12.setBounds(366, 142, 80, 30);
		add(jl4_12);

		jtf4_12 = new JTextField();
		jtf4_12.setFont(new Font("����", Font.PLAIN, 13));
		jtf4_12.setBounds(436, 143, 112, 28);
		add(jtf4_12);

		// �Ͽ�ʱ��
		JLabel jl4_13 = new JLabel("�Ͽ�ʱ��");
		jl4_13.setHorizontalAlignment(SwingConstants.RIGHT);
		jl4_13.setFont(new Font("����", Font.PLAIN, 15));
		jl4_13.setBounds(-10, 182, 80, 35);
		add(jl4_13);

		jtf4_13 = new JTextField();
		jtf4_13.setFont(new Font("����", Font.PLAIN, 13));
		jtf4_13.setBounds(80, 184, 91, 30);
		add(jtf4_13);

		// �Ͽεص�
		final JLabel jl4_14 = new JLabel("�Ͽεص�");
		jl4_14.setBounds(175, 182, 80, 35);
		jl4_14.setFont(new Font("����", Font.PLAIN, 15));
		jl4_14.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jl4_14);

		jtf4_14 = new JTextField();
		jtf4_14.setBounds(265, 184, 91, 30);
		jtf4_14.setFont(new Font("����", Font.PLAIN, 13));
		add(jtf4_14);

		// ������ť
		JButton jb4_1 = new JButton("����"); // �����˼Ӽ���
		jb4_1.setBounds(175, 258, 80, 34);
		jb4_1.setFont(new Font("����", Font.PLAIN, 18));
		Monitor m = new Monitor();
		jb4_1.addActionListener(m);
		add(jb4_1);

		// ȡ����ť
		JButton jb4_2 = new JButton("ȡ��");
		jb4_2.setBounds(292, 258, 80, 34);
		jb4_2.setFont(new Font("����", Font.PLAIN, 18));
		add(jb4_2);

		jb4_2.addActionListener(new ActionListener() {// ȡ����ť�ļ����¼�
			public void actionPerformed(ActionEvent e) {
				jtf4_1.setText("");
				jtf4_2.setText("");
				jtf4_4.setText("");
				jtf4_5.setText("");
				jtf4_12.setText("");
				jtf4_13.setText("");
				jtf4_14.setText("");
				jtf4_8.setText("");
			}
		});
	}

	// ������ť�ļ�����
	public class Monitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			publishCourse();
		}
	}

	// Ժϵ������ʦ�����γ�
	public void publishCourse() {
		String s1 = jtf4_1.getText();
		String s2 = jtf4_2.getText();
		String s3 = (String) jcb4_3.getSelectedItem();
		boolean b3 = true;
		if (s3.equals("����")) {
			b3 = true;
		} else {
			b3 = false;
		}
		String s4 = jtf4_4.getText();
		String s5 = jtf4_5.getText();
		String s6 = (String) jcb4_6.getSelectedItem();
		String s7 = (String) jcb4_7.getSelectedItem();
		String s8 = jtf4_8.getText();
		String s9 = (String) jcb4_9.getSelectedItem();
		String s10 = (String) jcb4_10.getSelectedItem();
		String s11 = jtf4_11.getText();
		String s12 = jtf4_12.getText();
		String s13 = jtf4_13.getText();
		String s14 = jtf4_14.getText();

		if (s1.equals("") || s2.equals("") || s4.equals("") || s5.equals("")
				|| s7.equals("") || s8.equals("") || s11.equals("")
				|| s11.equals("") || s13.equals("") || s14.equals("")) {
			JOptionPane.showMessageDialog(this, "�뽫��Ϣ��д������", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		}else {
			try {
				CourseBLService coservice = factory.getCQService();
				CourseVO vo = new CourseVO(s1, s2, b3, s4, s5, s6, s7, s8, s9,
						s10, s11, s12, s13, s14);
				int result = coservice.publishYXCourse(vo);
				switch (result) {
				case -1:
					JOptionPane.showMessageDialog(this, "�ÿγ̺Ż�γ����Ѿ����ڣ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case -2:
					JOptionPane.showMessageDialog(this, "�����ѧ�ֲ�����Ҫ��ӦΪ��������", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case -3:
					JOptionPane.showMessageDialog(this, "����Ŀ�ʱ������Ҫ��ӦΪ��������", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case -4:
					JOptionPane.showMessageDialog(this, "��������ӦΪ��������", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case -5:
					JOptionPane.showMessageDialog(this, "����ĵ��ſγ̵�ѧ�ֹ���", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case -6:
					JOptionPane.showMessageDialog(this, "����ĵ��ſγ̵Ŀ�ʱ����", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case 0:
					JOptionPane.showMessageDialog(this, "��ģ����ѧ���Ѿ������ƻ������ޣ�",
							"��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
					break;
				case 1:
					JOptionPane.showMessageDialog(this, "�γ̷����ɹ���", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					jtf4_1.setText("");
					jtf4_2.setText("");
					jtf4_4.setText("");
					jtf4_5.setText("");
					jtf4_8.setText("");
					jtf4_12.setText("");
					jtf4_13.setText("");
					jtf4_14.setText("");
					break;
				}

			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}
	}

	
}
