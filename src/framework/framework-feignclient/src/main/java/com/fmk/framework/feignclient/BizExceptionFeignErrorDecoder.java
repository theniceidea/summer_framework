package com.fmk.framework.feignclient;

//import feign.Response;

//@Configuration
//public class BizExceptionFeignErrorDecoder implements feign.codec.ErrorDecoder {
//    @Override
//    public Exception decode(String methodKey, Response response) {
////        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//        if(response.status() >= 400 && response.status() <= 499){
////            return new HystrixBadRequestException("xxxxxx");
//        }
//        return feign.FeignException.errorStatus(methodKey, response);
//    }
//}
