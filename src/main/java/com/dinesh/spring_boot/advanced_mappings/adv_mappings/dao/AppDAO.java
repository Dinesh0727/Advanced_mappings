package com.dinesh.spring_boot.advanced_mappings.adv_mappings.dao;

import java.util.List;

import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Course;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Instructor;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.InstructorDetail;

public interface AppDAO {

    public void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesWithInstructorId(int theId);

    Instructor findInstructorAndCoursesThroughJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);
}
