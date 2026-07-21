package com.attendance.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.dto.StudentRequest;
import com.attendance.demo.dto.StudentResponse;
import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.entity.Role;
import com.attendance.demo.entity.Student;
import com.attendance.demo.repository.ClassRepository;
import com.attendance.demo.repository.StudentRepository;
import com.attendance.demo.repository.UserRepository;



@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ClassRepository classRepository;




    // ============================
    // GET ALL STUDENTS
    // ============================

    public List<StudentResponse> getAllStudents() {


        List<Student> students =
                studentRepository.findAll();


        return convertToResponse(students);

    }






    // ============================
    // SEARCH STUDENTS
    // ============================

    public List<StudentResponse> searchStudents(String keyword) {


        List<Student> students =
                studentRepository
                .findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                );


        return convertToResponse(students);

    }






    // ============================
    // SEARCH BY CLASS
    // ============================

    public List<StudentResponse> searchByClass(String className) {


        List<Student> students =
                studentRepository.findByStudentClass_ClassNameContainingIgnoreCase(
                        className
                );


        return convertToResponse(students);

    }







    // ============================
    // SEARCH BY STREAM
    // ============================

    public List<StudentResponse> searchByStream(String stream) {


        List<Student> students =
                studentRepository.findByStudentClass_StreamContainingIgnoreCase(
                        stream
                );


        return convertToResponse(students);

    }








    // ============================
    // ADD STUDENT
    // ============================

    public Student addStudent(StudentRequest request) {



        if(userRepository.existsByUsername(request.getUsername())) {


            throw new RuntimeException(
                    "Username already exists"
            );

        }




        if(studentRepository.existsByRegistrationNo(
                request.getRegistrationNo())) {


            throw new RuntimeException(
                    "Registration number already exists"
            );

        }





        Student student = new Student();





        // USER TABLE

        student.setUsername(
                request.getUsername()
        );


        student.setPassword(
                request.getPassword()
        );


        student.setRole(
                Role.Student
        );






        // STUDENT TABLE

        student.setRegistrationNo(
                request.getRegistrationNo()
        );


        student.setStudentName(
                request.getStudentName()
        );


        student.setEmail(
                request.getEmail()
        );







        // CLASS

        if(request.getClassId()!=null){


            ClassEntity classEntity =
                    classRepository.findById(
                            request.getClassId()
                    )
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Class not found"
                            )
                    );



            student.setStudentClass(
                    classEntity
            );


        }






        return studentRepository.save(student);


    }








    // ============================
    // GET STUDENT BY ID
    // ============================

    public Student getStudentById(Integer id){


        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        )
                );


    }








    // ============================
    // UPDATE STUDENT
    // ============================

    public Student updateStudent(
            Integer id,
            StudentRequest request){



        Student student =
                getStudentById(id);






        // USER TABLE UPDATE

        student.setUsername(
                request.getUsername()
        );


        if(request.getPassword()!=null &&
                !request.getPassword().isEmpty()){


            student.setPassword(
                    request.getPassword()
            );

        }






        // STUDENT TABLE UPDATE

        student.setRegistrationNo(
                request.getRegistrationNo()
        );


        student.setStudentName(
                request.getStudentName()
        );


        student.setEmail(
                request.getEmail()
        );







        // CLASS UPDATE

        if(request.getClassId()!=null){



            ClassEntity classEntity =
                    classRepository.findById(
                            request.getClassId()
                    )
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Class not found"
                            )
                    );



            student.setStudentClass(
                    classEntity
            );


        }






        return studentRepository.save(student);


    }








    // ============================
    // DELETE STUDENT
    // ============================

    public void deleteStudent(Integer id){



        Student student =
                getStudentById(id);



        studentRepository.delete(student);



    }









    // ============================
    // CONVERT ENTITY TO RESPONSE
    // ============================

    private List<StudentResponse> convertToResponse(
            List<Student> students){



        List<StudentResponse> responseList =
                new ArrayList<>();





        for(Student student : students){



            StudentResponse response =
                    new StudentResponse();




            response.setStudentId(
                    student.getUserId()
            );



            response.setUsername(
                    student.getUsername()
            );



            response.setRegistrationNo(
                    student.getRegistrationNo()
            );



            response.setStudentName(
                    student.getStudentName()
            );



            response.setEmail(
                    student.getEmail()
            );





            if(student.getStudentClass()!=null){


                response.setClassName(
                        student.getStudentClass()
                        .getClassName()
                );


            }
            else{


                response.setClassName(
                        "Not Assigned"
                );


            }





            responseList.add(response);



        }






        return responseList;


    }



}