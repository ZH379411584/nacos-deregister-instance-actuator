package com.daxiyan.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;


@ConditionalOnClass(Endpoint.class)
@ConditionalOnBean(type = "com.alibaba.cloud.nacos.NacosDiscoveryProperties")
public class NacosDeRegisterInstanceEndpointAutoConfiguration {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @ConditionalOnMissingBean
    @Bean
    public NacosDeRegisterInstanceEndpoint nacosDeRegisterEndpoint() {
        return new NacosDeRegisterInstanceEndpoint(nacosDiscoveryProperties);
    }


}
