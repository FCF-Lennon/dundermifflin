import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './informacion-contacto-empleados.reducer';

export const InformacionContactoEmpleadosDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const informacionContactoEmpleadosEntity = useAppSelector(state => state.informacionContactoEmpleados.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="informacionContactoEmpleadosDetailsHeading">
          <Translate contentKey="dundermifflinApp.informacionContactoEmpleados.detail.title">InformacionContactoEmpleados</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{informacionContactoEmpleadosEntity.id}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="dundermifflinApp.informacionContactoEmpleados.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{informacionContactoEmpleadosEntity.telefono}</dd>
          <dt>
            <span id="tipoFono">
              <Translate contentKey="dundermifflinApp.informacionContactoEmpleados.tipoFono">Tipo Fono</Translate>
            </span>
          </dt>
          <dd>{informacionContactoEmpleadosEntity.tipoFono}</dd>
        </dl>
        <Button tag={Link} to="/informacion-contacto-empleados" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/informacion-contacto-empleados/${informacionContactoEmpleadosEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InformacionContactoEmpleadosDetail;
