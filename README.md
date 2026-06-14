# FoodDelivery_Gruppo12

## 1) Descrizione del progetto

Sistema di Gestione di Ordini per Piattaforma di Food Delivery. Progetto per il corso di Ingegneria del Software (a.a. 2025/26), Prof. Domenico Amalfitano. Gruppo 12.
FoodDelivery è un'applicazione Java desktop per la gestione di servizi di food delivery, dotata di interfaccia grafica (Swing) e persistenza dei dati su database relazionale.
Il progetto **FoodDelivery** consente di simulare l'intero ecosistema di un servizio di consegna a domicilio. L'applicazione permette agli utenti di effettuare il login o registrazione, ai clienti di esplorare i ristoranti aperti, consultare i relativi menù e ordinare i piatti desiderati, ai ristoratori di gestire il proprio ristorante e i propri ordini, e agli amministratori di valutare l'andamento complessivo del sistema. 

## 2) Prerequisiti da installare

Prima di avviare il progetto, assicurati che sul tuo sistema siano installati i seguenti componenti:

* **Java Development Kit (JDK):** Versione 21 o superiore.
* **Database Server:** MySQL Server (versione 8.0 o superiore).
* **IDE (Consigliato):** IntelliJ IDEA.
* **Strumento di Build:** Maven (solitamente già integrato in IntelliJ).
* **Client Database (Opzionale):** MySQL Workbench.

## 3) Configurazione del database

Per preparare l'ambiente del database, segui questi passaggi:

1. Avvia il tuo server MySQL locale.
2. Apri il tuo client di fiducia (es. MySQL Workbench) o il terminale MySQL.
3. Esegui il seguente comando SQL per creare lo schema vuoto:

```sql
CREATE DATABASE IF NOT EXISTS food_delivery_db;
```

## 4) Configurazione del persistence.xml

Per consentire a JPA di connettersi al tuo database MySQL locale, devi configurare le credenziali di accesso (username e password).

1. All'interno del progetto, naviga fino al file di configurazione situato nel percorso:
   `src/main/resources/META-INF/persistence.xml`
2. Apri il file e individua i tag `<property>` relativi alla connessione JDBC.
3. Modifica i valori inserendo la porta corretta del tuo MySQL, il tuo username e la tua password personale:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/food_delivery_db"/>
<property name="jakarta.persistence.jdbc.user" value="IL_TUO_USERNAME"/>
<property name="jakarta.persistence.jdbc.password" value="LA_TUA_PASSWORD"/>
```

## 5) Esecuzione del Main

Prima di avviare l'applicazione principale, è necessario popolare il database appena creato con dei dati di test (ristoranti iniziali, utenti, menù e piatti). Nel progetto è presente una classe dedicata a questo scopo.

### Tramite IntelliJ IDEA:
1. Nel pannello dei file a sinistra (**Project View**), naviga fino alla classe `MainInizializzaDatabase` (situata nel percorso `Database/MainInizializzaDatabase.java`).
2. Apri il file.
3. Clicca sul **triangolino verde (Play)** che compare a sinistra della riga: 
   `public static void main(String[] args)`
4. Seleziona la voce **Run 'MainInizializzaDatabase.main()'**.
5. Controlla la **Console** in basso: l'esecuzione creerà le tabelle e inserirà i dati iniziali di prova.
