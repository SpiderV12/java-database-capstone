// doctorDashboard.js
import { getAppointmentsForDoctor, getPatientPrescriptions } from './patientService.js';

const token = localStorage.getItem("token");
const doctorId = localStorage.getItem("userId"); // افترضنا حفظ معرف الطبيب عند تسجيل الدخول
const appointmentList = document.getElementById("appointmentList");
const searchInput = document.getElementById("appointmentSearch");

async function loadAppointments() {
    const appointments = await getAppointmentsForDoctor(token, doctorId);
    renderAppointments(appointments);
}

function renderAppointments(appointments) {
    appointmentList.innerHTML = "";
    appointments.forEach(appt => {
        const div = document.createElement("div");
        div.textContent = `${appt.patientName} - ${appt.date} ${appt.time}`;
        const viewBtn = document.createElement("button");
        viewBtn.textContent = "View Prescriptions";
        viewBtn.onclick = async () => {
            const prescriptions = await getPatientPrescriptions(token, appt.patientId);
            alert(JSON.stringify(prescriptions, null, 2));
        };
        div.appendChild(viewBtn);
        appointmentList.appendChild(div);
    });
}

searchInput.addEventListener("input", async (e) => {
    const allAppointments = await getAppointmentsForDoctor(token, doctorId);
    const filtered = allAppointments.filter(a => a.patientName.toLowerCase().includes(e.target.value.toLowerCase()));
    renderAppointments(filtered);
});

loadAppointments();
