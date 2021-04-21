package org.example.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;
import java.util.Set;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 10:44
 */
public class SimpleBeanSerializeModifier extends BeanSerializerModifier {
    private JsonSerializer<Object> stringJsonSerializer = new StringJsonSerializer();
    private JsonSerializer<Object> arrayJsonSerializer = new ArrayJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        //替换内部的方法,循环所有的beanPropertyWriter
        for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
            if (isObjectType(beanPropertyWriter, String.class)) {
                //重新设置参数类型,分配空值转换器
                beanPropertyWriter.assignNullSerializer(stringJsonSerializer);
            }
            if (isArrayType(beanPropertyWriter)) {
                //是集合数据
                beanPropertyWriter.assignNullSerializer(arrayJsonSerializer);
            }
        }
        return beanProperties;
    }


    /**
     * 判断对象类型是否相同
     */
    private boolean isObjectType(BeanPropertyWriter writer, Object obj) {
        Class<?> clazz = writer.getPropertyType();
        return clazz.equals(obj);
    }

    /**
     * 判断是否是集合
     *
     * @param writer
     * @return
     */
    protected boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getPropertyType();
        return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
    }
}
