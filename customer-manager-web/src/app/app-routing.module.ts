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
  { path: '', pathMatch: 'full', redirectTo: '/customers/card-view'},
  { path: 'customers', loadChildren: './customers/customers.module#CustomersModule'},  
  { path: 'orders', loadChildren: './orders/orders.module#OrdersModule'},
  { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false, enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }