let courseList = document.getElementById("courseUl")

//Getting list of students when opening the webpage
getCourses();

//Form button action.
document.getElementById("createCourseButton")
.addEventListener("click", e =>{
    e.preventDefault()

    //Create random courseId with rand();
    let rand = Math.random().toString(16).substr(1, 4);

    //Creating course object from the form information
    let course = {
        courseName: document.getElementById("coursename").value,
        courseId: rand + document.getElementById("coursename").value,
        teacher: document.getElementById("teacher").value,
        classroom: document.getElementById("classroom").value,
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
        .then(resp => {getCourses(); console.log(course)});
})


//Function for getting all the blogs
function getCourses()
{
    //Empty the list in webpage
    courseList.innerHTML = ""

    //Fetch all the blogs from the server
    fetch("http://localhost:8080/courses")
    .then(response => response.json())
    .then( data =>
    {
        console.log(data);
        //Create list item of each blog object
        data.forEach(i => {
            let li = document.createElement("li");
            li.innerText = "Course name: " + i.courseName + ", CourseId: " + i.courseId;
            courseList.appendChild(li);
        })
    })
}