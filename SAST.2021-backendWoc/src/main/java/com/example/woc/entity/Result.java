package com.example.woc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 返回格式
 * 原来返回类是以json格式返回的
 */
public class Result {
    private Boolean success = true;
    private String errMsg = null;
    private String errCode = null;
    private String data = null;
}
