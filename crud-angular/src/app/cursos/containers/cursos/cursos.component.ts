import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, of } from 'rxjs';
import { ConfirmationDialogComponent } from '../../../compartilhado/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from '../../../compartilhado/components/error-dialog/error-dialog.component';
import { Curso } from '../../model/curso';
import { CursosService } from '../../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent implements OnInit {
  cursos$: Observable<Curso[]> | null = null;

  // cursosService: CursosService;

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    private router: Router,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute
  ){
    this.refresh();
  }

  refresh(){
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

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route})
  }

  onEdit(curso: Curso){
    if (curso && curso._id) {
        this.router.navigate(['edit', curso._id], {relativeTo: this.route});
    }
  }

  onDelete(curso: Curso){
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Deseja excluir este curso?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result){
        this.cursosService.deletar(curso._id).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Removido com sucesso', 'X', { 
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            });
          },
          error => this.onError("Erro ao tentar remover curso")
          );
        }
    });
  }
}
