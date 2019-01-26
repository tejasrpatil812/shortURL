package com.tejas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

//FOR REPORT FORMATTING
@SuppressWarnings("serial")
public class ReportFormat implements Serializable{
	
	long TotalGETRequest;
	long TotalPOSTRequest;
	long TotalGETRequestinLast24hours;
	long TotalPOSTRequestinLast24hours;
	String MostRequestedURL;
	BigDecimal CountOfMostRequestedURL;
	String MostRequestedDomain;
	BigDecimal CountOfMostRequestedDomain;
	String MostProvidedDomain;
	BigInteger CountOfMostProvidedDomain;
	String Timestamp;
	
	
	//GETTER and SETTERS

	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public long getTotalGETRequest() {
		return TotalGETRequest;
	}
	public void setTotalGETRequest(long totalGETRequest) {
		TotalGETRequest = totalGETRequest;
	}
	public long getTotalPOSTRequest() {
		return TotalPOSTRequest;
	}
	public void setTotalPOSTRequest(long totalPostRequest) {
		TotalPOSTRequest = totalPostRequest;
	}
	public long getTotalGETRequestinLast24hours() {
		return TotalGETRequestinLast24hours;
	}
	public void setTotalGETRequestinLast24hours(long totalGETRequestinLast24hours) {
		TotalGETRequestinLast24hours = totalGETRequestinLast24hours;
	}
	public long getTotalPOSTRequestinLast24hours() {
		return TotalPOSTRequestinLast24hours;
	}
	public void setTotalPOSTRequestinLast24hours(long totalPOSTRequestinLast24hours) {
		TotalPOSTRequestinLast24hours = totalPOSTRequestinLast24hours;
	}
	public String getMostRequestedURL() {
		return MostRequestedURL;
	}
	public void setMostRequestedURL(String mostRequestedURL) {
		MostRequestedURL = mostRequestedURL;
	}
	public BigDecimal getCountOfMostRequestedURL() {
		return CountOfMostRequestedURL;
	}
	public void setCountOfMostRequestedURL(BigDecimal countOfMostRequestedURL) {
		CountOfMostRequestedURL = countOfMostRequestedURL;
	}
	public String getMostRequestedDomain() {
		return MostRequestedDomain;
	}
	public void setMostRequestedDomain(String mostRequestedDomain) {
		MostRequestedDomain = mostRequestedDomain;
	}
	public BigDecimal getCountOfMostRequestedDomain() {
		return CountOfMostRequestedDomain;
	}
	public void setCountOfMostRequestedDomain(BigDecimal countOfMostRequestedDomain) {
		CountOfMostRequestedDomain = countOfMostRequestedDomain;
	}
	public String getMostProvidedDomain() {
		return MostProvidedDomain;
	}
	public void setMostProvidedDomain(String mostProvidedDomain) {
		MostProvidedDomain = mostProvidedDomain;
	}
	public BigInteger getCountOfMostProvidedDomain() {
		return CountOfMostProvidedDomain;
	}
	public void setCountOfMostProvidedDomain(BigInteger countOfMostProviededDomain) {
		CountOfMostProvidedDomain = countOfMostProviededDomain;
	}
	
}
