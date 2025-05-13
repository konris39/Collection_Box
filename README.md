# Hi and welcome to my collection box app!

## Requirements:
- Java 17+
- Maven 3.x

# To Build and Run 

## 1. Clone the repository
```
git clone https://github.com/konris39/Collection_Box.git
```

After that go to project root folder collectionBox and run:
```
mvn clean package
```

## 2. Run the app using:
Jar:
```
java -jar target/collectionBox-0.0.1-SNAPSHOT.jar
```

or with the Maven:
```
mvn spring-boot:run
```

The app will start on http://localhost:8080

## H2 DB console is available at:
```
http://localhost:8080/h2-console
```
Username: 
sa

Password:


(By default there is no password)

## Endpoints

| Method | URL                  | Body / Params              | Description                                  |
| ------ | -------------------- | -------------------------- | ---------------------------------------------|
| POST   | `/boxes`             | none                       | Create new box                               |
| GET    | `/boxes`             | none                       | List all of the boxes                        |
| DELETE | `/boxes/{id}`        | path: id                   | Delete (unregister) a box                    |
| POST   | `/boxes/{id}/assign` | query: eventId={eventId}   | Assign box to an event                       |
| POST   | `/boxes/{id}/money`  | JSON body #1               | Add money to box                             |
| POST   | `/boxes/{id}/empty`  | none                       | Empty box into its assigned event (Transfer) |
| POST   | `/events`            | Json body #2               | Create new event                             |
| GET    | `/events/reports`    | none                       | Get all reports for events (balances)        |

JSON body #1:
```
{
  "currency": "EUR",
  "amount": 250.50
}
```

JSON body #2:
```
{
  "name": "Charity Event",
  "currency": "USD"
}
```

(Currency is enum, should choose from USD, EUR, PLN as they are already declared in enum, if any other needed declare it in enum file in /models)

