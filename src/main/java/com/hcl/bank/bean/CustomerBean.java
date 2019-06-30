package com.hcl.bank.bean;

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
	private String name;
	private long phone;
	private String address;

}