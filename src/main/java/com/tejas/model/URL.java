package com.tejas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name="URL",schema="shortURL")
public class URL {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	
	@Column(name="shortURL",unique=true)
	String shortUrl;
	
	@Column(name="longURL",length=2100)
	String longUrl;
	
	@Column(name="createdAt")
	String createdAt;
	
	@Column(name="expiresAt")
	String expiresAt="7";
	
	@Column(name="domain")
	String Domain="default";
	
	@Column(name="clicks")
	Integer clickcnt=0;
	
	@PrePersist
	public void beforeCreation() {
		Date tempdate = new Date();
		this.createdAt = tempdate.toString();
		this.expiresAt = (new Date(tempdate.getTime()+(Integer.parseInt(this.expiresAt)*24*3600*1000))).toString();
	}
	
	public String getDomain() {
		return Domain;
	}


	public Integer getClickcnt() {
		return clickcnt;
	}

	public void setClickcnt(Integer clickcnt) {
		this.clickcnt = clickcnt;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}
	
	
}
