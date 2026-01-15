const errorMsg = document.getElementById("errorMsg");
errorMsg.innerText = "";

if (!subjectsInput || !teachersInput || !roomsInput || !slotsInput) {
    errorMsg.innerText = "⚠️ Please fill all fields before generating timetable.";
    return;
}

function generateTimetable() {

    const subjectsInput = document.getElementById("subjects").value;
    const teachersInput = document.getElementById("teachers").value;
    const roomsInput = document.getElementById("rooms").value;
    const slotsInput = document.getElementById("slots").value;

    const subjects = subjectsInput.split(",").map(s => {
        const [name, count] = s.split(":");
        return { name: name.trim(), sessionsPerWeek: parseInt(count) };
    });

    const teachers = teachersInput.split(",").map(t => {
        const [name, subject] = t.split(":");
        return { name: name.trim(), subject: subject.trim() };
    });

    const rooms = roomsInput.split(",").map(r => r.trim());

    const timeSlots = slotsInput.split(",").map(s => {
        s = s.trim();
        if (s.toLowerCase() === "break") {
            return { day: "", startTime: "", endTime: "", isBreak: true };
        }
        const [day, time] = s.split(" ");
        const [start, end] = time.split("-");
        return {
            day,
            startTime: start,
            endTime: end,
            isBreak: false
        };
    });
    document.getElementById("loader").style.display = "block";


    fetch("/api/timetable/input", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            subjects,
            teachers,
            rooms,
            timeSlots
        })
    })
    .then(() => fetch("/api/timetable"))
    .then(res => res.json())
    .then(showTimetable);
}

function showTimetable(data) {
    const table = document.getElementById("timetable");
    table.innerHTML = `
        <tr>
            <th>Day</th>
            <th>Time</th>
            <th>Subject</th>
            <th>Teacher</th>
            <th>Room</th>
        </tr>
    `;

    data.forEach(t => {
        const row = table.insertRow();
        row.insertCell(0).innerText = t.timeSlot.day;
        row.insertCell(1).innerText =
            t.timeSlot.startTime + " - " + t.timeSlot.endTime;
        row.insertCell(2).innerText = t.subject.name;
        row.insertCell(3).innerText = t.teacher.name;
        row.insertCell(4).innerText = t.room.roomName;
    });
}
document.getElementById("loader").style.display = "none";

function downloadPdf() {
    window.open("/api/timetable/download", "_blank");
}
function toggleTheme() {
    document.body.classList.toggle("dark");
}

