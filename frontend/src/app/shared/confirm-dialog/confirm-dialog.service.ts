import { Injectable, signal } from '@angular/core';

interface DialogState {
  message: string;
  title: string;
  resolve: (value: boolean) => void;
}

@Injectable({ providedIn: 'root' })
export class ConfirmDialogService {
  state = signal<DialogState | null>(null);

  confirm(message: string, title = 'Confirmar exclusão'): Promise<boolean> {
    return new Promise(resolve => {
      this.state.set({ message, title, resolve });
    });
  }

  answer(value: boolean) {
    this.state()?.resolve(value);
    this.state.set(null);
  }
}
