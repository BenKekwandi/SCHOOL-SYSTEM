package com.enrollment_service.dto;

import java.util.Date;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.enrollment_service.model.Student;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnrollmentResponse{

    private Integer id;
    private Integer studentId;
    private Integer classId;
    private Date enrollmentDate;
    private Student student;
}
