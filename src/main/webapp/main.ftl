<html lang="en">
<#include "base.ftl">

<#macro title>Main page</#macro>

<#macro header>Main page</#macro>

<#macro content>

    <a href="/logout">Выйти</a>

    <br>

    <h5>
        Hello, ${sessionUser}! Login successful
        <br>
        Session ID = ${sessionId}
        <br>
        Cookie user = ${cookieUser}
        <br>
        Ваше изображения:
        <br>
        <img src="${image}" width=300px>
    </h5>

    <br>

    <a href="/user">Список пользователей</a>
    <a href="/index">Начальная index страница</a>

</#macro>


</html>