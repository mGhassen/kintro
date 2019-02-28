/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KintroTestModule } from '../../../test.module';
import { DocumentEmployeeDeleteDialogComponent } from 'app/entities/document-employee/document-employee-delete-dialog.component';
import { DocumentEmployeeService } from 'app/entities/document-employee/document-employee.service';

describe('Component Tests', () => {
    describe('DocumentEmployee Management Delete Component', () => {
        let comp: DocumentEmployeeDeleteDialogComponent;
        let fixture: ComponentFixture<DocumentEmployeeDeleteDialogComponent>;
        let service: DocumentEmployeeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [KintroTestModule],
                declarations: [DocumentEmployeeDeleteDialogComponent]
            })
                .overrideTemplate(DocumentEmployeeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DocumentEmployeeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentEmployeeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
