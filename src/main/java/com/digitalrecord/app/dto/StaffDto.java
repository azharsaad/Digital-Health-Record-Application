package com.digitalrecord.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String id;
    private String department;
    private String position;
    private String phone;
    private String shift;
    private String email;
    private String address;
    private String userId;
}
