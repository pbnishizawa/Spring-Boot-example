package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name")
    private String name;
    
    @Column(name = "emp_age")
    private Integer age;
}
