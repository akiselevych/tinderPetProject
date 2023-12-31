<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <#if users??>
                  <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" style="width: 150px; height: 150px" src=${user.avatarUrl} />
                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${user.name}
                                    </td>
                                    <td>
                                        <#list chats as chat>
                                            <#if chat.participants?seq_contains(user.id)>
                                                <a class="btn btn-primary" href="/messages/${chat.id}">Open Chat with user</a>
                                            </#if>
                                        </#list>

                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
                </#if>
            </div>
        </div>
    </div>
    <div class="col mt-5">
        <a href="/" class="btn btn-primary">Go to dashboard</a>
    </div>
</div>

</body>
</html>