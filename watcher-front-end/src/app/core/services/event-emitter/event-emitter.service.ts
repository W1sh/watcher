import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invoker = new EventEmitter();

  constructor() { }

  emit(value: string) {
    this.invoker.emit(value);
  }
}
