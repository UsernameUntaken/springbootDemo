package com.zkhy.officialWebsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zkhy.officialWebsite.dao.BaseMapper;
import com.zkhy.officialWebsite.model.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{
	
	@Update("<script>"
			+ "UPDATE zk_sysuser SET pwd = #{indexPwd}, updatetime = NOW() WHERE sys_user_id in "
			+ "<foreach item='item' index='index' collection='sysUserIds' open='(' separator=',' close=')'>"
			+ 		"#{item}"
			+ "</foreach>"
			+ "</script>")
	int changePwds(@Param("indexPwd") String indexPwd,@Param("sysUserIds") List<String> sysUserIds);
	
	@Select("<script>"
			+ "SELECT * FROM zk_sysuser WHERE 1 = 1 "
			+ "<if test= \" sysUser.sysUserId != null and sysUser.sysUserId != ''\"> and sys_user_id = #{sysUser.sysUserId} </if>"
			+ "<if test= \" sysUser.username != null and sysUser.username != ''\"> and username LIKE CONCAT('%',#{sysUser.username},'%') </if>"
			+ "<if test= \" sysUser.role != null and sysUser.role != ''\"> and role = #{sysUser.role} </if>"
			+ "ORDER BY createtime DESC"
			+ "</script>")
	List<SysUser> listSysUser(@Param("sysUser") SysUser sysUser);
	
	@Delete("<script>"
			+ "DELETE FROM zk_sysuser WHERE sys_user_id in "
			+ "<foreach item='item' index='index' collection='sysUserIds' open='(' separator=',' close=')'>"
			+ 		"#{item}"
			+ "</foreach>"
			+ "</script>")
	int delSysUser(@Param("sysUserIds") List<String> sysUserIds);
}
