# Enunciado del ejercicio:

Diseña una aplicación en Java 21 que simule un sistema de gestión de una biblioteca con una base de datos en memoria (
H2). La aplicación debe permitir agregar, buscar, listar y prestar libros, con validaciones y notificaciones. Implementa
los siguientes patrones de diseño: Singleton (para la conexión a la base de datos), Factory Method y Abstract Factory (
para crear diferentes tipos de libros y sus formatos), Builder (para construir objetos Libro complejos), Strategy (para
diferentes métodos de búsqueda), Observer (para notificar cambios en el estado de los libros), Decorator (para añadir
funcionalidades como préstamo), y Chain of Responsibility (para validar datos de libros). Asegúrate de incluir una clase
principal que demuestre el uso de estas funcionalidades.

# Requisitos:

- Usa Java 21 con una base de datos en memoria (H2).
- Implementa los patrones de diseño mencionados.
- La clase Libro debe tener atributos: id, titulo, autor, tipo (Ficción/No Ficción), formato (Físico/Digital), y
  estado (Disponible/Prestado).
- Proporciona métodos para:
  - Agregar un libro con validaciones (título no vacío, autor válido).
  - Buscar libros por título o autor usando diferentes estrategias.
  - Listar todos los libros.
  - Prestar un libro con notificación a observadores.
  - Implementar Clean Code,
  - Implementar SOLID

> 💡 **Importante:**
> Usa buenas prácticas como manejo de excepciones y cierre de recursos.

# Explicación de los patrones aplicados:

- [ ] Singleton: DatabaseConnection asegura una única instancia de la conexión a H2.
- [x] Factory Method: LibroFactory y sus implementaciones (FiccionFactory, NoFiccionFactory) crean libros según su tipo.
- [ ] Abstract Factory: AbstractLibroFactory (LibroFisicoFactory, LibroDigitalFactory) crea familias de libros según el
      formato (Físico/Digital).
- [x] Builder: LibroBuilder permite construir objetos Libro de manera flexible y legible.
- [x] Strategy: SearchStrategy (SearchByTitle, SearchByAuthor) define diferentes estrategias de búsqueda.
- [x] Observer: Observer y PrestamoObserver notifican cambios en el estado de los libros (por ejemplo, al prestar).
- [x] Decorator: `LoanDecorator` añade funcionalidad de préstamo sin modificar la clase `Book`.
- [x] Chain of Responsibility: Validador (ValidadorTitulo, ValidadorAutor) valida los datos del libro en una cadena.
- [x] Adapter: LegacyLibroAdapter adapta la clase LegacyLibro (con métodos incompatibles) para que implemente la interfaz
      `Book`, permitiendo integrar libros de un sistema antiguo en la biblioteca sin cambios en el código principal.
