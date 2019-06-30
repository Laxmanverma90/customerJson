package com.hcl.bank.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.bean.CustomerBean;
import com.hcl.bank.repository.CustomerRepository;
import com.hcl.bank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerBean addCustomer(CustomerBean customerBean) {
		return customerRepository.addCustomer(customerBean);
	}

	@Override
	public Object getCustomer(long id) {
		Map<Long, CustomerBean> custBeanMap = customerRepository.getCustomer();
		if(custBeanMap.containsKey(id)) {
			return custBeanMap.get(id);
		}
		return custBeanMap;
	}

	@Override
	public Object updateCustomer(CustomerBean customerBean) {
		return customerRepository.updateCustomer(customerBean);
	}

	@Override
	public Object deleteCustomer(long id) {
		return customerRepository.deleteCustomer(id);
	}

}
