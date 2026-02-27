import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'cardapio', pathMatch: 'full' },
  {
    path: 'cardapio',
    loadComponent: () => import('./components/cardapio/cardapio-list.component').then(m => m.CardapioListComponent)
  },
  {
    path: 'clientes',
    loadComponent: () => import('./components/cliente/cliente-list.component').then(m => m.ClienteListComponent)
  },
  {
    path: 'pedidos',
    loadComponent: () => import('./components/pedido/pedido-list.component').then(m => m.PedidoListComponent)
  },
  {
    path: 'pagamentos',
    loadComponent: () => import('./components/pagamento/pagamento-list.component').then(m => m.PagamentoListComponent)
  },
  {
    path: 'estoque',
    loadComponent: () => import('./components/estoque/estoque-list.component').then(m => m.EstoqueListComponent)
  },
  {
    path: 'fornecedores',
    loadComponent: () => import('./components/fornecedor/fornecedor-list.component').then(m => m.FornecedorListComponent)
  },
  {
    path: 'compras',
    loadComponent: () => import('./components/compra/compra-list.component').then(m => m.CompraListComponent)
  },
  {
    path: 'funcionarios',
    loadComponent: () => import('./components/funcionario/funcionario-list.component').then(m => m.FuncionarioListComponent)
  }
];
