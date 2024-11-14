import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { UserDTO } from '../../../../dtos/users/UserDTO';
import { UsersService } from '../../../../services/users.service';
import { UserComponent } from '../../../../dialogs/user/user.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { SaveUserDTO } from '../../../../dtos/users/SaveUserDTO';
import { UpdateUserDTO } from '../../../../dtos/users/UpdateUserDTO';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
})
export class UsersComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'email',
    'birthday',
    'login',
    'phone',
    'actions',
  ];

  dataSource = new MatTableDataSource<UserDTO>();

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usersService: UsersService, public dialog: MatDialog) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit() {
    this.usersService.getUsers().subscribe((users) => {
      this.dataSource.data = users;
    });
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  openDialog(
    action: 'create' | 'edit' | 'delete' = 'create',
    user?: UserDTO
  ): void {
    const dialogRef = this.dialog.open(UserComponent, {
      width: '400px',
      maxHeight: '80vh',
      data: { user: user, action: action },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result.action === 'create') {
          this.createUser(result.user);
        } else if (result.action === 'edit') {
          this.updateUser(result.user);
        } else if (result.action === 'delete') {
          this.deleteUser(result.user);
        }
      }
    });
  }

  createUser(user: UserDTO) {
    let saveUserDTO: SaveUserDTO = new SaveUserDTO(user);

    this.usersService.createUser(saveUserDTO).subscribe((response) => {
      this.dataSource.data.push(response);

      this.dataSource._updateChangeSubscription();
    });
  }

  updateUser(user: UserDTO) {
    let updateUserDTO: UpdateUserDTO = new UpdateUserDTO(user);

    this.usersService.updateUser(user.id, updateUserDTO).subscribe((updatedUser) => {
      const index = this.dataSource.data.findIndex(
        (u) => u.id === updatedUser.id
      );
      
      if (index !== -1) {
        this.dataSource.data[index] = updatedUser;
        this.dataSource = new MatTableDataSource(this.dataSource.data);
      }
    });
  }

  deleteUser(user: UserDTO) {
    this.usersService.deleteUser(user.id).subscribe(() => {
      this.dataSource.data = this.dataSource.data.filter(
        (u) => u.id !== user.id
      );
    });
  }
}
