package com.daxiyan.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;


@Endpoint(id = "nacos-deregister-instance")
public class NacosDeRegisterInstanceEndpoint {

    public static Logger logger = LoggerFactory.getLogger(NacosDeRegisterInstanceEndpoint.class);

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    public NacosDeRegisterInstanceEndpoint(NacosDiscoveryProperties nacosDiscoveryProperties) {
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
    }

    @ReadOperation
    public String deregisterInstance() {
        String serviceName = nacosDiscoveryProperties.getService();
        String groupName = nacosDiscoveryProperties.getGroup();
        String clusterName = nacosDiscoveryProperties.getClusterName();
        String ip = nacosDiscoveryProperties.getIp();
        int port = nacosDiscoveryProperties.getPort();
        logger.info("deregister from nacos, serviceName:{}, groupName:{}, clusterName:{}, ip:{}, port:{}", serviceName, groupName, clusterName, ip, port);
        try {
            nacosDiscoveryProperties.namingServiceInstance().deregisterInstance(serviceName, groupName, ip, port, clusterName);
        } catch (NacosException e) {
            logger.error("deregister from nacos error", e);
            return "error";
        }
        return "success";
    }

}
