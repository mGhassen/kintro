import { IEmployee } from 'app/shared/model/employee.model';
import { IJob } from 'app/shared/model/job.model';

export interface IDocument {
    id?: string;
    docName?: string;
    description?: string;
    docpath?: string;
    employee?: IEmployee;
    job?: IJob;
}

export class Document implements IDocument {
    constructor(
        public id?: string,
        public docName?: string,
        public description?: string,
        public docpath?: string,
        public employee?: IEmployee,
        public job?: IJob
    ) {}
}
