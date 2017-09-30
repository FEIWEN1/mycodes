package com.personal.base.until;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * TODO:分页的封装类，目前支持以下属性：1.页码 2.页面大小 3. 总页数 4.结果集 5.是否有上一页 6.是否有下一页 7.总记录数
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月17日/下午4:22:33
 * 
 */
public class Page<T> {
	/** 公共变量*/
	private static final String ASC="asc";
	private static final String DESC="desc";
	/** 分页参数*/
	private int pageNo=1;
	private int pageSize=-1;
	private String orderBy=null;//排序字段
	private String order=null;//正序、倒序
	private int pageCount;//总页数
	
	/** 返回结果*/
	private List<T> result = Lists.newArrayList();
	private long totalCount = -1;
	
	
	/**
	 * 构造函数
	 * 传入
	 */
	public Page(int pageNo, int pageSize){
		this.pageNo=pageNo<1?1:pageNo;
		this.pageSize=pageSize<1?10:pageSize;
	}
	/** 获取当前的页号 序号从1开始，默认为一*/
	public int getPageNo() {
		return pageNo;
	}
	/** 获得每页的记录数量，默认为-1 */
	public int getPageSize(){
		return pageSize;
	}
	/** 获得排序字段，多个排序字段用‘，’分隔 */
	public String getOrderBy(){
		return orderBy;
	}
	/** 设置排序字段，多个排序字段用‘，’分隔 */
	public void setOrderBy(final String orderBy){
		this.orderBy=orderBy;
	}
	/** 获得排序方向 */
	public String getOrder(){
		return order;
	}
	/** 设置排序方向 */
	public void setOrder(final String order){
		String lowcaseOrder=StringUtils.lowerCase(order);
		String[] orders=StringUtils.split(lowcaseOrder, ',');
		for(String orderStr:orders){
			if(!StringUtils.equals(DESC, orderStr)&&!StringUtils.equals(ASC, orderStr)){
				throw new IllegalArgumentException("排序方向"+orderStr+"不是合法值");
			}
		}
		this.order=lowcaseOrder;
	}
	/** 获得页内的记录列表 */
	public List<T> getResult(){
		return result;
	}
	/** 设置页内记录列表 */
	public void setResult(final List<T> result) {
		this.result = result;
	}
	/** 获得总记录数，默认值为-1 */
	public long getTotalCount(){
		return totalCount;
	}
	/** 设置总记录数 */
	public void setTotalCount(final long totalCount){
		this.totalCount=totalCount;
	}
	/** 获取总页数*/
	public int getPageCount(){
		pageCount=((int)this.totalCount+pageSize-1)/pageSize;
		return pageCount;
	}
	/** 是否还有下一页*/
	public boolean isHasNext(){
		return (pageNo+1<=getPageCount());
	}
	/**  是否有上一页 */
	public boolean isHasPre(){
		return (pageNo-1>=1);
	}
	
	/**
	 * TODO:根据pageNo和pageSize计算当前页第一条记录在总结果集的位置，序号从0开始,用于mysql
	 * 
	 * @return
	 */
	public int getOffset(){
		return (pageNo-1)*pageSize;
	}
	/**
	 * TODO:根据pageNo和pageSize计算当前页第一条记录在总结果集的位置，序号从1开始,用于oracle
	 * 
	 * @return
	 */
	public int getStartRow(){
		return getOffset()+1;
	}
	/**
	 * TODO:根据pageNo和pageSize计算当前页最后一条记录在总结果集中的位置, 序号从1开始.用于oracle
	 * 
	 * @return
	 */
	public int getEndRow(){
		return pageSize*pageNo;
	}
}
