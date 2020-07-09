package com.practice.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.practice.demo.model.Menu;
import com.practice.demo.service.UserService;
import com.practice.demo.util.GenerateUUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService serivce;
	
	/**
	 * 查询菜单
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "查询菜单列表")
	@GetMapping(value = "/getMenu")
	public List<Menu> getMenu() {
		return serivce.getMenu();
	}
	
	/**
	 *登录
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@ApiOperation(value = "登录")
	@PostMapping(value = "/login")
	public Map<String,Object> login() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", 20000);
		Map<String,Object> datamap = new HashMap<String, Object>();
		datamap.put("token", "b8bfba19d9358fbfcb3adb2381b4790f");
		map.put("data", datamap);
		return map;
	}




	@Autowired
	RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

	@GetMapping("/sendDirectMessage")
	public String sendDirectMessage() {
		String messageId = String.valueOf(GenerateUUID.getUUID());
		String messageData = "test message, hello!";
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String,Object> map=new HashMap<>();
		map.put("messageId",messageId);

		map.put("messageData",messageData);
		map.put("createTime",createTime);
		//将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
		rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
		return "ok";
	}

	
	
}
