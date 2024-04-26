import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RequestDto } from '../dtos/request-dto';
import { TransactionRequest } from '../dtos/transaction-request';

@Injectable({
  providedIn: 'root'
})
export class ImporterService {
  basedUrl: string = `${environment.apiUrl}/api/v1/request`
  constructor(private httpClient: HttpClient) { }

  public postNewRequest(transactionRequest: TransactionRequest): Observable<RequestDto | HttpErrorResponse> {
    return this.httpClient.post<RequestDto>(this.basedUrl,transactionRequest);
  }
  public getListOfEmails(): Observable< string[] | HttpErrorResponse> {
    const requestUrl = `${this.basedUrl}/mails`
    return this.httpClient.get<string[]>(requestUrl); 
  }
  public getRequestOnEmail(email: string): Observable<RequestDto[] | HttpErrorResponse> {
    const requestUrl = `${this.basedUrl}/by/${email}`
    return this.httpClient.get<RequestDto[]>(requestUrl);
  }
}
