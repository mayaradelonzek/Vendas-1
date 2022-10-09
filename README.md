### Funcionalidade adicionada: Ajuste no CRUD para consumir API externa

**Regras seguidas de acordo com cada tela:**  

- **SplashActivity**
Na tela de splash devemos verificar se já possuimos algum usuário salvo no banco, caso tenha nós deveremos navegar para a tela de Home, caso não tenha devemos ir para a tela de login

- **ProfileActivity e LoginActivity**
1. Ao entrar na tela de se registrar um usuário nós devemos criar a camada para chamar a nossa api, então devemos criar todas as camadas aprendidas em aula(ViewModel, Usecase, Repository, RemoteDataSource);
1. Após o usuário ser registrado nós devemos devemos voltar para tela de login onde iremos inserir os dados do usuário que acabou de ser criado e devemos realizar a ação de login(Essa estrutura já foi criada em aula), ao chamara a api para  fazer o login ela irá nos retornar um objeto User contendo id: Int, name:String, email:String, password:String, esse objeto deve ser salvo no banco de dados da aplicação: https://fundatec.herokuapp.com/api/login
1. Para criar um novo usuário devemos fazer um post para a api enviando name, email e password;
1. Na tela de criar transações não devemos salvar mais as transações no shared preferences do android e sim enviar a model para a api onde a api ficara responsável em salvar as transações; 
1. Criaremos uma model com base nos seguintes parametros:

```
valores aceitados pela api no campo transactionType: MARKET, GAS_STATION e PUB

{
    "value": 101,
    "transactionType": "PUB",
    "description": "Boteco dois irmãos"
}
```


6. Para esse cenário devemos seguir o padrão de projeto ensinado em aula(ViewModel, Usecase, Repository, RemoteDataSource);
7. Post passando o id do usuário por path parameter + a model de transação no body da request: https://fundatec.herokuapp.com/api/transaction

- **TransactionsActivity**
1. Na tela de listagem das transações nós devemos chamar a api para buscar as transações e apresentamos para o usuário utilizando o recyclerview;
1. GET passando o id do usuário via path parameter: https://fundatec.herokuapp.com/api/transaction

- **Cenário de delete**
1. Ao fazer uma ação de swipe em algum item da lista devemos remover o item da lista e enviar para o backend o id do usuário e o id da transação que foi excluída;
1. Exemplo do swipe para remover o item do recyclerview: https://stackoverflow.com/questions/33985719/android-swipe-to-delete-recyclerview
1. DELETE Para remover uma transação devemos enviar o id da transação para a api através de um path parameter:
https://fundatec.herokuapp.com/api/transaction

- **HomeActivity**
1. Ao entrar na tela de home devemos preencher as informações da home com as as informações do banco de dados do usuário, como por exemplo o nome;
1. Também devemos fazer uma somatória com todas as transações do usuário e preencher na tela de home;

- **Em todas as telas nós devemos exibir um loading;**

- **Imagens:**

**Splash:**

![image](https://user-images.githubusercontent.com/78550661/194761271-f95912d9-ba66-4721-969f-e74407d86bbd.png)


**Login:**

![image](https://user-images.githubusercontent.com/78550661/194761285-61ba8895-a2ea-4883-9060-78afa17454a8.png)


**Registrar:**

![image](https://user-images.githubusercontent.com/78550661/194761293-5cb7d37d-1343-4a0a-b7cb-a3e56fdd3c41.png)


**Home:**

![image](https://user-images.githubusercontent.com/78550661/194761336-6101f2a0-bbf4-42da-a798-5932be351fd4.png)


**Nova Compra:**

![image](https://user-images.githubusercontent.com/78550661/194761387-875332a2-0802-4318-9f5c-06fc32687d4e.png)
![image](https://user-images.githubusercontent.com/78550661/194761357-a55845e0-ec7c-4f23-9b15-16731563a51b.png)


**Transações:**

![image](https://user-images.githubusercontent.com/78550661/194761440-a8b2d1b7-c1d6-449a-a1ee-2840db152a60.png)
