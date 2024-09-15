# Sistema de Reservas de Hotel - Microsserviços

Este projeto consiste em três microsserviços construídos com Java 17 e Spring Boot 3.3.3, projetados para rodar em um ambiente Kubernetes. O sistema fornece funcionalidades para reservas de hotel e pesquisa de hotéis.

## Arquitetura

Segue um desenho de um sistema de busca e reserva de hóteis. Foi pensado em um tempo curto de desenvolvimento para que tenha uma boa escalabilidade e volume de acessos simultâneos.

![image](https://github.com/user-attachments/assets/fb493027-41f5-4e57-9860-677518be87d9)

Modelagem proposta pro desafio: 
![image](https://github.com/user-attachments/assets/41f395d9-b175-4e4a-a030-53dbac269f46)

## Melhorias se tivesse mais tempo de desenvolvimento
- Elastic search no serviço de busca.
- Lógica mais "complexa" ao reservar um hótel, pensando no fluxo de concorrência na reserva. De repente incluíndo algum tipo de bloqueio otimista, tempo de escolha da reserva.
- Mais testes unitários e teste de carga. No momento só incluí alguns testes de integração usando test-containers.
- Estrutura de log centralizado com Kibana/logstash.
- Implementação de serviços de monitoramento como: APM, Grafana, Prometheus, Micrometer
- Implementação do Sonar

## Pré-requisitos

Antes de começar, certifique-se de ter o seguinte instalado:

- Java 17
- Docker
- Kubectl
- Minikube

## Começando

Siga estes passos para colocar o projeto em funcionamento:

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-reservas-hotel.git
   cd sistema-reservas-hotel
   ```

2. Construa as imagens Docker para todos os microsserviços:
   ```bash
   ./build-images.sh
   ```

3. Aplique as configurações do Kubernetes:
   ```bash
   kubectl apply -k .
   ```

4. Para visualizar os pods e serviços em execução, inicie o painel do Minikube:
   ```bash
   minikube dashboard
   ```

## Microsserviços

### 1. Serviço de Reservas

Gerencia as reservas de hotel.

Exemplo de uso:
```bash
curl --location 'http://192.168.49.2/reservation-service/v1/reservations' \
--header 'Content-Type: application/json' \
--data '{
    "checkInDate": "2024-09-14",
    "checkOutDate": "2024-09-17",
    "guestsNumber": 4,
    "userId": 1,
    "roomId": 10002,
    "payments": [
        {
            "type": "PIX",
            "installmentNumber": 1,
            "value":298.74
        }
    ]
}'
```

### 2. Serviço de Busca

Fornece funcionalidade para pesquisar hotéis.

Exemplo de uso:
```bash
curl --location 'http://192.168.49.2/search-service/v1/hotels?roomCapacity=4&null=null&state=RS'
```

### 3. Serviço de Notificação

O serviço le uma fila do RabbitMQ e notifica a reserva.


## Desenvolvimento

Para fazer alterações no projeto:

1. Modifique o código-fonte conforme necessário.
2. Reconstrua as imagens Docker usando `./build-images.sh`.
3. Reaplique as configurações do Kubernetes com `kubectl apply -k .`.

## Solução de Problemas

Se você encontrar problemas:

1. Verifique o painel do Minikube para o status dos pods e logs.
2. Certifique-se de que todos os serviços necessários estão em execução:
   ```bash
   kubectl get services
   ```
3. Visualize os logs de um pod específico:
   ```bash
   kubectl logs <nome-do-pod>
   ```
