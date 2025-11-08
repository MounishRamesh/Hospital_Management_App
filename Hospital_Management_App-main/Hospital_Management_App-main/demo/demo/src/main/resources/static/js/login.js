const loginForm = document.getElementById("loginForm");
const errorMsg = document.getElementById("errorMsg");

// Hardcoded username and password for testing
const USERNAME = "admin";
const PASSWORD = "admin123";

loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if(username === USERNAME && password === PASSWORD){
        // Redirect to dashboard
        window.location.href = "dashboard.html";
    } else {
        errorMsg.innerText = "Invalid username or password!";
    }
});
