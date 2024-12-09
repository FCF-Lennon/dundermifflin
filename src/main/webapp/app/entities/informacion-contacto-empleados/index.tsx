import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InformacionContactoEmpleados from './informacion-contacto-empleados';
import InformacionContactoEmpleadosDetail from './informacion-contacto-empleados-detail';
import InformacionContactoEmpleadosUpdate from './informacion-contacto-empleados-update';
import InformacionContactoEmpleadosDeleteDialog from './informacion-contacto-empleados-delete-dialog';

const InformacionContactoEmpleadosRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InformacionContactoEmpleados />} />
    <Route path="new" element={<InformacionContactoEmpleadosUpdate />} />
    <Route path=":id">
      <Route index element={<InformacionContactoEmpleadosDetail />} />
      <Route path="edit" element={<InformacionContactoEmpleadosUpdate />} />
      <Route path="delete" element={<InformacionContactoEmpleadosDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InformacionContactoEmpleadosRoutes;
