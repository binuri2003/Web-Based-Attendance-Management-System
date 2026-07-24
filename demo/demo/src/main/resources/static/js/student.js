document.addEventListener("DOMContentLoaded", function () {
  console.log("Student Dashboard Loaded");

  const menuItems = document.querySelectorAll(".sidebar ul li a");

  menuItems.forEach((item) => {
    item.addEventListener("click", function (event) {
      document.querySelectorAll(".sidebar ul li").forEach((li) => {
        li.classList.remove("active");
      });

      this.parentElement.classList.add("active");
    });
  });

  const sessionBtn = document.getElementById("sessionBtn");

  if (sessionBtn) {
    sessionBtn.addEventListener("click", enterSessionCode);
  }

  const subjectBtn = document.getElementById("subjectBtn");

  if (subjectBtn) {
    subjectBtn.addEventListener("click", viewSubjects);
  }

  const historyBtn = document.getElementById("historyBtn");

  if (historyBtn) {
    historyBtn.addEventListener("click", viewAttendanceHistory);
  }

  const percentageBtn = document.getElementById("percentageBtn");

  if (percentageBtn) {
    percentageBtn.addEventListener("click", viewPercentage);
  }

  const logoutCardBtn = document.getElementById("logoutCardBtn");

  if (logoutCardBtn) {
    logoutCardBtn.addEventListener("click", logout);
  }
});

function enterSessionCode() {
  alert("Opening Enter Session Code Page");
}

function viewSubjects() {
  alert("Opening My Subjects Page");
}

function viewAttendanceHistory() {
  alert("Opening Attendance History Page");
}

function viewPercentage() {
  alert("Opening Attendance Percentage Page");
}

function logout() {
  const confirmLogout = confirm("Are you sure you want to logout?");

  if (confirmLogout) {
    fetch("/logout", {
      method: "GET",
    })
      .then(() => {
        window.location.replace("/");
      })
      .catch(() => {
        window.location.replace("/");
      });
  }
}
