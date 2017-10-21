package com.intellect.service;

import java.util.List;

import com.intellect.bean.UserBean;

public interface IUserService {

	UserBean saveUser(UserBean request);

	UserBean updateUser(UserBean request,String id);

	Boolean deleteUser(String id);

	List<UserBean> getListUsers(String id);

}
