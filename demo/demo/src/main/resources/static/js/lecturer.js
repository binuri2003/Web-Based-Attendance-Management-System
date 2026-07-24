// =====================================
// Manage Lecturers JS
// =====================================


let lecturers = [];

let selectedLecturerId = null;




// ================= PAGE LOAD =================


document.addEventListener("DOMContentLoaded", function(){


    console.log("Lecturers Page Loaded");


    loadLecturers();

    loadLecturerDropdown();

    loadSubjectDropdown();


});








// ================= LOAD TABLE =================


function loadLecturers(){


fetch("/api/lecturers")

.then(res=>res.json())

.then(data=>{


    lecturers=data;

    displayLecturers(data);


})

.catch(err=>{

console.log("Table Error:",err);

});


}








// ================= DISPLAY TABLE =================


function displayLecturers(data){


let tbody=document.getElementById("lecturerTableBody");

let count=document.getElementById("lecturerCount");


if(!tbody) return;


tbody.innerHTML="";


if(count)
count.innerText=data.length;




data.forEach((lecturer,index)=>{


tbody.innerHTML += `


<tr>

<td>${index+1}</td>

<td>${lecturer.username || "-"}</td>

<td>${lecturer.lecturerId}</td>

<td>${lecturer.lecturerName}</td>

<td>${lecturer.email}</td>

<td>${lecturer.subjects || "No Subjects Assigned"}</td>


<td>

<button class="btn btn-warning btn-sm"
onclick="openEditModal(${lecturer.lecturerId})">

Edit

</button>


<button class="btn btn-danger btn-sm"
onclick="openDeleteModal(${lecturer.lecturerId},'${lecturer.lecturerName}')">

Delete

</button>


</td>


</tr>


`;


});


}









// ================= HARD CODE LECTURER DROPDOWN =================


function loadLecturerDropdown(){


console.log("Loading lecturer dropdown");


let dropdown=document.getElementById("searchLecturer");


if(!dropdown){

console.log("searchLecturer not found");

return;

}



let list=[


[3,"Dr. Nuwan Fernando"],

[4,"Ms. Dilani Jayasinghe"],

[7,"Mr. M.D.R. Perera"],

[8,"Mrs. W.M.K.S. Illmini"],

[9,"Ms. N.H. Wanigasingha"],

[10,"Mrs. Surani Perera"],

[11,"Mr. Tisura Ambuldeniya"],

[12,"Miss Lavanka Harshani"],

[13,"Dr. K.A. Silva"],

[14,"Mr. D.M. Fernando"],

[15,"Mrs. P.N. Jayasinghe"],

[16,"Ms. H.M. Wickramasinghe"]


];




dropdown.innerHTML="";


dropdown.innerHTML +=`

<option value="">
-- Select Lecturer --
</option>

`;



list.forEach(item=>{


dropdown.innerHTML +=`

<option value="${item[1]}">

${item[0]} - ${item[1]}

</option>

`;


});



console.log("Lecturer dropdown loaded");


}











// ================= HARD CODE SUBJECT DROPDOWN =================


function loadSubjectDropdown(){


console.log("Loading subject dropdown");


let dropdown=document.getElementById("searchSubject");


if(!dropdown){

console.log("searchSubject not found");

return;

}



let list=[


["CS101","C Programming"],

["CS102","Programming Fundamentals"],

["CS103","Object-Oriented Programming"],

["CS104","Data Structures and Algorithms"],

["CS105","Database Management Systems"],

["CS106","Software Development"],

["CS107","Software Engineering"],

["CS108","Computer System Architecture"],

["CS109","Operating Systems"],

["CS110","Computer Networks"],

["CS111","Web Development"],

["CS112","Full Stack Development"],

["CS113","Mobile Application Development Lab"],

["CS114","Cyber Security Practical"],

["CS115","Information Security Lab"],

["CS116","Human Computer Interaction"],

["CS117","Artificial Intelligence Practical"]


];



dropdown.innerHTML="";


dropdown.innerHTML +=`

<option value="">
-- Select Subject --
</option>

`;



list.forEach(item=>{


dropdown.innerHTML +=`

<option value="${item[1]}">

${item[0]} - ${item[1]}

</option>

`;


});


console.log("Subject dropdown loaded");


}


// ================= SEARCH =================


document
.getElementById("searchBtn")
.addEventListener("click",function(){



let lecturer =
document.getElementById("searchLecturer").value;



let subject =
document.getElementById("searchSubject").value;





if(lecturer!==""){


fetch("/api/lecturers/search?keyword="
+encodeURIComponent(lecturer))


.then(res=>res.json())


.then(data=>{


displayLecturers(data);


});


return;


}





if(subject!==""){



fetch("/api/lecturers/search/subject?subject="
+encodeURIComponent(subject))


.then(res=>res.json())


.then(data=>{


displayLecturers(data);


});


return;


}





loadLecturers();


});









// ================= RESET =================


document
.getElementById("resetBtn")
.addEventListener("click",function(){


document.getElementById("searchLecturer").value="";


document.getElementById("searchSubject").value="";


loadLecturers();


});









// ================= ADD LECTURER =================


document
.getElementById("addLecturerForm")
.addEventListener("submit",function(e){


e.preventDefault();



let lecturer={


username:
document.getElementById("username").value,


password:
document.getElementById("password").value,


lecturerName:
document.getElementById("lecturerName").value,


email:
document.getElementById("email").value


};




fetch("/api/lecturers",{

method:"POST",

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify(lecturer)


})


.then(res=>res.json())


.then(()=>{


alert("Lecturer Added Successfully");


document.getElementById("addLecturerForm").reset();


loadLecturers();


});


});









// ================= EDIT =================


function openEditModal(id){


selectedLecturerId=id;


let lecturer =
lecturers.find(l=>l.lecturerId===id);



document.getElementById("editUsername").value=
lecturer.username;


document.getElementById("editLecturerName").value=
lecturer.lecturerName;


document.getElementById("editEmail").value=
lecturer.email;



new bootstrap.Modal(
document.getElementById("editLecturerModal")
).show();


}









// ================= UPDATE =================


document
.getElementById("editLecturerForm")
.addEventListener("submit",function(e){


e.preventDefault();



let lecturer={


username:
document.getElementById("editUsername").value,


lecturerName:
document.getElementById("editLecturerName").value,


email:
document.getElementById("editEmail").value


};



fetch("/api/lecturers/"+selectedLecturerId,{


method:"PUT",


headers:{


"Content-Type":"application/json"


},


body:JSON.stringify(lecturer)


})


.then(res=>res.json())


.then(()=>{


alert("Updated Successfully");


loadLecturers();


});


});









// ================= DELETE MODAL =================


function openDeleteModal(id,name){


selectedLecturerId=id;


document.getElementById("deleteLecturerName").innerText=name;


new bootstrap.Modal(
document.getElementById("deleteLecturerModal")
).show();


}









// ================= DELETE =================


document
.getElementById("confirmDeleteBtn")
.addEventListener("click",function(){



fetch("/api/lecturers/"+selectedLecturerId,{

method:"DELETE"

})


.then(()=>{


alert("Deleted Successfully");


bootstrap.Modal
.getInstance(
document.getElementById("deleteLecturerModal")
)
.hide();



loadLecturers();


});


});