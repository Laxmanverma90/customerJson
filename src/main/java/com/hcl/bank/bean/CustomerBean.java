package com.hcl.bank.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean {
	
	private long id;
	
	@NotEmpty(message="Please provide name.")
	@Size(min=5, max=10, message = "Name should be between 5-10 char.")
	private String name;
	
	private long phone;
	
	@NotEmpty(message="Please enter customer address.")
	private String address;

}