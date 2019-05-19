import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CalendarPageHeaderComponent } from './calendar-page-header.component';

const routes: Routes = [
    {
        path: '',
        component: CalendarPageHeaderComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CalendarPageHeaderRoutingModule {}
