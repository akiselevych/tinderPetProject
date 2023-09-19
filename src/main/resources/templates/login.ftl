<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login Page</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="text-center">
    <img class="mb-4" src="https://getbootstrap.com/docs/4.5/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Login to Your Account</h1>

    <#if completed?has_content>
        <h2 style="color:green;">Registration completed ! Please enter your login and password</h2>
    </#if>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="text" name="login" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        </div>

        <div class="form-group">
            <label for="inputPassword"  class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        </div>

        <input class="btn btn-lg btn-primary btn-block" value="Sing in" type="submit"/>
    </form>
    <a href="/registration" class="mt-1 btn btn-lg btn-secondary btn-block">Registration</a>

    <p class="mt-3 text-muted">&copy; Tinder 2023</p>
</body>
</html>
