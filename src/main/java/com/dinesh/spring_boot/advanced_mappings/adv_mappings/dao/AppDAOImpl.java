package com.dinesh.spring_boot.advanced_mappings.adv_mappings.dao;

import org.springframework.stereotype.Repository;

import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.Instructor;
import com.dinesh.spring_boot.advanced_mappings.adv_mappings.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
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

}
