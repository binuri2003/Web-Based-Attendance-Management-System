/* ==========================================
   Manage Students JavaScript
   Attendance Management System
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
============================== */

function loadStudents() {

    fetch("/api/students")
        .then(response => {

            if (!response.ok) {
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
   Display Students
============================== */

function displayStudents(students) {

    const tableBody = document.getElementById("studentTableBody");
    const count = document.getElementById("studentCount");

    tableBody.innerHTML = "";
    count.innerText = students.length;

    if (students.length === 0) {

        tableBody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center">
                    No students found
                </td>
            </tr>
        `;

        return;
    }

    students.forEach((student, index) => {

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
.addEventListener("click", function () {

    const keyword = document.getElementById("searchStudent").value;

    fetch("/api/students/search?keyword=" + encodeURIComponent(keyword))
        .then(response => response.json())
        .then(data => {

            displayStudents(data);

        });

});

/* ==============================
   Reset Search
============================== */

document.getElementById("resetBtn")
.addEventListener("click", function () {

    document.getElementById("searchStudent").value = "";

    loadStudents();

});

/* ==============================
   ADD STUDENT
============================== */

document.getElementById("addStudentForm")
.addEventListener("submit", function (e) {

    e.preventDefault();

    const student = {

        username: document.getElementById("username").value,

        password: document.getElementById("password").value,

        registrationNo: document.getElementById("registrationNo").value,

        studentName: document.getElementById("studentName").value,

        email: document.getElementById("email").value,

        classId: document.getElementById("classId").value

    };

    fetch("/api/students", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(student)

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Student adding failed");
        }

        return response.json();

    })

    .then(data => {

        alert("Student Added Successfully!");

        document.getElementById("addStudentForm").reset();

        bootstrap.Modal.getInstance(
            document.getElementById("addStudentModal")
        ).hide();

        loadStudents();

    })

    .catch(error => {

        console.log(error);

        alert("Error adding student");

    });

});

/* ==============================
   Open Edit Modal
============================== */

function openEditModal(id) {

    selectedStudentId = id;

    document.getElementById("editStudentId").value = id;

    new bootstrap.Modal(
        document.getElementById("editStudentModal")
    ).show();

}

/* ==============================
   Update Student
============================== */

document.getElementById("editStudentForm")
.addEventListener("submit", function (e) {

    e.preventDefault();

    alert("Update Student feature coming next.");

});

/* ==============================
   Open Delete Modal
============================== */

function openDeleteModal(id, name) {

    selectedStudentId = id;

    document.getElementById("deleteStudentName").innerText = name;

    new bootstrap.Modal(
        document.getElementById("deleteStudentModal")
    ).show();

}

/* ==============================
   Confirm Delete
============================== */

document.getElementById("confirmDeleteBtn")
.addEventListener("click", function () {

    alert("Delete Student feature coming next.");

});