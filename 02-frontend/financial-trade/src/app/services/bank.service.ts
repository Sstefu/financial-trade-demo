import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RequestDto } from '../dtos/request-dto';
import { BankDto } from '../dtos/bank-dto';

@Injectable({
  providedIn: 'root'
})
export class BankService {
  baseUrl = `${environment.apiUrl}/api/v1/bank`
  constructor(private httpClient: HttpClient) { }

  public getRequestsByBankName(bankName: string): Observable<RequestDto[] | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/name/${bankName}`;
    return this.httpClient.get<RequestDto[]>(requestUrl);
  }

  public getBankDetailsBasedOnRequestId(requestId: number): Observable<BankDto | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/request/${requestId}`;
    return this.httpClient.get<BankDto>(requestUrl);
  }
  public updateBankDetailsBasedOnRequestId(requestId: number, bankDetails: BankDto) {
    const requestUrl = `${this.baseUrl}/request/${requestId}`;
    return this.httpClient.put<any>(requestUrl,bankDetails);
  }
  public getDeclinedRequestsByExporter(bankName: string): Observable<RequestDto[] | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/name/declined/${bankName}`;
    return this.httpClient.get<RequestDto[]>(requestUrl);
  }
}
