package org.example.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Jdx
 * @version 1.0
 * @description 空字符串不返回null，返回空字符串
 * @date 2021/4/16 10:51
 */
public class StringJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //如果返回值为null，则将其设置为空字符串
        if (value == null) {
            gen.writeString("");
            return;
        }
        gen.writeObject(value);
    }
}
