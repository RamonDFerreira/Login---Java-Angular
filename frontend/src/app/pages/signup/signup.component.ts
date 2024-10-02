// src/app/pages/signup/signup.component.ts

import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common'; 

interface SignupForm {
  name: FormControl;
  email: FormControl;
  password: FormControl;
  passwordConfirm: FormControl;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ],
  providers: [
    LoginService
  ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignUpComponent {
  signupForm: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService
  ){
    this.signupForm = new FormGroup<SignupForm>({
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
        this.onlyLettersValidator
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email,
        this.emailWithTLDValidator
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)
      ]),
      passwordConfirm: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)
      ]),
    }, { validators: this.passwordMatchValidator });
  }

  onlyLettersValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value as string;
    const regex = /^[A-Za-zÀ-ÿ\s]+$/; // Inclui letras acentuadas e espaços
    if (value && !regex.test(value)) {
      return { onlyLetters: true };
    }
    return null;
  }

  emailWithTLDValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value as string;
    // Regex para verificar se o e-mail termina com um TLD válido
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
    if (value && !regex.test(value)) {
      return { emailTLD: true };
    }
    return null;
  }

  passwordMatchValidator(group: AbstractControl): ValidationErrors | null {
    const password = group.get('password')?.value;
    const passwordConfirm = group.get('passwordConfirm');

    if (password !== passwordConfirm?.value) {
      passwordConfirm?.setErrors({ passwordMismatch: true });
    } else {
      if (passwordConfirm?.hasError('passwordMismatch')) {
        // Remove apenas o erro de mismatch sem afetar outros erros
        const errors = { ...passwordConfirm.errors };
        delete errors['passwordMismatch'];
        if (Object.keys(errors).length === 0) {
          passwordConfirm.setErrors(null);
        } else {
          passwordConfirm.setErrors(errors);
        }
      }
    }

    return null; 
  }

  submit(){
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      return;
    }

    const { name, email, password, passwordConfirm } = this.signupForm.value;

    this.loginService.signup(name, email, password, passwordConfirm).subscribe({
      next: () => {
        this.toastService.success("Cadastro realizado com sucesso!");
        this.router.navigate([""]);
      },
      error: (err) => {
        const errorMessage = err.error?.message || "Erro inesperado! Tente novamente mais tarde";
        this.toastService.error(errorMessage);
      }
    })
  }

  navigate(){
    this.router.navigate([""])
  }
}
