export interface IProfesor {
  id?: number;
  nombre?: string;
}

export class Profesor implements IProfesor {
  constructor(public id?: number, public nombre?: string) {}
}

export function getProfesorIdentifier(profesor: IProfesor): number | undefined {
  return profesor.id;
}
