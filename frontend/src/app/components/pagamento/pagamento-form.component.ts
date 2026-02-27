import { Component, inject, output } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { PagamentoService } from '../../services/pagamento.service';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-pagamento-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './pagamento-form.component.html'
})
export class PagamentoFormComponent {
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(PagamentoService);
  private toast = inject(ToastService);

  salvando = false;
  metodos = ['Dinheiro', 'Cartão de Crédito', 'Cartão de Débito', 'PIX'];

  form = this.fb.group({
    idPedido:         [null as number | null, [Validators.required, Validators.min(1)]],
    valorPago:        [null as number | null, [Validators.required, Validators.min(0.01)]],
    metodoPagamento:  ['', Validators.required]
  });

  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.value;
    const payload = { idPedido: Number(val.idPedido), valorPago: Number(val.valorPago), metodoPagamento: val.metodoPagamento! };
    this.salvando = true;
    this.service.registrar(payload).subscribe({
      next: () => { this.toast.success('Pagamento registrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao registrar pagamento'); this.salvando = false; }
    });
  }
}
