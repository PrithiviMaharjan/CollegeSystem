<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css"
    href="../css/register.css" />
</head>
<body>
    <div class="container">
        <h1>Registration Form</h1>
        <form action="../register"
            method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="col">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="col">
                    <label for="birthday">Date of Birth:</label>
                    <input type="date" id="birthday" name="dob" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div class="col">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="col">
                    <label for="retypePassword">Retype Password:</label>
                    <input type="password" id="retypePassword" name="retypePassword" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="image">Profile Picture</label>
                    <input type="file" id="image" name="image">
                </div>
            </div>

            <!-- Buttons Row -->
            <div class="row buttons-row">
                <button type="submit" class="submit-button">Submit</button>
                <a href="../pages/login.jsp" class="login-button">Login</a>
            </div>
        </form>
    </div>
</body>
</html>