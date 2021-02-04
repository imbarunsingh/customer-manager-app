import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CardsComponent } from './components/cards/cards.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { FormsModule } from '@angular/forms';
import { SharedRoutingModule } from './shared-routing.module';
import { OrdersModule } from '../orders/orders.module';
import { HeaderComponent } from './components/header/header.component';
import { SorterService } from './services/sorter.service';

@NgModule({
  declarations: [PageNotFoundComponent, CardsComponent, HeaderComponent],
  imports: [
    CommonModule,
    FormsModule,
    SharedRoutingModule    
  ],
  providers: [SorterService],
  exports: [CardsComponent, PageNotFoundComponent, HeaderComponent]
})
export class SharedModule { }
