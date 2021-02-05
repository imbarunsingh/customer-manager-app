import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomersComponent } from './customers.component';
import { CardViewComponent } from './card-view/card-view.component';
import { ListViewComponent } from './list-view/list-view.component';
import { MapViewComponent } from './map-view/map-view.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';

const routes: Routes = [  
  {
    path: '', component: CustomersComponent,
    children: [
      { path: '', redirectTo: 'card-view', pathMatch: 'full'}, 
      { path: 'card-view', component: CardViewComponent },
      { path: 'list-view', component: ListViewComponent },
      { path: 'map-view', component: MapViewComponent },
      { path: 'add-customer', component: AddCustomerComponent }     
    ]
  },   
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersRoutingModule { }
