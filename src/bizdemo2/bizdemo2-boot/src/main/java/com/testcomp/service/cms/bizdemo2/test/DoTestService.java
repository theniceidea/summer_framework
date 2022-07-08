package com.testcomp.service.cms.bizdemo2.test;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.testcomp.summer.v1.cms.bizdemo.demotable.AddDemotable;
import com.testcomp.summer.v1.cms.bizdemo2.test.DoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.util.List;

@Service
@SummerService
public class DoTestService  implements SummerServiceBean<DoTest> {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serviceAddr;

    @Override
    public void sum(DoTest summer) {
        try {
            final NamingService namingService = NamingFactory.createNamingService(serviceAddr);
            final List<Instance> allInstances = namingService.getAllInstances("microservice-bizdemo");
            System.out.println("");
        } catch (NacosException e) {
            e.printStackTrace();
        }
//        final AddDemotable addDemotable = new AddDemotable();
//        addDemotable.sum();
    }
}
