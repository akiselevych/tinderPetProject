<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

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
            <!-- Заголовок диалога -->
            <div class="bg-primary text-white text-center p-2">
                <h4>Chat with Polina</h4>
            </div>
            <!-- Окно сообщений -->
            <div class="border rounded p-3 mt-2" style="height: 300px; overflow-y: scroll;">
                <!-- Сообщение отправителя -->
                <div class="media mb-3">
                    <img src="http://andreeanati.com/wp-content/uploads/2020/09/Mob-Journal-Prom-2.jpg" width="50" height="50" class="mr-3 img-fluid" alt="Изображение отправителя">
                    <div class="media-body">
                        <h5 class="mt-0">Sender</h5>
                        Привет! Как дела?
                    </div>
                </div>
                <!-- Сообщение получателя -->
                <div class="media mb-3">
                    <img src="https://avatars.githubusercontent.com/u/140167030?v=4?s=400" width="50" height="50" class="mr-3 img-fluid" alt="Изображение получателя">
                    <div class="media-body">
                        <h5 class="mt-0">Receiver</h5>
                        Привет! Всё хорошо, спасибо. А у тебя?
                    </div>
                </div>
                <!-- Другие сообщения могут быть добавлены здесь -->
            </div>
            <!-- Поле ввода сообщения -->
            <div class="input-group mt-3">
                <input type="text" class="form-control" placeholder="Enter your message">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>