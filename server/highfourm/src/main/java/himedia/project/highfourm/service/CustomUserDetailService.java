package himedia.project.highfourm.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import himedia.project.highfourm.dto.user.CustomUserDetails;
import himedia.project.highfourm.entity.User;
import himedia.project.highfourm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUserId(userId);
		
		if(user != null) {
			return new CustomUserDetails(user);
		}
		return null;
	}

}
