/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;

/**
 *
 * @author juanm
 */
@Stateless
public class SubastaFacade extends AbstractFacade<Subasta> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubastaFacade() {
        super(Subasta.class);
    }

    public List<Subasta> findSubastaActiva() {
       Query q;
             
       q = this.getEntityManager().createQuery("SELECT s FROM Subasta s  WHERE s.fechaCierre >= :today",Subasta.class);
       q.setParameter("today",new Date());
       
       return q.getResultList();
        
    }

    public List<Subasta> findSubastaDelProducto(Producto producto) {
        Query q;
        q= this.getEntityManager().createQuery("SELECT s FROM Subasta s WHERE s.producto.idProducto= :idProducto");
     //   q.setParameter("fecha", new Date());
        q.setParameter("idProducto", producto.getIdProducto());
        
        return q.getResultList();
    }

    public List<Subasta> findSubastasDelProducto(int idProducto) {
        Query q;
        q=this.getEntityManager().createQuery("select s from Subasta s where s.producto.idProducto= :id");
        q.setParameter("id", idProducto);
                
        return q.getResultList();
    }
    
     public Subasta findSubastaActiva(Producto p) {
       Query q;
       
       q = this.getEntityManager().createQuery("SELECT s FROM Subasta s JOIN Producto p ON s.producto.idProducto=p.idProducto WHERE s.fechaCierre >= :today and p.idProducto= :productoId");
       q.setParameter("today",new Date());
       q.setParameter("productoId", p.getIdProducto());
       
    //   q2 = this.getEntityManager().createQuery("SELECT s FROM Subasta s WHERE s.idSubasta= :id");
      // q2.setParameter("id", p.getIdProducto());
   
   List<Subasta> subastas = q.getResultList();
   if(subastas==null || subastas.isEmpty()) return null;
      // q2.setParameter("today",new Date());
       return subastas.get(0);
    }
    
}
