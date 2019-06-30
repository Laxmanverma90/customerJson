package com.hcl.bank.service;

import com.hcl.bank.bean.CustomerBean;

public interface CustomerService {
	
	public CustomerBean addCustomer(CustomerBean customerBean);
	
	public Object getCustomer(long id);

	public Object updateCustomer(CustomerBean customerBean);

	public Object deleteCustomer(long id);

}