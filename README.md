# SourceCodeMinimaps

SourceCodeMinimaps is an IntelliJ IDEA plugin that transforms your source code into a visual "minimap" image. It captures the essence of your code's structure and uploads it as a grayscale representation to a remote server.

## 🚀 Features

- **Code-to-Image Transformation**: Captures up to 128 lines of code from your active editor.
- **Grayscale Mapping**: Each character is intelligently mapped to a specific grayscale intensity (0-255) based on its type (letters, digits, whitespace).
- **Automated Upload**: Encodes the generated minimap into a Base64 PNG and prepares it for transmission to a centralized server.
- **Easy Access**: Trigger the capture directly from the Editor Popup Menu (right-click) or the Main Toolbar.

## 🛠️ How It Works

1. **Capture**: The plugin reads the first 128 lines of your current file.
2. **Process**: It iterates through every character, converting them into grayscale pixels using a specialized mapping service.
3. **Render**: A 128x128 grayscale `BufferedImage` is generated.
4. **Transmit**: The image is converted to Base64 and sent via an HTTP POST request along with the project name.

## 📦 Project Structure

- `actions.SendAction`: Handles the UI interaction and coordinates the capture process.
- `services.ImageService`: Contains the core logic for pixel mapping and image generation.
- `requests.PostImageRequest`: Manages the network communication with the backend server.
- `dto.ImageDTO`: Standardized data object for image transmission.

## 🔧 Installation & Setup

### Development
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Use the Gradle `Run Plugin` configuration to launch a development instance of the IDE with the plugin enabled.

### Configuration
Update the `url` constant in `src/main/java/requests/PostImageRequest.java` to point to your desired endpoint before building the plugin for production.

---

*Developed as a tool for visual source code analysis and remote synchronization.*
