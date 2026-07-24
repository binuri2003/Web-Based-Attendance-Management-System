document.addEventListener("DOMContentLoaded", function () {
  console.log("Reports Page Loaded Successfully");

  // Hide all report sections initially

  document.querySelectorAll(".report-section").forEach((section) => {
    section.style.display = "none";
  });

  // Load subjects into dropdowns

  loadSubjects();

  // Load session report

  loadSessionReport();
});

function showReport(reportId) {
  document.querySelectorAll(".report-section").forEach((section) => {
    section.style.display = "none";
  });

  let selected = document.getElementById(reportId);

  if (selected) {
    selected.style.display = "block";

    window.scrollTo({
      top: selected.offsetTop - 20,

      behavior: "smooth",
    });
  }
}

function loadSubjects() {
  const subjects = [
    {
      subjectId: 1,
      subjectCode: "CS101",
      subjectName: "C Programming",
    },

    {
      subjectId: 2,
      subjectCode: "CS102",
      subjectName: "Programming Fundamentals",
    },

    {
      subjectId: 3,
      subjectCode: "CS103",
      subjectName: "Object-Oriented Programming",
    },

    {
      subjectId: 4,
      subjectCode: "CS104",
      subjectName: "Data Structures and Algorithms",
    },

    {
      subjectId: 5,
      subjectCode: "CS105",
      subjectName: "Database Management Systems",
    },

    {
      subjectId: 6,
      subjectCode: "CS106",
      subjectName: "Software Development",
    },

    {
      subjectId: 7,
      subjectCode: "CS107",
      subjectName: "Software Engineering",
    },

    {
      subjectId: 8,
      subjectCode: "CS108",
      subjectName: "Computer System Architecture",
    },

    {
      subjectId: 9,
      subjectCode: "CS109",
      subjectName: "Operating Systems",
    },

    {
      subjectId: 10,
      subjectCode: "CS110",
      subjectName: "Computer Networks",
    },

    {
      subjectId: 11,
      subjectCode: "CS111",
      subjectName: "Web Development",
    },

    {
      subjectId: 12,
      subjectCode: "CS112",
      subjectName: "Full Stack Development",
    },

    {
      subjectId: 13,
      subjectCode: "CS113",
      subjectName: "Mobile Application Development Lab",
    },

    {
      subjectId: 14,
      subjectCode: "CS114",
      subjectName: "Cyber Security Practical",
    },

    {
      subjectId: 15,
      subjectCode: "CS115",
      subjectName: "Information Security Lab",
    },

    {
      subjectId: 16,
      subjectCode: "CS116",
      subjectName: "Human Computer Interaction",
    },

    {
      subjectId: 17,
      subjectCode: "CS117",
      subjectName: "Artificial Intelligence Practical",
    },
  ];

  let studentSubject = document.getElementById("studentSubject");

  let subjectSelect = document.getElementById("subjectSelect");

  subjects.forEach((subject) => {
    let option1 = document.createElement("option");

    option1.value = subject.subjectId;

    option1.textContent = subject.subjectCode + " - " + subject.subjectName;

    studentSubject.appendChild(option1);

    let option2 = document.createElement("option");

    option2.value = subject.subjectId;

    option2.textContent = subject.subjectCode + " - " + subject.subjectName;

    subjectSelect.appendChild(option2);
  });
}

function loadStudentReport() {
  let keyword = document.getElementById("studentSearch").value.trim();

  let subject = document.getElementById("studentSubject").value;

  let table = document.getElementById("studentReportBody");

  if (keyword === "" || subject === "") {
    alert("Please enter student and select subject");

    return;
  }

  fetch(
    "/api/reports/student?keyword=" +
      encodeURIComponent(keyword) +
      "&subjectId=" +
      subject,
  )
    .then((response) => {
      if (!response.ok) {
        throw new Error("API Error");
      }

      return response.json();
    })

    .then((data) => {
      console.log("Student Report Data:", data);

      table.innerHTML = "";

      if (data.length === 0) {
        table.innerHTML = `

<tr>

<td colspan="7" class="text-center">

No Attendance Data Found

</td>

</tr>

`;

        return;
      }

      data.forEach((student) => {
        table.innerHTML += `


<tr>

<td>${student.regNo}</td>


<td>${student.name}</td>


<td>${student.subject}</td>


<td>${student.present}</td>


<td>${student.absent}</td>


<td>${student.total}</td>


<td>

<span class="badge bg-success">

${student.percentage}%

</span>

</td>


</tr>


`;
      });
    })

    .catch((error) => {
      console.error("Student report error:", error);

      table.innerHTML = `

<tr>

<td colspan="7" class="text-center text-danger">

Unable to load report

</td>

</tr>

`;
    });
}

document.addEventListener("DOMContentLoaded", function () {
  let subjectSelect = document.getElementById("subjectSelect");

  if (subjectSelect) {
    subjectSelect.addEventListener("change", function () {
      loadSubjectReport();
    });
  }
});

function loadSubjectReport() {
  let subject = document.getElementById("subjectSelect").value;

  let table = document.getElementById("subjectReportBody");

  if (subject == "") {
    return;
  }

  fetch("/api/reports/subject/" + subject)
    .then((response) => response.json())

    .then((students) => {
      table.innerHTML = "";

      students.forEach((student) => {
        table.innerHTML += `


<tr>


<td>${student.name}</td>


<td>${student.reg}</td>


<td>${student.present}</td>


<td>${student.absent}</td>


<td>


<span class="badge bg-primary">

${student.percentage}

</span>


</td>


</tr>


`;
      });
    })

    .catch((error) => {
      console.log("Subject report error:", error);
    });
}

function loadSessionReport() {
  let table = document.getElementById("sessionReportBody");

  if (!table) {
    return;
  }

  fetch("/api/reports/sessions")
    .then((response) => response.json())

    .then((sessions) => {
      table.innerHTML = "";

      sessions.forEach((session) => {
        table.innerHTML += `


<tr>


<td>${session.code}</td>


<td>${session.name}</td>


<td>${session.lecturer}</td>


<td>


<span class="badge bg-success">

${session.count}

</span>


</td>


</tr>


`;
      });
    })

    .catch((error) => {
      console.log("Session report error:", error);
    });
}
