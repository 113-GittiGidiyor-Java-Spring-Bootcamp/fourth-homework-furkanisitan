package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
