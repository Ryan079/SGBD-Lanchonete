import { Component, inject, input, output, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { FuncionarioService } from '../../services/funcionario.service';
import { ToastService } from '../../shared/toast/toast.service';

function cpfValidator(ctrl: AbstractControl) {
  const v = (ctrl.value || '').replace(/\D/g, '');
  return v.length === 11 ? null : { cpfInvalido: true };
}

@Component({
  selector: 'app-funcionario-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './funcionario-form.component.html'
})
export class FuncionarioFormComponent implements OnInit {
  cpf = input<string | null>(null);
  fechar = output<void>();

  private fb = inject(FormBuilder);
  private service = inject(FuncionarioService);
  private toast = inject(ToastService);

  salvando = false;

  form = this.fb.group({
    cpf:          ['', [Validators.required, cpfValidator]],
    nome:         ['', [Validators.required, Validators.maxLength(50)]],
    cargo:        ['', [Validators.required, Validators.maxLength(50)]],
    dataAdmissao: ['', Validators.required],
    salario:      [null as number | null, [Validators.required, Validators.min(0)]],
    cpfGerente:   ['']
  });

  get titulo() { return this.cpf() ? 'Editar Funcionário' : 'Novo Funcionário'; }
  err(c: string) { const f = this.form.get(c); return f?.invalid && f?.touched; }

  ngOnInit() {
    if (this.cpf()) {
      this.form.get('cpf')!.disable();
      this.service.buscarPorCpf(this.cpf()!).subscribe({
        next: d => this.form.patchValue({ ...d, salario: d.salario as unknown as number }),
        error: () => this.toast.error('Erro ao carregar funcionário')
      });
    }
  }

  mascaraCpf(event: Event, campo: string) {
    const input = event.target as HTMLInputElement;
    let v = input.value.replace(/\D/g, '').slice(0, 11);
    if (v.length > 9)      v = v.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, '$1.$2.$3-$4');
    else if (v.length > 6) v = v.replace(/(\d{3})(\d{3})(\d{0,3})/, '$1.$2.$3');
    else if (v.length > 3) v = v.replace(/(\d{3})(\d{0,3})/, '$1.$2');
    input.value = v;
    this.form.get(campo)!.setValue(v.replace(/\D/g, ''), { emitEvent: false });
  }

  salvar() {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    const val = this.form.getRawValue();
    const payload = {
      cpf: val.cpf!,
      nome: val.nome!,
      cargo: val.cargo!,
      dataAdmissao: val.dataAdmissao!,
      salario: Number(val.salario),
      cpfGerente: val.cpfGerente || undefined
    };
    this.salvando = true;
    const op = this.cpf() ? this.service.atualizar(this.cpf()!, payload) : this.service.criar(payload);
    op.subscribe({
      next: () => { this.toast.success(this.cpf() ? 'Funcionário atualizado!' : 'Funcionário cadastrado!'); this.fechar.emit(); },
      error: () => { this.toast.error('Erro ao salvar funcionário'); this.salvando = false; }
    });
  }
}
