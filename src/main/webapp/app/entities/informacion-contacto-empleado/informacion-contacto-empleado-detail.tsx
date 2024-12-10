import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './informacion-contacto-empleado.reducer';

export const InformacionContactoEmpleadoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const informacionContactoEmpleadoEntity = useAppSelector(state => state.informacionContactoEmpleado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="informacionContactoEmpleadoDetailsHeading">
          <Translate contentKey="dundermifflinApp.informacionContactoEmpleado.detail.title">InformacionContactoEmpleado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{informacionContactoEmpleadoEntity.id}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="dundermifflinApp.informacionContactoEmpleado.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{informacionContactoEmpleadoEntity.telefono}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.informacionContactoEmpleado.empleado">Empleado</Translate>
          </dt>
          <dd>{informacionContactoEmpleadoEntity.empleado ? informacionContactoEmpleadoEntity.empleado.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/informacion-contacto-empleado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/informacion-contacto-empleado/${informacionContactoEmpleadoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InformacionContactoEmpleadoDetail;
