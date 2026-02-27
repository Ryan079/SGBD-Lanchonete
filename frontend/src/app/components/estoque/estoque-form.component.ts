import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { EstoqueService } from '../../services/estoque.service';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-estoque-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './estoque-form.component.html'
})
export class EstoqueFormComponent implements OnInit {
  id = input<number | null>(null);
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(EstoqueService);
  private toast = inject(ToastService);

  salvando = false;

  form = this.fb.group({
    nome:             ['', [Validators.required, Validators.maxLength(100)]],
    unidadeMedida:    ['', [Validators.required, Validators.maxLength(10)]],
    qtdEstoqueAtual:  [0, [Validators.min(0)]],
    qtdEstoqueMinimo: [5, [Validators.min(0)]]
  });

  get titulo() { return this.id() ? 'Editar Produto' : 'Novo Produto'; }
  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }

  ngOnInit() {
    if (this.id()) {
      this.service.buscarPorId(this.id()!).subscribe({
        next: d => this.form.patchValue(d),
        error: () => this.toast.error('Erro ao carregar produto')
      });
    }
  }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.value;
    const payload = {
      nome: val.nome!,
      unidadeMedida: val.unidadeMedida!,
      qtdEstoqueAtual: val.qtdEstoqueAtual ?? 0,
      qtdEstoqueMinimo: val.qtdEstoqueMinimo ?? 5
    };
    this.salvando = true;
    const op = this.id() ? this.service.atualizar(this.id()!, payload) : this.service.criar(payload);
    op.subscribe({
      next: () => { this.toast.success(this.id() ? 'Produto atualizado!' : 'Produto cadastrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao salvar produto'); this.salvando = false; }
    });
  }
}
