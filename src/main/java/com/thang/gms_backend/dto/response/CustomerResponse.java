package com.thang.gms_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String fullName;
    private String phone;
}
