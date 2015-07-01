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
		
		//��Ϣ��ʾ��
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("����", Font.PLAIN, 18));
		
		// -----------------------��һҳ----------------------
        //Ժϵ�б�		
		String[] depList = {"���ѧԺ", "��ѧԺ", "����ѧԺ", "�����ѧ�빤��ѧԺ", "������ѧѧԺ",
				"�����뺣���ѧѧԺ", "���ӿ�ѧ�빤��ѧԺ", "�ִ�������Ӧ�ÿ�ѧѧԺ", "���Ŵ���ѧԺ", "��ѧԺ",
				"�����ѧԺ", "��������ѧԺ", "���ѧԺ", "���̹���ѧԺ", "��������й滮ѧԺ", "������ռ��ѧѧԺ",
				"��Ϣ����ѧԺ", "��ѧ����ѧԺ", "����ѧԺ", "ҽѧԺ", "�������ѧԺ","��ѧϵ","��ʷѧԺ","������ѧѧԺ"};
		DefaultTableModel jp2deftbmd1 = new DefaultTableModel();
		jp2deftbmd1.addColumn("Ժϵ�б�", depList);
		final JTable jp2jt1 = new JTable(jp2deftbmd1){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt1.setFont(new Font("����", Font.PLAIN, 18));
		jp2jt1.getTableHeader().setFont(new Font("����", Font.PLAIN, 20));

		final JScrollPane jp2jsp1 = new JScrollPane(jp2jt1);
		jp2jsp1.setBounds(15, 10, 300, 302);
		add(jp2jsp1);

		final JLabel jp2jl1 = new JLabel("�꼶");
		jp2jl1.setBounds(340, 55, 200, 50);
		jp2jl1.setFont(new Font("����", Font.PLAIN, 18));
		add(jp2jl1);
		
		String[] type1 = { "2010", "2011", "2012", "2013"};
		final JComboBox<String> jp2jc = new JComboBox<String>(type1);
		jp2jc.setBounds(420, 65, 120, 30);
		jp2jc.setFont(new Font("����", Font.PLAIN, 18));
		add(jp2jc);
		
		final JLabel jp2jl2 = new JLabel("����ѧ��");
		jp2jl2.setBounds(340, 150, 200, 50);
		jp2jl2.setFont(new Font("����", Font.PLAIN, 18));
		add(jp2jl2);
		
		String[] type2 = { "1", "2" };
		final JComboBox<String> jp2jc2 = new JComboBox<String>(type2);
		jp2jc2.setBounds(420, 160, 120, 30);
		jp2jc2.setFont(new Font("����", Font.PLAIN, 18));
		add(jp2jc2);
		
		final JButton jp2jb = new JButton("�鿴");
		jp2jb.setBounds(400, 290, 80, 35);
		jp2jb.setFont(new Font("����", Font.PLAIN, 18));
		jp2jb.setForeground(Color.BLACK);
		
		// -----------------------�ڶ�ҳ----------------------		
		final JLabel jp2jl3 = new JLabel("����ѡ�޿�");
		jp2jl3.setBounds(100, 2, 200, 30);
		jp2jl3.setFont(new Font("����", Font.PLAIN, 16));
		
		final JLabel jp2jl4 = new JLabel("רҵ���޿�");
		jp2jl4.setBounds(360, 2, 200, 30);
		jp2jl4.setFont(new Font("����", Font.PLAIN, 16));

		String[][] courProperty1 = null;// �γ�����
		String[] thNames = { "�γ���", "ѧ��" };
		DefaultTableModel jp2deftbmd2 = new DefaultTableModel(courProperty1,
				thNames);
		final JTable jp2jt2 = new JTable(jp2deftbmd2){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt2.setFont(new Font("����", Font.PLAIN, 15));
		jp2jt2.getTableHeader().setFont(new Font("����", Font.PLAIN, 15));		
		final  DefaultTableModel model1 =(DefaultTableModel) jp2jt2.getModel();
			
		String[][] courProperty2 = null;// �γ�����
		DefaultTableModel jp2deftbmd3 = new DefaultTableModel(courProperty2,
				thNames);
		final JTable jp2jt3 = new JTable(jp2deftbmd3){
		    public boolean isCellEditable(int r,int c){
			    return false;
		    }
		};
		jp2jt3.setFont(new Font("����", Font.PLAIN, 15));
		jp2jt3.getTableHeader().setFont(new Font("����", Font.PLAIN, 15));
		final  DefaultTableModel model2 =(DefaultTableModel) jp2jt3.getModel();
		
		final JButton jp2jb2 = new JButton("����");
		jp2jb2.setBounds(400, 290, 80, 35);
		jp2jb2.setFont(new Font("����", Font.PLAIN, 20));
		jp2jb2.setForeground(Color.BLACK);
		
		jp2jb.addActionListener(new ActionListener() {// �鿴

			public void actionPerformed(ActionEvent e) {
				
				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				}
				while(model2.getRowCount()>0){
				      model2.removeRow(model2.getRowCount()-1);
				}
				
				int row = jp2jt1.getSelectedRow();
				if(row>=0){   //<0δѡ����ʾ
				    String institute = (String)jp2jt1.getValueAt(row, 0);
				    int grade = Integer.parseInt((String) jp2jc.getSelectedItem());
				    int semester = Integer.parseInt((String) jp2jc2.getSelectedItem());
				    
				    try{
					    PlanBLService pqservice = factory.getPLQService();
					    ArrayList<Course> openList = pqservice.getOpenList(institute, grade, semester);
					    ArrayList<Course> majorList = pqservice.getMajorList(institute, grade, semester);
					    
					    if(!openList.isEmpty()&&!majorList.isEmpty()){  //���ǿ�
					    	
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
							
							final JLabel jp2jl12 = new JLabel(institute+grade+"���"+semester+"ѧ�ڽ�ѧ�ƻ�");
							jp2jl12.setBounds(120, 290, 280,35);
							jp2jl12.setFont(new Font("����", Font.PLAIN, 16));
							
							add(jp2jl3);
							add(jp2jl4);
							add(jp2jb2);
							add(jp2jl12);
							add(jp2jsp3);					        
					    }else{
					    	jdjll.setText("����ѡ��ģ���רҵ����ģ��δ��д��");
							JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
					    }
					    repaint();
					    
				     }catch (RemoteException e1) {
					     e1.printStackTrace();
					     JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
					    		 JOptionPane.INFORMATION_MESSAGE);
				     }
				}else{
			    	jdjll.setText("��δѡ��Ժϵ��");
					JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
			    }
			}

		});

		jp2jb2.addActionListener(new ActionListener() { //����

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
