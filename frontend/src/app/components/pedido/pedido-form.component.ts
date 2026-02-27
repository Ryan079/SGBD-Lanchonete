import { Component, inject, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormArray, Validators } from '@angular/forms';
import { PedidoService } from '../../services/pedido.service';
import { CardapioService } from '../../services/cardapio.service';
import { CardapioResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-pedido-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './pedido-form.component.html'
})
export class PedidoFormComponent implements OnInit {
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(PedidoService);
  private cardapioService = inject(CardapioService);
  private toast = inject(ToastService);

  salvando = false;
  cardapioOpcoes: CardapioResponse[] = [];

  form = this.fb.group({
    cpfCliente:      ['', Validators.required],
    enderecoEntrega: [''],
    pontoReferencia: [''],
    taxaEntrega:     [0],
    trocoPara:       [0],
    itens: this.fb.array([])
  });

  get itens() { return this.form.get('itens') as FormArray; }

  ngOnInit() {
    this.cardapioService.listarTodos(undefined, 0, 100).subscribe({
      next: r => this.cardapioOpcoes = r.content,
      error: () => this.toast.error('Erro ao carregar cardápio')
    });
    this.adicionarItem();
  }

  adicionarItem() {
    this.itens.push(this.fb.group({
      idCardapio: [null, Validators.required],
      quantidade: [1, [Validators.required, Validators.min(1)]]
    }));
  }

  removerItem(i: number) {
    if (this.itens.length > 1) this.itens.removeAt(i);
  }

  mascaraCpf(event: Event) {
    const input = event.target as HTMLInputElement;
    let v = input.value.replace(/\D/g, '').slice(0, 11);
    if (v.length > 9)      v = v.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, '$1.$2.$3-$4');
    else if (v.length > 6) v = v.replace(/(\d{3})(\d{3})(\d{0,3})/, '$1.$2.$3');
    else if (v.length > 3) v = v.replace(/(\d{3})(\d{0,3})/, '$1.$2');
    input.value = v;
    this.form.get('cpfCliente')!.setValue(v.replace(/\D/g, ''), { emitEvent: false });
  }

  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }
  errItem(i: number, c: string) { const f = this.itens.at(i).get(c); return f?.invalid && f?.touched; }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.getRawValue();
    const payload = {
      cpfCliente: val.cpfCliente!,
      enderecoEntrega: val.enderecoEntrega || undefined,
      pontoReferencia: val.pontoReferencia || undefined,
      taxaEntrega: Number(val.taxaEntrega) || 0,
      trocoPara: Number(val.trocoPara) || 0,
      itens: (val.itens as any[]).map(i => ({ idCardapio: Number(i.idCardapio), quantidade: Number(i.quantidade) }))
    };
    this.salvando = true;
    this.service.criar(payload).subscribe({
      next: () => { this.toast.success('Pedido registrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao registrar pedido'); this.salvando = false; }
    });
  }
}
