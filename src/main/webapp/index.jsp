<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.productos"%>
<%
    if(session.getAttribute("listaproductos")== null){
        ArrayList<productos> productoaux= new ArrayList<productos>();
        session.setAttribute("listaproductos", productoaux);
    }
    ArrayList<productos> productos =(ArrayList<productos>)session.getAttribute("listaproductos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table cellspacing=0 border=1>
            <tr>
                <th>SEGUNDO PARCIAL TEM-742<br>
                    Nombre: Fidel Vargas Condori<br>
                    Carnet: 10077152
                </th>
            </tr>
        </table>
        <h1>GESTION DE PRODUCTOS</h1>
        <a href="MainServlet?op=nuevo">Nuevo Registro</a>
        <br>
        <table cellspacing=0 border=1>
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if(productos != null){
                    for(productos item : productos){
                        
                 
            %>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getDescripcion() %></td>
                <td><%=item.getCantidad()%></td>
                <td><%=item.getPrecio() %></td>
                <td><%=item.getCategoria() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%=item.getId()%>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%=item.getId()%>"
                       onclick="return(confirm('Esta seguro de eliminar?'))">Eliminar</a>
                </td>
                 
            </tr>
            <%
                  }
                }
            %>
        </table>
    </body>
</html>
