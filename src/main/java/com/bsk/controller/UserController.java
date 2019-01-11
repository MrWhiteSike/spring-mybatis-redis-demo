package com.bsk.controller;
 
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsk.entity.User;
import com.bsk.service.UserService;
 
 
/**
 * UserController类
 * @author
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private UserService mUserService;
	
	/**
	 * 保存用户
	 * @param name
	 * @param sex
	 * @param age
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Integer save(@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="sex", defaultValue="") String sex,
			@RequestParam(value="age", defaultValue="0") String age){
//		logger.debug(name);
		
		try {
			name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			sex = new String(sex.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.debug("编码异常");
		}
		logger.debug(name);
		logger.debug(sex);
		logger.debug(age);
		mUserService.saveUser(name, sex, Integer.parseInt(age));
		return 1;
	}
	
	/**
	 * 获取所有用户列表
	 * @return
	 */
	@RequestMapping("/getall")
	@ResponseBody
	public Object getAllUser(){
		List<User> users = mUserService.getAllUser();
		for(User u : users){
			logger.debug(u.toString());
		}
		return users;
	}
	/**
	 * 根据用户id获取用户信息
	 * @return
	 */
	@RequestMapping("/getUserById")
	@ResponseBody
	public Object getUserById(@RequestParam(value="id", defaultValue="0") String id){
		User user = mUserService.getUserById(Integer.parseInt(id));
		if(user != null) {
			logger.debug(user.toString());
		}
		return user;
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @return
	 */
	@RequestMapping("/renameUser")
	@ResponseBody
	public Integer renameUser(@RequestParam(value="id", defaultValue="0") String id, 
							 @RequestParam(value="name", defaultValue="") String name){
		logger.debug(id + "=========" + name);
		mUserService.renameUser(name, Integer.parseInt(id));
		return 1;
	}
	/**
	 * 根据用户id获取用户信息
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Integer delete(@RequestParam(value="id", defaultValue="0") String id){
		logger.debug(id);
		mUserService.deleteUserById(Integer.parseInt(id));
		return 1;
	}
}
