package com.tejas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.tejas.model.URL;
import com.tejas.repo.URLDao;


@Service
public class URLService {
	
	@Autowired
	URLDao urlDao;
	
	public String setUrl(URL url) {
		URL tempurl=urlDao.save(url);
		
		if(tempurl==null)
			return "Incorrect Input";
	
		Helper.incrValue("postCount");
		String shrt=Helper.toBase62(tempurl.getId());
		String shrturl="https://www."+tempurl.getDomain()+".com/"+Helper.padLeftZeros(shrt,7);
		tempurl.setShortUrl(shrturl);
		return urlDao.save(tempurl).getShortUrl();
	}

	public List<URL> getUrl() {
		return urlDao.findAll();
	}

	@Cacheable(value="urlCache")
	public String getUrlByShortUrl(String url) {
		Integer id=Helper.toBase10(url.substring(url.length()-7));
		URL tempurl=urlDao.findOne(id);
		return (tempurl!=null)?tempurl.getLongUrl():"Not Found";
	}

	public String deleteURL(Integer urlID) {
		urlDao.delete(urlID);
		return "Success";
	}
	
	@Async
	public void updateDB(String url) {
		Helper.incrValue("getCount");
		Integer id=Helper.toBase10(url.substring(url.length()-7));
		URL tempurl=urlDao.findOne(id);
		tempurl.setClickcnt(tempurl.getClickcnt()+1);
		urlDao.save(tempurl);
	}

	public String reportURL() {
		Integer getCount=Helper.getValue("getCount");
		Integer postCount=Helper.getValue("getCount");
		return "getCount : "+getCount+" postCount : "+postCount;
	}
	

}
