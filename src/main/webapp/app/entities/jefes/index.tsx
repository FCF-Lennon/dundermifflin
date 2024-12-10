import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Jefes from './jefes';
import JefesDetail from './jefes-detail';
import JefesUpdate from './jefes-update';
import JefesDeleteDialog from './jefes-delete-dialog';

const JefesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Jefes />} />
    <Route path="new" element={<JefesUpdate />} />
    <Route path=":id">
      <Route index element={<JefesDetail />} />
      <Route path="edit" element={<JefesUpdate />} />
      <Route path="delete" element={<JefesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JefesRoutes;
