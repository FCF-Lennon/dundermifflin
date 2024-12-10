import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DepartamentosJefes from './departamentos-jefes';
import DepartamentosJefesDetail from './departamentos-jefes-detail';
import DepartamentosJefesUpdate from './departamentos-jefes-update';
import DepartamentosJefesDeleteDialog from './departamentos-jefes-delete-dialog';

const DepartamentosJefesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DepartamentosJefes />} />
    <Route path="new" element={<DepartamentosJefesUpdate />} />
    <Route path=":id">
      <Route index element={<DepartamentosJefesDetail />} />
      <Route path="edit" element={<DepartamentosJefesUpdate />} />
      <Route path="delete" element={<DepartamentosJefesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DepartamentosJefesRoutes;
