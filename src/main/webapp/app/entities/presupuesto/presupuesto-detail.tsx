import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './presupuesto.reducer';

export const PresupuestoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const presupuestoEntity = useAppSelector(state => state.presupuesto.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="presupuestoDetailsHeading">
          <Translate contentKey="dundermifflinApp.presupuesto.detail.title">Presupuesto</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{presupuestoEntity.id}</dd>
          <dt>
            <span id="presupuestoDepartamento">
              <Translate contentKey="dundermifflinApp.presupuesto.presupuestoDepartamento">Presupuesto Departamento</Translate>
            </span>
          </dt>
          <dd>{presupuestoEntity.presupuestoDepartamento}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.presupuesto.departamento">Departamento</Translate>
          </dt>
          <dd>{presupuestoEntity.departamento ? presupuestoEntity.departamento.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/presupuesto" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/presupuesto/${presupuestoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PresupuestoDetail;
