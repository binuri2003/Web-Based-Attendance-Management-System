let selectedLecturerId = null;

document.addEventListener("DOMContentLoaded", function () {
  loadLecturers();
});

function loadLecturers() {
  fetch("/api/lecturers")
    .then((response) => response.json())

    .then((data) => {
      displayLecturers(data);
    })

    .catch((error) => {
      console.log("Load Error:", error);
    });
}

function displayLecturers(lecturers) {
  let tableBody = document.getElementById("lecturerTableBody");

  let count = document.getElementById("lecturerCount");

  tableBody.innerHTML = "";

  count.innerText = lecturers.length;

  if (lecturers.length === 0) {
    tableBody.innerHTML = `

        <tr>

        <td colspan="7" class="text-center">

        No Lecturers Found

        </td>

        </tr>

        `;

    return;
  }

  lecturers.forEach((lecturer, index) => {
    tableBody.innerHTML += `


<tr>


<td>${index + 1}</td>


<td>${lecturer.username || "-"}</td>


<td>${lecturer.lecturerId}</td>


<td>${lecturer.lecturerName}</td>


<td>${lecturer.email}</td>


<td>${lecturer.subjects || "No Subjects Assigned"}</td>



<td>


<button class="btn btn-warning btn-sm"

onclick="openEditModal(${lecturer.lecturerId})">

<i class="fas fa-pen"></i>

Edit

</button>




<button class="btn btn-danger btn-sm"

onclick="openDeleteModal(${lecturer.lecturerId}, '${lecturer.lecturerName}')">

<i class="fas fa-trash"></i>

Delete

</button>


</td>


</tr>


`;
  });
}

document.getElementById("searchBtn").addEventListener("click", function () {
  let lecturerKeyword = document.getElementById("searchLecturer").value;

  let subjectKeyword = document.getElementById("searchSubject").value;

  if (lecturerKeyword !== "") {
    fetch(
      "/api/lecturers/search?keyword=" + encodeURIComponent(lecturerKeyword),
    )
      .then((res) => res.json())

      .then((data) => {
        displayLecturers(data);
      });

    return;
  }

  if (subjectKeyword !== "") {
    fetch(
      "/api/lecturers/search/subject?subject=" +
        encodeURIComponent(subjectKeyword),
    )
      .then((res) => res.json())

      .then((data) => {
        displayLecturers(data);
      });

    return;
  }

  loadLecturers();
});

document.getElementById("resetBtn").addEventListener("click", function () {
  document.getElementById("searchLecturer").value = "";

  document.getElementById("searchSubject").value = "";

  loadLecturers();
});

document
  .getElementById("addLecturerForm")
  .addEventListener("submit", function (e) {
    e.preventDefault();

    let lecturer = {
      username: document.getElementById("username").value,

      password: document.getElementById("password").value,

      lecturerName: document.getElementById("lecturerName").value,

      email: document.getElementById("email").value,
    };

    fetch("/api/lecturers", {
      method: "POST",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(lecturer),
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error();
        }

        return res.json();
      })

      .then(() => {
        alert("Lecturer Added Successfully");

        document.getElementById("addLecturerForm").reset();

        bootstrap.Modal.getInstance(
          document.getElementById("addLecturerModal"),
        ).hide();

        loadLecturers();
      })

      .catch(() => {
        alert("Error Adding Lecturer");
      });
  });

function openEditModal(id) {
  selectedLecturerId = id;

  fetch("/api/lecturers")
    .then((res) => res.json())

    .then((lecturers) => {
      let lecturer = lecturers.find((l) => l.lecturerId === id);

      document.getElementById("editLecturerId").value = lecturer.lecturerId;

      document.getElementById("editUsername").value = lecturer.username;

      document.getElementById("editLecturerName").value = lecturer.lecturerName;

      document.getElementById("editEmail").value = lecturer.email;

      new bootstrap.Modal(document.getElementById("editLecturerModal")).show();
    });
}

document
  .getElementById("editLecturerForm")
  .addEventListener("submit", function (e) {
    e.preventDefault();

    let lecturer = {
      username: document.getElementById("editUsername").value,

      lecturerName: document.getElementById("editLecturerName").value,

      email: document.getElementById("editEmail").value,
    };

    fetch("/api/lecturers/" + selectedLecturerId, {
      method: "PUT",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(lecturer),
    })
      .then((res) => res.json())

      .then(() => {
        alert("Lecturer Updated Successfully");

        bootstrap.Modal.getInstance(
          document.getElementById("editLecturerModal"),
        ).hide();

        loadLecturers();
      });
  });

function openDeleteModal(id, name) {
  selectedLecturerId = id;

  document.getElementById("deleteLecturerName").innerText = name;

  new bootstrap.Modal(document.getElementById("deleteLecturerModal")).show();
}

document
  .getElementById("confirmDeleteBtn")
  .addEventListener("click", function () {
    fetch("/api/lecturers/" + selectedLecturerId, {
      method: "DELETE",
    }).then(() => {
      alert("Lecturer Deleted Successfully");

      bootstrap.Modal.getInstance(
        document.getElementById("deleteLecturerModal"),
      ).hide();

      loadLecturers();
    });
  });
