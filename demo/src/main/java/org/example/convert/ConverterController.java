package org.example.convert;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/14 20:36
 */
@RestController
@RequestMapping("/my")
public class ConverterController {

    @GetMapping("/hello")
    public Date hello(Date date) {
        return date;
    }

    @GetMapping("date")
    public DateResp resp(Date date) {
        DateResp resp = new DateResp();
        resp.setDate(date);
        return resp;
    }

    @GetMapping("now")
    public DateResp resp() {
        DateResp resp = new DateResp();
        resp.setDate(new Date());
        return resp;
    }
}
