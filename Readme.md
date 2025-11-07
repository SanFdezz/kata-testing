# Kata Testing unitario (J-Unit y Mockito)

##¿Que se pide en esta kata?
Para poder realizar esta kata de testing unitario tienes la implementación todo el código ya dado, hay que testearlo.

La implementación está dividida en 3 carpetas:

- Common
- JUnit
- Mockito

### Las clases que tienes que testear son las que se llaman:
    - JUnitChefService 
    - MockitoChefService
### El resto de clases no hace falta testearlas.


## Pistas / Consejos
### El testeo de la clase JUnitChefService te será más fácil que el testeo de MockitoChefService, igual te será más cómodo dejar la clase de testeo de Mockito para el final.
### - Cuando crees los tests deben llamarse igual que la clase que vas a testear añadiendo la palabra Test al final por ejemplo:
      si tienes que testar la clase Chef, el test debería llamarse ChefTest
- Los tests tienen que ser completamente independientes de la implementación (a excepción de los modelos de datos:
  "Chef"/"ChefResponse"/"ChefEntity" no puedes usar las clases de la implementación en los tests)
### - Recuerda hacer un tests por flujo, es decir, si tienes un método que tienes 2 posibles respuestas tienes que tener 2 tests uno para cada respuesta
### - Cómo mínimo te tienen que salir 14 tests, si haces menos hay algun flujo que se te está pasando por alto.
### - Recuerda poner la estructura de los tests (Given --> When --> Then)
### - Al principio siempre es más fácil dividir la pantalla, ponerte la implementación a un lado y el test al otro e ir haciendo el tests mientras vas consultando la implementación al mismo tiempo.
### - Recuerda aplicar los principios SOLID siempre que sea posible.

### Recuerda que cuando uses mockitos, tienes distintas herramientas para hacer cosas similares, por ejemplo:
    - Puedes usar Mockito.When(xxxx.zzzz).thenReturn(yyyy) o doReturn.(yyyy).when(xxxx).zzzz
    - Además recuerda que no siempre tienes que dar respuesta a las peticiones que hagas, tienes la posibilidad de usar otras opciones (por ejemplo verify) cuando tengas métodos void que mockear.



## Solución
Todos los tests tienen que pasar en verde y de una sola ejecución, si te pones en el paquete src/test/java y haces click con el botón secundario puedes ejecutar todos los tests al mismo tiempo (si haces eso tienen que salirte cómo mínimo 15 tests)