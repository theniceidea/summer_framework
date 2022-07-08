package com.fmk.framework.httpclient;

import com.fmk.framework.logger.Logger;
import com.fmk.framework.basic.ArrayUtils;
import com.fmk.framework.exception.ApplicationException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.nio.charset.CodingErrorAction;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


/**
 * httpclient工具类打包为服务
 * <ul>
 * <li>groupId:org.apache.httpcomponents</li>
 * <li>artifactId:httpclient</li>
 * <li>version:4.3.x</li>
 * </ul>
 * 
 * @author jerry
 */
public class HttpClientService {
	private static final Logger logger = Logger.getLogger(HttpClientService.class);

	private PoolingHttpClientConnectionManager connectionManager = null;
	private CloseableHttpClient httpclient = null;
	private String patternInfo = "HttpClientUtil.{} --->> [{}] url:{}, data:{}";
	private String defaultCharset = "UTF-8";
	private int defaultTimeout = 5;
	private String boundary = "----WebKitFormBoundaryZKakoyzRwD1QbdFB";
	private RequestConfig commonRequestConfig;

	public HttpClientService() {
		try {
			commonRequestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
			// SSLContext sslContext = SSLContexts.createSystemDefault();
			SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
			sslContextBuilder.useProtocol("TLSv1");
			sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLContext sslContext = sslContextBuilder.build();
			// SSLContext sslContext = SSLContexts.custom().useTLS().build();
			sslContext.init(null, new TrustManager[] {new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}}, null);

			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
			registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE);
			registryBuilder.register("https", new SSLConnectionSocketFactory(sslContext));
			Registry<ConnectionSocketFactory> socketFactoryRegistry = registryBuilder.build();

			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			HttpClientBuilder httpClientBuilder = HttpClients.custom();
			httpClientBuilder.setConnectionManager(connectionManager);
			httpClientBuilder.setRetryHandler((exception, executionCount, context) -> executionCount<=3);
			httpClientBuilder.setDefaultHeaders(getDefaultHeader());
			httpclient = httpClientBuilder.build();

			// Create socket configuration
			SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
			socketConfigBuilder.setTcpNoDelay(true);
			SocketConfig socketConfig = socketConfigBuilder.build();
			connectionManager.setDefaultSocketConfig(socketConfig);

			// Create message constraints
			MessageConstraints.Builder messageConstraintsBuilder = MessageConstraints.custom();
			messageConstraintsBuilder.setMaxHeaderCount(200);
			messageConstraintsBuilder.setMaxLineLength(2000);
			MessageConstraints messageConstraints = messageConstraintsBuilder.build();

			// Create connection configuration
			ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();
			connectionConfigBuilder.setMalformedInputAction(CodingErrorAction.IGNORE);
			connectionConfigBuilder.setUnmappableInputAction(CodingErrorAction.IGNORE);
			connectionConfigBuilder.setBufferSize(4096);
			connectionConfigBuilder.setCharset(Consts.UTF_8);
			connectionConfigBuilder.setMessageConstraints(messageConstraints);
			ConnectionConfig connectionConfig = connectionConfigBuilder.build();

