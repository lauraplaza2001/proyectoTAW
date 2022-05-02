/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import proyectoTAW.dao.GeneroFacade;
import proyectoTAW.dao.TipousuarioFacade;
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.dto.UsuarioDTO;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author juanm
 */
@Stateless
public class UsuarioService {

    @EJB
    UsuarioFacade uFacade;
    @EJB
    GeneroFacade gFacade;
    @EJB
    TipousuarioFacade tuFacade;

    public List<UsuarioDTO> toDTOList(List<Usuario> lista) {

        List<UsuarioDTO> result = null;

        if (lista != null) {
            result = new ArrayList<>();
            for (Usuario c : lista) {
                result.add(c.toDTO());
            }
        }

        return result;
    }

    public void crearUsuario(String username, String pass, String name, String surname, String city, String address, Integer age, String gender, String userType) {

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(username);
        usuario.setContrasena(pass);
        usuario.setNombre(name);
        usuario.setApellidos(surname);
        usuario.setCiudad(city);
        usuario.setDomicilio(address);
        usuario.setEdad(age);
        usuario.setGenero(this.gFacade.find(gender));
        usuario.setTipoUsuario(this.tuFacade.find(userType));

        this.uFacade.create(usuario);

    }

    public void remove(int id) {
        this.uFacade.remove((this.uFacade.find(id)));
    }

    public List<UsuarioDTO> getCategoriasLike(String like, Integer filtro) {
        List<UsuarioDTO> usuarios;

        if (like != null) {
            usuarios = this.toDTOList(this.uFacade.findFiltered(filtro, like));
        } else {
            usuarios = this.toDTOList(this.uFacade.findAll());
        }

        return usuarios;
    }
}
