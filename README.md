# NotesApp

A simple Android application for creating, viewing, editing, and managing notes. Built with Kotlin, MVVM architecture, Room database, and a clean UI.

## Features

- Create, edit, and delete notes
- Mark notes as favorite
- View note details
- Persistent storage using Room database
- Modern Android architecture (MVVM)
- Clean and intuitive user interface

## Project Structure

```
app/
  └── src/
      └── main/
          ├── java/com/onboarding/notesapp/
          │   ├── data/
          │   │   ├── converters/         # Type converters for Room
          │   │   ├── dao/                # Data Access Objects
          │   │   ├── entities/           # Database entities
          │   │   ├── repository/         # Data repository
          │   │   └── NotesDatabase.kt    # Room database setup
          │   ├── presentation/
          │   │   └── NotesViewModel.kt   # ViewModel for notes
          │   ├── ui/
          │   │   ├── CreateNoteActivity.kt
          │   │   ├── NoteDetailActivity.kt
          │   │   ├── NotesAdapter.kt
          │   │   └── util/               # UI utilities and extensions
          │   └── MainActivity.kt         # Main screen
          └── res/
              ├── layout/                 # XML layouts
              ├── drawable/               # Icons and images
              └── values/                 # Colors, strings, themes
```

## Getting Started

### Prerequisites

- Android Studio (latest recommended)
- Android SDK 23+
- Kotlin 1.8+

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/NotesApp.git
   cd NotesApp
   ```

2. **Open in Android Studio:**
   - Open Android Studio
   - Select "Open an existing project"
   - Choose the `NotesApp` directory

3. **Build and Run:**
   - Click the "Run" button to build and launch the app on an emulator or device.

## Dependencies

- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Local database
- [ViewModel & LiveData](https://developer.android.com/topic/libraries/architecture/viewmodel) - MVVM architecture
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Asynchronous operations
