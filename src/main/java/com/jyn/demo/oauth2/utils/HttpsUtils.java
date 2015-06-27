package com.jyn.demo.oauth2.utils;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpsUtils {
	public static String get(String keystoreFileName, String keystorePassword, String supportedCipherSuites, String url) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        return get(keystoreFileName, keystorePassword, supportedCipherSuites, url, true);
	}
	
	public static String get(String keystoreFileName, String keystorePassword, String supportedCipherSuites, String url, boolean needCloseClient) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        String result = null;
		CloseableHttpClient httpclient = getHttpClient(keystoreFileName, keystorePassword, supportedCipherSuites);
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            } finally {
                response.close();
            }
        } finally {
        	if(needCloseClient){
        		httpclient.close();
        	}
        }
	}
	
	public static String post(String keystoreFileName, String keystorePassword, String supportedCipherSuites, String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		return post(keystoreFileName, keystorePassword, supportedCipherSuites, url, nvps, true);
	}
	
	public static String post(String keystoreFileName, String keystorePassword, String supportedCipherSuites, String url, Map<String, String> params) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		NameValuePair nvp = null;
		Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> tmp = iter.next();
			nvp = new BasicNameValuePair(tmp.getKey(), tmp.getValue());
			nvps.add(nvp);
		}
		return post(keystoreFileName, keystorePassword, supportedCipherSuites, url, nvps, true);
	}
	
	public static String post(String keystoreFileName, String keystorePassword, String supportedCipherSuites, String url, List<NameValuePair> nvps, boolean needCloseClient) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		String result = null;
		CloseableHttpClient httpclient = getHttpClient(keystoreFileName, keystorePassword, supportedCipherSuites);
        try {
        	HttpPost httpPost = new HttpPost(url);
        	if(nvps != null){
        		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        	}
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return result;
            } finally {
                response.close();
            }
        } finally {
        	if(needCloseClient){
        		httpclient.close();
        	}
        }
	}
	
	public static CloseableHttpClient getHttpClient(String keystoreFileName, String keystorePassword, String supportedCipherSuites) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException{
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new File(keystoreFileName), keystorePassword.toCharArray(),
                        new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { supportedCipherSuites },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        return httpclient;
	}
	
    public final static void main(String[] args) throws Exception {
    	String keystoreFileName = "C:\\Program Files\\Java\\jdk1.6.0_45\\jre\\lib\\security\\cacerts";
    	String keystorePassword = "changeit";
    	String supportedCipherSuites = "TLSv1";
    	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=***&secret=***";
    	String getRes = get(keystoreFileName, keystorePassword, supportedCipherSuites, url);
    	System.out.println(getRes);
    }
}
