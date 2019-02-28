import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDocumentEmployee } from 'app/shared/model/document-employee.model';
import { DocumentEmployeeService } from './document-employee.service';

@Component({
    selector: 'jhi-document-employee-delete-dialog',
    templateUrl: './document-employee-delete-dialog.component.html'
})
export class DocumentEmployeeDeleteDialogComponent {
    documentEmployee: IDocumentEmployee;

    constructor(
        protected documentEmployeeService: DocumentEmployeeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.documentEmployeeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'documentEmployeeListModification',
                content: 'Deleted an documentEmployee'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-document-employee-delete-popup',
    template: ''
})
export class DocumentEmployeeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ documentEmployee }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DocumentEmployeeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.documentEmployee = documentEmployee;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/document-employee', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/document-employee', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
