<link rel="stylesheet" href="/styles.css">
<form id="loginForm" action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="text" id="password" name="password" required><br>
    <input type="submit" id="loginSubmit" name="loginSubmit">
    <select id="user_type" name="userType">
        <option value="Student">Student</option>
        <option value="Teacher">Teacher</option>
    </select>
</form>
