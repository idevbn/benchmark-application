<h1 align="center">PLN-API</h1>

<h3> O que é o projeto? </h3>
<p>O projeto é uma API REST que realiza operações de CRUD a partir de dados obtidos de uma API terceira.</p>
<p>Os dados obtidos são armazenados e processados para que sejam exibidos pelo cliente da aplicação.</p>
<p>A API utilizada nesse processo é a <a href="https://api-ninjas.com/api/covid19" target="_blank">API NINJAS</p>

<hr>

<h3 id="sumario"> Sumário </h3>

- <a href="#requisitos"> Requisitos para executar o projeto</a>
- <a href="#como-executar"> Como executar o projeto</a>
- <a href="#endpoints"> Endpoints utilizados</a>
- <a href="#tecnologias">Tecnologias e recursos utilizadas no projeto.</a>

<hr>

<h3 id="requisitos"> Requisitos para executar o projeto</h3>

- <a target="_blank" href="https://maven.apache.org/">Maven</a>
- <a target="_blank" href="https://openjdk.java.net/install/">Java 8</a>
- <a target="_blank" href="https://docs.docker.com/engine/install/">Docker</a>

<hr>

<h3 id="como-executar"> Executar o projeto</h3>

- Instancie e rode (por exemplo) o container executando o comando no terminal:

  ```shell
  sudo docker run --name nome-do-container -e POSTGRES_PASSWORD=sua_senha -p 5432:5432 -d postgres:13.3
  ```

<h4>As credenciais informadas deverão ser utilizadas no arquivo de propriedades da aplicação.</h4>

<h4>Mais detalhes de implementação e funcionalidades estão descritos no arquivo infos.txt</h4>

<br>

<h3 id="endpoints">Endpoints da aplicação</h3>

<h4>Requisição para obter dados da API externa:</h4>

```json
[GET] /covid - Realiza requisição para a API externa utilizando REST
```

<br>

<h4>Cria um <strong>benchmark</strong></h4>

```json
[POST] /benchmark
```

<p>Recebendo no corpo da requisição um payload da forma:</p>

```json
{
  "benchmark_name": "name",
  "first_country_name": "name1",
  "second_country_name": "name2"
}
```

<br>

<h4>Recupera os dados de um benchmark</h4>

```json
[GET] /benchmark/id
```

<br>

<h4>Atualiza o nome de um benchmark existente</h4>

```json
[PUT] /benchmark/id
```

<p>Corpo da requisição</p>

```json
{
  "benchmark_name": "New Name"
}
```

<br>

<h4>Exclui um benchmark</h4>

```json
[DEL] /benchmark/id
```

<br>

<h4>Obtém dados processados de uma benchmark com base no período utilizado na filtragem</h4>

```json
[GET] /benchmark/filter/id?startDate=startDate&endDate=endDate
```

<br>

<h3 id="tecnologias"> Tecnologias e recursos utilizadas no projeto</h3>

- Java
- Banco de dados PostgreSQL
- Docker
<hr>
