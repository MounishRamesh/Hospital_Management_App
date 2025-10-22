const BASE_URL = "http://localhost:9090"; // Backend base URL
const tableArea = document.getElementById("tableArea");
const formArea = document.getElementById("formArea");
const sectionTitle = document.getElementById("sectionTitle");

let editingId = null;

// Correct mapping of sidebar section to backend endpoint
const sectionApiMap = {
    billing: "billing",
    department: "departments",
    doctor: "doctors",
    appointment: "appointments",
    invoice: "invoices",
    invoiceNotification: "invoices", // same controller handles notifications
    patient: "patients",
    staff: "staff"
};

// Mapping of section to table fields
const sectionFields = {
    billing: ["id","patientName","amount","serviceType","paymentStatus"],
    department: ["id","name","description"],
    doctor: ["doctorId","name","specialty","contact"],
    appointment: ["appointmentId","patient.name","doctor.name","appointmentTime"],
    invoice: ["id","patientName","amount","serviceProvided","paymentStatus"],
    patient: ["patientId","name","age","gender","contact","address"],
    staff: ["staffId","name","role","contact","email","shiftStart","shiftEnd"]
};

// Sidebar navigation
document.querySelectorAll("#sidebar-wrapper a").forEach(link => {
    link.addEventListener("click", () => {
        const section = link.dataset.section;
        sectionTitle.innerText = section.charAt(0).toUpperCase() + section.slice(1);
        loadSection(section);
    });
});

// Load data for a section
function loadSection(section) {
    if (section === "invoiceNotification") {
        renderSection(section, []);
        return;
    }

    const apiEndpoint = sectionApiMap[section];
    fetch(`${BASE_URL}/${apiEndpoint}`)
        .then(res => res.json())
        .then(data => renderSection(section, data))
        .catch(err => {
            console.error("Error fetching data:", err);
            tableArea.innerHTML = "<p class='text-danger'>Failed to load data</p>";
        });
    editingId = null;
}

