package com.hust.wit120back.dto;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
public class DepartmentDTO {
    Integer departmentId;
    String departmentName;
    String departmentDesc;
}
