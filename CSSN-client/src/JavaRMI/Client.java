package JavaRMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.Naming;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceStripeWatermark;

import presentation.userui.LoginUI;

public class Client {
	public static RMIFactory factory;

	public static void main(String[] args) {
	
//
//		try {
//			//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
//			UIManager.setLookAndFeel(new SubstanceGreenMagicLookAndFeel());
//			JFrame.setDefaultLookAndFeelDecorated(true);
//			JDialog.setDefaultLookAndFeelDecorated(true);
//			SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
//			// SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
//			// SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
//			// SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
//			// SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
//			// SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
//			 //SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
//			} catch (Exception e) {
//			System.err.println("Something went wrong!");
//			}

		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);// 隐藏设置
		BeautyEyeLNFHelper.translucencyAtFrameInactive = true;// 关闭半透明效果
		BeautyEyeLNFHelper.frameBorderStyle=BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
//		try {
//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		new LoginUI();
		
	}

	public static RMIFactory getFactory() {
		if(factory==null){
			try {
				File file=new File("file/url.txt");
				FileReader fr=new FileReader(file);
				BufferedReader bufr=new BufferedReader(fr);
				String url=bufr.readLine();
				bufr.close();
				factory = (RMIFactory) Naming
						.lookup("rmi://"+url+":9999/factory");
			}  catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "连接服务器超时，登录失败", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		return factory;
	}

}
