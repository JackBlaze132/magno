package com.unibague.backend.model;

import com.unibague.backend.util.Sex;
import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sex")
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_program_id")
    private AcademicProgram academicProgram;
}
