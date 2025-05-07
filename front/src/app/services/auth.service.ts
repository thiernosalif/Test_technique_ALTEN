import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_URL = '/api/v1/ecom/token';
  private readonly ACCOUNT_URL = '/api/v1/ecom/account';

  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post<{ token: string }>(this.TOKEN_URL, { email, password }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  register(user: any) {
    return this.http.post(this.ACCOUNT_URL, user);
  }

  logout() {
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