			connectionManager.setDefaultConnectionConfig(connectionConfig);
			connectionManager.setMaxTotal(200);
			connectionManager.setDefaultMaxPerRoute(20);

		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	public HttpClientService(String keyStoryPath, String keyStoryType, String keyStoryPwd)
		throws IOException, CertificateException, UnrecoverableKeyException {
		try {
			char[] password = keyStoryPwd.toCharArray();
			KeyStore keyStore  = KeyStore.getInstance(keyStoryType);
			FileInputStream instream = new FileInputStream(new File(keyStoryPath));
			try {
				keyStore.load(instream, password);
			} finally {
				instream.close();
			}

			commonRequestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
			// SSLContext sslContext = SSLContexts.createSystemDefault();
			SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
			sslContextBuilder.useProtocol("TLSv1");
			sslContextBuilder.loadKeyMaterial(keyStore, password);

			SSLContext sslContext = sslContextBuilder.build();
//			SSLContext sslContext = SSLContexts.custom().
//			sslContext.init(null, new TrustManager[] {new X509TrustManager() {
//				@Override
//				public X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//
//				@Override
//				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//				}
//
//				@Override
//				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//				}
//			}}, null);

			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
			registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE);
			registryBuilder.register("https", new SSLConnectionSocketFactory(sslContext));
			Registry<ConnectionSocketFactory> socketFactoryRegistry = registryBuilder.build();

			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			HttpClientBuilder httpClientBuilder = HttpClients.custom();
			httpClientBuilder.setConnectionManager(connectionManager);
			httpClientBuilder.setRetryHandler((exception, executionCount, context) -> executionCount<=3);
			httpClientBuilder.setDefaultHeaders(getDefaultHeader());
			httpclient = httpClientBuilder.build();

			// Create socket configuration
			SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
			socketConfigBuilder.setTcpNoDelay(true);
			SocketConfig socketConfig = socketConfigBuilder.build();
			connectionManager.setDefaultSocketConfig(socketConfig);

			// Create message constraints
			MessageConstraints.Builder messageConstraintsBuilder = MessageConstraints.custom();
			messageConstraintsBuilder.setMaxHeaderCount(200);
			messageConstraintsBuilder.setMaxLineLength(2000);
			MessageConstraints messageConstraints = messageConstraintsBuilder.build();

			// Create connection configuration
			ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();
			connectionConfigBuilder.setMalformedInputAction(CodingErrorAction.IGNORE);
			connectionConfigBuilder.setUnmappableInputAction(CodingErrorAction.IGNORE);
			connectionConfigBuilder.setBufferSize(4096);
			connectionConfigBuilder.setCharset(Consts.UTF_8);
			connectionConfigBuilder.setMessageConstraints(messageConstraints);
			ConnectionConfig connectionConfig = connectionConfigBuilder.build();

			connectionManager.setDefaultConnectionConfig(connectionConfig);
			connectionManager.setMaxTotal(200);
			connectionManager.setDefaultMaxPerRoute(20);

		} catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	private List<Header> getDefaultHeader() {
		ArrayList<Header> headers = new ArrayList<>();

		String userAgent = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36";
		Header header1 = new BasicHeader(HttpHeaders.USER_AGENT, userAgent);
		headers.add(header1);

		String cacheControl = "max-age=0";
		// String cacheControl = "no-cache";
		Header header2 = new BasicHeader(HttpHeaders.CACHE_CONTROL, cacheControl);
		headers.add(header2);

		return headers;
	}

	/**
	 * 超时时间（单位：秒）
	 * 
	 * @param overtime
	 * @return
	 */
	private RequestConfig getRequestConfig(int overtime) {
		Builder requestConfigBuilder = RequestConfig.custom();

		requestConfigBuilder.setSocketTimeout(overtime);
		requestConfigBuilder.setConnectTimeout(overtime);
		requestConfigBuilder.setConnectionRequestTimeout(overtime);
		requestConfigBuilder.setExpectContinueEnabled(false);

		RequestConfig requestConfig = requestConfigBuilder.build();

		return requestConfig;
	}

	private void request(HttpUriRequest request, OnStatusLine onStatusLine, OnResponse onResponse) {
		CloseableHttpResponse response = null;

		try {
			// 发出请求
			response = httpclient.execute(request);

			StatusLine statusLine = response.getStatusLine();

			if(!onStatusLine.exec(response)) {
				logger.debug(request.getURI()+": status verifyCode:"+statusLine.getStatusCode());
				return;
			}

			// 获取响应实体
			HttpEntity responseEntity = response.getEntity();

			try {
				onResponse.exec(response);
			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
				throw new ApplicationException(e);
			} finally {
				// 销毁
				EntityUtils.consume(responseEntity);
			}
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ApplicationException(e);
		} finally {
//			IOUtils.closeQuietly(httpclient);
		}
	}


