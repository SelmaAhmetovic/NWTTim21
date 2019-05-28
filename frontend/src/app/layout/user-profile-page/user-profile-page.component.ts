import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import {UserProfilePageService} from "../../shared/services/user-profile-page.service";
import {User} from "../../shared";

@Component({
  selector: 'app-blank-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss'],

  animations: [routerTransition()]
})
export class UserProfilePageComponent implements OnInit {
  currentUser: any;
  firstName: any;
  password: any;
  userName: any;
  lastName: any;

  constructor(private _userProfilePageService: UserProfilePageService)
  {  }

  private init() {
    this._userProfilePageService.getUser('8').subscribe((r: any) => {
      this.currentUser = r.result;
      this.firstName = this.currentUser.firstName;
      this.lastName= this.currentUser.lastName;
      this.userName= this.currentUser.userName;
      this.password = this.currentUser.password;
    }, (error: any) => {
      console.log(error);
    });
  }

  updateProfile(firstName: any, lastName: any, userName: any, password: any) {
    var user = new User();
/*    user.id=this.currentUser.id;*/
    user.firstName=firstName;
    user.lastName=lastName;
    user.userName=userName;
    user.password=password;
    user.roleNames = ["admin"];
    this._userProfilePageService.updateUser(this.currentUser.id,user).subscribe((r: any) => {
      this.currentUser = r.result;
      this.firstName = this.currentUser.firstName;
      this.lastName= this.currentUser.lastName;
      this.userName= this.currentUser.userName;
      this.password = this.currentUser.password;

    }, (error: any) => {
      console.log(error);
    });
  }

  ngOnInit() {
    this.init();
  }
}
