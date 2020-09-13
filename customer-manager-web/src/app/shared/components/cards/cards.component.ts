import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {
  @Input()
  header: string;
  @Input()
  title: string;
  @Input()
  linkText: string;
  @Input()
  linkUrl: string;
  @Input()
  gender: String;

  constructor() { }

  ngOnInit(): void {
  }

}
