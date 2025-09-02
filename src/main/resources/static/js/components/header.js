document.addEventListener('DOMContentLoaded', () => {
    const role = localStorage.getItem('role');
    const headerEl = role === 'ADMIN' ? document.getElementById('adminHeader') : document.getElementById('doctorHeader');
    headerEl.innerHTML = `<nav>Welcome ${role} | <button onclick="logout()">Logout</button></nav>`;
});

function logout() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('role');
    window.location.href = '/login';
}
