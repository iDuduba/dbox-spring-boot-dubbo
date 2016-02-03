package org.dbox.sample.dubbo.consumer.web;

import com.alibaba.dubbo.config.annotation.Reference;
import org.dbox.sample.dubbo.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class DemoController {

    @Reference(version = "1.0.0")
    private DemoService demoService;

//    public void setDemoService(DemoService demoService) {
//        this.demoService = demoService;
//    }

    @RequestMapping("/hi/{name}")
    public String hi(@PathVariable("name") String name) throws Exception {
        String result = ">>.<<";
        try {
            result = demoService.sayHello(name);
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}