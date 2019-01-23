package com.tejas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.model.URL;

public interface URLDao extends JpaRepository<URL, Integer>{
	URL findByShortUrl(String url);
}
