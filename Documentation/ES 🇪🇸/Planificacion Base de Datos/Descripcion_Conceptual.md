## Realidad

La idea es hacer una aplicación que permita organizar el programa de una carrera, teniendo en cuenta todas las materias del mismo y organizándolas según diversos filtros. Inicialmente, no se contará con la capacidad de crear usuarios ni sincronizar datos con la nube; las configuraciones y datos cargados estarán alojados localmente. 

El sistema debe ser capaz de distinguir entre diferentes universidades a las que el alumno pueda estar inscrito. De ellas se desea mantener:
- Nombre de la universidad.

También se desea guardar información sobre aquellas facultades a las que pertenezcan las carreras:
- Nombre de la Facultad.
- Universidad a la que pertenece.

De cada carrera se quiere almacenar lo siguiente:
- Nombre de la carrera.
- Fecha de inscripción.
- Duración de la carrera (en cuatrimestres).
- Plan de la carrera (Enlace o PDF), *opcional*.
- Listado de materias.
- Promedio.
- Porcentaje de finalización.
- Título intermedio.
- Promedio del título intermedio.
- Porcentaje de finalización del título intermedio.

En cuanto a las materias, se requiere mantener la siguiente información:
- Nombre de la materia.
- Año al que corresponde.
- Cuatrimestre al que corresponde.
- Estado actual:    
    - Pendiente [Bloqueada, Cursable].
    - Cursando.
    - Regular.
    - Aprobada [Regular, Promoción].
- Materias necesarias (correlatividades).
- Materias bloqueadas por esta materia.
- Fecha de aprobación.
- Calificación.
