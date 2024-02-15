import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CursosFormComponent } from './containers/cursos-form/cursos-form.component';
import { CursosComponent } from './containers/cursos/cursos.component';

const routes: Routes = [
  {path: '', component: CursosComponent},
  {path: 'new', component: CursosFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CursosRoutingModule { }
