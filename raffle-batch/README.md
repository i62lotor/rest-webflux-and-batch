# Batch Job
Responsable de realizar la rifa (sorteo) y comunicarlo

## Proceso
### 1. Lee los posibles premios
Lee los premios del fichero resources/rewards.csv

Ejemplo de fichero:
```
1000,euros
3000,euros
5000,euros
```

### 2. Realiza el sorteo 
Aleatoriamente asigna un número a cada uno de los premios leídos del fichero rewards.csv.

Los números se definen en el fichero de propiedades:
```
raffle.initial.number=0
raffle.finish.number=999
```

### 3. Comunica los premios
Una vez se han asignado los premios (número y recompensa), se comunican. Se puede hacer de tres formas, según se especifique en el fichero de propiedades:
```
#Destination values: LOG, FILE, HTTP 
write.destination= LOG
```

- **LOG**: Los escribe por consola.
- **FILE**: Los escribe en un fichero target/test-outputs/awards.txt
- **HTTP**: Los envía vía HTTP POST a un servicio Rest. Se puede especifiar la propiedad __http.award.writer.url__ en el fichero de propiedades
