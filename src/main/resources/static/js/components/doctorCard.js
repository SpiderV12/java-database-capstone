function doctorCard(doc) {
    return `<div class="doctor-card">
        <h3>${doc.firstName} ${doc.lastName}</h3>
        <p>Specialty: ${doc.specialty}</p>
    </div>`;
}
