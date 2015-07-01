package presentation.scoreui;

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
import javax.swing.JTextField;

import presentation.courseui.StuTransformMethod;
import vo.course.CourseVO;
import vo.score.ScoreVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import Util.CreateSimpleExcelToDisk;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.scoreblservice.ScoreBLService;

public class StuScorePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTable jp5table;
	JTextField textField;
	ArrayList<CourseVO> myCourse = new ArrayList<CourseVO>();
	Object[][] selectedCourse = new Object[myCourse.size()][4];
	JPanel tempPanel = new JPanel();
	JScrollPane jp5scrollPane;
	String grade;
	final RMIFactory factory = Client.getFactory();
	
	public StuScorePanel(final String stuNO) {
		
		
		setLayout(null);
		final String[] jp5title = { "编号", "课程号", "课程名称", "任课教师","分数" };

		tempPanel.setLayout(null);
		tempPanel.setBounds(0, 50, 580, 300);

		JLabel label = new JLabel("请选择学期：");
		label.setFont(new Font("楷体", Font.PLAIN, 16));
//		label.setForeground(Color.DARK_GRAY);
		label.setBounds(60, 10, 112, 28);
		add(label);

		final String[] semester = { "第一学年第一学期", "第一学年第二学期", "第二学年第一学期", "第二学年第二学期",
				"第三学年第一学期", "第三学年第二学期", "第四学年第一学期", "第四学年第二学期" };
		grade=semester[0];
		final JComboBox<String> jc = new JComboBox<String>(semester);
		jc.setFont(new Font("楷体", Font.PLAIN, 16));
//		jc.setForeground(Color.blue);
		jc.setBounds(200, 10, 180, 30);
		add(jc);

		jp5table = new JTable(selectedCourse, jp5title);

		jp5scrollPane = new JScrollPane(jp5table);
		jp5scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp5scrollPane.setBounds(0, 0, 580, 220);
		tempPanel.add(jp5scrollPane);

//		JButton OKButton = new JButton("选择");
//		OKButton.setFont(new Font("楷体", Font.PLAIN, 17));
////		OKButton.setForeground(Color.BLUE);
//		OKButton.setBounds(50, 240, 75, 30);
//		OKButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int selectedNO = jp5table.getSelectedRow();
//				if (selectedNO < 0) {
//					JOptionPane.showMessageDialog(null, "请选择一门课程！");
//				} else {
//					String courseNO = (String) jp5table.getValueAt(selectedNO,
//							1);
//					ScoreVO vo = new ScoreVO();
//					try {
//						ScoreBLService sq = factory.getSCQService();
//						vo = sq.checkStuScore(courseNO, stuNO);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//					if(vo.getScore() == -1){
//						textField.setText("无");
//					}else{
//						textField.setText(vo.getScore() + "");
//					}
//				}
//			}
//		});
//		tempPanel.add(OKButton);

		final JButton allLabel = new JButton("  导出成绩单");
		allLabel.setFont(new Font("楷体",Font.PLAIN,18));
		allLabel.setBounds(410, 240, 140, 29);
//		ImageIcon icon = new ImageIcon("image/全科成绩.jpg");
//		allLabel.setIcon(icon);
		allLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<ScoreVO> voList = new ArrayList<ScoreVO>();
				ArrayList<String> courseNOList = new ArrayList<String>();

				for (int i = 0; i < myCourse.size(); i++) {
					courseNOList.add(myCourse.get(i).getCoID());
				}

				try {
					ScoreBLService scq = factory.getSCQService();
					voList = scq.checkStuScore(stuNO, courseNOList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String[] title = { "课程号", "课程名", "成绩" };
				//ArrayList
				String[][] info = new String[voList.size()][3];
				for (int i = 0; i < voList.size(); i++) {
					ScoreVO scorevo = voList.get(i);
					info[i][0] = scorevo.getCourseNO();
					info[i][1] = myCourse.get(i).getCoName();
					if(scorevo.getScore() == -1){
						info[i][2] = "无";
					}else{
						info[i][2] = scorevo.getScore()+"";
					}
				}
				
				CreateSimpleExcelToDisk createExcel = null;
				String[] studentInfo;
				try {
					createExcel=new CreateSimpleExcelToDisk(grade,title,info);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(createExcel==null)
						JOptionPane.showMessageDialog(null, "导出失败", "Default", JOptionPane.ERROR_MESSAGE); 
					else
						JOptionPane.showMessageDialog(null, "导出成功", "Success", JOptionPane.DEFAULT_OPTION);
				}

//				
//				JFrame frame = new JFrame("全科成绩");	
//
//				JTable table = new JTable(info, title);
//				JScrollPane scrollPane = new JScrollPane(table);
//				scrollPane
//						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//				scrollPane.setBounds(0, 0, 500, 350);
//				frame.getContentPane().add(scrollPane);
//				frame.setBounds(410, 215, 510, 360);
//				frame.setVisible(true);s
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				allLabel.setFont(new Font("楷体",Font.PLAIN,20));
				allLabel.setText("导出成绩单");
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				allLabel.setFont(new Font("楷体",Font.PLAIN,18));
				allLabel.setText("导出成绩单");
				setCursor(Cursor.getDefaultCursor());
			}

		});
		tempPanel.add(allLabel);

