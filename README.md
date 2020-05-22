# Save My Hero 

**SaveMyHero** is a sample app Android application 📱 built to demonstrate use of Modern Android development tools️. 

## About
It simply loads heroes data from an API and stores it in persistence storage. Favorite heroes will be always saved in the  local database.

- The app is offline capable to search for your favorites heroes. 
- You can select your heroes to learn more about their details.
- The app supports portrait and landscape position

## Built With 
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Navigation](https://developer.android.com/guide/navigation) - Allow users to navigate across, into, and back out from the different pieces of content within your app
- [Dagger 2](https://dagger.dev/) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Glide](https://bumptech.github.io/glide/) - An image loading library for Android.
- [MocKK](https://mockk.io/) - A kotlin library for Android unit test.
- [Espresso](https://developer.android.com/training/testing/espresso) - Espresso to write concise, beautiful, and reliable Android UI tests.

## Package Structure


    com.exercise.savemyhero    # Root Package
    .
    |── common                # For shared classes and files through the project
    │   ├── di                # For dependecy injection components
    |   │   ├── component     # Dagger Components
    │   ├   |── module        # Create dagger modules to provide shared objects   
    |   │   ├── ui            # Create dagger modules to provide ui elements (fragments) 
    │   
    |
    |
    |── data                  # For data handling.
    │   ├── local             # Local Persistence Database. Room (SQLite) database
    |   │    
    │   ├── remote            # Remote Data Handlers  Glide Service   
    |    
    | 
    |── domain                # For handle data domain used in the UI.
    │   ├── hero              # Use Cases and Repository for Heroes classes
    |   │   ├── usecase       # All uses cases object used in the project for Hero class 
    ├
    |
    |
    ├── ui                     # Activity/View layer
    │   ├── core               # Shared UI classes 
    |   |
    │   ├── favorite           # Favorite Fragment Activity & ViewModel
    |   │   |
    |   │   └── list           # Adapter for RecyclerView  
    |   │            
    │   ├── hero              # Hero Detail Fragment & ViewModel    
    │   ├── home              # Home Hero Fragment & ViewModel
    |   │   |
    |   └── └── list          # Adapter for RecyclerView  
    



## Architecture
This app uses [***MVVM and Clean Architecture***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://miro.medium.com/max/1200/1*XxYlayLVyJ7SkwrqOTt10w.png)

