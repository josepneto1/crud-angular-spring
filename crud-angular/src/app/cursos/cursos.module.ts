import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../compartilhado/app-material/app-material.module';
import { CompartilhadoModule } from '../compartilhado/compartilhado.module';
import { CursosListaComponent } from '../compartilhado/components/cursos-lista/cursos-lista.component';
import { CursosFormComponent } from './containers/cursos-form/cursos-form.component';
import { CursosComponent } from './containers/cursos/cursos.component';
import { CursosRoutingModule } from './cursos-routing.module';


@NgModule({
  declarations: [
    CursosComponent,
    CursosFormComponent,
    CursosListaComponent
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    AppMaterialModule,
    CompartilhadoModule,
    ReactiveFormsModule
  ]
})
export class CursosModule { }
