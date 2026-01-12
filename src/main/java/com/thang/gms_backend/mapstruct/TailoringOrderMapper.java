package com.thang.gms_backend.mapstruct;

import com.thang.gms_backend.dto.response.OrderResponse;
import com.thang.gms_backend.entity.TailoringOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CustomerMapper.class, MeasurementMapper.class})
public interface TailoringOrderMapper {

    @Mapping(source = "id", target = "id")
    OrderResponse toOrderResponse(TailoringOrders tailoringOrder);
}
