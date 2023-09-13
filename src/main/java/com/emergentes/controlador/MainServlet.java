
package com.emergentes.controlador;

import com.emergentes.modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String op=request.getParameter("op");
       productos objpro = new productos();
       int id,pos;
       
       HttpSession ses = request.getSession();
       ArrayList<productos> lista = (ArrayList<productos>)ses.getAttribute("listaproductos");
       switch(op){
           case "nuevo":
               request.setAttribute("objproducto", objpro);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               
               break;
           case "editar":
               id= Integer.parseInt(request.getParameter("id"));
               //averiguar la posision
               pos = buscarPorIndice(request,id);
               //obtener el objeto
               objpro = lista.get(pos);
               request.setAttribute("objproducto", objpro);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case"eliminar":
               id= Integer.parseInt(request.getParameter("id"));
               pos = buscarPorIndice(request,id);
               if(pos>=0){
                   lista.remove(pos);
               }
               request.setAttribute("listaproductos", lista);
               response.sendRedirect("index.jsp");
               break;
           default:
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<productos> lista = (ArrayList<productos>) ses.getAttribute("listaproductos");
        productos objpro = new productos();
        objpro.setId(id);
        objpro.setDescripcion(request.getParameter("descripcion"));
        objpro.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        objpro.setPrecio(Double.parseDouble(request.getParameter("precio")));
        objpro.setCategoria(request.getParameter("categoria"));
        
        if(id==0){
            //nuevo registro
            int idNuevo = obtenerId(request);
            objpro.setId(idNuevo);
            lista.add(objpro);
        }
        else{
            int pos = buscarPorIndice(request,id);
            lista.set(pos, objpro);
            
        }
        request.setAttribute("listaproductos", lista);
        response.sendRedirect("index.jsp");
      
    }
 
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<productos> lista =(ArrayList<productos>) ses.getAttribute("listaproductos");
        int pos =-1;
        if(lista !=null){
            for(productos ele : lista){
                ++pos;
                if(ele.getId() == id){
                    break;
                }
            }
        }
        return pos;
    }
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<productos> lista =(ArrayList<productos>) ses.getAttribute("listaproductos");
        int idn = 0;
        for(productos ele : lista){
            idn = ele.getId();
        }
        return idn + 1;
    }
}
