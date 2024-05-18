package com.example.warehouse.models;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Response {

    private ResponseType responseType;
    private  String message;
    public enum ResponseType{

        SUCCESS,FAILED,PARTIAL_SUCCESS

    }

}
