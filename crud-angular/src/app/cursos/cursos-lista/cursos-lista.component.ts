import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Curso } from '../model/curso';

@Component({
  selector: 'app-cursos-lista',
  templateUrl: './cursos-lista.component.html',
  styleUrl: './cursos-lista.component.scss'
})
export class CursosListaComponent {
  
  @Input() cursos: Curso[] = [];

  readonly displayedColumns = ['nome', 'categoria', 'acoes'];

  constructor(private router: Router,
    private route: ActivatedRoute){
      
    }

    onAdd() {
      this.router.navigate(['new'], {relativeTo: this.route})
    }
}
