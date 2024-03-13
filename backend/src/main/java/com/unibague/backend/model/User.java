package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.AcademicProgram;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "academic_program")
    private AcademicProgram academicProgram;

    @Column(name = "sex")
    private Sex sex;

    @Column(name = "is_external_user")
    private Boolean isExternalUser;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_research_seedbeds",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds;

    public User(String firstName, String middleName, String lastName, String secondLastName, String userCode, String identificationNumber, String email, String phoneNumber, AcademicProgram academicProgram){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.userCode = userCode;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.academicProgram = academicProgram;
    }

    public User(){

    }
}
