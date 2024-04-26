import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TransactionHistoryDto } from '../dtos/transaction-history-dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {

  private baseUrl = `${environment.apiUrl}/api/history` 

  constructor(private httpClient : HttpClient) { }

  public getHistoryOnRequestId(requestId: number): Observable<TransactionHistoryDto[] | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/${requestId}`;
    return this.httpClient.get<TransactionHistoryDto[]>(requestUrl);
  }
}
