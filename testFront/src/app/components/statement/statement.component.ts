import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { searchStatement } from 'src/app/domain/searchStatement';
import { Statement } from 'src/app/domain/statement';
import { StatementService } from 'src/app/services/statement.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent implements OnInit {
  statements: Statement[];
  isLoggedIn = false;
  isUser = false;
  roles: string[] = [];
  searchStatement: searchStatement={
    accountType:""
    ,accountNumber:""
    ,fromDate:""
    ,toDate:""
    ,fromAmount:""
    ,toAmount:""
  };
  constructor(private router:Router,public statementService:StatementService,private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      if(this.roles.includes("USER_ROLE")){
        this.isUser=true;
      }
    }else{
      this.router.navigate(['/login']);
    }
  }
  filter(): void{
    this.statementService.readAll(this.searchStatement)
    .subscribe(
      statements => {
        this.statements = statements;
        console.log(statements);
      },
      error => {
        console.log(error);
      });
  }
  
}
