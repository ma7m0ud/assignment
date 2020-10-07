import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'testFront';
  isLoggedIn = false;
  constructor(private tokenStorageService: TokenStorageService) { }
  

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }
  
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