	private String responseEntityToString(HttpEntity entity, String charset){
		try {
			return EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
	}

	private byte[] responseEntityToByteArray(HttpEntity entity){
		try {
			InputStream input = entity.getContent();
			return IOUtils.toByteArray(input);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
	}

	private void request(
		BaseRequest baseRequest, HttpRequestBase httpRequestBase,
		OnStatusLine onStatusLine, OnResponse onResponse){

		// 设置请求配置
		httpRequestBase.setConfig(getRequestConfig(baseRequest.getTimeout()));
		List<Header> headers = baseRequest.getHeaders();
		if(null != headers){
			headers.stream().forEach(httpRequestBase::addHeader);
		}

		// 发出请求
		request(httpRequestBase, onStatusLine, onResponse);
	}

	public GetRequest request(GetRequest req){

		HttpGet httpGet = new HttpGet(req.buildUrl());
		try {
			httpGet.setConfig(commonRequestConfig);
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpGet, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpGet.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "GET", "response", req.getUrl(), req.getResult());
		}

		return req;
	}

	public DeleteRequest request(DeleteRequest req){

		HttpDelete httpDelete = new HttpDelete(req.buildUrl());
		try {
			httpDelete.setConfig(commonRequestConfig);
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpDelete, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpDelete.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "DELETE", "response", req.getUrl(), req.getResult());
		}

		return req;
	}

