import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RequestDto } from '../dtos/request-dto';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ExporterDto } from '../dtos/exporter-dto';

@Injectable({
  providedIn: 'root'
})
export class ExporterService {
  public baseUrl = `${environment.apiUrl}/api/v1/exporter`
  constructor(private httpClient: HttpClient) { }

  public getBankApprovedRequestsByName(name: string): Observable<RequestDto[] | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/approved/${name}`;
    return this.httpClient.get<RequestDto[]>(requestUrl);
  }
  public updateExporterApprovalBasedOnRequestId(requestId: number, requestDto: RequestDto) {
    const requestUrl = `${this.baseUrl}/${requestId}`;
    return this.httpClient.put<any>(requestUrl,requestDto);
  }
  public getExporterByRequestId(requestId: number): Observable<ExporterDto | HttpErrorResponse> {
    const requestUrl = `${this.baseUrl}/request/${requestId}`;
    return this.httpClient.get<ExporterDto>(requestUrl);
  }
}
