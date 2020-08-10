package com.bdsip.common.core.json.gson.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bdsip.common.core.json.gson.strategy.IgnoreAnnotationExclusionStrategy;
import com.bdsip.common.core.json.gson.typeadaptor.CalendarSerializerAndDeserializer;
import com.bdsip.common.core.json.gson.typeadaptor.DateSerializerAndDeserializer;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GSON使用的工具类。
 * 一般情况下，请使用createCommonBuilder方法来创建GsonBuilder，然后再获得Gson对象，而尽量不要直接构建Gson对象。
 * 如果需要增加自定义的格式控制，可以在利用createCommonBuilder创建GsonBuilder之后，再给该GsonBuilder增加自定义能力。
 * @author wangqiang
 * 2012-6-16 上午07:27:27
 */
public class GsonUtils {
    
    /**
     * 单例对象，不可以被修改。
     */
    private static final GsonBuilder commonGsonBuilder = GsonUtils.createCommonBuilder(); 
    
    /**
     * 使用常用配置创建一个GsonBuilder实例。用户使用该方法获取的GsonBuilder对象，还可以继续根据用户的需求进一步进行调整。
     * 2012-6-16 上午07:28:34 wangqiang添加此方法
     * @return 一个全新的GsonBuilder实例。
     */
    public static GsonBuilder createCommonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .addSerializationExclusionStrategy(new ExclusionStrategy() { 
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttr) {
                    if (DateFormat.class.isAssignableFrom(fieldAttr.getDeclaredClass()) ) {
                        return true;
                    } else if (Gson.class.isAssignableFrom(fieldAttr.getDeclaredClass())) {
                        return true;
                    } else {
                        return false;
                    }
                }
                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .addSerializationExclusionStrategy(new IgnoreAnnotationExclusionStrategy())
            .addDeserializationExclusionStrategy(new IgnoreAnnotationExclusionStrategy())
            .registerTypeHierarchyAdapter(Date.class, new DateSerializerAndDeserializer())
            .registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializerAndDeserializer())
            .serializeNulls()
            .disableInnerClassSerialization();
        
        return gsonBuilder;
    }
    
    /**
     * 获取一个根据公用配置创建的Gson对象，以提升性能该对象。
     * 该方法首先使用一个预先根据常用配置创建好的一个GsonBuilder实例，创建出一个Gson对象，再返回该Gson对象。
     * GsonBuilder是一个公用的现成的对象，使用它可以避免重复创建GsonBuilder对象，不可以被改变以增加用户自定义行为。
     * 2012-6-16 上午07:28:34 wangqiang添加此方法
     * @return 一个全新的GsonBuilder实例。
     */
    public static Gson fetchCommonGson() {
        return GsonUtils.commonGsonBuilder.create();
    }
}
