package org.springframework.boot.autoconfigure.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Exporter.class)
public class DubboAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public AnnotationBean annotationBean(@Value("${spring.dubbo.basePackages}") String basePackages) {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(basePackages);
        annotationBean.setApplicationContext(applicationContext);
        return annotationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.dubbo.application")
    public ApplicationConfig requestApplicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.dubbo.registry")
    public RegistryConfig requestRegistryConfig() {
        return new RegistryConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.dubbo.protocol")
    public ProtocolConfig requestProtocolConfig() {
        return new ProtocolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.dubbo.provider")
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig,
                                         RegistryConfig registryConfig,
                                         ProtocolConfig protocolConfig) {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);
        return providerConfig;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
