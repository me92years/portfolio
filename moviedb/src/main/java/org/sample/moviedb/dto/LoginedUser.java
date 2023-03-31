package org.sample.moviedb.dto;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;
import org.sample.moviedb.entity.users.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginedUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String picture;
	private String role;
	
	public LoginedUser(User savedUser) {
		this.name = savedUser.getName();
		this.email = savedUser.getEmail();
		this.picture = savedUser.getPicture();
		this.role = savedUser.getRole().getTitle();
	}
	
	public String toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("name", name);
			json.put("email", email);
			json.put("picture", picture);
			json.put("role", role);
			return json.toString();
		} catch (JSONException e) {
			return "에러";
		}
	}

}
