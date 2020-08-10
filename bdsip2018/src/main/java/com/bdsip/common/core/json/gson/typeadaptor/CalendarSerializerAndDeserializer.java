package com.bdsip.common.core.json.gson.typeadaptor;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 用于Calendar的格式化。它默认将日期格式化为 yyyy-MM-dd HH:mm:ss 格式的字符串，同时允许用户指定日期格式以覆盖默认行为。
 * @author wangqiang
 * 2012-6-15 下午05:12:39
 */
public class CalendarSerializerAndDeserializer implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

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
    public CalendarSerializerAndDeserializer() {
        this(DateSerializerAndDeserializer.DefaultPattern);
    }

    /**
     * 指定格式的构造函数
     * @param patterm
     */
    public CalendarSerializerAndDeserializer(String dateFormat) {
        super();
        this.dateFormat = dateFormat;
        this.simpleDateFormat = new SimpleDateFormat(this.dateFormat);
    }
    
    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext context) {
        String value = null;
        if (calendar == null) {
            value = "";
        } else {
            value = this.simpleDateFormat.format(calendar.getTime());
        }
        
        return new JsonPrimitive(value);
    }
    
    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Calendar calendar = Calendar.getInstance();
        if (null != json) {
            try {
                Date date = simpleDateFormat.parse(json.getAsString());
                calendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar;
    }

}
