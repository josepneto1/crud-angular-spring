import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../compartilhado/app-material/app-material.module';
import { CompartilhadoModule } from '../compartilhado/compartilhado.module';
import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './cursos/cursos.component';
import { CursosFormComponent } from './cursos-form/cursos-form.component';


@NgModule({
  declarations: [
    CursosComponent,
    CursosFormComponent
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    AppMaterialModule,
    CompartilhadoModule
  ]
})
export class CursosModule { }
