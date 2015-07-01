package presentation.courseselectionui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentation.courseui.StuTransformMethod;
import vo.course.CourseVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;

public class StuMyCoursePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTable jp1table;
	JTable jp1table2;
	ArrayList<CourseVO> myCourse = new ArrayList<CourseVO>();
	ArrayList<CourseVO> unsureCourse;
	JScrollPane jp1scrollPane;
	JScrollPane jp1scrollPane2;
	JPanel tempPanel = new JPanel();
	Object[][] list = new Object[myCourse.size()][8];

	public StuMyCoursePanel(final String stuNO) {
		final RMIFactory factory = Client.getFactory();
		setLayout(null);
		final String[] jp1title = { "编号", "课程号", "课程名称", "课程性质", "学分", "教师",
				"上课地点", "上课时间" };

		tempPanel.setLayout(null);
		drawEmptyTable();
		tempPanel.setBounds(0, 55, 580, 300);

		JLabel label = new JLabel("选择学期：");
		label.setFont(new Font("楷体", Font.PLAIN, 18));
//		label.setForeground(Color.DARK_GRAY);
		label.setBounds(30, 16, 100, 24);
		add(label);

		String[] semester = { "第一学年第一学期", "第一学年第二学期", "第二学年第一学期", "第二学年第二学期",
				"第三学年第一学期", "第三学年第二学期", "第四学年第一学期", "第四学年第二学期" };
		final JComboBox<String> jc = new JComboBox<String>(semester);
		jc.setFont(new Font("楷体", Font.PLAIN, 16));
		jc.setBounds(115, 15, 180, 30);
		add(jc);

		final JLabel label1 = new JLabel("已选中课程");
		label1.setFont(new Font("楷体", Font.PLAIN, 15));
		label1.setBounds(320, 15, 100, 30);
		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				tempPanel.setVisible(false);
				tempPanel.removeAll();
				String selectedSemester = (jc.getSelectedIndex() + 1) + "";

				try {
					CourseSelectionBLService csq = factory.getCSQService();
					myCourse = csq.checkMyCourse(stuNO, selectedSemester,
							"true");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				if (myCourse.size() > 0) {
					StuTransformMethod trans = new StuTransformMethod();
					Object[][] list = trans.transform(myCourse);
					jp1table = new JTable(list, jp1title) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jp1table.getColumn("编号").setMinWidth(15); // 设置列宽
					jp1table.getColumn("课程号").setMinWidth(40);
					jp1table.getColumn("课程名称").setMinWidth(100);
					jp1table.getColumn("课程性质").setMinWidth(50);
					jp1table.getColumn("教师").setMinWidth(30);
					jp1table.getColumn("学分").setMinWidth(15);
					jp1table.getColumn("上课地点").setMinWidth(60);
					jp1table.getColumn("上课时间").setMinWidth(150);

					jp1scrollPane = new JScrollPane(jp1table);
					jp1scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp1scrollPane.setBounds(0, 0, 580, 250);
					tempPanel.add(jp1scrollPane);

					add(tempPanel);
					tempPanel.setVisible(true);
				} else {
					drawEmptyTable();
					JOptionPane.showMessageDialog(null, "尚未选中该学期课程！");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				label1.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				label1.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

		});
		add(label1);

		final JLabel label2 = new JLabel("等待抽签课程");
		label2.setFont(new Font("楷体", Font.PLAIN, 15));
		label2.setBounds(420, 15, 100, 30);
		label2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				tempPanel.setVisible(false);
				tempPanel.removeAll();

				String selectedSemester = (jc.getSelectedIndex() + 1) + "";

				try {
					CourseSelectionBLService csq = factory.getCSQService();
					unsureCourse = csq.checkMyCourse(stuNO, selectedSemester,
							"false");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				if (unsureCourse.size() > 0) {
					StuTransformMethod trans = new StuTransformMethod();
					Object[][] list2 = trans.transform(unsureCourse);
					jp1table2 = new JTable(list2, jp1title) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jp1table2.getColumn("编号").setMinWidth(15); // 设置列宽
					jp1table2.getColumn("课程号").setMinWidth(40);
					jp1table2.getColumn("课程名称").setMinWidth(100);
					jp1table2.getColumn("课程性质").setMinWidth(50);
					jp1table2.getColumn("教师").setMinWidth(30);
					jp1table2.getColumn("学分").setMinWidth(15);
					jp1table2.getColumn("上课地点").setMinWidth(60);
					jp1table2.getColumn("上课时间").setMinWidth(150);

					jp1scrollPane2 = new JScrollPane(jp1table2);
					jp1scrollPane2
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp1scrollPane2.setBounds(0, 0, 580, 250);
					tempPanel.add(jp1scrollPane2);

					add(tempPanel);
					tempPanel.setVisible(true);
				} else {
					drawEmptyTable();
					JOptionPane.showMessageDialog(null, "没有等待抽签的课程！");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				label2.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				label2.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

		});
		add(label2);
	}

	public void drawEmptyTable() {
		tempPanel.removeAll();
		String[] jp1title = { "编号", "课程号", "课程名称", "课程性质", "学分", "教师", "上课地点",
				"上课时间" };
		jp1table = new JTable(list, jp1title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jp1table.getColumn("编号").setMinWidth(15); // 设置列宽
		jp1table.getColumn("课程号").setMinWidth(40);
		jp1table.getColumn("课程名称").setMinWidth(100);
		jp1table.getColumn("课程性质").setMinWidth(50);
		jp1table.getColumn("教师").setMinWidth(30);
		jp1table.getColumn("学分").setMinWidth(15);
		jp1table.getColumn("上课地点").setMinWidth(60);
		jp1table.getColumn("上课时间").setMinWidth(150);

		jp1scrollPane = new JScrollPane(jp1table);
		jp1scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1scrollPane.setBounds(0, 0, 580, 250);
		tempPanel.add(jp1scrollPane);

		add(tempPanel);
		tempPanel.setVisible(true);
	}
}
