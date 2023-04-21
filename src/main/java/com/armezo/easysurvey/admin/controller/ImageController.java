package com.armezo.easysurvey.admin.controller;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ImageController {

	
	@RequestMapping(value = "/Image", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@RequestParam("name") String name,HttpServletRequest request) {
		byte [] data = null;
		try {
			String servletpath = request.getServletPath();
			String realpath = request.getRealPath(request.getServletPath());
			servletpath = servletpath.replaceAll("/", "\\\\");
			realpath = realpath.substring(0, realpath.indexOf(servletpath));
			String imagepath = realpath +"\\WEB-INF\\tr\\img\\";
			data = Files.readAllBytes(new File(imagepath+"/"+name).toPath());  
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
	}
}
