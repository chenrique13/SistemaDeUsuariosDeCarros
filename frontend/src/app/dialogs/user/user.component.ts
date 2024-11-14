import { Component, Inject } from '@angular/core';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import { UserDTO } from '../../dtos/users/UserDTO';
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
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { UsersService } from '../../services/users.service';
import { UserInfoDTO } from '../../dtos/users/UserInfoDTO';
import { CarDTO } from '../../dtos/cars/CarDTO';

@Component({
  selector: 'app-user',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDialogModule,
    MatDatepickerModule,
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss',
})
export class UserComponent {
  userForm: FormGroup;
  hidePassword = true;
  cars: CarDTO[] = [];

  constructor(
    public dialogRef: MatDialogRef<UserComponent>,
    private fb: FormBuilder,
    private usersService: UsersService,
    @Inject(MAT_DIALOG_DATA)
    public data: { user?: UserDTO; action: 'create' | 'edit' | 'delete' | 'me' }
  ) {
    this.userForm = this.fb.group({
      firstName: [data.user?.firstName || '', Validators.required],
      lastName: [data.user?.lastName || '', Validators.required],
      email: [data.user?.email || '', [Validators.required, Validators.email]],
      birthday: [data.user?.birthday || '', [Validators.required]],
      login: [data.user?.login || '', [Validators.required]],
      password: ['', [Validators.required]],
      phone: [data.user?.phone || '', Validators.required],
      createdAt: [''],
      lastLogin: [''],
    });

    if (data.action === 'me') {
      usersService.me().subscribe((response: UserInfoDTO) => {
        this.userForm.patchValue({
          firstName: response.firstName,
          lastName: response.lastName,
          email: response.email,
          birthday: response.birthday,
          login: response.login,
          phone: response.phone,
          createdAt: response.createdAt,
          lastLogin: response.lastLogin,
        });

        this.cars = response.cars;
      });

      this.userForm.disable();
    }
  }

  save() {
    this.dialogRef.close({
      action: this.data.action,
      user: { ...this.data.user, ...this.userForm.value },
    });
  }

  delete() {
    this.dialogRef.close({ action: 'delete', user: this.data.user });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
