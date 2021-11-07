package com.app.homeservices.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.homeservices.pojos.ServiceProvider;


@Repository
public interface ServiceProviderDao extends JpaRepository<ServiceProvider, Integer>{

	
	@Transactional
	@Modifying
	@Query(value="update service_provider  set working_status=?1 where service_provider_id=?2",nativeQuery=true)
	public void updateServiceProviderWoringStatus(int working_status,int service_provider_id);
	
	
	@Query("SELECT sp FROM ServiceProvider sp WHERE sp.username= ?1 and sp.password = ?2")
	ServiceProvider findByUsernameAndPassword(String username,String password);
	
	/*
	@Transactional
	@Modifying
	@Query("UPDATE ServiceProvider sp SET sp.workingStatus=?1 where sp.serviceProviderId=?2")
	int workCompleted(boolean status,int id);*/
	
	@Query("select sp from ServiceProvider sp join fetch sp.locations where sp.id=?1")
	ServiceProvider getServiceProviderDetails(int id);
	
	@Query(value="select * from service_provider where service_id=?1 and authentic_status=1 ",nativeQuery=true)
	List<ServiceProvider> getAuthenticatedServiceProvidersByServiceId(int serviceId);
	
	@Query("select sp from ServiceProvider sp where sp.authenticStatus!=1")
	List<ServiceProvider> getNotAuthenticatedServiceProvider();
	
	@Query("select sp from ServiceProvider sp where sp.authenticStatus!=1")
	List<ServiceProvider> getAllAuthenticatedServiceProvider();
}
