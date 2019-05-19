import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalendarPageRoutingModule } from './calendar-page-routing.module';
import {CalendarPageComponent} from './calendar-page.component';

@NgModule({
    imports: [CommonModule, CalendarPageRoutingModule],
    declarations: [CalendarPageComponent]
})
export class CalendarPageModule {}
