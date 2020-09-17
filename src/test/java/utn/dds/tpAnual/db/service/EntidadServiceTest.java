package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;
import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class EntidadServiceTest {

    @Autowired
    private EntidadService entidadService;



    EntidadJuridicaEmpresa unaEmpresa = new EntidadJuridicaEmpresaBuilder()
            .withNombre("EmpresaGenerica")
            .withVentaAnual(new VentaAnual(2012, 15f))
            .build();

    @Test
    public void persistenceTest() {
        entidadService.save(unaEmpresa);
        EntidadJuridicaEmpresa mismaEmpresa = (EntidadJuridicaEmpresa) entidadService.getFirstEntidadByNombre("EmpresaGenerica");
        assertTrue(unaEmpresa.getNombre().equals(mismaEmpresa.getNombre()));
    }

}