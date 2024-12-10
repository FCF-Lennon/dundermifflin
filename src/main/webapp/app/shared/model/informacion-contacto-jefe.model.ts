import { IJefes } from 'app/shared/model/jefes.model';

export interface IInformacionContactoJefe {
  id?: number;
  telefono?: string;
  tipoFono?: string;
  jefe?: IJefes | null;
}

export const defaultValue: Readonly<IInformacionContactoJefe> = {};
