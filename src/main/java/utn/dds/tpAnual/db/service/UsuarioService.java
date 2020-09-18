package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.ProveedorRepository;
import utn.dds.tpAnual.db.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService extends CustomJPAService<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public JpaRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }

    public Usuario getFirstUsuarioByNombre(String nombreUsuario){

        List<Usuario> usuarios = usuarioRepository.getUsuarioByNombre(nombreUsuario);
        return usuarios.isEmpty() ? null : usuarios.get(0);

    }
}