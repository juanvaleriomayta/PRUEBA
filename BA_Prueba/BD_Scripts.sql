select * from Genero
GO
select * from Pais
GO
select * from Libro
GO
select * from Autor
GO
select * from Bibliotecario
GO
select * from Cliente
GO

/*Creando una Vista*/
CREATE VIEW vwDatosLibros
AS
select libro.tituloLibro, Libro.Autor_idAutor, Autor.apeAutor 
from Libro
INNER JOIN Autor
on Libro.Autor_idAutor = Autor.idAutor
/* Ver registro de una Vista*/
select * from Autor
SELECT * FROM Libro
SELECT * FROM Pais


/*Listar el nombre del autor, el libro que ha escrito, el genero y pais repectivo en una vista vwDatosAutor.*/

select Autor.nomAutor, Libro.idLibro
FROM Autor
INNER JOIN Libro

/* una vista listar la cantidad de libro por genero*/
CREATE VIEW vwGenLib
AS
    SELECT Genero.nomGenero as 'Genero', COUNT(Libro.Genero_idGenero) as 'Total'
    from Libro
        INNER JOIN Genero
        ON Libro.Genero_idGenero = Genero.idGenero
    GROUP BY Genero.nomGenero
GO
/*Ver Registro de Vista*/
SELECT * 
from vwGenLib
GO

/* Vista que liste la cantidad de libros por pais*/
SELECT Pais.nomPais AS 'Pais', COUNT(Pais.idPais) as 'Total'
from Libro
INNER JOIN Pais
on Libro.Pais_idPais = Pais.idPais
GROUP BY Libro.tituloLibro


/**/

select Prestamos.fecsalPrestamo, CONCAT(Bibliotecario.nomBibliotecario,',',Bibliotecario.apeBibliotecario) AS 'Bibliotecario',
Detalle_Prestamo.Libro_idLibro, Cliente.nomCliente AS 'Nombre Cliente'
from Prestamos
INNER JOIN Bibliotecario
ON Prestamos.Bibliotecario_idBibliotecario = Bibliotecario.idBibliotecario
INNER JOIN Detalle_Prestamo
ON Detalle_Prestamo.Prestamos_idPrestamo= Prestamos.idPrestamo
INNER JOIN Libro
ON Detalle_Prestamo.Libro_idLibro = Libro.idLibro
INNER JOIN Cliente
ON Prestamos.Cliente_idCliente = Cliente.idCliente

/*clientes cuantos libros a pedido*/


/*************************************************************************************************/

/*ULTIMA MODIFICACION EN GITHUB*/

select Prestamos.fecsalPrestamo, CONCAT(Bibliotecario.nomBibliotecario,',',Bibliotecario.apeBibliotecario) AS 'Bibliotecario',
Detalle_Prestamo.Libro_idLibro, Cliente.nomCliente AS 'Nombre Cliente'
from Prestamos
INNER JOIN Bibliotecario
ON Prestamos.Bibliotecario_idBibliotecario = Bibliotecario.idBibliotecario
INNER JOIN Detalle_Prestamo
ON Detalle_Prestamo.Prestamos_idPrestamo= Prestamos.idPrestamo
INNER JOIN Libro
ON Detalle_Prestamo.Libro_idLibro = Libro.idLibro
INNER JOIN Cliente
ON Prestamos.Cliente_idCliente = Cliente.idCliente
