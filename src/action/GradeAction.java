package action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.Grade;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import util.DbUtil;
import util.NavUtil;
import util.ResponseUtil;
import util.StringUtil;

import com.opensymphony.xwork2.ActionSupport;

import dao.ClassDao;
import dao.GradeDao;

public class GradeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private GradeDao gradeDao=new GradeDao();
	private ClassDao classDao=new ClassDao();
	
	private List<Grade> gradeList=new ArrayList<Grade>();
	
	private String mainPage; 
	private String navCode;
	
	private Grade grade;
	private String gradeId;
	
	
	
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	public String list(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			gradeList=gradeDao.gradeList(con);
			navCode=NavUtil.getNavgation("系统管理", "年级维护");
			mainPage="grade/gradeList.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	
	public String preSave(){
		if(StringUtil.isNotEmpty(gradeId)){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				grade=gradeDao.getGradeById(con, gradeId);
				navCode=NavUtil.getNavgation("系统管理", "年级修改");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			navCode=NavUtil.getNavgation("系统管理", "年级添加");
		}
		mainPage="grade/gradeSave.jsp";
		return SUCCESS;
	}
	
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(gradeId)){
				grade.setGradeId(Integer.parseInt(gradeId));
				gradeDao.gradeUpdate(con, grade);
			}else{
				gradeDao.gradeAdd(con, grade);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "save";
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			boolean exist=classDao.existClass(con, gradeId);
			if(exist){
				resultJson.put("error", "年级下面有班级，不能删除");
			}else{
		    gradeDao.gradeDelete(con, gradeId);
		    resultJson.put("success", true);
			}
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
