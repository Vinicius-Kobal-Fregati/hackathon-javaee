package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserWithoutPasswordDTO;
import com.stefanini.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UserRepository extends GenericDAO<User, Long> {

    @Transactional
    public UserWithoutPasswordDTO createUser(User user) {
        this.save(user);
        return new UserWithoutPasswordDTO(user);
    }

    @Transactional
    public UserWithoutPasswordDTO updateUser(User user) {
        User userUpdated = this.update(user);
        return new UserWithoutPasswordDTO(userUpdated);
    }

    @Transactional
    public void deleteUser(Long id) {
        this.delete(id);
    }

    public List<UserWithoutPasswordDTO> listAllUsers() {
        List<User> users = this.listAll();
        return users.stream().map(UserWithoutPasswordDTO::new).collect(Collectors.toList());
    }

    public UserWithoutPasswordDTO findUserAndReturnDTO(Long id) {
        User user = findById(id);
        return user != null ? new UserWithoutPasswordDTO(user): null;
    }

    public User findUser(Long id) {
        return findById(id);
    }

    public List<UserWithoutPasswordDTO> listBirthday(Integer month) {
        Stream<User> userStream = this.createQuery("FROM User user WHERE MONTH(dataDeNascimento) = :mes " +
                        "ORDER BY user.nome", User.class)
                .setParameter("mes", month)
                .getResultStream();

        return userStream.map(UserWithoutPasswordDTO::new).collect(Collectors.toList());
    }

    public List<String> listEmailProvider() {
        return createQuery("SELECT DISTINCT SUBSTRING(email, LOCATE('@', email) + 1) " +
                "AS provedores FROM User ORDER BY provedores", String.class)
                .getResultList();
    }

    public List<UserWithoutPasswordDTO> listFirstLetterInName(Character character) {
        TypedQuery<User> query = this.createQuery("SELECT user FROM User user WHERE user.nome LIKE :nome");
        Stream<User> resultadoQuery = query.setParameter("nome", character + "%")
                .getResultStream();
        return resultadoQuery.map(UserWithoutPasswordDTO::new)
                .collect(Collectors.toList());
    }

    public User findUserByLogin(String login) {
        try {
            return createQuery("SELECT user FROM User user WHERE user.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
