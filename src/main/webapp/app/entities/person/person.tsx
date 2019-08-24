import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './person.reducer';
import { IPerson } from 'app/shared/model/person.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPersonProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPersonState = IPaginationBaseState;

export class Person extends React.Component<IPersonProps, IPersonState> {
  state: IPersonState = {
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
    const { personList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="person-heading">
          <Translate contentKey="vnstartupdirApp.person.home.title">People</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="vnstartupdirApp.person.home.createLabel">Create a new Person</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {personList && personList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('uuid')}>
                    <Translate contentKey="vnstartupdirApp.person.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('permalink')}>
                    <Translate contentKey="vnstartupdirApp.person.permalink">Permalink</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('firstname')}>
                    <Translate contentKey="vnstartupdirApp.person.firstname">Firstname</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('lastname')}>
                    <Translate contentKey="vnstartupdirApp.person.lastname">Lastname</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('alsoknownas')}>
                    <Translate contentKey="vnstartupdirApp.person.alsoknownas">Alsoknownas</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bio')}>
                    <Translate contentKey="vnstartupdirApp.person.bio">Bio</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('profileimageid')}>
                    <Translate contentKey="vnstartupdirApp.person.profileimageid">Profileimageid</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('roleinvestor')}>
                    <Translate contentKey="vnstartupdirApp.person.roleinvestor">Roleinvestor</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bornon')}>
                    <Translate contentKey="vnstartupdirApp.person.bornon">Bornon</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bornontrustcode')}>
                    <Translate contentKey="vnstartupdirApp.person.bornontrustcode">Bornontrustcode</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('diedon')}>
                    <Translate contentKey="vnstartupdirApp.person.diedon">Diedon</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('createdat')}>
                    <Translate contentKey="vnstartupdirApp.person.createdat">Createdat</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('updatedat')}>
                    <Translate contentKey="vnstartupdirApp.person.updatedat">Updatedat</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('permalinkaliases')}>
                    <Translate contentKey="vnstartupdirApp.person.permalinkaliases">Permalinkaliases</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('gender')}>
                    <Translate contentKey="vnstartupdirApp.person.gender">Gender</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('rank')}>
                    <Translate contentKey="vnstartupdirApp.person.rank">Rank</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('primaryaffiliationid')}>
                    <Translate contentKey="vnstartupdirApp.person.primaryaffiliationid">Primaryaffiliationid</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('primarylocationid')}>
                    <Translate contentKey="vnstartupdirApp.person.primarylocationid">Primarylocationid</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('primaryimageid')}>
                    <Translate contentKey="vnstartupdirApp.person.primaryimageid">Primaryimageid</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('title')}>
                    <Translate contentKey="vnstartupdirApp.person.title">Title</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('homepageurl')}>
                    <Translate contentKey="vnstartupdirApp.person.homepageurl">Homepageurl</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('facebookurl')}>
                    <Translate contentKey="vnstartupdirApp.person.facebookurl">Facebookurl</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('twitterurl')}>
                    <Translate contentKey="vnstartupdirApp.person.twitterurl">Twitterurl</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('linkedinurl')}>
                    <Translate contentKey="vnstartupdirApp.person.linkedinurl">Linkedinurl</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('cityname')}>
                    <Translate contentKey="vnstartupdirApp.person.cityname">Cityname</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('regionname')}>
                    <Translate contentKey="vnstartupdirApp.person.regionname">Regionname</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('countrycode')}>
                    <Translate contentKey="vnstartupdirApp.person.countrycode">Countrycode</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {personList.map((person, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${person.id}`} color="link" size="sm">
                        {person.id}
                      </Button>
                    </td>
                    <td>{person.uuid}</td>
                    <td>{person.permalink}</td>
                    <td>{person.firstname}</td>
                    <td>{person.lastname}</td>
                    <td>{person.alsoknownas}</td>
                    <td>{person.bio}</td>
                    <td>{person.profileimageid}</td>
                    <td>{person.roleinvestor ? 'true' : 'false'}</td>
                    <td>
                      <TextFormat type="date" value={person.bornon} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{person.bornontrustcode}</td>
                    <td>
                      <TextFormat type="date" value={person.diedon} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={person.createdat} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={person.updatedat} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{person.permalinkaliases}</td>
                    <td>{person.gender}</td>
                    <td>{person.rank}</td>
                    <td>{person.primaryaffiliationid}</td>
                    <td>{person.primarylocationid}</td>
                    <td>{person.primaryimageid}</td>
                    <td>{person.title}</td>
                    <td>{person.homepageurl}</td>
                    <td>{person.facebookurl}</td>
                    <td>{person.twitterurl}</td>
                    <td>{person.linkedinurl}</td>
                    <td>{person.cityname}</td>
                    <td>{person.regionname}</td>
                    <td>{person.countrycode}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${person.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${person.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${person.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="vnstartupdirApp.person.home.notFound">No People found</Translate>
            </div>
          )}
        </div>
        <div className={personList && personList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ person }: IRootState) => ({
  personList: person.entities,
  totalItems: person.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Person);
