package br.com.fiap.adj.techchallenge.getaf.service;

import br.com.fiap.adj.techchallenge.getaf.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("testedb")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(45)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("palavrasecreta"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("palavrasecreta"))
                .withIssuer("testedb")
                .build().verify(token).getSubject();

    }
}
