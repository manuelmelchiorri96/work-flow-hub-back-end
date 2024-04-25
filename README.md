# Progetto Finale Backend: Work_Flow_Hub

Questo è il backend del progetto finale, che gestisce la logica di business e fornisce un'API per interagire con il database.

## Tecnologie utilizzate

- Java
- Jakarta EE (Jakarta Persistence API)
- Maven
- JAX-RS (Java API for RESTful Web Services)

## Requisiti

- Java Development Kit (JDK) 8 o versioni successive
- Maven
- Un server Jakarta EE (ad esempio Apache TomEE)
- Un database MySQL

## Configurazione

1. Clonare il repository:

   ```bash
   git clone https://github.com/manuelmelchiorri96/work-flow-hub-back-end.git

2. Importare il progetto in un IDE compatibile con Maven (ad esempio IntelliJ IDEA o Eclipse).

3. Configurare il server TomEE nel tuo IDE e avviare il server.

4. Creare un database MySQL e configurare le credenziali di accesso nel file `persistence.xml` come nell' esempio. 
   Il `persistence.xml` nel progetto è configurato per l'accesso al DB remoto.

```xml
<persistence-unit name="persistence_unit">
    <properties>
        <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
        <property name="hibernate.connection.url" value="jdbc:mysql://localhost:8889/work_flow_hub"/>
        <property name="hibernate.connection.driver_class" value="com.mysql.cj.jdbc.Driver"/>
        <property name="hibernate.connection.username" value="root"/>
        <property name="hibernate.connection.password" value="root"/>
    </properties>
</persistence-unit>
```

5. Avviare il server e verificare che sia in esecuzione correttamente.

## Struttura del progetto

Il progetto è organizzato nei seguenti package:

- `com.manuel.work.flow.hub.entity`: Contiene le entità JPA che rappresentano le tabelle del database.
- `com.manuelwork.flow.hub.repository`: Contiene le interfacce e le implementazioni dei repository per l'accesso ai dati.
- `com.manuel.work.flow.hub.service`: Contiene le interfacce e le implementazioni dei servizi che gestiscono la logica di business.
- `com.manuel.work.flow.hub.resources`: Contiene le classi che definiscono le risorse RESTful e gestiscono le richieste HTTP.
- `com.manuel.work.flow.hub.exception`: Contiene le classi che definiscono eccezioni custom.
- `com.manuel.work.flow.hub.configuration`: Contiene la classe per configurare il front-end ad interagire con il back-end.
- `com.manuel.work.flow.hub.validation`: Contiene la classe per la validazione dei campi.
- `com.manuel.work.flow.hub.vo`: Contiene classi che rappresentano le entity per la lettura dal DB.
- `com.manuel.work.flow.hub.dto`: Contiene classi che rappresentano le entity per l'inserimento nel DB.

## API

Il backend fornisce le seguenti API:

Dipendenti:
- `POST /api/dipendenti/login`: Permette il login di un dipendente.
- `POST /api/dipendenti/register`: Permette la registrazione di un nuovo dipendente.
- `PUT /api/dipendenti/update`: Permette di aggiornare i dati di un dipendente esistente.
- `DELETE /api/dipendenti/{idDipendente}`: Elimina l'account di un dipendente specificato dall'ID.
- `GET /api/dipendenti/all`: Restituisce tutti i dipendenti.
- `GET /api/dipendenti/{idDipendente}`: Restituisce le informazioni di un dipendente specificato dall'ID.

Project Manager:
- `POST /api/project-manager/login`: Permette il login di un project manager.
- `POST /api/project-manager/register`: Permette la registrazione di un nuovo project manager.
- `PUT /api/project-manager/update`: Permette di aggiornare i dati di un project manager esistente.
- `DELETE /api/project-manager/{idProjectManager}`: Elimina l'account di un project manager specificato dall'ID.
- `GET /api/project-manager/{idProjectManager}`: Restituisce le informazioni di un project manager specificato dall'ID.

Progetti:
- `POST /api/progetti/save`: Salva un nuovo progetto.
- `PUT /api/progetti/update`: Aggiorna i dati di un progetto esistente.
- `DELETE /api/progetti/{idProgetto}`: Elimina un progetto specificato dall'ID.
- `GET /api/progetti/all`: Restituisce tutti i progetti.
- `GET /api/progetti/{idProgetto}`: Restituisce le informazioni di un progetto specificato dall'ID.

Attività:
- `POST /api/tasks/save`: Salva una nuova attività.
- `PUT /api/tasks/update`: Aggiorna i dati di un'attività esistente.
- `GET /api/tasks/all`: Restituisce tutte le attività.
- `GET /api/tasks/all-by-dipendente/{idDipendente}`: Restituisce tutte le attività associate a un dipendente.
- `GET /api/tasks/all-by-progetto/{idProgetto}`: Restituisce tutte le attività associate a un progetto.

## Licenza

Questo progetto è rilasciato sotto la licenza [MIT](LICENSE.txt).