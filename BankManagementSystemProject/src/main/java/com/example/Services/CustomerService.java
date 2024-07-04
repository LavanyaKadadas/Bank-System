package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.CustomerDTO;
import com.example.Entities.Customer;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());

        customer = customerRepository.save(customer);

        customerDTO.setId(customer.getId());
        return customerDTO;
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findByIdNotDeleted(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());

        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAllNotDeleted().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setId(customer.getId());
                    customerDTO.setName(customer.getName());
                    customerDTO.setEmail(customer.getEmail());
                    customerDTO.setPhoneNumber(customer.getPhoneNumber());
                    customerDTO.setAddress(customer.getAddress());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findByIdNotDeleted(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByIdNotDeleted(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());

        customer = customerRepository.save(customer);

        return customerDTO;
    }
}
