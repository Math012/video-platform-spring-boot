# Plataforma de Vídeos

Após os meus estudos sobre o Spring Boot, eu senti a necessidade de criar um projeto do zero para colocar em prática todos os meus conhecimentos que consegui adquirir até o momento.

Este projeto acabou ficando um pouco extenso, então irei dividir em partes.

# Model

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/894e8ad9-7087-45b0-96ac-c1d166164bb7)

Irei representar os models em sua forma já estabelecida no banco de dados para uma melhor formatação.

## UserModel:

Foi definido que a entidade usuário teria:
- Id
- user_name unico
- full_name
- password criptografada
  
As colunas: "account_non_expired" , "account_non_locked", "credentials_non_expired", "enabled" foram implementadas para a utilização do Spring Security.

  
![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/88544c1b-8e3b-4cc3-9fbd-5a989e7e76bb)

A ideia principal, foi criar uma relação entre usuários e vídeos, onde um usuário poderia ter muitos vídeos, assim criando uma relação de "1 x N".

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/5a2311d5-22fe-454e-b796-ffb386953b89)

## VideoModel:

Foi definido que a entidade vídeo teria:

- Id
- content
- date
- title
- url_video
- user_id

A decisão de criar uma API que lida diretamente com o download e upload de vídeos foi algo bem desafiador porque eu nunca tinha trabalhado com esse tipo de dado, a ideia principal era dividir o video em uma sequência de bytes para salvar dentro do banco de dados, mas após algumas pesquisas eu percebi que essa abordagem não era recomendada tendo em vista que poderia afetar a desempenho do banco de dados, então a melhor forma que encontrei para salvar essas informações foi através dos metadados, que consiste em salvar no banco o caminho que leva até o video, representado pela coluna url_video.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/57c60ac5-f22f-4640-8ed0-500accac8da5)

Seguindo a ideia do relacionamento de "1 x N" foi estabelecido no VideoModel que um video teria apenas um usuário, assim criando uma relação de "1 x 1".

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/04c1fdec-a88a-446b-9d46-fb8914961d07)

# Service

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/a4537723-ee79-44ab-86fa-4ba77cb755da)

- Serviço para efetuar o login.

Recebendo uma AccountVO como parâmetro, colocamos estes valores dentro de duas variáveis e verificamos com a própria ferramenta do Spring Securty Core se estas credenciais são registradas no banco de dados.

Apos a verificação setamos o username em uma variável e enviamos para JwtTokenProvider para que o token seja criado com o username que solicitou o login.

e por fim, retornamos o token para o usuário.

Não implementei o sistema de roles, mas é possível bloquear/liberar algumas rotas para usuário com roles especificas.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/fd109d0a-d74e-4347-868a-30de4ebab8be)


- Serviço para efetuar o registro.

Recebendo um RegisterVO como parametro, a primeira etapa é criptografar a senha do usuário.

Após a criptografia, verificamos se o username informado já está cadastrado no banco de dados, se o nome estiver registrado o sistema lança uma exceção personalizada informando que o username já está registrado.

Após todas as validações, criamos um objeto do tipo UserModel, setamos todas as informações pedidas no construtor criado no UserModel e salvamos este usuário no banco através do repository.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/403672d6-fe85-4b2d-abec-49ce80649d1c)

- Serviço para efetuar o upload dos vídeos.

Tendo um retorno de um VideoVO e recebendo um VideoVO e uma String como parâmetro, verificamos se este usuário está registrado no banco de dados.

A ideia principal é que o desenvolvedor front end, transite o token e o username do usuário pelas rotas da API através do localstorage, assim que usuário fizer login, o token e o username vão para o localstorage, assim podendo utilizar o sistema de upload de vídeos.

Após a verificação do username, convertemos o VideoVO em VideoModel para setar o usuário e a sua data.

Ao final do processo, convertemos novamente o VideoModel para VideoVO e registramos o video no banco de dados com o username do usuário que fez o upload.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/fb720a6b-1182-4eaf-b100-79b64fca489e)




