package com.api.tss.users.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.tss.users.models.UserEntity;

@RestController
public class Test {
    @Autowired
	private MongoTemplate mongoTemplate;
   /* @Autowired
    private UserRepository userRepository;*/
	@RequestMapping(value="/testt")
	public String test(@RequestParam("userEmailId") String userEmailId){
	boolean b=mongoTemplate.exists(Query.query(Criteria.where(userEmailId)), UserEntity.class);
	System.out.println(b);
    UserEntity userEntity2=mongoTemplate.findOne(Query.query(Criteria.where(userEmailId)),UserEntity.class);
	System.out.println(userEntity2);
    UserEntity userEntity=mongoTemplate.findById(userEmailId, UserEntity.class);
	System.out.println("User Entity:"+userEntity);
	List<UserEntity> list=mongoTemplate.find(Query.query(Criteria.where(userEmailId)),UserEntity.class);
	return	list.toString();
    
	}
	@RequestMapping(value="/test2")
	public String test2(){
	return null;
	}
	
	@RequestMapping(value="/imagetest",method=RequestMethod.POST)
	public void imageTest(@RequestParam("userImagePath") MultipartFile file){
     String imageType=file.getContentType();
     String[] s=imageType.split("/");
     String imagePath=s[s.length-1];
	try {
		file.transferTo(new File("/home/techbion/workspace/User-API/images/abcd."+imagePath));
	} catch (IllegalStateException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
