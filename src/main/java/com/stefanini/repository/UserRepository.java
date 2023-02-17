package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserDtoWithoutPassword;
import com.stefanini.entity.User;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserRepository extends GenericDAO<User, Long> {
    public UserDtoWithoutPassword createUser(User user) {
        this.save(user);
        return new UserDtoWithoutPassword(user);
    }

    public UserDtoWithoutPassword updateUser(User user) {
        User userUpdated = this.update(user);
        return new UserDtoWithoutPassword(userUpdated);
    }

    public void deleteUser(Long id) {
        this.delete(id);
    }

    public List<UserDtoWithoutPassword> listAllUsers() {
        List<User> users = this.listAll();
        List<UserDtoWithoutPassword> userDtoWithoutPasswords =
                users.stream().map(UserDtoWithoutPassword::new).collect(Collectors.toList());
        return userDtoWithoutPasswords;
    }

    public UserDtoWithoutPassword findUser(Long id) {
        User user = findById(id);
        return new UserDtoWithoutPassword(user);
    }

    public List<UserDtoWithoutPassword> listBirthday() {
        Month month = LocalDate.now().getMonth();
        String queryDb = "SELECT * FROM tb_usuario WHERE MONTH(data_de_nascimento) = " + month + " ORDER BY nome";
        TypedQuery<User> resultQuery = this.createQuery(queryDb);
        List<UserDtoWithoutPassword> users =
                resultQuery.getResultStream().map(UserDtoWithoutPassword::new)
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

    public List<UserDtoWithoutPassword> listFirstLetterInName(Character character) {
        String queryDb = "SELECT id, nome, login, email, data_de_nascimento, data_de_criacao, data_de_atualizacao " +
                "FROM tb_usuario WHERE nome LIKE " + character + "% ORDER BY nome";
        TypedQuery<User> resultadoQuery = this.createQuery(queryDb);
        List<UserDtoWithoutPassword> users = resultadoQuery.getResultStream().map(UserDtoWithoutPassword::new).collect(Collectors.toList());
        return users;
    }
}
