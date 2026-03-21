# Descripción del Proyecto

## EstomatoLearn

**EstomatoLearn** es una aplicación educativa móvil desarrollada en Android, diseñada para estudiantes y profesionales de odontología interesados en aprender sobre procedimientos endodónticos, con un enfoque específico en el acceso cameral. La app proporciona una experiencia de aprendizaje interactiva a través de lecciones teóricas, evaluaciones mediante quizzes y seguimiento del progreso del usuario.

### Características Principales

- **Registro de Usuario**: Pantalla inicial para ingresar o actualizar el nombre del usuario, almacenado localmente en una base de datos.
- **Lista de Cursos**: Vista principal con una lista de cursos sobre temas endodónticos, incluyendo:
  - Introducción al acceso cameral
  - Aspectos a considerar en la técnica
  - Pasos de la técnica
  - Técnicas específicas para dientes anteriores, premolares y molares
  - Instrumentación de conductos radiculares
- **Detalles de Cursos**: Pantalla detallada para cada curso, mostrando descripción completa y contenido educativo.
- **Sistema de Quizzes**: Evaluaciones interactivas con preguntas de opción múltiple, temporizador y cálculo de puntuación.
- **Seguimiento de Progreso**: Vista dedicada para monitorear el avance en los cursos y quizzes.
- **Navegación Intuitiva**: Barra de navegación inferior y navegación por Jetpack Navigation.
- **Pantalla de Splash**: Introducción animada al iniciar la aplicación.

### Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **Framework de UI**: Jetpack Compose (Material 3)
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **Persistencia de Datos**: Room Database
- **Navegación**: Navigation Compose
- **Manejo de Estado**: ViewModel y LiveData
- **Inyección de Dependencias**: ViewModelFactory
- **Compilación**: Gradle con Kotlin DSL
- **Versión Mínima de Android**: API 26 (Android 8.0)

### Estructura del Proyecto

El proyecto sigue una estructura modular organizada en paquetes:

- `mainView/`: Contiene las vistas y lógica para la pantalla principal de cursos, incluyendo el modelo de datos de cursos.
- `navigation/`: Maneja la navegación entre pantallas usando Navigation Compose.
- `quizModule/`: Implementa el sistema de quizzes con preguntas, temporizador y evaluación.
- `progressView/`: Vista para mostrar el progreso del usuario.
- `userName/`: Gestión de usuarios, incluyendo base de datos Room y vistas de registro.
- `ui/`: Recursos de interfaz de usuario compartidos.

### Instalación y Ejecución

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Sincroniza las dependencias de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico con Android API 26 o superior.

### Contribución

Este proyecto es una herramienta educativa enfocada en odontología endodóntica. Las contribuciones para mejorar el contenido educativo, agregar más cursos o quizzes son bienvenidas.

### Licencia

[Especificar licencia si aplica]
