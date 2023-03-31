package org.sample.moviedb.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.sample.moviedb.dto.LoginedUser;
import org.sample.moviedb.dto.OAuthAttributes;
import org.sample.moviedb.entity.users.User;
import org.sample.moviedb.enums.Role;
import org.sample.moviedb.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final HttpSession httpSession;
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User loadedUser = delegate.loadUser(userRequest);
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		String usernameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, usernameAttributeName, loadedUser.getAttributes());
		User savedUser = saveOrUpdate(attributes);
		httpSession.setAttribute("loginedUser", new LoginedUser(savedUser));
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(savedUser.getRole().getKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	@Transactional
	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = userRepository.findByEmail(attributes.getEmail()).map(en -> en.update(attributes.getName(), attributes.getPicture())).orElse(null); 
		if (user != null) return user;
		User newUser = User.builder()
				.email(attributes.getEmail())
				.name(attributes.getName())
				.picture(attributes.getPicture())
				.role((attributes.getEmail().equals("lapislazuli.des@gmail.com")) ? Role.ADMIN : Role.USER)
				.build();
		userRepository.save(newUser);
		return newUser;
	}
	
}
