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
		// =================第一层上面的组件=================
		// 全院课程列表
		final JScrollPane jsp3_1_1 = new JScrollPane();
		jsp3_1_1.setBounds(0, 56, 575, 218);
		jsp3_1_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp3_1_1);

		Object[][] cellData2 = new Object[50][3];
		String[] headers2 = { "编号", "课程号", "课程名字" };
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

		// 课程详细信息按钮
		final JButton button3_1_1 = new JButton("课程详细信息");
		button3_1_1.setFont(new Font("楷体", Font.PLAIN, 18));
		button3_1_1.setBounds(86, 284, 148, 40);
		add(button3_1_1);

		// 选课学生列表
		final JButton button3_1_2 = new JButton("选课学生列表");
		button3_1_2.setFont(new Font("楷体", Font.PLAIN, 18));
		button3_1_2.setBounds(303, 287, 156, 34);
		add(button3_1_2);

		// 显示全院课程列表
		final JButton button3_1_3 = new JButton("显示全院课程列表");
		button3_1_3.setFont(new Font("楷体", Font.PLAIN, 18));
		button3_1_3.setBounds(21, 10, 197, 36);
		button3_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourseBLService coservice = factory.getCQService();
					// 将来要改的
					ArrayList<CourseVO> vList2 = coservice
							.checkYXcourseList(institution);
					fillintable_course2(vList2);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(button3_1_3);

		// =======================第三层上面的组件=====================
		// 显示学生列表
		final JScrollPane jsp3_3_1 = new JScrollPane();
		jsp3_3_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp3_3_1.setBounds(0, 0, 575, 218);

		Object[][] cellData3 = new Object[50][3];
		String[] headers3 = { "编号", "学号", "学生姓名" };

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

		// 返回按钮
		final JButton button3_3_1 = new JButton("返回");
		button3_3_1.setFont(new Font("楷体", Font.PLAIN, 18));
		button3_3_1.setBounds(257, 250, 93, 40);

		// 返回按钮的响应
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

		// 学生选课列表按钮的响应
		button3_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table2.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "您未选择任何课程！", "提示",
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

		// =======================第三面板第二层上的组件（上）================
		// 课程号
		final JLabel jl3_1_1 = new JLabel("课程号:");
		jl3_1_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_1_1.setBounds(8, 9, 74, 21);

		final JLabel jl3_1_2 = new JLabel("New label");
		jl3_1_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_1_2.setBounds(101, 6, 120, 26);

		// 课程名
		final JLabel jl3_2_1 = new JLabel("课程名:");
		jl3_2_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_2_1.setBounds(8, 45, 72, 21);

		final JLabel jl3_2_2 = new JLabel("New label");
		jl3_2_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_2_2.setBounds(101, 42, 120, 26);

		// 课程性质
		final JLabel jl3_3_1 = new JLabel("课程性质:");
		jl3_3_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_3_1.setBounds(8, 83, 84, 21);

		final JLabel jl3_3_2 = new JLabel("New label");
		jl3_3_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_3_2.setBounds(101, 80, 120, 26);

		// 学分
		final JLabel jl3_4_1 = new JLabel("学分:");
		jl3_4_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_4_1.setBounds(8, 119, 84, 21);

		final JLabel jl3_4_2 = new JLabel("New label");
		jl3_4_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_4_2.setBounds(101, 116, 120, 26);

		// 课时
		final JLabel jl3_5_1 = new JLabel("课时:");
		jl3_5_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_5_1.setBounds(8, 155, 74, 21);

		final JLabel jl3_5_2 = new JLabel("New label");
		jl3_5_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_5_2.setBounds(101, 152, 120, 26);

		// 所属模块
		final JLabel jl3_6_1 = new JLabel("所属模块:");
		jl3_6_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_6_1.setBounds(8, 191, 84, 21);

		final JLabel jl3_6_2 = new JLabel("New label");
		jl3_6_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_6_2.setBounds(101, 188, 120, 26);

		// 教师工号
		final JLabel jl3_7_1 = new JLabel("教师工号:");
		jl3_7_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_7_1.setBounds(8, 231, 84, 15);

		final JLabel jl3_7_2 = new JLabel("New label");
		jl3_7_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_7_2.setBounds(101, 224, 120, 26);

		// 任课教师
		final JLabel jl3_8_1 = new JLabel("任课教师:");
		jl3_8_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_8_1.setBounds(294, 12, 84, 15);

		final JLabel jl3_8_2 = new JLabel("New label");
		jl3_8_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_8_2.setBounds(390, 6, 120, 26);

		// 开设学期
		final JLabel jl3_9_1 = new JLabel("开设学期:");
		jl3_9_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_9_1.setBounds(294, 48, 84, 15);

		final JLabel jl3_9_2 = new JLabel("New label");
		jl3_9_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_9_2.setBounds(390, 42, 120, 26);

		// 开设年级
		final JLabel jl3_10_1 = new JLabel("开设年级:");
		jl3_10_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_10_1.setBounds(294, 86, 84, 15);

		final JLabel jl3_10_2 = new JLabel("New label");
		jl3_10_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_10_2.setBounds(390, 80, 120, 26);

		// 开设院系
		final JLabel jl3_11_1 = new JLabel("开设院系:");
		jl3_11_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_11_1.setBounds(294, 122, 84, 15);

		final JLabel jl3_11_2 = new JLabel("New label");
		jl3_11_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_11_2.setBounds(390, 116, 120, 26);

		// 开课人数
		final JLabel jl3_12_1 = new JLabel("开课人数:");
		jl3_12_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_12_1.setBounds(294, 158, 84, 15);

		final JLabel jl3_12_2 = new JLabel("New label");
		jl3_12_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_12_2.setBounds(390, 152, 120, 26);

		// 上课时间
		final JLabel jl3_13_1 = new JLabel("上课时间:");
		jl3_13_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_13_1.setBounds(294, 194, 84, 15);

		final JLabel jl3_13_2 = new JLabel("New label");
		jl3_13_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_13_2.setBounds(390, 186, 120, 26);

		// 上课地点
		final JLabel jl3_14_1 = new JLabel("上课地点:");
		jl3_14_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_14_1.setBounds(294, 231, 84, 15);

		final JLabel jl3_14_2 = new JLabel("New label");
		jl3_14_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl3_14_2.setBounds(390, 220, 120, 26);

		// 下一页按钮
		final JButton button3_2_1 = new JButton("下一页");
		button3_2_1.setFont(new Font("楷体", Font.PLAIN, 15));
		button3_2_1.setBounds(204, 277, 110, 33);

		// =======================第三面板第二层上的组件（下）================
		// 课程描述
		final JLabel lblNewLabel = new JLabel("课程描述:");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 10, 94, 15);

		textArea = new JTextArea();
		textArea.setBounds(20, 24, 518, 70);
		final JScrollPane jsp_1 = new JScrollPane(textArea);
		jsp_1.setBounds(20, 24, 518, 70);

		// 教材
		final JLabel lblNewLabel_1 = new JLabel("教材:");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 104, 70, 15);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(20, 118, 518, 70);
		final JScrollPane jsp_2 = new JScrollPane(textArea_1);
		jsp_2.setBounds(20, 118, 518, 70);

		// 参考书目
		final JLabel lblNewLabel_2 = new JLabel("参考书目:");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(25, 198, 108, 15);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(20, 213, 518, 70);
		final JScrollPane jsp_3 = new JScrollPane(textArea_2);
		jsp_3.setBounds(20, 213, 518, 70);

		// 上一页按钮
		final JButton btnNewButton = new JButton("上一页");
		btnNewButton.setFont(new Font("楷体", Font.PLAIN, 15));
		btnNewButton.setBounds(136, 300, 128, 30);

		// 返回课程列表按钮
		final JButton button = new JButton("返回课程列表按钮");
		button.setFont(new Font("楷体", Font.PLAIN, 15));
		button.setBounds(292, 300, 150, 30);

		// 课程详细信息按钮响应
		button3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table2.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "您未选择任何课程！", "提示",
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
						back = coservice.checkAnyCourseInfo(cvo.getCoID());// 返回的详细信息
						jl3_1_2.setText(back.getCoID());
						jl3_2_2.setText(back.getCoName());
						if (back.getIsCompulsory()) {
							jl3_3_2.setText("必修");
						} else {
							jl3_3_2.setText("选修");
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

		// 下一页按钮的响应
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

		// 上一页按钮的响应
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
					jl3_3_2.setText("必修");
				} else {
					jl3_3_2.setText("选修");
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

		// 返回课程列表按钮的响应
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
	
	// 更新表格2
		public void fillintable_course2(List<CourseVO> voList2) {
			DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
			tableModel.setRowCount(0);// 清除原有行
			int i = 1;
			// 填充数据
			for (CourseVO cv : voList2) {
				String[] arr = new String[3];
				arr[0] = (i++) + "";
				arr[1] = cv.getCoID();
				arr[2] = cv.getCoName();

				// 添加数据到表格
				tableModel.addRow(arr);
			}
			// 更新表格
			table2.invalidate();
			table2.updateUI();
		}

		// 更新表格3
		public void fillintable_student(List<UserVO> voList3) {
			DefaultTableModel tableModel = (DefaultTableModel) table3.getModel();
			tableModel.setRowCount(0);// 清除原有行
			int i = 1;
			// 填充数据
			for (UserVO uv : voList3) {
				String[] arr = new String[3];
				arr[0] = (i++) + "";
				arr[1] = uv.getIdNum();
				arr[2] = uv.getUserName();

				// 添加数据到表格
				tableModel.addRow(arr);
			}
			// 更新表格
			table3.invalidate();
			table3.updateUI();
		}


}
