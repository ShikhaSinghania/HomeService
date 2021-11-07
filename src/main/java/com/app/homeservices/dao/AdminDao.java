package com.app.homeservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.homeservices.pojos.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	@Query("SELECT ad FROM Admin ad WHERE ad.username= ?1 and ad.password = ?2")
	Admin findByUsernameAndPassword(String username, String password);

}
