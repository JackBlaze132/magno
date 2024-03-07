package com.unibague.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_table")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private String academicProgram;
}
