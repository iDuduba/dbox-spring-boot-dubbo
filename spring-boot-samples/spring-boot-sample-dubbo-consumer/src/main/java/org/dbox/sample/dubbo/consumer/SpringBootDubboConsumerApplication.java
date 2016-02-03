package org.dbox.sample.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import org.dbox.sample.dubbo.DemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringBootDubboConsumerApplication implements CommandLineRunner {

    @Reference(version = "1.0.0")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboConsumerApplication.class, args);
    }

    public void run(String... strings) throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                String hello = demoService.sayHello("world" + i);
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }
    }

}
