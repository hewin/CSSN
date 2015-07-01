package presentation.frameui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import vo.frame.FrameVO;
import JavaRMI.Client;
import JavaRMI.RMIFactory;
import businesslogicservice.frameblservice.FrameBLService;

public class JWFramePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	int width,height;
	public JWFramePanel(int width,int height) {
		super();
		setLayout(null);
		this.width=width;
		this.height=height;
		initPanel();
	}

	public void initPanel() {
		removeAll();
		//this.setBackground(Color.DARK_GRAY);
//		final JDialog jd1 = new JDialog();
////		jd1.setBounds(500, 300, 300, 100);
////		jd1.setLayout(null);
////		jd1.setAlwaysOnTop(true);// �ö�
		
		ImageIcon image1 = new ImageIcon("image/����.PNG");
		final JLabel jl1 = new JLabel(image1);
		jl1.setBounds(width/20*4, 2, width/20, height/20);
		//jl1.setBounds(width/20, 2, width/20, height/20);
		add(jl1);

		final JLabel jp1jb1 = new JLabel("��ӿ��");
		jp1jb1.setFont(new Font("����", Font.PLAIN, 16));
		jp1jb1.setForeground(Color.BLACK);
		jp1jb1.setBounds(width/20*5, 2, width/10, height/20);
		//jp1jb1.setBounds(width/20*2, 2, width/20, height/20);
		add(jp1jb1);
		
		ImageIcon image2 = new ImageIcon("image/�޸�.PNG");
		final JLabel jl2 = new JLabel(image2);
		//jl2.setBounds(width/20*4, 2, width/20, height/20);
		jl2.setBounds(width/20*7, 2, width/20, height/20);
		add(jl2);

		final JLabel jp1jb2 = new JLabel("�޸Ŀ��");
		jp1jb2.setFont(new Font("����", Font.PLAIN, 16));
		jp1jb2.setForeground(Color.BLACK);
		jp1jb2.setBounds(width/20*8, 2, width/10, height/20);
		//jp1jb2.setBounds(width/20*5, 2, width/20, height/20);
		add(jp1jb2);
		
		ImageIcon image3 = new ImageIcon("image/�鿴.PNG");
		final JLabel jl3 = new JLabel(image3);
		//jl3.setBounds(width/20*7, 2, width/20, height/20);
		jl3.setBounds(width/20, 2, width/20, height/20);
		add(jl3);

		final JLabel jp1jb3 = new JLabel("�鿴���");
		jp1jb3.setFont(new Font("����", Font.PLAIN, 16));
		jp1jb3.setForeground(Color.BLACK);
		jp1jb3.setBounds(width/20*2, 2, width/10, height/20);
		//jp1jb3.setBounds(width/20*8, 2, width/20, height/20);
		add(jp1jb3);
	
		//��Ϣ��ʾ��
		final JLabel jdjll = new JLabel();
		jdjll.setHorizontalAlignment(SwingConstants.CENTER);
		jdjll.setBounds(30, 20, 70, 35);
		jdjll.setFont(new Font("����", Font.PLAIN, 18));

		// -----------------------����----------------------
		final JButton jp1jb11 = new JButton("����");
		jp1jb11.setBounds(150, 290, 100, 30);
		jp1jb11.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jb11);
	
		final JButton jp1jb12 = new JButton("ȡ��");
		jp1jb12.setBounds(300, 290, 90, 30);
		jp1jb12.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jb12);

		final JLabel jp1jl1 = new JLabel("�γ�ģ��");
		jp1jl1.setBounds(2, 53, 100, 35);
		jp1jl1.setFont(new Font("����", Font.PLAIN, 18));
		// ���ñ�ǩ�����ֵĶ��뷽ʽ
		jp1jl1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp1jl1);

		String[] type1 = {"רҵ����","����ѡ�� ","ͨʶͨ��","��ҵ�������"};
		final JComboBox<String> jp1jf1 = new JComboBox<String>(type1);
		jp1jf1.setBounds(131, 55, 145, 30);
		jp1jf1.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf1);

		final JLabel jp1jl2 = new JLabel("�γ�����");
		jp1jl2.setBounds(292, 53, 100, 35);
		jp1jl2.setFont(new Font("����", Font.PLAIN, 18));
		jp1jl2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp1jl2);

		String[] type2 = {"ѡ��","����"};
		final JComboBox<String> jp1jf2 = new JComboBox<String>(type2);
		jp1jf2.setBounds(415, 53, 145, 30);
		jp1jf2.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf2);

		final JLabel jp1jl3 = new JLabel("�γ����");
		jp1jl3.setBounds(2, 125, 100, 35);
		jp1jl3.setFont(new Font("����", Font.PLAIN, 18));
		jp1jl3.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jp1jl3);

		String[] type3 = {"רҵ���Ŀ� ","˼����������","����","����ѡ�޿�","��ҵ�������"};
		final JComboBox<String> jp1jf3 = new JComboBox<String>(type3);
		jp1jf3.setBounds(131, 125, 145, 30);
		jp1jf3.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf3);

		final JLabel jp1jl4 = new JLabel("����ѧ��");
		jp1jl4.setBounds(322, 125, 100, 35);
		jp1jl4.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jl4);

		final JTextField jp1jf4 = new JTextField();
		jp1jf4.setBounds(415, 125, 145, 30);
		jp1jf4.addKeyListener(new KeyAdapter(){

			public void keyTyped(KeyEvent arg0) {
				if(!(arg0.getKeyChar()>'0'&&arg0.getKeyChar()<'9'||arg0.getKeyChar()==','||arg0.getKeyChar()=='\b')){
					jdjll.setText("��Ϣ��д��ʽ����!����ѧ�ڽ���1-8ѧ��!��ͬѧ��֮���ԡ�Ӣ�Ķ��š�����!");
					JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		jp1jf4.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf4);

		final JLabel jp1jl5 = new JLabel("ѧ������");
		jp1jl5.setBounds(27, 197, 100, 35);
		jp1jl5.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jl5);

		final JTextField jp1jf5 = new JTextField();
		jp1jf5.setBounds(131, 197, 145, 30);
		jp1jf5.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf5);

		final JLabel jp1jl6 = new JLabel("ѧ������");
		jp1jl6.setBounds(322, 197, 100, 30);
		jp1jl6.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jl6);

		final JTextField jp1jf6 = new JTextField();
		jp1jf6.setBounds(415, 197, 145, 30);
		jp1jf6.setFont(new Font("����", Font.PLAIN, 18));
		add(jp1jf6);

		jp1jb1.addMouseListener(new MouseAdapter() { // ����

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp1jb1.setFont(new Font("����", Font.BOLD, 18));
				jp1jb2.setFont(new Font("����", Font.PLAIN, 16));
				jp1jb3.setFont(new Font("����", Font.PLAIN, 16));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp1jb1);
				add(jp1jb2);
				add(jp1jb3);
				add(jp1jb11);
				add(jp1jb12);
				add(jp1jl1);
				add(jp1jf1);
				add(jp1jl2);
				add(jp1jf2);
				add(jp1jl3);
				add(jp1jf3);
				add(jp1jl4);
				add(jp1jf4);
				add(jp1jl5);
				add(jp1jf5);
				add(jp1jl6);
				add(jp1jf6);
				repaint();
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}

		});

		jp1jb11.addActionListener(new ActionListener() { // ���뱣��

			public void actionPerformed(ActionEvent e) {
				if (((jp1jf4.getText()).equals(""))
						|| ((jp1jf5.getText()).equals(""))
						|| ((jp1jf6.getText()).equals(""))) {

					jdjll.setText("��Ϣ��д��������");			
				}else if (!isNumber(jp1jf6.getText())||Integer.parseInt(jp1jf6.getText()) <= 0||Integer.parseInt(jp1jf6.getText()) >= 300) {
					jdjll.setText("�����ѧ�����޲�����Ҫ��ӦΪ0-300��������");
				}else if (!isNumber(jp1jf5.getText())) {
					jdjll.setText("�����ѧ�����޲�����Ҫ��ӦΪ�Ǹ�������");
				}else if (!isOKNumber(jp1jf4.getText())) {
					jdjll.setText("����ѧ�ڽ���1-8ѧ��!��ͬѧ��֮���ԡ�Ӣ�Ķ��š�������");
				}
				else {
					RMIFactory factory = Client.getFactory();
					try {
						FrameBLService fqservice = factory.getFQService();
						FrameVO vo = new FrameVO((String)jp1jf1.getSelectedItem(),(String)jp1jf2
								.getSelectedItem(), (String)jp1jf3.getSelectedItem(), jp1jf4.getText(),
								Integer.parseInt(jp1jf5.getText()), Integer
										.parseInt(jp1jf6.getText()));
						int result = fqservice.addFrameVO(vo);

						switch (result) {
						case 1:
							jdjll.setText("��������ܳɹ���");
							break;
						case 0:
							jdjll.setText("��ģ���Ѵ��ڣ�");
							break;
						case -1:
							jdjll.setText("ѧ������С��ѧ�����ޣ�");
							break;

						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});
		
		jp1jb12.addActionListener(new ActionListener() { // ȡ��

			public void actionPerformed(ActionEvent e) {
				
				removeAll();
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp1jb1);
				add(jp1jb2);
				add(jp1jb3);
				add(jp1jb11);
				add(jp1jb12);

				jp1jf4.setText("");
				jp1jf5.setText("");
				jp1jf6.setText("");
				
				add(jp1jl1);
				add(jp1jf1);
				add(jp1jl2);
				add(jp1jf2);
				add(jp1jl3);
				add(jp1jf3);
				add(jp1jl4);
				add(jp1jf4);
				add(jp1jl5);
				add(jp1jf5);
				add(jp1jl6);
				add(jp1jf6);
				repaint();

			}

		});
		
		// ------------------------�޸���鿴---------------------
		String[][] courModuleList = null;
		String[] jp1jt1Names = { "ID", "�γ�ģ��", "�γ�����", "�γ����", "����ѧ��",
				"ѧ����","ѧ����" };
		DefaultTableModel jp1deftbmd1 = new DefaultTableModel(
				courModuleList, jp1jt1Names);
		// ------------------------�޸�------------------------		
		final JTable jp1jt1 = new JTable(jp1deftbmd1){
			private static final long serialVersionUID = 1L;
			boolean[] isEditable = { false, true, true, true,
					true, true, true };

			public boolean isCellEditable(int r, int c) {
				return isEditable[c];
			}
		};

		jp1jt1.getTableHeader().setFont(
				new Font("����", Font.PLAIN, 14));
		jp1jt1.setFont(new Font("����", Font.PLAIN, 14));
		jp1jt1.getColumn("ID").setWidth(2);
		jp1jt1.getColumn("�γ�ģ��").setMinWidth(80);
		jp1jt1.getColumn("�γ�����").setMinWidth(80);
		jp1jt1.getColumn("�γ����").setMinWidth(130);	
		jp1jt1.getColumn("����ѧ��").setMinWidth(100);
		final DefaultTableModel model =(DefaultTableModel) jp1jt1.getModel();
		
		final JButton jp1jb21 = new JButton("�޸ı���");
		jp1jb21.setBounds(width*2/12, (int) (height*((1.0/2.0)+(1.0/10))+10), 100, 35);
		//System.out.println(2/3);
		jp1jb21.setFont(new Font("����", Font.PLAIN, 18));
		
		final JButton jp1jb22 = new JButton("ɾ��");  
		jp1jb22.setBounds(width*2/12+150, (int) (height*((1.0/2.0)+(1.0/10))+10), 100, 35);
		jp1jb22.setFont(new Font("����", Font.PLAIN, 18));
		
		final JLabel deleteLabel1 = new JLabel("֧���Ҽ�ɾ��");
		deleteLabel1.setBounds(410, 290, 100, 20);
		deleteLabel1.setFont(new Font("����", Font.PLAIN, 14));
		
		final JLabel deleteLabel2 = new JLabel("������ɾ��");
		deleteLabel2.setBounds(410, 310, 100, 20);
		deleteLabel2.setFont(new Font("����", Font.PLAIN, 14));
		
		jp1jb2.addMouseListener(new MouseAdapter() { // �޸�

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp1jb1.setFont(new Font("����", Font.PLAIN, 16));
				jp1jb2.setFont(new Font("����", Font.BOLD, 18));
				jp1jb3.setFont(new Font("����", Font.PLAIN, 16));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp1jb1);
				add(jp1jb2);
				add(jp1jb3);
				add(jp1jb21); //�޸ı���
				add(jp1jb22); //ɾ��
	
				RMIFactory factory = Client.getFactory();
				while(model.getRowCount()>0){
				      model.removeRow(model.getRowCount()-1);
				 }
				
				try {
					FrameBLService fqservice = factory.getFQService();
					ArrayList<FrameVO> frameList = fqservice.getFrameList();

					for (int i = 0; i < frameList.size(); i++) {
						FrameVO frame = frameList.get(i);
						String[] row = {
								frame.getId()+"",
								frame.getCourseModule(),
								frame.getNature(),
								frame.getCategory(),
								frame.getSemester() + "",
								frame.getCreditLower() + "",
							    frame.getCreditUpper()+"" };
						model.addRow(row);
					}

					final JScrollPane jp1jsp1 = new JScrollPane(jp1jt1);
					jp1jsp1.setBounds(width/12,height/20+10, width*7/9, height/2);
					jp1jsp1.getWidth();
					//jp1jsp1.setA//setHorizontalAlignment(SwingConstants.CENTER);
//					System.out.println(width);
//					System.out.println(height);
//					System.out.println(jp1jsp1.getWidth());
					add(jp1jsp1);

					repaint();		
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
			}

		});
		
		jp1jb21.addActionListener(new ActionListener() {    //�޸ı���

			public void actionPerformed(ActionEvent e) {

				RMIFactory factory = Client.getFactory();
				
				try {
					FrameBLService fqservice = factory.getFQService();
					ArrayList<FrameVO> frameList = fqservice.getFrameList();
					
					int row = jp1jt1.getSelectedRow();
					if (row >= 0) {	
						int oldFrameId = frameList.get(row).getId();
						if(!isNumber((String)jp1jt1.getValueAt(row, 5))){
							jdjll.setText("�����ѧ�����޲�����Ҫ��ӦΪ�Ǹ�������");
						}else if(!isNumber((String)jp1jt1.getValueAt(row, 6))||Integer.parseInt((String)jp1jt1.getValueAt(row, 6))<=0){
							jdjll.setText("�����ѧ�����޲�����Ҫ��ӦΪ��������");
						}else if(!isOKNumber((String)jp1jt1.getValueAt(row, 4))){
							jdjll.setText("����ѧ�ڽ���1-8ѧ��!��ͬѧ��֮���ԡ�Ӣ�Ķ��š�������");
						}else{
					
						     FrameVO newFrameVO = new FrameVO((String)jp1jt1.getValueAt(row, 1), 
								(String)jp1jt1.getValueAt(row, 2), 
								(String)jp1jt1.getValueAt(row, 3), 
								(String)jp1jt1.getValueAt(row, 4),
								Integer.parseInt((String)jp1jt1.getValueAt(row, 5)), 
								Integer.parseInt((String)jp1jt1.getValueAt(row, 6)));
						
						    int result = fqservice.modifyFrameVO(oldFrameId, newFrameVO);
						
						    switch (result) {
						    case 1:
							   jdjll.setText("�޸������ܳɹ���");
							   break;
						    case -2:
							   jdjll.setText("ѧ�����޴���ѧ�����ޣ��������޸ģ�");
							   break;
						    default:
						    }
						}
					} else {
						jdjll.setText("��δѡ���޸�ģ�飡");
					}
					JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޷����ӵ���������������������", "ϵͳ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		
		jp1jb22.addMouseListener(new MouseAdapter() {    //ɾ�����ɶ���ɾ��

			public void mouseClicked(MouseEvent arg0){
                RMIFactory factory = Client.getFactory();
                int[] r = jp1jt1.getSelectedRows();
                
				try {
					FrameBLService fqservice = factory.getFQService();
					ArrayList<FrameVO> frameList = fqservice.getFrameList();
					
					if (r.length > 0) {	
						if (JOptionPane.showConfirmDialog(null,
								"ȷ��ɾ����", "ϵͳ��Ϣ",
								JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
							for (int i = r.length - 1; i >= 0; i--) {
							int deleteFrameId = frameList.get(r[i]).getId();
							fqservice.deleteFrameVO(deleteFrameId);
							 model.removeRow(r[i]);
								 
							}
							repaint();
						}					
					} else {
						jdjll.setText("��δѡ���޸�ģ�飡");
						JOptionPane.showMessageDialog(null, jdjll, "ϵͳ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			public void mouseEntered(MouseEvent arg0) {
				add(deleteLabel1);
				add(deleteLabel2);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent arg0) {
				remove(deleteLabel1);
				remove(deleteLabel2);
				setCursor(Cursor.getDefaultCursor());
			}
		});
		
		jp1jt1.addMouseListener(new MouseAdapter(){  //�Ҽ�ɾ��
			public void mousePressed(MouseEvent e) {
		        if (e.getButton() == 3) {
		        	JPopupMenu popmenu = new JPopupMenu();
		        	JMenuItem item = new JMenuItem("ɾ �� ");
		        	item.setFont(new Font("����", Font.BOLD, 15));
		        	item.addActionListener(new ActionListener(){

		        		public void actionPerformed(ActionEvent e) {
		        			RMIFactory factory = Client.getFactory();

		    				try {
		    					FrameBLService fqservice = factory.getFQService();
		    					ArrayList<FrameVO> frameList = fqservice.getFrameList();
		    					
		    					int row = jp1jt1.getSelectedRow();
		    					if (row >= 0) {		
		    						int deleteFrameId = frameList.get(row).getId();
		    						
		    						if (JOptionPane.showConfirmDialog(null,
		    								"ȷ��ɾ����ģ�飿", "ϵͳ��Ϣ",
		    								JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
		    							int deleteResult = fqservice.deleteFrameVO(deleteFrameId);
		    							
		    							if(deleteResult==1){
		    						         model.removeRow(row);
		    								 repaint();
		    							}
		    						}					
		    					}
		    				} catch (RemoteException e1) {
		    					e1.printStackTrace();
		    				}
		        		}

		        	});
		        	popmenu.add(item);
		        	if(jp1jt1.getSelectedRow()!=-1){
		        		popmenu.show(jp1jt1, e.getX(), e.getY());
		        	}
		        }

		    }
		});
		
		// ------------------------�鿴---------------------		
	    final JTable jp1jt2 = new JTable(jp1deftbmd1){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		jp1jt2.getTableHeader().setFont(
				new Font("����", Font.PLAIN, 14));
		jp1jt2.setFont(new Font("����", Font.PLAIN, 14));
		jp1jt2.getColumn("ID").setWidth(2);
		jp1jt2.getColumn("�γ�ģ��").setMinWidth(80);
		jp1jt2.getColumn("�γ�����").setMinWidth(80);
		jp1jt2.getColumn("�γ����").setMinWidth(130);	
		jp1jt2.getColumn("����ѧ��").setMinWidth(100);
		final DefaultTableModel model1 =(DefaultTableModel) jp1jt2.getModel();
		
		jp1jb3.addMouseListener(new MouseAdapter() { // �鿴

			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				jp1jb1.setFont(new Font("����", Font.PLAIN, 16));
				jp1jb2.setFont(new Font("����", Font.PLAIN, 16));
				jp1jb3.setFont(new Font("����", Font.BOLD, 18));
				add(jl1);
				add(jl2);
				add(jl3);
				add(jp1jb1);
				add(jp1jb2);
				add(jp1jb3);

				RMIFactory factory = Client.getFactory();
				while(model1.getRowCount()>0){
				      model1.removeRow(model1.getRowCount()-1);
				 }
				
				try {
					FrameBLService fqservice = factory.getFQService();
					ArrayList<FrameVO> frameList = fqservice.getFrameList();

					for (int i = 0; i < frameList.size(); i++) {
						FrameVO frame = frameList.get(i);
						String[] row = {
								frame.getId()+"",
								frame.getCourseModule(),
								frame.getNature(),
								frame.getCategory(),
								frame.getSemester() + "",
								frame.getCreditLower() + "",
								frame.getCreditUpper() + ""};
						model1.addRow(row);
					}

					final JScrollPane jp1jsp1 = new JScrollPane(jp1jt2);
					jp1jsp1.setBounds(width/12,height/20+10, width*7/9, height/2);
					add(jp1jsp1);
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
	
	public boolean isOKNumber(String s) {
		boolean isOKNumber = true;
		char ch[] = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (!(ch[i]>'0'&&ch[i]<'9'||ch[i]==',')) {
				isOKNumber = false;
			}
		}
		return isOKNumber;
	}

}
