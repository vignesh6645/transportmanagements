package com.example.TransportManagement.baseresponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class APIResponse <T>{

    Integer recordCount;

    T response;
}
