import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './person-company-relation.reducer';
import { IPersonCompanyRelation } from 'app/shared/model/person-company-relation.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPersonCompanyRelationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PersonCompanyRelationDetail extends React.Component<IPersonCompanyRelationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { personCompanyRelationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vnstartupdirApp.personCompanyRelation.detail.title">PersonCompanyRelation</Translate> [
            <b>{personCompanyRelationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="relationCode">
                <Translate contentKey="vnstartupdirApp.personCompanyRelation.relationCode">Relation Code</Translate>
              </span>
            </dt>
            <dd>{personCompanyRelationEntity.relationCode}</dd>
            <dt>
              <Translate contentKey="vnstartupdirApp.personCompanyRelation.company">Company</Translate>
            </dt>
            <dd>{personCompanyRelationEntity.company ? personCompanyRelationEntity.company.name : ''}</dd>
            <dt>
              <Translate contentKey="vnstartupdirApp.personCompanyRelation.person">Person</Translate>
            </dt>
            <dd>{personCompanyRelationEntity.person ? personCompanyRelationEntity.person.firstname : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/person-company-relation" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/person-company-relation/${personCompanyRelationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ personCompanyRelation }: IRootState) => ({
  personCompanyRelationEntity: personCompanyRelation.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PersonCompanyRelationDetail);
