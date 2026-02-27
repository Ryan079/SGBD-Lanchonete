import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { PedidoService } from '../../services/pedido.service';
import { PedidoResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-pedido-edit',
  standalone: true,
  imports: [ReactiveFormsModule, CurrencyPipe],
  templateUrl: './pedido-edit.component.html'
})
export class PedidoEditComponent implements OnInit {
  pedido = input.required<PedidoResponse>();
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(PedidoService);
  private toast = inject(ToastService);

  salvando = false;
  situacoes = ['Pendente', 'Em Preparo', 'Entregue', 'Cancelado'];

  form = this.fb.group({
    situacao: ['', Validators.required]
  });

  ngOnInit() {
    this.form.patchValue({ situacao: this.pedido().situacao });
  }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    this.salvando = true;
    this.service.atualizarSituacao(this.pedido().idPedido, this.form.value.situacao!).subscribe({
      next: () => { this.toast.success('Situação atualizada!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao atualizar situação'); this.salvando = false; }
    });
  }
}
