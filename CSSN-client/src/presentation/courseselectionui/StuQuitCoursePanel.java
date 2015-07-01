package presentation.courseselectionui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.courseui.StuTransformMethod;
import vo.course.CourseVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;

public class StuQuitCoursePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTable jp4table;
	DefaultTableModel dft;
	JPanel tempPanel = new JPanel();
	JScrollPane jp4scrollPane;
	JButton quitButton_1 = new JButton("退选");
	JButton quitButton_2 = new JButton("退选");
	public StuQuitCoursePanel(final String stuNO) {
		setLayout(null);
		removeAll();
		
		tempPanel.setLayout(null);
		tempPanel.setVisible(false);
		tempPanel.setBounds(0, 40, 580, 400);

		drawEmptyTable(stuNO,"true");
		
		quitButton_1.setFont(new Font("楷体", Font.PLAIN, 16));
		quitButton_1.setForeground(Color.black);
		quitButton_1.setBounds(250, 280, 74, 29);

		quitButton_2.setFont(new Font("楷体", Font.PLAIN, 16));
		quitButton_2.setForeground(Color.black);
		quitButton_2.setBounds(250, 280, 74, 29);

		quitButton_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int selectedNO = jp4table.getSelectedRow();
				String message = "";

				if(selectedNO < 0){
					message = "请选择一门课程！";
				}else{
					String courseNO = (String) jp4table.getValueAt(selectedNO, 1);

					try{
						RMIFactory factory = Client.getFactory();
						CourseSelectionBLService csq = factory.getCSQService();
						message = csq.quitCourse(courseNO, stuNO);
						if(message.equals("退选成功！")){
							dft.removeRow(selectedNO);
							jp4table.setModel(dft);
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}

				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		quitButton_2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int selectedNO = jp4table.getSelectedRow();
				String message = "";

				if(selectedNO < 0){
					message = "请选择一门课程！";
				}else{
					String courseNO = (String) jp4table.getValueAt(selectedNO, 1);

					try{
						RMIFactory factory = Client.getFactory();
						CourseSelectionBLService csq = factory.getCSQService();
						message = csq.quitCourse(courseNO, stuNO);
						dft.removeRow(selectedNO);
						jp4table.setModel(dft);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}

				JOptionPane.showMessageDialog(null, message);
			}
		});


		final JLabel label1 = new JLabel("退选已选中的课程");
		label1.setFont(new Font("楷体", Font.PLAIN, 15));
		label1.setForeground(Color.black);
		label1.setBounds(130, 5, 150, 30);
		label1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
					drawPanel(stuNO,"true");	
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				label1.setForeground(Color.orange);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				label1.setForeground(Color.black);
				setCursor(Cursor.getDefaultCursor());
			}

		});
		add(label1);


		final JLabel label2 = new JLabel("退选未抽签的课程");
		label2.setFont(new Font("楷体", Font.PLAIN, 15));
		label2.setForeground(Color.black);
		label2.setBounds(330, 5, 150, 30);
		label2.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				drawPanel(stuNO,"false");
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

	public void drawPanel(String stuNO,String state){
		final RMIFactory factory = Client.getFactory();
		
		ArrayList<CourseVO> mySelectedCourse = new ArrayList<CourseVO>();
		String[] jp4title = {"编号","课程号","课程名称","课程性质","学分","教师","上课地点","上课时间"};
	
		try{
			CourseSelectionBLService csq = factory.getCSQService();
			mySelectedCourse = csq.checkMyCourse(stuNO,null,state);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		if(mySelectedCourse.size() > 0){
			tempPanel.setVisible(false);
			tempPanel.removeAll();
			StuTransformMethod trans = new StuTransformMethod();
			Object[][] list = trans.transform(mySelectedCourse);
			dft = new DefaultTableModel(list, jp4title);
			jp4table = new JTable(dft){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			jp4table.getColumn("编号").setMinWidth(15); // 设置列宽
			jp4table.getColumn("课程号").setMinWidth(40);    
			jp4table.getColumn("课程名称").setMinWidth(100); 
			jp4table.getColumn("课程性质").setMinWidth(50); 
			jp4table.getColumn("教师").setMinWidth(30); 
			jp4table.getColumn("学分").setMinWidth(15);
			jp4table.getColumn("上课地点").setMinWidth(60);
			jp4table.getColumn("上课时间").setMinWidth(150);

			jp4scrollPane = new JScrollPane(jp4table);
			jp4scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp4scrollPane.setBounds(0, 0, 580, 270);
			tempPanel.add(jp4scrollPane);
			
			if(state.equals("true")){
				tempPanel.add(quitButton_1);
			}else if(state.equals("false")){
				tempPanel.add(quitButton_2);
			}

			add(tempPanel);
			repaint();
			tempPanel.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "尚未选中该学期课程！");
			drawEmptyTable(stuNO,state);
		}
	}
	
	public void drawEmptyTable(final String stuNO,final String state){
		tempPanel.setVisible(false);
		tempPanel.removeAll();
		String[] jp4title = {"编号","课程号","课程名称","课程性质","学分","教师","上课地点","上课时间"};
		Object[][] list = new Object[0][8];
		jp4table = new JTable(list,jp4title){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		jp4table.getColumn("编号").setMinWidth(15); // 设置列宽
		jp4table.getColumn("课程号").setMinWidth(40);    
		jp4table.getColumn("课程名称").setMinWidth(100); 
		jp4table.getColumn("课程性质").setMinWidth(50); 
		jp4table.getColumn("教师").setMinWidth(30); 
		jp4table.getColumn("学分").setMinWidth(15);
		jp4table.getColumn("上课地点").setMinWidth(60);
		jp4table.getColumn("上课时间").setMinWidth(150);
		
		jp4scrollPane = new JScrollPane(jp4table);
		jp4scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp4scrollPane.setBounds(0, 0, 580, 270);
		tempPanel.add(jp4scrollPane);
		
		if(state.equals("true")){
			tempPanel.add(quitButton_1);
		}else if(state.equals("false")){
			tempPanel.add(quitButton_2);
		}
		
		add(tempPanel);
		tempPanel.setVisible(true);	
	}

}
