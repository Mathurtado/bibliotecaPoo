# bibliotecaPoo

Proyecto para poner en práctica la Programación Orientada a Objetos (POO) en Java: un sistema de consola para gestionar el material y los préstamos de una biblioteca, con libros, revistas y DVD.

## Enunciado

> En la biblioteca tenemos libros, revistas y DVD. Todos tienen un código, un título y un año, y pueden estar disponibles o prestados.
>
> Cada tipo se presta por un tiempo distinto: libros 15 días, revistas 7 y DVD 3. Si alguien devuelve tarde, paga una multa por día de retraso: libro $1.000, revista $500 y DVD $2.000.
>
> Prestamos a estudiantes (máximo 3 préstamos activos) y profesores (máximo 5); los profesores tienen 50 % de descuento en las multas. Un usuario con multas pendientes no puede pedir préstamos hasta pagarlas. Las revistas no se prestan a estudiantes, solo se consultan en sala.
>
> Necesitamos registrar material y usuarios, prestar, devolver, pagar multas y ver qué tiene prestado cada usuario.

## Historias de usuario

1. Como bibliotecario, quiero registrar material (libro, revista o DVD) para tenerlo en el catálogo.
2. Como bibliotecario, quiero registrar usuarios (estudiante o profesor).
3. Como bibliotecario, quiero listar el material y ver cuál está disponible.
4. Como usuario, quiero pedir un préstamo de un material disponible.
5. Como usuario, quiero devolver un material y saber si generé multa.
6. Como usuario, quiero pagar mis multas.
7. Como bibliotecario, quiero ver los préstamos activos de un usuario.

## Diseño

| Clase | Tipo | Responsabilidad |
| --- | --- | --- |
| Biblioteca | Clase | Coordina todo: guarda las listas de materiales, usuarios y préstamos; presta, devuelve y valida |
| Material | Abstracta | Lo común de todo material: código, título, año y estado |
| Libro, Revista, DVD | Heredan de Material | Cada uno define sus días de préstamo y su multa por día |
| Usuario | Abstracta | Lo común de todo usuario: documento y multa pendiente |
| Estudiante, Profesor | Heredan de Usuario | Cada uno define su límite de préstamos y su descuento en multas |
| Prestamo | Clase | Une un material con un usuario y sus fechas; calcula la multa |

### Reglas por tipo

| Material | Días de préstamo | Multa por día |
| --- | --- | --- |
| Libro | 15 | $1.000 |
| Revista | 7 | $500 |
| DVD | 3 | $2.000 |

| Usuario | Límite de préstamos | Descuento en multas |
| --- | --- | --- |
| Estudiante | 3 | 0 % |
| Profesor | 5 | 50 % |

## Estructura del proyecto

```
bibliotecaPoo/
├── README.md
└── src/
    ├── modelo/     Biblioteca, Material, Libro, Revista, DVD, Usuario, Estudiante, Profesor, Prestamo
    └── consola/    Main y el menú
```

## Convenciones

- Nombres en español, sin tildes ni ñ.
- Clases con mayúscula inicial; atributos y métodos en camelCase; paquetes en minúscula.
- El paquete `modelo` no imprime nada en pantalla: devuelve mensajes y la consola los muestra.