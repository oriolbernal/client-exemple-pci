# client-exemple-pci
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

> Client d'exemple pels integradors de la PCI.

Aquest projecte és un **client SOAP d'exemple** i una **guia d'integració** pels integradors
que volen consumir els diferents serveis de l'AOC a través de la PCI (Plataforma de
Col·laboració Interadministrativa).

Documentació genèrica de la PCI: https://github.com/ConsorciAOC/PCI
Documentació específica de cada servei: https://consorciaoc.github.io/

> ℹ️ **No és codi de producció**: l'objectiu és didàctic. Mostra com construir, signar i
> enviar les peticions perquè us pugueu fer la vostra pròpia integració.

## Índex
- [Requisits](#requisits)
- [Conceptes clau (glossari)](#conceptes-clau-glossari)
- [Estructura del projecte](#estructura-del-projecte)
- [Configuració](#configuració)
- [Provar sense credencials: mode *preview*](#provar-sense-credencials-mode-preview)
- [Generació de codi JAXB](#generació-de-codi-jaxb)
- [Exemple d'ús real](#exemple-dús-real)
- [Catàleg de serveis](#catàleg-de-serveis)
- [Resolució de problemes (FAQ)](#resolució-de-problemes-faq)

## Requisits
### Java
Per compilar i utilitzar el codi cal la **JDK 1.8 (Java 8) o superior**.
- jdk8: https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html

El projecte genera bytecode compatible amb Java 1.8 (`sourceCompatibility`/`targetCompatibility = '1.8'`).
Si es compila amb una JDK 9 o superior, s'usa l'opció `--release 8` per garantir la
compatibilitat real amb l'API de Java 8.

### Gradle
El projecte incorpora un *wrapper* de Gradle (`./gradlew`), de manera que no cal tenir
Gradle instal·lat. Si el voleu instal·lar localment: https://gradle.org/install/

### Dades CAOC
Per consumir els serveis del CAOC cal haver estat donat d'alta prèviament als nostres
entorns. Podeu sol·licitar l'alta a https://www.aoc.cat/serveis/. Amb l'alta se us
assignaran els permisos i necessitareu:
- Codi del servei, de la modalitat i de la finalitat
- El certificat digital amb el qual signareu les peticions XML (XMLDSIG)
- Executar el codi des d'una IP habilitada

> 💡 Podeu explorar i provar el projecte **sense estar d'alta** gràcies al
> [mode *preview*](#provar-sense-credencials-mode-preview).

## Conceptes clau (glossari)
| Concepte | Descripció | On es configura |
|----------|------------|-----------------|
| **Servei** | El servei de la PCI que voleu consumir (e-NOTUM, Cadastre…). | `enum Serveis` |
| **Modalitat** | Variant/operació concreta dins d'un servei. | `enum OperacioXxx` |
| **Finalitat** | Motiu de la consulta (`PROVES` o `REGISTRE`). | `enum Finalitat` |
| **Entorn** | Entorn de destinació: `DEV`, `PRE` o `PRO`. | `enum Entorn` |
| **Frontal** | Mode de comunicació: síncron o asíncron. | `enum Frontal` |
| **Codi ens (INE10)** | Identificador de l'ens requeridor. | `client.properties` |

## Estructura del projecte
```
src/main/java/cat/aoc/client_pci/
├── api/                 # Nucli reutilitzable del client
│   ├── ClientPCI.java       # Client SOAP genèric (signatura, MTOM, enviament)
│   ├── clients/Serveis.java # Catàleg de serveis i fàbrica de clients
│   ├── model/               # Entorn, Frontal, Finalitat, Cluster
│   └── soap/                # Interceptors de signatura i de logging
├── samples/             # Exemples d'ús (un PeticionBuilder per servei)
│   ├── PeticioPreview.java  # Genera l'XML d'una petició SENSE enviar-la
│   └── serveis/...          # Constructors de peticions per servei
└── utils/               # Utilitats (lectura de propietats, dates XML)
```
Els esquemes XSD viuen a `src/main/resources/xsd` i es converteixen a classes Java amb la
tasca `jaxb` (vegeu més avall).

## Configuració
La configuració es divideix en dos fitxers. Es proporcionen plantilles `*.example`;
copieu-les sense el sufix i ompliu-les amb les vostres dades:

```bash
cp src/main/resources/client.properties.example   src/main/resources/client.properties
cp src/main/resources/keystore.properties.example src/main/resources/keystore.properties
```

> 🔒 Els fitxers reals `client.properties`, `keystore.properties` i qualsevol certificat
> (`*.p12`, `*.jks`, `*.pfx`) estan ignorats per git (vegeu `.gitignore`): **no els pugeu mai** al repositori.

- **`client.properties`** — dades de negoci de la petició (codi ens, emissor, sol·licitant, funcionari).
- **`keystore.properties`** — configuració del certificat de signatura (proveïdor, àlies, tipus, contrasenya i ruta del fitxer).

Cada camp està documentat dins de les plantilles.

## Provar sense credencials: mode *preview*
La classe [`PeticioPreview`](src/main/java/cat/aoc/client_pci/samples/PeticioPreview.java)
construeix i serialitza una petició a XML **sense enviar-la**, de manera que podeu veure
exactament què s'envia abans d'estar d'alta i sense necessitat de certificat ni connectivitat.

```java
Properties props = PropertiesReader.loadFromClasspath("client.properties.example");
Peticion peticion = new PeticionBuilderEnotum(props).build(OperacioEnotum.CERCA, Finalitat.PROVES);
System.out.println(PeticioPreview.toXml(Serveis.ENOTUM, peticion));
```

La classe inclou un `main` executable que imprimeix l'exemple anterior. La sortida té aquest aspecte:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns2:procesa xmlns="http://gencat.net/scsp/esquemes/peticion" xmlns:ns2="http://www.openuri.org/">
    <Peticion>
        <Atributos>
            <IdPeticion>9821920002-ENOTUM-1781711795987</IdPeticion>
            <NumElementos>1</NumElementos>
            <CodigoCertificado>ENOTUM</CodigoCertificado>
            <CodigoProducto>ENOTUM</CodigoProducto>
            <DatosAutorizacion>
                <IdentificadorSolicitante>9821920002</IdentificadorSolicitante>
                <NombreSolicitante>Ajuntament</NombreSolicitante>
                <Finalidad>PROVES</Finalidad>
            </DatosAutorizacion>
            ...
        </Atributos>
        <Solicitudes>...</Solicitudes>
    </Peticion>
</ns2:procesa>
```

El test [`PeticioPreviewTest`](src/test/java/cat/aoc/client_pci/samples/PeticioPreviewTest.java)
exercita aquest flux i **s'executa sense credencials**, de manera que serveix de comprovació
ràpida que el projecte compila i funciona.

## Generació de codi JAXB
Els XSD de `src/main/resources/xsd` (part genèrica de la PCI i part específica de cada
servei) es converteixen a classes Java amb la tasca `jaxb`:
```bash
./gradlew jaxb
```
La compilació depèn d'aquesta tasca, així que s'executa automàticament abans de compilar.
Per agilitzar les *builds* podeu comentar aquesta línia al `build.gradle`:
```groovy
compileJava.dependsOn jaxb
```
Les classes generades es creen a `src/main/java/generated` i **no es versionen**.

## Exemple d'ús real
Un cop configurat el certificat i estant d'alta:
```java
// 1. Obtenir el client del servei desitjat
ClientPCI client = Serveis.ENOTUM.getClient(Entorn.PRE, Frontal.SINCRON, "src/main/resources/keystore.properties");

// 2. Construir una petició
Properties clientProperties = PropertiesReader.load("src/main/resources/client.properties");
Peticion peticion = new PeticionBuilderEnotum(clientProperties).build(OperacioEnotum.CERCA, Finalitat.PROVES);

// 3. Enviar la petició
Respuesta respuesta = client.send(peticion);
```
Trobareu més exemples a l'apartat de tests del projecte.

## Catàleg de serveis
| Categoria | Serveis (`Serveis`) |
|-----------|---------------------|
| Generals | `BOE`, `EACAT`, `ENOTUM`, `ETAULER`, `OVER`, `REPRESENTA`, `SCT_DEV`, `SIR2` |
| VO · Estat | `CADASTRE`, `DEPENDENCIA`, `DGP`, `DGT`, `ESTRANGERIA`, `IGAE`, `INSS`, `MEPSYD`, `NOTARIS`, `REGISTRE_CIVIL`, `SEPE` |
| VO · Generalitat | `TFN`, `TFM`, `SOC`, `RCA`, `GRAU_DISCAPACITAT`, `ATC`, `REGISTRE_ENTITATS`, `RGC`, `RPE`, `PADRO_HISTORIC` |
| VO · Local | `PADRO` |

Les operacions (modalitats) de cada servei estan definides al seu `enum OperacioXxx`
corresponent dins de `samples/serveis/...`.

## Resolució de problemes (FAQ)
- **`FileNotFoundException: ...client.properties`** — Encara no heu copiat les plantilles.
  Vegeu [Configuració](#configuració).
- **`Could not load the keystore`** — Reviseu la ruta, el tipus, l'àlies i la contrasenya
  del certificat a `keystore.properties`. Useu barres `/` a les rutes.
- **Els tests `*Test` fallen amb errors de connexió** — És esperat: fan crides reals als
  serveis de l'AOC i requereixen alta, certificat i IP habilitada. Per provar sense res
  d'això, useu el [mode *preview*](#provar-sense-credencials-mode-preview).
- **Vull veure la petició i la resposta SOAP** — El nivell de log `cat.aoc.client_pci`
  està a `DEBUG` (vegeu `src/main/resources/logback.xml`); el `LoggerInterceptor` imprimeix
  els missatges.

> ⚠️ Els tests poden estar acoblats a les dades de `client.properties`. Si canvieu el
> requeridor (codi INE10), és possible que el resultat no sigui l'esperat.
