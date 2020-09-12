package com.yc.voto.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.voto.bean.UserInfo;
import com.yc.voto.biz.IUserInfoBiz;
import com.yc.voto.biz.impl.UserInfoBizImpl;
import com.yc.voto.util.RequestParamUtil;

@WebServlet("/user")
public class UserController extends BasicController{

	private static final long serialVersionUID = -5575261744664757594L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "reg": reg(request,response); break;
		case "login": login(request,response); break;
		case "check": check(request,response); break;

		default:error(request,response);
			break;
		}
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginUser");
		if(obj == null) {
			this.send(response, 500,null);
			return;
		}		
		this.send(response, 200,obj);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserInfo uf = RequestParamUtil.getParams(UserInfo.class, request);
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		UserInfo userInfo = userInfoBiz.login(uf);
		if(userInfo == null) {
			this.send(response, 500,null);
			return;
		}
		request.getSession().setAttribute("currentLoginUser", userInfo);
		this.send(response, 200,null);
	}

	private void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserInfo uf = RequestParamUtil.getParams(UserInfo.class, request);
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		
		if(userInfoBiz.add(uf) > 0) {
			this.send(response, 200,null);
			return;
		}
		this.send(response, 500,null);
	}
}
