package com.dinesh.spring_boot.advanced_mappings.adv_mappings.dao;

import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Instructor;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.InstructorDetail;

public interface AppDAO {

    public void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
