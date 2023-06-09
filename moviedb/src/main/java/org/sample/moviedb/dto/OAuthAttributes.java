package org.sample.moviedb.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String usernameAttributeName, Map<String, Object> attributes) {
		if (registrationId.equals("google")) return ofGoogle(usernameAttributeName, attributes);		
		return null;
	}

	private static OAuthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.nameAttributeKey(usernameAttributeName)
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.picture((String) attributes.get("picture"))
				.attributes(attributes)
				.build();
	}

}
