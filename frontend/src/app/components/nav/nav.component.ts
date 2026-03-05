import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent {
  links = [
    { label: 'Cardápio',     icon: '🍔', path: '/cardapio' },
    { label: 'Clientes',     icon: '👤', path: '/clientes' },
    { label: 'Pedidos',      icon: '📋', path: '/pedidos' },
    { label: 'Pagamentos',   icon: '💳', path: '/pagamentos' },
    { label: 'Relatórios',   icon: '📊', path: '/relatorios' },
    { label: 'Dashboard',    icon: '📈', path: '/dashboard-relatorios' },
    { label: 'Estoque',      icon: '📦', path: '/estoque' },
    { label: 'Fornecedores', icon: '🏭', path: '/fornecedores' },
    { label: 'Compras',      icon: '🛒', path: '/compras' },
    { label: 'Funcionários', icon: '👷', path: '/funcionarios' },
  ];
}
