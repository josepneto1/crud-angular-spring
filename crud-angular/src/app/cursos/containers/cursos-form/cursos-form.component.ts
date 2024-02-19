import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Curso } from '../../model/curso';
import { CursosService } from '../../services/cursos.service';

@Component({
  selector: 'app-cursos-form',
  templateUrl: './cursos-form.component.html',
  styleUrl: './cursos-form.component.scss'
})
export class CursosFormComponent {
  form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, 
      Validators.minLength(5),
      Validators.maxLength(100)]],
    categoria: ['', Validators.required]
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {

      const curso: Curso = this.route.snapshot.data['curso'];
      this.form.patchValue(curso)
  }

  onSubmit(){
    this.service.save(this.form.value)
    .subscribe(result => this.onSuccess(), error => this.onError());
  }

  onCancel(){
    this.location.back();
  }

  private onSuccess(){
    this.snackBar.open('Salvo com sucesso', '', { duration: 5000 });
    this.onCancel();
  }

  private onError(){
    this.snackBar.open('Erro ao salvar', '', { duration: 5000 });
  }

  getErrorMessage(nomeDoCampo: string){
    const campo = this.form.get(nomeDoCampo);
    
    if (campo?.hasError('required')){
      return 'Campo obrigatório';
    }
    
    if (campo?.hasError('minlength')){
      const requiredLength = campo.errors ? campo.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo de ${requiredLength} caracteres`
    }
    
    if (campo?.hasError('maxlength')){
      const requiredLength = campo.errors ? campo.errors['maxlength']['requiredLength'] : 200;
      return `Tamanho máximo de ${requiredLength} caracteres`
    }

    return 'Campo inválido'
  }
}
