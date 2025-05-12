package com.digitalrecord.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    private String name;
    private String dosage;
    private String frequency;
    private String duration;
}
