import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { BankDto } from 'src/app/dtos/bank-dto';
import { ExporterDto } from 'src/app/dtos/exporter-dto';
import { RequestDto } from 'src/app/dtos/request-dto';
import { TransactionHistoryDto } from 'src/app/dtos/transaction-history-dto';
import { BankService } from 'src/app/services/bank.service';
import { ExporterService } from 'src/app/services/exporter.service';
import { TransactionHistoryService } from 'src/app/services/transaction-history.service';
import { SubSink } from 'subsink';

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit, OnDestroy {
  private sub = new SubSink();
  public selectedBankName?: string;
  public pendingRequests?: RequestDto[];
  public selectedRequest?: RequestDto;

  public selectedRequestHistory?: TransactionHistoryDto[];

  public declinedByExporterRequests?: RequestDto[];
  public bankDetailsDto: BankDto = new BankDto();
  public exporterDetails?: ExporterDto;
  public requestIdForBankUpdate?: number;
  public isHidden: boolean =true;
  public isHistoryHidden: boolean = true;
  
  constructor(private bankService: BankService,
              private exporterService: ExporterService,
              private transactionHistoryService: TransactionHistoryService) { }


  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  ngOnInit(): void {
  }

  public onSelect(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if(selectedValue == "undefined"){
      return
    }
    this.selectedBankName = selectedValue;
    this.getBankPendingRequests(selectedValue);
    this.getDeclinedRequests(selectedValue);
  }

  public getRequestHistory(requestId: number, showApproval: boolean): void {
      this.isHistoryHidden = false;
      this.selectedRequest = this.pendingRequests!.find( dto => dto.id === requestId);
      this.sub.add(
        this.transactionHistoryService.getHistoryOnRequestId(requestId).subscribe(
          (response: any) => {
            this.selectedRequestHistory = response;
          },
          (errorResponse: HttpErrorResponse ) => {
            alert("Something went wrong.");
          }
        )
      )

      this.getRequestBankDetails(requestId,showApproval);
  }



  public getBankPendingRequests(bankName: string): void {
    this.bankService.getRequestsByBankName(bankName).subscribe(
      (response: any) => {
        this.pendingRequests = response;
        this.resetBankDetails();
      },
      (errorResponse: HttpErrorResponse) => {
        alert(errorResponse.error.message);
      }
    )
  }
  public getRequestBankDetails(requestId: number, showApproval: boolean) : void {
    this.requestIdForBankUpdate= requestId;
    this.isHidden= showApproval;
    this.sub.add(
      this.bankService.getBankDetailsBasedOnRequestId(requestId).subscribe(
        (response: any ) => {
            this.bankDetailsDto= response;
            console.log(this.bankDetailsDto);
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }
  public approveRequestSubmit(approveRequestForm: NgForm): void {
    const formData = approveRequestForm.value;
    this.bankDetailsDto.isApproved = true;
    this.bankDetailsDto.accountNumber = formData.accountNumber;
      this.sub.add(
        this.bankService.updateBankDetailsBasedOnRequestId(this.requestIdForBankUpdate!,this.bankDetailsDto).subscribe(
          (response: any) => {
             alert("Bank approved the request");
             approveRequestForm.reset();
             this.resetBankDetails();
             this.requestIdForBankUpdate = undefined;
          },
          (error: any) => {
            alert("something went wrong");
            approveRequestForm.reset();
          }
        )
      )
  }
  public declineRequestSubmit(declineRequestForm: NgForm): void {
    const formData = declineRequestForm.value;
    this.bankDetailsDto.isApproved = false;
    this.bankDetailsDto.comments = formData.comment;
   
    this.sub.add(
      this.bankService.updateBankDetailsBasedOnRequestId(this.requestIdForBankUpdate!,this.bankDetailsDto).subscribe(
        (response: any) => {
          alert("Bank declined the request");
          declineRequestForm.reset();
          this.resetBankDetails();
          this.requestIdForBankUpdate = undefined;
       },
       (error: any) => {
         alert("something went wrong");
         declineRequestForm.reset();
       }
      )
    )
  }
  public getDeclinedRequests(bankName: string): void {
    this.sub.add(
      this.bankService.getDeclinedRequestsByExporter(bankName).subscribe(
        (response: any) => {
          this.declinedByExporterRequests = response;
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }
  public resetBankDetails(): void {
    this.bankDetailsDto = new BankDto();
  }
  public getRequestExporterDetails(requestId: number): void {
      this.sub.add(
        this.exporterService.getExporterByRequestId(requestId).subscribe(
          (response: any) => {
            this.exporterDetails = response;
          }, 
          (errorResponse: HttpErrorResponse) => {
            alert(errorResponse.error.message);
          }
        )
      )
  }
}
