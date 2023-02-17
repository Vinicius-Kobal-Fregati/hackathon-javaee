package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserWithoutPasswordDTO;
import com.stefanini.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

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
        List<UserWithoutPasswordDTO> userWithoutPasswordDTOS =
                users.stream().map(UserWithoutPasswordDTO::new).collect(Collectors.toList());
        return userWithoutPasswordDTOS;
    }

    public UserWithoutPasswordDTO findUser(Long id) {
        User user = findById(id);
        return new UserWithoutPasswordDTO(user);
    }

    public List<UserWithoutPasswordDTO> listBirthday() {
        Month month = LocalDate.now().getMonth();
        String queryDb = "SELECT * FROM tb_usuario WHERE MONTH(data_de_nascimento) = " + month + " ORDER BY nome";
        TypedQuery<User> resultQuery = this.createQuery(queryDb);
        List<UserWithoutPasswordDTO> users =
                resultQuery.getResultStream().map(UserWithoutPasswordDTO::new)
                        .collect(Collectors.toList());

        return users;
    }

    public List<String> listEmailProvider() {
        String queryDb = "SELECT DISTINCT SUBSTRING(email, (PATINDEX('%@%', email) + 1), " +
                "LEN(email)) AS domain_email FROM tb_usuario";
        TypedQuery<String> query = this.createQuery(queryDb);
        List<String> domainProviders = query.getResultStream().collect(Collectors.toList());
        return domainProviders;
    }

    public List<UserWithoutPasswordDTO> listFirstLetterInName(Character character) {
        String queryDb = "SELECT id, nome, login, email, data_de_nascimento, data_de_criacao, data_de_atualizacao " +
                "FROM tb_usuario WHERE nome LIKE " + character + "%";
        TypedQuery<User> resultadoQuery = this.createQuery(queryDb);
        List<UserWithoutPasswordDTO> users = resultadoQuery.getResultStream().map(UserWithoutPasswordDTO::new).collect(Collectors.toList());
        return users;
    }
}
