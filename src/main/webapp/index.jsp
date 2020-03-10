<html>
<body>
    <h2>Webapi REST para validação de CPF e CNPJ</h2>
    <p>Para utilizar o serviço, mande uma requisição post no formato JSON com o CPF ou CNPJ, para a URI:</p>
    <p>http://localhost:8080/validador/webapi/validCPForCNPJ</p>
    <p>Somente são aceitas requisições de usuários cadastrados com o Header de autenticação Basic (Authentication) devidamente preenchido</p>
    <p>O Header de tipo de conteudo (Content-Type) deve ser preenchido com application/json.</p>
    <p>O conteudo da mensagem deve conter o par {"numberToValidate": "CPF/CNPJ"}</p>
    <p>A resposta é retornada em JSON com os campos</p>
    <p>&emsp;"valid": "(true/false)" - indicando se o CPF/CNPJ é ou não válido.</p>
    <p>&emsp;"requestTime": "(Date)" indicando a data e o momento que o servidor registrou a requisição.</p>
    <p>&emsp;"numberToValidate": "(CPF/CNPJ)" - indicando qual foi o CPF/CNPJ da requisição.</p>
    <!--  <p a="http://localhost:8080/validador/webapi/validCPForCNPJ/test"> Quick test</p>-->
</body>
</html>
