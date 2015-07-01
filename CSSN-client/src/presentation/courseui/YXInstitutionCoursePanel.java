package presentation.courseui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;

import JavaRMI.Client;
import JavaRMI.RMIFactory;

import vo.course.CourseVO;
import vo.user.UserVO;

public class YXInstitutionCoursePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	RMIFactory factory = Client.getFactory();
	
	CourseVO back;
	JTextArea textArea;
	JTextArea textArea_1;
	JTextArea textArea_2;
	
	JTable table2, table3;

	public YXInstitutionCoursePanel(final String institution) {
		setLayout(null);
		// =================��һ����������=================
		// ȫԺ�γ��б�
		final JScrollPane jsp3_1_1 = new JScrollPane();
		jsp3_1_1.setBounds(0, 56, 575, 218);
		jsp3_1_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp3_1_1);

		Object[][] cellData2 = new Object[50][3];
		String[] headers2 = { "���", "�γ̺�", "�γ�����" };
		table2 = new JTable(){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		table2.setModel(new DefaultTableModel(cellData2, headers2));
		jsp3_1_1.setViewportView(table2);
		table2.setBackground(Color.WHITE);
		table2.add(table2.getTableHeader());

		// �γ���ϸ��Ϣ��ť
		final JButton button3_1_1 = new JButton("�γ���ϸ��Ϣ");
		button3_1_1.setFont(new Font("����", Font.PLAIN, 18));
		button3_1_1.setBounds(86, 284, 148, 40);
		add(button3_1_1);

		// ѡ��ѧ���б�
		final JButton button3_1_2 = new JButton("ѡ��ѧ���б�");
		button3_1_2.setFont(new Font("����", Font.PLAIN, 18));
		button3_1_2.setBounds(303, 287, 156, 34);
		add(button3_1_2);

		// ��ʾȫԺ�γ��б�
		final JButton button3_1_3 = new JButton("��ʾȫԺ�γ��б�");
		button3_1_3.setFont(new Font("����", Font.PLAIN, 18));
		button3_1_3.setBounds(21, 10, 197, 36);
		button3_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourseBLService coservice = factory.getCQService();
					// ����Ҫ�ĵ�
					ArrayList<CourseVO> vList2 = coservice
							.checkYXcourseList(institution);
					fillintable_course2(vList2);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(button3_1_3);

		// =======================��������������=====================
		// ��ʾѧ���б�
		final JScrollPane jsp3_3_1 = new JScrollPane();
		jsp3_3_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp3_3_1.setBounds(0, 0, 575, 218);

		Object[][] cellData3 = new Object[50][3];
		String[] headers3 = { "���", "ѧ��", "ѧ������" };

		table3 = new JTable(){
			
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		table3.setModel(new DefaultTableModel(cellData3, headers3));
		jsp3_3_1.setViewportView(table3);
		table3.setBackground(Color.white);
		table3.add(table3.getTableHeader());

		// ���ذ�ť
		final JButton button3_3_1 = new JButton("����");
		button3_3_1.setFont(new Font("����", Font.PLAIN, 18));
		button3_3_1.setBounds(257, 250, 93, 40);

		// ���ذ�ť����Ӧ
		button3_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jsp3_1_1);
				add(button3_1_1);
				add(button3_1_2);
				add(button3_1_3);
				repaint();
			}
		});

		// ѧ��ѡ���б�ť����Ӧ
		button3_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table2.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "��δѡ���κογ̣�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					removeAll();
					add(jsp3_3_1);
					add(button3_3_1);
					repaint();
					try {
						CourseSelectionBLService csq = factory.getCSQService();
						CourseBLService cq = factory.getCQService();
						ArrayList<CourseVO> vList2 = cq
								.checkYXcourseList(institution);
						String coID = vList2.get(table2.getSelectedRow())
								.getCoID();
						ArrayList<UserVO> List = csq.checkAnyCourseStu(coID);
						fillintable_student(List);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		// =======================�������ڶ����ϵ�������ϣ�================
		// �γ̺�
		final JLabel jl3_1_1 = new JLabel("�γ̺�:");
		jl3_1_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_1_1.setBounds(8, 9, 74, 21);

		final JLabel jl3_1_2 = new JLabel("New label");
		jl3_1_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_1_2.setBounds(101, 6, 120, 26);

		// �γ���
		final JLabel jl3_2_1 = new JLabel("�γ���:");
		jl3_2_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_2_1.setBounds(8, 45, 72, 21);

		final JLabel jl3_2_2 = new JLabel("New label");
		jl3_2_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_2_2.setBounds(101, 42, 120, 26);

		// �γ�����
		final JLabel jl3_3_1 = new JLabel("�γ�����:");
		jl3_3_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_3_1.setBounds(8, 83, 84, 21);

		final JLabel jl3_3_2 = new JLabel("New label");
		jl3_3_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_3_2.setBounds(101, 80, 120, 26);

		// ѧ��
		final JLabel jl3_4_1 = new JLabel("ѧ��:");
		jl3_4_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_4_1.setBounds(8, 119, 84, 21);

		final JLabel jl3_4_2 = new JLabel("New label");
		jl3_4_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_4_2.setBounds(101, 116, 120, 26);

		// ��ʱ
		final JLabel jl3_5_1 = new JLabel("��ʱ:");
		jl3_5_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_5_1.setBounds(8, 155, 74, 21);

		final JLabel jl3_5_2 = new JLabel("New label");
		jl3_5_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_5_2.setBounds(101, 152, 120, 26);

		// ����ģ��
		final JLabel jl3_6_1 = new JLabel("����ģ��:");
		jl3_6_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_6_1.setBounds(8, 191, 84, 21);

		final JLabel jl3_6_2 = new JLabel("New label");
		jl3_6_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_6_2.setBounds(101, 188, 120, 26);

		// ��ʦ����
		final JLabel jl3_7_1 = new JLabel("��ʦ����:");
		jl3_7_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_7_1.setBounds(8, 231, 84, 15);

		final JLabel jl3_7_2 = new JLabel("New label");
		jl3_7_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_7_2.setBounds(101, 224, 120, 26);

		// �ον�ʦ
		final JLabel jl3_8_1 = new JLabel("�ον�ʦ:");
		jl3_8_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_8_1.setBounds(294, 12, 84, 15);

		final JLabel jl3_8_2 = new JLabel("New label");
		jl3_8_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_8_2.setBounds(390, 6, 120, 26);

		// ����ѧ��
		final JLabel jl3_9_1 = new JLabel("����ѧ��:");
		jl3_9_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_9_1.setBounds(294, 48, 84, 15);

		final JLabel jl3_9_2 = new JLabel("New label");
		jl3_9_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_9_2.setBounds(390, 42, 120, 26);

		// �����꼶
		final JLabel jl3_10_1 = new JLabel("�����꼶:");
		jl3_10_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_10_1.setBounds(294, 86, 84, 15);

		final JLabel jl3_10_2 = new JLabel("New label");
		jl3_10_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_10_2.setBounds(390, 80, 120, 26);

		// ����Ժϵ
		final JLabel jl3_11_1 = new JLabel("����Ժϵ:");
		jl3_11_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_11_1.setBounds(294, 122, 84, 15);

		final JLabel jl3_11_2 = new JLabel("New label");
		jl3_11_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_11_2.setBounds(390, 116, 120, 26);

		// ��������
		final JLabel jl3_12_1 = new JLabel("��������:");
		jl3_12_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_12_1.setBounds(294, 158, 84, 15);

		final JLabel jl3_12_2 = new JLabel("New label");
		jl3_12_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_12_2.setBounds(390, 152, 120, 26);

		// �Ͽ�ʱ��
		final JLabel jl3_13_1 = new JLabel("�Ͽ�ʱ��:");
		jl3_13_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_13_1.setBounds(294, 194, 84, 15);

		final JLabel jl3_13_2 = new JLabel("New label");
		jl3_13_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_13_2.setBounds(390, 186, 120, 26);

		// �Ͽεص�
		final JLabel jl3_14_1 = new JLabel("�Ͽεص�:");
		jl3_14_1.setFont(new Font("����", Font.PLAIN, 15));
		jl3_14_1.setBounds(294, 231, 84, 15);

		final JLabel jl3_14_2 = new JLabel("New label");
		jl3_14_2.setFont(new Font("����", Font.PLAIN, 15));
		jl3_14_2.setBounds(390, 220, 120, 26);

		// ��һҳ��ť
		final JButton button3_2_1 = new JButton("��һҳ");
		button3_2_1.setFont(new Font("����", Font.PLAIN, 15));
		button3_2_1.setBounds(204, 277, 110, 33);

		// =======================�������ڶ����ϵ�������£�================
		// �γ�����
		final JLabel lblNewLabel = new JLabel("�γ�����:");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 10, 94, 15);

		textArea = new JTextArea();
		textArea.setBounds(20, 24, 518, 70);
		final JScrollPane jsp_1 = new JScrollPane(textArea);
		jsp_1.setBounds(20, 24, 518, 70);

		// �̲�
		final JLabel lblNewLabel_1 = new JLabel("�̲�:");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 104, 70, 15);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(20, 118, 518, 70);
		final JScrollPane jsp_2 = new JScrollPane(textArea_1);
		jsp_2.setBounds(20, 118, 518, 70);

		// �ο���Ŀ
		final JLabel lblNewLabel_2 = new JLabel("�ο���Ŀ:");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(25, 198, 108, 15);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(20, 213, 518, 70);
		final JScrollPane jsp_3 = new JScrollPane(textArea_2);
		jsp_3.setBounds(20, 213, 518, 70);

		// ��һҳ��ť
		final JButton btnNewButton = new JButton("��һҳ");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 15));
		btnNewButton.setBounds(136, 300, 128, 30);

		// ���ؿγ��б�ť
		final JButton button = new JButton("���ؿγ��б�ť");
		button.setFont(new Font("����", Font.PLAIN, 15));
		button.setBounds(292, 300, 150, 30);

		// �γ���ϸ��Ϣ��ť��Ӧ
		button3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table2.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "��δѡ���κογ̣�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					removeAll();
					add(jl3_1_1);
					add(jl3_1_2);
					add(jl3_2_1);
					add(jl3_2_2);
					add(jl3_3_1);
					add(jl3_3_2);
					add(jl3_4_1);
					add(jl3_4_2);
					add(jl3_5_1);
					add(jl3_5_2);
					add(jl3_6_1);
					add(jl3_6_2);
					add(jl3_7_1);
					add(jl3_7_2);
					add(jl3_8_1);
					add(jl3_8_2);
					add(jl3_9_1);
					add(jl3_9_2);
					add(jl3_10_1);
					add(jl3_10_2);
					add(jl3_11_1);
					add(jl3_11_2);
					add(jl3_12_1);
					add(jl3_12_2);
					add(jl3_13_1);
					add(jl3_13_2);
					add(jl3_14_1);
					add(jl3_14_2);
					add(button3_2_1);
					repaint();
					try {

						CourseBLService coservice = factory.getCQService();
						ArrayList<CourseVO> vList2 = coservice
								.checkYXcourseList(institution);
						CourseVO cvo = vList2.get(table2.getSelectedRow());
						back = coservice.checkAnyCourseInfo(cvo.getCoID());// ���ص���ϸ��Ϣ
						jl3_1_2.setText(back.getCoID());
						jl3_2_2.setText(back.getCoName());
						if (back.getIsCompulsory()) {
							jl3_3_2.setText("����");
						} else {
							jl3_3_2.setText("ѡ��");
						}
						jl3_4_2.setText(back.getCredit());
						jl3_5_2.setText(back.getHour());
						jl3_6_2.setText(back.getModule());
						jl3_7_2.setText(back.getTeaID());
						jl3_8_2.setText(back.getTeacher());
						jl3_9_2.setText(back.getSemester());
						jl3_10_2.setText(back.getGrade());
						jl3_11_2.setText(back.getInstitution());
						jl3_12_2.setText(back.getNumOfStu());
						jl3_13_2.setText(back.getTime());
						jl3_14_2.setText(back.getLocation());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		// ��һҳ��ť����Ӧ
		button3_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(lblNewLabel);
				add(jsp_1);
				add(lblNewLabel_1);
				add(jsp_2);
				add(lblNewLabel_2);
				add(jsp_3);
				add(btnNewButton);
				add(button);
				repaint();
				textArea.setText(back.getDescription());
				textArea_1.setText(back.getTextbook());
				textArea_2.setText(back.getReference());
			}
		});

		// ��һҳ��ť����Ӧ
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jl3_1_1);
				add(jl3_1_2);
				add(jl3_2_1);
				add(jl3_2_2);
				add(jl3_3_1);
				add(jl3_3_2);
				add(jl3_4_1);
				add(jl3_4_2);
				add(jl3_5_1);
				add(jl3_5_2);
				add(jl3_6_1);
				add(jl3_6_2);
				add(jl3_7_1);
				add(jl3_7_2);
				add(jl3_8_1);
				add(jl3_8_2);
				add(jl3_9_1);
				add(jl3_9_2);
				add(jl3_10_1);
				add(jl3_10_2);
				add(jl3_11_1);
				add(jl3_11_2);
				add(jl3_12_1);
				add(jl3_12_2);
				add(jl3_13_1);
				add(jl3_13_2);
				add(jl3_14_1);
				add(jl3_14_2);
				add(button3_2_1);
				repaint();
				jl3_1_2.setText(back.getCoID());
				jl3_2_2.setText(back.getCoName());
				if (back.getIsCompulsory()) {
					jl3_3_2.setText("����");
				} else {
					jl3_3_2.setText("ѡ��");
				}
				jl3_4_2.setText(back.getCredit());
				jl3_5_2.setText(back.getHour());
				jl3_6_2.setText(back.getModule());
				jl3_7_2.setText(back.getTeaID());
				jl3_8_2.setText(back.getTeacher());
				jl3_9_2.setText(back.getSemester());
				jl3_10_2.setText(back.getGrade());
				jl3_11_2.setText(back.getInstitution());
				jl3_12_2.setText(back.getNumOfStu());
				jl3_13_2.setText(back.getTime());
				jl3_14_2.setText(back.getLocation());
			}
		});

		// ���ؿγ��б�ť����Ӧ
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jsp3_1_1);
				add(button3_1_1);
				add(button3_1_2);
				add(button3_1_3);
				repaint();
			}
		});
	}
	
	// ���±��2
		public void fillintable_course2(List<CourseVO> voList2) {
			DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
			tableModel.setRowCount(0);// ���ԭ����
			int i = 1;
			// �������
			for (CourseVO cv : voList2) {
				String[] arr = new String[3];
				arr[0] = (i++) + "";
				arr[1] = cv.getCoID();
				arr[2] = cv.getCoName();

				// ������ݵ����
				tableModel.addRow(arr);
			}
			// ���±��
			table2.invalidate();
			table2.updateUI();
		}

		// ���±��3
		public void fillintable_student(List<UserVO> voList3) {
			DefaultTableModel tableModel = (DefaultTableModel) table3.getModel();
			tableModel.setRowCount(0);// ���ԭ����
			int i = 1;
			// �������
			for (UserVO uv : voList3) {
				String[] arr = new String[3];
				arr[0] = (i++) + "";
				arr[1] = uv.getIdNum();
				arr[2] = uv.getUserName();

				// ������ݵ����
				tableModel.addRow(arr);
			}
			// ���±��
			table3.invalidate();
			table3.updateUI();
		}


}
