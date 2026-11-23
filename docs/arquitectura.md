    B["Retrofit + Gson"]

    P["Pasajeros Service :8081"]
    V["Vuelos Service :8082"]
    S["Asignaciones Service :8083"]

    DP[("PostgreSQL Pasajeros")]
    DV[("PostgreSQL Vuelos")]
    DS[("PostgreSQL Asignaciones")]

    A --> B
    B --> P
    B --> V
    B --> S

    P --> DP
    V --> DV
    S --> DS


Cada microservicio utiliza una base PostgreSQL independiente.
Asignaciones almacena los identificadores de pasajero y vuelo.

## 2. Arquitectura Android y roles

mermaid
flowchart TD
    I["Inicio"]
    L["Login / Registro"]
    R[("Room / SQLite - Usuarios y roles")]
    D{"Rol autenticado"}

    INV["Panel Invitado"]
    COL["Panel Colaborador"]

    C["Consultar vuelos y compartir"]
    G["Gestionar pasajeros, vuelos y asignaciones"]
    VM["ViewModels"]
    API["Retrofit / APIs REST"]

    I --> L
    L --> R
    R --> D

    D -->|"INVITADO"| INV
    D -->|"COLABORADOR"| COL

    INV --> C
    COL --> G

    C --> VM
    G --> VM
    VM --> API


La autenticación de ambos roles se realiza mediante Room/SQLite.
Los ViewModels de las funciones operacionales consumen APIs REST
mediante Retrofit.

CAPEBE conserva también funciones locales de la experiencia anterior.
Sus datos Room no se sincronizan automáticamente con PostgreSQL.
```