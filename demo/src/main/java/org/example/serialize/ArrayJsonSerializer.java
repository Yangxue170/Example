package org.example.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/16 11:03
 */
public class ArrayJsonSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            //JsonGenerator调用writeStartObject()方法创建JSON数组，并使用write()，创建一个空JSON数组
            gen.writeStartArray();
            gen.writeEndArray();
            return;
        }
        gen.writeObject(value);
    }
}
