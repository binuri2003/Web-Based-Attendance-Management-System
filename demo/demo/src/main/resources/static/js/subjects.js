// ===============================
// Manage Subjects JS
// ===============================


document.addEventListener("DOMContentLoaded", function () {


    loadSubjects();

    loadLecturers();


    document
        .getElementById("addSubjectForm")
        .addEventListener("submit", addSubject);


    document
        .getElementById("editSubjectForm")
        .addEventListener("submit", updateSubject);


    document
        .getElementById("searchBtn")
        .addEventListener("click", searchSubjects);


    document
        .getElementById("resetBtn")
        .addEventListener("click", function(){

            document.getElementById("searchSubject").value="";

            loadSubjects();

        });


});




// ===============================
// Load Subjects
// ===============================

function loadSubjects(){


    fetch("/api/subjects")

    .then(response => response.json())

    .then(data => {


        let table = document.getElementById("subjectTableBody");

        table.innerHTML="";


        document.getElementById("subjectCount").innerText=data.length;



        data.forEach((subject,index)=>{


            let lecturerName =
                subject.lecturer ?
                subject.lecturer.lecturerName :
                "Not Assigned";



            table.innerHTML += `


            <tr>

                <td>${index+1}</td>

                <td>${subject.subjectCode}</td>

                <td>${subject.subjectName}</td>

                <td>${subject.credits}</td>

                <td>${lecturerName}</td>


                <td>


                <button class="btn btn-sm btn-warning"
                onclick="openEditModal(${subject.subjectId})">

                <i class="fas fa-edit"></i>

                </button>



                <button class="btn btn-sm btn-danger"
                onclick="openDeleteModal(${subject.subjectId}, '${subject.subjectName}')">

                <i class="fas fa-trash"></i>

                </button>


                </td>


            </tr>


            `;


        });


    });


}







// ===============================
// Lecturer Dropdown
// ===============================

function loadLecturers(){


    let lecturers = `

        <option value="">
            Select Lecturer
        </option>

        <option value="3">
            3 - Dr. Nuwan Fernando
        </option>

        <option value="4">
            4 - Ms. Dilani Jayasinghe
        </option>

        <option value="7">
            7 - Mr. M.D.R. Perera
        </option>

        <option value="8">
            8 - Mrs. W.M.K.S. Illmini
        </option>

        <option value="9">
            9 - Ms. N.H. Wanigasingha
        </option>

        <option value="10">
            10 - Mrs. Surani Perera
        </option>

        <option value="11">
            11 - Mr. Tisura Ambuldeniya
        </option>

        <option value="12">
            12 - Miss Lavanka Harshani
        </option>

        <option value="13">
            13 - Dr. K.A. Silva
        </option>

        <option value="14">
            14 - Mr. D.M. Fernando
        </option>

        <option value="15">
            15 - Mrs. P.N. Jayasinghe
        </option>

        <option value="16">
            16 - Ms. H.M. Wickramasinghe
        </option>

    `;


    document.getElementById("lecturerId").innerHTML = lecturers;


    document.getElementById("editLecturerId").innerHTML = lecturers;


}







// ===============================
// Add Subject
// ===============================

function addSubject(e){

    e.preventDefault();


    let subject={


        subjectCode:
        document.getElementById("subjectCode").value,


        subjectName:
        document.getElementById("subjectName").value,


        credits:
        document.getElementById("credits").value,


        lecturerId:
        document.getElementById("lecturerId").value


    };



    fetch("/api/subjects",{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(subject)


    })


    .then(response=>response.json())


    .then(data=>{


        alert("Subject Added Successfully");


        document.getElementById("addSubjectForm").reset();



        bootstrap.Modal
        .getInstance(
            document.getElementById("addSubjectModal")
        )
        .hide();


        loadSubjects();


    });


}







// ===============================
// Search Subject
// ===============================

function searchSubjects(){


    let keyword =
    document.getElementById("searchSubject")
    .value
    .toLowerCase();



    fetch("/api/subjects")


    .then(response=>response.json())


    .then(data=>{


        let filtered=data.filter(subject=>


            subject.subjectName.toLowerCase()
            .includes(keyword)

            ||

            subject.subjectCode.toLowerCase()
            .includes(keyword)


        );


        displaySubjects(filtered);


    });


}






// ===============================
// Display Subjects
// ===============================

function displaySubjects(data){


    let table =
    document.getElementById("subjectTableBody");


    table.innerHTML="";


    document.getElementById("subjectCount")
    .innerText=data.length;



    data.forEach((subject,index)=>{


        table.innerHTML += `


        <tr>

        <td>${index+1}</td>

        <td>${subject.subjectCode}</td>

        <td>${subject.subjectName}</td>

        <td>${subject.credits}</td>


        <td>

        ${subject.lecturer ?
        subject.lecturer.lecturerName :
        "Not Assigned"}

        </td>


        <td>


        <button class="btn btn-warning btn-sm"
        onclick="openEditModal(${subject.subjectId})">

        <i class="fas fa-edit"></i>

        </button>


        <button class="btn btn-danger btn-sm"
        onclick="openDeleteModal(${subject.subjectId}, '${subject.subjectName}')">

        <i class="fas fa-trash"></i>

        </button>


        </td>


        </tr>


        `;


    });


}







// ===============================
// Open Edit Modal
// ===============================

function openEditModal(id){


    // load dropdown options
    loadLecturers();



    fetch(`/api/subjects/${id}`)

    .then(response=>response.json())

    .then(subject=>{


        document.getElementById("editSubjectId").value =
        subject.subjectId;


        document.getElementById("editSubjectCode").value =
        subject.subjectCode;


        document.getElementById("editSubjectName").value =
        subject.subjectName;


        document.getElementById("editCredits").value =
        subject.credits;



        // FIXED: select lecturer
        if(subject.lecturer){

            document.getElementById("editLecturerId").value =
            subject.lecturer.lecturerId;

        }



        let modal =
        new bootstrap.Modal(
            document.getElementById("editSubjectModal")
        );


        modal.show();


    });


}








// ===============================
// Update Subject
// ===============================

function updateSubject(e){

    e.preventDefault();



    let id =
    document.getElementById("editSubjectId").value;



    let subject={


        subjectCode:
        document.getElementById("editSubjectCode").value,


        subjectName:
        document.getElementById("editSubjectName").value,


        credits:
        document.getElementById("editCredits").value,


        lecturerId:
        document.getElementById("editLecturerId").value


    };



    fetch(`/api/subjects/${id}`,{


        method:"PUT",


        headers:{


            "Content-Type":"application/json"


        },


        body:JSON.stringify(subject)



    })


    .then(response=>response.json())


    .then(data=>{


        alert("Subject Updated Successfully");



        bootstrap.Modal
        .getInstance(
            document.getElementById("editSubjectModal")
        )
        .hide();



        loadSubjects();



    });



}







// ===============================
// Delete Modal
// ===============================


let deleteId;



function openDeleteModal(id,name){


    deleteId=id;


    document.getElementById("deleteSubjectName")
    .innerText=name;



    new bootstrap.Modal(
        document.getElementById("deleteSubjectModal")
    )
    .show();



}






// ===============================
// Confirm Delete
// ===============================


document
.getElementById("confirmDeleteBtn")
.addEventListener("click",function(){



    fetch(`/api/subjects/${deleteId}`,{


        method:"DELETE"


    })


    .then(response=>{


        alert("Subject Deleted Successfully");



        bootstrap.Modal
        .getInstance(
            document.getElementById("deleteSubjectModal")
        )
        .hide();



        loadSubjects();



    });



});