package presentation.courseselectionui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import JavaRMI.RMIFactory;
import vo.course.CourseVO;
import vo.user.UserVO;

public class RKCheckStuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel panel;
	DefaultTableModel jp2dft;
	JTable jp2table;
	RMIFactory factory;
	String id;
	JButton jp2b2;
	

	public RKCheckStuPanel(final RMIFactory factory, final String id) {
		panel = new JPanel();
		setPanel(factory, id);
		this.factory=factory;
		this.id=id;
	}

	public void setPanel(final RMIFactory factory, final String id) {
		panel.setBounds(0, 0, 580, 360);
		panel.setLayout(null);

		ArrayList<CourseVO> courseList2 = new ArrayList<>();
		try {
			CourseBLService cqservice = factory.getCQService();
			courseList2 = cqservice.getTeacherCourse(id);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		String[] jp2title = { "课程号", "课程名称", "学分", "学年", "学期" };
		final String[][] jp2info = new String[courseList2.size()][5];
		for (int m = 0; m < courseList2.size(); m++) {
			jp2info[m][0] = courseList2.get(m).getCoID();
			jp2info[m][1] = courseList2.get(m).getCoName();
			jp2info[m][2] = courseList2.get(m).getCredit();
			jp2info[m][3] = courseList2.get(m).getGrade();
			jp2info[m][4] = courseList2.get(m).getSemester();
		}

		jp2dft = new DefaultTableModel(jp2info, jp2title);
		jp2table = new JTable(jp2dft) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		jp2table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
		jp2table.setFont(new Font("楷体", Font.PLAIN, 16));
		jp2table.getColumn("课程名称").setMinWidth(150); // 设置列宽
		jp2table.setBounds(10, 10, 555, 271);
		jp2table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					checkStu();
				}
			}
		});

		final JScrollPane jp2scrollPane = new JScrollPane(jp2table);
		jp2scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2scrollPane.setBounds(10, 10, 555, 271);
		panel.add(jp2scrollPane);

		final JButton jp2b1 = new JButton("查看学生");
		jp2b1.setFont(new Font("楷体", Font.PLAIN, 18));
		jp2b1.setBounds(320, 300, 100, 30);
		panel.add(jp2b1);

		final JLabel jp4jl = new JLabel("双击课程名以查看或点击");
		jp4jl.setBounds(110, 300, 220, 30);
		jp4jl.setFont(new Font("楷体", Font.BOLD, 17));
		panel.add(jp4jl);
		
		jp2b2 = new JButton("返回");
		jp2b2.setFont(new Font("楷体", Font.BOLD, 18));
		jp2b2.setBounds(240, 303, 105, 30);

		jp2b1.addActionListener(new ActionListener() { // 查看学生按钮

			public void actionPerformed(ActionEvent e) {
				checkStu();
			}

		});

		jp2b2.addActionListener(new ActionListener() { // 返回按钮

			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.add(jp2scrollPane);
				panel.add(jp2b1);
				panel.add(jp4jl);
				panel.repaint();
			}

		});
	}

	public JPanel getpanel() {
		return panel;
	}

	public void checkStu(){
			int row = jp2table.getSelectedRow();
			if (row >= 0) {
				ArrayList<UserVO> stuList = new ArrayList<>();
				try {
					CourseSelectionBLService csqservice = factory
							.getCSQService();
					int r = jp2table.getSelectedRow();
					stuList = csqservice.checkMyCourseStu(
							(String) jp2dft.getValueAt(r, 0), id);
				} catch (RemoteException e2) {
					JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}

				String[] jp2title1 = { "编号", "学号", "姓名", "院系", "年级" };
				int stuNum = stuList.size();
				final String[][] jp2info1 = new String[stuNum][5];
				for (int m = 0; m < stuList.size(); m++) {
					jp2info1[m][0] = (m + 1) + "";
					jp2info1[m][1] = stuList.get(m).getIdNum();
					jp2info1[m][2] = stuList.get(m).getUserName();
					jp2info1[m][3] = stuList.get(m).getInstitute();
					jp2info1[m][4] = stuList.get(m).getGrade();
				}
				JTable jp2table1 = new JTable(jp2info1, jp2title1);
				jp2table1.getTableHeader().setFont(
						new Font("楷体", Font.PLAIN, 17));
				jp2table1.setFont(new Font("楷体", Font.PLAIN, 16));
				jp2table1.setBounds(10, 10, 555, 271);

				final JScrollPane jp2scrollPane1 = new JScrollPane(
						jp2table1);
				jp2scrollPane1
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp2scrollPane1.setBounds(10, 10, 555, 271);
				panel.removeAll();
				panel.add(jp2scrollPane1);
				panel.add(jp2b2);
				panel.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "尚未选择课程！", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			}

	}
	
}
