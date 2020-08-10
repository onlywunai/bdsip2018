package com.bdsip.common.dto.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bdsip.common.core.json.gson.annotation.Ignore;
import com.bdsip.common.core.json.gson.utils.GsonUtils;
import com.google.gson.Gson;

/**
 * 可用于分页或不分页的数据列表DTO
 * @author wangqiang
 * 2014年5月9日 上午11:21:03
 * @param <T>
 */
public class ListResult<T> implements Serializable {

    private static final long serialVersionUID = 5630518079179620343L;
    
    @Ignore
    private Gson gson = GsonUtils.createCommonBuilder().create();
    
    /**
     * 是否未鉴权（可能导致页面重定向到登陆页面）
     */
    private boolean unauthenticated = false;

    /**
     * 请求是否成功的标识
     */
    private boolean success;
    
    /**
     * 消息文本。如果操作失败，一般会存放失败原因。
     */
    private String message;
    
    /**
     * 返回的数据。可能为空。
     */
    private List<T> data;
    
    /**
     * 返回的数据(jquery)。可能为空。
     */
    private List<T> rows;
    
    /**
     * 如果有返回分页数据，则代表不分页情况下的数据总数。
     */
    private long total;
    
    public ListResult() {
        this(true, "");
    }

    public ListResult(boolean success, String message) {
        this(false, success, message);
    }

    public ListResult(boolean unauthenticated, boolean success, String message) {
        this.unauthenticated = unauthenticated;
        this.success = success;
        this.message = message;
        this.data = new ArrayList<T>();
    }

    /**
     * 该方法来自PageResult，后者于2015-12-24合并到本类中。
     * 构造函数
     * @param data
     * @param total
     */
    public ListResult(List<T> data, long total) {
        this();
        this.data.addAll(data);
        this.total = total;
    }
    
    /**
     * 获取属性  unauthenticated 的值
     * @return 属性 unauthenticated 的值
     */
    public boolean isUnauthenticated() {
        return this.unauthenticated;
    }
    
    /**
     * 给属性 unauthenticated 赋值
     * @param unauthenticated 将要给属性 unauthenticated 赋予的值
     */
    public void setUnauthenticated(boolean unauthenticated) {
        this.unauthenticated = unauthenticated;
    }

	public boolean isSuccess() {
    	return this.success;
    }

	public void setSuccess(boolean success) {
    	this.success = success;
    }

	public String getMessage() {
    	return this.message;
    }

	public void setMessage(String message) {
    	this.message = message;
    }

	public List<T> getData() {
    	return this.data;
    }

	public void setData(List<T> data) {
		if (data != null) {
			this.data = data;
		}
    }

	public long getTotal() {
    	return this.total;
    }

	public void setTotal(long total) {
    	this.total = total;
    }
	
	public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
	
	/**
	 * 将本对象以JSON的格式打印出来。
	 * 原来该方法基于flexjson实现。现在直接修改为基于Gson的实现。
     * 注意不要使用可构成对象导航闭环的data数据。
     * 2013-6-27 下午8:58:35 wangqiang添加此方法
	 */
	public String toString() {
        return this.gson.toJson(this);
	}
}
