package com.intellect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constants.CommonConstants;
import com.intellect.bean.UserBean;
import com.intellect.service.IUserService;
import com.intellect.to.response.ListResponse;
import com.intellect.to.response.UserRespose;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private IUserService iUserService;
	
    @RequestMapping(path="/user",method = RequestMethod.POST)
    private UserRespose saveUser(@RequestBody UserBean request){
    	
    	UserRespose userRespone = new UserRespose();
    	userRespone.setUserBean(iUserService.saveUser(request));
    	userRespone.setMessage("User Created Successfully");
    	userRespone.setStatus(CommonConstants.OK);    	
    	return userRespone;
    }
    
    @RequestMapping(path="/user/{id}",method = RequestMethod.PUT)
    private UserRespose updateUser(@RequestBody UserBean request,@PathVariable("id")String id){
    	
    	UserRespose userRespone = new UserRespose();
    	userRespone.setUserBean(iUserService.updateUser(request,id));
    	userRespone.setMessage("User Updated Successfully");
    	userRespone.setStatus(CommonConstants.OK);    	

    	return userRespone;
    }
    
    @RequestMapping(path="/user/{id}",method = RequestMethod.DELETE)
    private UserRespose updateUser(@PathVariable("id")String id){
    	
    	UserRespose userRespone = new UserRespose();
    	if(iUserService.deleteUser(id)){
    		userRespone.setMessage("User Deleted Successfully");
        	userRespone.setStatus(CommonConstants.OK);    	

    	}

    	return userRespone;
    }
    
    @RequestMapping(path="/user",method = RequestMethod.GET)
    private ListResponse getUser(@RequestParam(required=false)String id){
    	
    	ListResponse userRespone = new ListResponse();
    	userRespone.setUserBean(iUserService.getListUsers(id));
    	userRespone.setMessage("User Details Fetched Successfully");
    	userRespone.setStatus(CommonConstants.OK);    	

    	return userRespone;
    }

}
