let courseList = document.getElementById("courseUl")

//Getting list of students when opening the webpage
getCourses();

//Form button action.
document.getElementById("createCourseButton")
    .addEventListener("click", e => {
        e.preventDefault()

        //Create random courseId with rand();
        let rand = Math.random().toString(16).substr(2, 4);
        let courseNameSplit = document.getElementById("coursename").value.split(' ');
        //Creating course object from the form information
        let course = {
            courseName: document.getElementById("coursename").value,
            courseId: rand + "-" + courseNameSplit[0],
            teacher: document.getElementById("teacher").value,
            classRoom: document.getElementById("classRoom").value,
        }

        //Posting the object as JSON to server
        //After posting getting the updated list of students from the server
        fetch("http://localhost:8080/addcourse",
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(course)
            })
            .then(response => response.text())
            .then(data => alert(data))
            .then(() => getCourses())
    })

//Form button action.
document.getElementById("addStudentToCourseButton")
    .addEventListener("click", e => {
        e.preventDefault()

        //Create a object
        let object = {
            studentId: document.getElementById("studentId").value,
            courseId: document.getElementById("courseId").value
        }

        //Posting the object as JSON to server
        //After posting getting the updated list of students from the server
        fetch("http://localhost:8080/addstudenttocourse",
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(object)
            })
            .then(response => response.text())
            .then(data => alert(data))
            .then(() => getCourses())
    })


//Function for getting all the courses
function getCourses() 
{
    //clears table except first row
    var table = document.getElementById("courseTable");
    table.getElementsByTagName("tbody")[0].innerHTML = table.rows[0].innerHTML;

    //Fetch all the courses from the server
    fetch("http://localhost:8080/courses")
        .then(response => response.json())
        .then(data => {
            console.log(data[0].participants.length);
            console.log(data);
            const table = document.getElementById("courseTable");

            data.forEach(element => {
                let row = table.insertRow();
                let cell = row.insertCell();
                cell.innerHTML = element.courseName;
                cell = row.insertCell();
                cell.innerHTML = element.courseId;
                cell = row.insertCell();
                cell.innerHTML = element.teacher;
                cell = row.insertCell();
                cell.innerHTML = element.classRoom;
                cell = row.insertCell();
                cell.innerHTML = element.participants.length;
            });

        })
}
