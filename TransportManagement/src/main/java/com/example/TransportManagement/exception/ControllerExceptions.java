package com.example.TransportManagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@Component
public class ControllerExceptions  extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public ControllerExceptions(String errorCode,String errorMsg){
        super();
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
}
