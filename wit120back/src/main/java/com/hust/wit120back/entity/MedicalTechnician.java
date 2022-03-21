package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class MedicalTechnician {
    Integer technicianId;
    String technicianName;
    Integer docId;
    int cost;
}
