import {EventEmitter, Injectable} from '@angular/core';
import {Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invokeSearchPopular = new EventEmitter();
  subscription: Subscription;

  constructor() { }

  onSearchPopular() {
    this.invokeSearchPopular.emit();
  }
}
