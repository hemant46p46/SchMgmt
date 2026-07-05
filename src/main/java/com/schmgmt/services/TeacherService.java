package com.schmgmt.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schmgmt.models.Teacher;
import com.schmgmt.repositories.TeacherRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class TeacherService {
	
	private final TeacherRepository teacherRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepo = teacherRepository;
	}
	
	public Teacher saveTeacher(Teacher teacher) {
		return this.teacherRepo.save(teacher);
	}
	
	public List<Teacher> getAllTeacher(){
		return this.teacherRepo.findAll();
	}
	
	public List<Teacher> getTeacherById(Long id){
		return this.teacherRepo.findById(id).map(List::of).orElse(Collections.emptyList());
	}
	
	public Teacher updateTeacherById(Teacher updatedTeacher, Long id) {
		Teacher existingTeacher = this.teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
		existingTeacher.setFirstName(updatedTeacher.getFirstName());
		existingTeacher.setLastName(updatedTeacher.getLastName());
		existingTeacher.setEmail(updatedTeacher.getEmail());
		existingTeacher.setDepartment(updatedTeacher.getDepartment());
		return this.teacherRepo.save(existingTeacher);
	}
	
	public void deleteTeacherById(Long id) {
		this.teacherRepo.deleteById(id);
	}
	
	@Transactional
	public Teacher saveTeacherEM(Teacher teacher) {
		if(teacher.getTeacherId() == null) {
			this.entityManager.persist(teacher);
			return teacher;
		}else {
			return this.entityManager.merge(teacher);
		}
	}
	
	public List<Teacher> getAllTeacherEM(){
		String jpql = "SELECT t from teachers t";
		TypedQuery<Teacher> query = this.entityManager.createQuery(jpql, Teacher.class);
		return query.getResultList();
	}
	
	public List<Teacher> getTeacherByIdEM(Long id){
		Teacher teacher = this.entityManager.find(Teacher.class, id);
		return Optional.ofNullable(teacher).map(List::of).orElse(Collections.emptyList());
	}
	
	@Transactional
	public Teacher updateTeacherByIdEM(Teacher updateTeacher, Long id) {
		Teacher existingTeacher = this.entityManager.find(Teacher.class, id);
		if(existingTeacher == null) {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
		existingTeacher.setFirstName(updateTeacher.getFirstName());
		existingTeacher.setLastName(updateTeacher.getLastName());
		existingTeacher.setEmail(updateTeacher.getEmail());
		existingTeacher.setDepartment(updateTeacher.getDepartment());
	
		return this.entityManager.merge(existingTeacher);
	}
	
	@Transactional
	public void deleteTeacherByIdEM(Long id) {
		Teacher teacher = this.entityManager.find(Teacher.class, id);
		if(teacher == null) {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
		this.entityManager.remove(teacher);
	}
	
	// Using NamedQuery
	
	@Transactional
	public Teacher saveTeacherNQ(Teacher teacher) {
		if(teacher.getTeacherId() == null) {
			this.entityManager.persist(teacher);
			return teacher;
		}else {
			return this.entityManager.merge(teacher);
		}
	}
	
	public List<Teacher> getAllTeacherNQ(){
		return this.entityManager.createNamedQuery("Teacher.findAllNQ", Teacher.class).getResultList();
	}
	
	public List<Teacher> getTeacherByIdNQ(Long id){
		return this.entityManager.createNamedQuery("Teacher.findById", Teacher.class).setParameter("id", id).getResultList();
	}
	
	@Transactional
	public Teacher updateTeacherByIdNQ(Teacher updatedTeacher, Long id) {
		int updatedRows = this.entityManager.createNamedQuery("Teacher.updateFields", Teacher.class)
				.setParameter("firstName", updatedTeacher.getFirstName())
				.setParameter("lastName", updatedTeacher.getLastName())
				.setParameter("email", updatedTeacher.getEmail())
				.setParameter("department", updatedTeacher.getDepartment())
				.setParameter("id", id)
				.executeUpdate();
		
		if(updatedRows == 0) {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
		updatedTeacher.setTeacherId(id);
		return updatedTeacher;
	}
	
	public void deleteTeacherByIdNQ(Long id) {
		int deletedRow = this.entityManager.createNamedQuery("Teacher.deleteById", Teacher.class)
				.setParameter("id", id).executeUpdate();
		
		if(deletedRow == 0) {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
	}
}
