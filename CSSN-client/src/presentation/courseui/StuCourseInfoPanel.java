package presentation.courseui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import vo.course.CourseVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;

public class StuCourseInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTable jp2table;
	JPanel tempPanel = new JPanel(); // ��רҵԺϵ�γ�panel
	JPanel tsPanel = new JPanel(); // ͨʶ��panel
	JScrollPane jp2scrollPane;
	JButton checkButton;
	ArrayList<CourseVO> instituteCourse = new ArrayList<CourseVO>();
	Object[][] instituteCourseList = new Object[instituteCourse.size()][4];
	ArrayList<CourseVO> publicCourse = new ArrayList<CourseVO>();
	Object[][] publicCourseList = new Object[publicCourse.size()][4];

	public StuCourseInfoPanel() {
		setLayout(null);
		final RMIFactory factory = Client.getFactory();
		final String[] jp2title = { "���", "�γ̺�", "�γ�����", "��ʦ" };

		tempPanel.setLayout(null);
		tempPanel.setBounds(0, 45, 580, 320);
		tempPanel.setVisible(false);

		tsPanel.setLayout(null);
		tsPanel.setBounds(0, 45, 580, 320);
		tsPanel.setVisible(false);

		drawEmptyTable(tempPanel);

		JLabel type = new JLabel("�γ����ͣ�");
		type.setFont(new Font("����", Font.PLAIN, 18));
		// type.setForeground(new Color(0, 102, 51));
		type.setBounds(100, 10, 101, 29);
		add(type);

		class CheckListener implements ActionListener { // �ڲ���,�鿴��ť�ļ���
			public void actionPerformed(ActionEvent arg0) {
				int selectedNO = jp2table.getSelectedRow();

				if (selectedNO < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��һ�ſγ̣�");
				} else {
					String courseNO = (String) jp2table.getModel().getValueAt(
							selectedNO, 1);
					CourseVO course = new CourseVO();
					try {
						CourseBLService cq = factory.getCQService();
						course = cq.checkAnyCourseInfo(courseNO);
					} catch (Exception e) {
						e.printStackTrace();
					}

					JFrame frame = new JFrame("�γ���ϸ��Ϣ");
					frame.setResizable(false);
					JPanel panel = new JPanel();
					panel.setLayout(null);

					JLabel courseNOLabel = new JLabel("�γ̺ţ�");
					courseNOLabel.setFont(new Font("��Բ", Font.BOLD, 15));
					courseNOLabel.setBounds(43, 42, 66, 15);
					panel.add(courseNOLabel);

					JLabel lblCourseno = new JLabel(course.getCoID());
					lblCourseno.setBounds(130, 42, 66, 15);
					panel.add(lblCourseno);

					JLabel courseName = new JLabel("�γ����ƣ�");
					courseName.setFont(new Font("��Բ", Font.BOLD, 15));
					courseName.setBounds(246, 42, 87, 15);
					panel.add(courseName);

					JLabel lblCoursename = new JLabel(course.getCoName());
					lblCoursename.setBounds(355, 42, 96, 15);
					panel.add(lblCoursename);

					JLabel property = new JLabel("�γ����ʣ�");
					property.setFont(new Font("��Բ", Font.BOLD, 15));
					property.setBounds(43, 89, 80, 15);
					panel.add(property);

					boolean isCompulsory = course.getIsCompulsory();
					JLabel lblIscompulsory;
					if (isCompulsory) {
						lblIscompulsory = new JLabel("����");
					} else {
						lblIscompulsory = new JLabel("ѡ��");
					}
					lblIscompulsory.setBounds(130, 89, 85, 15);
					panel.add(lblIscompulsory);

					JLabel credit = new JLabel("ѧ��:");
					credit.setFont(new Font("��Բ", Font.BOLD, 15));
					credit.setBounds(246, 89, 54, 15);
					panel.add(credit);

					JLabel lblCredit = new JLabel(course.getCredit());
					lblCredit.setBounds(355, 89, 54, 15);
					panel.add(lblCredit);

					JLabel teacher = new JLabel("�ον�ʦ��");
					teacher.setFont(new Font("��Բ", Font.BOLD, 15));
					teacher.setBounds(43, 136, 80, 15);
					panel.add(teacher);

					JLabel lblTeacher = new JLabel(course.getTeacher());
					lblTeacher.setBounds(130, 136, 54, 15);
					panel.add(lblTeacher);

					JLabel location = new JLabel("�Ͽεص㣺");
					location.setFont(new Font("��Բ", Font.BOLD, 15));
					location.setBounds(246, 136, 87, 15);
					panel.add(location);

					JLabel lblLocation = new JLabel(course.getLocation());
					lblLocation.setBounds(355, 136, 54, 15);
					panel.add(lblLocation);

					JLabel time = new JLabel("�Ͽ�ʱ�䣺");
					time.setFont(new Font("��Բ", Font.BOLD, 15));
					time.setBounds(43, 183, 80, 15);
					panel.add(time);

					JLabel lblTime = new JLabel(course.getTime());
					lblTime.setBounds(130, 183, 54, 15);
					panel.add(lblTime);

					JLabel description = new JLabel("�γ�������");
					description.setFont(new Font("��Բ", Font.BOLD, 15));;
					description.setBounds(43, 230, 80, 15);
					panel.add(description);

					JTextArea textArea = new JTextArea();
					textArea.setBounds(130, 230, 298, 80);
					textArea.setText(course.getDescription());
					textArea.setEditable(false);

					panel.add(textArea);
					frame.getContentPane().add(panel);
					frame.setBounds(400, 200, 550, 400);
					frame.setVisible(true);
				}
			}
		}


		final JLabel instituteCourseLabel = new JLabel("��Ժϵ�γ�");
		instituteCourseLabel.setFont(new Font("����", Font.PLAIN, 16));
		// instituteCourseLabel.setForeground(Color.BLUE);
		instituteCourseLabel.setBounds(234, 13, 88, 22);
		instituteCourseLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				drawEmptyTable(tempPanel);
				final JFrame frame = new JFrame("ѡ��Ժϵ");
				frame.setBounds(450, 300, 400, 290);
				frame.setResizable(false);

				JPanel chooseInstitute = new JPanel();
				chooseInstitute.setLayout(null);
				String[] institute = { "���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
						"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
						"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
						"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "��ѧϵ","��ʷѧԺ","������ѧѧԺ"};

				final JComboBox<String> jc = new JComboBox<String>(institute);
				jc.setBounds(170, 40, 150, 30);
				chooseInstitute.add(jc);

				JLabel l = new JLabel("ѡ��Ժϵ:");
				l.setFont(new Font("��Բ", Font.PLAIN, 16));
				l.setForeground(Color.BLUE);
				l.setBounds(60, 40, 80, 30);
				chooseInstitute.add(l);

				JButton OKButton = new JButton("ȷ��");
				OKButton.setFont(new Font("��Բ", Font.PLAIN, 16));
				OKButton.setForeground(Color.BLUE);
				OKButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
						tsPanel.setVisible(false);
						String selectedInstitute = (String) jc
								.getSelectedItem();
						drawEmptyTable(tempPanel);

						try {
							instituteCourse.clear();
							CourseBLService cq = factory.getCQService();
							instituteCourse = cq.checkYXCompletedcourseList(selectedInstitute);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (instituteCourse.size() == 0) {
							JOptionPane.showMessageDialog(null, "��δ�����Ժϵ�γ̣�");
						} else {
							tempPanel.removeAll();
							StuTransformMethod trans = new StuTransformMethod();
							instituteCourseList = trans
									.shortTransform(instituteCourse);

							jp2table = new JTable(instituteCourseList, jp2title) {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							jp2scrollPane = new JScrollPane(jp2table);
							jp2scrollPane
									.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
							jp2scrollPane.setBounds(0, 0, 580, 270);
							tempPanel.add(jp2scrollPane);
							tempPanel.setVisible(true);
							
							tempPanel.add(checkButton);
							checkButton.addActionListener(new CheckListener());

							add(tempPanel);
							repaint();
						}
					}
				});
				OKButton.setBounds(160, 130, 74, 29);
				chooseInstitute.add(OKButton);

				frame.getContentPane().add(chooseInstitute);
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);// �ö�

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				instituteCourseLabel.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				instituteCourseLabel.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

		});
		add(instituteCourseLabel);

		final JLabel publicCourseLabel = new JLabel("ͨʶ���ֿ�");
		// publicCourseLabel.setForeground(Color.BLUE);
		publicCourseLabel.setFont(new Font("����", Font.PLAIN, 16));
		publicCourseLabel.setBounds(365, 13, 88, 23);
		publicCourseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tempPanel.setVisible(false);
				drawEmptyTable(tsPanel);

				try {
					CourseBLService cq = factory.getCQService();
					publicCourse = cq.getModuleCompletedCourseList("ͨʶͨ��");
					
					for(int i = 0;i < publicCourse.size();i ++){
						String courseNO = publicCourse.get(i).getCoID();
						if(publicCourse.get(i).getIsCompulsory() || (courseNO.charAt(0) == 'P' && courseNO.charAt(1) == 'E')){
							publicCourse.remove(i);
							i --;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(publicCourse.size() > 0){
					tsPanel.removeAll();
					StuTransformMethod trans = new StuTransformMethod();
					publicCourseList = trans.shortTransform(publicCourse);

					jp2table = new JTable(publicCourseList, jp2title) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jp2table.setBounds(0, 0, 580, 200);

					JScrollPane jp2scrollPane = new JScrollPane(jp2table);
					jp2scrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp2scrollPane.setBounds(0, 0, 580, 270);
					tsPanel.add(jp2scrollPane);
					checkButton.addActionListener(new CheckListener());
					tsPanel.add(checkButton);
					tsPanel.setVisible(true);

					add(tsPanel);
					repaint();
				}else{
					JOptionPane.showMessageDialog(null, "��δ����ͨʶ���ֿΣ�");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				publicCourseLabel.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				publicCourseLabel.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

		});
		add(publicCourseLabel);
	}

	public void drawEmptyTable(JPanel panel) {
		panel.removeAll();
		String[] jp2title = { "���", "�γ̺�", "�γ�����", "��ʦ" };
		Object[][] list = new Object[0][4];
		jp2table = new JTable(list, jp2title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jp2scrollPane = new JScrollPane(jp2table);
		jp2scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2scrollPane.setBounds(0, 0, 580, 270);
		panel.add(jp2scrollPane);
		
		checkButton = new JButton("�鿴");
		checkButton.setFont(new Font("����", Font.PLAIN, 16));
		checkButton.setBounds(250, 275, 74, 29);
		panel.add(checkButton);

		add(panel);
		panel.setVisible(true);
	}
	
}
