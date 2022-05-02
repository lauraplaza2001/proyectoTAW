/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dto;

import java.util.List;

/**
 *
 * @author juanm
 */
public class GeneroDTO {
    
    private static final long serialVersionUID = 1L;
    private String genero;

    public GeneroDTO() {
    }

    public GeneroDTO(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
