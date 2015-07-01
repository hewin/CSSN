package presentation.courseselectionui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;

import vo.course.CourseVO;
import vo.user.UserVO;

public class JWCourseSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JWCourseSelectionPanel() {
		super();
		setLayout(null);
		initPanel();
	}
	
	public void initPanel(){
		removeAll();
		
		//消息提示框
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("楷体", Font.PLAIN, 18));
		
		ImageIcon image1 = new ImageIcon("image/选课时间.PNG");
		final JLabel jl1 = new JLabel(image1);
		jl1.setBounds(100, 2, 30, 20);
		add(jl1);
		
		final JLabel jp5jb1 = new JLabel("选课时间");
		jp5jb1.setFont(new Font("楷体", Font.PLAIN, 16));
		jp5jb1.setForeground(Color.BLACK);
		jp5jb1.setBounds(130, 5, 80, 15);
		add(jp5jb1);
		
		ImageIcon image2 = new ImageIcon("image/抽签.PNG");
		final JLabel jl2 = new JLabel(image2);
		jl2.setBounds(230, -2, 30, 30);
		add(jl2);
 
		final JLabel jp5jb2 = new JLabel("抽签");
		jp5jb2.setFont(new Font("楷体", Font.PLAIN, 16));
		jp5jb2.setForeground(Color.BLACK);
		jp5jb2.setBounds(260, 5, 50, 15);
		add(jp5jb2);
	    
		//----------------选课时间----------------------
		final JLabel date1 = new JLabel("开始时间:");
		date1.setFont(new Font("楷体", Font.PLAIN, 18));
		date1.setBounds(140, 105, 100, 25);
		add(date1);

		final JXDatePicker timePicker1 = new JXDatePicker();
		timePicker1.setFont(new Font("楷体", Font.PLAIN, 18));
		timePicker1.getEditor().setText("YY-MM-DD");
		timePicker1.setBounds(225, 105, 160, 30);
		add(timePicker1);

		final JLabel date2 = new JLabel("结束时间:");
		date2.setFont(new Font("楷体", Font.PLAIN, 18));
		date2.setBounds(140, 175, 100, 25);
		add(date2);

		final JXDatePicker timePicker2 = new JXDatePicker();
		timePicker2.setFont(new Font("楷体", Font.PLAIN, 18));
		timePicker2.getEditor().setText("YY-MM-DD");
		timePicker2.setBounds(225, 175, 160, 30);
		add(timePicker2);
		
		final JButton jp5jb = new JButton("确定");
		jp5jb.setBounds(245, 290, 78, 35);
		jp5jb.setFont(new Font("楷体", Font.PLAIN, 18));
		add(jp5jb);
		
		jp5jb1.addMouseListener(new MouseAdapter() { // 选课时间

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp5jb1.setFont(new Font("楷体", Font.BOLD, 18));
				jp5jb2.setFont(new Font("楷体", Font.PLAIN, 16));
				add(date1);
				add(timePicker1);
				add(date2);
				add(timePicker2);
				add(jp5jb);
				add(jl1);
				add(jl2);
				add(jp5jb1);
				add(jp5jb2);
				repaint();
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}
		});
		
		jp5jb.addActionListener(new ActionListener() { // 确定

			public void actionPerformed(ActionEvent e) {
				String begin = timePicker1.getEditor().getText();
				String end = timePicker2.getEditor().getText();
				if(!begin.equals("YY-MM-DD")&&!end.equals("YY-MM-DD")
						&&!begin.equals("")&&!end.equals("")){
					RMIFactory factory = Client.getFactory();
					try {
						CourseSelectionBLService fqservice = factory.getCSQService();
						int result = fqservice.publishChooseCourseTime(begin, end);
						switch(result) {
						case 1:  jdjll.setText("选课时间保存完毕！"); break;
						case -1: jdjll.setText("开始时间晚于结束时间，请重新选择！"); break;
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				else{
		            if(begin.equals("YY-MM-DD")||begin.equals(""))
		        	    jdjll.setText("请填写开始时间！");
		            else if(end.equals("YY-MM-DD")||end.equals(""))
		        	    jdjll.setText("请填写结束时间！");        	
		        }
		        JOptionPane.showMessageDialog(null, jdjll, "系统信息",JOptionPane.INFORMATION_MESSAGE);
		}

	});
		//------------------抽签第一页----------------------
		final JLabel jp5jl2 = new JLabel("最大年级");
		jp5jl2.setBounds(17, 30, 200, 30);
		jp5jl2.setFont(new Font("楷体", Font.PLAIN, 18));

		String[] type2 = { "2006", "2007", "2008", "2009","2010"};
		final JComboBox<String> jp5jc2 = new JComboBox<String>(type2);
		jp5jc2.setBounds(107, 30, 100, 30);
		jp5jc2.setFont(new Font("楷体", Font.PLAIN, 18));
			
		final JLabel jp5jl1 = new JLabel("课程性质");
		jp5jl1.setBounds(245, 30, 100, 30);
		jp5jl1.setFont(new Font("楷体", Font.PLAIN, 18));

		String[] type1 = { "通识通修", "开放选修" };
		final JComboBox<String> jp5jc1 = new JComboBox<String>(type1);
		jp5jc1.setBounds(322, 30, 130, 30);
		jp5jc1.setFont(new Font("楷体", Font.PLAIN, 18));
	
		final JButton jp5jl11 = new JButton("确定");
		jp5jl11.setBounds(490, 30, 80, 30);
		jp5jl11.setFont(new Font("楷体", Font.PLAIN, 18));
		
		final JButton jp5jb3 = new JButton("开始抽签");
		jp5jb3.setBounds(250, 290, 100, 35);
		jp5jb3.setFont(new Font("楷体", Font.PLAIN, 18));
		
		String[] jp5title1 = {"课程号","课程名称"};
		final Object[][] jp5info1 =  null;
		DefaultTableModel jp1deftbmd1 = new DefaultTableModel(jp5info1, jp5title1);
		
		final JTable jp5table1 = new JTable(jp1deftbmd1){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp5table1.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
		jp5table1.setFont(new Font("楷体", Font.PLAIN, 17));
		jp5table1.getColumn("课程名称").setMinWidth(150);   //设置列宽
		
		final DefaultTableModel model1 =(DefaultTableModel) jp5table1.getModel();
		
		jp5jl11.addActionListener(new ActionListener() { // 确定

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jp5jl1);
				add(jp5jc1);
				add(jp5jl2);
				add(jp5jc2);
				add(jl1);
				add(jl2);
				add(jp5jb1);
				add(jp5jb2);
				add(jp5jb3);
				add(jp5jl11);  
				
				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				}
				String module = (String) jp5jc1.getSelectedItem();
				
				try {

					CourseBLService cqservice = factory.getCQService();
					ArrayList<CourseVO> courseList = cqservice.getModuleCompletedCourseList(module);
					ArrayList<CourseVO> acourseList = new ArrayList<CourseVO>();
					for(int i=0;i<courseList.size();i++){
						if(!courseList.get(i).getIsCompulsory()){
							acourseList.add(courseList.get(i));
						}
					}
					for(int i=0;i<acourseList.size();i++) {
						CourseVO course = acourseList.get(i);
						String[] row = {course.getCoID(), course.getCoName()};
						model1.addRow(row);
					}
					
					final JScrollPane jp5scrollPane1 = new JScrollPane(jp5table1);
					jp5scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp5scrollPane1.setBounds(15, 70, 560, 200);
					
					add(jp5scrollPane1);
					
					repaint();			
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});	
		
		jp5jb2.addMouseListener(new MouseAdapter() { // 抽签

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp5jb1.setFont(new Font("楷体", Font.PLAIN, 16));
				jp5jb2.setFont(new Font("楷体", Font.BOLD, 18));
				add(jp5jl1);
				add(jp5jc1);
				add(jp5jl2);
				add(jp5jc2);
				add(jl1);
				add(jl2);
				add(jp5jb1);
				add(jp5jb2);
				add(jp5jb3);
				add(jp5jl11);
				
			    final JScrollPane jp5scrollPane1 = new JScrollPane(jp5table1);
				jp5scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp5scrollPane1.setBounds(15, 70, 560, 200);
				add(jp5scrollPane1);
				
				repaint();
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}
		
		});
		
		//------------------抽签第二页----------------------			
		String[] jp5title2 = {"学号", "姓名" };
		final Object[][] jp5info2 = null;
		DefaultTableModel jp1deftbmd2 = new DefaultTableModel(
				jp5info2, jp5title2);
		final JTable jp5table2 = new JTable(jp1deftbmd2){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp5table2.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 17));
		jp5table2.setFont(new Font("楷体", Font.PLAIN, 17));
		
		final DefaultTableModel model2 =(DefaultTableModel) jp5table2.getModel();
		
		final JButton jp5jb4 = new JButton("返回");
		jp5jb4.setFont(new Font("楷体", Font.PLAIN, 18));
		jp5jb4.setBounds(400, 290, 80, 35);
		

		jp5jb3.addActionListener(new ActionListener() { // 开始抽签

			public void actionPerformed(ActionEvent e) {	
				removeAll();
				add(jl1);
				add(jl2);
				add(jp5jb1);
				add(jp5jb2);
				
				int row = jp5table1.getSelectedRow();
				if(row>=0){
					
					String courseNO = (String)jp5table1.getValueAt(row, 0);
					String maxGrade = (String)jp5jc2.getSelectedItem();
					
					RMIFactory factory = Client.getFactory();
					while(model2.getRowCount()>0){
					      model2.removeRow(model2.getRowCount()-1);
					}
					
					try {
						CourseSelectionBLService csqservice = factory.getCSQService();
						ArrayList<UserVO> studentList = csqservice.allocate(courseNO, maxGrade);
						
						if(studentList.size()>0){
						
						    for(int i=0;i<studentList.size();i++){
						       String[] row2 = {studentList.get(i).getIdNum(), studentList.get(i).getUserName()};
						       model2.addRow(row2);
						    }
						
						    final JScrollPane jp5scrollPane2 = new JScrollPane(jp5table2);
						    jp5scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						    jp5scrollPane2.setBounds(15, 25, 560, 250);
					        add(jp5scrollPane2);
					        add(jp5jb4);
					        
					        CourseBLService cservice = factory.getCQService();
					        String courseName = cservice.checkAnyCourseInfo(courseNO).getCoName();
					        final JLabel jp5jl12 = new JLabel(courseName+"抽签选中的学生列表");
							jp5jl12.setBounds(150, 290, 230,35);
							jp5jl12.setFont(new Font("楷体", Font.PLAIN, 16));
					        
					        add(jp5jl12);
						}else{
							
							removeAll();
							add(jp5jl1);
							add(jp5jc1);
							add(jp5jl2);
							add(jp5jc2);
							add(jl1);
							add(jl2);
							add(jp5jb1);
							add(jp5jb2);
							add(jp5jl11);
							
							final JScrollPane jp5scrollPane1 = new JScrollPane(jp5table1);
							jp5scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
							jp5scrollPane1.setBounds(15, 70, 560, 200);
							
							add(jp5scrollPane1);
							add(jp5jb3);
							
							jdjll.setText("该课程学生尚未选课，不可抽签！");
							JOptionPane.showMessageDialog(null, jdjll, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
				    
				}else{					
					removeAll();
					add(jp5jl1);
					add(jp5jc1);
					add(jp5jl2);
					add(jp5jc2);
					add(jl1);
					add(jl2);
					add(jp5jb1);
					add(jp5jb2);
					add(jp5jl11);
					
					final JScrollPane jp5scrollPane1 = new JScrollPane(jp5table1);
					jp5scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp5scrollPane1.setBounds(15, 70, 560, 200);
					
					add(jp5scrollPane1);
					add(jp5jb3);
					
					jdjll.setText("请点击完确定后选择一门课程再抽签！");
					JOptionPane.showMessageDialog(null, jdjll, "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
				}
				repaint();
			}
		});
	
		jp5jb4.addActionListener(new ActionListener() { // 返回

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jp5jl1);
				add(jp5jc1);
				add(jp5jl2);
				add(jp5jc2);
				add(jl1);
				add(jl2);
				add(jp5jb1);
				add(jp5jb2);
				add(jp5jl11);
				
				final JScrollPane jp5scrollPane1 = new JScrollPane(jp5table1);
				jp5scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp5scrollPane1.setBounds(15, 70, 560, 200);
				
				add(jp5scrollPane1);
				add(jp5jb3);
				repaint();
			}
		});
	}

}
