import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './person.reducer';
import { IPerson } from 'app/shared/model/person.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPersonDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PersonDetail extends React.Component<IPersonDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { personEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vnstartupdirApp.person.detail.title">Person</Translate> [<b>{personEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="uuid">
                <Translate contentKey="vnstartupdirApp.person.uuid">Uuid</Translate>
              </span>
            </dt>
            <dd>{personEntity.uuid}</dd>
            <dt>
              <span id="firstname">
                <Translate contentKey="vnstartupdirApp.person.firstname">Firstname</Translate>
              </span>
            </dt>
            <dd>{personEntity.firstname}</dd>
            <dt>
              <span id="lastname">
                <Translate contentKey="vnstartupdirApp.person.lastname">Lastname</Translate>
              </span>
            </dt>
            <dd>{personEntity.lastname}</dd>
            <dt>
              <span id="bornon">
                <Translate contentKey="vnstartupdirApp.person.bornon">Bornon</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={personEntity.bornon} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="diedon">
                <Translate contentKey="vnstartupdirApp.person.diedon">Diedon</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={personEntity.diedon} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdat">
                <Translate contentKey="vnstartupdirApp.person.createdat">Createdat</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={personEntity.createdat} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedat">
                <Translate contentKey="vnstartupdirApp.person.updatedat">Updatedat</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={personEntity.updatedat} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="gender">
                <Translate contentKey="vnstartupdirApp.person.gender">Gender</Translate>
              </span>
            </dt>
            <dd>{personEntity.gender}</dd>
            <dt>
              <span id="title">
                <Translate contentKey="vnstartupdirApp.person.title">Title</Translate>
              </span>
            </dt>
            <dd>{personEntity.title}</dd>
            <dt>
              <span id="homepageurl">
                <Translate contentKey="vnstartupdirApp.person.homepageurl">Homepageurl</Translate>
              </span>
            </dt>
            <dd>{personEntity.homepageurl}</dd>
            <dt>
              <span id="facebookurl">
                <Translate contentKey="vnstartupdirApp.person.facebookurl">Facebookurl</Translate>
              </span>
            </dt>
            <dd>{personEntity.facebookurl}</dd>
            <dt>
              <span id="twitterurl">
                <Translate contentKey="vnstartupdirApp.person.twitterurl">Twitterurl</Translate>
              </span>
            </dt>
            <dd>{personEntity.twitterurl}</dd>
            <dt>
              <span id="linkedinurl">
                <Translate contentKey="vnstartupdirApp.person.linkedinurl">Linkedinurl</Translate>
              </span>
            </dt>
            <dd>{personEntity.linkedinurl}</dd>
            <dt>
              <span id="cityname">
                <Translate contentKey="vnstartupdirApp.person.cityname">Cityname</Translate>
              </span>
            </dt>
            <dd>{personEntity.cityname}</dd>
            <dt>
              <span id="regionname">
                <Translate contentKey="vnstartupdirApp.person.regionname">Regionname</Translate>
              </span>
            </dt>
            <dd>{personEntity.regionname}</dd>
            <dt>
              <span id="countrycode">
                <Translate contentKey="vnstartupdirApp.person.countrycode">Countrycode</Translate>
              </span>
            </dt>
            <dd>{personEntity.countrycode}</dd>
            <dt>
              <Translate contentKey="vnstartupdirApp.person.createdBy">Created By</Translate>
            </dt>
            <dd>{personEntity.createdById ? personEntity.createdById : ''}</dd>
            <dt>
              <Translate contentKey="vnstartupdirApp.person.updatedBy">Updated By</Translate>
            </dt>
            <dd>{personEntity.updatedById ? personEntity.updatedById : ''}</dd>
            <dt>
              <Translate contentKey="vnstartupdirApp.person.assignedTo">Assigned To</Translate>
            </dt>
            <dd>{personEntity.assignedToId ? personEntity.assignedToId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/person" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/person/${personEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ person }: IRootState) => ({
  personEntity: person.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PersonDetail);
