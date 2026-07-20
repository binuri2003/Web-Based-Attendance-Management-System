// =====================================
// Student Dashboard JavaScript
// Attendance Management System
// =====================================


// Page Load

document.addEventListener("DOMContentLoaded", function(){


    console.log("Student Dashboard Loaded");



    // ===============================
    // Active Sidebar Menu
    // ===============================


    const menuItems = document.querySelectorAll(".sidebar ul li a");


    menuItems.forEach(item => {


        item.addEventListener("click", function(event){


            document
            .querySelectorAll(".sidebar ul li")
            .forEach(li => {


                li.classList.remove("active");


            });



            this.parentElement.classList.add("active");


        });


    });







    // ===============================
    // Dashboard Buttons
    // ===============================



    const sessionBtn = document.getElementById("sessionBtn");


    if(sessionBtn){


        sessionBtn.addEventListener(
            "click",
            enterSessionCode
        );


    }






    const subjectBtn = document.getElementById("subjectBtn");


    if(subjectBtn){


        subjectBtn.addEventListener(
            "click",
            viewSubjects
        );


    }







    const historyBtn = document.getElementById("historyBtn");


    if(historyBtn){


        historyBtn.addEventListener(
            "click",
            viewAttendanceHistory
        );


    }







    const percentageBtn = document.getElementById("percentageBtn");


    if(percentageBtn){


        percentageBtn.addEventListener(
            "click",
            viewPercentage
        );


    }







    const logoutCardBtn = document.getElementById("logoutCardBtn");


    if(logoutCardBtn){


        logoutCardBtn.addEventListener(
            "click",
            logout
        );


    }



});









// =====================================
// Student Functions
// =====================================



function enterSessionCode(){


    alert(
        "Opening Enter Session Code Page"
    );


    // Spring Boot later
    // window.location.href="/student/session-code";


}







function viewSubjects(){


    alert(
        "Opening My Subjects Page"
    );


    // window.location.href="/student/subjects";


}







function viewAttendanceHistory(){


    alert(
        "Opening Attendance History Page"
    );


    // window.location.href="/student/history";


}







function viewPercentage(){


    alert(
        "Opening Attendance Percentage Page"
    );


    // window.location.href="/student/percentage";


}









// =====================================
// Logout Function
// =====================================


function logout(){



    let confirmLogout = confirm(

        "Are you sure you want to logout?"

    );





    if(confirmLogout){



        alert(

            "Logged out successfully"

        );



        // Spring Boot login controller

        window.location.href="/";



    }



}