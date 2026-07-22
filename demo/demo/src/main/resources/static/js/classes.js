let selectedClassId = null;


/* ===============================
   SAMPLE CLASS DATA
   (Frontend only - backend later)
================================ */

let classes = [

    {
        classId:1,
        className:"CS 1st Year",
        program:"General",
        year:1,
        stream:"Computer Science",
        semester:1,
        academicYear:"2026"
    },

    {
        classId:2,
        className:"CS 2nd Year",
        program:"General",
        year:2,
        stream:"Computer Science",
        semester:1,
        academicYear:"2026"
    },

    {
        classId:3,
        className:"CS 3rd Year General",
        program:"General",
        year:3,
        stream:"Computer Science",
        semester:1,
        academicYear:"2026"
    },

    {
        classId:4,
        className:"IT 1st Year",
        program:"General",
        year:1,
        stream:"Information Technology",
        semester:1,
        academicYear:"2026"
    }

];





/* ===============================
   PAGE LOAD
================================ */


document.addEventListener("DOMContentLoaded",function(){

    displayClasses(classes);

});






/* ===============================
   DISPLAY CLASSES
================================ */


function displayClasses(data){


    let table =
    document.getElementById("classTableBody");


    let count =
    document.getElementById("classCount");



    table.innerHTML="";


    count.innerText=data.length;



    if(data.length===0){


        table.innerHTML=`

        <tr>

        <td colspan="8"
        class="text-center">

        No Classes Found

        </td>

        </tr>

        `;


        return;

    }





    data.forEach((cls,index)=>{


        table.innerHTML += `


        <tr>


        <td>${index+1}</td>


        <td>${cls.className}</td>


        <td>${cls.program}</td>


        <td>${cls.year}</td>


        <td>

        <span class="badge bg-success">

        ${cls.stream}

        </span>

        </td>


        <td>${cls.semester}</td>


        <td>${cls.academicYear}</td>



        <td>


        <button class="btn btn-info btn-sm"
        onclick="viewClass(${cls.classId})">


        <i class="fas fa-eye"></i>

        View


        </button>



        <button class="btn btn-warning btn-sm"
        onclick="editClass(${cls.classId})">


        <i class="fas fa-pen"></i>

        Edit


        </button>




        <button class="btn btn-danger btn-sm"
        onclick="deleteClass(${cls.classId},
        '${cls.className}')">


        <i class="fas fa-trash"></i>

        Delete


        </button>



        </td>


        </tr>


        `;


    });


}








/* ===============================
   SEARCH
================================ */


document
.getElementById("searchBtn")
.addEventListener("click",function(){



let keyword =
document.getElementById("searchClass")
.value
.toLowerCase();



let year =
document.getElementById("yearFilter")
.value;



let stream =
document.getElementById("streamFilter")
.value;





let result = classes.filter(cls=>{


return (

(cls.className.toLowerCase()
.includes(keyword)
||
cls.program.toLowerCase()
.includes(keyword))


&&

(year==="" ||
cls.year==year)


&&

(stream==="" ||
cls.stream==stream)


);


});



displayClasses(result);



});










/* ===============================
   RESET
================================ */


document
.getElementById("resetBtn")
.addEventListener("click",function(){



document.getElementById("searchClass").value="";

document.getElementById("yearFilter").value="";

document.getElementById("streamFilter").value="";



displayClasses(classes);



});









/* ===============================
   ADD CLASS
================================ */


document
.getElementById("addClassForm")
.addEventListener("submit",function(e){


e.preventDefault();



let newClass={


classId:
Date.now(),


className:
document.getElementById("className").value,


program:
document.getElementById("program").value,


year:
document.getElementById("year").value,


stream:
document.getElementById("stream").value,


semester:
document.getElementById("semester").value,


academicYear:
document.getElementById("academicYear").value


};




classes.push(newClass);



displayClasses(classes);



alert("Class Added Successfully");



document
.getElementById("addClassForm")
.reset();




bootstrap.Modal
.getInstance(
document.getElementById("addClassModal")
)
.hide();



});









/* ===============================
   VIEW CLASS
================================ */


function viewClass(id){


let cls =
classes.find(c=>c.classId===id);



document.getElementById("viewClassName")
.innerText=cls.className;


document.getElementById("viewProgram")
.innerText=cls.program;


document.getElementById("viewYear")
.innerText=cls.year;


document.getElementById("viewStream")
.innerText=cls.stream;


document.getElementById("viewSemester")
.innerText=cls.semester;


document.getElementById("viewAcademicYear")
.innerText=cls.academicYear;



new bootstrap.Modal(

document.getElementById("viewClassModal")

).show();



}









/* ===============================
   EDIT CLASS
================================ */


function editClass(id){


let cls =
classes.find(c=>c.classId===id);



alert(
"Edit function ready. Backend connection will be added later for Class ID: "
+id
);



}










/* ===============================
   DELETE CLASS
================================ */


function deleteClass(id,name){


selectedClassId=id;



document
.getElementById("deleteClassName")
.innerText=name;



new bootstrap.Modal(

document.getElementById("deleteClassModal")

).show();



}






document
.getElementById("confirmDeleteBtn")
.addEventListener("click",function(){



classes =
classes.filter(
c=>c.classId!==selectedClassId
);



displayClasses(classes);



alert("Class Deleted Successfully");



bootstrap.Modal
.getInstance(
document.getElementById("deleteClassModal")
)
.hide();



});