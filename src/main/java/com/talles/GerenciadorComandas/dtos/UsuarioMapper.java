package com.talles.GerenciadorComandas.dtos;

import com.talles.GerenciadorComandas.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario mapToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsuarioLogin(usuarioDTO.getUsuarioLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setRole(usuarioDTO.getRole());
        return usuario;
    }
    public UsuarioDTO mapToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUsuarioLogin(usuario.getUsuarioLogin());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setRole(usuario.getRole());
        return usuarioDTO;
    }

}
