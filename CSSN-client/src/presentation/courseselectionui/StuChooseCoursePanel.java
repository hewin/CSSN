package presentation.courseselectionui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import presentation.courseui.StuTransformMethod;
import vo.course.CourseVO;
import vo.user.UserVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.userblservice.UserBLService;

public class StuChooseCoursePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTable jp3table;
	JScrollPane jp3scrollPane;
	JPanel tsPanel = new JPanel();    //ͨʶ��Panel
	JPanel kzyPanel = new JPanel();   //��רҵѡ��Panel
	JPanel tyPanel = new JPanel();   //����ѡ��Panel
	JButton chooseButton = new JButton("ѡ��");
	static ArrayList<CourseVO> myTSCourse = new ArrayList<CourseVO>();
	static int num = 0;
	public StuChooseCoursePanel(final String stuNO) {
		final RMIFactory factory = Client.getFactory();
		setLayout(null);

		tsPanel.setVisible(false);
		tsPanel.setLayout(null);
		tsPanel.setBounds(0, 55, 580, 350);

		kzyPanel.setVisible(false);
		kzyPanel.setLayout(null);
		kzyPanel.setBounds(0, 55, 580, 350);

		tyPanel.setVisible(false);
		tyPanel.setLayout(null);
		tyPanel.setBounds(0, 55, 580, 350);

		drawEmptyTable(tsPanel);

		final String[] jp3title = {"���","�γ̺�","�γ�����","�γ�����","ѧ��","��ʦ","�Ͽεص�","�Ͽ�ʱ��"};

		JLabel label = new JLabel("�γ����ͣ�");
		//		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("����", Font.PLAIN, 16));
		label.setBounds(85, 20, 80, 20);
		add(label);

		class ChooseActionListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				int selectedNO = jp3table.getSelectedRow();
				if(selectedNO < 0){
					JOptionPane.showMessageDialog(null, "��ѡ��һ�ſγ̣�");
				}else{
					String courseNO = (String)jp3table.getModel().getValueAt(selectedNO, 1);
					String message = "";
					try{
						CourseBLService cq = factory.getCQService();
						final CourseVO cv = cq.checkAnyCourseInfo(courseNO);
						String module = cv.getModule();
						boolean isCompulsory = cv.getIsCompulsory();
						String teacherNO = cv.getTeaID();
						CourseSelectionBLService csq = factory.getCSQService();
						if(module.equals("ͨʶͨ��")&&courseNO.charAt(0) == 'P'&&courseNO.charAt(1) == 'E'){
							message = csq.choosePELesson(courseNO, stuNO, teacherNO);
						}else if(module.equals("ͨʶͨ��")&& (!isCompulsory)){
							message = csq.chooseCourse(courseNO, stuNO);
							
							if(message.equals("ѡ����ɣ�")){
								num ++;
								final JLabel course = new JLabel(cv.getCoName());
								int length = cv.getCoName().length();
								course.setBounds(10 + 130 * (num - 1), 280, length * 18, 30);
								course.setFont(new Font("�����п�",Font.BOLD,15));
								course.addMouseListener(new MouseAdapter(){

									@Override
									public void mouseClicked(MouseEvent arg0) {
										// TODO �Զ����ɵķ������
										JFrame frame = new JFrame("�γ���ϸ��Ϣ");
										frame.setResizable(false);
										JPanel panel = new JPanel();
										panel.setLayout(null);

										JLabel courseNOLabel = new JLabel("�γ̺ţ�");
										courseNOLabel.setFont(new Font("��Բ", Font.BOLD, 15));
										courseNOLabel.setBounds(43, 42, 66, 15);
										panel.add(courseNOLabel);

										JLabel lblCourseno = new JLabel(cv.getCoID());
										lblCourseno.setBounds(130, 42, 66, 15);
										panel.add(lblCourseno);

										JLabel courseName = new JLabel("�γ����ƣ�");
										courseName.setFont(new Font("��Բ", Font.BOLD, 15));
										courseName.setBounds(246, 42, 87, 15);
										panel.add(courseName);

										JLabel lblCoursename = new JLabel(cv.getCoName());
										lblCoursename.setBounds(355, 42, 96, 15);
										panel.add(lblCoursename);

										JLabel property = new JLabel("�γ����ʣ�");
										property.setFont(new Font("��Բ", Font.BOLD, 15));
										property.setBounds(43, 89, 80, 15);
										panel.add(property);

										boolean isCompulsory = cv.getIsCompulsory();
										JLabel lblIscompulsory;
										if (isCompulsory) {
											lblIscompulsory = new JLabel("����");
										} else {
											lblIscompulsory = new JLabel("ѡ��");
										}
										lblIscompulsory.setBounds(130, 89, 85, 15);
										panel.add(lblIscompulsory);

										JLabel credit = new JLabel("ѧ��:");
										credit.setFont(new Font("��Բ", Font.BOLD, 15));
										credit.setBounds(246, 89, 54, 15);
										panel.add(credit);

										JLabel lblCredit = new JLabel(cv.getCredit());
										lblCredit.setBounds(355, 89, 54, 15);
										panel.add(lblCredit);

										JLabel teacher = new JLabel("�ον�ʦ��");
										teacher.setFont(new Font("��Բ", Font.BOLD, 15));
										teacher.setBounds(43, 136, 80, 15);
										panel.add(teacher);

										JLabel lblTeacher = new JLabel(cv.getTeacher());
										lblTeacher.setBounds(130, 136, 54, 15);
										panel.add(lblTeacher);

										JLabel location = new JLabel("�Ͽεص㣺");
										location.setFont(new Font("��Բ", Font.BOLD, 15));
										location.setBounds(246, 136, 87, 15);
										panel.add(location);

										JLabel lblLocation = new JLabel(cv.getLocation());
										lblLocation.setBounds(355, 136, 54, 15);
										panel.add(lblLocation);

										JLabel time = new JLabel("�Ͽ�ʱ�䣺");
										time.setFont(new Font("��Բ", Font.BOLD, 15));
										time.setBounds(43, 183, 80, 15);
										panel.add(time);

										JLabel lblTime = new JLabel(cv.getTime());
										lblTime.setBounds(130, 183, 54, 15);
										panel.add(lblTime);

										JLabel description = new JLabel("�γ�������");
										description.setFont(new Font("��Բ", Font.BOLD, 15));
										description.setBounds(43, 230, 80, 15);
										panel.add(description);

										JTextArea textArea = new JTextArea();
										textArea.setBounds(130, 230, 298, 80);
										textArea.setText(cv.getDescription());
										textArea.setEditable(false);

										panel.add(textArea);
										frame.getContentPane().add(panel);
										frame.setBounds(400, 200, 550, 400);
										frame.setVisible(true);
									}

									@Override
									public void mouseEntered(MouseEvent arg0) {
										// TODO Auto-generated method stub
										course.setForeground(Color.orange);
										setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
									}

									@Override
									public void mouseExited(MouseEvent arg0) {
										// TODO Auto-generated method stub
										course.setForeground(Color.black);
										setCursor(Cursor.getDefaultCursor());
									}

								});
								repaint();
								tsPanel.add(course);
							}
						}else{
							message = csq.chooseCourse(courseNO,stuNO);
						}
					}catch(Exception e){
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, message);
				}
			}
		}

		//		chooseButton.setForeground(Color.black);
		chooseButton.setFont(new Font("����", Font.PLAIN, 16));
		chooseButton.addActionListener(new ChooseActionListener());

		final JLabel label_1 = new JLabel("ͨʶ���ֿ�");
		label_1.setFont(new Font("����", Font.PLAIN, 15));
