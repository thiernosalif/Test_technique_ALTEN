
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly TOKEN_KEY = 'token';
  private readonly API_URL = 'http://localhost:8081/api/v1/ecom';
  public isLoggedIn$ = new BehaviorSubject<boolean>(!!this.getToken());

  constructor(private http: HttpClient, private router: Router) {}


  register(data: { username: string; firstname: string; email: string; password: string }): Observable<any> {
    return this.http.post(`${this.API_URL}/account`, data);
  }

  login(credentials: { email: string; password: string }): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.API_URL}/token`, credentials).pipe(
      tap(response => {
        localStorage.setItem(this.TOKEN_KEY, response.token);
        this.isLoggedIn$.next(true);
      })
    );
  }


  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.isLoggedIn$.next(false);
    this.router.navigate(['/login']);
  }


  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }


  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}




