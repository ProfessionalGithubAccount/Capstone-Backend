package com.capstone.coursemicroservice.service;

import com.capstone.coursemicroservice.entity.Course;
import com.capstone.coursemicroservice.repository.CourseRepository;
//import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class  CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Course> getAllCourses() {

        return courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId) {

        return courseRepository.findById(courseId).orElse(null);
    }

    public Course saveCourse(Course course) {

        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {

        return courseRepository.save(course);
    }
 // Assuming RestTemplate is configured as a bean



    public List getAllCoursesByStudentId(Integer studentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHVkZW50MTIzIiwiZXhwIjoxNzI1MDYwNDkyLCJpYXQiOjE3MjUwMjQ0OTJ9.IvM3LFIoDjlEKCgb-WEU0OO4G1xbu0k87oGQoeHpoGU");

        headers.set("Content-Type", "application/json");

        // Create an HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Define the URL
        String studentServiceUrl = "http://localhost:5003/user/students/" + studentId + "/courses";

        // Make the request
        ResponseEntity<List> response = restTemplate.exchange(
                studentServiceUrl,
                HttpMethod.GET,
                entity,
                List.class
        );
        // Step 2: Retrieve courses by course IDs
        return courseRepository.findAllById(response.getBody());
    }

    public List<Course> getAllCoursesByTeacherId(Integer teacherId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHVkZW50MTIzIiwiZXhwIjoxNzI1MDYwNDkyLCJpYXQiOjE3MjUwMjQ0OTJ9.IvM3LFIoDjlEKCgb-WEU0OO4G1xbu0k87oGQoeHpoGU");
        headers.set("Content-Type", "application/json");

        // Create an HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Step 1: Call the Teacher Microservice to get the list of course IDs for the teacher
        String teacherServiceUrl = "http://localhost:5003/teachers/" + teacherId + "/courses"; // Adjust the URL as necessary
        List<Integer> courseIds = restTemplate.getForObject(teacherServiceUrl, List.class);

        // Step 2: Retrieve courses by course IDs
        return courseRepository.findAllById(courseIds);
    }

//    public void deleteCourse(Integer courseId) {
//        courseRepository.deleteById(courseId);
//    }
//
//    public List<Course> getRecentCourses() {
//        // Dummy implementation, adjust based on real requirements
//        return courseRepository.findTop5ByOrderByLastAccessedDesc(); // Fetch all courses for now
//    }

//    public String getProfessorNameById(Integer professorId) {
//        String url = "http://professor-service/professors/" + professorId;
//        return restTemplate.getForObject(url, String.class);
//    }

//    public List<Integer> getAssignmentIdsByCourseId(Integer courseId) {
//        Optional<Course> course = courseRepository.findById(courseId);
//        if (course.isPresent()) {
//            return course.get().getAssignmentIds(); // Assuming getAssignmentIds returns a List<Integer>
//        } else {
//            throw new RuntimeException("My friend the problem is ..Course not found with id " + courseId);
//        }
//    }


//
//    public int getOverallScore(Integer courseId) {
//        int totalScore = 0;
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new RuntimeException(" My friend the problem is ..Course not found with id " + courseId));
//
//        // Assuming assignmentIds is stored as a comma-separated string
//        List<Integer> assignmentIds = course.getAssignmentIds();
//                /*
//                    if working with strings instead of List<Integer> for assignmentids use this
//                    List<Long> assignmentIds = Arrays.stream(assignmentIdsString.split(","))
//                                         .map(Long::parseLong)
//                                         .collect(Collectors.toList());
//                */
//        for (Integer assignmentId : assignmentIds) {
//            // Call the Assignment microservice to get the score for each assignment
//            String url = "http://localhost:5002/assignments/getScore/" + assignmentId;
//            Integer score = restTemplate.getForObject(url, Integer.class);
//            if (score != null) {
//                totalScore += score;
//            }
//        }
//        return totalScore;
//    }
}
