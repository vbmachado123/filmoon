#Projeto Filmoon

### Tempo Gasto no desenvolvimento: 1 Hora 

0 - Esquema do Banco de Dados
![plot](./filmoon_scheme.png)

##Como executar?

1 - Com o projeto baixado, abra-o em uma IDE de sua preferência;

2 - Abra o terminal integrado da IDE e digite o comando para o maven instalar as dependências: mvn clean dependency:resolve e aguarde finalizar;

3 - Abra o arquivo application.properties src/main/resources/application.properties e altere as linhas 4 e 5 com o seu usuário e senha do banco respectivamente;

4 - Abra o CommandLine do Mysql ou algum programa que acesse o banco (HeidiSQL ou MySql Worckbench por exemplo) e crie um banco de dados intitulado *filmoon* - o banco não precisa ter nenhuma tabela, o Java criará elas automaticamente;

5 - Feito isso, já é possível subir o servidor, pelo atalho de Run da IDE ou abrindo novamente o terminal integrado da IDE e digitando mvn clean spring-boot:run - esse comando sobe o server;

6 - Pronto, já da para testar os Endpoint's;

7 - Acessando localhost:8080/swagger-ui/ é possível visualizar mais detalhadamente a API, como por exemplo as Entidades do banco e os Controllers - e os endpoints de cada um