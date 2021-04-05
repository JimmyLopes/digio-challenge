# digio-challenge

-> Desafio de Teste Java - Sistema de Compras

-> Link Collection Postman
https://www.getpostman.com/collections/a2e487d1a45f7da05a94

-> API desenvolvida com Java 8 utilizando o Spring Boot -> Funcionalidades:
- Cadastrar de Cliente - Editar nome de um Cliente - Buscar todos Clientes - Buscar Cliente por CPF - Buscar Cliente por
E-mail - Cadastrar de Produto - Excluir de Produto - Editar valor de Produto - Buscar todos Produtos - Cadastrar nova
Compra - Buscar todas as Compras - Buscar Compra por CPF Cliente - Buscar Compra por data

-> Banco utilizado: Redis

-> Mapeamento das entidades utilizado ModelMapper

-> Validação de input de dados por anotação com Javax Validation

-> Utilização de enum para dados padronizados

-> Exceções e handlers personalizadas injetando um response mais agradável

-> Comunicação com o banco de dados utilizado o CrudRepository

-> Resource subdividos por domínios e tipo de requisição

-> Endpoint para verificar o status da aplicação (Health-check)

-> Services contendo regras de negócio e tratamento de exceções

-> Classes utilitárias contendo métodos auxiliar para ajudar no desenvolvimento

-> Instância inicial dos dados utilizando a interface CommandLineRunner

-> Mensagens informativas para cada erro mapeado

-> Testes unitários desenvolvidos JUnit 4.13 e Mockito para as classes de serviço