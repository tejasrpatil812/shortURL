package com.tejas.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tejas.service.Convertor;

@SuppressWarnings("serial")
@Entity
@Table(name="Report",schema="shortURL")
public class Report implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="shortURL")
	String shortUrl;
	
	@Column(name="requestDate")
	Date requestDate;

	@Column(name="domain")
	String domain;
	
	@Column(name="cnt")
	long cnt;
	
	public Report() {
		
	}
	
	public Report(String url, Date currDate) {
		this.shortUrl=url;
		this.requestDate=currDate;
		this.domain=Convertor.getDomainName(url);
		this.cnt=1;
	}

	/*
	 * GETTER and SETTERS
	 */
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public long getCnt() {
		return cnt;
	}

	public void setCnt(long count) {
		this.cnt = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}	
}
