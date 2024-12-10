import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './empleado.reducer';

export const EmpleadoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const empleadoEntity = useAppSelector(state => state.empleado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="empleadoDetailsHeading">
          <Translate contentKey="dundermifflinApp.empleado.detail.title">Empleado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.id}</dd>
          <dt>
            <span id="nombreEmpleado">
              <Translate contentKey="dundermifflinApp.empleado.nombreEmpleado">Nombre Empleado</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.nombreEmpleado}</dd>
          <dt>
            <span id="apellidoEmpleado">
              <Translate contentKey="dundermifflinApp.empleado.apellidoEmpleado">Apellido Empleado</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.apellidoEmpleado}</dd>
          <dt>
            <span id="correo">
              <Translate contentKey="dundermifflinApp.empleado.correo">Correo</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.correo}</dd>
          <dt>
            <Translate contentKey="dundermifflinApp.empleado.departamento">Departamento</Translate>
          </dt>
          <dd>
            {empleadoEntity.departamentos
              ? empleadoEntity.departamentos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {empleadoEntity.departamentos && i === empleadoEntity.departamentos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/empleado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/empleado/${empleadoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmpleadoDetail;
