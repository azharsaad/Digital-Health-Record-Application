package com.digitalrecord.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffProfileRequest {
    private String department;
    private String position;
    private String phone;
    private String shift;
    private String address;
    private String email;
    private String userId;
}
