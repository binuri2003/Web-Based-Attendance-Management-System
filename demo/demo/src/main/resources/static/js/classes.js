console.log("CLASSES JS RUNNING");

let classes = [];

document.addEventListener("DOMContentLoaded", function () {
  loadClasses();

  document.getElementById("searchBtn").addEventListener("click", searchClasses);

  document.getElementById("resetBtn").addEventListener("click", function () {
    document.getElementById("searchClass").value = "";
    document.getElementById("yearFilter").value = "";
    document.getElementById("streamFilter").value = "";

    loadClasses();
  });
});

function loadClasses() {
  fetch("/api/classes")
    .then((response) => response.json())

    .then((data) => {
      console.log("Classes:", data);

      classes = data;

      displayClasses(data);
    })

    .catch((error) => {
      console.log(error);
    });
}

function searchClasses() {
  let name = document.getElementById("searchClass").value;

  let year = document.getElementById("yearFilter").value;

  let stream = document.getElementById("streamFilter").value;

  let result = classes.filter((cls) => {
    return (
      (name == "" || cls.className == name) &&
      (year == "" || cls.year == Number(year)) &&
      (stream == "" || cls.stream == stream)
    );
  });

  displayClasses(result);
}

function displayClasses(data) {
  let tbody = document.getElementById("classTableBody");

  tbody.innerHTML = "";

  document.getElementById("classCount").innerText = data.length;

  data.forEach((cls, index) => {
    tbody.innerHTML += `


<tr>


<td>${index + 1}</td>


<td>${cls.className}</td>


<td>${cls.program}</td>


<td>${cls.year}</td>


<td>${cls.stream}</td>


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

<i class="fas fa-edit"></i>

Edit

</button>



</td>


</tr>


`;
  });
}

function viewClass(id) {
  fetch("/api/classes/" + id)
    .then((response) => response.json())

    .then((cls) => {
      document.getElementById("viewClassName").innerText = cls.className;

      document.getElementById("viewProgram").innerText = cls.program;

      document.getElementById("viewYear").innerText = cls.year;

      document.getElementById("viewStream").innerText = cls.stream;

      document.getElementById("viewSemester").innerText = cls.semester;

      document.getElementById("viewAcademicYear").innerText = cls.academicYear;

      new bootstrap.Modal(document.getElementById("viewClassModal")).show();
    });
}

function editClass(id) {
  fetch("/api/classes/" + id)
    .then((response) => response.json())

    .then((cls) => {
      document.getElementById("editClassId").value = cls.classId;

      document.getElementById("editClassName").value = cls.className;

      document.getElementById("editProgram").value = cls.program;

      document.getElementById("editYear").value = cls.year;

      document.getElementById("editStream").value = cls.stream;

      document.getElementById("editSemester").value = cls.semester;

      document.getElementById("editAcademicYear").value = cls.academicYear;

      new bootstrap.Modal(document.getElementById("editClassModal")).show();
    });
}

function updateClass() {
  let id = document.getElementById("editClassId").value;

  let data = {
    className: document.getElementById("editClassName").value,

    program: document.getElementById("editProgram").value,

    year: Number(document.getElementById("editYear").value),

    stream: document.getElementById("editStream").value,

    semester: Number(document.getElementById("editSemester").value),

    academicYear: document.getElementById("editAcademicYear").value,
  };

  fetch("/api/classes/" + id, {
    method: "PUT",

    headers: {
      "Content-Type": "application/json",
    },

    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Update failed");
      }

      return response.json();
    })

    .then(() => {
      alert("Class updated successfully");

      loadClasses();

      let modal = bootstrap.Modal.getInstance(
        document.getElementById("editClassModal"),
      );

      if (modal) {
        modal.hide();
      }
    })

    .catch((error) => {
      console.log(error);
    });
}
