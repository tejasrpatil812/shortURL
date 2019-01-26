package com.tejas.repo;


import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tejas.model.URL;


public interface URLDao extends JpaRepository<URL, Long>{
	
	@Query("SELECT COUNT(id) FROM URL WHERE createdAt>=?1")
	Long getCountOfLatestPOST(Date yesterday);

	@Query(value="select domain from (select COUNT(id) as cntsum,domain from shortURL.URL group by domain) as temp where cntsum=?1",nativeQuery=true)
	String mostProvidedDomain(BigInteger bigDecimal);

	@Query(value="select max(cntsum) from (select COUNT(id) as cntsum,domain from shortURL.URL group by domain) as temp",nativeQuery=true)
	BigInteger getCountOfMostProvidedDomain();
}
