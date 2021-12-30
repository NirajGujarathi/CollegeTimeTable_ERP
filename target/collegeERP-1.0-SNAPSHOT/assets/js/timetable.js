let timetable = document.getElementById("timetable");
timetable.addEventListener('submit',async(e) =>{
    e.preventDefault();
    e.stopPropagation();

    console.log("running timetable.js");

    let response = await fetch('api/students/courseid', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify({
        roll_number : localStorage.getItem("roll_number")
    })
});
try {
    let result = await response.json();
    console.log(result);
    console.log(result.length);
    console.log(result[0]);
    let name=[]
    let days=[]
    let time=[]
    for (let i=0;i<result.length;i++)
    {
        name.push(result[i]['course_name']);
    }
    console.log(name);
    localStorage.setItem("data",JSON.stringify(result));
}
catch (err){
    console.log(err);
}

})
