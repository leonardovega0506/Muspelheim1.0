package mx.com.ananda.midgard.service.auth;



import mx.com.ananda.midgard.exception.UsuarioFoundException;
import mx.com.ananda.midgard.model.auth.UsuarioModel;
import mx.com.ananda.midgard.model.auth.UsuarioRolModel;
import mx.com.ananda.midgard.repositories.IRolRepository;
import mx.com.ananda.midgard.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IRolRepository iRol;

    @Autowired
    private IUsuarioRepository iUsuario;

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel, Set<UsuarioRolModel> usuariosRoles) throws Exception {
        UsuarioModel usuarioLocal = iUsuario.findByUsername(usuarioModel.getUsername());
        if (usuarioLocal != null) {
            throw new UsuarioFoundException("El usuario ya esta presente");
        } else {
            for (UsuarioRolModel usuarioRol : usuariosRoles) {
                iRol.save(usuarioRol.getRol());
            }
            usuarioModel.getUsuarioRoles().addAll(usuariosRoles);
            usuarioLocal = iUsuario.save(usuarioModel);
        }
        return usuarioLocal;
    }


    @Override
    public UsuarioModel findUsuarioByUsername(String username) {
        return iUsuario.findByUsername(username);
    }

    @Override
    public List<UsuarioModel> findAllUsuarios() {
        return iUsuario.findAll();
    }

    @Override
    public void updateUsuario(UsuarioModel usuario) {
        Optional<UsuarioModel> oUsuario = iUsuario.findById(usuario.getIdUsuario());
        if(usuario.getPassword().equals(oUsuario.get().getPassword())){
            iUsuario.save(usuario);
        }
    }

    @Override
    public void deleteUsuario(Long idUsuario) {
        iUsuario.deleteById(idUsuario);
    }
}
