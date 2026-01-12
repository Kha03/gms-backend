package com.thang.gms_backend.mapstruct;

import com.thang.gms_backend.dto.response.CustomerResponse;
import com.thang.gms_backend.entity.Customers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "id", target = "id")
    CustomerResponse toCustomerResponse(Customers customer);
}
