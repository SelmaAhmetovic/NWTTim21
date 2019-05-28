import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import {UserProfilePageService} from "../../shared/services/user-profile-page.service";

@Component({
  selector: 'app-blank-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss'],

  animations: [routerTransition()]
})
export class UserProfilePageComponent implements OnInit {
  user: any;
  firstName: any;
  password: any;
  userName: any;
  lastName: any;
  constructor(private _userProfilePageService: UserProfilePageService)
  {  }

  private init() {
    this._userProfilePageService.getUser('8').subscribe((r: any) => {
      this.user = r.result;
      this.firstName = this.user.firstName;
      this.password = this.user.password;
      this.userName= this.user.userName;
      this.lastName= this.user.lastName;
    }, (error: any) => {
      console.log(error);
    });
  }

  ngOnInit() {
    this.init();
  }
}
