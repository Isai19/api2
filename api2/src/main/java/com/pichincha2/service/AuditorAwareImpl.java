package com.pichincha2.service;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pichincha2.filters.JwtAuthenticationFilter;


public class AuditorAwareImpl  implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		/*capturar usuario*/
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
		}
		
		return Optional.of(authentication.getName());
	}
	
	
	

}
