import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InformacionContactoJefe from './informacion-contacto-jefe';
import InformacionContactoJefeDetail from './informacion-contacto-jefe-detail';
import InformacionContactoJefeUpdate from './informacion-contacto-jefe-update';
import InformacionContactoJefeDeleteDialog from './informacion-contacto-jefe-delete-dialog';

const InformacionContactoJefeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InformacionContactoJefe />} />
    <Route path="new" element={<InformacionContactoJefeUpdate />} />
    <Route path=":id">
      <Route index element={<InformacionContactoJefeDetail />} />
      <Route path="edit" element={<InformacionContactoJefeUpdate />} />
      <Route path="delete" element={<InformacionContactoJefeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InformacionContactoJefeRoutes;
