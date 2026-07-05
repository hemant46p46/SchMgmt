package com.schmgmt.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.schmgmt.models.Teacher;
import com.schmgmt.services.TeacherService;

@RestController
@RequestMapping("/schmgmt/api/teachers")
public class TeacherController {

	private final TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	// Native way
	@PostMapping("/saveTeacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return this.teacherService.saveTeacher(teacher);
	}
	
	@GetMapping("/getAllTeacher")
	public List<Teacher> getAllTeachers(){
		return this.teacherService.getAllTeacher();
	}
	
	@GetMapping("/getTeacherById/{id}")
	public List<Teacher> getTeacherById(@PathVariable Long id){
		return this.teacherService.getTeacherById(id);
	}
	
	@PutMapping("/updateTeacherById/{id}")
	public Teacher updateTeacherById(@RequestBody Teacher updatedTeacher, @PathVariable Long id) {
		return this.teacherService.updateTeacherById(updatedTeacher, id);
	}
	
	@DeleteMapping("/deleteTeacherById/{id}")
	public void deleteTeacherById(@PathVariable Long id) {
		this.teacherService.deleteTeacherById(id);
	}
	
	// Using ResponseEntity
	
	@PostMapping("/saveTeacherRE")
	public ResponseEntity<Teacher> saveTeacherRE(@RequestBody Teacher teacher){
		return ResponseEntity.ok(this.teacherService.saveTeacherEM(teacher));
	}
	
	@GetMapping("/getAllTeacherRes")
	public ResponseEntity<List<Teacher>> getAllTeacherResp(){
		return ResponseEntity.ok(this.teacherService.getAllTeacher());
	}
	
	@GetMapping("/getTeacherByIdRE/{id}")
	public ResponseEntity<List<Teacher>> getTeacherByIdRE(@PathVariable Long id){
		return ResponseEntity.ok(this.teacherService.getTeacherByIdEM(id));
	}
	
	@PutMapping("/updateTeacher/{id}")
	public ResponseEntity<Teacher> updateTeacherEM(@RequestBody Teacher updateTeacher, @PathVariable Long id){
		return ResponseEntity.ok(this.teacherService.updateTeacherByIdEM(updateTeacher, id));
	}
	
	@DeleteMapping("/deleteTeacherEM")
	public ResponseEntity<Void> deleteTeacherEM(@PathVariable Long id) {
		this.teacherService.deleteTeacherByIdEM(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// Using NamedQuery
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher saveTeacherNQ(@RequestBody Teacher teacher) {
        return this.teacherService.saveTeacherNQ(teacher);
    }

    @GetMapping("/getTeachersNQ")
    public List<Teacher> getAllTeachersNQ() {
        return this.teacherService.getAllTeacherNQ();
    }

    @GetMapping("/getTeacherByIdNQ/{id}")
    public List<Teacher> getTeacherByIdNQ(@PathVariable Long id) {
        return this.teacherService.getTeacherByIdNQ(id);
    }

    @PutMapping("/updateTeacherByIdNQ/{id}")
    public Teacher updateTeacherByIdNQ(@PathVariable Long id, @RequestBody Teacher teacher) {
        return this.teacherService.updateTeacherByIdNQ(teacher, id);
    }

    @DeleteMapping("/deleteTeacherNQ/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacherNQ(@PathVariable Long id) {
        this.teacherService.deleteTeacherByIdNQ(id);
    }
	
    
}
