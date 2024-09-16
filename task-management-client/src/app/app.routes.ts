import { Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { TaskManagementComponent } from './pages/task-management/task-management.component';

export const routes: Routes = [
    {path:'', component:RegisterComponent},
    {path:'tasks', component:TaskManagementComponent},
];

