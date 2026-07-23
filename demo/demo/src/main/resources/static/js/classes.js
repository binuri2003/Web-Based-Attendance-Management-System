/* ===============================
   SEARCH
================================ */

document
.getElementById("searchBtn")
.addEventListener("click", function () {

    let keyword =
    document.getElementById("searchClass").value;

    let year =
    document.getElementById("yearFilter").value;

    let stream =
    document.getElementById("streamFilter").value;

    let result = classes.filter(cls => {

        return (

            (keyword === "" ||
             cls.className === keyword)

            &&

            (year === "" ||
             cls.year == year)

            &&

            (stream === "" ||
             cls.stream === stream)

        );

    });

    displayClasses(result);

});