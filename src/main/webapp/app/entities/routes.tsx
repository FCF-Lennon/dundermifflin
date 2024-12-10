import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Departamento from './departamento';
import DepartamentosJefes from './departamentos-jefes';
import Empleado from './empleado';
import InformacionContactoEmpleado from './informacion-contacto-empleado';
import InformacionContactoJefe from './informacion-contacto-jefe';
import Jefes from './jefes';
import Presupuesto from './presupuesto';
import { Route } from 'react-router';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="departamento/*" element={<Departamento />} />
        <Route path="departamentos-jefes/*" element={<DepartamentosJefes />} />
        <Route path="empleado/*" element={<Empleado />} />
        <Route path="informacion-contacto-empleado/*" element={<InformacionContactoEmpleado />} />
        <Route path="informacion-contacto-jefe/*" element={<InformacionContactoJefe />} />
        <Route path="jefes/*" element={<Jefes />} />
        <Route path="presupuesto/*" element={<Presupuesto />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
