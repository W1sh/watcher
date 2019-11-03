import {EventEmitter, Injectable} from '@angular/core';
import {Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invokeSearchPopular = new EventEmitter();
  popularSubscription: Subscription;

  invokeSearchUpcoming = new EventEmitter();
  upcomingSubscription: Subscription;

  constructor() { }

  onSearchPopular() {
    this.invokeSearchPopular.emit();
  }

  onSearchUpcoming() {
    this.invokeSearchUpcoming.emit();
  }
}
