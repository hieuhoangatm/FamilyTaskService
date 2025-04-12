package com.ptit.mobie.taskfamily_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T>{
    private int statusCode;
    private String message;
    private T data;
}
