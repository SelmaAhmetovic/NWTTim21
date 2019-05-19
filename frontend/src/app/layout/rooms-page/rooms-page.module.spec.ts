import { RoomsPageModule } from './rooms-page.module';

describe('BlankPageModule', () => {
    let roomsPageModule: RoomsPageModule;

    beforeEach(() => {
      roomsPageModule = new RoomsPageModule();
    });

    it('should create an instance', () => {
        expect(roomsPageModule).toBeTruthy();
    });
});
