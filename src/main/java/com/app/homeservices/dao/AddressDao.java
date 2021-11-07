package com.app.homeservices.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.homeservices.pojos.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{

}
