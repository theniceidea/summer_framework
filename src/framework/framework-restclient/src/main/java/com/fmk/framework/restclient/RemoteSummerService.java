package com.fmk.framework.restclient;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.fmk.framework.daomodel.CurrentTransactionId;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.basic.StringUtil;
import com.fmk.framework.basic.Summer2HttpInfo;
import com.fmk.framework.basic.Summer2UrlHelper;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.deploy.DeployConfig;
import com.fmk.framework.deploy.DeployService;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.session.CurrentUserId;
import com.fmk.framework.session.GetAcceptLanguage;
import com.fmk.framework.web.CurrentRequestId;
import com.fmk.framework.web.RequestDeployVersion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.summerframework.model.*;

import java.util.*;

@Service
@SummerService
public class RemoteSummerService implements SummerServiceBean<RemoteServiceSummer>, ApplicationRunner {
    private static final Logger logger = Logger.getLogger(RemoteSummerService.class);
    private static Logger requestLogger = logger;//Logger.getLogger("requestLog");

    private static HashMap<String, HttpMethod> HTTP_METHODS = new HashMap<>();

    @Value("${app.project.prefix:}")
    private String projectPrefix;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${app.gray.enabled:false}")
    private boolean grayEnabled;

    @Value("${app.debug:false}")
    private boolean debug;

    @Autowired
    private DeployServiceConfig deployServiceConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClientConfig restClientConfig;

    private HashMap<String, String> serviceNameMap = new HashMap<>();

    static {
        HTTP_METHODS.put("POST", HttpMethod.POST);
        HTTP_METHODS.put("PUT", HttpMethod.PUT);
        HTTP_METHODS.put("DELETE", HttpMethod.DELETE);
        HTTP_METHODS.put("GET", HttpMethod.GET);
    }

    private String getServiceVersion(String requestVersion, String serviceName){
        DeployConfig deploy = deployServiceConfig.getDeploy();
        if(null == deploy){
            return serviceName;
        }
        if(null == deploy.getServices()){
            if(Objects.equals("1", requestVersion)){
//                return serviceName+(StringUtils.isBlank(deploy.getVersion1())?"":("-"+deploy.getVersion1()));
                return getServiceVersion(serviceName, deploy.getVersion1(), null);
            }else{
//                return serviceName+(StringUtils.isBlank(deploy.getVersion2())?"":("-"+deploy.getVersion2()));
                return getServiceVersion(serviceName, deploy.getVersion2(), null);
            }
        }

        Map<String, DeployService> services = deploy.getServices();
        if(!serviceNameMap.containsKey(serviceName)){
            services.forEach((s, deployService) -> {
                serviceNameMap.put(deployService.getServiceName(), s);
            });
        }
        String key = serviceNameMap.get(serviceName);
        if(StringUtils.isBlank(key)){
            return serviceName;
        }
        DeployService deployService = services.get(key);
        if(null == deployService) {
            return serviceName;
        }

        if(Objects.equals("1", requestVersion)){
            return getServiceVersion(serviceName, deploy.getVersion1(), deployService.getVersion1());
        }else{
            return getServiceVersion(serviceName, deploy.getVersion2(), deployService.getVersion2());
        }
    }
    private String getServiceVersion(String serviceName, String deployVersion, String serviceVersion){
        if(StringUtils.isNotBlank(serviceVersion)){
            return serviceName+"-"+serviceVersion;
        }
        if(StringUtils.isNotBlank(deployVersion)){
            return serviceName+"-"+deployVersion;
        }
        return serviceName;
    }

