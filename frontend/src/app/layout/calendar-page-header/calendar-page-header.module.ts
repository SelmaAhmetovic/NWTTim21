import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalendarModule } from 'angular-calendar';
import { CalendarPageHeaderComponent } from './calendar-page-header.component';


@NgModule({
    imports: [CommonModule,
              FormsModule,
              CalendarModule
    ],
    declarations: [CalendarPageHeaderComponent]
})
export class CalendarPageHeaderModule {}
