package com.tejas.repo;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tejas.model.URL;

public interface URLDao extends JpaRepository<URL, Long>{
	
	URL findByShortUrl(String url);
	
	@Query("SELECT COUNT(id) FROM URL WHERE createdAt>=?1")
	public long getCountOfLatestPOST(Date yesterday);

	@Query(value="SELECT shortURL.URL.domain FROM shortURL.URL GROUP BY shortURL.URL.domain ORDER BY COUNT(*) DESC LIMIT 1",nativeQuery=true)
	String mostProvidedDomain();

	@Query("SELECT COUNT(domain) FROM URL WHERE domain=?1")
	Long getCountOfMostProvidedDomain(String mostProvidedDomain);
}
