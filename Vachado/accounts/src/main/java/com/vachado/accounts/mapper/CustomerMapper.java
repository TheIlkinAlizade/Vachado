package com.vachado.accounts.mapper;

import com.vachado.accounts.entity.Customer;
import com.vachado.accounts.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setUsername(customer.getUsername());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPoints(customer.getPoints());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setUsername(customerDto.getUsername());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPoints(customerDto.getPoints());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}