package com.thang.gms_backend.mapstruct;

import com.thang.gms_backend.dto.request.CustomerRequest;
import com.thang.gms_backend.dto.response.CustomerResponse;
import com.thang.gms_backend.entity.Customers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE =  Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "id")
    CustomerResponse toCustomerResponse(Customers customer);
}
