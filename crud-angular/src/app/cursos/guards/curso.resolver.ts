import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Curso } from '../model/curso';
import { CursosService } from '../services/cursos.service';

export const cursoResolver: ResolveFn<Observable<Curso>> = (route, state,  service: CursosService = inject(CursosService)) => {

  if (route.params?.['id']){
    return service.buscarPorId(route.params['id']);
  }

  return of({_id: '', nome: '', categoria: ''});

};
