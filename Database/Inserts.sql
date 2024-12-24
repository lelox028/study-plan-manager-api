USE PlanManager;

INSERT INTO Universidades (id_U, Nombre_U) VALUES
(1,"UNSL"),
(2,"Siglo 21");

INSERT INTO Facultades (id_F, Nombre_F, Pertenecen_Id_U) VALUES
(1,"FCMyN",1),
(2,"Default",2);

INSERT INTO Carreras 
(`Nombre_C`, `FechaInscripcion`, `Duracion`, `Plan`, `TituloIntermedio`, `Dicta_Id_F`) 
VALUES 
('Ingeniería en Sistemas', '2024-01-15', 5, 'https://example.com/plan_sistemas.pdf', 'Analista de Sistemas', 1);

