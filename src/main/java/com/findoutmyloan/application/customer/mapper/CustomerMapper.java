package com.findoutmyloan.application.customer.mapper;

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE=Mappers.getMapper(CustomerMapper.class);
    Customer convertToCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);
    CustomerDTO convertToCustomerDTO(Customer customer);

    Customer convertToCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}