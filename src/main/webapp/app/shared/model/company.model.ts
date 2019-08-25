import { Moment } from 'moment';
import { IPersonCompanyRelation } from 'app/shared/model/person-company-relation.model';

export interface ICompany {
  id?: number;
  uuid?: string;
  type?: string;
  name?: string;
  shortdescription?: string;
  description?: string;
  foundedon?: Moment;
  closedon?: Moment;
  numemployeesmin?: number;
  numemployeesmax?: number;
  totalfundingusd?: number;
  totalfundingvnd?: number;
  numberofinvestments?: number;
  createdat?: Moment;
  updatedat?: Moment;
  contactemail?: string;
  phonenumber?: string;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  people?: IPersonCompanyRelation[];
  createdById?: number;
  updatedById?: number;
  assignedToId?: number;
}

export const defaultValue: Readonly<ICompany> = {};
