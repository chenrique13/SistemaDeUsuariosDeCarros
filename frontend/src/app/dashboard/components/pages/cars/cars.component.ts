import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { CarDTO } from '../../../../dtos/cars/CarDTO';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { CarsService } from '../../../../services/cars.service';
import { SaveUpdateCarDTO } from '../../../../dtos/cars/SaveUpdateCarDTO';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CarComponent } from '../../../../dialogs/car/car.component';

@Component({
  selector: 'app-cars',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './cars.component.html',
  styleUrl: './cars.component.scss',
})
export class CarsComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'id',
    'model',
    'year',
    'licensePlate',
    'color',
    'actions',
  ];

  dataSource = new MatTableDataSource<CarDTO>();

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private carsService: CarsService, public dialog: MatDialog) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit() {
    this.carsService.getCars().subscribe((cars) => {
      this.dataSource.data = cars;
    });
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  openDialog(
    action: 'create' | 'edit' | 'delete' = 'create',
    car?: CarDTO
  ): void {
    const dialogRef = this.dialog.open(CarComponent, {
      width: '400px',
      maxHeight: '80vh',
      data: { car: car, action: action },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result.action === 'create') {
          this.createCar(result.car);
        } else if (result.action === 'edit') {
          this.updateCar(result.car);
        } else if (result.action === 'delete') {
          this.deleteCar(result.car);
        }
      }
    });
  }

  createCar(car: CarDTO) {
    let saveUpdateCarDTO: SaveUpdateCarDTO = new SaveUpdateCarDTO(car);

    this.carsService.createCar(saveUpdateCarDTO).subscribe((response) => {
      this.dataSource.data.push(response);

      this.dataSource._updateChangeSubscription();
    });
  }

  updateCar(car: CarDTO) {
    let saveUpdateCarDTO: SaveUpdateCarDTO = new SaveUpdateCarDTO(car);

    this.carsService
      .updateCar(car.id, saveUpdateCarDTO)
      .subscribe((updatedCar) => {
        const index = this.dataSource.data.findIndex(
          (u) => u.id === updatedCar.id
        );

        if (index !== -1) {
          this.dataSource.data[index] = updatedCar;
          this.dataSource = new MatTableDataSource(this.dataSource.data);
        }
      });
  }

  deleteCar(car: CarDTO) {
    this.carsService.deleteCar(car.id).subscribe(() => {
      this.dataSource.data = this.dataSource.data.filter(
        (u) => u.id !== car.id
      );
    });
  }
}
