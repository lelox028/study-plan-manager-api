## Study Plan Manager API

<details>
  <summary>English :gb:</summary>

# Repository Description

Welcome to the repository of the API for "Study Plan Manager" – an application designed to organize and manage academic study plans. This API enables users to store detailed information about subjects, study plans, and their relationships, providing a flexible platform to manage academic progress.

## Key Features:

- **Subject Management:** Store and retrieve detailed information for each subject, including grades, dependencies, current status, and more.
- **Plan Administration:** Organize, filter, and sort multiple study plans and their subjects, simplifying academic planning.
- **Advanced Interaction:** Offer various views and utilities such as currently enrollable subjects, blocked/unblocked subjects, averages, and more.

## Upcoming Features (Future):

- **Plan Sharing:** Allows sharing plans, importing plans from university websites, or PDFs.
- **Class Schedule Calendar:** Load and display class schedules with special events.

## Technologies:

- Developed using Spring Boot for Java.

## Design and Development Process:

The design and development process will be documented. You can find the relevant documentation [here](https://github.com/lelox028/study-plan-manager-api/tree/main/Documentation/EN%20%F0%9F%87%AC%F0%9F%87%A7).

## Requirements:
> [!Note]
>To clone and execute this repository, ensure the following are installed:
>
>- **JDK 21 or higher**: Required to run and compile the project.
>- **Maven Wrapper (mvnw)**: Already included in the repository, no need for a global Maven installation.
>- **MySQL**: For creating and managing the local database.
>- **Database Credentials**: A properly configured `.env` file with valid MySQL credentials.

## Setup Instructions:

To get started with the project locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/lelox028/study-plan-manager-api.git
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd study-plan-manager-api/StudyPlanManagerApi
   ```
3. **Prepare the Database:**
   Navigate to the `Database` folder and execute the following SQL scripts in your MySQL database:

   - `PlanManager_DB_CreationScript.sql`
   - `inserts.sql`

4. **Create the Environment File:**
   Create a file named `.env` in the root of the `StudyPlanManagerApi` folder with the following content, replacing `<value>` with your database credentials:

   ```env
   DB_USERNAME=<value>
   DB_PASSWORD=<value>
   DB_URL=<value>
   ```

5. **Build the Project:**
   Use the included Maven Wrapper to build the project:
   ```bash
   ./mvnw clean install
   ```
6. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```
7. **Access the API:**
   Open your browser or API client and navigate to `http://localhost:8080`.

## How to Contribute:

Contributions are welcome and can be made as follows:

1. **Fork the repository.**
2. **Select an existing issue** or **create a new one** detailing the problem to solve or the feature to add.
3. **Create a branch** for your contribution, ensuring it is linked to the selected or created issue in step 2 (`git checkout -b issue-<issue-number>-feature/my-feature`).
4. **Make changes** and commit them (`git commit -m 'Description of changes'`).
5. **Submit a Pull Request** with your changes.
6. **Wait for the repository owner's review** and make adjustments if needed.

</details>

<details>
  <summary>Español :es:</summary>

# Descripción del Repositorio

Bienvenido al repositorio de la API para "Gestor de Planes de Estudio" – una aplicación diseñada para organizar y gestionar planes de estudio académicos. Esta API permite a los usuarios almacenar información detallada sobre las asignaturas, los planes de estudio y sus relaciones, proporcionando una plataforma flexible para gestionar el progreso académico.

## Características Clave:

- **Gestión de Asignaturas:** Almacenar y recuperar información detallada de cada asignatura, incluyendo calificaciones, correlatividades, estado actual, y más.
- **Administración de Planes:** Organizar, filtrar y ordenar múltiples planes de estudio y sus asignaturas, simplificando la planificación académica.
- **Interacción Avanzada:** Ofrece diversas vistas y utilidades como asignaturas actualmente cursables, asignaturas bloqueadas/desbloqueadas, promedios, y más.

## Características Futuras (Próximamente):

- **Compartir Planes:** Permite compartir planes, importar planes desde sitios web de universidades o PDFs.
- **Calendario de Horarios de Clases:** Cargar y mostrar horarios de clases con eventos especiales.

## Tecnologías:

- Desarrollado utilizando Spring Boot para Java.

## Proceso de Diseño y Desarrollo:

El proceso de diseño y desarrollo será documentado. Puedes encontrar la documentación relevante [aquí](https://github.com/lelox028/study-plan-manager-api/tree/main/Documentation/%F0%9F%87%AA%F0%9F%87%B8ES).

## Requisitos:

> [!Note]
> Para clonar y ejecutar este repositorio, asegúrate de contar con:
>
> - **JDK 21 o superior**: Requerido para ejecutar y compilar el proyecto.
> - **Maven Wrapper (mvnw)**: Ya incluido en el repositorio, no necesitas instalar Maven globalmente.
> - **MySQL**: Para crear y gestionar la base de datos local.
> - **Credenciales de Base de Datos**: Un archivo `.env` configurado correctamente con credenciales válidas de MySQL.

## Instrucciones para Configurar el Proyecto:

Para comenzar a trabajar con el proyecto localmente, sigue estos pasos:

1. **Clona el Repositorio:**
   ```bash
   git clone https://github.com/lelox028/study-plan-manager-api.git
   ```
2. **Accede al Directorio del Proyecto:**
   ```bash
   cd study-plan-manager-api/StudyPlanManagerApi
   ```
3. **Prepara la Base de Datos:**
   Navega a la carpeta `Database` y ejecuta los siguientes scripts SQL en tu base de datos MySQL:

   - `PlanManager_DB_CreationScript.sql`
   - `inserts.sql`

4. **Crea el Archivo de Entorno:**
   Crea un archivo llamado `.env` en la raíz de la carpeta `StudyPlanManagerApi` con el siguiente contenido, reemplazando `<value>` con las credenciales de tu base de datos:

   ```env
   DB_USERNAME=<value>
   DB_PASSWORD=<value>
   DB_URL=<value>
   ```

5. **Construye el Proyecto:**
   Usa el Maven Wrapper incluido para construir el proyecto:
   ```bash
   ./mvnw clean install
   ```
6. **Ejecuta la Aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```
7. **Accede a la API:**
   Abre tu navegador o cliente API y navega a `http://localhost:8080`.

## Cómo Contribuir:

Las contribuciones son bienvenidas y se pueden hacer de la siguiente manera:

1. **Haz un Fork del repositorio.**
2. **Selecciona un issue existente** o **crea uno nuevo** detallando la problemática a resolver o funcionalidad a agregar.
3. **Crea una rama** para tu contribución, asegurándote de que esté vinculada con el issue seleccionado o creado en el paso 2 (`git checkout -b issue-<número-issue>-feature/mi-funcionalidad`).
4. **Realiza los cambios** y haz commit (`git commit -m 'Descripción de los cambios'`).
5. **Envía un Pull Request** con tus cambios.
6. **Espera la revisión** del propietario del repositorio y realiza ajustes si es necesario.

</details>
