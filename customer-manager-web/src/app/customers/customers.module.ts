import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatPaginatorModule } from '@angular/material/paginator';

import { CustomersRoutingModule } from './customers-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CustomersComponent } from './customers.component';
import { CardViewComponent } from './card-view/card-view.component';
import { ListViewComponent } from './list-view/list-view.component';
import { MapViewComponent } from './map-view/map-view.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { CustomerService } from '../core/services/customer.service';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    CustomersComponent,
    CardViewComponent,
    ListViewComponent,
    MapViewComponent,
    AddCustomerComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    CustomersRoutingModule,
    SharedModule,
    MatPaginatorModule
  ],
  providers: [CustomerService]
})
export class CustomersModule { }
