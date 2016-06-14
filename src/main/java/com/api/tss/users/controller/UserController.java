package com.api.tss.users.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.pojo.UserPojo;
import com.api.tss.users.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userreg",method=RequestMethod.POST)
	public void userRegistration(@ModelAttribute("userPojo") UserPojo userPojo,HttpServletResponse response){
		if(userService.addUser(userPojo).equalsIgnoreCase("YES")){
			try {
				response.sendRedirect("login.jsp?message=Your account Successfully registered Please "
						+ "Check your Email and Click on the confirmation link to activate your account");
			} catch (IOException e) {}
		}
	}
	
	@RequestMapping(value="/checkemail",method=RequestMethod.POST)
	public String checkEmailAvailabilty(@RequestParam("q") String userEmailId){
		if(userService.isUserEmailIdExists(userEmailId)){
			return "EmailId Already Exists...";
		}
		return "";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void verifyUser(HttpServletRequest request,HttpServletResponse response){
		String userEmailId=request.getParameter("userEmailId");
		String string=userService.verifyUser(userEmailId,request.getParameter("userPassword"));
		if(string.equalsIgnoreCase("YES")){
			HttpSession session=request.getSession(true);
			session.setAttribute("userEmailId", userEmailId);
			try {
				response.sendRedirect("home.jsp");
			} catch (IOException e) {}
		}
		else {
			try {
				response.sendRedirect("login.jsp?message="+string);
			} catch (IOException e) {}
		}
	}
	
	@RequestMapping(value="/activateuser")
	public void activateUser(@ModelAttribute("userPojo") UserPojo userPojo,HttpServletResponse response){
		String string=userService.activateUser(userPojo);
		try {
			response.sendRedirect("login.jsp?message="+string);
		} catch (IOException e) {}
		
	}
	
	@RequestMapping(value="/forgotpassword",method=RequestMethod.POST)
	public void forgotPassword(@ModelAttribute("userPojo") UserPojo userPojo,HttpServletResponse response){
		String string=userService.forgotPassword(userPojo);
		if(string.equalsIgnoreCase("YES")){
			try {
				response.sendRedirect("login.jsp?message="+"Please visit your mail to reset your password");
			} catch (IOException e) {
			}
		}
		else {
			try {
				response.sendRedirect("forgotpassword.jsp?message="+string);
			} catch (IOException e) {}
		}
	}
	
	@RequestMapping(value="/checkEmailId",method=RequestMethod.POST)
	public String checkEmailId(@RequestParam("q") String userEmailId){
		if(userService.isUserEmailIdExists(userEmailId)) return "";
		else return "EmailId doesn't exists Please <a href='userregistration.jsp'>Register</a>";
	}
	
	@RequestMapping(value="/verifycneid",method=RequestMethod.POST)
	public String checkEmailAndContactMatch(@RequestParam("userEmailId") String userEmailId,
			@RequestParam("userContactNumber") long userContactNumber){
		return userService.checkMatch(userEmailId,userContactNumber);
	}
	
	@RequestMapping(value="/verifyfplink")
	public void verifyForgotPasswordLink(HttpServletRequest request,HttpServletResponse response){
		String userEmailId=request.getParameter("userEmailId");
		String string=userService.verifyForgotPasswordLink(userEmailId,request.getParameter("userToken"));
		if(string.equalsIgnoreCase("YES")){
			try {
				request.setAttribute("userEmailId",userEmailId);
				request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
			} catch (ServletException | IOException e) {}
		}
		else {
			try {
				response.sendRedirect("login.jsp?message="+string);
			} catch (IOException e) {}
		}
	}
	
	@RequestMapping(value="/resetpassword",method=RequestMethod.POST)
	public void resetPassword(@RequestParam("userEmailId") String userEmailId,
			@RequestParam("userPassword") String userPassword,HttpServletResponse response){
		String string=userService.resetPassword(userEmailId, userPassword);
		try {
			response.sendRedirect("login.jsp?message="+string);
		} catch (IOException e) {}
	}
	
	@RequestMapping(value="/viewprofile")
	public void getProfile(HttpServletRequest request,HttpServletResponse response){
	
		String userEmailId=null;
		try{
		HttpSession session=request.getSession(false);
		if(session!=null) userEmailId=session.getAttribute("userEmailId").toString();
		}catch(Exception e){
			try {
				response.sendRedirect("login.jsp?message=You must login first");
				return;
			} catch (IOException e1) {}
		}
		UserPojo userPojo=userService.getUserProfile(userEmailId);
		try {
			request.setAttribute("user", userPojo);
			request.getRequestDispatcher("showprofile.jsp").forward(request, response);
		} catch (ServletException | IOException e) {}
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.POST)
	public void updateUser(@ModelAttribute("userPojo") UserPojo userPojo,HttpServletResponse response){
        String string=userService.updateUser(userPojo);
		try {
			response.sendRedirect("home.jsp?message="+string);
		} catch (IOException e) {}
	}
	
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public void changePassword(HttpServletRequest request,HttpServletResponse response){
		String userEmailId=null;
		try {
			HttpSession session=request.getSession(false);
			if(session==null) throw new Exception();
			userEmailId=session.getAttribute("userEmailId").toString();
		} catch (Exception e) {
			try {
				response.sendRedirect("login.jsp?message=You must login first");return;
			} catch (IOException e1) {}
		}
     String string=userService.changePassword(userEmailId,request.getParameter("userCurrentPassword"),
    		 request.getParameter("userNewPassword"));
	 if(string.equalsIgnoreCase("YES")){
		 try {
			response.sendRedirect("home.jsp?message=Your Password Changed Successfully");return;
		} catch (IOException e) {}
	 }
	 else {
		 try {
			response.sendRedirect("changepassword.jsp?message="+string);return;
		} catch (IOException e) {}
	 }
	}
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session=request.getSession(false);
			if(session!=null) {
				session.invalidate();throw new Exception();
			}
			else throw new Exception();
		} catch (Exception e) {
			try {
				response.sendRedirect("login.jsp");return;
			} catch (IOException e1) {}
		}
	}
	@RequestMapping(value="/uploadimage",method=RequestMethod.POST)
	public void uploadImage(@RequestParam("userImagePath") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		String userEmailId=null;
		try {
			HttpSession session=request.getSession(false);
			if(session==null) throw new Exception();
			userEmailId=session.getAttribute("userEmailId").toString();
		} catch (Exception e) {
			try {
				response.sendRedirect("login.jsp?message=You must login first");return;
			} catch (IOException e1) {}
		}
	   String string=userService.uploadImage(userEmailId, file);
	  
		    try {
				response.sendRedirect("home.jsp?message="+string);return;
			} catch (IOException e) {}
		 }
	@RequestMapping(value="/viewimage")
	public byte[] getImage(HttpServletRequest request,HttpServletResponse response){
		String userEmailId=null;
		try {
			HttpSession session=request.getSession(false);
			if(session==null) throw new Exception();
			userEmailId=session.getAttribute("userEmailId").toString();
		} catch (Exception e) {
			try {
				response.sendRedirect("login.jsp?message=You must login first");
			} catch (IOException e1) {}
		}
		System.out.println(userEmailId);
	   String imageURL=userService.getImage(userEmailId);
	   System.out.println(imageURL);
	   File file=new File(imageURL);
	   try {
		   
		return Files.readAllBytes(file.toPath());
	} catch (IOException e) {return null;}
	}
}
