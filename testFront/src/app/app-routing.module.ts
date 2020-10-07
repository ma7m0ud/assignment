import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatementComponent } from './components/statement/statement.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: 'statement', pathMatch: 'full' },
  { path: 'statement', component: StatementComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
