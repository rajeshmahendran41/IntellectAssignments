package com.intellect.to.response;

import java.util.List;

import com.intellect.bean.UserBean;
import com.response.CommonResponse;

public class ListResponse extends CommonResponse{

	private List<UserBean> userBean;

	public List<UserBean> getUserBean() {
		return userBean;
	}

	public void setUserBean(List<UserBean> userBean) {
		this.userBean = userBean;
	}
}
