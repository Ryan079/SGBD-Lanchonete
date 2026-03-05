import { Component, inject, signal, OnInit } from '@angular/core';
import { SlicePipe } from '@angular/common';
import { PagamentoService } from '../../services/pagamento.service';
import { PagamentoResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { PagamentoFormComponent } from './pagamento-form.component';

@Component({
  selector: 'app-pagamento-list',
  standalone: true,
  imports: [SlicePipe, PagamentoFormComponent],
  templateUrl: './pagamento-list.component.html'
})
export class PagamentoListComponent implements OnInit {
  private service = inject(PagamentoService);
  private toast = inject(ToastService);

  pagamentos = signal<PagamentoResponse[]>([]);
  loading = signal(false);
  showForm = signal(false);
  page = 0;
  pageSize = 10;

  get totalPages() { return Math.ceil(this.pagamentos().length / this.pageSize) || 1; }
  get paginados() { return this.pagamentos().slice(this.page * this.pageSize, (this.page + 1) * this.pageSize); }

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos().subscribe({
      next: r => { this.pagamentos.set(r); this.page = 0; this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar pagamentos'); this.loading.set(false); }
    });
  }

  anterior() { if (this.page > 0) this.page--; }
  proximo()  { if (this.page < this.totalPages - 1) this.page++; }

  novoPagamento() { this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }
  formatarPreco(p: number) { return p.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }
}
