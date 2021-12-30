let student_form = document.getElementById('student-validation');
let course_form = document.getElementById('course-validation');
window.onload = fetch_courses;

student_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (student_form.checkValidity() === true) {
        console.log();
        let response = await fetch('api/students/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                first_name: document.getElementById('first_name').value,
                last_name: document.getElementById('last_name').value,
                courses: [{'course_id':document.getElementById('courses1').value},
                    {'course_id':document.getElementById('courses2').value}],
                roll_number: document.getElementById('roll_number').value,
            })
        }).then(
            response => {
                if (response['status'] === 203) {
                    document.getElementById("login-success").style.display = "none";
                    document.getElementById("login-alert").style.display = "block";

                } else {
                    document.getElementById("login-alert").style.display = "none";
                    document.getElementById("login-success").style.display = "block";
                    document.getElementById("student-validation").reset();
                    setTimeout(function (){
                        location.href="index.html";
                    },2000);
                }
            }
        );
    } else {
        student_form.classList.add('was-validated');
    }
});

async function fetch_courses() {

    let response = await fetch("api/courses/get");
    let courses = await response.json(); // read response body and parse as JSON
    console.log(courses);
    let courses_option1 = document.getElementById('courses1');
    let courses_option2 = document.getElementById('courses2');

    courses_option1.innerHTML = '<option value=""> Choose Course1</option>';

    for (let i = 0; i < courses.length; i++) {
        courses_option1.innerHTML += '<option value="' + courses[i]['course_id'] + '">' + courses[i]['name'] + '</option>';
    }
    courses_option2.innerHTML = '<option value=""> Choose Course2</option>';

    for (let i = 0; i < courses.length; i++) {
        courses_option2.innerHTML += '<option value="' + courses[i]['course_id'] + '">' + courses[i]['name'] + '</option>';
    }

}