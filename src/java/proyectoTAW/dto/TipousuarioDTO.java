/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dto;

/**
 *
 * @author juanm
 */
public class TipousuarioDTO {
    
    private static final long serialVersionUID = 1L;
    private String tipoUsuario;

    public TipousuarioDTO() {
    }

    public TipousuarioDTO(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
