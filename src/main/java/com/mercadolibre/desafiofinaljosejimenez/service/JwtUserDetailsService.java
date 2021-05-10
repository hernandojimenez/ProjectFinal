package com.mercadolibre.desafiofinaljosejimenez.service;

import java.util.ArrayList;
import java.util.Map;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.UserRequestDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.InvalidFilterInformation;
import com.mercadolibre.desafiofinaljosejimenez.model.MainSubsidiary;
import com.mercadolibre.desafiofinaljosejimenez.model.Subsidiary;
import com.mercadolibre.desafiofinaljosejimenez.model.UserCentral;
import com.mercadolibre.desafiofinaljosejimenez.repositories.MainSubsidiaryRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.SubsidiaryRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.UserRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private SubsidiaryRepository subsidiaryRepository;

	private MainSubsidiaryRepository mainSubsidiaryRepository;

	public JwtUserDetailsService(SubsidiaryRepository subsidiaryRepository, MainSubsidiaryRepository mainSubsidiaryRepository) {
		this.subsidiaryRepository = subsidiaryRepository;
		this.mainSubsidiaryRepository = mainSubsidiaryRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCentral user = userRepository.findByUsernameEquals(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	public boolean autorizado(String username, Map<String, String> params) throws Exception {
		UserCentral user = userRepository.findByUsernameEquals(username);

		boolean value = true;//params.containsKey("hhhh");

		if(user.getSubsidiary_id() == null && value) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return true;
	}
	protected void configure(HttpSecurity http) throws Exception {

	}
	
	public UserCentral save(UserRequestDTO user) {
		if (!Validator.isValidPass(user.getPassword())) throw new InvalidFilterInformation("Invalid Password");
		UserCentral userCentral = userRepository.findByUsernameEquals(user.getUsername());
		if(userCentral!=null) throw new UsernameNotFoundException("Username already exists: " + user.getUsername());
		UserCentral newUser = new UserCentral();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setSubsidiary_id(user.getCentral());
		newUser.setMain_subsidiary_id(user.getCentral2());
		return userRepository.save(newUser);
	}
}