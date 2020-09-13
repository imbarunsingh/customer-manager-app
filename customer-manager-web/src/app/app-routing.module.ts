import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './customers/customers.component';
import { OrdersComponent } from './orders/orders.component';
import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';
import { CardViewComponent } from './customers/card-view/card-view.component';
import { ListViewComponent } from './customers/list-view/list-view.component';
import { MapViewComponent } from './customers/map-view/map-view.component';
import { AddCustomerComponent } from './customers/add-customer/add-customer.component';

//Order of route is important and it is searched in the order of definition
const routes: Routes = [  
  {
    path: 'customers',
    component: CustomersComponent,
    children: [
      { path: '', redirectTo: 'card-view', pathMatch: 'full' },
      { path: 'card-view', component: CardViewComponent },
      { path: 'list-view', component: ListViewComponent },
      { path: 'map-view', component: MapViewComponent },
      { path: 'add-customer', component: AddCustomerComponent }     
    ]
  },
  { path: 'orders', component: OrdersComponent },
  { path: '', redirectTo: '/customers/card-view', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }