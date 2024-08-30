package com.capstone.coursemicroservice.controller;


import com.capstone.coursemicroservice.entity.Course;
import com.capstone.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/updateCourse")
    public Course updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

//    @DeleteMapping("/{courseId}")
//    public void deleteCourse(@PathVariable Integer courseId) {
//        courseService.deleteCourse(courseId);
//    }



//    @GetMapping("/{courseId}/professor")
//    public String getProfessorName(@PathVariable Integer courseId) {
//        Course course = courseService.getCourseById(courseId);
//        return courseService.getProfessorNameById(course.getProfessorId());
//    }

//    public List<Course> getAssignmentsByCourseId(@PathVariable Integer courseId) {
//        return courseService.getAssignmentsByCourseId();
//    }
//    @GetMapping("/getOverallScore")
//    public int getOverallScore(@RequestParam Integer courseId, @RequestParam List<Long> assignmentIds) {
//        return courseService.getOverallScore(courseId);
//    }


    @GetMapping("/courses/student/{studentId}")
    public List getAllCoursesByStudentId(@PathVariable Integer studentId) {
        return courseService.getAllCoursesByStudentId(studentId);
    }

    @GetMapping("/courses/teacher/{teacherId}")
    public List<Course> getAllCoursesByTeacherId(@PathVariable Integer teacherId) {
        return courseService.getAllCoursesByTeacherId(teacherId);
    }
}
