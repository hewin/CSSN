package presentation.statisticsui;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import Util.CreateSimpleExcelToDisk;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import businesslogicservice.userblservice.UserBLService;
import vo.course.CourseVO;
import vo.statistics.StatisticsVO;
import vo.user.UserVO;

public class JWStatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JWStatisticsPanel() {
		super();
		setLayout(null);
		initPanel();
	}
	
	public void initPanel(){
		removeAll();
		
		ImageIcon image1 = new ImageIcon("image/��ʦͳ��.PNG");
		final JLabel jl1 = new JLabel(image1);
		jl1.setBounds(100, -5, 30, 30);
		add(jl1);
		
		final JLabel jp3jb1 = new JLabel("��ʦͳ��");
		jp3jb1.setFont(new Font("����", Font.PLAIN, 16));
		jp3jb1.setForeground(Color.BLACK);
		jp3jb1.setBounds(130, 5, 80, 15);
		add(jp3jb1);
		
		ImageIcon image2 = new ImageIcon("image/ѧ�����.PNG");
		final JLabel jl2 = new JLabel(image2);
		jl2.setBounds(230, 2, 30, 20);
		add(jl2);
		
		final JLabel jp3jb2 = new JLabel("ѧ�����");
		jp3jb2.setFont(new Font("����", Font.PLAIN, 16));
		jp3jb2.setForeground(Color.BLACK);
		jp3jb2.setBounds(260, 5, 80, 15);
		add(jp3jb2);
		
		ImageIcon image3 = new ImageIcon("image/�γ�ͳ��.PNG");
		final JLabel jl3 = new JLabel(image3);
		jl3.setBounds(360, 2, 30, 20);
		add(jl3);
		
		final JLabel jp3jb3 = new JLabel("�γ�ͳ��");
		jp3jb3.setFont(new Font("����", Font.PLAIN, 16));
		jp3jb3.setForeground(Color.BLACK);
		jp3jb3.setBounds(390, 5, 80, 15);
		add(jp3jb3);
		
		//��Ϣ��ʾ��
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("����", Font.PLAIN, 18));
		
		// -----------------------��ʦͳ��----------------------
		//��ʦ�б�
		final JButton jp3jb11 = new JButton("ѡ���ʦ");
		jp3jb11.setBounds(250, 290, 100, 35);
		jp3jb11.setFont(new Font("����", Font.PLAIN, 18));
		
		String[] jp3title1 = {"��ʦ����","����"};
		final Object[][] jp3info1 = null;
		DefaultTableModel jp3title1tbmd1 = new DefaultTableModel(
				jp3info1, jp3title1);
		final JTable jp3table1 = new JTable(jp3title1tbmd1){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3table1.getTableHeader().setFont(new Font("����", Font.PLAIN, 17));
		jp3table1.setFont(new Font("����", Font.PLAIN, 16));
		final DefaultTableModel model1 =(DefaultTableModel) jp3table1.getModel();
		
    	final JScrollPane jp3scrollPane1 = new JScrollPane(jp3table1);
		jp3scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp3scrollPane1.setBounds(15, 25, 560, 250);
		add(jp3scrollPane1);
		
		jp3jb1.addMouseListener(new MouseAdapter() {//��ʦͳ��

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp3jb1.setFont(new Font("����", Font.BOLD, 18));
				jp3jb2.setFont(new Font("����", Font.PLAIN, 16));
				jp3jb3.setFont(new Font("����", Font.PLAIN, 16));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				add(jp3jb11);
				
				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				}
				
				try{
					UserBLService uqservice = factory.getUQService();
					ArrayList<UserVO> teacherList = uqservice.getAllTeacher();
					for(int i=0;i<teacherList.size();i++){
						UserVO teacher = teacherList.get(i);
						
						String[] row = {teacher.getIdNum(),teacher.getUserName()};
						model1.addRow(row);
					}
						
						final JScrollPane jp3scrollPane1 = new JScrollPane(jp3table1);
						jp3scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						jp3scrollPane1.setBounds(15, 25, 560, 250);
											
						add(jp3scrollPane1);
						repaint();
			
				}catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
			
			public void mouseEntered(MouseEvent arg0) {;
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}

		});

		String[] jp3title2 = {"�γ̺�","�γ���",">90","80-90","70-80","60-70","<60"};
		final Object[][] jp3info2 = null;
		DefaultTableModel jp3title1tbmd2 = new DefaultTableModel(
				jp3info2, jp3title2);
		final JTable jp3table2 = new JTable(jp3title1tbmd2){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3table2.getColumn("�γ���").setMinWidth(130);   //�����п�
		jp3table2.getTableHeader().setFont(new Font("����", Font.PLAIN, 17));
		jp3table2.setFont(new Font("����", Font.PLAIN, 16));
		
		final DefaultTableModel model2 =(DefaultTableModel) jp3table2.getModel();
		
		final JButton jp3jbr1 = new JButton("����");
		jp3jbr1.setBounds(400, 290, 80, 35);
		jp3jbr1.setFont(new Font("����", Font.PLAIN, 18));
		
		jp3jb11.addActionListener(new ActionListener() { //ѡ���ʦ

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				
				RMIFactory factory = Client.getFactory();
				while(model2.getRowCount()>0){
				      model2.removeRow(model2.getRowCount()-1);
				}
				
				try {
					int row = jp3table1.getSelectedRow();
				    if(row>=0){  
					   StatisticsBLService stqservice = factory.getSTQService();
					   String teacherId = (String)jp3table1.getValueAt(row, 0);
				       ArrayList<StatisticsVO> teacherStatistics = stqservice.checkTeacherStatistics(teacherId);
					
				       for(int i=0;i<teacherStatistics.size();i++){
				    	   StatisticsVO statistics = teacherStatistics.get(i);
				    	   String[] row2 = {statistics.getCourseNo(),statistics.getCourseName(), statistics.getExcellent()+"",
				    			   statistics.getFine()+"", statistics.getMedium()+"",
				    			   statistics.getPass()+"", statistics.getFail()+""}; 
				    	   model2.addRow(row2);
				       }
				       
				       final JScrollPane jp3scrollPane2 = new JScrollPane(jp3table2);
					   jp3scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					   jp3scrollPane2.setBounds(15, 25, 560, 250);
					   add(jp3scrollPane2);
					   
					   UserBLService uqservice = factory.getUQService();
					   
					   final JLabel jp3jl11 = new JLabel(uqservice.getUserByIdNum(teacherId).getUserName()+"��ʦ���̿γ̵ķ����ηֲ���");
					   jp3jl11.setBounds(140, 285, 260,35);
					   jp3jl11.setFont(new Font("����", Font.PLAIN, 16));
					   
					   final JLabel jp3jl12 = new JLabel("˫�����鿴��״ͼ");
					   jp3jl12.setBounds(140, 310, 260,35);
					   jp3jl12.setFont(new Font("����", Font.PLAIN, 16));
						
					   add(jp3jl11);
					   add(jp3jl12);
					   add(jp3jbr1);
				   }else{
					   add(jp3jb11);
					   
					   final JScrollPane jp3scrollPane1 = new JScrollPane(jp3table1);
					   jp3scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					   jp3scrollPane1.setBounds(15, 25, 560, 250);
											
					   add(jp3scrollPane1);
					   
					   jdjll.setText("��δѡ���ʦ��");
					   JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
				   }	
				   repaint();
				
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		
		jp3table2.addMouseListener(new MouseAdapter(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(e.getClickCount()==2){
					try {
					    int row = jp3table1.getSelectedRow();	
					    RMIFactory factory = Client.getFactory();
				        if(row>=0){  
					       StatisticsBLService stqservice = factory.getSTQService();
					       String teacherId = (String)jp3table1.getValueAt(row, 0);
				           ArrayList<StatisticsVO> teacherStatistics = stqservice.checkTeacherStatistics(teacherId);
			
				           int row2 = jp3table2.getSelectedRow();
				           StatisticsVO statistics = teacherStatistics.get(row2);
				           String[] row1 = {statistics.getCourseNo(),statistics.getCourseName(), statistics.getExcellent()+"",
				    			   statistics.getFine()+"", statistics.getMedium()+"",
				    			   statistics.getPass()+"", statistics.getFail()+""}; 
				   		   
					      UserBLService uqservice = factory.getUQService();
					      String teacherName = uqservice.getUserByIdNum(teacherId).getUserName();
					   
						  StandardChartTheme sct = new StandardChartTheme("CN");
						  sct.setExtraLargeFont(new Font("����", Font.BOLD, 20));
						  sct.setRegularFont(new Font("����", Font.BOLD, 20));
						  sct.setLargeFont(new Font("����", Font.BOLD, 20));
						  DefaultPieDataset dataset = new DefaultPieDataset();
						  dataset.setValue(">90", Integer.parseInt(row1[2]));
						  dataset.setValue("80-90",Integer.parseInt(row1[3]));
						  dataset.setValue("70-80", Integer.parseInt(row1[4]));
						  dataset.setValue("60-70", Integer.parseInt(row1[5]));
						  dataset.setValue("<60", Integer.parseInt(row1[6]));
						  ChartFactory.setChartTheme(sct);
						  JFreeChart jfreechart = ChartFactory.createPieChart3D(teacherName+"��ʦ����"+row1[1]+"�ε�ѧ�������ηֲ�", dataset,
						  true, true, true);
						  ChartFrame frame = new ChartFrame("��ʦͳ�Ʊ���", jfreechart);
						  frame.setVisible(true);
						  frame.setLocation(300, 120);
						  frame.pack();
				    }   
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
				}
			 }
		});
		
		jp3jbr1.addMouseListener(new MouseAdapter() { // ���ؽ�ʦͳ��

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				add(jp3jb11);
				
				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				}
				
				try{
					UserBLService uqservice = factory.getUQService();
					ArrayList<UserVO> teacherList = uqservice.getAllTeacher();
					for(int i=0;i<teacherList.size();i++){
						UserVO teacher = teacherList.get(i);
						
						String[] row = {teacher.getIdNum(),teacher.getUserName()};
						model1.addRow(row);
					}
						
						final JScrollPane jp3scrollPane1 = new JScrollPane(jp3table1);
						jp3scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						jp3scrollPane1.setBounds(15, 25, 560, 250);
											
						add(jp3scrollPane1);
						repaint();
			
				}catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		// -----------------------ѧ�����----------------------
		//Ժϵ�б�
		String[] depList = {"���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
				"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
				"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
				"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "��ѧϵ","��ʷѧԺ","������ѧѧԺ"};
		DefaultTableModel jp3deftbmd1 = new DefaultTableModel();
		jp3deftbmd1.addColumn("Ժϵ�б�", depList);
		final JTable jp3jt1 = new JTable(jp3deftbmd1){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3jt1.setFont(new Font("����", Font.PLAIN, 18));
		jp3jt1.getTableHeader().setFont(new Font("����", Font.PLAIN, 20));

		final JScrollPane jp3jsp1 = new JScrollPane(jp3jt1);
		jp3jsp1.setBounds(15, 25, 300, 302);
		
		final JLabel jp3jl1 = new JLabel("���б���ѡ��Ҫ��");
		jp3jl1.setBounds(355, 150, 200, 50);
		jp3jl1.setFont(new Font("����", Font.PLAIN, 18));
		jp3jl1.setForeground(Color.BLACK);

		final JLabel jp3jl2 = new JLabel("����Ժϵ���ٵ��");
		jp3jl2.setForeground(Color.BLACK);
		jp3jl2.setBounds(355, 190, 200, 50);
		jp3jl2.setFont(new Font("����", Font.PLAIN, 18));
		
		final JButton jp3jbc1 = new JButton("�鿴");
		jp3jbc1.setBounds(400, 290, 80, 35);
		jp3jbc1.setFont(new Font("����", Font.PLAIN, 18));
		
		jp3jb2.addMouseListener(new MouseAdapter() { //ѧ�����

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp3jb1.setFont(new Font("����", Font.PLAIN, 16));
				jp3jb2.setFont(new Font("����", Font.BOLD, 18));
				jp3jb3.setFont(new Font("����", Font.PLAIN, 16));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				add(jp3jsp1);
				add(jp3jl1);
				add(jp3jl2);
				add(jp3jbc1);
				repaint();
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}

		});
		
		String[] jp3title3 = {"ѧ��","����","רҵ����","����ѡ��","ͨ�޿γ�","ͨʶ����","���ϸ��Ŀ"};
		final Object[][] jp3info3 = null;
		DefaultTableModel jp1deftbmd3 = new DefaultTableModel(jp3info3, jp3title3);
		final JTable jp3table3 = new JTable(jp1deftbmd3){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3table3.getTableHeader().setFont(new Font("����", Font.PLAIN, 13));
		jp3table3.setFont(new Font("����", Font.PLAIN, 13));
		final DefaultTableModel model3 =(DefaultTableModel) jp3table3.getModel();
				
		final JButton jp3jbr2 = new JButton("����");
		jp3jbr2.setBounds(400, 290, 80, 35);
		jp3jbr2.setFont(new Font("����", Font.PLAIN, 18));
		
		jp3jbc1.addActionListener(new ActionListener() {//ѧ����˲鿴

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				try {
				    int row = jp3jt1.getSelectedRow();
				    if(row>=0){
					    RMIFactory factory = Client.getFactory();
					    while(model3.getRowCount()>0){
						    model3.removeRow(model3.getRowCount()-1);
					    }
									
						UserBLService uqservice = factory.getUQService();
					    StatisticsBLService stqservice = factory.getSTQService();
						
						String institution = (String)jp3jt1.getValueAt(row, 0);
						
						ArrayList<UserVO> studentList = uqservice.getYXStuID(institution);
				
						for(int i=0;i<studentList.size();i++){
						
						    UserVO student = studentList.get(i);
						    StatisticsVO sta = stqservice.checkStuStatistics(student.getIdNum());
						  
						    int majorCredit = sta.getMajorCredit();// ѧ��רҵģ���޵�ѧ��							
							int openCredit = sta.getOpenCredit();  // ����ѡ��ģ���޵�ѧ��							
							int commonCredit = sta.getCommonCredit();// ͨ�޿γ�,����΢���ֵ�							
							int liberalEducation = sta.getLiberalEducation();   // ͨʶ����14��ѧ��
							ArrayList<CourseVO> failList = sta.getFailList();   // �ҿ��б�
							
							//"ѧ��","����","רҵ����","����ѡ��","ͨ�޿γ�","ͨʶ����","���ϸ��Ŀ"
							String[] row3 = {student.getIdNum(), student.getUserName(), majorCredit+"", 
									openCredit+"", commonCredit+"", liberalEducation+"", failList.size()+""};
						
							model3.addRow(row3);
						}
						
						final JScrollPane jp3scrollPane3 = new JScrollPane(jp3table3);
						jp3scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						jp3scrollPane3.setBounds(15, 25, 560, 250);
						
						final JLabel jp3jl12 = new JLabel(institution+"ѧ����ģ��ѧ�ֲַ���");
						jp3jl12.setBounds(140, 290, 270,35);
						jp3jl12.setFont(new Font("����", Font.PLAIN, 16));
						
						add(jp3scrollPane3);
						add(jp3jl12);
						add(jp3jbr2);
				    }else{
				    	add(jp3jsp1);
					    add(jp3jl1);
					    add(jp3jl2);
					    add(jp3jbc1);
					    jdjll.setText("��δѡ��Ժϵ��");
					    JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
							   JOptionPane.INFORMATION_MESSAGE);
				    }
				    repaint();
				    
			    } catch (RemoteException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				}	
			}

		});
		
		jp3jbr2.addActionListener(new ActionListener() { //����ѧ�����

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				add(jp3jsp1);
				add(jp3jl1);
				add(jp3jl2);
				add(jp3jbc1);
				repaint();
			}

		});
		
		// -----------------------�γ�ͳ��----------------------
		final JButton jp3jb13 = new JButton("�������");
		jp3jb13.setBounds(400, 290, 100, 35);
		jp3jb13.setFont(new Font("����", Font.PLAIN, 18));
		
		//"�γ̺�","�γ���"
		String[] jp3title4 = {"�γ̺�","�γ���","ѡ������"};  //��ѡ��ѧ����
		final Object[][] jp3info4 = null;
		DefaultTableModel jp1deftbmd4 = new DefaultTableModel(
				jp3info4, jp3title4);
		final JTable jp3table4 = new JTable(jp1deftbmd4){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp3table4.getTableHeader().setFont(new Font("����", Font.PLAIN, 17));
		jp3table4.setFont(new Font("����", Font.PLAIN, 16));
		final DefaultTableModel model4 =(DefaultTableModel) jp3table4.getModel();
		
	
		jp3jb3.addMouseListener(new MouseAdapter()  { //�γ�ͳ��

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp3jb1.setFont(new Font("����", Font.PLAIN, 16));
				jp3jb2.setFont(new Font("����", Font.PLAIN, 16));
				jp3jb3.setFont(new Font("����", Font.BOLD, 18));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp3jb1);
				add(jp3jb2);
				add(jp3jb3);
				add(jp3jb13);
				RMIFactory factory = Client.getFactory();
				while(model4.getRowCount()>0){
				      model4.removeRow(model4.getRowCount()-1);
				}
				
				try {
					//����CQService������ʦ�鿴���пγ��б��γ̺ţ��γ�����
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
//							jdjll.setText("ѡ��ÿγ̵�ѧ����Ϊ"+stuNo+".");
						} catch (RemoteException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
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
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
			    }
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}

		});
		
		jp3jb13.addMouseListener(new MouseAdapter()  { //�γ�ͳ��

			public void mouseClicked(MouseEvent arg0) {
				String[][] courseInfo;
				RMIFactory factory = Client.getFactory();
				while(model4.getRowCount()>0){
				      model4.removeRow(model4.getRowCount()-1);
				}
				
				try {
					//����CQService������ʦ�鿴���пγ��б��γ̺ţ��γ�����
			    	CourseBLService cqservice = factory.getCQService();
			    	ArrayList<CourseVO> allCourseList = cqservice.getAllCourseList();
			    	courseInfo=new String[allCourseList.size()][3];
			    	for(int i=0;i<allCourseList.size();i++){
			
			    		CourseVO course = allCourseList.get(i);
			    		
			    		RMIFactory factory1 = Client.getFactory();
			    		int stuNo=i%4;
						try {
							StatisticsBLService stqservice = factory1.getSTQService();
							String courseNo = course.getCoID();
							//StatisticsVO stVO = stqservice.checkCourseStatistics(courseNo);
							
							// stuNo= stVO.getNum();
//							jdjll.setText("ѡ��ÿγ̵�ѧ����Ϊ"+stuNo+".");
						} catch (RemoteException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						}
						courseInfo[i][0]=course.getCoID();
						courseInfo[i][1]=course.getCoName();
						courseInfo[i][2]=stuNo+"";
			    		//String[] row = {course.getCoID(),course.getCoName(),stuNo+""};
			    		//courseInfo[i]=row;
			    		//System.out.println(course.getCoName());
			    		//model4.addRow(row);
			    	}
			    	String[] title={"�γ̺�","�γ���","ѡ������"};
					try {
						CreateSimpleExcelToDisk export=new CreateSimpleExcelToDisk("�γ�ͳ��",title,courseInfo);
						JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
			    }
				
			}
		
		});
		
	}
	
}
