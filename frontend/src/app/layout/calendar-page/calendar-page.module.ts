import { NgModule } from '@angular/core';

import { CalendarPageRoutingModule } from './calendar-page-routing.module';
import {CalendarPageComponent} from './calendar-page.component';
import { PageHeaderModule } from './../../shared/modules/page-header/page-header.module';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarService } from './calendar-page.service';


@NgModule({
    imports: [CommonModule,
              CalendarPageRoutingModule,
              PageHeaderModule,
              FormsModule,
              NgbModalModule,
              FlatpickrModule.forRoot(),
              CalendarModule.forRoot({
                provide: DateAdapter,
                useFactory: adapterFactory
              })
    ],
    declarations: [CalendarPageComponent],
    providers: [CalendarService]
})
export class CalendarPageModule {}
