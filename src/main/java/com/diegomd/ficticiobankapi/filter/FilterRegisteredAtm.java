package com.diegomd.ficticiobankapi.filter;

import com.diegomd.ficticiobankapi.entities.section.SectionController;
import com.diegomd.ficticiobankapi.entities.section.SectionModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class FilterRegisteredAtm extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        var servletPath = request.getServletPath();
        var atmId = request.getHeader("atm-id");

        if (servletPath.equals("/api/new-section") && atmId != null) {

            SectionModel section = SectionController.findByAtmClientId(UUID.fromString(atmId));

            if (section == null) {
                var savedSection = SectionController.save(new SectionModel(UUID.fromString(atmId),"new-section"));
            }


            //SectionModel section = SectionController.findByAtmClientId(UUID.fromString(response.getHeader("atm-id")));
            //request.getHeader("atm-id");
            //Section section = SectionsTemp.findByAtmClientId();
        }
        //request.setAttribute("test", "this is a test");

        //System.out.println(request);
        filterChain.doFilter(request, response);
    }
}

/*
package com.diegomd.todolist.filter;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.diegomd.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Base64;

// Essa classe faz a autenticação do usuário antes de permitir o cadastro de uma tarefa
// Toda classe que eu quero que o Spring gerencie eu preciso colocar @Controller, @Compoment ou @RestController
@Component// O @Component é a classe mais genérica de gerenciamento do Spring.
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authorization = request.getHeader("Authorization");

        if (request.getServletPath().equals("/todolist/new-task") && authorization != null) {

            // Pegar a autenticação (usuário e senha)

            String basicAuthType = "Basic";
            String authEncoded, authDecoded, username, password;

            if (authorization.contains(basicAuthType)) {

                authEncoded = authorization.substring(basicAuthType.length()).trim();
                authDecoded = new String(Base64.getDecoder().decode(authEncoded));

                username = authDecoded.split(":")[0];
                password = authDecoded.split(":")[1];

                // Procurando o usuário no banco de dados utilizando o username dele
                var user = this.userRepository.findByUsername(username);

                // validar usuário
                if (user == null) {

                    response.sendError(401);

                } else {

                    // validar senha
                    var verifyResult = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                    if (verifyResult.verified) {

                        // Definindo o ID do usuário na nossa tarefa
                        request.setAttribute("userId", user.getId());

                        // Aqui vamos Permitir que a requisição continue seu fluxo normal de processamento
                        filterChain.doFilter(request, response);

                    } else {

                        response.sendError(401);

                    }
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean processRequest(){return false;}
}



 */