//		JLabel lb = new JLabel("该门课成绩");
//		lb.setFont(new Font("楷体", Font.PLAIN, 17));
////		lb.setForeground(new Color(50, 205, 50));
//		lb.setBounds(160, 245, 100, 25);
//		tempPanel.add(lb);
//
//		textField = new JTextField();
//		textField.setBounds(250, 245, 75, 25);
//		textField.setColumns(10);
//		textField.setEditable(false);
//		tempPanel.add(textField);

		JButton checkButton = new JButton("确定");
		checkButton.setFont(new Font("楷体", Font.PLAIN, 14));
//		checkButton.setForeground(Color.BLUE);
		checkButton.setBounds(430, 10, 80, 30);
		
		String selectedSemester = (jc.getSelectedIndex() + 1) + "";
	
		try {
			CourseSelectionBLService csq = factory.getCSQService();
			myCourse = csq.checkMyCourse(stuNO, selectedSemester,
					"true");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (myCourse.size() > 0) {
			tempPanel.remove(jp5scrollPane);
			StuTransformMethod trans = new StuTransformMethod();
			Object[][] selectedCourse = trans.scoreTransform(myCourse,stuNO);

			jp5table = new JTable(selectedCourse, jp5title);

			jp5scrollPane = new JScrollPane(jp5table);
			jp5scrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp5scrollPane.setBounds(0, 0, 580, 220);
			tempPanel.add(jp5scrollPane);
		} else {
			JOptionPane.showMessageDialog(null, "尚未选中该学期课程！");
		}
		
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedSemester = (jc.getSelectedIndex() + 1);
				grade=semester[selectedSemester-1];

				try {
					CourseSelectionBLService csq = factory.getCSQService();
					myCourse = csq.checkMyCourse(stuNO, selectedSemester+"",
							"true");
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (myCourse.size() > 0) {
					tempPanel.remove(jp5scrollPane);
					StuTransformMethod trans = new StuTransformMethod();
					Object[][] selectedCourse = trans.scoreTransform(myCourse,stuNO);

					jp5table = new JTable(selectedCourse, jp5title);

					jp5scrollPane = new JScrollPane(jp5table);
					jp5scrollPane
							.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp5scrollPane.setBounds(0, 0, 580, 220);
					tempPanel.add(jp5scrollPane);
				} else {
					JOptionPane.showMessageDialog(null, "尚未选中该学期课程！");
				}

			}

		});
		add(checkButton);

		add(tempPanel);
	}
	
	
}
