package presentation.scoreui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.course.CourseVO;
import vo.score.ScoreVO;
import vo.user.UserVO;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.scoreblservice.ScoreBLService;

public class RKRecordScorePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[][] jp3info1;
	JTable jp3table1,jp3table;
	String selectedNO, selectedCredit,id;
	DefaultTableModel jp3dft;
	RMIFactory factory;
	JButton jp3b2,jp3b3;
	JLabel jp4jl;
	ArrayList<Integer> scoreExist = new ArrayList<Integer>();
	// 存储着已存在课程成绩的学生记录
	JPanel panel;

	public RKRecordScorePanel(final RMIFactory factory, final String id) {
		panel = new JPanel();
		setPanel(factory, id);
		this.factory=factory;
		this.id=id;
	}

	public void setPanel(final RMIFactory factory, final String id) {
		panel.setBounds(0, 0, 580, 360);
		panel.setLayout(null);

		ArrayList<CourseVO> courseList3 = new ArrayList<>();
		try {
			CourseBLService cqservice = factory.getCQService();
			courseList3 = cqservice.getTeacherCourse(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] jp3title = { "课程号", "课程名称", "学分", "学年", "学期" };
		final String[][] jp3info = new String[courseList3.size()][5];
		for (int m = 0; m < courseList3.size(); m++) {
			jp3info[m][0] = courseList3.get(m).getCoID();
			jp3info[m][1] = courseList3.get(m).getCoName();
			jp3info[m][2] = courseList3.get(m).getCredit();
			jp3info[m][3] = courseList3.get(m).getGrade();
			jp3info[m][4] = courseList3.get(m).getSemester();
		}
		jp3dft = new DefaultTableModel(jp3info, jp3title);
		jp3table = new JTable(jp3dft) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		jp3table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
		jp3table.setFont(new Font("楷体", Font.PLAIN, 16));
		jp3table.getColumn("课程名称").setMinWidth(150); // 设置列宽
		jp3table.setBounds(10, 10, 555, 271);
		jp3table.addMouseListener(new MouseAdapter(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(e.getClickCount()==2){
					record();
				}
			}
		});

		final JScrollPane jp3scrollPane = new JScrollPane(jp3table);
		jp3scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp3scrollPane.setBounds(10, 10, 555, 271);
		panel.add(jp3scrollPane);

		final JButton jp3b1 = new JButton("输入成绩");
		jp3b1.setFont(new Font("楷体", Font.BOLD, 18));
		jp3b1.setBounds(320, 300, 105, 30);
		panel.add(jp3b1);
		
		jp4jl = new JLabel("双击课程以输入或点击");
		jp4jl.setBounds(110, 300, 220, 30);
		jp4jl.setFont(new Font("楷体", Font.BOLD, 17));
		panel.add(jp4jl);

		jp3b2 = new JButton("保存");
		jp3b2.setFont(new Font("楷体", Font.BOLD, 17));
		jp3b2.setBounds(162, 312, 68, 28);

		jp3b3 = new JButton("返回");
		jp3b3.setFont(new Font("楷体", Font.BOLD, 17));
		jp3b3.setBounds(336, 309, 68, 28);

		jp3b1.addActionListener(new ActionListener() {// 输入成绩按钮

			public void actionPerformed(ActionEvent e) {
				record();
			}

		});

		jp3b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScoreBLService scq = factory.getSCQService();
					boolean isFull = true;
					boolean isFormat = true;
					for (int i = 0; i < jp3table1.getRowCount(); i++) {
						if (jp3table1.getValueAt(i, 5).equals("")) {
							isFull = false;// 表示未填写成绩完整
							break;
						}
						try {
							int score = Integer.parseInt((String) jp3table1.getValueAt(i, 5));
							if (score < 0 || score > 100) {
								isFormat = false;
								JOptionPane.showMessageDialog(null,	"输入成绩区间错误，应在0~100之间", "系统信息",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						} catch (Exception e1) {
							isFormat = false;
							JOptionPane.showMessageDialog(null,
									"输入成绩格式有误，期望得到整数", "系统信息",JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}// 判断成绩是否填写完整
					if (isFormat) {
						if (!isFull) {
							if (JOptionPane.showConfirmDialog(null,"成绩尚未填写完全，确定发布？", "系统信息",
									JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
								for (int i = 0; i < jp3table1.getRowCount(); i++) {
									if (!jp3table1.getValueAt(i, 5).equals("")) {
										if (scoreExist.contains(i)) {// 这是要更新的；有余力再改吧。。
											scq.updateScore(selectedNO,	id,	(String) jp3table1.getValueAt(i, 1),	Integer.parseInt((String) jp3table1
															.getValueAt(i, 5)),Integer.parseInt(selectedCredit));
										} else {// 如果之前没有；直接登记
											scq.recordScore(selectedNO,id,(String) jp3table1.getValueAt(i, 1),	Integer.parseInt((String) jp3table1	.
													getValueAt(i, 5)),Integer.parseInt(selectedCredit));
										}
									} 
								}
								JOptionPane	.showMessageDialog(null, "发布成功","系统信息",
												JOptionPane.INFORMATION_MESSAGE);
								panel.removeAll();
								panel.add(jp3scrollPane);
								panel.add(jp3b1);
								panel.add(jp4jl);
								panel.repaint();
							}
						} else {
							boolean recSus=true;
							for (int i = 0; i < jp3table1.getRowCount(); i++) {
								if (scoreExist.contains(i)) {// 这是要更新的；有余力再改吧。。
									try{
									scq.updateScore(
											selectedNO,id,
											(String) jp3table1.getValueAt(i, 1),
											Integer.parseInt((String) jp3table1
													.getValueAt(i, 5)), Integer
													.parseInt(selectedCredit));
									}catch(RemoteException e1){
										JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
												JOptionPane.INFORMATION_MESSAGE);
									}
								} else {// 如果之前没有；直接登记
								
									try{
									scq.recordScore(
											selectedNO,id,
											(String) jp3table1.getValueAt(i, 1),
											Integer.parseInt((String) jp3table1
													.getValueAt(i, 5)), Integer
													.parseInt(selectedCredit));
									}catch(RemoteException e1){
										JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
												JOptionPane.INFORMATION_MESSAGE);
										recSus=false;
									}
								}
							}
							if(recSus){
							JOptionPane.showMessageDialog(null, "发布成功", "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
							}
							panel.removeAll();
							panel.add(jp3scrollPane);
							panel.add(jp3b1);
							panel.repaint();
						}
					} else {
						// 信息格式错
					}

				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		jp3b3.addActionListener(new ActionListener() { // 返回按钮

			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.add(jp3scrollPane);
				panel.add(jp3b1);
				panel.add(jp4jl);
				panel.repaint();
			}

		});
	
	}

	public JPanel getpanel() {
		return panel;
	}

	public void record(){
			scoreExist.clear();// 清空
			int row = jp3table.getSelectedRow();
			if (row >= 0) {
				ArrayList<UserVO> stuList = new ArrayList<>();
				try {
					CourseSelectionBLService csqservice = factory
							.getCSQService();
					int r = jp3table.getSelectedRow();
					stuList = csqservice.checkMyCourseStu((String) jp3dft.getValueAt(r, 0), id);
					selectedNO = (String) jp3table.getValueAt(r, 0);// 课程号赋值
					selectedCredit = (String) jp3table.getValueAt(r, 2);// 学分赋值
				} catch (RemoteException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"无法连接到服务器，请检查网络连接 错误码404", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}

				String[] jp3title1 = { "编号", "学号", "姓名", "院系", "年级", "成绩" };
				int stuNum = stuList.size();
				jp3info1 = new String[stuNum][6];
				ScoreBLService scq = null;
				try {
					scq = factory.getSCQService();
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"无法连接到服务器，请检查网络连接 错误码404", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}
				for (int m = 0; m < stuList.size(); m++) {
					jp3info1[m][0] = (m + 1) + "";
					jp3info1[m][1] = stuList.get(m).getIdNum();
					jp3info1[m][2] = stuList.get(m).getUserName();
					jp3info1[m][3] = stuList.get(m).getInstitute();
					jp3info1[m][4] = stuList.get(m).getGrade();
					ScoreVO svo = null;
					try {
						svo = scq.checkStuScore(selectedNO, jp3info1[m][1]);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					if (svo.getScore() == -1) {
						jp3info1[m][5] = "";// 表示尚未登记
					} else {
						jp3info1[m][5] = svo.getScore() + "";// 如果之前登记过成绩
						scoreExist.add(m);// 第m条记录原来已经有成绩了
					}
				}
				jp3table1 = new JTable(jp3info1, jp3title1) {
					private static final long serialVersionUID = 1L;
					boolean[] isEditable = { false, false, false, false,false, true };
					public boolean isCellEditable(int r, int c) {
						return isEditable[c];
					}
				};
				jp3table1.getTableHeader().setFont(
						new Font("楷体", Font.PLAIN, 17));
				jp3table1.setFont(new Font("楷体", Font.PLAIN, 16));
				jp3table1.setBounds(10, 10, 555, 271);

				final JScrollPane jp3scrollPane1 = new JScrollPane(
						jp3table1);
				jp3scrollPane1
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp3scrollPane1.setBounds(10, 10, 555, 271);
				panel.removeAll();
				panel.add(jp3scrollPane1);
				panel.add(jp3b2);
				panel.add(jp3b3);
				panel.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "尚未选择课程！", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			}

	}
	
}
