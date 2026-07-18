// =======================================
// Lecturer Dashboard JavaScript
// Attendance Management System
// =======================================


// Run when page loads
document.addEventListener("DOMContentLoaded", function () {

    console.log("Lecturer Dashboard Loaded");

});




// =======================================
// Navigation Functions
// =======================================


function openSubjects() {

    alert("Opening My Subjects page");

    // Later connect with Spring Boot controller
    // window.location.href = "/lecturer/subjects";

}




function openSessions() {

    alert("Opening Create Attendance Session page");

    // Later:
    // window.location.href = "/lecturer/sessions";

}




function openMarkAttendance() {

    alert("Opening Mark / Edit Attendance page");

    // Later:
    // window.location.href = "/lecturer/attendance";

}




function openReports() {

    alert("Opening Attendance Reports page");

    // Later:
    // window.location.href = "/lecturer/reports";

}




// =======================================
// Logout Function
// =======================================


function logout() {


    let confirmLogout = confirm(
        "Are you sure you want to logout?"
    );


    if(confirmLogout){

        alert(
            "Logged out successfully"
        );


        // Go to login page
        window.location.href = "/";

    }


}