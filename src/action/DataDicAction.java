package action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DataDic;
import model.DataDicType;
import model.PageBean;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import util.DbUtil;
import util.NavUtil;
import util.PageUtil;
import util.PropertiesUtil;
import util.ResponseUtil;
import util.StringUtil;

import com.opensymphony.xwork2.ActionSupport;

import dao.DataDicDao;
import dao.DataDicTypeDao;

public class DataDicAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private DataDicDao dataDicDao=new DataDicDao();
	private DataDicTypeDao dataDicTypeDao=new DataDicTypeDao();
	private List<DataDic> dataDicList=new ArrayList<DataDic>();
	private List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();
	private String mainPage; 
	private String navCode;
	
	private String s_ddTypeName;
	
	private String page;
	private int total;
	private String pageCode;
	
	private String ddId;
	
	private DataDic dataDic;
	
	
	
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}

	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}

	public DataDic getDataDic() {
		return dataDic;
	}

	public void setDataDic(DataDic dataDic) {
		this.dataDic = dataDic;
	}

	public String getDdId() {
		return ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public List<DataDic> getDataDicList() {
		return dataDicList;
	}

	public void setDataDicList(List<DataDic> dataDicList) {
		this.dataDicList = dataDicList;
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

	public String getS_ddTypeName() {
		return s_ddTypeName;
	}

	public void setS_ddTypeName(String s_ddTypeName) {
		this.s_ddTypeName = s_ddTypeName;
	}

	public String list(){
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Connection con=null;
		try{
			HttpSession session=request.getSession();
			DataDic s_dataDic=new DataDic();
			if(s_ddTypeName!=null){
				s_dataDic.setDdTypeName(s_ddTypeName);
				session.setAttribute("s_ddTypeName", s_ddTypeName);
			}else{
				Object o=session.getAttribute("s_ddTypeName");
				if(o!=null){
					s_ddTypeName=(String)session.getAttribute("s_ddTypeName");
					s_dataDic.setDdTypeName(s_ddTypeName);
				}
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			con=dbUtil.getCon();
			dataDicList=dataDicDao.dataDicList(con,s_dataDic,pageBean);
			total=dataDicDao.dataDicCount(con, s_dataDic);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/dataDic!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			navCode=NavUtil.getNavgation("系统管理", "数据字典维护");
			mainPage="dataDic/dataDicList.jsp";
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
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	public String preSave(){
		Connection con=null;
		try{
		dataDicTypeList=dataDicTypeDao.dataDicTypeList(con);
		if(StringUtil.isEmpty(ddId)){
			dataDic=dataDicDao.getDataDicById(con, ddId);
			navCode=NavUtil.getNavgation("系统管理", "数据字典修改");
		}else{
			navCode=NavUtil.getNavgation("系统管理", "数据字典添加");
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
		mainPage="dataDic/dataDicSave.jsp";
		return SUCCESS;
		
	}
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(ddId)){
				dataDic.setDdId(Integer.parseInt(ddId));
				dataDicDao.dataDicUpdate(con, dataDic);
			}else{
				dataDicDao.dataDicAdd(con, dataDic);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.getCon();
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
			dataDicDao.dataDicDelete(con, ddId);
			resultJson.put("success",true);
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
