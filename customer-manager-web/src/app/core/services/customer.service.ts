import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../../shared/models/customer';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { BaseService } from './base.service';

@Injectable()
export class CustomerService extends BaseService {

  //http client injected here as dependency
  constructor(private http: HttpClient) {
    super();
  }

  getCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.baseUrl + "/v1/customers")
      .pipe(
        catchError(this.handleError)
      );
  }

  createCustomer(customer: Customer): Observable<any> {
    return this.http.post(this.baseUrl + "/v1/customer", customer);
  }
}
