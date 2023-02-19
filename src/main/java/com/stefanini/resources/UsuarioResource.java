package com.stefanini.resources;

import com.stefanini.dto.UsuarioCriacaoDTO;
import com.stefanini.dto.UsuarioSemSenhaDTO;
import com.stefanini.exceptions.BadRequestException;
import com.stefanini.exceptions.NaoExisteException;
import com.stefanini.exceptions.ObjetoNaoEncontradoException;
import com.stefanini.services.UsuarioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {
    @Inject
    private UsuarioService usuarioService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaUsuario(UsuarioCriacaoDTO usuarioCriacaoDTO) {
        try {
            UsuarioSemSenhaDTO usuarioCriado = usuarioService.criaUsuario(usuarioCriacaoDTO);
            return Response.status(Response.Status.CREATED).entity(usuarioCriado).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizaUsuario(@PathParam("id") Long id, UsuarioCriacaoDTO usuarioCriacaoDTO) {
        try {
            UsuarioSemSenhaDTO usuarioSemSenhaDTO = usuarioService.atualizaUsuario(id, usuarioCriacaoDTO);
            return Response.status(Response.Status.OK).entity(usuarioSemSenhaDTO).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response apagaUsuario(@PathParam("id") Long id) {
        try {
            usuarioService.apagaUsuario(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NaoExisteException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaTodosUsuarios() {
        try {
            List<UsuarioSemSenhaDTO> usuarios = usuarioService.listaTodosUsuarios();
            return Response.status(Response.Status.OK).entity(usuarios).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aniversariantes/{numeroDoMes}")
    public Response listaAniversariantes(@PathParam("numeroDoMes") Integer month) {
        try {
            List<UsuarioSemSenhaDTO> usuariosSemSenhaDTO = usuarioService.listaAniversariantes(month);
            return Response.status(Response.Status.OK).entity(usuariosSemSenhaDTO).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/emails")
    public Response listaProvedoresEmail() {
        try {
            List<String> listaProvedoresEmail = usuarioService.listaProvedoresEmail();
            return Response.status(Response.Status.OK).entity(listaProvedoresEmail).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/primeira-letra-nome/{letra}")
    public Response listaUsuariosPelaInicial(@PathParam("letra") char letter) {
        try {
            List<UsuarioSemSenhaDTO> usuariosSemSenhaDTO = usuarioService.listaUsuariosPelaInicial(letter);
            return Response.status(Response.Status.OK).entity(usuariosSemSenhaDTO).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response buscaUsuarioPeloId(@PathParam("id") Long id) {
        try {
            UsuarioSemSenhaDTO usuario = usuarioService.buscaUsuarioRetornaDTO(id);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (ObjetoNaoEncontradoException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
