import { UserProfilePageModule } from './user-profile-page.module';

describe('UserProfilePageModule', () => {
    let userProfilePageModule: UserProfilePageModule;

    beforeEach(() => {
      userProfilePageModule = new UserProfilePageModule();
    });

    it('should create an instance', () => {
        expect(userProfilePageModule).toBeTruthy();
    });
});
