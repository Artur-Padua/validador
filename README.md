# Webapi REST para validação de CPF e CNPJ
    Para utilizar o serviço, mande uma requisição Post no formato JSON com o CPF ou CNPJ, para a URI
    http://localhost:8080/validador/webapi/validCPForCNPJ
    Para ver os dados do usuario, mande uma requisição Get para a URI
    http://localhost:8080/validador/webapi/validCPForCNPJ/userData
    Somente são aceitas requisições de usuários cadastrados com o Header de autenticação Basic (Authentication) devidamente preenchido.
    O Header de tipo de conteudo (Content-Type) deve ser preenchido com application/json.
    O conteudo da mensagem deve conter o par {"numberToValidate": "CPF/CNPJ"}
    A resposta é retornada em JSON com os campos
        "valid": "(true/false)" - indicando se o CPF/CNPJ é ou não válido.
        "requestTime": "(Date)" indicando a data e o momento que o servidor registrou a requisição.
        "numberToValidate": "(CPF/CNPJ)" - indicando qual foi o CPF/CNPJ da requisição.
        
   Using Tomcat 9.0
   
   Using Maven dependencies
   
   Database not working properly, because databaseCreation method get called every request.
   
   PostmanTestData.txt contains the import file for Postman Chrome plugin. Postman plugin was used to perform the tests.
