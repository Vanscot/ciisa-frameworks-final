# ciisa-frameworks-final
## Trabajo Final

### Informe sobre avance proyecto De Frameworks.
- Se actualizaron los modelos para reflejar las relaciones entre las tablas de la base de datos usando las anotaciones @OneToMany, @ManyToOne y @JoinColumn, las tablas actuales que existen son:
	- Cliente
	- Credito
	- Cuota
	- Liquidacion
	- TipoCredito
	- Simulacion (opcional, por lo que aun no se crea)
- Se definieron las rutas de la API en dos grandes ramas:
	- /API/clientes/
	- /API/clientes/{id_cliente}
	- /API/clientes/{id_cliente}/cuotas
	- /API/clientes/{id_cliente}/liquidaciones
	- /API/clientes/{id_cliente}/liquidaciones/{id_liquidacion}
	- /API/clientes/{id_cliente}/creditos
	- /API/clientes/{id_cliente}/creditos/simular
	- /API/clientes/{id_cliente}/creditos/{id_credito}
	- /API/clientes/{id_cliente}/creditos/{id_credito}/cuotas
	- /API/clientes/{id_cliente}/creditos/{id_credito}/cuotas/{id_cuota}
	- /API/creditos/
	- /API/creditos/{id_credito}
	- /API/creditos/{id_credito}/cuotas
	- /API/creditos/{id_credito}/cuotas/{id_cuota}
	- /API/creditos/simular
- Se quitó el archivo orientado a una GUI para el proyecto.
- Archivos JSON para crear/modificar clientes, liquidaciones.
- Falta implementar métodos para agregar créditos, modificar cuotas (para dejarlas como pagadas), simular créditos
- Falta implementar restricciones para no tener más de un crédito activo.
- Falta crear archivos JSON para crear/eliminar créditos y/o modificar cuotas.
- Considerar los cambios al archivo application.properties que se deben realizar para conectarse correctamente a la base de datos postgres.
- Se recomienda revisar el código para ver en detalle los modelos y rutas de la API
