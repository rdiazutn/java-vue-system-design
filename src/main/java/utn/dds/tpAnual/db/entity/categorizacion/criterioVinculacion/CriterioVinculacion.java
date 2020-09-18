package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.RestanteVinculacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_VINCULACION")
public abstract class CriterioVinculacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long criterioId;

    public abstract RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                                     List<ReglaVinculacion> reglas);

    public Long getCriterioId() {
        return criterioId;
    }

    public void setCriterioId(Long criterioId) {
        this.criterioId = criterioId;
    }

    public Float getRestanteSinVincular (Ingreso ingreso) {
        return ingreso.getTotal() - getValorVinculado(ingreso);
    }

    private Float getValorVinculado(Ingreso ingreso){
        return ingreso.getEgresosAsociados() == null ? 0F :
                ingreso.getEgresosAsociados().stream().map(egreso -> egreso.getTotal())
                .reduce(0F, (valor, acumulador) -> valor + acumulador);
    }

    private boolean satisfaceRestante (Ingreso ingreso, Egreso egreso) {
        return getRestanteSinVincular(ingreso) >= egreso.getTotal();
    }

    private boolean cumpleReglas (Ingreso ingreso, Egreso egreso, List<ReglaVinculacion> reglas){
        return reglas.stream().allMatch(reglaVinculacion -> reglaVinculacion.puedeVincularse(ingreso, egreso));
    }

    protected RestanteVinculacion vincularListasYaOrdenadasPrimerEgreso(List<Ingreso> ingresos, List<Egreso> egresos,
                                                            List<ReglaVinculacion> reglas){
        List<Egreso> egresosARemover = new ArrayList<>();
        for (Ingreso ingreso : ingresos){
            for (Egreso egreso : egresos) {
                if (cumpleReglas(ingreso, egreso, reglas) &&
                    satisfaceRestante(ingreso, egreso)) {
                    ingreso.vincularEgreso(egreso);
                    egresosARemover.add(egreso);
                }
            }
            egresos.removeAll(egresosARemover);
            egresosARemover.clear();
        }
        return new RestanteVinculacion(ingresos, egresos);
    }
}