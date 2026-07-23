let selectedStudentId = null;


document.addEventListener("DOMContentLoaded", function () {

    loadStudents();

});





/* ==============================
   LOAD ALL STUDENTS
============================== */

function loadStudents(){


    fetch("/api/students")

    .then(response => response.json())

    .then(data => {

        displayStudents(data);

    })

    .catch(error => {

        console.log(error);

    });


}






/* ==============================
   DISPLAY STUDENTS
============================== */

function displayStudents(students){


    const tableBody =
    document.getElementById("studentTableBody");


    const count =
    document.getElementById("studentCount");



    tableBody.innerHTML="";


    count.innerText = students.length;





    if(students.length === 0){


        tableBody.innerHTML = `

        <tr>

            <td colspan="7" class="text-center">

                No Students Found

            </td>

        </tr>

        `;


        return;


    }






    students.forEach((student,index)=>{



        tableBody.innerHTML += `


        <tr>


            <td>${index+1}</td>


            <td>${student.username}</td>


            <td>${student.registrationNo}</td>


            <td>${student.studentName}</td>


            <td>${student.email}</td>


            <td>

                <span class="badge bg-success">

                    ${student.className}

                </span>

            </td>



            <td>


                <button class="btn btn-warning btn-sm"

                onclick="openEditModal(${student.studentId})">


                    <i class="fas fa-pen"></i>

                    Edit


                </button>





                <button class="btn btn-danger btn-sm"

                onclick="openDeleteModal(
                ${student.studentId},
                '${student.studentName}'
                )">


                    <i class="fas fa-trash"></i>

                    Delete


                </button>


            </td>


        </tr>


        `;



    });



}








/* ==============================
   SEARCH
============================== */


document
.getElementById("searchBtn")
.addEventListener("click",function(){



let keyword =
document.getElementById("searchStudent").value.trim();


let className =
document.getElementById("classFilter").value;


let stream =
document.getElementById("streamFilter").value;





if(keyword !== ""){


fetch("/api/students/search?keyword="
+encodeURIComponent(keyword))


.then(res=>res.json())

.then(data=>{

displayStudents(data);

});


return;

}





if(className !== ""){


fetch("/api/students/search/class?className="
+encodeURIComponent(className))


.then(res=>res.json())

.then(data=>{

displayStudents(data);

});


return;

}





if(stream !== ""){


fetch("/api/students/search/stream?stream="
+encodeURIComponent(stream))


.then(res=>res.json())

.then(data=>{

displayStudents(data);

});


return;


}





loadStudents();



});









/* ==============================
 RESET
============================== */


document
.getElementById("resetBtn")
.addEventListener("click",function(){



document.getElementById("searchStudent").value="";


document.getElementById("classFilter").value="";


document.getElementById("streamFilter").value="";



loadStudents();



});









/* ==============================
 ADD STUDENT
============================== */


document
.getElementById("addStudentForm")
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






fetch("/api/students",{


method:"POST",


headers:{


"Content-Type":"application/json"


},


body:JSON.stringify(student)


})



.then(res=>{


if(!res.ok){

throw new Error();

}


return res.json();


})



.then(data=>{


alert("Student Added Successfully");



document
.getElementById("addStudentForm")
.reset();




bootstrap.Modal
.getInstance(
document.getElementById("addStudentModal")
)
.hide();




loadStudents();



})



.catch(()=>{


alert("Error Adding Student");


});



});









/* ==============================
 OPEN EDIT MODAL
============================== */


function openEditModal(id){


selectedStudentId=id;




fetch("/api/students")

.then(res=>res.json())

.then(students=>{


let student =
students.find(
s=>s.studentId===id
);



document.getElementById("editStudentId").value =
student.studentId;


document.getElementById("editUsername").value =
student.username;


document.getElementById("editRegistrationNo").value =
student.registrationNo;


document.getElementById("editStudentName").value =
student.studentName;


document.getElementById("editEmail").value =
student.email;





new bootstrap.Modal(

document.getElementById("editStudentModal")

).show();



});



}









/* ==============================
 UPDATE STUDENT
============================== */


document
.getElementById("editStudentForm")
.addEventListener("submit",function(e){


e.preventDefault();




let student = {


username:
document.getElementById("editUsername").value,


password:"123456",


registrationNo:
document.getElementById("editRegistrationNo").value,


studentName:
document.getElementById("editStudentName").value,


email:
document.getElementById("editEmail").value


};





fetch("/api/students/"+selectedStudentId,{


method:"PUT",


headers:{


"Content-Type":"application/json"


},


body:JSON.stringify(student)


})



.then(res=>res.json())

.then(data=>{


alert("Student Updated Successfully");



bootstrap.Modal
.getInstance(
document.getElementById("editStudentModal")
)
.hide();




loadStudents();



});



});









/* ==============================
 OPEN DELETE MODAL
============================== */


function openDeleteModal(id,name){


selectedStudentId=id;



document
.getElementById("deleteStudentName")
.innerText=name;




new bootstrap.Modal(

document.getElementById("deleteStudentModal")

).show();



}









/* ==============================
 DELETE STUDENT
============================== */


document
.getElementById("confirmDeleteBtn")
.addEventListener("click",function(){



fetch("/api/students/"+selectedStudentId,{


method:"DELETE"


})



.then(res=>res.text())


.then(data=>{


alert("Student Deleted Successfully");




bootstrap.Modal
.getInstance(
document.getElementById("deleteStudentModal")
)
.hide();




loadStudents();



});



});