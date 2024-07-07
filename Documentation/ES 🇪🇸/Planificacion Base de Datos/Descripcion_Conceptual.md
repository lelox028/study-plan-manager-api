## Realidad

La idea es hacer una aplicación que permita organizar el programa de una carrera, teniendo en cuenta todas las materias del mismo y organizándolas según diversos filtros y vistas que den al usuario diferentes perspectivas utiles sobre el estado actual de sus estudios. 
Inicialmente, no se contará con la capacidad de crear usuarios ni sincronizar datos con la nube por lo que las configuraciones y datos cargados estarán alojados localmente. 
El sistema debe ser capaz de distinguir entre diferentes universidades (o entidades) en las que el alumno pueda estar inscrito. De cada universidad se desea guardar solamente su nombre.
También, se sabe que las propuestas de una Universidad estan divididas en Facultades, de ellas se interesa almacenar su Nombre y la Universidad a la que pertenecen.
De cada carrera (o plan de estudio) se quiere mantener la siguiente informacion:
- Nombre de la carrera.
- Fecha de inscripción.
- Duración de la carrera (en cuatrimestres).
- Plan de la carrera (Enlace o PDF), *opcional*.
- Listado de materias.
- Promedio.
- Porcentaje de finalización.
- Título intermedio *opcional*.
- Promedio del título intermedio *opcional*.
- Porcentaje de finalización del título intermedio *opcional*.
En cuanto a las materias, se requiere mantener la siguiente información:
- Nombre de la materia.
- Año al que corresponde.
- Cuatrimestre al que corresponde.
- Estado actual, este puede ser:    
    - Pendiente y Bloqueada.
    - Pendiente y Cursable.
    - Cursando.
    - Regularizada.
    - Aprobada (sin Promoción).
    - Aprobada (Promocionada).
- Materias necesarias para aprobarla (correlatividades).
- Materias bloqueadas por esta materia.
- Fecha de aprobación *Si está aprobada*.
- Fecha de regularizacion *Si está regular*.
- Calificación *Si está aprobada*.
Con esta información, el sistema deberia ser capaz de permitirle al usuario administrar multiples planes de estudio, correspondientes a diferentes (o la misma) Universidad o entidad educativa, ademas de consultar informacion util sobre cada plan, tales como listado completo de materias