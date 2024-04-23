package com.manuel.work.flow.hub.dto;


public class CredenzialiDTO {

    private String email;

    private String password;

    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CredenzialiDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
    
	public CredenzialiDTO() {
		
	}

}
