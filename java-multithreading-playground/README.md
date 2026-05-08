
# 🧵 Java Multithreading Playground

A hands-on **Java multithreading learning** covering everything from **core concurrency concepts** to **modern Virtual Threads**.

This repo focuses on **clear examples, progressive learning, and practical understanding**, making it ideal for learning.

---

## 🚀 What This Project Covers

- Java threads & lifecycle
- Runnable vs Thread
- Callable & Future
- ExecutorService & thread pools
- CompletableFuture (async programming)
- Synchronization & thread safety
- Virtual Threads (Java 21+)

---

## 🧱 Project Structure

```text
multithreading-playground
├── basics
├── runnable
├── callable
├── executor
├── completablefuture
├── synchronization
├── virtualthreads
```

Each package focuses on one concurrency concept with simple, runnable examples.

---

## ✨ Highlights

- ✅ Step-by-step progression from basics to advanced topics  
- ✅ Practical examples (no theory-only code)  
- ✅ Virtual Threads vs Platform Threads comparison

---

## 🆕 Virtual Threads (Project Loom)

This project includes hands-on examples of **Virtual Threads (Java 21+)**:

- Lightweight JVM-managed threads  
- Cheap blocking (sleep, I/O)  
- Massive scalability without reactive complexity  

---

## 🛠 Requirements

| Feature | Requirement |
|------|------------|
| Core examples | Java 8+ |
| Virtual Threads | Java 21+ |
| IDE | IntelliJ / Eclipse / VS Code |

---

## ▶️ How to Run

1. Open the project in an IDE  
2. Use the appropriate Java version:
   - **Java 8–17** → Run all modules except `virtualthreads`
   - **Java 21+** → Run everything  
3. Execute individual `main()` methods  

---

## 🎯 Learning Outcome

By the end of this project, you will:

- Understand Java concurrency fundamentals  
- Know when to use threads, executors, or async programming  
- Clearly grasp the problem that Virtual Threads solve  
