package businesslogicservice.scoreblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.score.ScoreVO;

public interface ScoreBLService extends Remote {
	// 查看某学生某门课的成绩
	public ScoreVO checkStuScore(String coNo, String stuNo)
			throws RemoteException;

	// 查看某学生所有课程的成绩
	public ArrayList<ScoreVO> checkStuScore(String stuNo,
			ArrayList<String> courseList) throws RemoteException;

	// 更改成绩(如果发布已经登记过成绩的学生的成绩)
	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	// 登记成绩
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	// 查看成绩单（某一学期的成绩）
	public ArrayList<ScoreVO> checkScoreList(String stuNo, String grade,
			String semester) throws RemoteException;

}