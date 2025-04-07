package com.ptit.mobie.taskfamily_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED error"),
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    IllegalArgumentException(1003,"Search name must not be null or empty"),
    CUSTOMER_NOT_FOUND(1004, "Customer not found"),
    PRODUCT_NOT_FOUND(1005, "Product not found"),
    CATEGORY_NAME_EXISTED(1006, "Category name existed"),
    CATEGORY_NOT_FOUND(1007, "Category not found"),
    FALE_TO_UPLOAD_FILE(1008, "Failed to upload file"),
    DOCUMENT_NOT_FOUND(1009, "Not found document with id ")
    ;
    private int code;
    private String message;
}
