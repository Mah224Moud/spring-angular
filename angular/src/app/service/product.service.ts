import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  postProduct(product: any): Observable<any> {
    return this.http.post(URL + "/api/product", product);
  }

  getAllProducts(): Observable<any> {
    return this.http.get(URL + "/api/products")
  }
}
