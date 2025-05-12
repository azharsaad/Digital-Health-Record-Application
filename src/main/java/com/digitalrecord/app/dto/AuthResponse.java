package com.digitalrecord.app.dto;

import com.digitalrecord.app.enums.Role;
import lombok.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Role role;

}
