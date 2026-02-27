import { Component, inject, signal, OnInit } from '@angular/core';
import { SlicePipe } from '@angular/common';
import { CompraService } from '../../services/compra.service';
import { CompraResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { CompraFormComponent } from './compra-form.component';

@Component({
  selector: 'app-compra-list',
  standalone: true,
  imports: [SlicePipe, CompraFormComponent],
  templateUrl: './compra-list.component.html'
})
export class CompraListComponent implements OnInit {
  private service = inject(CompraService);
  private toast = inject(ToastService);

  compras = signal<CompraResponse[]>([]);
  loading = signal(false);
  showForm = signal(false);

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos().subscribe({
      next: r => { this.compras.set(r); this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar compras'); this.loading.set(false); }
    });
  }

  novaCompra() { this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }
  formatarPreco(p: number) { return p.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }
  formatarCnpj(cnpj: string) { return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5'); }
}
