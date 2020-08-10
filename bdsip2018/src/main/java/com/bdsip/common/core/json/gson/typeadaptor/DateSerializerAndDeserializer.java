package com.bdsip.common.core.json.gson.typeadaptor;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 用于Date的格式化。它默认将日期格式化为 yyyy-MM-dd HH:mm:ss 格式的字符串，同时允许用户指定日期格式以覆盖默认行为
 * @author wangqiang
 * 2012-6-15 下午05:12:39
 */
public class DateSerializerAndDeserializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

    public static final String DefaultPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式转化的样式字符串
     */
    private String dateFormat;
    
    private SimpleDateFormat simpleDateFormat;
    
    public String getDateFormat() {
        return this.dateFormat;
    }
    
    /**
     * 默认构造器
     */
    public DateSerializerAndDeserializer() {
        this(DateSerializerAndDeserializer.DefaultPattern);
    }

    /**
     * 指定格式的构造函数
     * @param patterm
     */
    public DateSerializerAndDeserializer(String dateFormat) {
        super();
        this.dateFormat = dateFormat;
        this.simpleDateFormat = new SimpleDateFormat(this.dateFormat);
    }
    
    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
        String value = null;
        if (date == null) {
            value = "";
        } else {
            value = this.simpleDateFormat.format(date);
        }
        
        return new JsonPrimitive(value);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Date date = new Date();
        if (null != json) {
            try {
                date = simpleDateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
