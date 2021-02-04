import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export class BaseService {
  baseUrl: string = "https://localhost:8080/api";
  
  constructor() { }

  public handleError(error: any) {
    console.error('server error:', error);
    if (error.error instanceof Error) {
      const errMessage = error.error.message;
      return Observable.throw(errMessage);
      // Use the following instead if using lite-server
      // return Observable.throw(err.text() || 'backend server error');
    }
    return Observable.throw(error || 'Node.js server error');
  }
}
