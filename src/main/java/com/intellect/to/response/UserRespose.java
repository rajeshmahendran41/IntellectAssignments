package com.intellect.to.response;

import com.intellect.bean.UserBean;
import com.response.CommonResponse;

public class UserRespose extends CommonResponse {

	
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
