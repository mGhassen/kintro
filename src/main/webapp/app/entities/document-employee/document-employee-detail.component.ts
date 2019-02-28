import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDocumentEmployee } from 'app/shared/model/document-employee.model';

@Component({
    selector: 'jhi-document-employee-detail',
    templateUrl: './document-employee-detail.component.html'
})
export class DocumentEmployeeDetailComponent implements OnInit {
    documentEmployee: IDocumentEmployee;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ documentEmployee }) => {
            this.documentEmployee = documentEmployee;
        });
    }

    previousState() {
        window.history.back();
    }
}
