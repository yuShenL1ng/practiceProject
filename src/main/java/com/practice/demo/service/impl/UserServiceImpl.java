package com.practice.demo.service.impl;

import java.util.List;

import com.practice.demo.mapper.UserMapper;
import com.practice.demo.model.Menu;
import com.practice.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return mapper.getMenu();
	}
	
}
