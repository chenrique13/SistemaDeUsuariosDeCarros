import { Component, Inject } from '@angular/core';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { CarDTO } from '../../dtos/cars/CarDTO';

@Component({
  selector: 'app-car',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDialogModule,
  ],
  templateUrl: './car.component.html',
  styleUrl: './car.component.scss',
})
export class CarComponent {
  carForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<CarComponent>,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public data: { car?: CarDTO; action: 'create' | 'edit' | 'delete' }
  ) {
    this.carForm = this.fb.group({
      model: [data.car?.model || '', Validators.required],
      year: [data.car?.year || '', Validators.required],
      licensePlate: [data.car?.licensePlate || '', [Validators.required]],
      color: [data.car?.color || '', [Validators.required]],
    });
  }

  save() {
    this.dialogRef.close({
      action: this.data.action,
      car: { ...this.data.car, ...this.carForm.value },
    });
  }

  delete() {
    this.dialogRef.close({ action: 'delete', car: this.data.car });
  }
}
