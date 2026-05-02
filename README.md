# 💬 Java Chat Application (Client-Server)

A simple **console-based chat application** built using Java **Socket Programming**.
This project allows multiple clients to connect to a server and communicate in real-time.

---

## 📌 Features

* Start server and accept multiple clients
* Real-time messaging between clients
* Broadcast messages to all connected users
* Server can also send messages
* Multi-threaded handling of clients

---

## 🛠️ Technologies Used

* Java
* Socket Programming (`java.net`)
* Multithreading (`Thread`)
* I/O Streams (`BufferedReader`, `PrintWriter`)

---

## 📂 Project Structure

```id="structure2"
ChatApp.java
```

---

## ▶️ How to Run

### 1️⃣ Compile the program

```id="compile2"
javac ChatApp.java
```

---

### 2️⃣ Start the Server

```id="runserver"
java ChatApp
```

Choose:

```
1. Start Server
```

---

### 3️⃣ Start Client(s)

Open **new terminal windows** and run again:

```id="runclient"
java ChatApp
```

Choose:

```
2. Start Client
```

---

## ⚙️ Configuration

```id="config2"
Server Port : 5000
Host        : localhost
```

You can modify these values inside the code if needed.

---

## 🧠 How It Works

* The server listens on a port using `ServerSocket`
* Clients connect using `Socket`
* Each client is handled in a separate thread (`ClientHandler`)
* Messages are broadcast to all connected clients
* Server can also send messages using a separate thread

---

## 🖥️ Sample Output

### Server:

```id="serverout"
Server started...
Client connected
Received: Hello from client!
```

### Client:

```id="clientout"
Connected to server!
Hello everyone!
```

---

## ⚠️ Notes

* Run server **before** starting clients
* Ensure port `5000` is free
* Works on local machine (`localhost`)
* No encryption (basic learning project)

---

## 🚀 Future Improvements

* Add usernames for clients
* Private messaging
* GUI using JavaFX / Swing
* Message timestamps
* Encryption (secure chat)
* Chat history storage

---

## 👩‍💻 Author

**Meenakshi Srinivasan**
Aspiring Java Developer 💻

---

## 📄 License

This project is open-source and free to use.
