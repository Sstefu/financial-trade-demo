import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RequestDto } from 'src/app/dtos/request-dto';
import { TransactionHistoryDto } from 'src/app/dtos/transaction-history-dto';

import { TransactionRequest } from 'src/app/dtos/transaction-request';
import { ImporterService } from 'src/app/services/importer.service';
import { TransactionHistoryService } from 'src/app/services/transaction-history.service';
import { SubSink } from 'subsink';

@Component({
  selector: 'app-importer',
  templateUrl: './importer.component.html',
  styleUrls: ['./importer.component.css']
})
export class ImporterComponent implements OnInit, OnDestroy {

  private sub = new SubSink();
  public listOfEmails!: string[];
  public listOfRequests?: RequestDto[];
  public isHidden : boolean = true;
  public transactionHistory?: TransactionHistoryDto[];
  constructor(private service: ImporterService,
              private transactionService: TransactionHistoryService) { }


  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  ngOnInit(): void {
    this.sub.add(
      this.service.getListOfEmails().subscribe(
        (response: any ) => {
          this.listOfEmails = response;
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }

  public onSubmit(requestForm: NgForm): void {
    const formData= requestForm.value;

    let requestDto = new TransactionRequest();
    
    requestDto.amount = formData.amount;
    requestDto.bankAccountNumber = formData.bankAccountNumber;
    requestDto.bankName = formData.bankName;
    requestDto.deliveryAddress = formData.deliveryAddress;
    requestDto.email = formData.email;
    requestDto.exporterName = formData.exporterName;
    requestDto.goods = formData.goods;
    requestDto.phone = formData.phone;

    this.sub.add(
      this.service.postNewRequest(requestDto).subscribe(
        (response: any) => {
          console.log(response);
          requestForm.reset();
          alert(`Your request was made and will be treated by the selected bank and exporter`);
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }
  public onSelectEmail(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue === "undefined") {
      return;
    }
    this.getRequestsForSelectedEmail(selectedValue);
    this.isHidden=true;
  }

  public getRequestsForSelectedEmail(email: string) {
    this.sub.add(
      this.service.getRequestOnEmail(email).subscribe(
        (response: any) => {
          this.listOfRequests = response;
        },
        (errorResponse: HttpErrorResponse ) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }

  public showRequestHistory(requestId: number): void {
    this.isHidden = false;
    this.sub.add(
      this.transactionService.getHistoryOnRequestId(requestId).subscribe(
        (response: any) => {
          this.transactionHistory = response;
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse);
        }
      )
    )

  }

}
