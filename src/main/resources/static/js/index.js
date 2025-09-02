// index.js
document.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");

    if(token && role) {
        if(role === "ADMIN") {
            window.location.href = `/adminDashboard/${token}`;
        } else if(role === "DOCTOR") {
            window.location.href = `/doctorDashboard/${token}`;
        }
    }
});

function loginUser(username, password, role) {
    fetch("/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password, role })
    })
    .then(res => res.json())
    .then(data => {
        if(data.token) {
            localStorage.setItem("token", data.token);
            localStorage.setItem("role", role);
            window.l
