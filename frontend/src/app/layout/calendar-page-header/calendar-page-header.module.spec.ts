import { CalendarPageHeaderModule } from './calendar-page-header.module';

describe('CalendarPageHeaderModule', () => {
    let calendarPageHeaderModule: CalendarPageHeaderModule;

    beforeEach(() => {
      calendarPageHeaderModule = new CalendarPageHeaderModule();
    });

    it('should create an instance', () => {
        expect(calendarPageHeaderModule).toBeTruthy();
    });
});
