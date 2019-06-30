package com.hcl.bank.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.hcl.bank.bean.CustomerBean;

@Repository
public class CustomerRepository {

	static Map<Long, CustomerBean> customerMap = new HashMap<>();

	public CustomerBean addCustomer(CustomerBean custBean) {

		JSONParser jsonParser = new JSONParser();
		if (custBean != null) {
			JSONObject customerDetail = new JSONObject();
			customerDetail.put("id", custBean.getId());
			customerDetail.put("name", custBean.getName());
			customerDetail.put("phone", custBean.getPhone());
			customerDetail.put("address", custBean.getAddress());

			JSONObject customerObject = new JSONObject();
			customerObject.put("customerBean", customerDetail);

			try {
				File file = new File("customerData.json");
				JSONArray customerList = null;
				if (file.exists() && file.length() > 0) {
					FileReader reader = new FileReader("customerData.json");
					Object obj = jsonParser.parse(reader);
					customerList = (JSONArray) obj;
				} else {
					customerList = new JSONArray();
				}

				customerList.add(customerObject);

				FileWriter fileWriter = new FileWriter("customerData.json");
				fileWriter.write(customerList.toJSONString());
				fileWriter.flush();
				return custBean;
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Map<Long, CustomerBean> getCustomer() {
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("customerData.json")) {
			Object obj = jsonParser.parse(reader);

			JSONArray customerList = (JSONArray) obj;
			System.out.println(customerList);
			customerList.forEach(customer -> parseEmployeeObject((JSONObject) customer));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return customerMap;
	}

	public Object updateCustomer(CustomerBean customerBean) {

		if (customerMap.containsKey(customerBean.getId())) {
			customerMap.put(customerBean.getId(), customerBean);
		}
		return customerMap.get(customerBean.getId());
	}

	public Object deleteCustomer(long id) {
		getCustomer();
		customerMap.remove(id);
		return customerMap;
	}

	private static void parseEmployeeObject(JSONObject customerBean) {
		JSONObject customerObject = (JSONObject) customerBean.get("customerBean");

		long id = (long) customerObject.get("id");
		String name = (String) customerObject.get("name");
		long phone = (long) customerObject.get("phone");
		String address = (String) customerObject.get("address");

		customerMap.put(id, new CustomerBean(id, name, phone, address));
	}

}
