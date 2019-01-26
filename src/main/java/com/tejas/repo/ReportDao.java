package com.tejas.repo;


import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tejas.model.Report;

public interface ReportDao extends JpaRepository<Report, Long>{
	
	Report findByShortUrlAndRequestDate(String url,java.sql.Date currDate);
	
	@Query("SELECT SUM(cnt) FROM Report WHERE requestDate>=?1")
	Long getCountOfLatestGET(Date yesterday);

	@Query("SELECT SUM(cnt) FROM Report")
	Long countGET();
	
	@Query(value="select max(cntsum) from (select sum(cnt) as cntsum,shortURL from shortURL.Report group by shortURL) as temp",nativeQuery=true)
	BigDecimal getCntOfMostReqURL();
	
	@Query(value="select max(cntsum) from (select sum(cnt) as cntsum,domain from shortURL.Report group by domain) as temp",nativeQuery=true)
	BigDecimal getCntOfMostReqDomain();
	
	@Query(value="select shortURL from (select sum(cnt) as cntsum,shortURL from shortURL.Report group by shortURL) as temp where cntsum=?1",nativeQuery=true)
	String getMostReqURL(BigDecimal countOfMostRequestedURL);

	@Query(value="select domain from (select sum(cnt) as cntsum,domain from shortURL.Report group by domain) as temp where cntsum=?1",nativeQuery=true)
	String getMostReqDom(BigDecimal countOfMostRequestedDomain);
}
