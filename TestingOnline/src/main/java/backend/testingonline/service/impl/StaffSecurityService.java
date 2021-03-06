package backend.testingonline.service.impl;

import java.util.ArrayList;

import backend.testingonline.model.Staff;
import backend.testingonline.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffSecurityService implements UserDetailsService{

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Staff staff = staffRepository.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode(staff.getPassword());
		return new User(username, pass , new ArrayList<>());
	}
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String pass = encoder.encode("admin");
//		System.out.println(pass);
//	}

}
