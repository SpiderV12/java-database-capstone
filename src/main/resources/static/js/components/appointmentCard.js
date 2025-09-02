function appointmentCard(app) {
    return `<div class="appointment-card">
        <p>Patient: ${app.patientName}</p>
        <p>Date/Time: ${app.dateTime}</p>
        <p>Notes: ${app.notes || 'N/A'}</p>
    </div>`;
}
