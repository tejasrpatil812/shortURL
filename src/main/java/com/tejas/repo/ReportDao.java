package com.tejas.repo;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tejas.model.Report;

public interface ReportDao extends JpaRepository<Report, Long>{
	
	@Query("SELECT COUNT(id) FROM Report WHERE reqTime>=?1")
	public long getCountOfLatestGET(Date yesterday);

	@Query(value="SELECT shortURL.Report.shortUrl FROM shortURL.Report GROUP BY shortURL.Report.shortUrl ORDER BY COUNT(*) DESC LIMIT 1",nativeQuery=true)
	public String getMostReqURL();

	@Query("SELECT COUNT(shortUrl) FROM Report WHERE shortUrl=?1")
	public Long countByShortUrl(String mostreqURL);

	@Query(value="SELECT shortURL.Report.domain FROM shortURL.Report GROUP BY shortURL.Report.domain ORDER BY COUNT(*) DESC LIMIT 1",nativeQuery=true)
	public String mostReqDomain();

	@Query("SELECT COUNT(domain) FROM Report WHERE domain=?1")
	public Long getCountofMostRequestedDomain(String mostRequestedDomain);
}
