package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.CourseService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import dev.patika.schoolmanagementsystem.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<CourseDto>>> getAll(@RequestParam Optional<String> filter) {
        return ResponseEntities.okDataResult(courseService.findAll(filter.orElse(null)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<CourseDto>> getById(@PathVariable long id) {

        CourseDto courseDto = courseService.findById(id);

        return courseDto == null ?
                ResponseEntities.notFoundDataResult(Course.class.getSimpleName(), Pair.of("id", id)) :
                ResponseEntities.okDataResult(courseDto);
    }

    @PostMapping
    public ResponseEntity<DataResult<CourseDto>> create(@RequestBody CourseCreateDto createCreateDto) {

        CourseDto courseDto = courseService.create(createCreateDto);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(CourseController.class).getById(courseDto.getId())).buildAndExpand().toUri();

        return ResponseEntities.createdDataResult(courseDto, uri);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable long id, @RequestBody CourseUpdateDto courseUpdateDto) {

        courseUpdateDto.setId(id);
        courseService.update(courseUpdateDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteAllByName(@RequestParam String name) {

        courseService.deleteAllByName(name);
        return ResponseEntity.noContent().build();
    }

}
