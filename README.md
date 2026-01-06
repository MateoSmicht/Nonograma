# ⬛⬜ Nonogram Logic Game

> Aplicación de escritorio desarrollada en **Java** que implementa la lógica y jugabilidad de los puzles japoneses Nonogramas (Hanjie/Picross).

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)
![Logic](https://img.shields.io/badge/Logic-Matrix%20Validation-green?style=for-the-badge)

## 📋 Descripción

Este proyecto fue desarrollado para la materia **Programación III** de la Licenciatura en Sistemas (UNGS). El software recrea la experiencia completa de un Nonograma, donde el jugador debe rellenar celdas en una grilla basándose en pistas numéricas ubicadas en los bordes, las cuales indican la longitud de los bloques consecutivos a colorear.

El sistema no solo gestiona la interfaz gráfica, sino que implementa un **motor de validación** que verifica en tiempo real o bajo demanda si la configuración actual del tablero cumple con las restricciones lógicas de las filas y columnas.

## 🚀 Funcionalidades

* **Tablero Interactivo:** Grilla dinámica que responde a eventos del mouse para pintar o marcar celdas.
* **Motor de Validación:** Algoritmo que compara el estado actual de la matriz del usuario contra las pistas numéricas (restricciones) para determinar la victoria.
* **Carga de Niveles:** Capacidad de levantar configuraciones de juego.
* **Interfaz Gráfica (GUI):** Desarrollada con Java Swing, separando la lógica del juego de la vista.

## 🧠 Desafío Técnico: Validación de Filas y Columnas

El núcleo del proyecto reside en la lógica para interpretar si una fila es correcta. El algoritmo debe:
1.  Recorrer la matriz de la fila/columna.
2.  Agrupar las celdas pintadas consecutivas.
3.  Comparar esos grupos con el array de "pistas" (ej: `[3, 1]`).
4.  Validar si coinciden exactamente en cantidad y orden.


🛠️ Tecnologías
Lenguaje: Java SE

Interfaz: Java Swing (JFrame, JPanel, Graphics)

Arquitectura: Separación entre Lógica (Modelo) y Vista (GUI).

📸 Capturas de Pantalla
<img width="664" height="502" alt="Captura" src="https://github.com/user-attachments/assets/307e2b8c-9bbb-43bb-8340-8b9555cc5659" />
<img width="657" height="506" alt="Captura" src="https://github.com/user-attachments/assets/016f04e0-02e9-46cb-8afb-236413abde46" />


👤 Autor
Mateo Damian Smicht

LinkedIn: www.linkedin.com/in/mateosmicht

Email: mateosmicht13@gmail.com
