package com.thang.gms_backend.mapstruct;

import com.thang.gms_backend.dto.response.MeasurementResponse;
import com.thang.gms_backend.entity.Measurements;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    @Mapping(source = "id", target = "id")
    MeasurementResponse toMeasurementResponse(Measurements measurement);

    @Mapping(source = "id", target = "id")
    List<MeasurementResponse> toMeasurementResponseList(List<Measurements> measurements);
}
