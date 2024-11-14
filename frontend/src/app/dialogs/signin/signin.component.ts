import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { UsersService } from '../../services/users.service';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { SigninDTO } from '../../dtos/authentication/SigninDTO';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
  ],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss',
})
export class SigninComponent {
  signInForm: FormGroup;
  hidePassword = true;

  constructor(
    private fb: FormBuilder,
    private usersService: UsersService,
    private dialogRef: MatDialogRef<SigninComponent>
  ) {
    this.signInForm = this.fb.group({
      login: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() {
    if (this.signInForm.valid) {
      let signinDTO: SigninDTO = new SigninDTO(this.signInForm.value);

      this.usersService.signin(signinDTO).subscribe({
        next: (response) => {
          const authorizationHeader = response.headers.get('Authorization');

          if (authorizationHeader) {
            localStorage.setItem('jwtToken', authorizationHeader);
            this.dialogRef.close({ success: true });
          }
        },
        error: (error) => {
          this.dialogRef.close({ success: false });
        },
      });
    }
  }
}