// Render table and form
function renderSection(section, data) {
    let formHtml = "";

    switch (section) {
        case "billing":
            formHtml = `
                <form>
                    <div class="mb-3">
                        <label>Patient Name</label>
                        <input type="text" name="patientName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Amount</label>
                        <input type="number" step="0.01" name="amount" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Service Type</label>
                        <select name="serviceType" class="form-select" required>
                            <option value="">--Select Service--</option>
                            <option value="Consultation">Consultation</option>
                            <option value="Surgery">Surgery</option>
                            <option value="Lab Test">Lab Test</option>
                            <option value="Physiotherapy">Physiotherapy</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Payment Status</label>
                        <select name="paymentStatus" class="form-select">
                            <option value="Pending">Pending</option>
                            <option value="Paid">Paid</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Billing" : "Add Billing"}</button>
                </form>`;
            break;

       case "department":
           formHtml = `
               <form>
                   <div class="mb-3">
                       <label>Name</label>
                       <input type="text" name="name" class="form-control" required>
                   </div>
                   <div class="mb-3">
                       <label>Description</label>
                       <select name="description" class="form-select" required>
                           <option value="">Select Description</option>
                           <option value="Cardiology">Cardiology</option>
                           <option value="Neurology">Neurology</option>
                           <option value="Pediatrics">Pediatrics</option>
                           <option value="Oncology">Oncology</option>
                           <option value="Orthopedics">Orthopedics</option>
                           <option value="Dermatology">Dermatology</option>
                           <option value="Radiology">Radiology</option>
                           <option value="ENT">ENT</option>
                           <option value="Gastroenterology">Gastroenterology</option>
                           <option value="Urology">Urology</option>
                           <option value="Nephrology">Nephrology</option>
                           <option value="Endocrinology">Endocrinology</option>
                           <option value="Psychiatry">Psychiatry</option>
                           <option value="Ophthalmology">Ophthalmology</option>
                           <option value="Pulmonology">Pulmonology</option>
                           <option value="Rheumatology">Rheumatology</option>
                           <option value="Pathology">Pathology</option>
                           <option value="Anesthesiology">Anesthesiology</option>
                           <option value="Physiotherapy">Physiotherapy</option>
                           <option value="Nutrition">Nutrition</option>
                       </select>
                   </div>
                   <button type="submit" class="btn btn-success">${editingId ? "Update Department" : "Add Department"}</button>
               </form>`;
           break;

        case "doctor":
            formHtml = `
                <form>
                    <div class="mb-3">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Specialty</label>
                        <select name="specialty" class="form-select" required>
                            <option value="">--Select Specialty--</option>
                            <option value="Cardiology">Cardiology</option>
                            <option value="Neurology">Neurology</option>
                            <option value="Orthopedics">Orthopedics</option>
                            <option value="Pediatrics">Pediatrics</option>
                            <option value="Dermatology">Dermatology</option>
                            <option value="Ophthalmology">Ophthalmology</option>
                            <option value="Psychiatry">Psychiatry</option>
                            <option value="Gynecology">Gynecology</option>
                            <option value="ENT">ENT</option>
                            <option value="Radiology">Radiology</option>
                            <option value="Physiotherapy">Physiotherapy</option>
                            <option value="Urology">Urology</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Contact</label>
                        <input type="text" name="contact" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Doctor" : "Add Doctor"}</button>
                </form>`;
            break;

        case "appointment":
            formHtml = `
                <form>
                    <div class="mb-3"><label>Patient ID</label><input type="number" name="patientId" class="form-control" required></div>
                    <div class="mb-3"><label>Doctor ID</label><input type="number" name="doctorId" class="form-control" required></div>
                    <div class="mb-3"><label>Appointment Time</label><input type="datetime-local" name="appointmentTime" class="form-control" required></div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Appointment" : "Add Appointment"}</button>
                </form>`;
            break;

        case "invoice":
            formHtml = `
                <form>
                    <div class="mb-3">
                        <label>Patient Name</label>
                        <input type="text" name="patientName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Amount</label>
                        <input type="number" step="0.01" name="amount" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Service Provided</label>
                        <select name="serviceProvided" class="form-select" required>
                            <option value="">--Select Service--</option>
                            <option value="Consultation">Consultation</option>
                            <option value="X-Ray">X-Ray</option>
                            <option value="MRI Scan">MRI Scan</option>
                            <option value="Blood Test">Blood Test</option>
                            <option value="Surgery">Surgery</option>
                            <option value="Physiotherapy">Physiotherapy</option>
                            <option value="Vaccination">Vaccination</option>
                            <option value="Ultrasound">Ultrasound</option>
                            <option value="ECG">ECG</option>
                            <option value="Dental Care">Dental Care</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Payment Status</label>
                        <select name="paymentStatus" class="form-select">
                            <option value="Pending">Pending</option>
                            <option value="Paid">Paid</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Invoice" : "Add Invoice"}</button>
                </form>`;
            break;

        case "invoiceNotification":
            formHtml = `
                <form>
                    <div class="mb-3">
                        <label>ID</label>
                        <input type="number" name="invoiceId" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Method</label>
                        <select name="method" class="form-select" required>
                            <option value="email">Email</option>
                            <option value="whatsapp">WhatsApp</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Enter the Email ID</label>
                        <input type="email" name="recipient" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">Send Notification</button>
                </form>`;

            tableArea.innerHTML = "";
            formArea.innerHTML = formHtml;

            const notifForm = formArea.querySelector("form");
            notifForm.addEventListener("submit", e => {
                e.preventDefault();
                const formData = new FormData(notifForm);
                const invoiceId = formData.get("invoiceId");
                const via = formData.get("method");
                const to = formData.get("recipient");

                fetch(`${BASE_URL}/invoices/${invoiceId}/notify?via=${via}&to=${to}`, { method: "POST" })
                    .then(res => res.text())
                    .then(msg => alert(msg))
                    .catch(err => alert("Failed to send notification: " + err));
            });
            return;

        case "patient":
            formHtml = `
                <form>
                    <div class="mb-3"><label>Name</label><input type="text" name="name" class="form-control" required></div>
                    <div class="mb-3"><label>Age</label><input type="number" name="age" class="form-control" required></div>
                    <div class="mb-3"><label>Gender</label><input type="text" name="gender" class="form-control" required></div>
                    <div class="mb-3"><label>Contact</label><input type="text" name="contact" class="form-control" required></div>
                    <div class="mb-3"><label>Address</label><input type="text" name="address" class="form-control" required></div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Patient" : "Add Patient"}</button>
                </form>`;
            break;

        case "staff":
            formHtml = `
                <form>
                    <div class="mb-3"><label>Name</label><input type="text" name="name" class="form-control" required></div>
                    <div class="mb-3"><label>Role</label><input type="text" name="role" class="form-control" required></div>
                    <div class="mb-3"><label>Contact</label><input type="text" name="contact" class="form-control" required></div>
                    <div class="mb-3"><label>Email</label><input type="email" name="email" class="form-control" required></div>
                    <div class="mb-3"><label>Shift Start</label><input type="time" name="shiftStart" class="form-control" required></div>
                    <div class="mb-3"><label>Shift End</label><input type="time" name="shiftEnd" class="form-control" required></div>
                    <button type="submit" class="btn btn-success">${editingId ? "Update Staff" : "Add Staff"}</button>
                </form>`;
            break;
    }

    // Render table
    let tableHtml = "<table class='table table-bordered'><thead><tr>";
    sectionFields[section]?.forEach(h => tableHtml += `<th>${h.split(".").pop()}</th>`);
    tableHtml += "<th>Actions</th></tr></thead><tbody>";

    data.forEach(item => {
        const recordId = item.patientId || item.doctorId || item.departmentId || item.staffId || item.appointmentId || item.billingId || item.invoiceId || item.id;
        tableHtml += "<tr>";
        sectionFields[section]?.forEach(field => {
            const keys = field.split(".");
            let value = item;
            keys.forEach(k => value = value ? value[k] : "");
            tableHtml += `<td>${value !== undefined ? value : ""}</td>`;
        });
        tableHtml += `<td>
                        <button class="btn btn-sm btn-primary me-1" onclick="editRecord('${section}',${recordId})">Edit</button>
                        <button class="btn btn-sm btn-danger" onclick="deleteRecord('${section}',${recordId})">Delete</button>
                      </td>`;
        tableHtml += "</tr>";
    });

    tableHtml += "</tbody></table>";

    tableArea.innerHTML = tableHtml;
    formArea.innerHTML = formHtml;

    const form = formArea.querySelector("form");
    form?.addEventListener("submit", e => {
        e.preventDefault();
        saveRecord(section, new FormData(form));
    });
}

