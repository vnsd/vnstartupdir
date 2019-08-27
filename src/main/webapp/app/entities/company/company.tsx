import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
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
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { companyList, match, totalItems } = this.props;
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
          {companyList && companyList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('name')}>
                    <Translate contentKey="vnstartupdirApp.company.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('foundedon')}>
                    <Translate contentKey="vnstartupdirApp.company.foundedon">Founded On</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('totalfundingusd')}>
                    <Translate contentKey="vnstartupdirApp.company.totalfundingusd">Total funding ($)</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('contactemail')}>
                    <Translate contentKey="vnstartupdirApp.company.contactemail">Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phonenumber')}>
                    <Translate contentKey="vnstartupdirApp.company.phonenumber">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('homepageurl')}>
                    <Translate contentKey="vnstartupdirApp.company.homepageurl">Web Site</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('facebookurl')}>
                    <Translate contentKey="vnstartupdirApp.company.facebookurl">Facebook</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('cityname')}>
                    <Translate contentKey="vnstartupdirApp.company.cityname">City</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="vnstartupdirApp.company.assignedTo">Assigned To</Translate> <FontAwesomeIcon icon="sort" />
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
                    <td>{company.name}</td>
                    <td>
                      <TextFormat type="date" value={company.foundedon} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{company.totalfundingusd}</td>
                    <td>{company.contactemail}</td>
                    <td>{company.phonenumber}</td>
                    <td>{company.homepageurl}</td>
                    <td>{company.facebookurl}</td>
                    <td>{company.cityname}</td>
                    <td>{company.assignedToId ? company.assignedToId : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${company.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                        </Button>
                        <Button tag={Link} to={`${match.url}/${company.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                        </Button>
                        <Button tag={Link} to={`${match.url}/${company.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
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
        </div>
        <div className={companyList && companyList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={this.state.activePage} total={totalItems} itemsPerPage={this.state.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={this.state.activePage}
              onSelect={this.handlePagination}
              maxButtons={5}
              itemsPerPage={this.state.itemsPerPage}
              totalItems={this.props.totalItems}
            />
          </Row>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ company }: IRootState) => ({
  companyList: company.entities,
  totalItems: company.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Company);
