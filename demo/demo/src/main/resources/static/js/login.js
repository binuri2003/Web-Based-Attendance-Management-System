document.getElementById("loginForm").addEventListener("submit", function(e){

    e.preventDefault();

    const username = document.getElementById("username").value;

    const password = document.getElementById("password").value;


    fetch("/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            username: username,
            password: password

        })

    })


    .then(response => response.json())


    .then(data => {


        if(data.role === "Admin"){

            window.location.href = "/admin/dashboard";

        }

        else if(data.role === "Lecturer"){

            window.location.href = "/lecturer/dashboard";

        }

        else if(data.role === "Student"){

            window.location.href = "/student/dashboard";

        }

        else{

            document.getElementById("error").innerHTML =
                "Username or Password Incorrect";

        }


    })


    .catch(error => {

        console.log("Login Error:", error);

        document.getElementById("error").innerHTML =
            "Server Error";

    });

});