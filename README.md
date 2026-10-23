# CAPEBE

Aplicación móvil Android desarrollada para la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles**.

CAPEBE permite apoyar la gestión operacional de vuelos recreativos en terreno, centralizando el registro de pasajeros, vuelos, consentimientos, asignaciones y evidencia operacional.

## Funcionalidades

### Usuarios
- Registro de usuarios.
- Inicio de sesión.
- Validación de formularios.
- Persistencia local de credenciales para el funcionamiento del prototipo.

### Gestión de pasajeros
- Registro de cédula de identidad.
- Registro de nombre completo.
- Selección de categoría:
    - Socio Club.
    - Socio Bienestar BancoEstado.
- Registro de peso verificado.
- Consulta de pasajeros registrados.
- Estado visual del consentimiento.

### Consentimiento digital
- Captura táctil de firma manuscrita.
- Validación de firma.
- Estado de consentimiento:
    - Pendiente.
    - Firmado.

### Gestión de vuelos
- Registro de matrícula de aeronave.
- Registro del modelo de aeronave.
- Registro del piloto al mando.
- Número de tripulantes.
- Lectura inicial y final del horómetro.
- Cálculo automático del tiempo de vuelo.
- Consulta de vuelos registrados.

### Asignación de pasajeros
- Selección de pasajero.
- Selección de vuelo.
- Confirmación de la asignación.
- Prevención de asignaciones duplicadas.
- Estados visuales para identificar las selecciones.

### Evidencia operacional
- Captura de fotografías mediante la cámara del dispositivo.
- Selección de imágenes almacenadas en el dispositivo.
- Previsualización de evidencia.
- Eliminación y reemplazo de la evidencia seleccionada.

### Panel operacional
- Total de pasajeros registrados.
- Total de consentimientos firmados.
- Total de vuelos registrados.
- Total de asignaciones realizadas.

## Funcionamiento offline

Los datos operacionales principales son almacenados localmente mediante **Room/SQLite**.

Esto permite continuar trabajando sin conexión a internet con información como:

- Usuarios.
- Pasajeros.
- Consentimientos.
- Firmas.
- Vuelos.
- Horómetros.
- Asignaciones.

## Arquitectura

El proyecto utiliza el patrón arquitectónico **MVVM**.

La aplicación organiza sus responsabilidades principalmente en:

- `model`: modelos utilizados por la interfaz y los ViewModel.
- `data/local/entity`: entidades almacenadas mediante Room.
- `data/local/dao`: operaciones de acceso a los datos.
- `data/database`: configuración de la base de datos local.
- `ui/screen`: pantallas de la aplicación.
- `ui/components`: componentes reutilizables.
- `ui/viewmodel`: gestión de estado y lógica de las pantallas.
- `ui/navigation`: navegación de la aplicación.
- `ui/theme`: configuración visual mediante Material 3.

## Tecnologías utilizadas

- Kotlin.
- Android Studio.
- Jetpack Compose.
- Material Design 3.
- MVVM.
- Room.
- SQLite.
- Navigation Compose.
- Corrutinas de Kotlin.
- Recursos nativos de Android.
- Git.
- GitHub.

## Recursos nativos

La aplicación utiliza recursos disponibles en el dispositivo:

- Cámara para captura de evidencia operacional.
- Selector de imágenes del sistema.
- Interfaz táctil para captura de firma manuscrita.

## Gestión de estado

La aplicación utiliza `ViewModel` junto con estados de Compose para mantener y actualizar la información mostrada en las diferentes pantallas.

## Persistencia

Room permite almacenar localmente la información de la aplicación mediante entidades y DAO.

Entre las principales entidades se encuentran:

- `UsuarioEntity`.
- `PasajeroEntity`.
- `VueloEntity`.
- `AsignacionEntity`.

## Flujo principal

```text
Inicio
   ↓
Login / Registro
   ↓
Panel Operacional
   ├── Pasajeros
   │     ├── Registrar Pasajero
   │     └── Consentimiento y Firma
   │
   └── Vuelos
         ├── Registrar Vuelo
         ├── Asignar Pasajeros
         └── Evidencia Operacional
```

## Validaciones implementadas

La aplicación contempla validaciones como:

- Campos obligatorios.
- Confirmación de contraseña.
- Correo ya registrado.
- Credenciales incorrectas.
- Peso mayor que cero.
- Número de tripulantes mayor que cero.
- Validación de lecturas del horómetro.
- Horómetro final mayor que el inicial.
- Firma obligatoria para confirmar consentimiento.
- Selección obligatoria de pasajero y vuelo.
- Prevención de asignación duplicada de pasajeros.

## Horómetro

El horómetro representa las horas acumuladas de utilización de una aeronave.

CAPEBE registra:

- Lectura inicial.
- Lectura final.
- Diferencia entre ambas lecturas.

La aplicación calcula automáticamente el tiempo consumido y presenta el resultado en horas y minutos.

Ejemplo:

```text
Lectura inicial: 1280.3
Lectura final:   1280.4

Tiempo calculado:
0.1 h · 6 min
```

## Proyecto académico

CAPEBE fue desarrollado como parte de la Evaluación 2 de la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles**, aplicando conceptos de desarrollo Android, arquitectura MVVM, persistencia local, navegación, gestión de estado, Material 3 y recursos nativos del dispositivo.