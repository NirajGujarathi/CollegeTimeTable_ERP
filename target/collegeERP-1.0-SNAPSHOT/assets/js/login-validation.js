let login_form = document.getElementById('login-validation');
login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        document.getElementById("submit-button").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";
        let response = await fetch('api/students/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                roll_number: document.getElementById('roll_number').value,
            })
        });
        let result = await response;
        console.log(response);
        if(result['status'] === 200){
            let data = response.json();
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";


            localStorage.setItem("roll_number",document.getElementById("roll_number").value);
            location.href="timetable.html";
        }
        else{
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";

            document.getElementById("login-alert").style.display = "block";
        }
    }
});