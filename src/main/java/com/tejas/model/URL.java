package com.tejas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tejas.service.Helper;



@SuppressWarnings("serial")
@Entity
@Table(name="URL",schema="shortURL")
public class URL implements Serializable{
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="shortURL",unique=true)
	String shortUrl;
	
	@Column(name="longURL",length=2100)
	String longUrl;
	
	@JsonIgnore
	@Column(name="createdAt")
	Date createdAt;

	@Column(name="expiresAt")
	Date expiresAt=new Date(11);
	
	@Column(name="domain")
	String domain="tp.pt";
	
	@PrePersist
	public void beforeCreation() {
		Date tempdate = new Date();
		this.createdAt = tempdate;
		this.expiresAt = new Date(tempdate.getTime()+this.expiresAt.getTime()*Helper.oneDay);
	}
	
	//GETTER and SETTERs

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
	
}
