# Weather App
The Weather app is built using modern Android development tools and technologies to provide a fast, scalable, and maintainable solution.

## Programming Language: Kotlin for clean, concise, and safe code.
UI Framework: Jetpack Compose for declarative UI development.
Networking: Retrofit for API communication, with OkHttp for request handling and Gson for JSON parsing.
Dependency Injection: Hilt for managing dependencies and improving testability.
Asynchronous Programming: Kotlin Coroutines for efficient background task management.
State Management: StateFlow for managing UI state reactively.
Local Storage: SharedPreferences for data persistence,
Image Loading: Coil for efficient image loading.
API: Data fetched from WeatherAPI for real-time weather updates.
Testing: JUnit, Espresso, and Mockito for unit and UI testing.

## Requirements
To use this app, you'll need an API key from OpenWeatherMap. Follow these steps:

1. **Create an Account**: Visit [https://www.weatherapi.com/](https://www.weatherapi.com/) and sign up for a free account. This process takes just a few minutes.
2. **Obtain Your API Key**: Once your account is created, navigate to the API section and generate your API key.

## Configuration
After obtaining your API key, you'll need to integrate it into the project:

1. Search for `WeatherRepository` in the project.
2. Open the `fetchWeather` function and apply your API key.
