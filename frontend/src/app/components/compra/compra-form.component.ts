import { Component, inject, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormArray, Validators } from '@angular/forms';
import { CompraService } from '../../services/compra.service';
import { FornecedorService } from '../../services/fornecedor.service';
import { EstoqueService } from '../../services/estoque.service';
import { FornecedorResponse, EstoqueResponse } from '../../models';
import { ToastService } from '../../shared/toast/toast.service';

@Component({
  selector: 'app-compra-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './compra-form.component.html'
})
export class CompraFormComponent implements OnInit {
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(CompraService);
  private fornecedorService = inject(FornecedorService);
  private estoqueService = inject(EstoqueService);
  private toast = inject(ToastService);

  salvando = false;
  fornecedores: FornecedorResponse[] = [];
  produtos: EstoqueResponse[] = [];

  form = this.fb.group({
    cnpjFornecedor: ['', Validators.required],
    itens: this.fb.array([])
  });

  get itens() { return this.form.get('itens') as FormArray; }

  ngOnInit() {
    this.fornecedorService.listarTodos().subscribe({ next: r => this.fornecedores = r, error: () => this.toast.error('Erro ao carregar fornecedores') });
    this.estoqueService.listarTodos(undefined, 0, 100).subscribe({ next: r => this.produtos = r.content, error: () => this.toast.error('Erro ao carregar produtos') });
    this.adicionarItem();
  }

  adicionarItem() {
    this.itens.push(this.fb.group({
      idProduto:    [null, Validators.required],
      quantidade:   [1,  [Validators.required, Validators.min(1)]],
      valorUnitario:[null,[Validators.required, Validators.min(0.01)]],
      lote:         ['',  Validators.required]
    }));
  }

  removerItem(i: number) { if (this.itens.length > 1) this.itens.removeAt(i); }
  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }
  errItem(i: number, c: string) { const f = this.itens.at(i).get(c); return f?.invalid && f?.touched; }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.getRawValue();
    const payload = {
      cnpjFornecedor: val.cnpjFornecedor!,
      itens: (val.itens as any[]).map(i => ({
        idProduto: Number(i.idProduto),
        quantidade: Number(i.quantidade),
        valorUnitario: Number(i.valorUnitario),
        lote: i.lote
      }))
    };
    this.salvando = true;
    this.service.registrar(payload).subscribe({
      next: () => { this.toast.success('Compra registrada!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao registrar compra'); this.salvando = false; }
    });
  }
}
