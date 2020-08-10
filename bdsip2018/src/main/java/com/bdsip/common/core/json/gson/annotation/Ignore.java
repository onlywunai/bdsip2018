/**
 * 
 */
package com.bdsip.common.core.json.gson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Gson进行和反序列化时，使用了本标注的属性将一定被忽略掉。
 * 本标注需要和IgnoreAnnotationExclusionStrategy 排除策略一起使用，否则将不会生效
 * @author wangqiang
 * 2014年2月21日 上午11:34:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {

}
