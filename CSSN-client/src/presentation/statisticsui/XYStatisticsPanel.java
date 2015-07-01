package presentation.statisticsui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.course.CourseVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;

public class XYStatisticsPanel extends JPanel{
	public XYStatisticsPanel() {
		super();
		setLayout(null);
		initPanel();
	}
	
	public void initPanel(){	
		
		String[] jp3title1 = {"教师工号","姓名"};
		final Object[][] jp3info1 = null;
		DefaultTableModel jp3title1tbmd1 = new DefaultTableModel(
				jp3info1, jp3title1);
		final JTable jp3table1 = new JTable(jp3title1tbmd1){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3table1.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
		jp3table1.setFont(new Font("楷体", Font.PLAIN, 16));
		final DefaultTableModel model1 =(DefaultTableModel) jp3table1.getModel();
		
    	final JScrollPane jp3scrollPane1 = new JScrollPane(jp3table1);
		jp3scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp3scrollPane1.setBounds(15, 25, 560, 250);
		add(jp3scrollPane1);
		jp3scrollPane1.setVisible(true);
		
		
		//课程统计
		//"课程号","课程名"
				String[] jp3title4 = {"课程号","课程名","选课人数"};  //得选课学生数
				final Object[][] jp3info4 = null;
				DefaultTableModel jp1deftbmd4 = new DefaultTableModel(
						jp3info4, jp3title4);
				final JTable jp3table4 = new JTable(jp1deftbmd4){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int r,int c){
					    return false;
				    }
				};
				jp3table4.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
				jp3table4.setFont(new Font("楷体", Font.PLAIN, 16));
				final DefaultTableModel model4 =(DefaultTableModel) jp3table4.getModel();
		
				
				removeAll();
		
				RMIFactory factory = Client.getFactory();
				while(model4.getRowCount()>0){
				      model4.removeRow(model4.getRowCount()-1);
				}
				
				try {
					//调用CQService教务处老师查看所有课程列表（课程号，课程名）
			    	CourseBLService cqservice = factory.getCQService();
			    	ArrayList<CourseVO> allCourseList = cqservice.getAllCourseList();
			    	
			    	for(int i=0;i<allCourseList.size();i++){
			
			    		CourseVO course = allCourseList.get(i);
			    		
			    		RMIFactory factory1 = Client.getFactory();
			    		int stuNo=i%4;
						try {
							StatisticsBLService stqservice = factory1.getSTQService();
							String courseNo = course.getCoID();
							//StatisticsVO stVO = stqservice.checkCourseStatistics(courseNo);
							
							 //stuNo= stVO.getNum();
// 							jdjll.setText("选择该课程的学生数为"+stuNo+".");
						} catch (RemoteException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
						}
					
			    		String[] row = {course.getCoID(),course.getCoName(),stuNo+""};
			    		//courseInfo[i]=row;
			    		//System.out.println(course.getCoName());
			    		model4.addRow(row);
			    	}
			
				    final JScrollPane jp3scrollPane4 = new JScrollPane(jp3table4);
				    jp3scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				    jp3scrollPane4.setBounds(15, 25, 560, 250);
				
				    add(jp3scrollPane4);
				    repaint();			
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
			    }
		}
			
			
		
}
	

