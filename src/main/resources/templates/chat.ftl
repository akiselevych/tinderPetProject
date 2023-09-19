<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="bg-primary text-white text-center p-2">
                <#if user??>
                    <h4>Chat with ${user.name}</h4>
                </#if>

            </div>
            <div class="border rounded p-3 mt-2" style="height: 300px; overflow-y: scroll;">
                <#if messages??>
                    <#list messages as message>
                        <#if message.fromUserId == sessionUser.id>
                            <div class="media mb-3">
                                <img src="${sessionUser.avatarUrl}" width="50" height="50" class="mr-3 img-fluid" alt="Изображение отправителя">
                                <div class="media-body">
                                    <h5 class="mt-0"${sessionUser.name}</h5>
                                    ${message.text}
                                </div>
                            </div>
                        <#else>
                            <div class="media mb-3">
                                <img src="${user.avatarUrl}" width="50" height="50" class="mr-3 img-fluid" alt="Изображение отправителя">
                                <div class="media-body">
                                    <h5 class="mt-0"${user.name}</h5>
                                    ${message.text}
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <!-- Поле ввода сообщения -->
            <div class="input-group mt-3">
                <form action="/messages/${chat.id}" method="post">
                    <input type="text" name="message" class="form-control" placeholder="Enter your message"/>
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">Send</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col mt-5">
        <a href="/" class="btn btn-primary">Go to dashboard</a>
    </div>
</div>

</body>
</html>