import { Injectable, signal } from '@angular/core';

export interface Toast {
  id: number;
  message: string;
  type: 'success' | 'error' | 'warning' | 'info';
}

@Injectable({ providedIn: 'root' })
export class ToastService {
  private counter = 0;
  toasts = signal<Toast[]>([]);

  success(message: string, duration = 3500) { this.show(message, 'success', duration); }
  error(message: string, duration = 4500)   { this.show(message, 'error', duration); }
  warning(message: string, duration = 4000) { this.show(message, 'warning', duration); }
  info(message: string, duration = 3500)    { this.show(message, 'info', duration); }

  show(message: string, type: Toast['type'], duration: number) {
    const id = ++this.counter;
    this.toasts.update(t => [...t, { id, message, type }]);
    setTimeout(() => this.remove(id), duration);
  }

  remove(id: number) {
    this.toasts.update(t => t.filter(toast => toast.id !== id));
  }
}
