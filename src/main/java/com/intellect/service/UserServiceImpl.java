package com.intellect.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Util.Util;
import com.intellect.bean.UserBean;

@Service
public class UserServiceImpl implements IUserService {
	
	private static List<UserBean> userDetails = new ArrayList<>();

	@Override
	public UserBean saveUser(UserBean request) {

		validateCreateUser(request);
		userDetails.add(request);
		return request;
	}
	
	private void validateCreateUser(UserBean request){
		
		if(Util.isNull(request.getfName())){
			Util.throwException("First Name Cannot be empty");
		}else if(Util.isNull(request.getlName())){
			Util.throwException("Last Name Cannot be empty");
		}else if(Util.isNull(request.getBirthDate())){
			Util.throwException("Birth Date Cannot be empty");
		}else if(Util.isNull(request.getPinCode())){
			Util.throwException("Pincode Cannot be empty");
		}else if(Util.isNull(request.getEmail())){
			Util.throwException("Email id Cannot be null");
		}else if(validateEmail(request.getEmail())){
			Util.throwException("Email id already Exist");
		}else if(validateId(request.getId())){
			Util.throwException("Id field already Exist");
		} else
			try {
				if(validateDate(request.getBirthDate())){
					Util.throwException("Future Date Cannot be given");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
	
	
	private void validateUpdateUser(UserBean request){
		
		if(!Util.isNull(request.getId())||!Util.isNull(request.getEmail())||!Util.isNull(request.getfName())||!Util.isNull(request.getlName())){
			Util.throwException("User Can Only Update Date of Birth & Pincode");
		}
		if(Util.isNull(request.getPinCode() ) && Util.isNull(request.getBirthDate())){
			Util.throwException("Please update Date or Birth or Pincode");
		}else
			try {
				if(validateDate(request.getBirthDate())){
					Util.throwException("Future Date Cannot be given");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
	
	private boolean validateEmail(String currentEmailId){
		
		if(!Util.isNullList(userDetails)){
			for(UserBean userBean: userDetails){
				if(userBean.getEmail().equals(currentEmailId)&&userBean.getIsActive()==true){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean validateId(String id){
		
		if(!Util.isNullList(userDetails)){
			for(UserBean userBean: userDetails){
				if(userBean.getId().equals(id)&&userBean.getIsActive()==true){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean validateDate(Date date) throws ParseException{
		
		if(date.after(Util.getCurrentDate())){
			return true;
		}
		
		return false;
	}

	@Override
	public UserBean updateUser(UserBean request,String id) {
		
		validateUpdateUser(request);
		if(!Util.isNullList(userDetails)){
			for(int i =0 ; i < userDetails.size() ; i ++){
				if(userDetails.get(i).getId().equals(id)&&userDetails.get(i).getIsActive()==true){
					userDetails.get(i).setPinCode(request.getPinCode());
					userDetails.get(i).setBirthDate(request.getBirthDate());
					return userDetails.get(i);
				}
			}
		}
		
		Util.throwException("Please enter valid userId");
		return null;
	}
	
	@Override
	public Boolean deleteUser(String id) {
		
		
		if(!Util.isNullList(userDetails)){
			for(int i =0 ; i < userDetails.size() ; i ++){
				if(userDetails.get(i).getId().equals(id)&&userDetails.get(i).getIsActive()==true){
					userDetails.get(i).setIsActive(false);
					return true;
				}
			}
		}
		
		Util.throwException("Please enter valid user Id");
		return false;
	}

	@Override
	public List<UserBean> getListUsers(String id) {
		
		if(Util.isNull(id)){
			return (List<UserBean>) userDetails.stream().filter(user -> user.getIsActive()==true).collect(Collectors.toList());
		}else{
			for(UserBean userBean : userDetails){
				if(userBean.getId().equals(id)&&userBean.getIsActive()==true){
					return Arrays.asList(userBean);
				}
			}
			Util.throwException("Please enter a valid userId");
		}
		return null;
	    
	}

}
