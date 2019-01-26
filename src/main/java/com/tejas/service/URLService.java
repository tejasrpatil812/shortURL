package com.tejas.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tejas.model.Report;
import com.tejas.model.ReportFormat;
import com.tejas.model.URL;
import com.tejas.repo.ReportDao;
import com.tejas.repo.URLDao;


@Service
public class URLService {
	
	@Autowired
	URLDao urlDao;
	
	@Autowired
	ReportDao reportDao;
	
	@Autowired
	ApplicationContext applicationContext;
	
	/*
	 * Checking Method for GET Request
	 */
	public ResponseEntity<Object> getURL(String url){
		URLService tempService=applicationContext.getBean(URLService.class);
		URL temp=tempService.getUrlByShortUrl(url);
	
		if(temp==null)
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		
		if(temp.getExpiresAt().before(new Date()))
			return new ResponseEntity<Object>("\"ERROR\"\nURL Expired", HttpStatus.BAD_REQUEST);
		
		tempService.updateReq(url);								//Call to Async Function to Update Database
		return new ResponseEntity<Object>(temp, HttpStatus.OK);
	}
	
	/*
	 * Main Method for GET Request
	 * Enabled Caching with condition
	 */
	@Cacheable(value="shortURL",unless="#result==null")
	public URL getUrlByShortUrl(String url) {
		long id=Convertor.toBase10(url.substring(url.length()-7));						
		URL tempURL=urlDao.findOne(id);
		return tempURL;
	}
	
	/*
	 * Method for Updating Click of GET Request
	 * Enabled Async
	 */
	@Async
	public void updateReq(String url) {
		java.sql.Date currDate= new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        Report report=reportDao.findByShortUrlAndRequestDate(url, currDate);
        
        if(report != null)
            report.setCnt(report.getCnt()+1);
        else 
            report=new Report(url, currDate);
        
        reportDao.save(report);
	}

	/*
	 * Check Method for POST Request
	 */
	public ResponseEntity<Object> setURL(URL url){
		URL temp=this.setUrlDB(url);
		
		if(temp==null)
			return new ResponseEntity<Object>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Object>(temp,HttpStatus.ACCEPTED);
	}
	
	/*
	 *  Main Method For POST Request
	 */
	@Transactional
	public URL setUrlDB(URL url) {
		URL tempurl=urlDao.save(url);
		String shrt=Convertor.toBase62(tempurl.getId());								
		String shrturl="https://www."+tempurl.getDomain()+".com/"+Convertor.padLeftZeros(shrt,7);
		tempurl.setShortUrl(shrturl);												
		return urlDao.save(tempurl);
	}
	
	/*
	 * Main Method For Report Request
	 */
	public ResponseEntity<Object> getReport() {
		ReportFormat report = new ReportFormat();
		Date today = new Date();
		Date yesterday = new Date(today.getTime() - Convertor.oneDay);
		report.setTimestamp(today.toString());
		report.setTotalGETRequest(reportDao.countGET());
		report.setTotalPOSTRequest(urlDao.count());
		report.setTotalGETRequestinLast24hours(reportDao.getCountOfLatestGET(yesterday));
		report.setTotalPOSTRequestinLast24hours(urlDao.getCountOfLatestPOST(yesterday));
		report.setCountOfMostRequestedURL(reportDao.getCntOfMostReqURL());
		report.setMostRequestedURL(reportDao.getMostReqURL(report.getCountOfMostRequestedURL()));
		report.setCountOfMostRequestedDomain(reportDao.getCntOfMostReqDomain());
		report.setMostRequestedDomain(reportDao.getMostReqDom(report.getCountOfMostRequestedDomain()));
		report.setCountOfMostProvidedDomain(urlDao.getCountOfMostProvidedDomain());
		report.setMostProvidedDomain(urlDao.mostProvidedDomain(report.getCountOfMostProvidedDomain()));
		return new ResponseEntity<Object>(report,HttpStatus.OK);
	}


}
