//package org.example.convert;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author Jdx
// * @version 1.0
// * @description 将string类型转换为date类型
// * @date 2021/4/15 16:33
// */
//@Component
//public class DateConvert implements Converter<String, Date> {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//    @Override
//    public Date convert(String source) {
//        if (StringUtils.isNotBlank(source) && !"".equals(source)) {
//            try {
//                return sdf.parse(source);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//}