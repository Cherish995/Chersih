package com.cherish.mm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable{
    private Long total;//总记录数
    private List rows;//当前页结果
}
