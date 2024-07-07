## Realidad

La idea es hacer una aplicación que permita organizar el programa de una carrera, teniendo en cuenta todas las materias del mismo y organizándolas según diversos filtros y vistas que den al usuario diferentes perspectivas útiles sobre el estado actual de sus estudios. Inicialmente, no se contará con la capacidad de crear usuarios ni sincronizar datos con la nube, por lo que las configuraciones y datos cargados estarán alojados localmente. El sistema debe ser capaz de distinguir entre diferentes universidades (o entidades) en las que el alumno pueda estar inscrito. De cada universidad se desea guardar solamente su nombre. También, se sabe que las propuestas de una universidad están divididas en facultades, de ellas se interesa almacenar su nombre y la universidad a la que pertenecen.

De cada carrera (o plan de estudio) se quiere mantener la siguiente información:
- Nombre de la carrera.
- Fecha de inscripción.
- Duración de la carrera (en cuatrimestres).
- Plan de la carrera (enlace o PDF), **opcional**.
- Listado de materias.
- Promedio.
- Porcentaje de finalización.
- Título intermedio **opcional**.
- Promedio del título intermedio **opcional**.
- Porcentaje de finalización del título intermedio **opcional**.

En cuanto a las materias, se requiere mantener la siguiente información:
- Nombre de la materia.
- Año al que corresponde.
- Cuatrimestre al que corresponde.
- Estado actual, este puede ser:
    - Pendiente y bloqueada.
    - Pendiente y cursable.
    - Cursando.
    - Regularizada.
    - Aprobada (sin promoción).
    - Aprobada (promocionada).
- Materias necesarias para aprobarla (correlatividades).
- Materias bloqueadas por esta materia.
- Fecha de aprobación **si está aprobada**.
- Fecha de regularización **si está regular**.
- Calificación **si está aprobada**.

Con esta información, el sistema debería ser capaz de permitirle al usuario administrar múltiples planes de estudio, correspondientes a diferentes (o la misma) universidades o entidades educativas, además de consultar información útil sobre cada plan, tales como listado completo de materias, listado de materias disponibles para cursar, materias de un año/cuatrimestre específico, porcentaje de finalización de un programa/año/cuatrimestre, promedio actual del plan, de cada año o cuatrimestre y, en el caso de aquellas materias bloqueadas, el motivo por el cual se encuentran bloqueadas.
