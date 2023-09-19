<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Registration</h2>
    <#if error?has_content>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <form action="/registration" method="post">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="avatar_url">Avatar Url</label>
            <input type="text" class="form-control" id="avatar_url" name="avatar_url" required>
        </div>
        <div class="form-group">
            <label for="gender">Gender</label>
            <select class="form-control" id="gender" name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Sign up</button>
    </form>
</div>
</body>
</html>