	public DownloadRequest request(DownloadRequest req){
		HttpGet httpGet = new HttpGet(req.buildUrl());
		try {
			httpGet.setConfig(commonRequestConfig);
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpGet, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				// 解析响应正文
				req.setResult(responseEntityToByteArray(response.getEntity()));

			});
		} finally {
			httpGet.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "GET Download", "response", req.getUrl(), "binary data");
		}

		return req;
	}

	public PostFormRequest request(PostFormRequest req){
		HttpPost httpPost = new HttpPost(req.buildUrl());
		httpPost.setConfig(commonRequestConfig);

		if(null != req.getPostData()) {
			UrlEncodedFormEntity requestEntity = null;
			try {
				requestEntity = new UrlEncodedFormEntity(req.getPostData(), req.getCharset());
				requestEntity.setContentEncoding(req.getCharset());
				if(StringUtils.isNotBlank(req.getContentType())) {
					requestEntity.setContentType(req.getContentType());
				}
			} catch (UnsupportedEncodingException e) {
				throw new ApplicationException(e);
			}
			httpPost.setEntity(requestEntity);
		}

		try {
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpPost, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpPost.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "POST", "response", req.getUrl(), req.getResult());
		}

		return req;
	}
    public PostPBRequest request(PostPBRequest req){
        HttpPost httpPost = new HttpPost(req.buildUrl());
        httpPost.setConfig(commonRequestConfig);

        if(null != req.getPostData()) {
            req.addHeader("Content-Encoding", "gzip");
            ByteArrayEntity bae = new ByteArrayEntity(req.getPostData());
            bae.setContentEncoding("gzip");
            if(StringUtils.isNotBlank(req.getContentType())) {
                bae.setContentType(req.getContentType());
            }
            httpPost.setEntity(new GzipCompressingEntity(bae));
        }

        try {
            RequestResultStatus status = new RequestResultStatus();
            req.setStatus(status);
            request(req, httpPost, response -> {
                StatusLine statusLine = response.getStatusLine();
                status.setStatusCode(statusLine.getStatusCode());
                req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
                return statusLine.getStatusCode() == HttpStatus.SC_OK;
            }, response -> {
                String s = responseEntityToString(response.getEntity(), req.getCharset());
                req.setResult(s);
            });
        } finally {
            httpPost.releaseConnection();
        }

        if (logger.isDebugEnabled()) {
            logger.debug(patternInfo, "POST", "response", req.getUrl(), req.getResult());
        }

        return req;
    }

	public PutFormRequest request(PutFormRequest req){
		HttpPut httpPut = new HttpPut(req.buildUrl());
		httpPut.setConfig(commonRequestConfig);

		if(null != req.getPostData()) {
			UrlEncodedFormEntity requestEntity = null;
			try {
				requestEntity = new UrlEncodedFormEntity(req.getPostData(), req.getCharset());
				requestEntity.setContentEncoding(req.getCharset());
				if(StringUtils.isNotBlank(req.getContentType())) {
					requestEntity.setContentType(req.getContentType());
				}
			} catch (UnsupportedEncodingException e) {
				throw new ApplicationException(e);
			}
			httpPut.setEntity(requestEntity);
		}

		try {
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpPut, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpPut.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "PUT", "response", req.getUrl(), req.getResult());
		}

		return req;
	}

	public PostStringRequest request(PostStringRequest req){
		HttpPost httpPost = new HttpPost(req.buildUrl());
		httpPost.setConfig(commonRequestConfig);

		if(null != req.getPostData()) {
//			try {
				StringEntity stringEntity = new StringEntity(req.getPostData(), req.getCharset());
				stringEntity.setContentEncoding(req.getCharset());
				if(StringUtils.isNotBlank(req.getContentType())) {
					stringEntity.setContentType(req.getContentType());
				}
				httpPost.setEntity(stringEntity);
//			} catch (UnsupportedEncodingException e) {
//				throw new ApplicationException(e.getMessage(), e);
//			}
		}

		try {
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpPost, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpPost.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "POST", "response", req.getUrl(), req.getResult());
		}

		return req;
	}

    public PutStringRequest request(PutStringRequest req){
        HttpPut httpPut = new HttpPut(req.buildUrl());
        httpPut.setConfig(commonRequestConfig);

        if(null != req.getPostData()) {
//			try {
            StringEntity stringEntity = new StringEntity(req.getPostData(), req.getCharset());
            stringEntity.setContentEncoding(req.getCharset());
            if(StringUtils.isNotBlank(req.getContentType())) {
                stringEntity.setContentType(req.getContentType());
            }
            httpPut.setEntity(stringEntity);
//			} catch (UnsupportedEncodingException e) {
//				throw new ApplicationException(e.getMessage(), e);
//			}
        }

        try {
            RequestResultStatus status = new RequestResultStatus();
            req.setStatus(status);
            request(req, httpPut, response -> {
                StatusLine statusLine = response.getStatusLine();
                status.setStatusCode(statusLine.getStatusCode());
                req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
                return statusLine.getStatusCode() == HttpStatus.SC_OK;
            }, response -> {
                String s = responseEntityToString(response.getEntity(), req.getCharset());
                req.setResult(s);
            });
        } finally {
            httpPut.releaseConnection();
        }

        if (logger.isDebugEnabled()) {
            logger.debug(patternInfo, "PUT", "response", req.getUrl(), req.getResult());
        }

        return req;
    }



	public PostFileRequest request(PostFileRequest req){
		HttpPost httpPost = new HttpPost(req.buildUrl());
		httpPost.setConfig(commonRequestConfig);

		FileBody fileBody = new FileBody(req.getPostFile());
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart(req.getInputName(), fileBody);

		httpPost.setEntity(builder.build());

		try {
			RequestResultStatus status = new RequestResultStatus();
			req.setStatus(status);
			request(req, httpPost, response -> {
				StatusLine statusLine = response.getStatusLine();
				status.setStatusCode(statusLine.getStatusCode());
				req.setResponseHeaders(ArrayUtils.toList(response.getAllHeaders()));
				return statusLine.getStatusCode() == HttpStatus.SC_OK;
			}, response -> {
				String s = responseEntityToString(response.getEntity(), req.getCharset());
				req.setResult(s);
			});
		} finally {
			httpPost.releaseConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(patternInfo, "GET", "response", req.getUrl(), req.getResult());
		}

		return req;
	}

	public static void main(String[] args) {
		// TODO 标记程序入口

		// {"status":0, "environment":"Sandbox",
		// "receipt":{"receipt_type":"ProductionSandbox", "adam_id":0, "app_item_id":0, "bundle_id":"com.hcnm.mocon",
		// "application_version":"1", "download_id":0, "version_external_identifier":0,
		// "receipt_creation_date":"2016-03-30 07:01:53 Etc/GMT", "receipt_creation_date_ms":"1459321313000",
		// "receipt_creation_date_pst":"2016-03-30 00:01:53 America/Los_Angeles",
		// "request_date":"2016-03-30 07:38:04 Etc/GMT", "request_date_ms":"1459323484584",
		// "request_date_pst":"2016-03-30 00:38:04 America/Los_Angeles",
		// "original_purchase_date":"2013-08-01 07:00:00 Etc/GMT", "original_purchase_date_ms":"1375340400000",
		// "original_purchase_date_pst":"2013-08-01 00:00:00 America/Los_Angeles", "original_application_version":"1.0",
		// "in_app":[
		// {"quantity":"1", "product_id":"com.hcnm.mocon.coin1", "transaction_id":"1000000202306900",
		// "original_transaction_id":"1000000202306900", "purchase_date":"2016-03-30 07:01:53 Etc/GMT",
		// "purchase_date_ms":"1459321313000", "purchase_date_pst":"2016-03-30 00:01:53 America/Los_Angeles",
		// "original_purchase_date":"2016-03-30 07:01:53 Etc/GMT", "original_purchase_date_ms":"1459321313000",
		// "original_purchase_date_pst":"2016-03-30 00:01:53 America/Los_Angeles", "is_trial_period":"false"}]}}
//		verifyReceipt();
//		GetRequest getRequest = new GetRequest();
//		getRequest.putQueryParam("a", "1")
//			.putQueryParam("b", "中国");
//		getRequest.setUrl("http://www.baidu.com/?c=1").request();
//		System.out.println(getRequest.getResult());

//		PostFormRequest postFormRequest = new PostFormRequest();
//		postFormRequest.setUrl("http://139.196.202.79:8081/fmk/rs/cms/wx-aide-subscribe?debuguid=93")
//			.addPostData("keyword", "黄渤2")
//			.addPostData("ab", "dddd大调");
//		String request = request(postFormRequest);
//		System.out.println(request);

//		PostFileRequest postFileRequest = new PostFileRequest();
//		postFileRequest
//			.setUrl("http://ketongtx.com/tools/upload_ajax.ashx?action=UpLoadFile&IsWater=1&IsThumbnail=1&debuguid=93")
//			.setPostFile("/home/jerry/src/python/scrapies/fmk-crawl/data/img/08/82/14/10222f0629c97d115fe52281c9.jpg");
//		String request = request(postFileRequest);
//		System.out.println(request);

//		DownloadRequest downloadRequest = new DownloadRequest();
//		downloadRequest.setUrl("http://pic-ketongtx-com.oss-cn-hangzhou.aliyuncs.com/ad9f838fdb2c4313b1828e97466fe641");
//		request(downloadRequest);
//		downloadRequest.saveAs(new File("/home/jerry/src/java/fmk-service/test.jpg"));

//		DownloadRequest downloadRequest = new DownloadRequest();
//		downloadRequest.setUrl("http://www.ketongtx.com/Admin/Manager/CheckCode");
//		request(downloadRequest);
//		List<Header> responseHeaders = downloadRequest.getResponseHeaders();


//		PostFormRequest request = PostFormRequest.create()
//			.setUrl("http://139.196.198.90:8081/weixin/get/115?debuguid=40")
//			.request();
//
//		TokenModel tokenModel = JSONParser.parseObject(request.getResult(), TokenModel.class);
////
////		System.out.println(request.getResult());
//		String token = "oFWzNaKGDtx9n9qI0H3hxv5gzIDwr7TQcGWaTwmP19KI51lqPSO2QCXlz_7e4XK6gzGjZ-_o3lFDdMjmve92XvxHbD0sGmi-HCOGqIwhndqDqsSjCYiU1N9ON7tvnofMHWIfAEDODP";
//		token=tokenModel.getData().getAuthorizer_access_token();
//		PostFileRequest request2 = PostFileRequest.create()
//			.setUrl("https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+token)
//			.setInputName("media")
//			.setPostFile("/home/jerry/src/python/scrapies/fmk-crawl/data/img/08/82/14/10222f0629c97d115fe52281c9.jpg")
//			.request();
//		System.out.println(request2.getResult());



//		HashMap<String, Object> postData = new HashMap<>();
//
//		postData.put("type", "image");
//		postData.put("offset", 0);
//		postData.put("count", 20);
//
//		PostStringRequest request3 = PostStringRequest.create()
//			.setUrl("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+token)
//			.setPostData(JSON.toJSONString(postData))
//			.request(new HttpClientServiceImpl());
//		System.out.println(request3.getResult());
	}

}
