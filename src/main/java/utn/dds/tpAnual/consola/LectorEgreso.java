package utn.dds.tpAnual.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.compra.Item;
import utn.dds.tpAnual.compra.Presupuesto;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;
import utn.dds.tpAnual.validador.Validador;


public class LectorEgreso extends Lector{
	private static Scanner scanner = new Scanner(System.in);
	private static LectorEgreso instance = new LectorEgreso();

	private LectorEgreso() {
	}

	public static LectorEgreso getInstance() {
		return instance;
	}

	@Override
	public void ejecutar(){
		System.out.println("Se dará de alta un Egreso:");	
		List<DetalleOperacion> detallesOperacion = getDetallesOperacion();
		Integer cantidadMinimaPresupuestos = getInteger("Ingrese la cantidad minima de presupuestos");
		CriterioCompra criterio = getCriterio();
		List<Presupuesto> presupuestos = getPresupuestos(detallesOperacion);
		List<Usuario> revisores = getRevisores();
		Egreso egreso = new Egreso(null, null, 0, null, null, null, cantidadMinimaPresupuestos, criterio, presupuestos, null, revisores);
		Validador validador = Validador.getInstance();

		if(validador.validarEgreso(egreso)) {
			System.out.println("La validación del egreso fue exitosa.");			
		} else {
			System.out.println("Falló la validación del egreso.");
		}
		
		if(revisores != null && revisores.size() > 0) {
			System.out.println("\nSe procedera a mostrar la bandeja de mensajes de cada usuario");
			for(Usuario unRevisor : revisores){
				System.out.println("Revisor: " + unRevisor);
				List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
				System.out.println("- " + mensajeUsuario.get(0).getCuerpo());
			}
		}

	}
	
	private Integer getInteger(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
			return (val.isEmpty())? null : Integer.valueOf(val);
	}
	
	private String getString(String mensaje) {
		System.out.println(mensaje);
		String val = scanner.nextLine();
		return (val.isEmpty())? null : val;
	}
	
	private Long getLong(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
		return (val.isEmpty())? null : Long.valueOf(val);
	}
	
	private Float getFloat(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
		return (val.isEmpty())? null : Float.valueOf(val);
	}
	
	private boolean consultarSobre(String nombreTipoDato) {
		String eleccion = getString("¿Desea registrar un " + nombreTipoDato + "? Y/N");
		return eleccion.equalsIgnoreCase("Y");
	}
	
	private List<DetalleOperacion> getDetallesOperacion(){
		List<DetalleOperacion> detallesOperacion = new ArrayList<DetalleOperacion>();
		System.out.println("Se tomara registro de los detalles de items del egreso.");
		while(true) {
			if(consultarSobre("detalle de item")) {
				DetalleOperacion detalleOperacion = getDetalleOperacion();
				detallesOperacion.add(detalleOperacion);
			} else {
				break;
			}
		}
		return detallesOperacion;
	}
	
	private Item getItem() {
		Long codigo = getLong("Ingrese el codigo del item");
		String descripcion = getString("Ingrese la descripción del item");
		return (codigo == null || descripcion == null) ? null : new Item (codigo, descripcion);  
	}
	
	private DetalleOperacion getDetalleOperacion() {
		Integer cantidad = getInteger("Ingrese la cantidad de items");
		Item item = getItem();
		Float precio = getFloat("Ingrese el precio del item");
		return (cantidad == null || item == null || precio == null) ? null : new DetalleOperacion(item, precio, cantidad);  
	}
	
	private CriterioCompra getCriterio() {
		System.out.println("Ingrese 1 para Criterio Menor Precio.\n"
							+ "Ingrese 0 para ningun criterio.");
		String criterio = scanner.nextLine();
		
		return Integer.valueOf(criterio) == 0 ? null : CriterioMenorPrecio.getInstance();
	}
	
	private Usuario getRevisor() {
		String nombre = getString("Ingrese el nombre del usuario");
		return nombre == null? null : new Usuario(nombre, null);   
	}
	
	private List<Usuario> getRevisores() {
		List<Usuario> revisores = new ArrayList<Usuario>();
		System.out.println("Se tomara registro de los revisores");
		while(true) {
			if(consultarSobre("revisor")) {
				Usuario revisor = getRevisor();
				revisores.add(revisor);
			} else {
				break;
			}
		}
		return revisores;
	}
	
	private List<Presupuesto> getPresupuestos(List<DetalleOperacion> detallesOperacion){
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		System.out.println("Se tomara registro de los presupuestos");
		while(true) {
			if(consultarSobre("presupuesto")) {
				Presupuesto presupuesto = new Presupuesto(null, null, 0, getDetallesPrecio(detallesOperacion));
				presupuestos.add(presupuesto);
			} else {
				break;
			}
		}
		return presupuestos;
	}
	
	private List<DetallePrecio> getDetallesPrecio(List<DetalleOperacion> detallesOperacion){
		List<DetallePrecio> detallesPrecio = new ArrayList<DetallePrecio>();
		for (DetalleOperacion detalleOperacion : detallesOperacion) {
			DetallePrecio detallePrecio = getDetallePrecio(detalleOperacion);
			detallesPrecio.add(detallePrecio);
		}
		return detallesPrecio;
	}
	
	private DetallePrecio getDetallePrecio(DetalleOperacion detalleOperacion) {
		Float precio = getFloat("Ingrese el valor presupuestado del item " + detalleOperacion.getDescripcionItem());
		return new DetallePrecio(detalleOperacion, precio);
	}
	
}