import { HttpHeaders } from "@angular/common/http";

export const environment = {
  production: false,
  backendHost: "http://localhost:8080"
};

export const headers = new HttpHeaders({
  'Authorization': 'Basic ' + btoa('string:string')
});

