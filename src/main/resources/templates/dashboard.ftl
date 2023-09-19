<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container pt-5">
    <div class="row">
        <#if sessionUserName??>
            <div class="col">
                <h3>Current User : ${sessionUserName}</h3>
            </div>
        </#if>
        <div class="col">
            <a href="/users" class="btn btn-primary">Go to users</a>
        </div>
        <div class="col">
            <a href="/liked" class="btn btn-success">Go to users you liked</a>
        </div>
        <div class="col">
            <a href="/logout" class="btn btn-danger">Log out</a>
        </div>
    </div>
</div>
</body>
</html>
