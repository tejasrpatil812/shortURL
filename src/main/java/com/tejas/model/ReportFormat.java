package com.tejas.model;

import java.io.Serializable;

//FOR REPORT FORMATTING
@SuppressWarnings("serial")
public class ReportFormat implements Serializable{
	
	long TotalGETRequest;
	long TotalPOSTRequest;
	long TotalGETRequestinLast24hours;
	long TotalPOSTRequestinLast24hours;
	String MostRequestedURL;
	long CountOfMostRequestedURL;
	String MostRequestedDomain;
	long CountOfMostRequestedDomain;
	String MostProvidedDomain;
	long CountOfMostProvidedDomain;
	String Timestamp;
	
	
	//GETTER and SETTERs

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
	public long getCountOfMostRequestedURL() {
		return CountOfMostRequestedURL;
	}
	public void setCountOfMostRequestedURL(long countOfMostRequestedURL) {
		CountOfMostRequestedURL = countOfMostRequestedURL;
	}
	public String getMostRequestedDomain() {
		return MostRequestedDomain;
	}
	public void setMostRequestedDomain(String mostRequestedDomain) {
		MostRequestedDomain = mostRequestedDomain;
	}
	public long getCountOfMostRequestedDomain() {
		return CountOfMostRequestedDomain;
	}
	public void setCountOfMostRequestedDomain(long countOfMostRequestedDomain) {
		CountOfMostRequestedDomain = countOfMostRequestedDomain;
	}
	public String getMostProvidedDomain() {
		return MostProvidedDomain;
	}
	public void setMostProvidedDomain(String mostProvidedDomain) {
		MostProvidedDomain = mostProvidedDomain;
	}
	public long getCountOfMostProvidedDomain() {
		return CountOfMostProvidedDomain;
	}
	public void setCountOfMostProvidedDomain(long countOfMostProviededDomain) {
		CountOfMostProvidedDomain = countOfMostProviededDomain;
	}
	
}
