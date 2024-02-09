import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, catchError, of } from 'rxjs';
import { ErrorDialogComponent } from '../../compartilhado/components/error-dialog/error-dialog.component';
import { Curso } from '../model/curso';
import { CursosService } from '../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent implements OnInit {
  cursos$: Observable<Curso[]>;
  displayedColumns = ['nome', 'categoria']

  // cursosService: CursosService;

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog  
  ){
    // this.cursosService = new CursosService();
    this.cursos$ = this.cursosService.list()
    .pipe(
      catchError(error  => {
        this.onError("Erro ao carregar cursos");
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void {
  }
}
