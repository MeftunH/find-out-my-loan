package com.findoutmycreditscore.application.customer.mapper;

import com.findoutmycreditscore.application.creditscore.mapper.CreditScoreMapper;
import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE=Mappers.getMapper(CustomerMapper.class);
    Customer convertToCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);
    CustomerDTO convertToCustomerDTO(Customer customer);
}