import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Curso } from '../../../cursos/model/curso';

@Component({
  selector: 'app-cursos-lista',
  templateUrl: './cursos-lista.component.html',
  styleUrl: './cursos-lista.component.scss'
})
export class CursosListaComponent {
  
  @Input() cursos: Curso[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);

  readonly displayedColumns = ['nome', 'categoria', 'acoes'];

  constructor(){
  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(curso: Curso){
    this.edit.emit(curso);
    console.log('edicao');
  }
}
