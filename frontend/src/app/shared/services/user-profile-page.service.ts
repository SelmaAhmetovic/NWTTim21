import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../../environments/environment";


@Injectable()
export class UserProfilePageService {

  constructor(private httpClient: HttpClient) {
  }

  getUser(id: any) {
    return this.httpClient.get(
      `${environment.userServiceUrl}/user/` + id);
  }

  updateUser(id: any, body: any) {
    return this.httpClient.get(
      `${environment.userServiceUrl}/user/` + id, body);
  }

}
