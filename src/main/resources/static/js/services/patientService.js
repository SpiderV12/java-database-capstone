// patientService.js
const PATIENT_API = "/api/patients";

export async function getAppointmentsForDoctor(token, doctorId) {
    const res = await fetch(`${PATIENT_API}/appointments/${doctorId}`, {
        headers: { "Authorization": `Bearer ${token}` }
    });
    return await res.json();
}

export async function getPatientPrescriptions(token, patientId) {
    const res = await fetch(`${PATIENT_API}/prescriptions/${patientId}`, {
        headers: { "Authorization": `Bearer ${token}` }
    });
    return await res.json();
}
