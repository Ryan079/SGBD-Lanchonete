import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CardapioService } from '../../services/cardapio.service';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-cardapio-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './cardapio-form.component.html'
})
export class CardapioFormComponent implements OnInit {
  id = input<number | null>(null);
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(CardapioService);
  private toast = inject(ToastService);

  salvando = false;

  form = this.fb.group({
    nome:      ['', [Validators.required, Validators.maxLength(100)]],
    categoria: ['', Validators.required],
    descricao: [''],
    preco:     [null as number | null, [Validators.required, Validators.min(0.01)]]
  });

  get titulo() { return this.id() ? 'Editar Item' : 'Novo Item'; }
  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }

  ngOnInit() {
    if (this.id()) {
      this.service.buscarPorId(this.id()!).subscribe({
        next: d => this.form.patchValue({ ...d, preco: d.preco as unknown as number }),
        error: () => this.toast.error('Erro ao carregar item')
      });
    }
  }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.value;
    const payload = {
      nome: val.nome!,
      categoria: val.categoria as 'Bebida' | 'Lanche' | 'Sobremesa',
      descricao: val.descricao || undefined,
      preco: Number(val.preco)
    };
    this.salvando = true;
    const op = this.id()
      ? this.service.atualizar(this.id()!, payload)
      : this.service.criar(payload);
    op.subscribe({
      next: () => { this.toast.success(this.id() ? 'Item atualizado!' : 'Item cadastrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao salvar item'); this.salvando = false; }
    });
  }
}
