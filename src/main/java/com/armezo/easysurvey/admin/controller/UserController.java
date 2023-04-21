package com.armezo.easysurvey.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.armezo.easysurvey.Utility.Constant;
import com.armezo.easysurvey.Utility.GenAccesskey;
import com.armezo.easysurvey.admin.model.UserMaster;
import com.armezo.easysurvey.admin.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public String showUserInfo(Model mod,HttpServletRequest request)
	{
		return Constant.addUser;
	}
	
	@PostMapping("/addUserPro")
	 public String addClient(@ModelAttribute("AddQuestionEntity")UserMaster entity,
			 @RequestParam("imageFile")MultipartFile photo,Model mod,HttpServletRequest request)
	 {
		String password = "";
		String photokey = "";
		try {
			password = GenAccesskey.generateAccesskey(5);
			photokey = GenAccesskey.generateAccesskey(5);
			if(photo.getOriginalFilename() != null || !photo.getOriginalFilename().equals("")) {
				 entity.setPhotoFlag("Y");
			 }else {
				 entity.setPhotoFlag("N");
			 }
			entity.setRoleid("C");
			entity.setStatus("A");
			entity.setSystemType("RC");
			entity.setPassword(password);
			entity.setPhotoKey(photokey);
			
			if(photo.getOriginalFilename() == null || photo.getOriginalFilename().equals("")) {
			 }else {
				 String servletpath = request.getServletPath();
					String realpath = request.getRealPath(request.getServletPath());
					servletpath = servletpath.replaceAll("/", "\\\\");
					realpath = realpath.substring(0, realpath.indexOf(servletpath));
					String imagepath = realpath +"\\WEB-INF\\tr\\img\\";
					File dest = new File(imagepath+"/"+photokey+".jpg"); 
				 photo.transferTo(dest);
			 }
			
			userService.addUserData(entity);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return Constant.addUser;
	 }
	
	//View All Users
		@GetMapping("/viewUsers")
		public String viewAllUsers(Model model) {
			List<UserMaster> users = userService.getAllCompany();
			model.addAttribute("users", users);
			return Constant.viewUser;
		}
		
		//Show Update Client Page
		@PostMapping("/viewUpdateUser")
		public String viewUpdateUserPage(@RequestParam("id") Long id, Model model) {
			UserMaster user = new UserMaster();
			//Get User By Id
			Optional<UserMaster> optional = userService.getUserById(id);
			if(optional.isPresent()) {
				user=optional.get();
			}
			model.addAttribute("user", user);
			return Constant.editUser;
		}
		
		//Update Client
		@PostMapping("/updateUser")
		public String updateClientDetails(@ModelAttribute("user") UserMaster user,@RequestParam("photo") MultipartFile file ,Model model) {
			
			if(file.getOriginalFilename()!=null && !file.getOriginalFilename().equals("")) {
				//System.out.println(user.getPhotoKey()+"8888888");
				Path path= Paths.get("src", "main", "webapp", "WEB-INF", "image",user.getPhotoKey());
				File folder = new File(path.toString());
				if(!folder.exists()) {
					folder.mkdirs();
				}
				Path filePath = path.resolve(StringUtils.cleanPath(file.getOriginalFilename()));
						//Paths.get(path.toString(), file.getOriginalFilename());
				try {
					Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
				}
				//Setting data to object
					user.setPhotoFlag("Y");
					//user.setPhotoName(file.getOriginalFilename());
					model.addAttribute("path", filePath.toAbsolutePath().toString());
				}else {
					user.setPhotoFlag("N");
				}
				user.setRoleid("C");
				user.setStatus("A");
				user.setSystemType("RC");
				user.setPassword(user.getPhotoKey());
				user.setPhotoKey(user.getPhotoKey());
				//user.setClientName(user.getClientName());
				try {
					userService.addUserData(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<UserMaster> users = userService.getAllCompany();
				model.addAttribute("users", users);
			return Constant.viewUser;
		}

}
