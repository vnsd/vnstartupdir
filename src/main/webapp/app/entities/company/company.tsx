import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './company.reducer';
import { ICompany } from 'app/shared/model/company.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICompanyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ICompanyState = IPaginationBaseState;

export class Company extends React.Component<ICompanyProps, ICompanyState> {
  state: ICompanyState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    if (this.props.updateSuccess) {
      this.reset();
    }
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => {
      this.getEntities();
    });
  };

  handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
    }
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => {
        this.reset();
      }
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { companyList, match } = this.props;
    return (
      <div>
        <h2 id="company-heading">
          <Translate contentKey="vnstartupdirApp.company.home.title">Companies</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="vnstartupdirApp.company.home.createLabel">Create a new Company</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage - 1 < this.props.links.next}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            {companyList && companyList.length > 0 ? (
              <Table responsive>
                <thead>
                  <tr>
                    <th className="hand" onClick={this.sort('id')}>
                      <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('uuid')}>
                      <Translate contentKey="vnstartupdirApp.company.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('permalink')}>
                      <Translate contentKey="vnstartupdirApp.company.permalink">Permalink</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('name')}>
                      <Translate contentKey="vnstartupdirApp.company.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('alsoknownas')}>
                      <Translate contentKey="vnstartupdirApp.company.alsoknownas">Alsoknownas</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('shortdescription')}>
                      <Translate contentKey="vnstartupdirApp.company.shortdescription">Shortdescription</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('description')}>
                      <Translate contentKey="vnstartupdirApp.company.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('profileimageid')}>
                      <Translate contentKey="vnstartupdirApp.company.profileimageid">Profileimageid</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('primaryrole')}>
                      <Translate contentKey="vnstartupdirApp.company.primaryrole">Primaryrole</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('foundedon')}>
                      <Translate contentKey="vnstartupdirApp.company.foundedon">Foundedon</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('foundedontrustcode')}>
                      <Translate contentKey="vnstartupdirApp.company.foundedontrustcode">Foundedontrustcode</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('closedon')}>
                      <Translate contentKey="vnstartupdirApp.company.closedon">Closedon</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('numemployeesmin')}>
                      <Translate contentKey="vnstartupdirApp.company.numemployeesmin">Numemployeesmin</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('numemployeesmax')}>
                      <Translate contentKey="vnstartupdirApp.company.numemployeesmax">Numemployeesmax</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('totalfundingusd')}>
                      <Translate contentKey="vnstartupdirApp.company.totalfundingusd">Totalfundingusd</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('totalfundingvnd')}>
                      <Translate contentKey="vnstartupdirApp.company.totalfundingvnd">Totalfundingvnd</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('stockexchange')}>
                      <Translate contentKey="vnstartupdirApp.company.stockexchange">Stockexchange</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('stocksymbol')}>
                      <Translate contentKey="vnstartupdirApp.company.stocksymbol">Stocksymbol</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('numberofinvestments')}>
                      <Translate contentKey="vnstartupdirApp.company.numberofinvestments">Numberofinvestments</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('createdat')}>
                      <Translate contentKey="vnstartupdirApp.company.createdat">Createdat</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('updatedat')}>
                      <Translate contentKey="vnstartupdirApp.company.updatedat">Updatedat</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('permalinkaliases')}>
                      <Translate contentKey="vnstartupdirApp.company.permalinkaliases">Permalinkaliases</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('investortype')}>
                      <Translate contentKey="vnstartupdirApp.company.investortype">Investortype</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('contactemail')}>
                      <Translate contentKey="vnstartupdirApp.company.contactemail">Contactemail</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('phonenumber')}>
                      <Translate contentKey="vnstartupdirApp.company.phonenumber">Phonenumber</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('rank')}>
                      <Translate contentKey="vnstartupdirApp.company.rank">Rank</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('primaryimageid')}>
                      <Translate contentKey="vnstartupdirApp.company.primaryimageid">Primaryimageid</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('ownedbyid')}>
                      <Translate contentKey="vnstartupdirApp.company.ownedbyid">Ownedbyid</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('headquartersid')}>
                      <Translate contentKey="vnstartupdirApp.company.headquartersid">Headquartersid</Translate>{' '}
                      <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('acquiredbyid')}>
                      <Translate contentKey="vnstartupdirApp.company.acquiredbyid">Acquiredbyid</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('ipoid')}>
                      <Translate contentKey="vnstartupdirApp.company.ipoid">Ipoid</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('homepageurl')}>
                      <Translate contentKey="vnstartupdirApp.company.homepageurl">Homepageurl</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('facebookurl')}>
                      <Translate contentKey="vnstartupdirApp.company.facebookurl">Facebookurl</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('twitterurl')}>
                      <Translate contentKey="vnstartupdirApp.company.twitterurl">Twitterurl</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('linkedinurl')}>
                      <Translate contentKey="vnstartupdirApp.company.linkedinurl">Linkedinurl</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('cityname')}>
                      <Translate contentKey="vnstartupdirApp.company.cityname">Cityname</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('regionname')}>
                      <Translate contentKey="vnstartupdirApp.company.regionname">Regionname</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th className="hand" onClick={this.sort('countrycode')}>
                      <Translate contentKey="vnstartupdirApp.company.countrycode">Countrycode</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th>
                      <Translate contentKey="vnstartupdirApp.company.owner">Owner</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th>
                      <Translate contentKey="vnstartupdirApp.company.assignee">Assignee</Translate> <FontAwesomeIcon icon="sort" />
                    </th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {companyList.map((company, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${company.id}`} color="link" size="sm">
                          {company.id}
                        </Button>
                      </td>
                      <td>{company.uuid}</td>
                      <td>{company.permalink}</td>
                      <td>{company.name}</td>
                      <td>{company.alsoknownas}</td>
                      <td>{company.shortdescription}</td>
                      <td>{company.description}</td>
                      <td>{company.profileimageid}</td>
                      <td>{company.primaryrole}</td>
                      <td>
                        <TextFormat type="date" value={company.foundedon} format={APP_DATE_FORMAT} />
                      </td>
                      <td>{company.foundedontrustcode}</td>
                      <td>
                        <TextFormat type="date" value={company.closedon} format={APP_DATE_FORMAT} />
                      </td>
                      <td>{company.numemployeesmin}</td>
                      <td>{company.numemployeesmax}</td>
                      <td>{company.totalfundingusd}</td>
                      <td>{company.totalfundingvnd}</td>
                      <td>{company.stockexchange}</td>
                      <td>{company.stocksymbol}</td>
                      <td>{company.numberofinvestments}</td>
                      <td>
                        <TextFormat type="date" value={company.createdat} format={APP_DATE_FORMAT} />
                      </td>
                      <td>
                        <TextFormat type="date" value={company.updatedat} format={APP_DATE_FORMAT} />
                      </td>
                      <td>{company.permalinkaliases}</td>
                      <td>{company.investortype}</td>
                      <td>{company.contactemail}</td>
                      <td>{company.phonenumber}</td>
                      <td>{company.rank}</td>
                      <td>{company.primaryimageid}</td>
                      <td>{company.ownedbyid}</td>
                      <td>{company.headquartersid}</td>
                      <td>{company.acquiredbyid}</td>
                      <td>{company.ipoid}</td>
                      <td>{company.homepageurl}</td>
                      <td>{company.facebookurl}</td>
                      <td>{company.twitterurl}</td>
                      <td>{company.linkedinurl}</td>
                      <td>{company.cityname}</td>
                      <td>{company.regionname}</td>
                      <td>{company.countrycode}</td>
                      <td>{company.ownerId ? company.ownerId : ''}</td>
                      <td>{company.assigneeId ? company.assigneeId : ''}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${company.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.view">View</Translate>
                            </span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${company.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.edit">Edit</Translate>
                            </span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${company.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" />{' '}
                            <span className="d-none d-md-inline">
                              <Translate contentKey="entity.action.delete">Delete</Translate>
                            </span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            ) : (
              <div className="alert alert-warning">
                <Translate contentKey="vnstartupdirApp.company.home.notFound">No Companies found</Translate>
              </div>
            )}
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ company }: IRootState) => ({
  companyList: company.entities,
  totalItems: company.totalItems,
  links: company.links,
  entity: company.entity,
  updateSuccess: company.updateSuccess
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Company);
