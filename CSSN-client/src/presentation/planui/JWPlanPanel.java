package presentation.planui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.planblservice.PlanBLService;

import vo.plan.Course;

public class JWPlanPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JWPlanPanel() {
		super();
		setLayout(null);
		initPanel();
	}
	
	@SuppressWarnings("serial")
	public void initPanel(){
		removeAll();
		
		//消息提示框
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("楷体", Font.PLAIN, 18));
		
		// -----------------------第一页----------------------
        //院系列表		
		String[] depList = {"软件学院", "文学院", "物理学院", "地球科学与工程学院", "大气科学学院",
				"地理与海洋科学学院", "电子科学与工程学院", "现代工程与应用科学学院", "新闻传播学院", "商学院",
				"外国语学院", "政府管理学院", "社会学院", "工程管理学院", "建筑与城市规划学院", "天文与空间科学学院",
				"信息管理学院", "化学化工学院", "环境学院", "医学院", "海外教育学院","数学系","历史学院","生命科学学院"};
		DefaultTableModel jp2deftbmd1 = new DefaultTableModel();
		jp2deftbmd1.addColumn("院系列表", depList);
		final JTable jp2jt1 = new JTable(jp2deftbmd1){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt1.setFont(new Font("楷体", Font.PLAIN, 18));
		jp2jt1.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 20));

		final JScrollPane jp2jsp1 = new JScrollPane(jp2jt1);
		jp2jsp1.setBounds(15, 10, 300, 302);
		add(jp2jsp1);

		final JLabel jp2jl1 = new JLabel("年级");
		jp2jl1.setBounds(340, 55, 200, 50);
		jp2jl1.setFont(new Font("楷体", Font.PLAIN, 18));
		add(jp2jl1);
		
		String[] type1 = { "2010", "2011", "2012", "2013"};
		final JComboBox<String> jp2jc = new JComboBox<String>(type1);
		jp2jc.setBounds(420, 65, 120, 30);
		jp2jc.setFont(new Font("楷体", Font.PLAIN, 18));
		add(jp2jc);
		
		final JLabel jp2jl2 = new JLabel("开设学期");
		jp2jl2.setBounds(340, 150, 200, 50);
		jp2jl2.setFont(new Font("楷体", Font.PLAIN, 18));
		add(jp2jl2);
		
		String[] type2 = { "1", "2" };
		final JComboBox<String> jp2jc2 = new JComboBox<String>(type2);
		jp2jc2.setBounds(420, 160, 120, 30);
		jp2jc2.setFont(new Font("楷体", Font.PLAIN, 18));
		add(jp2jc2);
		
		final JButton jp2jb = new JButton("查看");
		jp2jb.setBounds(400, 290, 80, 35);
		jp2jb.setFont(new Font("楷体", Font.PLAIN, 18));
		jp2jb.setForeground(Color.BLACK);
		
		// -----------------------第二页----------------------		
		final JLabel jp2jl3 = new JLabel("开放选修课");
		jp2jl3.setBounds(100, 2, 200, 30);
		jp2jl3.setFont(new Font("楷体", Font.PLAIN, 16));
		
		final JLabel jp2jl4 = new JLabel("专业必修课");
		jp2jl4.setBounds(360, 2, 200, 30);
		jp2jl4.setFont(new Font("楷体", Font.PLAIN, 16));

		String[][] courProperty1 = null;// 课程属性
		String[] thNames = { "课程名", "学分" };
		DefaultTableModel jp2deftbmd2 = new DefaultTableModel(courProperty1,
				thNames);
		final JTable jp2jt2 = new JTable(jp2deftbmd2){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt2.setFont(new Font("楷体", Font.PLAIN, 15));
		jp2jt2.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 15));		
		final  DefaultTableModel model1 =(DefaultTableModel) jp2jt2.getModel();
			
		String[][] courProperty2 = null;// 课程属性
		DefaultTableModel jp2deftbmd3 = new DefaultTableModel(courProperty2,
				thNames);
		final JTable jp2jt3 = new JTable(jp2deftbmd3){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt3.setFont(new Font("楷体", Font.PLAIN, 15));
		jp2jt3.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 15));
		final  DefaultTableModel model2 =(DefaultTableModel) jp2jt3.getModel();
		
		final JButton jp2jb2 = new JButton("返回");
		jp2jb2.setBounds(400, 290, 80, 35);
		jp2jb2.setFont(new Font("楷体", Font.PLAIN, 20));
		jp2jb2.setForeground(Color.BLACK);
		
		jp2jb.addActionListener(new ActionListener() {// 查看

			public void actionPerformed(ActionEvent e) {
				
				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				}
				while(model2.getRowCount()>0){
				      model2.removeRow(model2.getRowCount()-1);
				}
				
				int row = jp2jt1.getSelectedRow();
				if(row>=0){   //<0未选择提示
				    String institute = (String)jp2jt1.getValueAt(row, 0);
				    int grade = Integer.parseInt((String) jp2jc.getSelectedItem());
				    int semester = Integer.parseInt((String) jp2jc2.getSelectedItem());
				    
				    try{
					    PlanBLService pqservice = factory.getPLQService();
					    ArrayList<Course> openList = pqservice.getOpenList(institute, grade, semester);
					    ArrayList<Course> majorList = pqservice.getMajorList(institute, grade, semester);
					    
					    if(!openList.isEmpty()&&!majorList.isEmpty()){  //考虑空
					    	
					        for(int i=0;i<openList.size();i++){
					     	    String[] row1 = {openList.get(i).getName(), openList.get(i).getCredit()+""};
					            model1.addRow(row1);
					        }
					        
					        for(int i=0;i<majorList.size();i++){
					     	    String[] row2 = {majorList.get(i).getName(), majorList.get(i).getCredit()+""};
					            model2.addRow(row2);
					        }
					        
					        removeAll();
					        
							final JScrollPane jp2jsp2 = new JScrollPane(jp2jt2);
							jp2jsp2.setBounds(15, 32, 277, 255);
							add(jp2jsp2);
							
							final JScrollPane jp2jsp3 = new JScrollPane(jp2jt3);
							jp2jsp3.setBounds(297, 32, 277, 255);
							
							final JLabel jp2jl12 = new JLabel(institute+grade+"年第"+semester+"学期教学计划");
							jp2jl12.setBounds(120, 290, 280,35);
							jp2jl12.setFont(new Font("楷体", Font.PLAIN, 16));
							
							add(jp2jl3);
							add(jp2jl4);
							add(jp2jb2);
							add(jp2jl12);
							add(jp2jsp3);					        
					    }else{
					    	jdjll.setText("开放选修模块或专业必修模块未填写！");
							JOptionPane.showMessageDialog(null, jdjll, "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
					    }
					    repaint();
					    
				     }catch (RemoteException e1) {
					     e1.printStackTrace();
					     JOptionPane.showMessageDialog(null, "无法连接到服务器，请检查网络连接", "系统信息",
					    		 JOptionPane.INFORMATION_MESSAGE);
				     }
				}else{
			    	jdjll.setText("尚未选择院系！");
					JOptionPane.showMessageDialog(null, jdjll, "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
			    }
			}

		});

		jp2jb2.addActionListener(new ActionListener() { //返回

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jp2jsp1);
				add(jp2jb);
				add(jp2jl1);
				add(jp2jc);
				add(jp2jl2);
				add(jp2jc2);
				repaint();
			}

		});
		add(jp2jb);
		}		
}
