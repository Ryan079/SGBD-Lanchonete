import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { ToastService } from '../../shared/toast/toast.service';

function cpfValidator(ctrl: AbstractControl) {
  const v = (ctrl.value || '').replace(/\D/g, '');
  return v.length === 11 ? null : { cpfInvalido: true };
}

@Component({
  selector: 'app-cliente-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './cliente-form.component.html'
})
export class ClienteFormComponent implements OnInit {
  cpf = input<string | null>(null);
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(ClienteService);
  private toast = inject(ToastService);

  salvando = false;

  form = this.fb.group({
    cpf:      ['', [Validators.required, cpfValidator]],
    nome:     ['', [Validators.required, Validators.maxLength(100)]],
    email:    ['', [Validators.email]],
    telefone: [''],
    endereco: ['']
  });

  get titulo() { return this.cpf() ? 'Editar Cliente' : 'Novo Cliente'; }
  get isEdicao() { return !!this.cpf(); }

  err(campo: string) {
    const c = this.form.get(campo);
    return c?.invalid && c?.touched;
  }

  ngOnInit() {
    if (this.cpf()) {
      this.form.get('cpf')!.disable();
      this.service.buscarPorCpf(this.cpf()!).subscribe({
        next: d => this.form.patchValue(d),
        error: () => this.toast.error('Erro ao carregar cliente')
      });
    }
  }

  aplicarMascaraCpf(event: Event) {
    const input = event.target as HTMLInputElement;
    let v = input.value.replace(/\D/g, '').slice(0, 11);
    if (v.length > 9)      v = v.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, '$1.$2.$3-$4');
    else if (v.length > 6) v = v.replace(/(\d{3})(\d{3})(\d{0,3})/, '$1.$2.$3');
    else if (v.length > 3) v = v.replace(/(\d{3})(\d{0,3})/, '$1.$2');
    input.value = v;
    this.form.get('cpf')!.setValue(v.replace(/\D/g, ''), { emitEvent: false });
  }

  aplicarMascaraTelefone(event: Event) {
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
    const payload = {
      cpf: val.cpf!,
      nome: val.nome!,
      email: val.email || undefined,
      telefone: val.telefone || undefined,
      endereco: val.endereco || undefined
    };

    this.salvando = true;
    const op = this.isEdicao
      ? this.service.atualizar(this.cpf()!, payload)
      : this.service.criar(payload);

    op.subscribe({
      next: () => {
        this.toast.success(this.isEdicao ? 'Cliente atualizado!' : 'Cliente cadastrado!');
        this.fechar.emit();
      },
      error: () => { this.toast.error('Erro ao salvar cliente'); this.salvando = false; }
    });
  }
}
