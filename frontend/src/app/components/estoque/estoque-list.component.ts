import { Component, inject, signal, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SlicePipe } from '@angular/common';
import { EstoqueService } from '../../services/estoque.service';
import { EstoqueResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { EstoqueFormComponent } from './estoque-form.component';

@Component({
  selector: 'app-estoque-list',
  standalone: true,
  imports: [FormsModule, SlicePipe, EstoqueFormComponent],
  templateUrl: './estoque-list.component.html'
})
export class EstoqueListComponent implements OnInit {
  private service = inject(EstoqueService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  itens = signal<EstoqueResponse[]>([]);
  loading = signal(false);
  busca = '';
  page = 0;
  totalPages = 0;

  showForm = signal(false);
  editingId = signal<number | null>(null);

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos(this.busca || undefined, this.page, 10).subscribe({
      next: res => { this.itens.set(res.content); this.totalPages = res.totalPages; this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar estoque'); this.loading.set(false); }
    });
  }

  pesquisar() { this.page = 0; this.carregar(); }
  novoItem() { this.editingId.set(null); this.showForm.set(true); }
  editar(id: number) { this.editingId.set(id); this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  async excluir(id: number, nome: string) {
    const ok = await this.confirm.confirm(`Deseja excluir o produto "${nome}" do estoque?`);
    if (!ok) return;
    this.service.deletar(id).subscribe({
      next: () => { this.toast.success('Produto excluído!'); this.carregar(); },
      error: () => this.toast.error('Erro ao excluir produto')
    });
  }

  critico(item: EstoqueResponse) { return item.qtdEstoqueAtual <= item.qtdEstoqueMinimo; }
  anterior() { if (this.page > 0) { this.page--; this.carregar(); } }
  proximo()  { if (this.page < this.totalPages - 1) { this.page++; this.carregar(); } }
}
