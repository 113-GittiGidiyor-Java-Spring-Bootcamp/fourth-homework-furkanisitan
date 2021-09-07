package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import dev.patika.schoolmanagementsystem.dataaccess.dtos.StudentGroupByGenderResponse;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<StudentDto>>> getAll(@RequestParam Optional<String> filter) {
        return ResponseEntities.okDataResult(studentService.findAll(filter.orElse(null)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<StudentDto>> getById(@PathVariable long id) {

        StudentDto studentDto = studentService.findById(id);
        return studentDto == null ?
                ResponseEntities.notFoundDataResult(Student.class.getSimpleName(), Pair.of("id", id)) :
                ResponseEntities.okDataResult(studentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gender-counts")
    public ResponseEntity<DataResult<List<StudentGroupByGenderResponse>>> getGenderCounts(@RequestParam Optional<String> filter) {
        return ResponseEntities.okDataResult(studentService.countAndGroupByGender());
    }
}
