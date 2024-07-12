package com.dinesh.spring_boot.advanced_mappings.adv_mappings.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Course;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Instructor;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);        
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor theInstructor = entityManager.find(Instructor.class, theId);
        List<Course> courses = findCoursesWithInstructorId(theId);

        for(Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesWithInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:data", Course.class);

        query.setParameter("data", theId);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorAndCoursesThroughJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
            "select i from Instructor i " + "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail " + "where i.id = :data "    
        , Instructor.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = findCourseById(theId);
        course.setInstructor(null);
        System.out.println("Aftr select");
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + " JOIN FETCH c.reviews " + "where c.id=:data", Course.class);

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    
    
}