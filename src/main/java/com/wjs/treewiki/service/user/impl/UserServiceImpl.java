package com.wjs.treewiki.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.TreeItemVo;

@Service("indexService")
public class UserServiceImpl implements UserService {

	public List<TreeItemVo> listUser() {

		List<TreeItemVo>  result = new ArrayList<TreeItemVo>();
		for (int i = 0; i < 5; i++) {
			TreeItemVo vo = new TreeItemVo();
			
			result.add(vo);
		}
		
		return result;
	}

}
