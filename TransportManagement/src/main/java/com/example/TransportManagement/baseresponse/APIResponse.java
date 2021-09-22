package com.example.TransportManagement.baseresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse <T>{

    @Builder.Default
    private String statusMsg="Success";

    @Builder.Default
    private String statusCode = "200";

   // @Builder.Default
    Integer recordCount;

    T response;
}
