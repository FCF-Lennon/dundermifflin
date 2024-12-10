import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './informacion-contacto-jefe.reducer';

export const InformacionContactoJefeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const informacionContactoJefeEntity = useAppSelector(state => state.informacionContactoJefe.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="informacionContactoJefeDetailsHeading">
          <Translate contentKey="dundermifflinApp.informacionContactoJefe.detail.title">InformacionContactoJefe</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{informacionContactoJefeEntity.id}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="dundermifflinApp.informacionContactoJefe.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{informacionContactoJefeEntity.telefono}</dd>
          <dt>
            <span id="tipoFono">
              <Translate contentKey="dundermifflinApp.informacionContactoJefe.tipoFono">Tipo Fono</Translate>
            </span>
          </dt>
          <dd>{informacionContactoJefeEntity.tipoFono}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.informacionContactoJefe.jefe">Jefe</Translate>
          </dt>
          <dd>{informacionContactoJefeEntity.jefe ? informacionContactoJefeEntity.jefe.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/informacion-contacto-jefe" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/informacion-contacto-jefe/${informacionContactoJefeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InformacionContactoJefeDetail;
