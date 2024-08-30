package com.capstone.assignmentmicroservice.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;

@Entity
@Data
public class Assignment {
    @Id
    private Integer assignmentId;
    private Integer professorId;
    private Integer courseId;
    @Lob
    @Column(name = "assignment_file")
    private byte[] assignmentFile;
    private String title;
    private Integer marks;

    private String fileName;
    private Timestamp deadline;
}
