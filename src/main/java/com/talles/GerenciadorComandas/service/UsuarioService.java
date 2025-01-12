package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.dtos.UsuarioDTO;
import com.talles.GerenciadorComandas.dtos.UsuarioMapper;
import com.talles.GerenciadorComandas.entity.Usuario;
import com.talles.GerenciadorComandas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioMapper.mapToEntity(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuarioMapper.mapToDTO(usuario);
    }
}
