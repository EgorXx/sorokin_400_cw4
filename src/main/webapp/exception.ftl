<#include "base.ftl">

<#macro title>Exception details</#macro>

<#macro content>
    <h1>Exception</h1>
    <br>
    <div style="background: black; border-radius: 5px; width: 200px">
        <span style="color: red"><strong>Request uri: ${uri}</strong></span>
    </div>
    <br>
    <strong>Status code: ${statusCode}</strong>
    <br>
    <#if message??><strong>${message}</strong></#if>
</#macro>

<#macro header>
</#macro>
