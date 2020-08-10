package com.bdsip.common.dto.web;

import java.io.Serializable;

import com.bdsip.common.core.json.gson.annotation.Ignore;
import com.bdsip.common.core.json.gson.utils.GsonUtils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;

public class SingleResult<T> implements Serializable {

    private static final long serialVersionUID = 8172315972492346878L;

    /**
     * 通用序列化器，如需特殊处理，则必须自定义序列化器。
     */
    @Ignore
    private Gson gson = GsonUtils.createCommonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            if (fa.getName().equals("gson") || fa.getName().equals("jsonSer")) {
                return true;
            } else {
                return false;
            }
        }
        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }
    }).create();
    
    /**
     * 是否未鉴权（可能导致页面重定向到登陆页面）
     */
    private boolean unauthenticated = false;

    private boolean success;
    
    private String message;
    
    private T data;
    
    public SingleResult() {
    	this(true, "", null);
    }

    public SingleResult(boolean success, String message) {
    	this(success, message, null);
    }

    public SingleResult(boolean success, String message, T data) {
        this(false, success, message, null);
    }

    public SingleResult(boolean unauthenticated, boolean success, String message, T data) {
        this.unauthenticated = unauthenticated;
        this.success = success;
        this.message = message;
        this.data = data;
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

	public T getData() {
    	return this.data;
    }

	public void setData(T data) {
    	this.data = data;
    }
	
	/**
	 * 将本对象以JSON的格式打印出来。
     * 使用Gson实现的toString方法。
     * 注意不要使用可构成对象导航闭环的data数据。
     * 2013-6-27 下午8:58:35 wangqiang添加此方法
	 */
	public String toString() {
        return this.gson.toJson(this);
	}
	
    /**
     * 该方法原来来自flexjson的实现。而Gson默认include所有属性。因此迁移到Gson后，该方法无需提供实现。
     * 2015年1月21日 下午10:23:45 wangqiang添加此方法
     * @deprecated 保留该方法是为了让原来使用flexjson的代码，可以顺利迁移到使用GsonUtils的代码。
     * @param propNames 要被序列化的属性名。
     * @return 
     */
    @Deprecated
	public SingleResult<T> appendJsonIncludes(String... propNames) {
        //just do nothing.
        return this;
	}
    
    /**
     * 修改对象本身的Gson的实现，不序列化给定名称的属性，同时返回ListResult对象本身。
     * 2013-8-9 下午3:13:01 wangqiang添加此方法
     * @deprecated 保留该方法是为了让原来使用flexjson的代码，可以顺利迁移到使用GsonUtils的代码。
     * @param propNames 需要忽略的属性列表
     * @return
     */
    @Deprecated
	public SingleResult<T> appendJsonExcludes(final String... propNames) {
        this.gson = GsonUtils.createCommonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                if (fa.getName().equals("gson") || fa.getName().equals("jsonSer")) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public boolean shouldSkipClass(Class<?> arg0) {
                return false;
            }
        }).addSerializationExclusionStrategy(new ExclusionStrategy() {
            
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                for (String propName : propNames) {
                    if (fa.getName().equals(propName)) {
                        return true;
                    }
                }
                return false;
            }
            
            @Override
            public boolean shouldSkipClass(Class<?> arg0) {
                return false;
            }
        }).create();
        
        return this;
	}
}
