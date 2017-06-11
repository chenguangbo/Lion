package cn.cgb.store.domain;

import java.util.List;

public class PageBean <T>{
	private int pageNumber;//当前页(浏览器传递)
	private int pageSize; //每页显示个数(固定值,可以是浏览器传递)
	private int totalRecord;//总记录数(db查询获得)
	
	
	private int totalPage; //总分页数量
	private int startIndex;//开始索引
	
	
	private List<T> data;//分页数据(DB获取)

	private String url;
	
	//${pageBean.printPage}
	public String getPrintPage(){
		StringBuffer buf=new StringBuffer();
		buf.append("<div style=\"width:100%;margin:0 auto;margin-top:50px;\" align=\"center\">");
		buf.append("第"+this.pageNumber+"/"+this.totalPage+"页&nbsp;&nbsp;");
		buf.append("");
		buf.append("");
		buf.append("");
		buf.append("");
	
		return buf.toString();
	}
	
	
	public PageBean(int pageNumber,int  totalRecord,int  pageSize) {
		this.pageNumber=pageNumber;
		this.totalRecord=totalRecord;
		this.pageSize=pageSize;
		
		this.startIndex=(pageNumber-1)*pageSize;
		
		totalPage=(totalRecord%pageSize==0)?(totalRecord/pageSize):(totalRecord/pageSize+1);
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}
