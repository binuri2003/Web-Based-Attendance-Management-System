document.addEventListener("DOMContentLoaded", () => {

    // -------------------------------
    // Welcome message
    // -------------------------------
    console.log("Admin Dashboard Loaded Successfully");

    // -------------------------------
    // Highlight active menu
    // -------------------------------
    const currentPage = window.location.pathname;
    const menuLinks = document.querySelectorAll(".menu a");

    menuLinks.forEach(link => {

        if (link.getAttribute("href") === currentPage) {
            link.parentElement.classList.add("active");
        }

    });

    // -------------------------------
    // Card hover animation
    // -------------------------------
    const cards = document.querySelectorAll(".dashboard-card");

    cards.forEach(card => {

        card.addEventListener("mouseenter", () => {

            card.style.transform = "translateY(-8px)";

        });

        card.addEventListener("mouseleave", () => {

            card.style.transform = "translateY(0px)";

        });

    });

    // -------------------------------
    // Logout confirmation
    // -------------------------------
    const logoutButtons = document.querySelectorAll('a[href="/logout"]');

    logoutButtons.forEach(logoutBtn => {

        logoutBtn.addEventListener("click", function (event) {

            event.preventDefault();

            const confirmLogout = confirm("Are you sure you want to logout?");

            if (confirmLogout) {

                fetch("/logout", {
                    method: "GET"
                })
                .then(() => {

                    window.location.replace("/");

                })
                .catch(error => {

                    console.error("Logout Error:", error);

                    window.location.replace("/");

                });

            }

        });

    });

    // -------------------------------
    // Button click effect
    // -------------------------------
    const buttons = document.querySelectorAll(".btn");

    buttons.forEach(button => {

        button.addEventListener("click", function () {

            this.style.opacity = "0.8";

            setTimeout(() => {

                this.style.opacity = "1";

            }, 150);

        });

    });

});