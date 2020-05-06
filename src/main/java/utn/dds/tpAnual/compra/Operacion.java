package utn.dds.tpAnual.compra;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class Operacion {

	protected DocumentoComercial documentoComercial;
	protected Entidad entidadRealizadora;
	private int codigoOperacion;
	
	public Operacion(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion) {
		this.documentoComercial = documentoComercial;
		this.entidadRealizadora = entidadRealizadora;
		this.codigoOperacion = codigoOperacion;
	}
	
	public Operacion(int codigoOperacion){
		this.codigoOperacion = codigoOperacion;
	}
	
	public int getCodigoOperacion() {
		return codigoOperacion;
	}

	public abstract Float getTotal();
}