ECOMMERCE_POC
=============

Ola !  Este é o POC do KWIK-EMART feito por Nelson Fernando da Silva Menetti

============================================================================
Indice
============================================================================

0- A solução implementada
1- Ambiente
2- Configuração
3- Como testar ?

============================================================================
0- A solução
============================================================================

Diante dos requisitos da POC o seguinte foi feito :

Cadastro de clientes - 

Foi criado um serviço Rest com RESTEASY que é ativado via AJAX por uma tela de cadastro , 
este serviço invoca um EJB que grava os dados no banco ( JPA- Hibernate) 

Cadastro de produtos - 

Foi criado um serviço Rest com RESTEASY que é ativado via AJAX por uma tela de cadastro , 
este serviço invoca um EJB que grava os dados no banco ( JPA- Hibernate) 

Busca de produtos - 

Foi criado uma tela de busca que ativa via AJAX um serviço REST que via Hibernate-Search (LUCENE) busca produtos por suas
Descrições , nomes e categorias

Tela de Login e Segurança-

 Uma tela de login foi criada porem não fiz nenhuma implementação de fundo para a mesma.

 O plano inicial era que a segurança ( e consequentemente o Login de usuarios) fosse realizado via OAuth com Token + Sessoes Web via Shiro
 mas o tempo para desenvolver tal solução é maior do que o que posso fazer no momento até o fim de segunda-feira meu quinto dia.
 (Pois tenho que conciliar esse POC com meu trabalho).
 Caso esse seja um critero eliminatorio PARE DE LER AGORA , EU JA ESTOU REPROVADO.

Cache - EhCache é usado como 2 nivel de cache para o hibernate e todas as querys sao cacheadas pelo proprio HIBERNATE

Testes Unitarios - Não ouve tempo para criar uma suite de testes unitarios
                   Caso esse seja um critero eliminatorio PARE DE LER AGORA , EU JA ESTOU REPROVADO.

Sobre a estrutura das paginas e CSS - Eu tomei as estruturas HTML com template do BLOG http://www.webdezign.co.uk/blog/ 
  
============================================================================
1- Ambiente
============================================================================
 
 Container JBOSS EAP  
 
   A aplicação Kwik-E-Mart deste POC foi construida para rodar em Jboss EAP 6.1.
   A versão utilizada no desenvolvimento pode ser obtida em: http://www.jboss.org/products/eap 
 
 Banco de dados relacional - MySQL
 
   Como banco de dados foi utilizado o MySQL Community Edition , o mesmo pode ser obtido em http://dev.mysql.com/downloads/

 Build da solução - MAVEN

   O build da solução foi feito com MAVEN , o mesmo pode ser encontrado aqui http://maven.apache.org/

============================================================================
2- Configuração
============================================================================

 Antes de realizar testes nesta solução é preciso realizar a configuração do JBOSS e do MySQL :
 

 MySQL
 
A-) Criação do banco de dados e carga inicial de dados
 
 No projeto partindo da pasta raiz  acesse a pasta:
   \kemart-ecommerce-solution\persistence\src\main\sql\mysql
 
 Execute os scripts :
   dbschema.sql & data.sql ( NESTA ORDEM) 
  
   
 JBOSS 

 A-) Crie o modulo MySQL para o driver de banco de dados 

 Na pasta raiz da instalação do JBOSS va ate a pasta /modules:
 Crie a seguinte estrutura de pastas com/mysql/main
 Na pasta /main crie um XML com o nome "module" com o seguinte conteudo:
 
 <?xml version="1.0" encoding="UTF-8"?>
 <module xmlns="urn:jboss:module:1.0" name="com.mysql">
   <resources>
     <resource-root path="mysql-connector-java-5.1.6.jar"/>
   </resources>
   <dependencies>
     <module name="javax.api"/>
     <module name="javax.transaction.api"/>
   </dependencies>
 </module>
  
 Va até o site do MySQL ( ou baixe via Maven) o jar do  conector MySQL versao 5.1.6 e coloque o mesmo na pasta /main
 Sua estrura deve ficar assim:
 [JBOSS_HOME]/Module/com/mysql/main -> module.xml & mysql-connector-java-5.1.6.jar
 
 B-)Crie o Datasource da Aplicação no dominio standalone

 Agora vamos criar o datasource da aplicação , va até a pasta [JBOSS_HOME]/Standalone/Configurations .
 Edite o arquivo standalone.xml
 Procure pela palavra "datasource" , logo abaixo do datasource de exemplo cole a configuração abaixo:

              <datasource jta="true" jndi-name="java:jboss/datasources/KemartDS" pool-name="KemartDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/kemart</connection-url>
                    <driver>mysql</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <pool>
                        <min-pool-size>20</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                        <use-strict-min>false</use-strict-min>
                    </pool>
                    <security>
                        <user-name>USUARIO_BANCO_MYSQL</user-name>
                        <password>SENHA_DO_BANCO_MYSQL</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <statement>
                        <prepared-statement-cache-size>100</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
 
  Altere o usuario e senha para os configurados na sua instalação de banco de dados.

  Procure pela palavra "drivers"   e logo apos o driver de exemplo cole a configuração abaixo:
                <driver name="mysql" module="com.mysql"/>
  
  C-)Faça a instalação da solução no Jboss


  Execute o script standalone.bat ( ou standalone.sh se estiver em ambiente Unix) situados na pasta [JBOSS_HOME]/bin para subir a instancia do JBOSS.
  Acesse a pagina  http://localhost:9990/console/App.html#deployments e faça o deploy do war da solução **
  **(Se este for seu primeiro acesso precisará cadastrar um usuario usando o script add-user também presente na pasta [JBOSS_HOME]/bin)
  
  Você já esta pronto para testar!  

============================================================================
3- Como Testar
============================================================================          

A-) Cadastro de Usuario :

  Acesse http://localhost:8080/kwikemart-online-store/clientRegister.html
  Preencha todos os campos e clique em Create Account.

B-) Cadastro de Produto:

  Acesse http://localhost:8080/kwikemart-online-store/productRegister.html
  Preencha todos os campos e clique em Save Product.


C-) Busca de Produto:

  Crie os indices do lucene invocando o seguinte serviço REST no browser:
  http://localhost:8080/kwikemart-online-store/services/lucene/create 

  Observe que os indices serao criados na pasta  C:\var\lucene\indexes

  Acesse a pagina http://localhost:8080/kwikemart-online-store/search.html

  Faça sua busca .

D-) Login 

  A pagina de login nao funcional pode ser acessada pelo link:

  http://localhost:8080/kwikemart-online-store/login.html






 
  
