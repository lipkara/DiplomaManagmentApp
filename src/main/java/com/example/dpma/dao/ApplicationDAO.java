package com.example.dpma.dao;

import com.example.dpma.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicationDAO extends JpaRepository<Application,Integer> {

    public Optional<Application> findApplicationByStudentIdAndSubjectId(Integer student_id, Integer subject_id);



    public void deleteApplicationsByStudentId(Integer student_id);

    @Modifying
    @Query("DELETE FROM Application a WHERE a.student.student_id = :studentId")
    public void deleteByStudentId(@Param("studentId") Integer studentId);

}