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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.courseblservice.CourseBLService;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import vo.course.CourseVO;

public class YXModifyInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	RMIFactory factory = Client.getFactory();

	JLabel add1;
	JLabel add2;
	JTextField jtf1_3;
	JTextField jtf1_4;
	JTextField jtf1_5;
	JTextField jtf1_6;
	JTextField jtf1_7;
	JTextField jtf1_8;
	JTextField jtf1_9;
	JTextField jtf1_10;
	JLabel add11;
	JTextField jtf1_12;
	JTextField jtf1_13;
	JTextField jtf1_14;
	

	JTable table1;

	public YXModifyInfoPanel(final String institution) {
		setLayout(null);
		// ===================第一层上显示的组件============
		final JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBounds(0, 0, 575, 246);
		add(scrollPane1);

		Object[][] cellData1 = new Object[50][3];
		String[] headers1 = { "编号", "课程号", "课程名字" };

		table1 = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		table1.setModel(new DefaultTableModel(cellData1, headers1));
		scrollPane1.setViewportView(table1);
		table1.setBackground(Color.WHITE);
		table1.add(table1.getTableHeader());

		// 第一层的显示我的课程按钮
		final JButton button1_2 = new JButton("显示我的课程");
		button1_2.setFont(new Font("楷体", Font.PLAIN, 18));
		button1_2.setBounds(108, 279, 146, 36);
		button1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourseBLService coservice = factory.getCQService();
					ArrayList<CourseVO> vList = coservice
							.checkMycourseList(institution);
					fillintable_course(vList);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(button1_2);

		// 第一层的选择按钮
		final JButton button1_1 = new JButton("选择");
		button1_1.setFont(new Font("楷体", Font.PLAIN, 18));
		button1_1.setBounds(264, 279, 150, 36);
		add(button1_1);

		// ===================第二层上显示的组件======================
		// 课程号
		final JLabel jl1_1 = new JLabel("课程号");
		jl1_1.setBounds(0, 10, 70, 35);
		jl1_1.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_1.setHorizontalAlignment(SwingConstants.RIGHT);

		add1 = new JLabel();
		add1.setBounds(80, 15, 80, 30);
		add1.setFont(new Font("楷体", Font.PLAIN, 15));

		// 课程名
		final JLabel jl1_2 = new JLabel("课程名");
		jl1_2.setBounds(185, 10, 70, 35);
		jl1_2.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_2.setHorizontalAlignment(SwingConstants.RIGHT);

		add2 = new JLabel();
		add2.setBounds(265, 15, 80, 30);
		add2.setFont(new Font("楷体", Font.PLAIN, 15));

		// 课程性质
		final JLabel jl1_3 = new JLabel("课程性质");
		jl1_3.setBounds(366, 12, 80, 30);
		jl1_3.setFont(new Font("楷体", Font.PLAIN, 15));

		jtf1_3 = new JTextField();
		jtf1_3.setBounds(436, 12, 112, 30);
		jtf1_3.setFont(new Font("楷体", Font.PLAIN, 13));

		// 学分
		final JLabel jl1_4 = new JLabel("学分");
		jl1_4.setBounds(10, 54, 53, 30);
		jl1_4.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_4.setHorizontalAlignment(SwingConstants.RIGHT);

		jtf1_4 = new JTextField();
		jtf1_4.setBounds(80, 55, 80, 30);
		jtf1_4.setFont(new Font("楷体", Font.PLAIN, 13));

		// 课时
		final JLabel jl1_5 = new JLabel(" 课时");
		jl1_5.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_5.setBounds(199, 59, 46, 21);

		jtf1_5 = new JTextField();
		jtf1_5.setFont(new Font("楷体", Font.PLAIN, 13));
		jtf1_5.setBounds(265, 54, 80, 30);

		// 所属模块
		final JLabel jl1_6 = new JLabel("所属模块");
		jl1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		jl1_6.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_6.setBounds(346, 54, 80, 30);

		jtf1_6 = new JTextField();
		jtf1_6.setFont(new Font("楷体", Font.PLAIN, 13));
		jtf1_6.setBounds(436, 54, 112, 30);

		// 教师工号
		final JLabel jl1_7 = new JLabel(" 教师工号");
		jl1_7.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_7.setBounds(0, 102, 70, 30);

		jtf1_7 = new JTextField();
		jtf1_7.setFont(new Font("楷体", Font.PLAIN, 13));
		jtf1_7.setBounds(80, 102, 80, 30);

		// 任课教师
		final JLabel jl1_8 = new JLabel("任课教师");
		jl1_8.setBounds(199, 102, 80, 30);
		jl1_8.setFont(new Font("楷体", Font.PLAIN, 15));

		jtf1_8 = new JTextField();
		jtf1_8.setBounds(265, 102, 80, 30);
		jtf1_8.setFont(new Font("楷体", Font.PLAIN, 13));

		// 开设学期
		final JLabel jl1_9 = new JLabel("开设学期");
		jl1_9.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_9.setBounds(366, 102, 70, 30);

		jtf1_9 = new JTextField();
		jtf1_9.setBounds(436, 104, 112, 26);
		jtf1_9.setFont(new Font("楷体", Font.PLAIN, 13));

		// 开设年级
		final JLabel jl1_10 = new JLabel("开设年级");
		jl1_10.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_10.setBounds(10, 142, 70, 30);

		jtf1_10 = new JTextField();
		jtf1_10.setBounds(80, 143, 80, 29);
		jtf1_10.setFont(new Font("楷体", Font.PLAIN, 13));

		// 开设院系
		final JLabel jl1_11 = new JLabel("\u5F00\u8BBE\u9662\u7CFB");
		jl1_11.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_11.setBounds(199, 142, 70, 30);

		add11 = new JLabel();
		add11.setFont(new Font("楷体", Font.PLAIN, 15));
		add11.setBounds(265, 142, 80, 30);

		// 开课人数
		final JLabel jl1_12 = new JLabel("开课人数");
		jl1_12.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_12.setBounds(366, 142, 80, 30);

		jtf1_12 = new JTextField();
		jtf1_12.setFont(new Font("楷体", Font.PLAIN, 13));
		jtf1_12.setBounds(436, 143, 112, 28);

		// 上课时间
		final JLabel jl1_13 = new JLabel("上课时间");
		jl1_13.setHorizontalAlignment(SwingConstants.RIGHT);
		jl1_13.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_13.setBounds(-10, 182, 80, 35);

		jtf1_13 = new JTextField();
		jtf1_13.setFont(new Font("楷体", Font.PLAIN, 13));
		jtf1_13.setBounds(80, 184, 80, 30);

		// 上课地点
		final JLabel jl1_14 = new JLabel("上课地点");
		jl1_14.setBounds(175, 182, 80, 35);
		jl1_14.setFont(new Font("楷体", Font.PLAIN, 15));
		jl1_14.setHorizontalAlignment(SwingConstants.RIGHT);

		jtf1_14 = new JTextField();
		jtf1_14.setBounds(265, 184, 161, 30);
		jtf1_14.setFont(new Font("楷体", Font.PLAIN, 13));

		// 确定修改按钮
		final JButton jb1_2_1 = new JButton("确定修改");
		jb1_2_1.setBounds(175, 279, 93, 30);
		jb1_2_1.setFont(new Font("楷体", Font.PLAIN, 15));
		Monitor4 m4 = new Monitor4(); // 加监听
		jb1_2_1.addActionListener(m4);

		// 返回按钮
		final JButton jb1_2_2 = new JButton("返回");
		jb1_2_2.setBounds(296, 279, 93, 30);
		jb1_2_2.setFont(new Font("楷体", Font.PLAIN, 15));

		// "取消"按钮监听事件
		jb1_2_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(scrollPane1);
				add(button1_1);
				add(button1_2);
				repaint();
			}

		});

		// 选择课程监听事件
		button1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table1.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "您未选择任何课程", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					removeAll();
					add(jl1_1);
					add(jl1_2);
					add(jl1_3);
					add(jl1_4);
					add(jl1_5);
					add(jl1_6);
					add(jl1_7);
					add(jl1_8);
					add(jl1_9);
					add(jl1_10);
					add(jl1_11);
					add(jl1_12);
					add(jl1_13);
					add(jl1_14);
					add(add1);
					add(add2);
					add(jtf1_3);
					add(jtf1_4);
					add(jtf1_5);
					add(jtf1_6);
					add(jtf1_7);
					add(jtf1_8);
					add(jtf1_9);
					add(jtf1_10);
					add(add11);
					add(jtf1_12);
					add(jtf1_13);
					add(jtf1_14);
					add(jb1_2_1);
					add(jb1_2_2);
					repaint();
					try {
						CourseBLService cqs = factory.getCQService();
						ArrayList<CourseVO> vList2 = cqs
								.checkMycourseList(institution);// 要改的
						CourseVO cvo = vList2.get(table1.getSelectedRow());
						CourseVO cv = cqs.checkYXUnfinishedcourseInfo(cvo
								.getCoID());
						add1.setText(cv.getCoID());
						add2.setText(cv.getCoName());
						if (cv.getIsCompulsory()) {
							jtf1_3.setText("必修");
						} else {
							jtf1_3.setText("选修");
						}
						jtf1_4.setText(cv.getCredit());
						jtf1_5.setText(cv.getHour());
						jtf1_6.setText(cv.getModule());
						jtf1_7.setText(cv.getTeaID());
						jtf1_8.setText(cv.getTeacher());
						jtf1_9.setText(cv.getSemester());
						jtf1_10.setText(cv.getGrade());
						add11.setText(cv.getInstitution());
						jtf1_12.setText(cv.getNumOfStu());
						jtf1_13.setText(cv.getTime());
						jtf1_14.setText(cv.getLocation());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});
	}
	// 修改课程信息面板上“确定修改”按钮的监听事件
		public class Monitor4 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modifyCourse();
			}
		}

		// 修改课程信息
		public void modifyCourse() {
			String s1 = add1.getText();
			String s2 = add2.getText();
			String s3 = jtf1_3.getText();
			boolean b3 = true;
			if (s3.equals("必修")) {
				b3 = true;
			} else {
				b3 = false;
			}
			String s4 = jtf1_4.getText();
			String s5 = jtf1_5.getText();
			String s6 = jtf1_6.getText();
			String s7 = jtf1_7.getText();
			String s8 = jtf1_8.getText();
			String s9 = jtf1_9.getText();
			String s10 = jtf1_10.getText();
			String s11 = add11.getText();
			String s12 = jtf1_12.getText();
			String s13 = jtf1_13.getText();
			String s14 = jtf1_14.getText();

			if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")
					|| s5.equals("") || s6.equals("") || s7.equals("")
					|| s8.equals("") || s9.equals("") || s10.equals("")
					|| s11.equals("") || s11.equals("") || s13.equals("")
					|| s14.equals("")) {
				JOptionPane.showMessageDialog(this, "请将信息填写完整！", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else if(!isNumber(s4)){
				JOptionPane.showMessageDialog(this, "您填的学分不符合要求！", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else if(!isNumber(s5)){
				JOptionPane.showMessageDialog(this, "您填的课时不符合要求！", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else if(!isNumber(s12)){
				JOptionPane.showMessageDialog(this, "您填的开课人数不符合要求！", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else if(Integer.parseInt(s4)>5){
				JOptionPane.showMessageDialog(this, "您填的单门课程的学分过大", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			}else if(Integer.parseInt(s5)>50){
				JOptionPane.showMessageDialog(this, "您填的单门课程的课时过大", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				try {
					CourseBLService coservice = factory.getCQService();
					CourseVO vo = new CourseVO(s1, s2, b3, s4, s5, s6, s7, s8, s9,
							s10, s11, s12, s13, s14);
					int result = coservice.modifyCourseInfo(s1, vo);
					switch (result) {
					case -1:
						JOptionPane.showMessageDialog(this, "不存在这门课程！", "提示",
								JOptionPane.INFORMATION_MESSAGE, null);
						break;
					case 0:
						JOptionPane.showMessageDialog(this, "课程号或课程名已经存在！",//
								"提示", JOptionPane.INFORMATION_MESSAGE, null);
						break;
					case 1:
						JOptionPane.showMessageDialog(this, "课程修改成功！", "提示",
								JOptionPane.INFORMATION_MESSAGE, null);
						add1.setText("");
						add2.setText("");
						jtf1_3.setText("");
						jtf1_4.setText("");
						jtf1_5.setText("");
						jtf1_6.setText("");
						jtf1_7.setText("");
						jtf1_8.setText("");
						jtf1_9.setText("");
						jtf1_10.setText("");
						add11.setText("");
						jtf1_12.setText("");
						jtf1_13.setText("");
						jtf1_14.setText("");
						break;
					}

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		}
		
		// 更新表格
		public void fillintable_course(List<CourseVO> voList) {
			DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
			tableModel.setRowCount(0);// 清除原有行
			int i = 1;

			// 填充数据
			for (CourseVO cv : voList) {
				String[] arr = new String[3];
				arr[0] = (i++) + "";
				arr[1] = cv.getCoID();
				arr[2] = cv.getCoName();

				// 添加数据到表格
				tableModel.addRow(arr);
			}

			// 更新表格
			table1.invalidate();
			table1.updateUI();
		}

		public boolean isNumber(String s){
			boolean isNumber=true;
			char ch[]=s.toCharArray();
			for(int i=0;i<ch.length;i++){
				if(ch[i]<'0'||ch[i]>'9'){
					isNumber=false;
				}
			}
			return isNumber;
		}
	

}
