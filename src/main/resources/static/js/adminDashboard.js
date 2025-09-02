// adminDashboard.js
import { getAllDoctors, searchDoctors, addDoctor, deleteDoctor } from './doctorService.js';

const token = localStorage.getItem("token");
const doctorListContainer = document.getElementById("doctorList");
const searchInput = document.getElementById("doctorSearch");

async function loadDoctors() {
    const doctors = await getAllDoctors(token);
    renderDoctors(doctors);
}

function renderDoctors(doctors) {
    doctorListContainer.innerHTML = "";
    doctors.forEach(doc => {
        const div = document.createElement("div");
        div.textContent = `${doc.name} - ${doc.specialty}`;
        const delBtn = document.createElement("button");
        delBtn.textContent = "Delete";
        delBtn.onclick = () => deleteDoctor(token, doc.id).then(loadDoctors);
        div.appendChild(delBtn);
        doctorListContainer.appendChild(div);
    });
}

searchInput.addEventListener("input", async (e) => {
    const filtered = await searchDoctors(token, e.target.value);
    renderDoctors(filtered);
});

// Modal for adding doctor
document.getElementById("addDoctorBtn").onclick = async () => {
    const name = document.getElementById("newDoctorName").value;
    const specialty = document.getElementById("newDoctorSpecialty").value;
    await addDoctor(token, { name, specialty });
    loadDoctors();
}

loadDoctors();
