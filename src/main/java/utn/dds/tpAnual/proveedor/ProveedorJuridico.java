package utn.dds.tpAnual.proveedor;


/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class ProveedorJuridico extends Proveedor {

	private Long CUIT;
	private String razonSocial;

	public ProveedorJuridico(int direccionPostal, Long CUIT, String razonSocial) {
		super(direccionPostal);
		this.CUIT = CUIT;
		this.razonSocial = razonSocial;
	}
}