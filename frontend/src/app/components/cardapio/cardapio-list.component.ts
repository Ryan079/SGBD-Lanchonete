import { Component, inject, signal, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CardapioService } from '../../services/cardapio.service';
import { CardapioResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';
import { ConfirmDialogService } from '../../shared/confirm-dialog/confirm-dialog.service';
import { CardapioFormComponent } from './cardapio-form.component';

@Component({
  selector: 'app-cardapio-list',
  standalone: true,
  imports: [FormsModule, CardapioFormComponent],
  templateUrl: './cardapio-list.component.html'
})
export class CardapioListComponent implements OnInit {
  private service = inject(CardapioService);
  private toast = inject(ToastService);
  private confirm = inject(ConfirmDialogService);

  itens = signal<CardapioResponse[]>([]);
  loading = signal(false);
  categoria = '';
  page = 0;
  totalPages = 0;

  showForm = signal(false);
  editingId = signal<number | null>(null);

  categorias = ['', 'Bebida', 'Lanche', 'Sobremesa'];

  ngOnInit() { this.carregar(); }

  carregar() {
    this.loading.set(true);
    this.service.listarTodos(this.categoria || undefined, this.page, 10).subscribe({
      next: res => { this.itens.set(res.content); this.totalPages = res.totalPages; this.loading.set(false); },
      error: () => { this.toast.error('Erro ao carregar cardápio'); this.loading.set(false); }
    });
  }

  filtrar() { this.page = 0; this.carregar(); }
  novoItem() { this.editingId.set(null); this.showForm.set(true); }
  editar(id: number) { this.editingId.set(id); this.showForm.set(true); }
  fecharForm() { this.showForm.set(false); this.carregar(); }

  async excluir(id: number, nome: string) {
    const ok = await this.confirm.confirm(`Deseja excluir "${nome}" do cardápio?`);
    if (!ok) return;
    this.service.deletar(id).subscribe({
      next: () => { this.toast.success('Item excluído!'); this.carregar(); },
      error: (err) => {
        const msg = err?.status === 409
          ? 'Item não pode ser excluído pois está vinculado a pedidos existentes.'
          : 'Erro ao excluir item';
        this.toast.error(msg);
      }
    });
  }

  formatarPreco(p: number) { return p.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); }
  badgeCategoria(cat: string) {
    return cat === 'Bebida' ? 'badge--blue' : cat === 'Lanche' ? 'badge--green' : 'badge--yellow';
  }
  anterior() { if (this.page > 0) { this.page--; this.carregar(); } }
  proximo()  { if (this.page < this.totalPages - 1) { this.page++; this.carregar(); } }
}
