package com.example.demo.student;

import com.example.demo.student.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


//all resources from API

@RestController
@RequestMapping(path="/api/v1/")
public class StudentController {


    @Autowired
    StudentService studentService;


    @GetMapping("/getStudentDetails")
    public Student getStudent(@RequestParam String name ) throws InterruptedException, ExecutionException {
        return studentService.getStudentDetails(name);
    }

    @PostMapping("/createStudent")
    public String createStudent(@RequestBody Student student ) throws InterruptedException, ExecutionException {
        return studentService.saveStudentDetails(student);
    }

    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student  ) throws InterruptedException, ExecutionException {
        return studentService.updateStudentDetails(student);
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam String name){
        return studentService.deleteStudent(name);
    }
}
