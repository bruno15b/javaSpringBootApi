API REST construida utilizando Spring Boot, banco utilizado H2, em produção nos servidores da AWS.

•	Criar uma pessoa:
POST -> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers
{
"name": "nome",
"birthDate": "yyyy-mm-dd"
}

•	Editar uma pessoa
PUT -> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers/id <-(id da pessoa)
{
"name": "nome",
"birthDate": "yyyy-mm-dd"
}

•	Consultar uma pessoa
GET -> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers/id <-(id da pessoa)

•	Listar pessoas
GET -> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers

•	Criar endereço para pessoa
POST ->http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers/id/add_address
                                                                      (id da pessoa)^
{
"streetOrAvenueName": "rua ou avenida",
"addressNumber": 30, <- numero inteiro
"zipCode": "cep",
"cityName": "cidade"
}

•	Listar endereços da pessoa
GET -> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers/id/all_addresses
                                                                     (id da pessoa) ^
•	Poder informar qual endereço é o principal da pessoa  
PUT-> http://apicustomer-env.eba-3yipt6mj.sa-east-1.elasticbeanstalk.com/customers/id/id <- (id do endereço)
                                                                    (id da pessoa) ^
