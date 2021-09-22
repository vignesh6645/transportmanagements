package com.example.TransportManagement.baseresponse;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BaseResponse <T> {

    String statusCode;

    String statusMsg;

    private T Data;
}
