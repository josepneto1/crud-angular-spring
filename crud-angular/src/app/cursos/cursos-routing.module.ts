import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CursosFormComponent } from './containers/cursos-form/cursos-form.component';
import { CursosComponent } from './containers/cursos/cursos.component';
import { cursoResolver } from './guards/curso.resolver';

const routes: Routes = [
  { path: '', component: CursosComponent },
  { path: 'new', component: CursosFormComponent, resolve: { curso: cursoResolver }  },
  { path: 'edit/:id', component: CursosFormComponent, resolve: { curso: cursoResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CursosRoutingModule { }
