<html lang="en">
    <head>
    <title>Dashboard</title>
    </head>

    <body>
        <h1>Dashboard</h1>
        <#if user??>
        <p>Current logged user is : ${user.name}</p>
        <p>His age is : ${user.age}</p>
        </#if>
        <a href="/countries"> Go to countries </a>
        <a href="/logout"> Log out </a>
    </body>
</html>