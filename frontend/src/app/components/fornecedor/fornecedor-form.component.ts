import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { FornecedorService } from '../../services/fornecedor.service';
import { ToastService } from '../../shared/toast/toast.service';

function cnpjValidator(ctrl: AbstractControl) {
  const v = (ctrl.value || '').replace(/\D/g, '');
  return v.length === 14 ? null : { cnpjInvalido: true };
}

@Component({
  selector: 'app-fornecedor-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './fornecedor-form.component.html'
})
export class FornecedorFormComponent implements OnInit {
  cnpj = input<string | null>(null);
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(FornecedorService);
  private toast = inject(ToastService);

  salvando = false;

  form = this.fb.group({
    cnpj:        ['', [Validators.required, cnpjValidator]],
    nomeEmpresa: ['', [Validators.required, Validators.maxLength(100)]],
    dataCadastro:['', Validators.required],
    telefone:    ['', Validators.required]
  });

  get titulo() { return this.cnpj() ? 'Editar Fornecedor' : 'Novo Fornecedor'; }
  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }

  ngOnInit() {
    if (this.cnpj()) {
      this.form.get('cnpj')!.disable();
      this.service.buscarPorCnpj(this.cnpj()!).subscribe({
        next: d => this.form.patchValue(d),
        error: () => this.toast.error('Erro ao carregar fornecedor')
      });
    }
  }

  mascaraCnpj(event: Event) {
    const input = event.target as HTMLInputElement;
    let v = input.value.replace(/\D/g, '').slice(0, 14);
    if (v.length > 12) v = v.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{0,2})/, '$1.$2.$3/$4-$5');
    else if (v.length > 8) v = v.replace(/(\d{2})(\d{3})(\d{3})(\d{0,4})/, '$1.$2.$3/$4');
    else if (v.length > 5) v = v.replace(/(\d{2})(\d{3})(\d{0,3})/, '$1.$2.$3');
    else if (v.length > 2) v = v.replace(/(\d{2})(\d{0,3})/, '$1.$2');
    input.value = v;
    this.form.get('cnpj')!.setValue(v.replace(/\D/g, ''), { emitEvent: false });
  }

  mascaraTelefone(event: Event) {
    const input = event.target as HTMLInputElement;
    let v = input.value.replace(/\D/g, '').slice(0, 11);
    if (v.length > 10) v = v.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    else if (v.length > 6) v = v.replace(/(\d{2})(\d{4,5})(\d{0,4})/, '($1) $2-$3');
    else if (v.length > 2) v = v.replace(/(\d{2})(\d{0,5})/, '($1) $2');
    input.value = v;
    this.form.get('telefone')!.setValue(v, { emitEvent: false });
  }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.getRawValue();
    const payload = { cnpj: val.cnpj!, nomeEmpresa: val.nomeEmpresa!, dataCadastro: val.dataCadastro!, telefone: val.telefone! };
    this.salvando = true;
    const op = this.cnpj() ? this.service.atualizar(this.cnpj()!, payload) : this.service.criar(payload);
    op.subscribe({
      next: () => { this.toast.success(this.cnpj() ? 'Fornecedor atualizado!' : 'Fornecedor cadastrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao salvar fornecedor'); this.salvando = false; }
    });
  }
}
