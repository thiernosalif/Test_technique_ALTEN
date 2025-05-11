// src/app/services/product.service.ts
import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import {Product} from "../products/data-access/product.model";

/*export interface Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: 'INSTOCK' | 'LOWSTOCK' | 'OUTOFSTOCK';
  rating: number;
  createdAt: number;
  updatedAt: number;
}*/

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly http = inject(HttpClient);
  private readonly API = '/api/v1/ecom/products';  // Utilisation du proxy

  private readonly _products = signal<Product[]>([]);
  public readonly products = this._products.asReadonly();

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.API).pipe(
      tap((products) => this._products.set(products))
    );
  }

  getById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.API}/${id}`);
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.API, product).pipe(
      tap((created) => {
        this._products.update(products => [created, ...products]);
      })
    );
  }

  update(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.API}/${product.id}`, product).pipe(
      tap((updated) => {
        this._products.update(products =>
          products.map(p => p.id === updated.id ? updated : p)
        );
      })
    );
  }

  addToCart(productId: number | null): Observable<void> {
    return this.http.post<void>(`http://localhost:8081/api/v1/ecom/cart/${productId}`, {});
  }


  delete(id: number | null): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`).pipe(
      tap(() => {
        this._products.update(products =>
          products.filter(p => p.id !== id)
        );
      })
    );
  }
}

