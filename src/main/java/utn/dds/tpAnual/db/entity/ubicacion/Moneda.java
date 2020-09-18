package utn.dds.tpAnual.db.entity.ubicacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MONEDA")
public class Moneda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long monedaId;
	
	@Column(name = "ID_API", unique = true, nullable = false)
	private String idAPI;
	
	@Column(name = "DESCRIPCION", unique = false, nullable = false)
	private String descripcion;
	
	@Column(name = "SIMBOLO", unique = false, nullable = false)
	private String simbolo;
	
	public Moneda() {}
	
	public Moneda(String idAPI, String descripcion, String simbolo) {
		this.idAPI = idAPI;
		this.descripcion = descripcion;
		this.simbolo = simbolo;
	}
	
	public Long getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(Long monedaId) {
		this.monedaId = monedaId;
	}

	public String getIdAPI() {
		return idAPI;
	}

	public void setIdAPI(String idAPI) {
		this.idAPI = idAPI;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}
