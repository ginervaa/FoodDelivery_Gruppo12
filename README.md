# FoodDelivery_Gruppo12

## 1) Descrizione del progetto

Sistema di Gestione di Ordini per Piattaforma di Food Delivery. Progetto per il corso di Ingegneria del Software (a.a. 2025/26), Prof. Domenico Amalfitano. Gruppo 12.
FoodDelivery è un'applicazione Java desktop per la gestione di servizi di food delivery, dotata di interfaccia grafica (Swing) e persistenza dei dati su database relazionale.
Il progetto **FoodDelivery** consente di simulare l'intero ecosistema di un servizio di consegna a domicilio. L'applicazione permette agli utenti di effettuare il login o registrazione, ai clienti di esplorare i ristoranti aperti, consultare i relativi menù e ordinare i piatti desiderati, ai ristoratori di gestire il proprio ristorante e i propri ordini, e agli amministratori di valutare l'andamento complessivo del sistema. 

---

## 2) Prerequisiti da installare

Prima di avviare il progetto, assicurati che sul tuo sistema siano installati i seguenti componenti:

* **Java Development Kit (JDK):** Versione 21 o superiore.
* **Database Server:** MySQL Server (versione 8.0 o superiore).
* **IDE (Consigliato):** IntelliJ IDEA.
* **Strumento di Build:** Maven (solitamente già integrato in IntelliJ).
* **Client Database (Opzionale):** MySQL Workbench.

---

## 3) Configurazione del database

Per preparare l'ambiente del database, segui questi passaggi:

1. Avvia il tuo server MySQL locale.
2. Apri il tuo client di fiducia (es. MySQL Workbench) o il terminale MySQL.
3. Esegui il seguente comando SQL per creare lo schema vuoto:

```sql
CREATE DATABASE IF NOT EXISTS food_delivery_db;

## 4) 
