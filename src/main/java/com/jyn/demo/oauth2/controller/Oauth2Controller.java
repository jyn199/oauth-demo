package com.jyn.demo.oauth2.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jyn.demo.oauth2.model.Oauth2Model;
import com.jyn.demo.oauth2.utils.HttpsUtils;

@Controller
@RequestMapping("/oauth2")
@Scope(value="prototype")
public class Oauth2Controller {
	
	@Resource
	private Oauth2Model oauth2Model;
	
	@RequestMapping(value="", method={RequestMethod.GET})
	public ModelAndView index(HttpServletRequest requet, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/oauth2/index");
//		JSONObject jo = JSONObject.parseObject(getAccessToken());
		//获取access_token
		JSONObject jo = getJsonAccessToken();
		oauth2Model.setAccessToken(jo.getString("access_token"));
		System.out.println(jo.getString("access_token"));
		
//		System.out.println(getUserSummary());
		System.out.println(getUserList());
		
		return mav;
	}
	
	private String getUserList(){
		Object[] params = new Object[]{oauth2Model.getAccessToken()};
		String userListUrl = MessageFormat.format(oauth2Model.getUserListUrl(), params);
		String result = null;
		try {
			result = HttpsUtils.get(oauth2Model.getKeystoreFileName(), oauth2Model.getKeystorePassword(), oauth2Model.getSupportedCipherSuites(), userListUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result == null)
			result = "";
		return result;
	}
	
	private String addKfAccount(){
		Object[] params = new Object[]{oauth2Model.getAccessToken()};
		String userSummaryUrl = MessageFormat.format(oauth2Model.getUserSummaryUrl(), params);
		String result = null;
		Map<String, String> httpParams = new HashMap<String, String>();
		httpParams.put("kf_account", "test1@test");
		httpParams.put("nickname", "客服1");
		httpParams.put("password", "pswmd5");
		try {
			result = HttpsUtils.post(oauth2Model.getKeystoreFileName(), oauth2Model.getKeystorePassword(), oauth2Model.getSupportedCipherSuites(), userSummaryUrl, httpParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result == null)
			result = "";
		return result;
	}
	
	private String getUserSummary(){
		Object[] params = new Object[]{oauth2Model.getAccessToken()};
		String userSummaryUrl = MessageFormat.format(oauth2Model.getUserSummaryUrl(), params);
		String result = null;
		try {
			result = HttpsUtils.post(oauth2Model.getKeystoreFileName(), oauth2Model.getKeystorePassword(), oauth2Model.getSupportedCipherSuites(), userSummaryUrl, new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result == null)
			result = "";
		return result;
	}
	
	private String getAccessToken(){
		Object[] params = new Object[]{oauth2Model.getAccessTokenGrantType(), oauth2Model.getAppID(), oauth2Model.getAppSecret()};
		String accessTokenUrl = MessageFormat.format(oauth2Model.getAccessTokenUrl(), params);
		String result = null;
		try {
			result = HttpsUtils.get(oauth2Model.getKeystoreFileName(), oauth2Model.getKeystorePassword(), oauth2Model.getSupportedCipherSuites(), accessTokenUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result == null)
			result = "";
		return result;
	}
	
	private JSONObject getJsonAccessToken(){
		Object[] params = new Object[]{oauth2Model.getAccessTokenGrantType(), oauth2Model.getAppID(), oauth2Model.getAppSecret()};
		String accessTokenUrl = MessageFormat.format(oauth2Model.getAccessTokenUrl(), params);
		String result = null;
		try {
			result = HttpsUtils.get(oauth2Model.getKeystoreFileName(), oauth2Model.getKeystorePassword(), oauth2Model.getSupportedCipherSuites(), accessTokenUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result == null)
			result = "{}";

		JSONObject jo = JSONObject.parseObject(result);
		return jo;
	}
	
}
