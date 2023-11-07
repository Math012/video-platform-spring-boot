# Plataforma de Vídeos

Após os meus estudos sobre o Spring Boot, eu senti a necessidade de criar um projeto do zero para colocar em prática todos os meus conhecimentos que eu consegui adquirir até o momento.

Este projeto acabou ficando um pouco extenso, então irei dividir em partes.

# Model

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/894e8ad9-7087-45b0-96ac-c1d166164bb7)

Irei representar os models em sua forma já estabelecida no banco de dados para uma melhor formatação.

## UserModel:

Foi definido que a entidade usuario teria:
- Id
- user_name unico
- full_name
- password criptografada
  
As colunas: "account_non_expired" , "account_non_locked", "credentials_non_expired", "enabled" foram implementadas para a utilização do Spring Security.

  
![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/88544c1b-8e3b-4cc3-9fbd-5a989e7e76bb)

A ideia principal, foi criar uma relação entre usuarios e videos, onde um usuario poderia ter muitos videos, assim criando uma realção de "1 x N".

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/5a2311d5-22fe-454e-b796-ffb386953b89)

## VideoModel:

Foi definido que a entidade vídeo teria:

- Id
- content
- date
- title
- url_video
- user_id

A decisão de criar uma API que lida diretamente com o download e upload de videos foi algo bem desafiador porque eu nunca tinha trabalhado com esse tipo de dado, a ideia principal era dividir o video em uma sequencia de bytes para salvar dentro do banco de dados, mas após algumas pesquisas eu percebi que essa abordagem não era recomendada tendo em vista que poderia afetar a performance do banco de dados, então a melhor forma que eu encontrei para salvar essas informações foi atraves dos metadados, que consiste em salvor no banco o caminho que leva ate o video, representado pela coluna url_video.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/57c60ac5-f22f-4640-8ed0-500accac8da5)

Seguindo a ideia do relacionamento de "1 x N" foi estabelecido no VideoModel que um video teria apenas um usuario, assim criando uma relação de "1 x 1".

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/04c1fdec-a88a-446b-9d46-fb8914961d07)

# Service

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/a4537723-ee79-44ab-86fa-4ba77cb755da)

- Serviço para efetuar o login.

Recebendo uma AccountVO como parametro, colocamos estes valores dentro de duas variaveis e verificamos com a propria ferramenta do Spring Securty Core se estas credenciais esão registradas no banco de dados.

Apos a verificação setamos o username dentro de uma variavel e enviamos para JwtTokenProvider para que o token seja criado com o username que solicitou o login.

e por fim, retornamos o token para o usuario.

Não implementei o sistema de roles, mas é possivel bloquear/liberar algumas rotas para usuario com roles especificas.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/fd109d0a-d74e-4347-868a-30de4ebab8be)


- Serviço para efetuar o registro.

Recebendo um RegisterVO como parametro, a primeira etapa é criptografar a senha do usuario.

Após a criptografia, verificamos se o username informado já esta cadastrado no banco de dados, se o nome estiver registrado o sistema lança uma exceções personalizada informando que o username já esta registrado.

Após todas as validações, criamos um objeto do tipo UserModel, setamos todas as informações que são pedidas no construtor criado no UserModel e salvamos este usuario no banco através do repository.

![image](https://github.com/Math012/video-platform-spring-boot/assets/109437880/403672d6-fe85-4b2d-abec-49ce80649d1c)


