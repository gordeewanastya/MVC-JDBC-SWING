package com.gordeeva.TJI_Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
