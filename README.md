# 🛍️ Online Shop Application

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

*A modern, feature-rich Android e-commerce application built with cutting-edge technologies*

[![GitHub stars](https://img.shields.io/github/stars/mahdizaredev/OnlineShopApplication?style=social)](https://github.com/mahdizaredev/OnlineShopApplication)
[![GitHub forks](https://img.shields.io/github/forks/mahdizaredev/OnlineShopApplication?style=social)](https://github.com/mahdizaredev/OnlineShopApplication)
[![GitHub issues](https://img.shields.io/github/issues/mahdizaredev/OnlineShopApplication)](https://github.com/mahdizaredev/OnlineShopApplication/issues)
[![GitHub license](https://img.shields.io/github/license/mahdizaredev/OnlineShopApplication)](https://github.com/mahdizaredev/OnlineShopApplication/blob/main/LICENSE)

</div>

---

## ✨ Features

### 🏠 **Core Functionality**
- **User Authentication** - Secure login and registration system
- **Product Catalog** - Browse products with categories, colors, and sizes
- **Shopping Cart** - Add/remove items with real-time updates
- **Payment Processing** - Secure payment gateway integration
- **Order Management** - Track orders and view invoices
- **User Profile** - Manage personal information and preferences

### 🎨 **User Experience**
- **Modern UI/UX** - Built with Material Design 3 principles
- **Responsive Design** - Optimized for all screen sizes
- **Smooth Navigation** - Intuitive app flow with bottom navigation
- **Dark/Light Theme** - Adaptive theming system
- **Edge-to-Edge Design** - Immersive full-screen experience

### 🔧 **Technical Excellence**
- **MVVM Architecture** - Clean separation of concerns
- **Dependency Injection** - Hilt for efficient dependency management
- **Local Database** - Room database for offline data persistence
- **API Integration** - Retrofit for seamless backend communication
- **Image Loading** - Glide for efficient image management
- **Navigation** - Jetpack Navigation Compose for smooth transitions

---

## 🚀 Technology Stack

| Category | Technology | Version |
|----------|------------|---------|
| **Language** | Kotlin | 2.2.0 |
| **UI Framework** | Jetpack Compose | 2025.07.00 |
| **Architecture** | MVVM + Repository Pattern | - |
| **Dependency Injection** | Hilt (Dagger) | 2.57 |
| **Database** | Room | 2.7.2 |
| **Networking** | Retrofit + GSON | 3.0.0 |
| **Image Loading** | Glide (Landscapist) | 2.5.1 |
| **Navigation** | Navigation Compose | 2.9.2 |
| **Minimum SDK** | Android 5.0 (API 21) | - |
| **Target SDK** | Android 14 (API 36) | - |

---

## 📱 Screenshots

<div align="center">

| Splash Screen | Home Screen | Products |
|:---:|:---:|:---:|
| ![Splash](https://via.placeholder.com/300x600/3DDC84/FFFFFF?text=Splash+Screen) | ![Home](https://via.placeholder.com/300x600/4285F4/FFFFFF?text=Home+Screen) | ![Products](https://via.placeholder.com/300x600/FF5722/FFFFFF?text=Products) |

| Shopping Cart | User Profile | Payment |
|:---:|:---:|:---:|
| ![Cart](https://via.placeholder.com/300x600/4CAF50/FFFFFF?text=Shopping+Cart) | ![Profile](https://via.placeholder.com/300x600/9C27B0/FFFFFF?text=User+Profile) | ![Payment](https://via.placeholder.com/300x600/FF9800/FFFFFF?text=Payment) |

*Screenshots coming soon - Currently using placeholders*

</div>

---

## 🏗️ Architecture

```
app/
├── 📁 api/           # API interfaces and network layer
├── 📁 config/        # Application configuration
├── 📁 db/           # Room database and DAOs
├── 📁 models/       # Data models and entities
├── 📁 modules/      # Hilt modules for DI
├── 📁 repositories/ # Data repositories
├── 📁 ui/          # UI components and screens
│   ├── components/  # Reusable UI components
│   ├── screens/     # App screens
│   └── theme/       # App theming
├── 📁 utils/        # Utility classes
└── 📁 viewmodels/   # ViewModels for UI logic
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 36 (Android 14)
- Minimum SDK: API 21 (Android 5.0)
- Java 17 or later

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/mahdizaredev/OnlineShopApplication.git
   cd OnlineShopApplication
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Open the project folder
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`
   - Select your target device and wait for the app to install

### Build Variants
- **Debug** - Development build with debugging enabled
- **Release** - Production build with ProGuard optimization

---

## 🔧 Configuration

### Environment Setup
The app is configured to work with the online shop backend at `onlineshopholosen.ir`. Make sure you have:
- Internet connectivity
- Valid API endpoints configured
- Proper authentication credentials

### Customization
- Modify `app/src/main/res/values/` for app branding
- Update `app/src/main/java/com/mahdizaredev/onlineshop/config/` for API configuration
- Customize themes in `app/src/main/java/com/mahdizaredev/onlineshop/ui/theme/`

---

## 📊 Performance Features

- **Multi-ABI Support** - Optimized for x86, x86_64, ARM, and ARM64
- **Universal APK** - Single APK for all architectures
- **ProGuard Optimization** - Code shrinking and obfuscation
- **Resource Optimization** - Efficient resource management
- **Memory Management** - Optimized image loading and caching

---

## 🧪 Testing

The project includes comprehensive testing setup:
- **Unit Tests** - JUnit 4 for business logic
- **UI Tests** - Espresso for UI automation
- **Compose Tests** - Compose testing framework
- **Instrumentation Tests** - Android device testing

Run tests with:
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Contribution Guidelines
- Follow Kotlin coding conventions
- Use meaningful commit messages
- Add tests for new functionality
- Update documentation as needed
- Follow Material Design principles

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Developer

**Mahdi Zare** - *Lead Android Developer*

- 🌐 [Website](https://mahdizaredev.ir)
- 📧 [Email](mailto:contact@mahdizaredev.ir)
- 💼 [LinkedIn](https://linkedin.com/in/mahdizaredev)
- 🐦 [Twitter](https://twitter.com/mahdizaredev)

---

## 🙏 Acknowledgments

- **Google** - For Android and Jetpack Compose
- **JetBrains** - For Kotlin language
- **Material Design** - For design guidelines
- **Open Source Community** - For amazing libraries and tools

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

Made with ❤️ by [Mahdi Zare](https://github.com/mahdizaredev)

</div>
