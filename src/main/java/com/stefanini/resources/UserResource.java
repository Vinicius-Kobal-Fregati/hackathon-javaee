package com.stefanini.resources;

import com.stefanini.dto.UserForCreateDTO;
import com.stefanini.dto.UserWithoutPasswordDTO;
import com.stefanini.exceptions.*;
import com.stefanini.exceptions.BadRequestException;
import com.stefanini.service.UserService;
import com.stefanini.utils.PasswordEncryptor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserResource {
    @Inject
    private UserService userService;

    @POST
    public Response createUser(UserForCreateDTO userForCreateDTO) {
        try {
            UserWithoutPasswordDTO userCreated = userService.createUser(userForCreateDTO);
            return Response.status(Response.Status.CREATED).entity(userCreated).build();
            //return "a";
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
            //return userForCreateDTO.getEmail();
        }
    }

    @PUT
    public Response updateUser(UserForCreateDTO userForCreateDTO) {
        try {
            UserWithoutPasswordDTO userWithoutPasswordDTO = userService.updateUser(userForCreateDTO);
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTO).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{idUser}")
    public Response deleteUser(@PathParam("idUser") Long id) {
        try {
            userService.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/happy-birthday")
    public Response listBirthday() {
        try {
            List<UserWithoutPasswordDTO> userWithoutPasswordDTOS = userService.listBirthday();
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTOS).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
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
    @Path("/first-letter/{letter}")
    public Response listFirstLetterInName(@PathParam("letter") char letter) {
        try {
            List<UserWithoutPasswordDTO> userWithoutPasswordDTOS = userService.listFirstLetterInName(letter);
            return Response.status(Response.Status.OK).entity(userWithoutPasswordDTOS).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
