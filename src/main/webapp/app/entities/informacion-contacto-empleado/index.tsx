import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InformacionContactoEmpleado from './informacion-contacto-empleado';
import InformacionContactoEmpleadoDetail from './informacion-contacto-empleado-detail';
import InformacionContactoEmpleadoUpdate from './informacion-contacto-empleado-update';
import InformacionContactoEmpleadoDeleteDialog from './informacion-contacto-empleado-delete-dialog';

const InformacionContactoEmpleadoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InformacionContactoEmpleado />} />
    <Route path="new" element={<InformacionContactoEmpleadoUpdate />} />
    <Route path=":id">
      <Route index element={<InformacionContactoEmpleadoDetail />} />
      <Route path="edit" element={<InformacionContactoEmpleadoUpdate />} />
      <Route path="delete" element={<InformacionContactoEmpleadoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InformacionContactoEmpleadoRoutes;
