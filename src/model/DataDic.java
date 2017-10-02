package model;

public class DataDic {
	private int ddId;
	private int ddTypeId=-1;
	private String ddValue;
	private String ddDesc;
	private String ddTypeName;
	
	public String getDdTypeName() {
		return ddTypeName;
	}
	public void setDdTypeName(String ddTypeName) {
		this.ddTypeName = ddTypeName;
	}
	public int getDdId() {
		return ddId;
	}
	public void setDdId(int ddId) {
		this.ddId = ddId;
	}
	public int getDdTypeId() {
		return ddTypeId;
	}
	public void setDdTypeId(int ddTypeId) {
		this.ddTypeId = ddTypeId;
	}
	public String getDdValue() {
		return ddValue;
	}
	public void setDdValue(String ddValue) {
		this.ddValue = ddValue;
	}
	public String getDdDesc() {
		return ddDesc;
	}
	public void setDdDesc(String ddDesc) {
		this.ddDesc = ddDesc;
	}
	
	

}
