package org.dbox.sample.dubbo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDubboProviderApplication implements CommandLineRunner {

    @Value("${spring.dubbo.basePackages}")
    private String basePackages;

    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private RegistryConfig registryConfig;
    @Autowired
    private ProtocolConfig protocolConfig;
    @Autowired
    private ProviderConfig providerConfig;
    @Autowired
    private AnnotationBean annotationBean;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboProviderApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(applicationConfig.toString());
    }
}
