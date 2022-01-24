export interface IColegio {
  id?: number;
  nombre?: string;
}

export class Colegio implements IColegio {
  constructor(public id?: number, public nombre?: string) {}
}

export function getColegioIdentifier(colegio: IColegio): number | undefined {
  return colegio.id;
}
