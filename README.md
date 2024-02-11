# CEP Finder

## Descrição
Este projeto é um serviço para consultar informações de CEP, permitindo consultar dados do endereço a partir do CEP ou do endereço completo (UF, cidade, rua).

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.2.2
- Spring Web e Spring WebFlux
- SpringDoc OpenAPI Starter WebMvc UI 2.1.0

## API Consumida
- ViaCep (https://viacep.com.br)

## Documentação
- Inicie a aplicação
- Acesse a documentação da API através do Swagger UI no seguinte link:
[Swagger UI - Consulta de CEP](http://localhost:8080/swagger-ui.html)

# Instalação e Execução

## Pré-requisitos

Antes de começar, verifique se você atende aos seguintes requisitos:

- Você instalou a versão Java 21 LTS
- Você instalou o Maven em sua máquina(Suporta java 21 LTS)
- Você tem acesso à internet para baixar as dependências do projeto

## Instalação

Siga estas etapas para instalar e executar o serviço:

1. Clone o repositório para o seu ambiente de desenvolvimento:

    ```
    git clone https://github.com/Kaliware/cepfinder.git
    ```

2. Navegue até o diretório do projeto

3. Compile o projeto usando o Maven:

    ```
    mvn clean install
    ```

## Execução

Após a instalação, siga estas etapas para executar o serviço:

1. Execute o serviço Spring Boot:

    ```
    java -jar cepfinder.jar
    ```

2. O serviço estará disponível em http://localhost:8080

# Contribuição

Se desejar contribuir com este projeto, siga estas etapas:

1. Faça um fork do projeto
2. Crie uma branch para a sua feature (`git checkout -b feature/NovaFeature`)
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/NovaFeature`)
5. Crie um novo Pull Request para branch original
