import { Moment } from 'moment';
import { IEmployee } from 'app/shared/model/employee.model';
import { IDocumentEmployee } from 'app/shared/model/document-employee.model';

export interface IJob {
    id?: string;
    jobTitle?: string;
    description?: string;
    startDate?: Moment;
    endDate?: Moment;
    employee?: IEmployee;
    documentEmployees?: IDocumentEmployee[];
}

export class Job implements IJob {
    constructor(
        public id?: string,
        public jobTitle?: string,
        public description?: string,
        public startDate?: Moment,
        public endDate?: Moment,
        public employee?: IEmployee,
        public documentEmployees?: IDocumentEmployee[]
    ) {}
}
