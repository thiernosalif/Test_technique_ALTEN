import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Product} from "../products/data-access/product.model";
import {HttpClient} from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class CartService {
  private apiUrl = 'http://localhost:8081/api/v1/ecom/cart';

  constructor(private http: HttpClient) {}

  getCart(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}`);
  }

  removeFromCart(productId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${productId}`);
  }

  addToCart(productId: number): Observable<void> {
    return this.http.post<void>(`http://localhost:8081/api/v1/ecom/cart/${productId}`, {});
  }






}
