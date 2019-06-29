package com.hero.hotel.service;

import com.hero.hotel.pojo.Comment;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface CommentService {
	//分页查找所有评论
	public List<Comment> findAll(Integer PageNum );
	//查找总条数
	public Integer findTotal();
	//添加评论
	public Boolean addComment(Comment comment,HttpSession session);

	public List<Comment> findAllNow();

	Boolean deleteComment(Integer id);
	
}
