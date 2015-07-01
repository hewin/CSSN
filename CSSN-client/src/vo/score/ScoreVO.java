package vo.score;

import java.io.Serializable;

public class ScoreVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String stuNO;
	String courseNO;
	int score;
	int credit;
	
	public ScoreVO(){}
	public ScoreVO(String stuNO,String courseNO,int score){
		this.stuNO = stuNO;
		this.courseNO = courseNO;
		this.score = score;
	}
	
	public ScoreVO(String stuNO,String courseNO,int score,int credit){
		this.stuNO = stuNO;
		this.courseNO = courseNO;
		this.score = score;
		this.credit=credit;
	}
	
	public String getStuNO(){
		return this.stuNO;
	}
	
	public String getCourseNO(){
		return this.courseNO;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getCredit(){
		return this.credit;
	}
	
	public void setStuNO(String stuNO){
		this.stuNO = stuNO;
	}
	
	public void setCourseNO(String courseNO){
		this.courseNO = courseNO;
	}
	
	public void setScore(int score){
		this.score = score;
	}
}
