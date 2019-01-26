package com.tejas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tejas.model.ReportFormat;
import com.tejas.model.URL;
import com.tejas.service.URLService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="URL Shortner")
@Controller
@RequestMapping("/urls")
public class URLController {
	
	@Autowired
	URLService urlService;
	
	/*
	 * GET REQUEST FOR SHORT-LONG
	*/
	@ApiOperation(value = "View URL's Info mapped to the provided short URL", response = URL[].class)
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getUrl(@ApiParam(value = "Insert ShortURL", required = true)@RequestParam String shorturl) {
		return urlService.getURL(shorturl);
	}
	
	
	/*
	 * POST REQUEST FOR LONG-SHORT
	*/
	@ApiOperation(value = "Provide LongUrl along with optional parameters to be shorten.",response = URL[].class)
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> setUrl(@ApiParam(value = "Insert longUrl and optional expiresAt(in days) and domain", required = true)@RequestBody URL URLBody) {
		return urlService.setURL(URLBody);
	}
	
	/*
	 * GET REQUEST FOR REPORT
	*/
	@ApiOperation(value = "View the Report of the URL Shortner Service.",response = ReportFormat[].class)
	@RequestMapping(value="/report",method=RequestMethod.GET)
	@ResponseBody
	public  ResponseEntity<Object> getReport() {
		return urlService.getReport();
	}
	
}
