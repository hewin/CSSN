package presentation.courseui;

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
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.courseblservice.CourseBLService;
import JavaRMI.RMIFactory;
import vo.course.CourseVO;

public class RKFinishCourInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel panel;
	DefaultTableModel jp1dft;
	JTable jp1table1;
	JLabel label,label_1,jsp2jl1,jsp2jl2;
	JButton button_1,button_2;
	JScrollPane jsp1,jsp2,jsp3;

	public RKFinishCourInfoPanel(final RMIFactory factory, String id) {

		panel = new JPanel();
		setPanel(factory, id);

	}

	public void setPanel(final RMIFactory factory, String id) {
		panel.setBounds(0, 0, 580, 360);
		panel.setLayout(null);

		ArrayList<CourseVO> courseList = new ArrayList<>();
		try {
			CourseBLService cqservice = factory.getCQService();
			courseList = cqservice.getCourseToFinishInfo(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] jp1title1 = { "�γ̺�", "�γ�����", "ѧ��", "ѧ��", "ѧ��" };
		final String[][] jp1info1 = new String[courseList.size()][5];
		for (int m = 0; m < courseList.size(); m++) {
			jp1info1[m][0] = courseList.get(m).getCoID();
			jp1info1[m][1] = courseList.get(m).getCoName();
			jp1info1[m][2] = courseList.get(m).getCredit();
			jp1info1[m][3] = courseList.get(m).getGrade();
			jp1info1[m][4] = courseList.get(m).getSemester();
		}

		jp1dft = new DefaultTableModel(jp1info1,
				jp1title1);
		jp1table1 = new JTable(jp1dft) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		jp1table1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					chooseFinishInfo();
				}
			}
		});
		jp1table1.getTableHeader().setFont(new Font("����", Font.PLAIN, 17));
		jp1table1.setFont(new Font("����", Font.PLAIN, 16));
		jp1table1.getColumn("�γ�����").setMinWidth(150); // �����п�

		final JScrollPane jp1scrollPane1 = new JScrollPane(jp1table1);
		jp1scrollPane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp1scrollPane1.setBounds(10, 10, 555, 271);
		panel.add(jp1scrollPane1);

		final JButton jp1b1 = new JButton("��д��Ϣ");
		jp1b1.setFont(new Font("����", Font.PLAIN, 18));
		jp1b1.setBounds(320, 300, 105, 30);
		panel.add(jp1b1);

		final JLabel jp4jl = new JLabel("˫���γ�������д����");
		jp4jl.setBounds(110, 300, 220, 30);
		jp4jl.setFont(new Font("����", Font.BOLD, 17));
		panel.add(jp4jl);
		
		label = new JLabel("�γ̴��");
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(90, 18, 84, 25);

		final JTextArea jsp1jt = new JTextArea(10, 10);
		jsp1jt.setFont(new Font("����", Font.BOLD, 20));

		jsp1 = new JScrollPane(jsp1jt);
		jsp1.setBounds(25, 50, 240, 255);

		label_1 = new JLabel("�̲�Ŀ¼���ο���Ŀ");
		label_1.setFont(new Font("����", Font.BOLD, 20));
		label_1.setBounds(351, 18, 200, 25);

		ImageIcon icon1 = new ImageIcon("image/�̲�.jpg");
		jsp2jl1 = new JLabel(icon1);
		jsp2jl1.setBounds(295, 70, 30, 75);

		ImageIcon icon2 = new ImageIcon("image/�ο���.jpg");
		jsp2jl2 = new JLabel(icon2);
		jsp2jl2.setBounds(295, 200, 30, 100);

		final JTextArea jsp2jt = new JTextArea(10, 5);
		jsp2jt.setFont(new Font("����", Font.BOLD, 20));

		jsp2 = new JScrollPane(jsp2jt);
		jsp2.setBounds(325, 50, 240, 120);

		final JTextArea jsp3jt = new JTextArea(10, 5);
		jsp3jt.setFont(new Font("����", Font.BOLD, 20));

		jsp3 = new JScrollPane(jsp3jt);
		jsp3.setBounds(325, 185, 240, 120);

		button_1 = new JButton("����");
		button_1.setFont(new Font("����", Font.BOLD, 18));
		button_1.setBounds(162, 312, 78, 28);

		button_2 = new JButton("����");
		button_2.setFont(new Font("����", Font.BOLD, 18));
		button_2.setBounds(346, 312, 78, 28);

		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// ���水ť
				if (((jsp1jt.getText()).equals(""))
						|| ((jsp2jt.getText()).equals(""))
						|| ((jsp3jt.getText()).equals(""))) {
					JOptionPane.showMessageDialog(null, "�뽫������Ϣ��д������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						CourseBLService cqservice = factory.getCQService();
						int r = jp1table1.getSelectedRow();
						cqservice.completeCourseInfo((String) jp1dft
								.getValueAt(r, 0),
								new CourseVO(jsp1jt.getText(),
										jsp2jt.getText(), jsp3jt.getText()));
						jp1dft.removeRow(r);
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
					JOptionPane.showMessageDialog(null, "�γ���Ϣ����ɹ���", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});

		button_2.addActionListener(new ActionListener() { // ���ذ�ť

			public void actionPerformed(ActionEvent e) {
				jsp1jt.setText("");
				jsp2jt.setText("");
				jsp3jt.setText("");
				panel.removeAll();
				panel.add(jp1scrollPane1);
				panel.add(jp1b1);
				panel.add(jp4jl);
				panel.repaint();
			}

		});

		jp1b1.addActionListener(new ActionListener() { // ��д��Ϣ��ť

			public void actionPerformed(ActionEvent e) {
				chooseFinishInfo();
			}

		});
	}

	public JPanel getpanel() {
		return panel;
	}

	public void chooseFinishInfo(){
		int row = jp1table1.getSelectedRow();
		if (row >= 0) {
			panel.removeAll();
			panel.add(label);
			panel.add(jsp1);
			panel.add(jsp2);
			panel.add(jsp3);
			panel.add(jsp2jl1);
			panel.add(jsp2jl2);
			panel.add(label_1);
			panel.add(button_1);
			panel.add(button_2);
			panel.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "��δѡ��γ̣�", "ϵͳ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
