import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SigninComponent } from '../../../dialogs/signin/signin.component';
import { UsersService } from '../../../services/users.service';
import { UserComponent } from '../../../dialogs/user/user.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  isLoggedIn: boolean;

  constructor(private dialog: MatDialog, usersService: UsersService) {
    this.isLoggedIn = !!localStorage.getItem('jwtToken');
  }

  openSignInDialog() {
    this.dialog
      .open(SigninComponent, {
        width: '400px',
      })
      .afterClosed()
      .subscribe((result) => {
        if (result?.success) {
          this.isLoggedIn = true;
        }
      });
  }

  openMe() {
    this.dialog.open(UserComponent, {
      width: '400px',
      data: { action: 'me' },
    });
  }

  logout() {
    this.isLoggedIn = false;
    localStorage.removeItem('jwtToken');
  }
}