    @Override
    public void sum(RemoteServiceSummer summer) {
        Summer summerSum = summer.getSummerSum();
        Class<? extends Summer> summerSumClass = summerSum.getClass();
        Summer2HttpInfo httpInfo = Summer2UrlHelper.summerClass2Http(summerSumClass);
        String moduleName = projectPrefix +"microservice-"+httpInfo.getModuleName();
//        logger.info("==============remote summer service 1: {}", moduleName);
        Map<String, String> services = restClientConfig.getServices();
        if(services.containsKey(summerSumClass.getName())){
            moduleName = services.get(summerSumClass.getName());
//            logger.info("==============remote summer service 2: {}", moduleName);
        }
//        logger.info("==============remote summer service 3: {}", moduleName);
        if(Objects.equals(applicationName, moduleName)){
            throw new ApplicationException("summer service uninstall: "+summerSumClass.getName());
        }
        boolean hasVar = StringUtils.isNotBlank(httpInfo.getVarName());
        Object[] urlVars = new Object[]{};
        if(hasVar) {
            urlVars = new Object[1];
            String varName = httpInfo.getVarName();
            urlVars[0] = BeanUtil.getFieldValue(summerSum, StringUtil.firstCharLower(varName));
        }
        String requestVersion = RequestDeployVersion.s();
        String serviceVersion = getServiceVersion(requestVersion, moduleName);
        if(StringUtils.isBlank(serviceVersion)){
            serviceVersion = moduleName;
//            logger.info("==============remote summer service 4: {}", moduleName);
        }
//        logger.info("==============remote summer service 5: {}", moduleName);

        String url = "http://"+ serviceVersion +"/feign"+httpInfo.getUrl();

        HttpMethod httpMethod = HTTP_METHODS.get(httpInfo.getMethod());

        HttpHeaders requestHeaders = buildHeaders();

        HttpEntity httpEntity = null;
        if(HttpMethod.GET.equals(httpMethod)){
            httpEntity = new HttpEntity<MultiValueMap>(requestHeaders);
            url = url+"?msrb2="+ URLUtil.encode(JSON.toJSONString(summerSum));
        }else {
            httpEntity = new HttpEntity<String>(JSON.toJSONString(summerSum), requestHeaders);
        }
        String requestId = CurrentRequestId.s(true);
        String subrequestId = ObjectId.next();

        requestLogger.info("in:{} {} {} {} {} {}", requestId, subrequestId, httpMethod.name(), url, httpEntity.toString(), urlVars);

        ResponseEntity<? extends Summer> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, summerSum.getClass(), urlVars);
        }catch (RuntimeException e){
            requestLogger.info("out:{} {} exceptionMsg: {}", requestId, subrequestId, e.getMessage());
            throw e;
        }

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            requestLogger.info("out:{} {} noSuccessful: {}", requestId, subrequestId, responseEntity.getStatusCodeValue());
            throw Excep.ae("restTemplate return status code not success : "+responseEntity.getStatusCodeValue());
        }
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        String code = responseHeaders.getFirst(Consts.SERVICE_RESULT_CODE);

        if(!"0".equals(code)){
            String msg = URLUtil.decode(Objects.requireNonNull(responseHeaders.getFirst(Consts.SERVICE_RESULT_MSG)));
            requestLogger.info("out:{} {} logicException: {} {}", requestId, subrequestId, code, msg);
            throw Excep.le(this.getClass(), code, msg, false);
        }

        Object summerResult = responseEntity.getBody()
            .getSummerResult();

        if(this.debug) {
            requestLogger.info("out:{} {} ok: {}", requestId, subrequestId, JSON.toJSONString(summerResult));
        }else{
            requestLogger.info("out:{} {} ok", requestId, subrequestId);
        }
        summerSum.setSummerResult(summerResult);
    }

    private HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();

//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

//        String userId = (String)requestAttributes.getAttribute(Consts.REQUEST_CURRENT_USER_ID_KEY, 0);
        String userId = CurrentUserId.s(false);
//        String transactionId = (String)requestAttributes.getAttribute(Consts.SERVICE_TRANSACTION_ID, 0);
        String transactionId = CurrentTransactionId.s(true);
        String requestId = CurrentRequestId.s(true);

        headers.add(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        headers.add(Consts.SERVICE_IS_MICRO_SERVICE_CALL, "1");
        headers.add(Consts.SERVICE_RESTTEMPLATE_CLIENT, "1");
        headers.add(Consts.SERVICE_REQUEST_ID, requestId);
        headers.add(Consts.REQUEST_REQUEST_VERSION, RequestDeployVersion.s());

        String lang = GetAcceptLanguage.s();
        if(StringUtils.isNotBlank(lang)){
            headers.add(HttpHeaders.ACCEPT_LANGUAGE, lang);
        }
        if (null != userId) {
            headers.add(Consts.SERVICE_USER_ID, userId);
        }

        if (null != transactionId) {
            headers.add(Consts.SERVICE_TRANSACTION_ID, transactionId);
        }

        return headers;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(true) return;
        try {
            GetRegistedSummerModels getRegistedSummerModels = new GetRegistedSummerModels();
            Set<Class<?>> klses = getRegistedSummerModels.sum();
            klses.forEach(kls -> {
                String klsName = kls.getName();
                if (klsName.startsWith("com."+Consts.SYSTEM_COMP_NAME+".summer.cms.") || klsName.startsWith("com."+Consts.SYSTEM_COMP_NAME+".summer.portal.")) {
                    Summer2UrlHelper.summerClass2Http(kls);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
