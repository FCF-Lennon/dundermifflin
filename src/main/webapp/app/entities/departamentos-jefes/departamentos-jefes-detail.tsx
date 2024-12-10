import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './departamentos-jefes.reducer';

export const DepartamentosJefesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const departamentosJefesEntity = useAppSelector(state => state.departamentosJefes.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="departamentosJefesDetailsHeading">
          <Translate contentKey="dundermifflinApp.departamentosJefes.detail.title">DepartamentosJefes</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{departamentosJefesEntity.id}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.departamentosJefes.departamento">Departamento</Translate>
          </dt>
          <dd>{departamentosJefesEntity.departamento ? departamentosJefesEntity.departamento.id : ''}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.departamentosJefes.jefe">Jefe</Translate>
          </dt>
          <dd>{departamentosJefesEntity.jefe ? departamentosJefesEntity.jefe.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/departamentos-jefes" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/departamentos-jefes/${departamentosJefesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DepartamentosJefesDetail;
