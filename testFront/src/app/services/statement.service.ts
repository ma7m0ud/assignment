import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseURL = 'http://localhost:8080/api/statement';

@Injectable({
  providedIn: 'root'
})
export class StatementService {

  constructor(private httpClient: HttpClient) { }

  readAll(searchStatment): Observable<any> {
    return this.httpClient.post(baseURL, searchStatment);
  }
}
