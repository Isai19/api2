package com.pichincha2.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha2.model.RoleEntity;

@Repository
public interface RoleRepo extends CrudRepository <RoleEntity, Long >{
	

}
