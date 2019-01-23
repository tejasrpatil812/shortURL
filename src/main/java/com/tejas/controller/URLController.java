package com.tejas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tejas.model.URL;
import com.tejas.service.URLService;

@Controller
public class URLController {
	
	@Autowired
	URLService urlService;
	
	
	@RequestMapping(value="/url",method=RequestMethod.GET)
	@ResponseBody
	public List<URL> getUrls() {
		return urlService.getUrl();
	}
	
	@RequestMapping(value="/urls",method=RequestMethod.GET)
	@ResponseBody
	public String getUrl(@RequestParam String url) {
		String temp=urlService.getUrlByShortUrl(url);
		urlService.updateDB(url);
		return temp;
	}
	
	@RequestMapping(value="/urls",method=RequestMethod.POST)
	@ResponseBody
	public String setUrl(@RequestBody URL url) {
		String temp=urlService.setUrl(url);
		return temp;
	}
	
	@RequestMapping(value="/urls/{urlID}",method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	@ResponseBody
	public void deleteURL(@PathVariable Integer urlID) {
		urlService.deleteURL(urlID);
	}
	
	@RequestMapping(value="/urls/report",method=RequestMethod.GET)
	@ResponseBody
	public String reportURL() {
		return urlService.reportURL();
	}
	
}
