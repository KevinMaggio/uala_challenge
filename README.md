# Ualá Android Challenge - Cities App

Aplicación Android desarrollada en Kotlin y Jetpack Compose que permite explorar, buscar y filtrar un listado de ciudades, visualizarlas en un mapa y gestionar favoritos.

## 📱 Capturas de Pantalla


<img width="373" height="831" alt="Captura de pantalla 2026-02-27 130049" src="https://github.com/user-attachments/assets/9730bbab-2b69-47a5-b4e5-e4cf47f653c1" />
<img width="378" height="823" alt="Captura de pantalla 2026-02-27 130107" src="https://github.com/user-attachments/assets/bf50073f-6fdf-4ef9-b55b-7a8be6455070" /> <img width="387" height="869" alt="Captura de pantalla 2026-02-27 130155" src="https://github.com/user-attachments/assets/2cd3be0f-1807-4b67-b3d7-62f6ce94fe2c" /> <img width="1399" height="626" alt="Captura de pantalla 2026-02-27 130138" src="https://github.com/user-attachments/assets/bc20564b-1c03-458b-aca2-13fb9ed731c9" />
 <img width="374" height="829" alt="Captura de pantalla 2026-02-27 130120" src="https://github.com/user-attachments/assets/d1c27856-77c3-42ef-9882-e6ea527583b1" />


## 🚀 Características Principales

*   **Listado de Ciudades:** Carga y visualización eficiente de ~200k ciudades.
*   **Búsqueda Optimizada:** Algoritmo de búsqueda por prefijo en tiempo real (O(log n)) con debounce.
*   **Filtros:** Filtrado rápido por nombre y opción de "Solo Favoritos".
*   **Mapa Interactivo:** Visualización de la ubicación de la ciudad (Google Maps).
*   **Diseño Adaptativo:** Soporte para modo Portrait (lista) y Landscape (lista + mapa).
*   **Persistencia:** Guardado de ciudades favoritas.

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Kotlin
*   **UI:** Jetpack Compose (Material 3)
*   **Arquitectura:** MVI (Model-View-Intent) + Clean Architecture (Data, Domain, Presenter)
*   **Inyección de Dependencias:** Hilt
*   **Asincronía:** Coroutines & Flow
*   **Red:** Retrofit + Gson
*   **Mapas:** Google Maps Compose
*   **Testing:** JUnit 4, Mockk, Coroutines Test, Compose Test

## ⚡ Optimización del Buscador (Challenge Requirement)

Para cumplir con el requerimiento de *"optimise for fast searches"* sobre una lista de 200,000 elementos, se implementó una estrategia de **Búsqueda Binaria por Prefijo**:

1.  **Pre-procesamiento:** Al cargar los datos, cada ciudad calcula una `searchKey` ("nombre, país" en minúsculas) y la lista se ordena por esta clave.
2.  **Búsqueda Binaria:** En lugar de recorrer la lista completa (O(n)) en cada tecleo, usamos búsqueda binaria para encontrar el rango de índices `[inicio, fin)` que coinciden con el prefijo.
    *   Complejidad: **O(log n)**.
    *   Impacto: De ~200,000 comparaciones a ~36 comparaciones por búsqueda.
3.  **Zero-Allocation:** Se evita la creación de objetos `String` temporales durante el filtrado para no saturar el Garbage Collector.
4.  **Debounce:** Se aplica un retraso de 300ms en el input del usuario para evitar búsquedas innecesarias mientras se escribe.

## 📂 Estructura del Proyecto

El proyecto sigue una estructura modular por *features*:

```
com.google.uala_challenge
├── core                # Utilidades, extensiones y constantes globales
├── features
│   ├── activity        # MainActivity
│   ├── details         # Feature: Detalle de ciudad
│   ├── home            # Feature: Listado y búsqueda (Principal)
│   │   ├── data        # Repositorio, DTOs, API
│   │   ├── domain      # Modelos, UseCases, Interfaces
│   │   └── presenter   # ViewModels (Blocs), Pantallas, Composables
│   └── navigation      # Grafo de navegación y rutas
└── ui.theme            # Tema y estilos de la app
```

## ✅ Tests

El proyecto incluye tests unitarios y de UI cubriendo las partes críticas:

*   **Unit Tests:**
    *   `PrefixSearchUtilsTest`: Valida la lógica matemática de los rangos de búsqueda.
    *   `SearchCitiesUseCaseTest`: Verifica la integración del buscador.
    *   `HandleSearchCitiesBlocTest`: Prueba el debounce y la gestión de estado.
    *   `CityModelTest`: Valida la integridad de los modelos de datos.
*   **UI Tests (Instrumented):**
    *   `ItemCityTest`: Verifica que los componentes visuales muestren la información correcta y respondan a los eventos.

Para ejecutar los tests:
```bash
./gradlew testDebugUnitTest      # Unit Tests
./gradlew connectedAndroidTest   # UI Tests
```

## 🔧 Configuración

1.  Clonar el repositorio.
2.  Abrir en Android Studio Ladybug o superior.
3.  Agregar tu API Key de Google Maps en `local.properties`:
    ```properties
    MAPS_API_KEY=tu_api_key_aqui
    ```
4.  Sincronizar Gradle y ejecutar.
