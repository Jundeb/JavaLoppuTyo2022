let studentList = document.getElementById("studentUl");

//Getting list of students when opening the webpage
getStudents();

//Form button action.
document.getElementById("createStudentButton")
.addEventListener("click", e =>{
    e.preventDefault();

    //Create random studentId with rand();
    let rand = Math.random().toString(16).substr(1, 4);

    //Creating blog object from the form information
    let student = {
        name: document.getElementById("name").value,
        age: document.getElementById("age").value,
        studentId: rand + document.getElementById("name").value,
        groupId: document.getElementById("groupId").value,
        email: document.getElementById("email").value,
        phonenumber: document.getElementById("phonenumber").value
    }

    //Posting the object as JSON to server
    //After posting getting the updated list of students from the server
    fetch("http://localhost:8080/addstudent",
        {
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(student)
        })
        .then(resp => {getStudents(); console.log(student)});
})


//Function for getting all the blogs
function getStudents()
{
    //Empty the list in webpage
    studentList.innerHTML = ""

    //Fetch all the blogs from the server
    fetch("http://localhost:8080/students")
    .then(response => response.json())
    .then( data =>
    {
        console.log(data);
        //Create list item of each blog object
        data.forEach(i => {
            let li = document.createElement("li")
            li.innerText ="Student name: " + i.name + ", StudentId: " + i.studentId;
            studentList.appendChild(li)
        })
    })
}