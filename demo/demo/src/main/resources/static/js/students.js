/* ==========================================
   Manage Students JavaScript
   Attendance Management System
   Admin Module
========================================== */


let selectedStudentId = null;


/* ==============================
   Page Load
============================== */

document.addEventListener("DOMContentLoaded", function () {

    loadStudents();

});



/* ==============================
   Load Students
   (Backend API will connect in Part 5)
============================== */


function loadStudents() {


    fetch("/api/students")

        .then(response => {


            if(!response.ok){

                throw new Error("Failed to load students");

            }


            return response.json();


        })


        .then(data => {


            displayStudents(data);


        })


        .catch(error => {


            console.log(error);


            document.getElementById("studentTableBody").innerHTML = `

                <tr>

                    <td colspan="7" class="text-center text-danger">

                        Unable to load student data

                    </td>

                </tr>

            `;


        });


}






/* ==============================
   Display Students In Table
============================== */


function displayStudents(students){


    let tableBody =
        document.getElementById("studentTableBody");


    let count =
        document.getElementById("studentCount");



    tableBody.innerHTML = "";


    count.innerText = students.length;




    if(students.length === 0){


        tableBody.innerHTML = `

            <tr>

                <td colspan="7" class="text-center">

                    No students found

                </td>

            </tr>

        `;


        return;

    }






    students.forEach((student,index)=>{


        tableBody.innerHTML += `


        <tr>


            <td>${index + 1}</td>


            <td>${student.username}</td>


            <td>${student.registrationNo}</td>


            <td>${student.studentName}</td>


            <td>${student.email}</td>



            <td>


                <span class="badge bg-success">

                    ${student.className || "Not Assigned"}

                </span>


            </td>



            <td>


                <button class="btn btn-warning btn-sm"
                onclick="openEditModal(${student.studentId})">


                    <i class="fas fa-pen"></i>

                    Edit


                </button>




                <button class="btn btn-danger btn-sm"
                onclick="openDeleteModal(${student.studentId}, '${student.studentName}')">


                    <i class="fas fa-trash"></i>

                    Delete


                </button>


            </td>


        </tr>


        `;


    });



}








/* ==============================
   Search Students
============================== */


document.getElementById("searchBtn")
.addEventListener("click",function(){


    let keyword =
    document.getElementById("searchStudent").value;



    fetch("/api/students/search?keyword=" + keyword)


    .then(response => response.json())


    .then(data => {


        displayStudents(data);


    });



});








/* ==============================
   Reset Search
============================== */


document.getElementById("resetBtn")
.addEventListener("click",function(){


    document.getElementById("searchStudent").value="";


    loadStudents();


});









/* ==============================
   Add Student
============================== */


document.getElementById("addStudentForm")
.addEventListener("submit",function(e){


    e.preventDefault();



    let student = {


        username:
        document.getElementById("username").value,


        password:
        document.getElementById("password").value,


        registrationNo:
        document.getElementById("registrationNo").value,


        studentName:
        document.getElementById("studentName").value,


        email:
        document.getElementById("email").value,


        classId:
        document.getElementById("classId").value


    };



    console.log(student);



    /*
      Backend connection later:

      POST /api/students

    */


    alert("Add Student is ready");


});








/* ==============================
   Open Edit Modal
============================== */


function openEditModal(id){


    selectedStudentId = id;



    document.getElementById("editStudentId").value = id;



    let modal = new bootstrap.Modal(
        document.getElementById("editStudentModal")
    );


    modal.show();


}









/* ==============================
   Update Student
============================== */


document.getElementById("editStudentForm")
.addEventListener("submit",function(e){


    e.preventDefault();



    let student = {


        username:
        document.getElementById("editUsername").value,


        registrationNo:
        document.getElementById("editRegistrationNo").value,


        studentName:
        document.getElementById("editStudentName").value,


        email:
        document.getElementById("editEmail").value


    };



    console.log(
        "Update ID:",
        selectedStudentId,
        student
    );



    /*
       Backend later:

       PUT /api/students/{id}

    */



    alert("Update Student is ready");


});








/* ==============================
   Open Delete Modal
============================== */


function openDeleteModal(id,name){


    selectedStudentId=id;



    document.getElementById("deleteStudentName")
    .innerText=name;



    let modal = new bootstrap.Modal(
        document.getElementById("deleteStudentModal")
    );


    modal.show();


}









/* ==============================
   Confirm Delete
============================== */


document.getElementById("confirmDeleteBtn")
.addEventListener("click",function(){


    console.log(
        "Delete Student ID:",
        selectedStudentId
    );



    /*
       Backend later:

       DELETE /api/students/{id}

    */



    alert("Delete Student is ready");


});