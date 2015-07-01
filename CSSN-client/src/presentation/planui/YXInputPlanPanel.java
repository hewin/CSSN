package presentation.planui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businesslogicservice.frameblservice.FrameBLService;
import businesslogicservice.planblservice.PlanBLService;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import vo.plan.Course;
import vo.plan.PlanVO;

public class YXInputPlanPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	RMIFactory factory = Client.getFactory();
	
	String grade = "";
	String semester = "";
	
	ArrayList<Course> openList = new ArrayList<Course>();
	ArrayList<Course> majorList = new ArrayList<Course>();
	
	JComboBox<String> jcb2_2;
	JComboBox<String> jcb2_3;
	
	private JTextField textField_1;
	private JTextField textField_2;

	public YXInputPlanPanel(final String institution) {
		setLayout(null);
		
		
		// ==========================��һ��========================
		// Ժϵ
		final JLabel jl2_1 = new JLabel("��ѡ���꼶��ѧ�ڣ�");
		jl2_1.setFont(new Font("����", Font.PLAIN, 20));
		jl2_1.setBounds(93, 31, 207, 29);
		add(jl2_1);

		// �꼶
		final JLabel jl2_2 = new JLabel(" ��  ��");
		jl2_2.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2.setBounds(142, 89, 89, 29);
		add(jl2_2);

		jcb2_2 = new JComboBox<String>();
		jcb2_2.setFont(new Font("����", Font.PLAIN, 18));
		jcb2_2.setModel(new DefaultComboBoxModel<String>(new String[] { "2013",
				"2012", "2011", "2010" }));
		jcb2_2.setToolTipText("");
		jcb2_2.setBounds(261, 89, 125, 29);
		add(jcb2_2);

		// ѧ��
		final JLabel jl2_3 = new JLabel(" ѧ  ��");
		jl2_3.setFont(new Font("����", Font.PLAIN, 18));
		jl2_3.setBounds(142, 148, 86, 29);
		add(jl2_3);

		jcb2_3 = new JComboBox<String>();
		jcb2_3.setFont(new Font("����", Font.PLAIN, 18));
		jcb2_3.setModel(new DefaultComboBoxModel<String>(new String[] { "1",
				"2", "3", "4", "5", "6", "7", "8" }));
		jcb2_3.setBounds(261, 148, 125, 29);
		add(jcb2_3);

		// ȷ����ť
		final JButton button2_1 = new JButton("ȷ��");
		button2_1.setFont(new Font("����", Font.PLAIN, 18));
		button2_1.setBounds(193, 234, 128, 36);
		add(button2_1);
		
		// ============================�ڶ���===========================
		final JLabel jl2_2_1 = new JLabel("2012");
		jl2_2_1.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_1.setBounds(121, 46, 39, 21);

		final JLabel jl2_2_2 = new JLabel("��    ��");
		jl2_2_2.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_2.setBounds(160, 46, 80, 21);

		final JLabel jl2_2_3 = new JLabel("1");
		jl2_2_3.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_3.setBounds(238, 46, 20, 21);

		final JLabel jl2_2_4 = new JLabel("ѧ�ڣ�");
		jl2_2_4.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_4.setBounds(250, 46, 80, 21);

		final JLabel jl2_2_5 = new JLabel("�γ�����");
		jl2_2_5.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_5.setBounds(146, 94, 98, 40);

		textField_1 = new JTextField();
		textField_1.setBounds(269, 98, 120, 29);
		textField_1.setColumns(10);

		final JLabel jl2_2_6 = new JLabel("ѧ �֣�");
		jl2_2_6.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_6.setBounds(146, 139, 98, 40);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(269, 140, 120, 29);

		final JLabel jl2_2_7 = new JLabel("����ģ�飺");
		jl2_2_7.setFont(new Font("����", Font.PLAIN, 18));
		jl2_2_7.setBounds(146, 183, 98, 40);

		final JComboBox<Object> jcb2_2_7 = new JComboBox<Object>();
		jcb2_2_7.setFont(new Font("����", Font.PLAIN, 18));
		jcb2_2_7.setBounds(271, 185, 120, 29);

		final JButton jbt2_2_8 = new JButton("ȷ   ��");
		jbt2_2_8.setFont(new Font("����", Font.PLAIN, 18));
		jbt2_2_8.setBounds(130, 259, 110, 30);

		final JButton jbt2_2_9 = new JButton("�������");
		jbt2_2_9.setFont(new Font("����", Font.PLAIN, 18));
		jbt2_2_9.setBounds(250, 259, 116, 30);

		final JButton jbt2_2_10 = new JButton("��   ��");
		jbt2_2_10.setFont(new Font("����", Font.PLAIN, 18));
		jbt2_2_10.setBounds(375, 259, 110, 30);

		
		
		
		
		// ��һ���ϡ�ȷ������ť�ļ���
		button2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openList.clear();
				majorList.clear();
				removeAll();
				add(jl2_2_1);
				add(jl2_2_2);
				add(jl2_2_3);
				add(jl2_2_4);
				add(jl2_2_5);
				add(textField_1);
				add(jl2_2_6);
				add(textField_2);
				add(jl2_2_7);
				add(jcb2_2_7);
				add(jbt2_2_8);
				add(jbt2_2_9);
				add(jbt2_2_10);
				repaint();

				grade = (String) jcb2_2.getSelectedItem();
				semester = (String) jcb2_3.getSelectedItem();
				jl2_2_1.setText(grade);
				jl2_2_3.setText(semester);
				
				try{
					FrameBLService fb=factory.getFQService();
					jcb2_2_7.setModel(new DefaultComboBoxModel<Object>(fb.getModuleNameList()));
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}

		});

		// �ڶ����ϡ�ȷ������ť�ļ���
		jbt2_2_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textField_2.getText();
				if (textField_1.getText().equals("")
						|| textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�뽫�γ���Ϣ��д������", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				}else if(!isNumber(s)){
					JOptionPane.showMessageDialog(null, "�����ѧ�ֲ�����Ҫ��", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				}else if(Integer.parseInt(s)>5){
					JOptionPane.showMessageDialog(null, "����ĵ��ſγ̵�ѧ�ֳ��������ޣ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				}else if(jcb2_2_7.getSelectedItem().equals("����ѡ��")||
						jcb2_2_7.getSelectedItem().equals("רҵ����")){
					String module = (String) jcb2_2_7.getSelectedItem();
					Course c = new Course(textField_1.getText(), Integer
							.parseInt(textField_2.getText()), module);
					if (module.equals("����ѡ��")) {
						openList.add(c);
					} else if (module.equals("רҵ����")) {
						majorList.add(c);
					}
					JOptionPane.showMessageDialog(null, "һ�ſγ�����ɹ���", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
					textField_1.setText("");
					textField_2.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "��ģ��Ը�Ժϵ�����ţ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});

		// �ڶ����ϡ�������ϡ���ť�ļ���
		jbt2_2_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(openList.isEmpty()||majorList.isEmpty()){
					JOptionPane.showMessageDialog(null, "���꼶��ѧ�ڼƻ���δ����������", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE, null);
				}else{
				try {
					
					for(int i=0;i<openList.size();i++){
						System.out.println(openList.get(i).getName());
					}
					for(int i=0;i<majorList.size();i++){
						System.out.println(majorList.get(i).getName());
					}
					
					PlanBLService plq = factory.getPLQService();
					PlanVO pp = new PlanVO(institution,
							Integer.parseInt(grade),
							Integer.parseInt(semester), openList, majorList);
					plq.inputPlan(pp);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "���꼶��ѧ�ڼƻ�����ɹ���", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE, null);
				removeAll();
				add(jl2_1);
				add(jl2_2);
				add(jl2_3);
				add(jcb2_2);
				add(jcb2_3);
				add(button2_1);
				repaint();
			}
		}
		});

		// ���ذ�ť�ļ���
		jbt2_2_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(jl2_1);
				add(jl2_2);
				add(jl2_3);
				add(jcb2_2);
				add(jcb2_3);
				add(button2_1);
				repaint();
			}
		});
	}
	
	public boolean isNumber(String s) {
		boolean isNumber = true;
		char ch[] = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] < '0' || ch[i] > '9') {
				isNumber = false;
			}
		}
		return isNumber;
	}
	

}
