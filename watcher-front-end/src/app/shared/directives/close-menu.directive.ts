import { Directive, ElementRef, Input, HostListener } from '@angular/core';
import {timeout} from 'rxjs/operators';

@Directive({
  selector: '[menuClose]'
})
export class CloseMenuDirective {
  @Input()
  public menu: any;

  constructor(private element: ElementRef) { }

  @HostListener('click')
  private onClick() {
    this.menu.classList.remove('show');
  }
}
