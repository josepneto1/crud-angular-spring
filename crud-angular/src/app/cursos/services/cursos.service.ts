import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';
import { Curso } from '../model/curso';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'api/cursos'

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(this.API)
    .pipe(
      first(),
      // delay(2000),
      tap(cursos => console.log(cursos))
    );
  }
  
  buscarPorId(id: string){
      return this.httpClient.get<Curso>(`${this.API}/${id}`);
  }

  save(curso: Partial<Curso>){
    if (curso._id){
      return this.atualizar(curso)
    }
    return this.criar(curso);
    
  }

  private criar(curso: Partial<Curso>){
    return this.httpClient.post<Curso>(this.API, curso);
  }
  
  private atualizar(curso: Partial<Curso>){
    return this.httpClient.put<Curso>(`${this.API}/${curso._id}`, curso);
  }
  
  deletar(id: string){
    return this.httpClient.delete(`${this.API}/${id}`);
  }
  
}