//		label_1.setForeground(Color.black);
		label_1.setBounds(203, 20, 100, 20);
		label_1.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				kzyPanel.setVisible(false);
				tyPanel.setVisible(false);
		
				ArrayList<CourseVO> publicCourse = new ArrayList<CourseVO>();
				try{
					CourseBLService cq = factory.getCQService();
					publicCourse = cq.getModuleCompletedCourseList("ͨʶͨ��");

					for(int i = 0;i < publicCourse.size();i ++){
						String courseNO = publicCourse.get(i).getCoID();
						String semester = publicCourse.get(i).getSemester();
						if(publicCourse.get(i).getIsCompulsory()||!semester.equals("All")||(courseNO.charAt(0) == 'P'&&courseNO.charAt(1) == 'E')){
							publicCourse.remove(i);
							i --;
						}
					}

				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				drawEmptyTable(tsPanel);
				
				if(publicCourse.size() > 0){
					tsPanel.removeAll();
					tsPanel.setVisible(false);
					StuTransformMethod trans = new StuTransformMethod();

					Object[][] publicCourseList = trans.transform(publicCourse);

					jp3table = new JTable(publicCourseList, jp3title);
					jp3table.getColumn("���").setMinWidth(15); // �����п�
					jp3table.getColumn("�γ̺�").setMinWidth(40);    
					jp3table.getColumn("�γ�����").setMinWidth(100); 
					jp3table.getColumn("�γ�����").setMinWidth(50); 
					jp3table.getColumn("��ʦ").setMinWidth(30); 
					jp3table.getColumn("ѧ��").setMinWidth(15);
					jp3table.getColumn("�Ͽεص�").setMinWidth(60);
					jp3table.getColumn("�Ͽ�ʱ��").setMinWidth(150);

					final JScrollPane jp3scrollPane = new JScrollPane(jp3table); 
					jp3scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp3scrollPane.setBounds(0, 0, 580, 220);
					tsPanel.add(jp3scrollPane);
					tsPanel.add(chooseButton);
					chooseButton.setBounds(250, 225, 74, 29);
					
				    JLabel label = new JLabel("����ѡ���ͨʶ���ֿΣ�");
					label.setFont(new Font("����",Font.PLAIN,15));
					label.setBounds(20, 255, 200, 30);
					tsPanel.add(label);

					try{
						RMIFactory factory = Client.getFactory();
						CourseSelectionBLService csq = factory.getCSQService();
						myTSCourse = csq.checkMyCourse(stuNO, null, "false");
						num = myTSCourse.size();

						for(int i = 0;i < myTSCourse.size();i ++){
							if(!myTSCourse.get(i).getModule().equals("ͨʶͨ��") || myTSCourse.get(i).getIsCompulsory()){
								myTSCourse.remove(i);
								i --;
							}
						}
						
						for(int i = 0;i < myTSCourse.size();i ++){
							final CourseVO vo = myTSCourse.get(i);
							final JLabel course = new JLabel(vo.getCoName());
							int length = vo.getCoName().length();
							course.setBounds(10 + 130 * i, 280, length * 18, 30);
							course.setFont(new Font("�����п�",Font.BOLD,15));
							course.addMouseListener(new MouseAdapter(){

								@Override
								public void mouseClicked(MouseEvent arg0) {
									// TODO �Զ����ɵķ������
									JFrame frame = new JFrame("�γ���ϸ��Ϣ");
									frame.setResizable(false);
									JPanel panel = new JPanel();
									panel.setLayout(null);

									JLabel courseNOLabel = new JLabel("�γ̺ţ�");
									courseNOLabel.setFont(new Font("��Բ", Font.BOLD, 15));
									courseNOLabel.setBounds(43, 42, 66, 15);
									panel.add(courseNOLabel);

									JLabel lblCourseno = new JLabel(vo.getCoID());
									lblCourseno.setBounds(130, 42, 66, 15);
									panel.add(lblCourseno);

									JLabel courseName = new JLabel("�γ����ƣ�");
									courseName.setFont(new Font("��Բ", Font.BOLD, 15));
									courseName.setBounds(246, 42, 87, 15);
									panel.add(courseName);

									JLabel lblCoursename = new JLabel(vo.getCoName());
									lblCoursename.setBounds(355, 42, 96, 15);
									panel.add(lblCoursename);

									JLabel property = new JLabel("�γ����ʣ�");
									property.setFont(new Font("��Բ", Font.BOLD, 15));
									property.setBounds(43, 89, 80, 15);
									panel.add(property);

									boolean isCompulsory = vo.getIsCompulsory();
									JLabel lblIscompulsory;
									if (isCompulsory) {
										lblIscompulsory = new JLabel("����");
									} else {
										lblIscompulsory = new JLabel("ѡ��");
									}
									lblIscompulsory.setBounds(130, 89, 85, 15);
									panel.add(lblIscompulsory);

									JLabel credit = new JLabel("ѧ��:");
									credit.setFont(new Font("��Բ", Font.BOLD, 15));
									credit.setBounds(246, 89, 54, 15);
									panel.add(credit);

									JLabel lblCredit = new JLabel(vo.getCredit());
									lblCredit.setBounds(355, 89, 54, 15);
									panel.add(lblCredit);

									JLabel teacher = new JLabel("�ον�ʦ��");
									teacher.setFont(new Font("��Բ", Font.BOLD, 15));
									teacher.setBounds(43, 136, 80, 15);
									panel.add(teacher);

									JLabel lblTeacher = new JLabel(vo.getTeacher());
									lblTeacher.setBounds(130, 136, 54, 15);
									panel.add(lblTeacher);

									JLabel location = new JLabel("�Ͽεص㣺");
									location.setFont(new Font("��Բ", Font.BOLD, 15));
									location.setBounds(246, 136, 87, 15);
									panel.add(location);

									JLabel lblLocation = new JLabel(vo.getLocation());
									lblLocation.setBounds(355, 136, 54, 15);
									panel.add(lblLocation);

									JLabel time = new JLabel("�Ͽ�ʱ�䣺");
									time.setFont(new Font("��Բ", Font.BOLD, 15));
									time.setBounds(43, 183, 80, 15);
									panel.add(time);

									JLabel lblTime = new JLabel(vo.getTime());
									lblTime.setBounds(130, 183, 54, 15);
									panel.add(lblTime);

									JLabel description = new JLabel("�γ�������");
									description.setFont(new Font("��Բ", Font.BOLD, 15));
									description.setBounds(43, 230, 80, 15);
									panel.add(description);

									JTextArea textArea = new JTextArea();
									textArea.setBounds(130, 230, 298, 80);
									textArea.setText(vo.getDescription());
									textArea.setEditable(false);

									panel.add(textArea);
									frame.getContentPane().add(panel);
									frame.setBounds(400, 200, 550, 400);
									frame.setVisible(true);
								}

								@Override
								public void mouseEntered(MouseEvent arg0) {
									// TODO Auto-generated method stub
									course.setForeground(Color.orange);
									setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								}

								@Override
								public void mouseExited(MouseEvent arg0) {
									// TODO Auto-generated method stub
									course.setForeground(Color.black);
									setCursor(Cursor.getDefaultCursor());
								}

							});
							repaint();
							tsPanel.add(course);
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
					tsPanel.setVisible(true);
					
					add(tsPanel);
				}else{
					JOptionPane.showMessageDialog(null, "��δ����ͨʶ���ֿΣ�");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label_1.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				label_1.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		add(label_1);
		repaint();
		
		final JLabel label_2 = new JLabel("��רҵѡ��");
//		label_2.setForeground(Color.black);
		label_2.setFont(new Font("����", Font.PLAIN, 15));
		label_2.setBounds(325, 20, 100, 20);
		label_2.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tsPanel.setVisible(false);
				tyPanel.setVisible(false);
				final JFrame frame = new JFrame();
				frame.setBounds(450,300,400,290);

				drawEmptyTable(kzyPanel);
				
				JPanel chooseInstitute = new JPanel();
				chooseInstitute.setLayout(null);
				String[] institute = { "���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
						"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
						"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
						"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ", "��ѧϵ","��ʷѧԺ","������ѧѧԺ" };

				final JComboBox<String> jc = new JComboBox<String>(institute);
				jc.setBounds(180, 40, 150, 30);
				chooseInstitute.add(jc);

				JLabel l = new JLabel("ѡ��Ժϵ:");
				l.setFont(new Font("��Բ", Font.PLAIN, 16));
				l.setForeground(Color.black);
				l.setBounds(80, 40, 80, 30);
				chooseInstitute.add(l);

				JButton OKButton = new JButton("ȷ��");
				OKButton.setFont(new Font("��Բ", Font.PLAIN, 16));
				OKButton.setForeground(Color.black);
				OKButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
						
						repaint();
						String selectedInstitute = (String)jc.getSelectedItem();
						
						ArrayList<CourseVO> instituteCourse = new ArrayList<CourseVO>();
						try {
							UserBLService uq = factory.getUQService();
							UserVO uv = uq.getUserByIdNum(stuNO);
							String myInstitute = uv.getInstitute();
							if(myInstitute.equals(selectedInstitute)){
								JOptionPane.showMessageDialog(null, "�����ڱ�רҵ���޷���רҵѡ�Σ�");
							}else{
								CourseBLService cq = factory.getCQService();
								instituteCourse = cq.checkYXCompletedcourseList(selectedInstitute);
								
								UserBLService user = factory.getUQService();
								UserVO vo = user.getUserByIdNum(stuNO);
								
								int grade = Integer.parseInt(vo.getGrade());
								
								Calendar cal = Calendar.getInstance();
								int year = cal.get(Calendar.YEAR);
								int month = cal.get(Calendar.MONTH);
								
								int currentSemester = -1;
								
								if(month >= 9 && month <= 12){
									currentSemester = (year - grade) * 2 + 1;
								}else if(month == 1){
									currentSemester = (year - grade) * 2 - 1;
								}else if(month >= 2 && month <= 6){
									currentSemester = (year - grade) * 2;
								}
								
								
								for(int i = 0;i < instituteCourse.size();i ++){
									if(!(instituteCourse.get(i).getModule().equals("����ѡ��"))||!instituteCourse.get(i).getSemester().equals(currentSemester+"")){
										instituteCourse.remove(i);
										i --;
									}
								}
								
								if(instituteCourse.size() > 0){
									kzyPanel.removeAll();
									
									StuTransformMethod trans = new StuTransformMethod();
									Object[][] instituteCourseList = trans.transform(instituteCourse);

									jp3table = new JTable(instituteCourseList, jp3title);
									jp3table.getColumn("���").setMinWidth(15); // �����п�
									jp3table.getColumn("�γ̺�").setMinWidth(40);    
									jp3table.getColumn("�γ�����").setMinWidth(100); 
									jp3table.getColumn("�γ�����").setMinWidth(50); 
									jp3table.getColumn("��ʦ").setMinWidth(30); 
									jp3table.getColumn("ѧ��").setMinWidth(15);
									jp3table.getColumn("�Ͽεص�").setMinWidth(60);
									jp3table.getColumn("�Ͽ�ʱ��").setMinWidth(150);

									jp3scrollPane = new JScrollPane(jp3table); 
									jp3scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
									jp3scrollPane.setBounds(0, 0, 580, 255);
									kzyPanel.add(jp3scrollPane);
									kzyPanel.add(chooseButton);
									chooseButton.setBounds(250, 265, 74, 29);
									kzyPanel.setVisible(true);
									add(kzyPanel);
								}else{
									JOptionPane.showMessageDialog(null, "��δ������Ժϵ�γ̣�");
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				OKButton.setBounds(160, 150, 74, 29);
				chooseInstitute.add(OKButton);

				frame.getContentPane().add(chooseInstitute);
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);//�ö�

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label_2.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				label_2.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		add(label_2);
		repaint();
		
		final JLabel label_3 = new JLabel("������");
		label_3.setFont(new Font("����", Font.PLAIN, 15));
//		label_3.setForeground(Color.black);
		label_3.setBounds(450, 20, 100, 20);
		label_3.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				kzyPanel.setVisible(false);
				tsPanel.setVisible(false);
				
				ArrayList<CourseVO> PECourse = new ArrayList<CourseVO>();
				try{
					CourseBLService cq = factory.getCQService();
					PECourse = cq.getModuleCompletedCourseList("ͨʶͨ��");
					
					UserBLService uq = factory.getUQService() ;
					UserVO vo = uq.getUserByIdNum(stuNO);
					
					int grade = Integer.parseInt(vo.getGrade());
					
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					
					int currentSemester = -1;
					
					if(month >= 9 && month <= 12){
						currentSemester = (year - grade) * 2 + 1;
					}else if(month == 1){
						currentSemester = (year - grade) * 2 - 1;
					}else if(month >= 2 && month <= 6){
						currentSemester = (year - grade) * 2;
					}
					
					for(int i = 0;i < PECourse.size();i ++){
						String courseNO = PECourse.get(i).getCoID();
						String semester = PECourse.get(i).getSemester();
						
						if(courseNO.charAt(0) != 'P' || courseNO.charAt(1) != 'E' || !semester.equals(currentSemester + "")){
							PECourse.remove(i);
							i --;
						}
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				

				drawEmptyTable(tyPanel);
				
				if(PECourse.size() > 0){
					tyPanel.removeAll();
					StuTransformMethod trans = new StuTransformMethod();

					Object[][] PECourseList = trans.transform(PECourse);

					jp3table = new JTable(PECourseList, jp3title);
					jp3table.getColumn("���").setMinWidth(15); // �����п�
					jp3table.getColumn("�γ̺�").setMinWidth(40);    
					jp3table.getColumn("�γ�����").setMinWidth(100); 
					jp3table.getColumn("�γ�����").setMinWidth(50); 
					jp3table.getColumn("��ʦ").setMinWidth(30); 
					jp3table.getColumn("ѧ��").setMinWidth(15);
					jp3table.getColumn("�Ͽεص�").setMinWidth(60);
					jp3table.getColumn("�Ͽ�ʱ��").setMinWidth(150);

					final JScrollPane jp3scrollPane = new JScrollPane(jp3table); 
					jp3scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					jp3scrollPane.setBounds(0, 0, 580, 255);
					tyPanel.add(jp3scrollPane);
					tyPanel.add(chooseButton);
					chooseButton.setBounds(250, 265, 74, 29);
					repaint();
					tyPanel.setVisible(true);

					add(tyPanel);
				}else{
					JOptionPane.showMessageDialog(null, "��δ���������Σ�");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				label_3.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				label_3.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}	
		});
		add(label_3);
		repaint();
		
	}
	
	public void drawEmptyTable(JPanel panel){
		panel.removeAll();
		String[] jp3title = {"���","�γ̺�","�γ�����","�γ�����","ѧ��","��ʦ","�Ͽεص�","�Ͽ�ʱ��"};
		Object[][] list = new Object[0][8];
		jp3table = new JTable(list,jp3title){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		jp3table.getColumn("���").setMinWidth(15); // �����п�
		jp3table.getColumn("�γ̺�").setMinWidth(40);    
		jp3table.getColumn("�γ�����").setMinWidth(100); 
		jp3table.getColumn("�γ�����").setMinWidth(50); 
		jp3table.getColumn("��ʦ").setMinWidth(30); 
		jp3table.getColumn("ѧ��").setMinWidth(15);
		jp3table.getColumn("�Ͽεص�").setMinWidth(60);
		jp3table.getColumn("�Ͽ�ʱ��").setMinWidth(150);
		
		jp3scrollPane = new JScrollPane(jp3table);
		jp3scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp3scrollPane.setBounds(0, 0, 580, 255);
		panel.add(jp3scrollPane);

		chooseButton.setBounds(250, 265, 74, 29);
		panel.add(chooseButton);
		
		add(panel);
		panel.setVisible(true);	
	}

}
