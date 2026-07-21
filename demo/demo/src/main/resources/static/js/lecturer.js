// =====================================
// Lecturer Dashboard JavaScript
// Attendance Management System
// =====================================


// Page Load

document.addEventListener("DOMContentLoaded", function () {

    console.log("Lecturer Dashboard Loaded");

    // Active sidebar menu

    const menuItems = document.querySelectorAll(".sidebar ul li a");

    menuItems.forEach(item => {

        item.addEventListener("click", function () {

            document.querySelectorAll(".sidebar ul li")
                .forEach(li => {

                    li.classList.remove("active");

                });

            this.parentElement.classList.add("active");

        });

    });

});



// =====================================
// Lecturer Navigation Functions
// =====================================

function openSubjects() {

    alert("Opening My Subjects Page");

    // window.location.href="/lecturer/subjects";

}

function openSessions() {

    alert("Opening Create Attendance Session Page");

    // window.location.href="/lecturer/create-session";

}

function openMarkAttendance() {

    alert("Opening Mark / Edit Attendance Page");

    // window.location.href="/lecturer/attendance";

}

function openReports() {

    alert("Opening Attendance Reports Page");

    // window.location.href="/lecturer/reports";

}



// =====================================
// Logout Function
// =====================================

function logout() {

    const confirmLogout = confirm("Are you sure you want to logout?");

    if (confirmLogout) {

        fetch("/logout", {
            method: "GET"
        })
        .then(() => {

            window.location.replace("/");

        })
        .catch(() => {

            window.location.replace("/");

        });

    }

}