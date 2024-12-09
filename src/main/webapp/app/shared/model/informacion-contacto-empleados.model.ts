export interface IInformacionContactoEmpleados {
  id?: number;
  telefono?: string | null;
  tipoFono?: string | null;
}

export const defaultValue: Readonly<IInformacionContactoEmpleados> = {};
