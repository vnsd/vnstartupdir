import { Moment } from 'moment';
import { IPersonCompanyRelation } from 'app/shared/model/person-company-relation.model';

export interface IPerson {
  id?: number;
  uuid?: string;
  firstname?: string;
  lastname?: string;
  bornon?: Moment;
  diedon?: Moment;
  createdat?: Moment;
  updatedat?: Moment;
  gender?: string;
  title?: string;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  companies?: IPersonCompanyRelation[];
  createdById?: number;
  updatedById?: number;
  assignedToId?: number;
}

export const defaultValue: Readonly<IPerson> = {};
