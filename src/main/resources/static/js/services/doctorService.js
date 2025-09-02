// doctorService.js
const API_URL = "/api/doctors";

export async function getAllDoctors(token) {
    const res = await fetch(API_URL, {
        headers: { "Authorization": `Bearer ${token}` }
    });
    return await res.json();
}

export async function searchDoctors(token, query) {
    const doctors = await getAllDoctors(token);
    return doctors.filter(d => d.name.toLowerCase().includes(query.toLowerCase()));
}

export async function addDoctor(token, doctorData) {
    const res = await fetch(API_URL, {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(doctorData)
    });
    return await res.json();
}

export async function deleteDoctor(token, doctorId) {
    const res = await fetch(`${API_URL}/${doctorId}`, {
        method: "DELETE",
        headers: { "Authorization": `Bearer ${token}` }
    });
    return await res.json();
}

