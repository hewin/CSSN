package presentation.statisticsui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.scoreblservice.ScoreBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import businesslogicservice.userblservice.UserBLService;
import vo.course.CourseVO;
import vo.score.ScoreVO;
import vo.statistics.StatisticsVO;
import vo.user.UserVO;

import javax.swing.JScrollPane;

import presentation.courseui.StuTransformMethod;

public class StuStatisticsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel tempPanel = new JPanel();
	JPanel innerPanel = new JPanel();
	JPanel majorPanel = new JPanel();
	JPanel commonPanel = new JPanel();
	JPanel liberalPanel = new JPanel();
	JScrollPane emptyScrollPane;
	ArrayList<CourseVO> myCourse;
	ArrayList<ScoreVO> voList;
	StatisticsVO vo = new StatisticsVO();

	public StuStatisticsPanel(final String stuNO) {
		final RMIFactory factory = Client.getFactory();
		setLayout(null);

		tempPanel.setLayout(null);
		tempPanel.setBounds(0, 45, 580, 400);// 410

		innerPanel.setLayout(null);
		innerPanel.setVisible(false);
		innerPanel.setBounds(0, 53, 580, 400);// 400

		drawInitialPanel(stuNO);

		JLabel chooseItemLabel = new JLabel("选择项目：");
		chooseItemLabel.setFont(new Font("楷体", Font.PLAIN, 18));
//		chooseItemLabel.setForeground(Color.GREEN);
		chooseItemLabel.setBounds(43, 16, 102, 18);
		add(chooseItemLabel);

		JButton scoreList = new JButton("成绩单");
		scoreList.setFont(new Font("楷体", Font.PLAIN, 15));
		// scoreList.setForeground(Color.black);
		scoreList.setBounds(152, 15, 93, 27);
		scoreList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				drawInitialPanel(stuNO);
			}
		});
		add(scoreList);

		JButton GPAButton = new JButton("学分绩");
		GPAButton.setForeground(Color.black);
		GPAButton.setFont(new Font("楷体", Font.PLAIN, 15));
		GPAButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tempPanel.removeAll();
				ArrayList<Double> gpaList = new ArrayList<Double>();
				try {
					StatisticsBLService stq = factory.getSTQService();
					UserBLService uq = factory.getUQService();
					UserVO uv = uq.getUserByIdNum(stuNO);
					String grade = uv.getGrade();
					StatisticsVO vo = stq.checkGPA(stuNO, grade);
					gpaList = vo.getGpa();
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (int i = 0; i < gpaList.size(); i++) {
					JLabel lb = new JLabel("第" + (i + 1) + "学年学分绩为："
							+ gpaList.get(i));
					lb.setBounds(230, 50 + 50 * i, 200, 30);
					lb.setFont(new Font("楷体", Font.PLAIN, 15));
					tempPanel.add(lb);
					repaint();
				}
			}
		});
		GPAButton.setBounds(255, 15, 93, 27);
		add(GPAButton);

		JButton inAndOut = new JButton("准入准出课程统计");
		inAndOut.setForeground(Color.black);
		inAndOut.setFont(new Font("楷体", Font.PLAIN, 15));
		inAndOut.setBounds(358, 15, 159, 27);
		inAndOut.addActionListener(new ActionListener() {
			JPanel outPanel = new JPanel();
			JPanel inPanel = new JPanel();

			public void actionPerformed(ActionEvent arg0) {
				tempPanel.setVisible(false);
				tempPanel.removeAll();

				final JLabel outLabel = new JLabel("准出信息");
				outLabel.setFont(new Font("楷体", Font.PLAIN, 15));
//				outLabel.setForeground(Color.gray);
				outLabel.setBounds(140, 10, 60, 27);
				outLabel.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						drawInAndOutInitialPanel(inPanel, outPanel, outLabel,
								stuNO);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						outLabel.setForeground(Color.orange);
						setCursor(Cursor
								.getPredefinedCursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						outLabel.setForeground(Color.black);
						setCursor(Cursor.getDefaultCursor());
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
				tempPanel.add(outLabel);

				drawInAndOutInitialPanel(inPanel, outPanel, outLabel, stuNO);

				final JLabel inLabel = new JLabel("准入信息");
//				inLabel.setForeground(Color.gray);
				inLabel.setFont(new Font("楷体", Font.PLAIN, 15));
				inLabel.setBounds(380, 10, 60, 27);
				inLabel.addMouseListener(new MouseListener() {
					JPanel institutePanel = new JPanel();

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						institutePanel.setVisible(false);
						institutePanel.setLayout(null);
						outPanel.setVisible(false);
						inPanel.setLayout(null);
						inPanel.setBounds(5, 40, 600, 280);// 400
						inPanel.removeAll();
						JLabel prompt = new JLabel("选择院系：");
						prompt.setFont(new Font("楷体", Font.PLAIN, 16));
						prompt.setForeground(Color.black);
						prompt.setBounds(100, 0, 110, 30);
						inPanel.add(prompt);

						String[] institute = { "软件学院", "文学院", "物理学院", "地球科学与工程学院", "大气科学学院",
								"地理与海洋科学学院", "电子科学与工程学院", "现代工程与应用科学学院", "新闻传播学院", "商学院",
								"外国语学院", "政府管理学院", "社会学院", "工程管理学院", "建筑与城市规划学院", "天文与空间科学学院",
								"信息管理学院", "化学化工学院", "环境学院", "医学院", "海外教育学院", "生命科学学院" };

						final JComboBox<String> jc = new JComboBox<String>(
								institute);
						jc.setBounds(200, 0, 150, 30);
						jc.setFont(new Font("楷体", Font.PLAIN, 15));
						jc.setBackground(Color.white);
						jc.setForeground(Color.black);
						inPanel.add(jc);

						String[] title = { "编号", "课程号", "课程名", "学分" };
						Object[][] info = new Object[0][4];
						JTable table = new JTable(info, title);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane
								.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scrollPane.setBounds(20, 0, 530, 200);
						institutePanel.removeAll();
						institutePanel.setBounds(0, 40, 600, 400);
						institutePanel.add(scrollPane);

						JLabel total = new JLabel("已修满该院专业核心课总学分：");
						total.setFont(new Font("楷体", Font.PLAIN, 15));
						total.setForeground(Color.DARK_GRAY);
						total.setBounds(100, 200, 250, 30);
						institutePanel.add(total);

						final JTextField field = new JTextField();
						field.setEditable(false);
						field.setBounds(340, 200, 100, 30);
						field.setBackground(Color.white);
						institutePanel.add(field);

						institutePanel.setVisible(true);
						inPanel.add(institutePanel);

						JButton checkButton = new JButton("确定");
						checkButton.setFont(new Font("楷体", Font.PLAIN, 14));
						checkButton.setForeground(Color.black);
						checkButton.setBounds(380, 0, 80, 30);
						checkButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								institutePanel.removeAll();
								inPanel.remove(institutePanel);
								ArrayList<CourseVO> course = new ArrayList<CourseVO>();
								int creditTotal = 0;
								
								String[] title = { "编号", "课程号", "课程名", "学分" };
								Object[][] info = new Object[0][4];
								JTable table = new JTable(info, title);
								JScrollPane scrollPane = new JScrollPane(table);
								scrollPane
										.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scrollPane.setBounds(20, 0, 530, 200);
								institutePanel.add(scrollPane);
								inPanel.add(institutePanel);
								
								try {
									StatisticsBLService stq = factory
											.getSTQService();
									String institute = (String) jc
											.getSelectedItem();
									StatisticsVO stv = stq.checkAccess(stuNO,
											institute);
									course = stv.getList();
									creditTotal = stv.getCreditnum();
								} catch (Exception e) {
									e.printStackTrace();
								}

								if(course.size() > 0){
									StuTransformMethod trans = new StuTransformMethod();
									info = trans.transformThreeItem(course);
									table = new JTable(info, title) {
										/**
										 * 
										 */
										private static final long serialVersionUID = 1L;

										public boolean isCellEditable(int row,
												int column) {
											return false;
										}
									};
									for (int i = 0; i < info.length; i++) {
										creditTotal = creditTotal+ Integer.parseInt((String) info[i][3]);
									}
									scrollPane = new JScrollPane(table);
									scrollPane
									.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
									scrollPane.setBounds(20, 0, 530, 200);
									institutePanel.add(scrollPane);
									field.setText(creditTotal + "");
								}else{
									JOptionPane.showMessageDialog(null, "尚未选修该院系课程！");
								}
							}

						});
						inPanel.add(checkButton);
						tempPanel.add(inPanel);
						tempPanel.validate();
						repaint();
						inPanel.setVisible(true);
						tempPanel.setVisible(true);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						inLabel.setForeground(Color.orange);
						setCursor(Cursor
								.getPredefinedCursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						inLabel.setForeground(Color.black);
						setCursor(Cursor.getDefaultCursor());
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
				tempPanel.add(inLabel);
				add(tempPanel);
				tempPanel.repaint();
				tempPanel.setVisible(true);
			}
		});
		add(inAndOut);
	}

	public void drawInitialPanel(final String stuNO) {
		final RMIFactory factory = Client.getFactory();
		tempPanel.removeAll();
		tempPanel.setVisible(false);

		String[] semester = { "第一学年第一学期", "第一学年第二学期", "第二学年第一学期", "第二学年第二学期",
				"第三学年第一学期", "第三学年第二学期", "第四学年第一学期", "第四学年第二学期" };
		final JComboBox<String> jc = new JComboBox<String>(semester);
		jc.setFont(new Font("楷体", Font.PLAIN, 15));
		jc.setBackground(Color.white);
		jc.setForeground(Color.black);
		jc.setBounds(200, 8, 180, 30);
		tempPanel.add(jc);

		JLabel l = new JLabel("选择学期:");
		l.setFont(new Font("楷体", Font.PLAIN, 15));
		l.setForeground(Color.black);
		l.setBounds(100, 8, 80, 30);
		tempPanel.add(l);

		String[] title = { "课程号", "课程名", "成绩" };
		Object[][] info = new Object[0][3];
		JTable table = new JTable(info, title);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 0, 550, 230);
		innerPanel.add(scrollPane);
		tempPanel.add(innerPanel);
		innerPanel.setVisible(true);

		JButton OKButton = new JButton("确定");
		OKButton.setFont(new Font("楷体", Font.PLAIN, 15));
		OKButton.setForeground(Color.black);
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				innerPanel.removeAll();
				String[] title = { "课程号", "课程名", "成绩" };
				Object[][] info = new Object[0][3];
				JTable table = new JTable(info, title);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(23, 0, 550, 230);
				innerPanel.add(scrollPane);

				String selectedInstitute = (jc.getSelectedIndex() + 1) + "";
				ArrayList<String> courseNOList = new ArrayList<String>();

				try {
					CourseSelectionBLService csq = factory.getCSQService();
					myCourse = csq.checkMyCourse(stuNO, selectedInstitute,
							"true");
					if (myCourse.size() > 0) {
						for (int i = 0; i < myCourse.size(); i++) {
							courseNOList.add(myCourse.get(i).getCoID());
						}
						ScoreBLService scq = factory.getSCQService();
						voList = scq.checkStuScore(stuNO, courseNOList);

						info = new Object[voList.size()][3];
						for (int i = 0; i < voList.size(); i++) {
							ScoreVO vo = voList.get(i);
							info[i][0] = vo.getCourseNO();
							info[i][1] = myCourse.get(i).getCoName();
							if(vo.getScore() == -1){
								info[i][2] = "无";
							}else{
								info[i][2] = vo.getScore();
							}
						}

						table = new JTable(info, title) {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						scrollPane = new JScrollPane(table);
						scrollPane.setBounds(23, 0, 550, 230);
						innerPanel.add(scrollPane);
						tempPanel.add(innerPanel);
						innerPanel.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "尚未选中该学期课程！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		OKButton.setBounds(420, 8, 74, 29);
		tempPanel.add(OKButton);

		add(tempPanel);

		tempPanel.setVisible(true);
	}

	public void drawInAndOutInitialPanel(final JPanel inPanel,
			final JPanel outPanel, final JLabel outLabel, final String stuNO) {
		RMIFactory factory = Client.getFactory();
		inPanel.setVisible(false);
		majorPanel.setVisible(false);
		commonPanel.setVisible(false);
		liberalPanel.setVisible(false);

		outPanel.setLayout(null);
		outPanel.setBounds(5, 42, 600, 300);
		outPanel.removeAll();

		try {
			StatisticsBLService stq = factory.getSTQService();
			UserBLService uq = factory.getUQService();
			UserVO user = uq.getUserByIdNum(stuNO);
			String institute = user.getInstitute();
			vo = stq.checkAccess(stuNO, institute);
		} catch (Exception e) {
			e.printStackTrace();
		}

		final StuTransformMethod trans = new StuTransformMethod();

		final String[] title = { "编号", "课程号", "课程名", "学分" };

		Object[][] list = new Object[0][4];
		JTable table = new JTable(list, title);
		emptyScrollPane = new JScrollPane(table);
		emptyScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		emptyScrollPane.setBounds(20, 38, 530, 190);
		outPanel.removeAll();
		outPanel.add(emptyScrollPane);

		JLabel choose = new JLabel("选择课程类别：");
		choose.setFont(new Font("楷体", Font.PLAIN, 15));
		choose.setForeground(Color.black);
		choose.setBounds(70, 0, 120, 30);
		outPanel.add(choose);

		final JLabel majorLabel = new JLabel("学科专业课程");
		majorLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		majorLabel.setForeground(Color.black);
		majorLabel.setBounds(190, 0, 90, 30);
		majorLabel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				liberalPanel.setVisible(false);
				majorPanel.removeAll();
				outPanel.remove(emptyScrollPane);
				commonPanel.setVisible(false);

				majorPanel.setLayout(null);
				majorPanel.setBounds(10, 30, 600, 400);

				ArrayList<CourseVO> majorCourse = new ArrayList<CourseVO>();
				majorCourse = vo.getVO().getMajorList();

				if (majorCourse.size() > 0) {
					Object[][] major = trans.transformThreeItem(majorCourse);
					JTable table = new JTable(major, title);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setBounds(10, 8, 530, 190);
					majorPanel.add(scrollPane);

					JLabel total = new JLabel("已修满本院专业核心课总学分：");
					total.setFont(new Font("楷体", Font.PLAIN, 15));
					//total.setForeground(Color.DARK_GRAY);
					total.setBounds(90, 200, 250, 30);
					majorPanel.add(total);
					JTextField field = new JTextField();
					field.setEditable(false);
					field.setBounds(330, 200, 100, 25);
					field.setBackground(Color.white);
					String credit = getTotalCredit(majorCourse);
					field.setText(credit);
					majorPanel.add(field);

					outPanel.add(majorPanel);
					majorPanel.setVisible(true);
					repaint();
				} else {
					drawInAndOutInitialPanel(inPanel, outPanel, outLabel, stuNO);
					JOptionPane.showMessageDialog(null, "已修满本院专业核心课总学分为0！");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				majorLabel.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				majorLabel.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		outPanel.add(majorLabel);
		repaint();

		final JLabel commonLabel = new JLabel("通修课程");
		commonLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		commonLabel.setForeground(Color.black);
		commonLabel.setBounds(305, 0, 60, 30);
		commonLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				majorPanel.setVisible(false);
				liberalPanel.setVisible(false);
				commonPanel.removeAll();
				outPanel.remove(emptyScrollPane);
				commonPanel.setLayout(null);
				commonPanel.setBounds(10, 30, 600, 400);

				ArrayList<CourseVO> commonCourse = vo.getVO().getCommonList();
				if (commonCourse.size() > 0) {
					Object[][] common = trans.transformThreeItem(commonCourse);
					JTable table = new JTable(common, title);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setBounds(10, 8, 530, 190);
					commonPanel.add(scrollPane);

					JLabel total = new JLabel("已修满通修课程总学分：");
					total.setFont(new Font("楷体", Font.PLAIN, 15));
					total.setForeground(Color.DARK_GRAY);
					total.setBounds(90, 200, 250, 30);
					commonPanel.add(total);
					JTextField field = new JTextField();
					field.setEditable(false);
					field.setBounds(315, 200, 100, 25);
					field.setBackground(Color.white);
					String credit = getTotalCredit(commonCourse);
					field.setText(credit);
					commonPanel.add(field);

					outPanel.add(commonPanel);
					commonPanel.setVisible(true);
					repaint();
				} else {
					drawInAndOutInitialPanel(inPanel, outPanel, outLabel, stuNO);
					JOptionPane.showMessageDialog(null, "已修满通修课程总学分为0！");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				commonLabel.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				commonLabel.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		outPanel.add(commonLabel);
		repaint();

		final JLabel liberalLabel = new JLabel("通识教育课程");
		liberalLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		liberalLabel.setForeground(Color.black);
		liberalLabel.setBounds(390, 0, 90, 30);
		liberalLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				majorPanel.setVisible(false);
				commonPanel.setVisible(false);
				liberalPanel.removeAll();
				outPanel.remove(emptyScrollPane);
				liberalPanel.setLayout(null);
				liberalPanel.setBounds(10, 30, 600, 400);

				ArrayList<CourseVO> liberalCourse = vo.getVO()
						.getLiberalEducationList();

				if (liberalCourse.size() > 0) {
					Object[][] liberal = trans
							.transformThreeItem(liberalCourse);
					JTable table = new JTable(liberal, title);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setBounds(10, 8, 530, 190);
					liberalPanel.add(scrollPane);

					JLabel total = new JLabel("已修满通识教育课程总学分：");
					total.setFont(new Font("楷体", Font.PLAIN, 15));
					total.setForeground(Color.DARK_GRAY);
					total.setBounds(90, 200, 250, 30);
					liberalPanel.add(total);
					JTextField field = new JTextField();
					field.setEditable(false);
					field.setBounds(330, 200, 100, 25);
					field.setBackground(Color.white);
					String credit = getTotalCredit(liberalCourse);
					field.setText(credit);
					liberalPanel.add(field);

					outPanel.add(liberalPanel);
					liberalPanel.setVisible(true);
					repaint();
				} else {
					drawInAndOutInitialPanel(inPanel, outPanel, outLabel, stuNO);
					JOptionPane.showMessageDialog(null, "已修满通识教育课程总学分为0！");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				liberalLabel.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				liberalLabel.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		outPanel.add(liberalLabel);
		repaint();

		tempPanel.add(outPanel);
		outPanel.setVisible(true);
	}
	
	public String getTotalCredit(ArrayList<CourseVO> vo){
		int totalCredit = 0;
		for(int i = 0;i < vo.size();i ++){
			totalCredit = totalCredit + Integer.parseInt(vo.get(i).getCredit());
		}
		
		return totalCredit + "";
	}
}
