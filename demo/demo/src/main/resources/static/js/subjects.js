// ===============================
// Manage Subjects JS
// ===============================


document.addEventListener("DOMContentLoaded", function () {


    loadSubjects();

    loadLecturers();


    // Add Subject
    document
        .getElementById("addSubjectForm")
        .addEventListener("submit", addSubject);


    // Edit Subject
    document
        .getElementById("editSubjectForm")
        .addEventListener("submit", updateSubject);


    // Search
    document
        .getElementById("searchBtn")
        .addEventListener("click", searchSubjects);


    // Reset
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
                onclick="openDeleteModal(${subject.subjectId},
                '${subject.subjectName}')">


                <i class="fas fa-trash"></i>

                </button>


                </td>


            </tr>


            `;



        });



    })

    


}





// ===============================
// Load Lecturers Dropdown
// ===============================

function loadLecturers() {

    fetch("/api/lecturers")
        .then(response => response.json())
        .then(data => {

            const addDropdown = document.getElementById("lecturerId");
            const editDropdown = document.getElementById("editLecturerId");

            // Clear previous options
            addDropdown.innerHTML =
                '<option value="">Select Lecturer</option>';

            editDropdown.innerHTML =
                '<option value="">Select Lecturer</option>';

            data.forEach(lecturer => {

                // Add Subject dropdown
                const addOption = document.createElement("option");
                addOption.value = lecturer.lecturerId;
                addOption.textContent =
                    lecturer.lecturerId + " - " + lecturer.lecturerName;
                addDropdown.appendChild(addOption);

                // Edit Subject dropdown
                const editOption = document.createElement("option");
                editOption.value = lecturer.lecturerId;
                editOption.textContent =
                    lecturer.lecturerId + " - " + lecturer.lecturerName;
                editDropdown.appendChild(editOption);

            });

        })
        .catch(error => {
            console.error("Error loading lecturers:", error);
        });

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



let modal =
bootstrap.Modal.getInstance(
document.getElementById("addSubjectModal")
);


modal.hide();



loadSubjects();



})


.catch(error=>{


console.log(error);

alert("Add Subject Failed");


});


}







// ===============================
// Search Subject
// ===============================


function searchSubjects(){


let keyword =
document.getElementById("searchSubject").value.toLowerCase();



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
// Display Table
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
onclick="openDeleteModal(${subject.subjectId},
'${subject.subjectName}')">


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



fetch(`/api/subjects/${id}`)


.then(response=>response.json())


.then(subject=>{


document.getElementById("editSubjectId").value=
subject.subjectId;


document.getElementById("editSubjectCode").value=
subject.subjectCode;


document.getElementById("editSubjectName").value=
subject.subjectName;


document.getElementById("editCredits").value=
subject.credits;



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



let modal =
bootstrap.Modal.getInstance(
document.getElementById("editSubjectModal")
);


modal.hide();



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



let modal =
new bootstrap.Modal(
document.getElementById("deleteSubjectModal")
);


modal.show();



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



let modal =
bootstrap.Modal.getInstance(
document.getElementById("deleteSubjectModal")
);


modal.hide();



loadSubjects();



})

.catch(error=>{


console.log(error);

alert("Delete Failed");


});


});