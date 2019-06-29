package com.hero.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Update;

import com.hero.hotel.pojo.Comment;

public interface CommentDao {
	
	//查找所有评论,PageNum当前页
	@Select("select * from t_comment where flag=1 order by id desc limit #{pageNum},10")
	public List<Comment> findAll( Integer pageNum );

	//查找评论总数
	@Select("select count(*) from t_comment where flag=1")
	public Integer findAllNumber();
	
	//添加评论
	@Insert("insert into t_comment(orderid,createtime,massage,userid) values(#{orderid},#{createtime},#{massage}) ")
	public Boolean addComment(Comment comment);

	// 查询不同的状态的  评论    1  0
	//@Select("select id,orderid,date_format(createtime,'%Y-%m-%d %h:%i:%s') AS createtime,message,userid from t_comment where flag=#{flag}")
	@Select("select * from t_comment where flag=#{flag}")
	public List<Comment> findAllNow(@Param("flag") Integer flag);

	// 修改为  删除状态
	@Update("UPDATE t_comment SET flag = 2 WHERE id = #{id}")
	Boolean deleteCommentByid(Integer id);
}
