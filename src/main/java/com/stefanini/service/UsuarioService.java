package com.stefanini.service;

import com.stefanini.dto.UsuarioCriacaoDTO;
import com.stefanini.dto.UsuarioSemSenhaDTO;
import com.stefanini.entity.Usuario;
import com.stefanini.exceptions.*;
import com.stefanini.interfaces.MensagensConstantes;
import com.stefanini.repository.UsuarioRepository;
import com.stefanini.utils.EncriptadorSenha;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

import static com.stefanini.interfaces.MensagensConstantes.DATA_FORA_PADRAO;
import static com.stefanini.interfaces.RegexConstantes.*;

@ApplicationScoped
public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    public UsuarioSemSenhaDTO criaUsuario(UsuarioCriacaoDTO usuario) throws BadRequestException {
        Usuario usuarioRecebido = new Usuario(usuario);
        descriptaSenha(usuarioRecebido);
        verificaAtributosDoUsuario(usuarioRecebido, true);
        return usuarioRepository.criaUsuario(usuarioRecebido);
    }

    public UsuarioSemSenhaDTO atualizaUsuario(Long id, UsuarioCriacaoDTO usuario) throws BadRequestException {
        Usuario usuarioRetornado = usuarioRepository.buscaUsuario(id);
        if (usuarioRetornado != null) {
            Boolean verificaLoginIgual = !usuario.getLogin().equals(usuarioRetornado.getLogin());
            descriptaSenha(usuario);
            verificaAtributosDoUsuario(usuario, verificaLoginIgual);
            realizaAtribuicoes(usuarioRetornado, usuario);
            usuarioRetornado.setDataDeAtualizacao(LocalDateTime.now());
            return usuarioRepository.atualizaUsuario(usuarioRetornado);
        } else {
            return criaUsuario(usuario);
        }
    }

    public void apagaUsuario(Long id) throws NaoExisteException {
        if (usuarioRepository.buscaUsuarioRetornaDTO(id) == null) {
            throw new NaoExisteException("Usuario não encontrado");
        }
        usuarioRepository.apagaUsuario(id);
    }

    public List<UsuarioSemSenhaDTO> listaAniversariantes(Integer mes) throws ObjetoNaoEncontradoException {
        List<UsuarioSemSenhaDTO> users = usuarioRepository.listaAniversariantes(mes);
        if (users.size() < 1) {
            throw new ObjetoNaoEncontradoException("Nenhum aniversariante encontrado");
        }
        return users;
    }

    public List<String> listaProvedoresEmail() throws ObjetoNaoEncontradoException {
        List<String> listEmailProvider = usuarioRepository.listaProvedoresEmail();
        if (listEmailProvider.size() < 1) {
            throw new ObjetoNaoEncontradoException("Nenhum provedor encontrado");
        }
        return listEmailProvider;
    }

    public List<UsuarioSemSenhaDTO> listaUsuariosPelaInicial(Character character) throws ObjetoNaoEncontradoException {
        List<UsuarioSemSenhaDTO> users = usuarioRepository.listaUsuariosPelaInicial(character);
        if (users.size() < 1) {
            throw new ObjetoNaoEncontradoException("Nenhum usuário encontrado");
        }
        return users;
    }

    public List<UsuarioSemSenhaDTO> listaTodosUsuarios() throws ObjetoNaoEncontradoException {

        List<UsuarioSemSenhaDTO> users = usuarioRepository.listaTodosUsuarios();
        if (users.size() < 1) {
            throw new ObjetoNaoEncontradoException("Nenhum usuário encontrado");
        }
        return users;
    }

    public UsuarioSemSenhaDTO buscaUsuarioRetornaDTO(Long id) throws ObjetoNaoEncontradoException {
        UsuarioSemSenhaDTO user = usuarioRepository.buscaUsuarioRetornaDTO(id);
        if (user == null) {
            throw new ObjetoNaoEncontradoException("Nenhum usuário encontrado");
        }
        return user;
    }

    private static void descriptaSenha(Usuario usuario) {
        String password = EncriptadorSenha.descripta(usuario.getSenha());
        usuario.setSenha(password);
    }

    private static void descriptaSenha(UsuarioCriacaoDTO user) {
        String password = EncriptadorSenha.descripta(user.getSenha());
        user.setSenha(password);
    }

    private void verificaAtributosDoUsuario(Usuario usuario, Boolean checaUsuarioExiste) throws BadRequestException {
        verificaNome(usuario);
        verificaLogin(usuario, checaUsuarioExiste);
        verificaEmail(usuario);
        verificaSenha(usuario);
        verificaDataDeNascimento(usuario);
    }

    private void verificaAtributosDoUsuario(UsuarioCriacaoDTO user, Boolean checkUserExist) throws BadRequestException {
        Usuario usuarioCriado = new Usuario(user);
        verificaAtributosDoUsuario(usuarioCriado, checkUserExist);
    }

    private static void verificaDataDeNascimento(Usuario usuario) throws ForaDoPadraoException {
        if (usuario.getDataDeNascimento().toString().matches(DATA_ANIVERSARIO_REGEX)) {
            throw new ForaDoPadraoException(DATA_FORA_PADRAO);
        }
    }

    private static void verificaSenha(Usuario usuario) throws TamanhoErradoException, ForaDoPadraoException {
        if (usuario.getSenha().length() < 4 || usuario.getSenha().length() > 10) {
            throw new TamanhoErradoException("Senha deve ter mais de 4 e menos de 10 caracteres");
        } else if (!usuario.getSenha().matches(SENHA_REGEX)) {
            throw new ForaDoPadraoException("Senha está fora do padrão, deve ter pelo menos 1 letra minúcula, " +
                    "1 maiúscula, 1 número e 1 caracter especial");
        }
    }

    private static void verificaEmail(Usuario usuario) throws NaoVazioException, EmailErradoException {
        if (usuario.getEmail().isBlank()) {
            throw new NaoVazioException(MensagensConstantes.noEmpty("Email"));
        } else if (!usuario.getEmail().matches(EMAIL_REGEX)) {
            throw new EmailErradoException("Email inválido");
        }
    }

    private void verificaLogin(Usuario usuario, Boolean checaUsuarioExiste) throws TamanhoErradoException,
            NaoNuloException, LoginJaExisteException {
        if (usuario.getLogin().length() < 5 || usuario.getLogin().length() > 20) {
            throw new TamanhoErradoException("Login deve ter entre 5 a 20 caracteres");
        } else if (usuario.getLogin() == null) {
            throw new NaoNuloException(MensagensConstantes.noNull("Login"));
        } else if (checaUsuarioExiste && usuarioRepository.buscaUsuarioPeloLogin(usuario.getLogin()) != null) {
            throw new LoginJaExisteException("Este login já existe");
        }
    }

    private static void verificaNome(Usuario usuario) throws MaiorQuePermitidoException, NaoVazioException, NaoNuloException {
        if (usuario.getNome().length() > 50) {
            throw new MaiorQuePermitidoException("Nome não pode ter mais que 50 letras");
        } else if (usuario.getNome().isBlank()) {
            throw new NaoVazioException(MensagensConstantes.noEmpty("Nome"));
        } else if (usuario.getNome() == null) {
            throw new NaoNuloException(MensagensConstantes.noNull("Nome"));
        }
    }

    private static void realizaAtribuicoes(Usuario usuario, UsuarioCriacaoDTO usuarioDTO) {
        usuarioDTO.setSenha(usuarioDTO.getSenha());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setDataDeNascimento(usuarioDTO.getDataDeNascimento());
    }
}
