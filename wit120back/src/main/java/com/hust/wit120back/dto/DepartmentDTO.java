package com.hust.wit120back.dto;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private String departmentDesc;
}
