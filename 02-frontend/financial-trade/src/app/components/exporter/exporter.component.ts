import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ExporterDto } from 'src/app/dtos/exporter-dto';
import { RequestDto } from 'src/app/dtos/request-dto';
import { ExporterService } from 'src/app/services/exporter.service';
import { SubSink } from 'subsink';

@Component({
  selector: 'app-exporter',
  templateUrl: './exporter.component.html',
  styleUrls: ['./exporter.component.css']
})
export class ExporterComponent implements OnInit, OnDestroy {
  public sub = new SubSink();
  public bankApprovedRequests?: RequestDto[];
  public isHidden: boolean = true;
  public requestIdForExporterUpdate?: number;
  constructor(private exporterService: ExporterService) { }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  ngOnInit(): void {
  }

  public onSelect(event: Event ): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if(selectedValue == "undefined"){
      return
    }
    this.getBankApprovedRequests(selectedValue);
  
  }
  public getBankApprovedRequests(exporterName: string): void  {
    this.sub.add(
      this.exporterService.getBankApprovedRequestsByName(exporterName).subscribe(
        (response: any) => {
          this.bankApprovedRequests = response;
        },
        (errorResponse: HttpErrorResponse) => {
          alert(errorResponse.error.message);
        }
      )
    )
  }
  public enableExporterEdits(requestId: number): void {
    this.isHidden = false;
    this.requestIdForExporterUpdate=requestId;
  }
  public approveRequestSubmit(approveRequest: NgForm): void {
      const formData = approveRequest.value;
      let exporterDto = new ExporterDto();
      exporterDto.isApproved= true;
      exporterDto.price = formData.price;
      exporterDto.estimationDate = formData.estimationDate;
      
      this.sub.add(
        this.exporterService.updateExporterApprovalBasedOnRequestId(this.requestIdForExporterUpdate!,exporterDto).subscribe(
          (response: any) => {
            alert(`Request approved by exporter`);
            this.requestIdForExporterUpdate = undefined;
            approveRequest.reset();
          },
          (errorResponse: HttpErrorResponse) => {
            alert(errorResponse.error.message);
            this.requestIdForExporterUpdate = undefined;
            approveRequest.reset();
          }
        )
      )
  }
  public declineRequestSubmit(declineRequest: NgForm): void {
      const formData =  declineRequest.value;
      let exporterDto = new ExporterDto();
      exporterDto.isApproved = false;
      exporterDto.comments = formData.comment;

      this.sub.add(
        this.exporterService.updateExporterApprovalBasedOnRequestId(this.requestIdForExporterUpdate!,exporterDto).subscribe(
          (response: any) => {
            alert(`Request declined by exporter`);
            this.requestIdForExporterUpdate = undefined;
            declineRequest.reset();
          },
          (errorResponse: HttpErrorResponse) => {
            alert(errorResponse.error.message);
            this.requestIdForExporterUpdate = undefined;
            declineRequest.reset();
          }
        )
      )
  }

}
