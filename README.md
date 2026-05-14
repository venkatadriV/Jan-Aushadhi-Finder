<![CDATA[<div align="center">

# 💊 Jan-Aushadhi Finder

### Affordable Healthcare for All 🇮🇳

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Material 3](https://img.shields.io/badge/Design-Material%203-6750A4?style=for-the-badge&logo=material-design&logoColor=white)](https://m3.material.io)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

**Find affordable generic medicines and nearby Jan-Aushadhi Kendras across India**

*A companion app for the Pradhan Mantri Bhartiya Janaushadhi Pariyojana (PMBJP) — a Government of India initiative to provide quality generic medicines at affordable prices.*

---

</div>

## ✨ Features

### 🔍 Smart Medicine Search
- Search medicines by **name** or **salt composition**
- **Fuzzy search** algorithm for typo-tolerant results
- Filter by categories: Pain Relief, Antibiotics, Cardiac, Diabetes, and more
- View search history with quick-access suggestions

### 💰 Price Comparison & Savings Calculator
- Compare **branded vs generic** medicine prices side-by-side
- Calculate **50–90% savings** by switching to generic alternatives
- Monthly and yearly savings projections
- Share medicine savings info with family and friends

### 🗺️ Store Locator
- Find **nearby Jan-Aushadhi Kendras** using Google Maps
- Real-time distance calculation within 10 km radius
- Get **directions** to the nearest store
- Check store open/closed status
- Firebase Firestore integration for live store data

### ⏰ Medicine Reminders
- Set **refill reminders** (daily, weekly, monthly)
- Push notifications with exact alarm scheduling
- Auto-reschedule reminders after device reboot
- Add notes to each reminder

### ❤️ Favorites
- Save frequently purchased medicines for quick access
- One-tap add/remove from any screen

### 🏠 Rich Home Dashboard
- Quick action cards for all major features
- "Did You Know?" educational facts carousel
- Popular & recent search suggestions
- Statistics overview (500+ medicines, 9000+ stores, 50-90% savings)

---

## 🏗️ Architecture

```
📦 com.janaushadhi.finder
├── 📂 data
│   ├── 📂 db                    # Room Database (DAO + Entities)
│   │   ├── AppDatabase.kt       # Room DB with medicines, favorites, reminders, search history
│   │   ├── MedicineDao.kt       # CRUD + search queries for medicines
│   │   ├── FavoriteDao.kt       # Favorite medicines management
│   │   ├── ReminderDao.kt       # Reminder scheduling
│   │   └── SearchHistoryDao.kt  # Search history tracking
│   ├── 📂 model                 # Data models / Entities
│   │   ├── Medicine.kt          # Medicine entity with price comparison
│   │   ├── Store.kt             # Store location data
│   │   ├── Favorite.kt          # Favorite medicine reference
│   │   ├── Reminder.kt          # Reminder scheduling entity
│   │   └── SearchHistory.kt     # Search history entry
│   ├── 📂 repository            # Repository pattern for data access
│   │   ├── MedicineRepository.kt
│   │   ├── StoreRepository.kt
│   │   ├── FirestoreStoreRepository.kt
│   │   └── ReminderRepository.kt
│   └── SeedData.kt              # Pre-loaded medicine database (500+ medicines)
├── 📂 ui
│   ├── 📂 home                  # Home dashboard
│   ├── 📂 search                # Medicine search & results
│   ├── 📂 detail                # Medicine detail view
│   ├── 📂 store                 # Store locator with Google Maps
│   ├── 📂 savings               # Savings calculator
│   ├── 📂 favorites             # Favorites list
│   └── 📂 reminder              # Reminder management
├── 📂 util
│   ├── AnalyticsHelper.kt       # Firebase Analytics events
│   ├── FuzzySearch.kt           # Typo-tolerant search algorithm
│   ├── NotificationHelper.kt    # Push notification management
│   ├── ReminderReceiver.kt      # Alarm broadcast receiver
│   ├── BootReceiver.kt          # Reschedule alarms on boot
│   └── Extensions.kt            # Kotlin extension functions
├── JanAushadhiApp.kt            # Application class (Firebase init)
├── MainActivity.kt              # Main activity with navigation
└── SplashActivity.kt            # Splash screen
```

### Design Patterns
- **MVVM** (Model-View-ViewModel) with LiveData
- **Repository Pattern** for clean data abstraction
- **Offline-First** with Room database + Firestore sync
- **Navigation Component** for fragment-based navigation

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Kotlin 1.9.22 |
| **Min SDK** | API 24 (Android 7.0) |
| **Target SDK** | API 34 (Android 14) |
| **UI Framework** | Material Design 3 |
| **Architecture** | MVVM + Repository Pattern |
| **Local Database** | Room 2.6.1 |
| **Remote Database** | Firebase Firestore |
| **Maps** | Google Maps SDK + Play Services Location |
| **Analytics** | Firebase Analytics |
| **Crash Reporting** | Firebase Crashlytics |
| **Navigation** | Jetpack Navigation Component 2.7.7 |
| **Async** | Kotlin Coroutines + Flow |
| **View Binding** | Android View Binding |
| **Build System** | Gradle 8.2.2 (Kotlin DSL) |

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK 17**
- **Android SDK** with API 34
- A **Google Maps API Key** ([Get one here](https://developers.google.com/maps/documentation/android-sdk/get-api-key))
- A **Firebase project** ([Setup guide](https://firebase.google.com/docs/android/setup))

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/venkatadriV/Jan-Aushadhi-Finder.git
   cd Jan-Aushadhi-Finder
   ```

2. **Add your Google Maps API Key**
   
   In `app/src/main/AndroidManifest.xml`, replace the API key:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_API_KEY_HERE" />
   ```

3. **Add Firebase configuration**
   
   Place your `google-services.json` in the `app/` directory.
   > You can download this from your [Firebase Console](https://console.firebase.google.com/).

4. **Build & Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Or simply open in Android Studio and click **Run ▶️**

---

## 📱 Screenshots

| Home | Search | Store Locator | Medicine Detail |
|------|--------|---------------|-----------------|
| Dashboard with quick actions | Smart fuzzy search | Nearby stores on map | Price comparison view |

> *Screenshots coming soon*

---

## 🎨 Design Highlights

- **Dark Mode** support with dedicated night theme (`values-night/`)
- **Material Design 3** components and color system
- **Smooth animations** for screen transitions (`res/anim/`)
- **Custom vector icons** for all UI elements
- **Responsive layouts** with ConstraintLayout
- **Gradient backgrounds** and card-based UI

---

## 📂 Project Structure

```
Jan-Aushadhi-Finder/
├── app/
│   ├── build.gradle.kts          # App-level build config
│   ├── google-services.json      # Firebase config
│   ├── proguard-rules.pro        # ProGuard rules
│   └── src/main/
│       ├── AndroidManifest.xml   # App manifest
│       ├── java/                 # Kotlin source code
│       └── res/                  # Resources
│           ├── anim/             # Transition animations
│           ├── drawable/         # Vector icons & backgrounds
│           ├── layout/           # XML layouts
│           ├── menu/             # Bottom navigation menu
│           ├── navigation/       # Navigation graph
│           ├── values/           # Colors, strings, themes, dimens
│           └── values-night/     # Dark mode overrides
├── build.gradle.kts              # Root build config
├── settings.gradle.kts           # Project settings
├── gradle.properties             # Gradle properties
├── gradlew / gradlew.bat         # Gradle wrapper scripts
├── .gitignore                    # Git ignore rules
├── LICENSE                       # MIT License
└── README.md                     # This file
```

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- **Pradhan Mantri Bhartiya Janaushadhi Pariyojana (PMBJP)** — for the initiative to make healthcare affordable
- **Bureau of Pharma PSUs of India (BPPI)** — for managing Jan-Aushadhi Kendras
- **Google** — for Maps SDK, Firebase, and Material Design
- **JetBrains** — for Kotlin and IntelliJ platform

---

## 📬 Contact

**venkatadriV** — [@venkatadriV](https://github.com/venkatadriV)

Project Link: [https://github.com/venkatadriV/Jan-Aushadhi-Finder](https://github.com/venkatadriV/Jan-Aushadhi-Finder)

---

<div align="center">

**⭐ Star this repo if you found it helpful! ⭐**

Made with ❤️ for affordable healthcare in India

</div>
]]>
