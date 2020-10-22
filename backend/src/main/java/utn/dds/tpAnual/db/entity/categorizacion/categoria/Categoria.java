package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long idCategoria;

	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	@ManyToOne(cascade = CascadeType.ALL)
	private CriterioCategorizacion criterioCategorizacion;
	
	public Categoria(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Categoria(){

	}

	public Long getIdCategoria () {
		return idCategoria;
	}

	public void setIdCategoria (Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion () {
		return descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}


	public CriterioCategorizacion getCriterioCategorizacion() {
		return criterioCategorizacion;
	}

	public void setCriterioCategorizacion(CriterioCategorizacion criterioCategorizacion) {
		this.criterioCategorizacion = criterioCategorizacion;
	}
}
