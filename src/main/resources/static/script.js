const showMessage = (msg, color = 'green') => {
  const messageDiv = document.getElementById("message");
  messageDiv.textContent = msg;
  messageDiv.style.color = color;
};

function signup() {
  const user = {
    email: document.getElementById("signup-email").value,
    password: document.getElementById("signup-password").value,
  };

  fetch("/api/auth/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  })
    .then((res) => res.text())
    .then((data) => showMessage(data))
    .catch(() => showMessage("Signup failed", "red"));
}

function login() {
  const user = {
    email: document.getElementById("login-email").value,
    password: document.getElementById("login-password").value,
  };

  fetch("/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  })
    .then((res) => res.text())
    .then((data) => showMessage(data))
    .catch(() => showMessage("Login failed", "red"));
}
