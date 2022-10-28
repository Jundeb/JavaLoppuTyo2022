let studentList = document.getElementById("studentUl");

//Getting list of students when opening the webpage
getStudents();

//Form button action.
document.getElementById("createStudentButton")
.addEventListener("click", e =>{
    e.preventDefault();

    //Create random studentId with rand();
    let rand = Math.random().toString(16).substr(2, 4);
    let nameSplit = document.getElementById("name").value.split(' ');
    //Creating blog object from the form information
    let student = {
        name: document.getElementById("name").value,
        age: document.getElementById("age").value,
        studentId: rand + "-" + nameSplit[0],
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
        .then(response => response.text())
        .then(data => alert(data))
        .then(() => getStudents())
})


//Function for getting all the blogs
function getStudents()
{
    //clears table except first row
    var table = document.getElementById("studentTable");
    table.getElementsByTagName("tbody")[0].innerHTML = table.rows[0].innerHTML;
    //Fetch all the students from the server
    fetch("http://localhost:8080/students")
    .then(response => response.json())
    .then( data =>
    {
        const table = document.getElementById("studentTable");
        
        data.forEach(element => {
        let row = table.insertRow();
        let cell = row.insertCell();
        cell.innerHTML = element.name;
        cell = row.insertCell();
        cell.innerHTML = element.age;
        cell = row.insertCell();
        cell.innerHTML = element.studentId;
        cell = row.insertCell();
        cell.innerHTML = element.groupId;
        cell = row.insertCell();
        cell.innerHTML = element.email;
        cell = row.insertCell();
        cell.innerHTML = element.phonenumber;
        });
    })
}