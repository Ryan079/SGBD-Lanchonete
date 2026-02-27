# Serviços Angular - Documentação

Este diretório contém todos os serviços para comunicação com a API do backend.

## Estrutura

- **cliente.service.ts** - Gerenciamento de clientes
- **cardapio.service.ts** - Gerenciamento do cardápio
- **pedido.service.ts** - Gerenciamento de pedidos
- **estoque.service.ts** - Gerenciamento de estoque
- **fornecedor.service.ts** - Gerenciamento de fornecedores
- **compra.service.ts** - Gerenciamento de compras
- **funcionario.service.ts** - Gerenciamento de funcionários
- **pagamento.service.ts** - Gerenciamento de pagamentos
- **reports.service.ts** - Relatórios e views

## Exemplo de Uso

### Em um Componente

```typescript
import { Component, inject } from '@angular/core';
import { ClienteService } from './services';
import { ClienteRequest, ClienteResponse } from './models';

@Component({
  selector: 'app-exemplo',
  standalone: true,
  template: `
    <div>
      <button (click)="listarClientes()">Listar Clientes</button>
      <button (click)="criarCliente()">Criar Cliente</button>
    </div>
  `
})
export class ExemploComponent {
  private clienteService = inject(ClienteService);

  listarClientes() {
    this.clienteService.listarTodos().subscribe({
      next: (clientes) => {
        console.log('Clientes:', clientes);
      },
      error: (err) => {
        console.error('Erro:', err);
      }
    });
  }

  criarCliente() {
    const novoCliente: ClienteRequest = {
      cpf: '12345678901',
      nome: 'João Silva',
      email: 'joao@email.com',
      telefone: '87999999999',
      endereco: 'Rua Exemplo, 123'
    };

    this.clienteService.criar(novoCliente).subscribe({
      next: (cliente) => {
        console.log('Cliente criado:', cliente);
      },
      error: (err) => {
        console.error('Erro ao criar:', err);
      }
    });
  }
}
```

### Usando Signals (Angular 20+)

```typescript
import { Component, inject, signal } from '@angular/core';
import { ClienteService } from './services';
import { ClienteResponse } from './models';

@Component({
  selector: 'app-clientes',
  standalone: true,
  template: `
    <div>
      @for (cliente of clientes(); track cliente.cpf) {
        <p>{{ cliente.nome }}</p>
      }
    </div>
  `
})
export class ClientesComponent {
  private clienteService = inject(ClienteService);
  clientes = signal<ClienteResponse[]>([]);

  ngOnInit() {
    this.clienteService.listarTodos().subscribe({
      next: (page) => {
        this.clientes.set(page.content);
      }
    });
  }
}
```

## Configuração da API

A URL base da API está configurada em `config/api.config.ts`. Por padrão, aponta para:
- `http://localhost:8080/api`

Para alterar, edite o arquivo `src/app/config/api.config.ts`.
