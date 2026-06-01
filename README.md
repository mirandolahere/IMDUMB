# IMDUMB

Aplicación Android desarrollada como parte de un reto técnico para la visualización de categorías de películas y detalle de películas utilizando MVP + Clean Architecture.

## Características

* Splash Screen,  carga de configuración desde Firebase Remote Config en el Home Activity.
* Consumo de APIs REST utilizando Retrofit + Gson.
* Programación reactiva con RxJava / RxKotlin.
* Inyección de dependencias con Hilt.
* Product Flavors (dev / prod).
* Listado de categorías de películas.
* RecyclerView anidado (categorías y películas).
* Detalle de película.
* Carrusel de imágenes mediante ViewPager2.
* Descripción HTML utilizando Html.fromHtml().
* Lista de actores.
* BottomSheet para recomendación de películas.

---

## Arquitectura

El proyecto utiliza MVP (Model View Presenter) junto con Clean Architecture.

### Capa Presentation

Responsable de la interacción con el usuario.

* SplashActivity
* HomeActivity
* MovieDetailActivity
* Presenters
* Adapters
* BottomSheet

### Capa Domain

Contiene la lógica de negocio.

* Models
* Repositories (Interfaces)
* UseCases

### Capa Data

Responsable del acceso a datos.

* Retrofit Services
* DTOs
* Mappers
* Repository Implementations
* Firebase Remote Config
* SharedPreferences

### Core Module

Contiene componentes reutilizables.

* BaseView
* BasePresenter
* SchedulerProvider

---

## Product Flavors

### DEV

```text
applicationId:
com.gestionsource.imdumb.dev
```

Configuraciones:

* Logs habilitados.
* Firebase Dev.
* Nombre aplicación: IMDUMB Dev.

### PROD

```text
applicationId:
com.gestionsource.imdumb
```

Configuraciones:

* Logs deshabilitados.
* Firebase Prod.
* Nombre aplicación: IMDUMB.

---

## Firebase

El proyecto utiliza:

* Firebase Analytics
* Firebase Remote Config

Remote Config:

```text
welcome_text
feature_recommendations_enabled
```

---

## Cómo ejecutar el proyecto

### Requisitos

* Android Studio Narwhal o superior.
* JDK 17.
* Android SDK 34.

### Pasos

1. Clonar el repositorio.

```bash
git clone <repository-url>
```

2. Abrir el proyecto en Android Studio.

3. Sincronizar Gradle.

4. Seleccionar Build Variant:

```text
devDebug
```

o

```text
prodDebug
```


## Capturas

<img width="430" height="931" alt="Screenshot 2026-06-01 at 2 02 07 PM" src="https://github.com/user-attachments/assets/a7d99f39-087c-4caf-b80f-b2be9e5e6fe7" />
<img width="425" height="936" alt="Screenshot 2026-06-01 at 2 02 16 PM" src="https://github.com/user-attachments/assets/5d15a227-f082-4c68-99e9-80cb646f2cfa" />
<img width="441" height="929" alt="Screenshot 2026-06-01 at 2 02 28 PM" src="https://github.com/user-attachments/assets/3f20b90c-b12c-4bd0-8df4-fe1eae4fb037" />


---

## Autor

Claudia Alexandra Miranda Chillitupa
Android Developer
