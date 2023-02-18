package com.stefanini.resources;

import com.stefanini.dto.UserForCreateDTO;
import com.stefanini.dto.UserWithoutPasswordDTO;
import com.stefanini.exceptions.BadRequestException;
import com.stefanini.exceptions.NotExistException;
import com.stefanini.exceptions.ObjectNotFoundException;
import com.stefanini.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserResource {
    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserForCreateDTO userForCreateDTO) {
        try {
            UserWithoutPasswordDTO userCreated = userService.createUser(userForCreateDTO);
            return Response.status(Response.Status.CREATED).entity(userCreated).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idUser}")
    public Response updateUser(@PathParam("idUser") Long id, UserForCreateDTO userForCreateDTO) {
        try {
            UserWithoutPasswordDTO userWithoutPasswordDTO = userService.updateUser(id, userForCreateDTO);
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTO).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idUser}")
    public Response deleteUser(@PathParam("idUser") Long id) {
        try {
            userService.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllUsers() {
        try {
            List<UserWithoutPasswordDTO> users = userService.listAllUsers();
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/happy-birthday/{numberOfMonth}")
    public Response listBirthday(@PathParam("numberOfMonth") Integer month) {
        try {
            List<UserWithoutPasswordDTO> userWithoutPasswordDTOS = userService.listBirthday(month);
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTOS).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/emails")
    public Response listEmailProvider() {
        try {
            List<String> listEmailProvider = userService.listEmailProvider();
            return Response.status(Response.Status.OK).entity(listEmailProvider).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/first-letter/{letter}")
    public Response listFirstLetterInName(@PathParam("letter") char letter) {
        try {
            List<UserWithoutPasswordDTO> userWithoutPasswordDTOS = userService.listFirstLetterInName(letter);
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTOS).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response searchUserById(@PathParam("id") Long id) {
        try {
            UserWithoutPasswordDTO user = userService.searchUserById(id);
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
