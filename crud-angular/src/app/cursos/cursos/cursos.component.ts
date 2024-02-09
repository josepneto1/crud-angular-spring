import { Component, OnInit } from '@angular/core';
import { Curso } from '../model/curso';
import { CursosService } from '../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent implements OnInit {
  cursos: Curso[] = [];
  displayedColumns = ['nome', 'categoria']

  // cursosService: CursosService;

  constructor(private cursosService: CursosService){
    // this.cursosService = new CursosService();
    this.cursos = this.cursosService.list();
  }

  ngOnInit(): void {
  }
}
