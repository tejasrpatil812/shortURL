package com.tejas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
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
	
	//Caching only when URL is not Empty
	@Cacheable(value="shortURL",unless="#result==null")
	public URL getUrlByShortUrl(String url) {
		long id=Helper.toBase10(url.substring(url.length()-7));						//Extracting 7 letters String
		URL tempURL=urlDao.findOne(id);
		return tempURL;
	}

	public URL setUrl(URL url) {
		URL tempurl=urlDao.save(url);
		String shrt=Helper.toBase62(tempurl.getId());								//Converting Key into Base62 String
		String shrturl="https://www."+tempurl.getDomain()+".com/"+Helper.padLeftZeros(shrt,7);
		tempurl.setShortUrl(shrturl);												//Creating Short URL from Base62 String and Domain
		return urlDao.save(tempurl);
	}

	@Async
	public void updateDB(String url) {
		Report newreport=new Report();												//Updates Database
		newreport.setShortUrl(url);
		newreport.setDomain(Helper.getDomainName(url));
		reportDao.save(newreport);
	}

	public ReportFormat getReport() {
		ReportFormat report = new ReportFormat();
		Date today = new Date();
		Date yesterday = new Date(today.getTime() - Helper.oneDay);
		report.setTimestamp(today.toString());
		report.setTotalGETRequest(reportDao.count());
		report.setTotalPOSTRequest(urlDao.count());
		report.setTotalGETRequestinLast24hours(reportDao.getCountOfLatestGET(yesterday));
		report.setTotalPOSTRequestinLast24hours(urlDao.getCountOfLatestPOST(yesterday));
		report.setMostRequestedURL(reportDao.getMostReqURL());
		report.setCountOfMostRequestedURL(reportDao.countByShortUrl(report.getMostRequestedURL()));
		report.setMostProvidedDomain(urlDao.mostProvidedDomain());
		report.setCountOfMostProvidedDomain(urlDao.getCountOfMostProvidedDomain(report.getMostProvidedDomain()));
		report.setMostRequestedDomain(reportDao.mostReqDomain());
		report.setCountOfMostRequestedDomain(reportDao.getCountofMostRequestedDomain(report.getMostRequestedDomain()));
		
		return report;
	}


}
