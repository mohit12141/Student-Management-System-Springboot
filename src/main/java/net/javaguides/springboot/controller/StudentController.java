package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
// @RequestMapping("students") // class level base url
public class StudentController {

    // usage : http://localhost:8080/student
    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Mohit",
                "Sahu"
        );
        return student;
    }

    // usage : http://localhost:8080/studentResponseEntity
    // showing how to use ResponseEntity class
    @GetMapping("/studentResponseEntity")
    public ResponseEntity<Student> getStudentResEnt(){
        Student student = new Student(
                1,
                "Mohit",
                "Sahu"
        );
    //   return new ResponseEntity<>(student, HttpStatus.OK);
       return ResponseEntity.ok(student);
    }

    //usage : http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Mohit","Sahu"));
        students.add(new Student(2,"Vajra", "Trivedy"));
        students.add(new Student(3,"Grishma","Kalyan"));
        students.add(new Student(4,"Apat","Kalin"));
        return students;
    }

    // Spring Boot REST API with Path variable
    // {id} - URI template variable
    // usage : http://localhost:8080/students/1
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(id, firstName, lastName );
    }

    // Spring Boot REST API with Request Parameter
    // usage : http://localhost:8080/students/query?id=1&firstName=Mohit&lastName=Sahu
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName,lastName);
    }

    // spring boot REST API that handles Post request
    // usage http://localhost:8080/students/create
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // usage http://localhost:8080/students/2/update
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully";
    }
}
