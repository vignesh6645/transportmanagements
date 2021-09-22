package com.example.TransportManagement.baseresponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public
class BaseResponseRep<T> {

    @Builder.Default
    private String statusMsg="Success";

    @Builder.Default
    private String statusCode = "200";

    private T Data;
}
