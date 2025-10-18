<html lang="en">
<#include "base.ftl">

<#macro title>SignUp Page</#macro>

<#macro header>SignUp</#macro>

<#macro content>

<div class="container" style="max-width: 500px;">
    <form method="post" action="/sign_up" enctype="multipart/form-data">
        Введите <strong>name</strong>, <strong>lastname</strong>, <strong>login</strong>, <strong>password</strong>, для регистрации
        <br>
        <br>
        Name:
        <input type="text" class="form-control" name="name" placeholder="type your name here">
        <br>
        Lastname:
        <input type="text" class="form-control" name="lastname" placeholder="type your lastname here">
        <br>
        Login:
        <input type="text" name="login" class="form-control" id="input_login" placeholder="type your login here">

        <div class="flex-box" id="ajax-response-exist-login" style="color: red"></div>

        Password:
        <input type="password" name="password" class="form-control">
        <br>

        Загрузка фото:
        <input type="file" name="file" class="form-control">
        <br>

        <input type="submit" class="btn btn-primary" id="submit-button" value="sign_up">
    </form>
</div>

</#macro>

<#macro scripts>
    <script>
        $(document).on("input", "#input_login", function () {

            $.get("/ajax/login_exist?login=" + $("#input_login").val(), function (response) {
                $("#ajax-response-exist-login").text(response)

                if (response !== "") {
                    $("#submit-button").prop("disabled", true);
                } else {
                    $("#submit-button").prop("disabled", false);
                }
            })

        })
    </script>
</#macro>

</html>