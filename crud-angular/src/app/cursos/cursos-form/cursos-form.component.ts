import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CursosService } from '../services/cursos.service';

@Component({
  selector: 'app-cursos-form',
  templateUrl: './cursos-form.component.html',
  styleUrl: './cursos-form.component.scss'
})
export class CursosFormComponent {
  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar) {
    this.form = this.formBuilder.group({
      nome: [null],
      categoria: [null]
    })
  }

  onSubmit(){
    this.service.save(this.form.value)
    .subscribe(result => console.log(result), error => this.onError());
  }

  onCancel(){

  }

  onError(){
    this.snackBar.open('Erro ao salvar', '', { duration: 5000 })
  }
}
