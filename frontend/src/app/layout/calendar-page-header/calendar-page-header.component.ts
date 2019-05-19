import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-calendar-page-header',
  templateUrl: './calendar-page-header.component.html'
})
export class CalendarPageHeaderComponent {
  @Input() view: string;

  @Input() viewDate: Date;

  @Input() locale: string = 'en';

  @Output() viewChange: EventEmitter<string> = new EventEmitter();

  @Output() viewDateChange: EventEmitter<Date> = new EventEmitter();
}
