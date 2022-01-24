export interface IEstudiante {
  id?: number;
  nombre?: string;
}

export class Estudiante implements IEstudiante {
  constructor(public id?: number, public nombre?: string) {}
}

export function getEstudianteIdentifier(estudiante: IEstudiante): number | undefined {
  return estudiante.id;
}
