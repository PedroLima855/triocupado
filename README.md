# API de Hospedagem Triocupado

Esta é uma API de Hospedagem que permite aos usuários pesquisar, comparar, reservar e gerenciar reservas de hotéis. Além disso, a API oferece um sistema de notificações para confirmar o status de check-in/check-out das reservas. 

## Funcionalidades

### 1. Pesquisa de Hotéis
Os usuários podem pesquisar hotéis com base nos seguintes critérios:
- **Destino**: Nome da cidade ou região.
- **Datas**: Data de check-in e check-out.
- **Número de Quartos**: Quantidade de quartos desejados.
- **Número de Hóspedes**: Quantidade de pessoas por quarto.

### 2. Comparação de Opções
Os usuários podem comparar diferentes opções de hotéis com base nos seguintes critérios:
- **Preço**: Preço total da reserva.
- **Localização**: Endereço ou proximidade de pontos de interesse.
- **Avaliações**: Nota média de avaliações de outros usuários.

### 3. Reserva de Quartos
Os usuários podem reservar quartos para as datas desejadas, fornecendo as seguintes informações:
- **Nome do Hóspede**: Nome completo.
- **Informações de Contato**: Telefone e e-mail.
- **Detalhes de Pagamento**: Cartão de crédito ou outro método de pagamento suportado.

### 4. Sistema de Notificações
A API inclui um sistema de notificações que informa os usuários sobre o status da reserva, check-in e check-out, com mensagens automáticas via e-mail.

## Como Executar a API Localmente

Para executar a API no seu ambiente local, siga os passos abaixo:

1. **Clone o projeto**: Faça o clone deste repositório para sua máquina local

2. **Importe o projeto**: Abra o projeto na IDE de sua preferência (como IntelliJ IDEA, Eclipse ou VS Code).

3. **Suba a Api**: Execute a classe principal TriocupadoApplication para iniciar o servidor da API.

4. **Utilize a collection do Postman**: Para facilitar o teste dos endpoints, importe a collection do Postman fornecida com o projeto. Esta coleção contém todas as requisições necessárias para interagir com a API
