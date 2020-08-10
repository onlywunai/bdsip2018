package com.bdsip.common.core.json.gson.strategy;

import com.bdsip.common.core.json.gson.annotation.Ignore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 排除策略，允许序列化和反序列化时，忽略标注了Ignore的属性的策略。
 * @author wangqiang
 * 2014年2月21日 上午11:52:36
 */
public class IgnoreAnnotationExclusionStrategy implements ExclusionStrategy {
    
    /**
     * 根据初始化排除策略对象时指定的类和属性名，忽略所有匹配的对象和属性。
     * 只要有一个 对象、属性名 构成的二元组匹配上了，就排除此属性。
     */
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttr) {
        return fieldAttr.getAnnotation(Ignore.class) != null;
    }

    /**
     * 参看父类中的注释 @see com.google.gson.ExclusionStrategy#shouldSkipClass(java.lang.Class)
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
