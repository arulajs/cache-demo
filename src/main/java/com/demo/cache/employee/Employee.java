package com.demo.cache.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Employee {

    @Id
    private Long id;
    private String name;
    private String designation;
}
