package com.stefanini.repositories;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UsuarioSemSenhaDTO;
import com.stefanini.entities.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UsuarioRepository extends GenericDAO<Usuario, Long> {

    @Transactional
    public UsuarioSemSenhaDTO criaUsuario(Usuario usuario) {
        this.save(usuario);
        return new UsuarioSemSenhaDTO(usuario);
    }

    @Transactional
    public UsuarioSemSenhaDTO atualizaUsuario(Usuario usuario) {
        Usuario usuarioAtualizado = this.update(usuario);
        return new UsuarioSemSenhaDTO(usuarioAtualizado);
    }

    @Transactional
    public void apagaUsuario(Long id) {
        this.delete(id);
    }

    public List<UsuarioSemSenhaDTO> listaTodosUsuarios() {
        List<Usuario> usuarios = this.listAll();
        return usuarios.stream().map(UsuarioSemSenhaDTO::new).collect(Collectors.toList());
    }

    public UsuarioSemSenhaDTO buscaUsuarioRetornaDTO(Long id) {
        Usuario usuario = findById(id);
        return usuario != null ? new UsuarioSemSenhaDTO(usuario) : null;
    }

    public Usuario buscaUsuario(Long id) {
        return findById(id);
    }

    public List<UsuarioSemSenhaDTO> listaAniversariantes(Integer mes) {
        Stream<Usuario> userStream = this.createQuery("FROM Usuario usuario WHERE MONTH(dataDeNascimento) = :mes " +
                        "ORDER BY usuario.nome", Usuario.class)
                .setParameter("mes", mes)
                .getResultStream();

        return userStream.map(UsuarioSemSenhaDTO::new).collect(Collectors.toList());
    }

    public List<String> listaProvedoresEmail() {
        return createQuery("SELECT DISTINCT SUBSTRING(email, LOCATE('@', email) + 1) " +
                "AS provedores FROM Usuario ORDER BY provedores", String.class)
                .getResultList();
    }

    public List<UsuarioSemSenhaDTO> listaUsuariosPelaInicial(Character caracter) {
        TypedQuery<Usuario> query = this.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.nome LIKE :nome");
        Stream<Usuario> resultadoQuery = query.setParameter("nome", caracter + "%")
                .getResultStream();
        return resultadoQuery.map(UsuarioSemSenhaDTO::new)
                .collect(Collectors.toList());
    }

    public Usuario buscaUsuarioPeloLogin(String login) {
        try {
            return createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.login = :login", Usuario.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