// Save or update
function saveRecord(section, formData) {
    const dataObj = Object.fromEntries(formData.entries());
    const apiEndpoint = sectionApiMap[section];
    const url = editingId ? `${BASE_URL}/${apiEndpoint}/${editingId}` : `${BASE_URL}/${apiEndpoint}`;
    const method = editingId ? "PUT" : "POST";

    fetch(url, {
        method: method,
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(dataObj)
    }).then(() => loadSection(section));

    editingId = null;
}

// Edit record
function editRecord(section, id) {
    const apiEndpoint = sectionApiMap[section];
    fetch(`${BASE_URL}/${apiEndpoint}/${id}`)
        .then(res => res.json())
        .then(item => {
            editingId = id;
            loadSection(section);
            setTimeout(() => {
                const form = formArea.querySelector("form");
                Object.keys(item).forEach(key => {
                    // Handle nested patient or doctor objects
                    if ((key === "patient" || key === "doctor") && item[key]) {
                        const nestedId = item[key].patientId || item[key].doctorId || item[key].id;
                        if (form[key + "Id"]) form[key + "Id"].value = nestedId;
                    } else if (form[key]) {
                        form[key].value = item[key];
                    }
                });
            }, 150);
        });
}

// Delete record
function deleteRecord(section, id) {
    const apiEndpoint = sectionApiMap[section];
    if (confirm("Are you sure you want to delete this record?")) {
        fetch(`${BASE_URL}/${apiEndpoint}/${id}`, { method: "DELETE" })
            .then(res => {
                if (res.ok) {
                    alert("Record deleted successfully ✅");
                    loadSection(section);
                } else {
                    alert("Failed to delete the record ❌");
                }
            })
            .catch(err => {
                console.error("Delete failed:", err);
                alert("Error while deleting record: " + err);
            });
    }
}
