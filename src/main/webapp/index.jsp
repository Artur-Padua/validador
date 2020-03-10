<html>
<body>
    <h2>Webapi REST para valida��o de CPF e CNPJ</h2>
    <p>Para utilizar o servi�o, mande uma requisi��o post no formato JSON com o CPF ou CNPJ, para a URI:</p>
    <p>http://localhost:8080/validador/webapi/validCPForCNPJ</p>
    <p>Somente s�o aceitas requisi��es de usu�rios cadastrados com o Header de autentica��o Basic (Authentication) devidamente preenchido</p>
    <p>O Header de tipo de conteudo (Content-Type) deve ser preenchido com application/json.</p>
    <p>O conteudo da mensagem deve conter o par {"numberToValidate": "CPF/CNPJ"}</p>
    <p>A resposta � retornada em JSON com os campos</p>
    <p>&emsp;"valid": "(true/false)" - indicando se o CPF/CNPJ � ou n�o v�lido.</p>
    <p>&emsp;"requestTime": "(Date)" indicando a data e o momento que o servidor registrou a requisi��o.</p>
    <p>&emsp;"numberToValidate": "(CPF/CNPJ)" - indicando qual foi o CPF/CNPJ da requisi��o.</p>
    <!--  <p a="http://localhost:8080/validador/webapi/validCPForCNPJ/test"> Quick test</p>-->
</body>
</html>
