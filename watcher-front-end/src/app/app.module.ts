import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { LayoutModule } from '@angular/cdk/layout';
import { NavigationComponent } from './modules/navigation/navigation.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './modules/home/home.component';
import { MoviesComponent } from './modules/movies/movies.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { CloseMenuDirective } from './shared/directives/close-menu.directive';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MovieService} from './core/services/movie/movie.service';
import {HttpClientModule} from '@angular/common/http';
import {EventEmitterService} from './core/services/event-emitter/event-emitter.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { LoginComponent } from './modules/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { RegisterComponent } from './modules/register/register.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    MoviesComponent,
    CloseMenuDirective,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatCheckboxModule,
    FormsModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule
  ],
  providers: [
    MovieService,
    EventEmitterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
