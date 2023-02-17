package com.stefanini.service;

import com.stefanini.dto.UserForCreateDTO;
import com.stefanini.dto.UserWithoutPasswordDTO;
import com.stefanini.entity.User;
import com.stefanini.exceptions.*;
import com.stefanini.interfaces.EntityMessageConstants;
import com.stefanini.repository.UserRepository;
import com.stefanini.utils.PasswordEncryptor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.stefanini.interfaces.EntityMessageConstants.PATTERN_BIRTHDAY_DATE;
import static com.stefanini.interfaces.RegexConstants.*;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserWithoutPasswordDTO createUser(UserForCreateDTO userForCreateDTO) throws GreaterThanAllowedException,
            NotEmptyException, NotNullException, WrongLengthException, WrongEmailException, OutOfPatternException {
        User receivedUser = new User(userForCreateDTO);
        descryptPassword(receivedUser);
        verificaAtributosDoUsuario(receivedUser);
        return userRepository.createUser(receivedUser);
    }

    public UserWithoutPasswordDTO updateUser(UserForCreateDTO userForCreateDTO) throws GreaterThanAllowedException, OutOfPatternException,
            WrongEmailException, NotEmptyException, NotNullException, WrongLengthException {
        User user = new User(userForCreateDTO);
        descryptPassword(user);
        verificaAtributosDoUsuario(user);
        user.setDataDeAtualizacao(LocalDateTime.now());
        return userRepository.updateUser(user);
    }

    public void deleteUser(Long id) throws NotExistException {
        if (userRepository.findUser(id) == null) {
            throw new NotExistException("Usuario não encontrado");
        }
        userRepository.deleteUser(id);
    }

    public List<UserWithoutPasswordDTO> listBirthday() throws ObjectNotFoundException {
        List<UserWithoutPasswordDTO> users = userRepository.listBirthday();
        if (users.size() < 1) {
            throw new ObjectNotFoundException("Nenhum aniversariante encontrado");
        }
        return users;
    }

    public List<String> listEmailProvider() throws ObjectNotFoundException {
        List<String> listEmailProvider = userRepository.listEmailProvider();
        if (listEmailProvider.size() < 1) {
            throw new ObjectNotFoundException("Nenhum provedor encontrado");
        }
        return listEmailProvider;
    }

    public List<UserWithoutPasswordDTO> listFirstLetterInName(Character character) throws ObjectNotFoundException {
        List<UserWithoutPasswordDTO> users = userRepository.listFirstLetterInName(character);
        if (users.size() < 1) {
            throw new ObjectNotFoundException("Nenhum usuário encontrado");
        }
        return users;
    }

    private static void descryptPassword(User user) {
        String password = PasswordEncryptor.descrypt(user.getSenha());
        user.setSenha(password);
    }

    private static void verificaAtributosDoUsuario(User user) throws GreaterThanAllowedException, NotEmptyException,
            NotNullException, WrongLengthException, WrongEmailException, OutOfPatternException {
        verificaNome(user);
        verificaLogin(user);
        confereLogin(user);
        verificaSenha(user);
        verificaDataDeNascimento(user);
    }

    private static void verificaDataDeNascimento(User user) throws OutOfPatternException {
        if (user.getDataDeNascimento().toString().matches(DAY_OF_BIRTH_REGEX)) {
            throw new OutOfPatternException(PATTERN_BIRTHDAY_DATE);
        }
    }

    private static void verificaSenha(User user) throws WrongLengthException, OutOfPatternException {
        if (user.getSenha().length() < 4 || user.getSenha().length() > 10) {
            throw new WrongLengthException("Senha deve ter mais de 4 e menos de 10 caracteres");
        }
        else if (user.getSenha().matches(PASSWORD_REGEX)) {
            throw new OutOfPatternException("Senha está fora do padrão, deve ter pelo menos 1 letra minúcula, " +
                    "1 maiúscula, 1 número e 1 caracter especial");
        }
    }

    private static void confereLogin(User user) throws NotEmptyException, WrongEmailException {
        if (user.getEmail().isBlank()) {
            throw new NotEmptyException(EntityMessageConstants.noEmpty("Email"));
        }
        else if (user.getEmail().matches(EMAIL_REGEX)) {
            throw new WrongEmailException("Email inválido");
        }
    }

    private static void verificaLogin(User user) throws WrongLengthException, NotNullException {
        if (user.getLogin().length() < 5 || user.getLogin().length() > 20) {
            throw new WrongLengthException("Login deve ter entre 5 a 20 caracteres");
        }
        else if (user.getLogin() == null) {
            throw new NotNullException(EntityMessageConstants.noNull("Login"));
        }
    }

    private static void verificaNome(User user) throws GreaterThanAllowedException, NotEmptyException, NotNullException {
        if (user.getNome().length() > 50) {
            throw new GreaterThanAllowedException("Nome não pode ter mais que 50 letras");
        }
        else if (user.getNome().isBlank()) {
            throw new NotEmptyException(EntityMessageConstants.noEmpty("Nome"));
        }
        else if (user.getNome() == null) {
            throw new NotNullException(EntityMessageConstants.noNull("Nome"));
        }
    }
}
