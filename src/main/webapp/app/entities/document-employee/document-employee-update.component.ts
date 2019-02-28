import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDocumentEmployee } from 'app/shared/model/document-employee.model';
import { DocumentEmployeeService } from './document-employee.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee';
import { IJob } from 'app/shared/model/job.model';
import { JobService } from 'app/entities/job';

@Component({
    selector: 'jhi-document-employee-update',
    templateUrl: './document-employee-update.component.html'
})
export class DocumentEmployeeUpdateComponent implements OnInit {
    documentEmployee: IDocumentEmployee;
    isSaving: boolean;

    employees: IEmployee[];

    jobs: IJob[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected documentEmployeeService: DocumentEmployeeService,
        protected employeeService: EmployeeService,
        protected jobService: JobService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ documentEmployee }) => {
            this.documentEmployee = documentEmployee;
        });
        this.employeeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEmployee[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEmployee[]>) => response.body)
            )
            .subscribe((res: IEmployee[]) => (this.employees = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.jobService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IJob[]>) => mayBeOk.ok),
                map((response: HttpResponse<IJob[]>) => response.body)
            )
            .subscribe((res: IJob[]) => (this.jobs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.documentEmployee.id !== undefined) {
            this.subscribeToSaveResponse(this.documentEmployeeService.update(this.documentEmployee));
        } else {
            this.subscribeToSaveResponse(this.documentEmployeeService.create(this.documentEmployee));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocumentEmployee>>) {
        result.subscribe((res: HttpResponse<IDocumentEmployee>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmployeeById(index: number, item: IEmployee) {
        return item.id;
    }

    trackJobById(index: number, item: IJob) {
        return item.id;
    }
}
