package com.yc.voto.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.voto.bean.Topics;
import com.yc.voto.bean.UserInfo;
import com.yc.voto.biz.ITopicsBiz;

import com.yc.voto.biz.impl.TopicsBizImpl;
import com.yc.voto.util.RequestParamUtil;
@WebServlet("/topic")
public class TopicController extends BasicController {

	private static final long serialVersionUID = 5585138468215591497L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch (op) {
		case "add":add(request,response);break;
		case "findAll":findAll(request,response);break;
		case "findByTid":findByTid(request,response);break;
		case "vote":vote(request,response);break;

		default:error(request,response);break;
		}
	}

	private void vote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginUser");
		System.out.println(obj);
		if(obj == null) {
			this.send(response, 500,null);
			return;
		}
		String tid = request.getParameter("tid");
		int usid = ((UserInfo) obj).getUsid();
		String inos = request.getParameter("inos");
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		if(topicsBiz.update(tid,usid,inos)>0) {
			this.send(response, 200,null);
			return;
		}
		this.send(response, 501,null);
	}

	private void findByTid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		Topics topic = topicsBiz.findByTid(tid);
		if(topic != null) {
			this.send(response, 200,topic);
			return;
		}
		this.send(response, 500,null);
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		this.send(response, 200,topicsBiz.findAll());
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Topics topics = RequestParamUtil.getParams(Topics.class, request);
		
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		if(topicsBiz.add(topics) > 0) {
			this.send(response, 200,null);
			return;
		}
		this.send(response, 500,null);
	}
}
