<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro header>My Users</#macro>

<#macro content>

    <a href="/logout">Выйти</a>

    <br>

    <#if users?has_content>
        Таблица пользователей:
        <br>
        Имя Логин
        <br>
        <#list users as u>
            ${u.name} ${u.login}
            <br>
        </#list>
    </#if>
</#macro>


</html>