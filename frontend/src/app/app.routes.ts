import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UsersComponent } from './dashboard/components/pages/users/users.component';
import { CarsComponent } from './dashboard/components/pages/cars/cars.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard/users',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    redirectTo: 'dashboard/users',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: 'users',
        component: UsersComponent,
      },
      {
        path: 'cars',
        component: CarsComponent,
        canActivate: [authGuard],
      },
    ],
  },
];
