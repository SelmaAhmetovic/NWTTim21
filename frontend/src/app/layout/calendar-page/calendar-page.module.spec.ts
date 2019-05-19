import { CalendarPageModule } from './calendar-page.module';

describe('CalendarPageModule', () => {
    let calendarPageModule: CalendarPageModule;

    beforeEach(() => {
      calendarPageModule = new CalendarPageModule();
    });

    it('should create an instance', () => {
        expect(calendarPageModule).toBeTruthy();
    });
});
