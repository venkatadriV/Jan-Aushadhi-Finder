<div align="center">
  
  <img src="https://img.icons8.com/color/96/pill.png" width="80" alt="Jan-Aushadhi Logo"/>

  # 💊 Jan-Aushadhi Finder
  
  ### *Affordable Healthcare for All* 🇮🇳

  <br/>

  [![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
  [![API](https://img.shields.io/badge/Min%20SDK-24-brightgreen?style=for-the-badge)](https://developer.android.com/about/versions/nougat)
  [![Material 3](https://img.shields.io/badge/Material%20Design-3-6750A4?style=for-the-badge&logo=material-design&logoColor=white)](https://m3.material.io)
  
  [![Firebase](https://img.shields.io/badge/Firebase-Integrated-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com)
  [![Google Maps](https://img.shields.io/badge/Google%20Maps-SDK-4285F4?style=for-the-badge&logo=google-maps&logoColor=white)](https://developers.google.com/maps)
  [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

  <br/>

  **A companion app for the [Pradhan Mantri Bhartiya Janaushadhi Pariyojana (PMBJP)](https://janaushadhi.gov.in/)**
  
  *Find affordable generic medicines • Locate nearby Jan-Aushadhi Kendras • Save 50–90% on medicines*

  <br/>

  [Features](#-features) · [Tech Stack](#%EF%B8%8F-tech-stack) · [Getting Started](#-getting-started) · [Architecture](#%EF%B8%8F-architecture) · [Contributing](#-contributing)

  <br/>

  ---

</div>

<br/>

## 📋 Overview

**Jan-Aushadhi Finder** is an Android application that helps Indian citizens find quality **generic medicines** at affordable prices through the government's PMBJP scheme. The app enables users to search for medicines, compare prices between branded and generic equivalents, find nearby Jan-Aushadhi Kendras on an interactive map, and manage medicine reminders — all in one place.

> 🏥 *Jan-Aushadhi medicines are **50–90% cheaper** than branded equivalents, with the **same quality, safety, and efficacy** as branded ones.*

<br/>

## ✨ Features

<table>
  <tr>
    <td width="50%">
      <h3>🔍 Smart Medicine Search</h3>
      <ul>
        <li>Search by <b>name</b> or <b>salt composition</b></li>
        <li><b>Fuzzy search</b> — typo-tolerant results</li>
        <li>Filter by 10+ categories</li>
        <li>Search history & popular suggestions</li>
      </ul>
    </td>
    <td width="50%">
      <h3>💰 Price Comparison</h3>
      <ul>
        <li><b>Branded vs Generic</b> side-by-side pricing</li>
        <li>Instant <b>savings calculation</b></li>
        <li>Monthly & yearly savings projections</li>
        <li>Share savings info with family</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <h3>🗺️ Store Locator</h3>
      <ul>
        <li>Find <b>nearby Jan-Aushadhi Kendras</b></li>
        <li>Google Maps with <b>real-time distance</b></li>
        <li>One-tap <b>directions</b></li>
        <li>Store status (Open/Closed)</li>
      </ul>
    </td>
    <td width="50%">
      <h3>⏰ Medicine Reminders</h3>
      <ul>
        <li><b>Refill reminders</b> — daily, weekly, monthly</li>
        <li>Push notifications with <b>exact alarms</b></li>
        <li>Auto-reschedule on device reboot</li>
        <li>Custom notes support</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <h3>❤️ Favorites</h3>
      <ul>
        <li>Save frequently purchased medicines</li>
        <li>One-tap add/remove from any screen</li>
        <li>Quick access dashboard</li>
      </ul>
    </td>
    <td width="50%">
      <h3>🎨 Modern UI/UX</h3>
      <ul>
        <li><b>Material Design 3</b> components</li>
        <li>Full <b>Dark Mode</b> support</li>
        <li>Smooth animations & transitions</li>
        <li>Responsive card-based layouts</li>
      </ul>
    </td>
  </tr>
</table>

<br/>

## 🛠️ Tech Stack

<div align="center">

| Layer | Technology |
|:---:|:---|
| **Language** | ![Kotlin](https://img.shields.io/badge/Kotlin_1.9.22-7F52FF?style=flat-square&logo=kotlin&logoColor=white) |
| **UI** | ![Material3](https://img.shields.io/badge/Material_Design_3-6750A4?style=flat-square&logo=material-design&logoColor=white) ![ViewBinding](https://img.shields.io/badge/View_Binding-3DDC84?style=flat-square&logo=android&logoColor=white) |
| **Architecture** | ![MVVM](https://img.shields.io/badge/MVVM-FF6F00?style=flat-square) ![Repository](https://img.shields.io/badge/Repository_Pattern-00897B?style=flat-square) |
| **Local DB** | ![Room](https://img.shields.io/badge/Room_2.6.1-3DDC84?style=flat-square&logo=android&logoColor=white) |
| **Remote** | ![Firestore](https://img.shields.io/badge/Cloud_Firestore-FFCA28?style=flat-square&logo=firebase&logoColor=black) ![Auth](https://img.shields.io/badge/Firebase_Auth-FFCA28?style=flat-square&logo=firebase&logoColor=black) |
| **Maps** | ![Maps](https://img.shields.io/badge/Google_Maps_SDK-4285F4?style=flat-square&logo=google-maps&logoColor=white) ![Location](https://img.shields.io/badge/Fused_Location-4285F4?style=flat-square&logo=google&logoColor=white) |
| **Analytics** | ![Analytics](https://img.shields.io/badge/Firebase_Analytics-FFCA28?style=flat-square&logo=firebase&logoColor=black) ![Crashlytics](https://img.shields.io/badge/Crashlytics-FFCA28?style=flat-square&logo=firebase&logoColor=black) |
| **Async** | ![Coroutines](https://img.shields.io/badge/Coroutines-7F52FF?style=flat-square&logo=kotlin&logoColor=white) ![LiveData](https://img.shields.io/badge/LiveData-3DDC84?style=flat-square&logo=android&logoColor=white) |
| **Navigation** | ![NavComponent](https://img.shields.io/badge/Navigation_Component_2.7.7-3DDC84?style=flat-square&logo=android&logoColor=white) |
| **Build** | ![Gradle](https://img.shields.io/badge/Gradle_8.2.2_(Kotlin_DSL)-02303A?style=flat-square&logo=gradle&logoColor=white) |

</div>

<br/>

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture with a **Repository Pattern** for clean separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                        UI Layer                         │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌────────┐  │
│  │   Home   │  │  Search  │  │  Stores  │  │Reminder│  │
│  │ Fragment │  │ Fragment │  │ Fragment │  │Fragment│  │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘  └───┬────┘  │
│       │              │              │             │      │
│  ┌────┴─────┐  ┌────┴─────┐  ┌────┴─────┐  ┌───┴────┐  │
│  │   Home   │  │  Search  │  │  Store   │  │Reminder│  │
│  │ViewModel │  │ViewModel │  │ViewModel │  │  VM    │  │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘  └───┬────┘  │
├───────┼──────────────┼──────────────┼────────────┼──────┤
│       │         Data Layer         │             │      │
│  ┌────┴──────────────┴──────┐ ┌────┴─────┐ ┌───┴────┐  │
│  │   MedicineRepository     │ │  Store   │ │Reminder│  │
│  │                          │ │   Repo   │ │  Repo  │  │
│  └────┬─────────────┬───────┘ └────┬─────┘ └───┬────┘  │
│       │             │              │            │       │
│  ┌────┴─────┐  ┌────┴─────┐  ┌────┴─────┐     │       │
│  │   Room   │  │   Seed   │  │Firestore │     │       │
│  │ Database │  │   Data   │  │   Sync   │     │       │
│  └──────────┘  └──────────┘  └──────────┘     │       │
└───────────────────────────────────────────────────────┘
```

### 📂 Package Structure

```
com.janaushadhi.finder/
│
├── 📁 data/
│   ├── 📁 db/                          # Room Database Layer
│   │   ├── AppDatabase.kt              # Database with 4 tables
│   │   ├── MedicineDao.kt              # Medicine CRUD + search
│   │   ├── FavoriteDao.kt              # Favorites management
│   │   ├── ReminderDao.kt              # Reminder operations
│   │   └── SearchHistoryDao.kt         # Search history tracking
│   │
│   ├── 📁 model/                       # Entity Classes
│   │   ├── Medicine.kt                 # Medicine with pricing logic
│   │   ├── Store.kt                    # Store location data
│   │   ├── Favorite.kt                 # Favorite reference
│   │   ├── Reminder.kt                 # Reminder scheduling
│   │   └── SearchHistory.kt            # Search history entry
│   │
│   ├── 📁 repository/                  # Data Repositories
│   │   ├── MedicineRepository.kt       # Medicine data operations
│   │   ├── StoreRepository.kt          # Local store logic
│   │   ├── FirestoreStoreRepository.kt # Cloud store sync
│   │   └── ReminderRepository.kt       # Reminder operations
│   │
│   └── SeedData.kt                     # 500+ pre-loaded medicines
│
├── 📁 ui/
│   ├── 📁 home/                        # Home Dashboard
│   ├── 📁 search/                      # Medicine Search & Results
│   ├── 📁 detail/                      # Medicine Detail View
│   ├── 📁 store/                       # Store Locator + Map
│   ├── 📁 savings/                     # Savings Calculator
│   ├── 📁 favorites/                   # Favorites List
│   └── 📁 reminder/                    # Reminder Management
│
├── 📁 util/
│   ├── AnalyticsHelper.kt              # Firebase event tracking
│   ├── FuzzySearch.kt                  # Typo-tolerant search algo
│   ├── NotificationHelper.kt           # Notification management
│   ├── ReminderReceiver.kt             # Alarm broadcast receiver
│   ├── BootReceiver.kt                 # Reschedule on boot
│   └── Extensions.kt                   # Kotlin extensions
│
├── JanAushadhiApp.kt                   # Application (Firebase init)
├── MainActivity.kt                     # Main + Navigation Host
└── SplashActivity.kt                   # Animated Splash Screen
```

<br/>

## 🚀 Getting Started

### Prerequisites

| Requirement | Version |
|:---|:---|
| Android Studio | Hedgehog (2023.1.1)+ |
| JDK | 17 |
| Android SDK | API 34 |
| Google Maps API Key | [Get one here →](https://developers.google.com/maps/documentation/android-sdk/get-api-key) |
| Firebase Project | [Setup guide →](https://firebase.google.com/docs/android/setup) |

### ⚡ Quick Setup

```bash
# 1. Clone the repository
git clone https://github.com/venkatadriV/Jan-Aushadhi-Finder.git
cd Jan-Aushadhi-Finder

# 2. Open in Android Studio
# File → Open → Select the project folder

# 3. Build & Run
./gradlew assembleDebug
```

### 🔑 Configuration

<details>
<summary><b>Google Maps API Key</b></summary>

In `app/src/main/AndroidManifest.xml`, replace the API key:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_GOOGLE_MAPS_API_KEY" />
```

</details>

<details>
<summary><b>Firebase Setup</b></summary>

1. Create a project in [Firebase Console](https://console.firebase.google.com/)
2. Register your Android app with package name `com.janaushadhi.finder`
3. Download `google-services.json` and place it in the `app/` directory
4. Enable **Firestore**, **Analytics**, and **Crashlytics** in the console

</details>

<br/>

## 📱 App Screens

| Screen | Description |
|:---|:---|
| 🏠 **Home** | Dashboard with quick actions, statistics, facts carousel, and recent searches |
| 🔍 **Search** | Medicine search with fuzzy matching, category filters, and search history |
| 💊 **Detail** | Medicine info with branded vs generic pricing, savings %, and share option |
| 🗺️ **Stores** | Google Maps with nearby Jan-Aushadhi Kendras, directions, and store details |
| 💰 **Savings** | Calculator showing monthly/yearly savings by switching to generics |
| ❤️ **Favorites** | Saved medicines for quick access |
| ⏰ **Reminders** | Medicine refill reminders with notification scheduling |

<br/>

## 🔑 Key Highlights

- 🏗️ **Clean Architecture** — MVVM + Repository Pattern for maintainability
- 📴 **Offline-First** — Full functionality with Room DB, Firestore sync when online
- 🔍 **Smart Search** — Custom fuzzy search algorithm tolerant to typos
- 🌙 **Dark Mode** — Complete dark theme with Material You design
- 🔔 **Reliable Notifications** — Exact alarms with boot-persistent scheduling
- 📊 **Analytics Ready** — Firebase Analytics + Crashlytics for production monitoring
- 💊 **500+ Medicines** — Pre-seeded database with real medicine data
- 🗺️ **9000+ Stores** — Comprehensive store directory across India

<br/>

## 🤝 Contributing

Contributions make this project better! Here's how you can help:

1. **Fork** the repository
2. **Create** your feature branch
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit** your changes
   ```bash
   git commit -m "feat: add amazing feature"
   ```
4. **Push** to the branch
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open** a Pull Request

<br/>

## 📄 License

Distributed under the **MIT License**. See [`LICENSE`](LICENSE) for more information.

<br/>

## 🙏 Acknowledgments

- [**PMBJP**](https://janaushadhi.gov.in/) — Pradhan Mantri Bhartiya Janaushadhi Pariyojana
- [**BPPI**](https://janaushadhi.gov.in/) — Bureau of Pharma PSUs of India
- [**Google**](https://developers.google.com/) — Maps SDK, Firebase, Material Design
- [**JetBrains**](https://www.jetbrains.com/) — Kotlin & IntelliJ Platform

<br/>

---

<div align="center">

  **Built with ❤️ for affordable healthcare in India**

  <br/>

  [![GitHub](https://img.shields.io/badge/venkatadriV-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/venkatadriV)

  <br/>

  ⭐ **If this project helped you, consider giving it a star!** ⭐

</div>
