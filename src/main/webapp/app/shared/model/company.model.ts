import { Moment } from 'moment';

export interface ICompany {
  id?: number;
  uuid?: string;
  permalink?: string;
  name?: string;
  alsoknownas?: string;
  shortdescription?: string;
  description?: string;
  profileimageid?: number;
  primaryrole?: string;
  foundedon?: Moment;
  foundedontrustcode?: number;
  closedon?: Moment;
  numemployeesmin?: number;
  numemployeesmax?: number;
  totalfundingusd?: number;
  totalfundingvnd?: number;
  stockexchange?: string;
  stocksymbol?: string;
  numberofinvestments?: number;
  createdat?: Moment;
  updatedat?: Moment;
  permalinkaliases?: string;
  investortype?: string;
  contactemail?: string;
  phonenumber?: string;
  rank?: number;
  primaryimageid?: number;
  ownedbyid?: number;
  headquartersid?: number;
  acquiredbyid?: number;
  ipoid?: number;
  homepageurl?: string;
  facebookurl?: string;
  twitterurl?: string;
  linkedinurl?: string;
  cityname?: string;
  regionname?: string;
  countrycode?: string;
  ownerId?: number;
  assigneeId?: number;
}

export const defaultValue: Readonly<ICompany> = {};
