package com.tejas.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	
	//GET REQUEST FOR SHORT-LONG
	@ApiOperation(value = "View URL's Info mapped to the provided short URL")
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getUrl(@ApiParam(value = "Insert ShortURL", required = true)@RequestParam String shorturl) {
		URL temp=urlService.getUrlByShortUrl(shorturl);
		
		if(temp==null)
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		
		if(temp.getExpiresAt().before(new Date()))
			return new ResponseEntity<Object>("\"ERROR\"\nURL Expired", HttpStatus.BAD_REQUEST);
		
		urlService.updateDB(shorturl);								//Call to Async Function to Update Database
		return new ResponseEntity<Object>(temp, HttpStatus.OK);
	}
	
	
	//POST REQUEST FOR LONG-SHORT
	@ApiOperation(value = "Provide LongUrl along with optional parameters to be shorten.")
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> setUrl(@ApiParam(value = "Insert longUrl and optional expiresAt(in days) and domain", required = true)@RequestBody URL URLBody) {
		URL temp=urlService.setUrl(URLBody);
		if(temp==null)
			return new ResponseEntity<Object>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(temp,HttpStatus.ACCEPTED);
	}
	
	
	//GET REQUEST FOR REPORT
	@ApiOperation(value = "View the Report of the URL Shortner Service.")
	@RequestMapping(value="/report",method=RequestMethod.GET)
	@ResponseBody
	public  ResponseEntity<Object> getReport() {
		ReportFormat reportFormat=urlService.getReport();
		if(reportFormat==null)
			return new ResponseEntity<Object>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(reportFormat,HttpStatus.OK);

	}
	
}
