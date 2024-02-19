package com.cbl.jwttoken.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    @NotBlank(message = "Username is Required")
    @NotNull(message = "Username is Required")
    private String userName;

    @NotBlank(message = "Password is Required")
    @NotNull(message = "Password is Required")
    private String password;

    @NotBlank(message = "Device ID is Required")
    @NotNull(message = "Device ID is Required")
    private String deviceId;

    // Internal Use Purpose
    private String sessionId;
    private Long userId;
    private String latitude;
    private String longitude;
    private String phoneNo;
    private String status;
    private Long id;
}
