package utn.dds.tpAnual.db.entity.afip.tamanios;



/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Pequenia extends TamanioEmpresa{

	private final int jerarquia = 1;
	private final String nombre = "Pequenia";
	private static Pequenia instance = new Pequenia();
	
	private Pequenia() {
		
	}
	
	public static Pequenia getInstance() {
		return instance;
	}
	
	@Override
	public int getJerarquia() {
		return jerarquia;
	}

	@Override
	public String getNombre() {
		return nombre;
	}
}