import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../core/services/customer.service';
import { Customer } from '../../shared/models/customer';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  customer:Customer = new Customer ();
  submitted: boolean = false;

  //Injecting the service through the constructor
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
    this.addCustomer(this.customer);
  }

  private addCustomer(customer: Customer): any {
    this.customerService.createCustomer(customer)
      .subscribe((any) => { // Since the HTTP call return type is observable, we subscribe
        console.log("returned response ::" + any);
      });
  }

}
