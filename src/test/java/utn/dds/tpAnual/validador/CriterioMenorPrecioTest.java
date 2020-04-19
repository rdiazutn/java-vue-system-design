package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Item;
import utn.dds.tpAnual.compra.Presupuesto;

import org.junit.Test;

public class CriterioMenorPrecioTest {
	
	private Item mesa = new Item(1L, "Mesa");
	
	private DetalleOperacion unDetalleOperacion = new DetalleOperacion(mesa, 15F, 3);
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacion(mesa, 20F, 4);
	
	private DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	
	private List<DetallePrecio> unosDetalles = Arrays.asList(unDetallePrecio, otroDetallePrecio);
	private List<DetallePrecio> otrosDetalles = Arrays.asList(unDetallePrecio, unDetallePrecio, otroDetallePrecio, otroDetallePrecio);
	
	private Presupuesto unPresupuesto = new Presupuesto(unosDetalles);
	private Presupuesto otroPresupuesto = new Presupuesto(otrosDetalles);
	
	private List<Presupuesto> listaPresupuestos = Arrays.asList(unPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaPresupuestosNula;
	private List<Presupuesto> listaSinPresupuestos = Arrays.asList(); 
	
	private CriterioMenorPrecio criterioMenorPrecio = new CriterioMenorPrecio();
	
	@Test 
	public void obtengoPresupuestoDeMenorPrecio(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaPresupuestos),unPresupuesto);
	}
	
	@Test 
	public void obtengoNullSobreUnaListaNula(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaPresupuestosNula),null);
	}
	
	@Test 
	public void obtengoNullSobreUnaListaVacia(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaSinPresupuestos),null);
	}
}