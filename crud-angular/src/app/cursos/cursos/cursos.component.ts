import { Component } from '@angular/core';
import { Curso } from '../model/curso';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent {
  cursos: Curso[] = [
    {_id: "1", nome: "Angular", categoria: "front-end"}
  ];
  displayedColumns = ['nome', 'categoria']

  constructor(){
  }
}
