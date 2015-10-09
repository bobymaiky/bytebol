package br.com.bytebol.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.bytebol.utilitarios.JpaUtil;


@WebFilter(servletNames = { "Faces Servlet" })
public class ControlerServletFilter implements Filter {
	
	
//    private EntityManagerFactory entityManagerFactory;
 
    @Override
    public void destroy() {
    //	this.entityManagerFactory.close();
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterClain)  throws IOException, ServletException {

    	EntityManager entityManager = JpaUtil.getEntityManager();
        request.setAttribute("entityManager", entityManager);
        entityManager.getTransaction().begin();
        filterClain.doFilter(request, response);
        try {
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServletException(e.toString());
        
        } finally {
          entityManager.close();
        }
    }
    @Override
    public void init(FilterConfig fc) throws ServletException {
      //  this.entityManagerFactory = Persistence.createEntityManagerFactory("integrador");
    }
}