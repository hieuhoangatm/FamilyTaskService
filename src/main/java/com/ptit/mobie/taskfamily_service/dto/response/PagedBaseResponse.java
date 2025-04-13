package com.ptit.mobie.taskfamily_service.dto.response;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedBaseResponse<T>  implements Serializable {
    private int statusCode;
    private String message;
    private T data;
    public int pageNumber;
    public int pageSize;
    public long totalElements;
    public int totalPages;
}