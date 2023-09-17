package com.pichincha2.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pichincha2.model.UserEntity;

@Repository
public interface UserRepo  extends CrudRepository<UserEntity, Long>{

	Optional<UserEntity> findByUsername(String username);
}
