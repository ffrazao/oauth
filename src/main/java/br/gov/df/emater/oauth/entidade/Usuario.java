package br.gov.df.emater.oauth.entidade;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.oauth.dominio.Confirmacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

	@NonNull
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ToString.Include
	private String nome;

	private String email;

	private String senha;

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@Column(name = "qtd_erro_senha")
	private Integer qtdErroSenha;

	@Column(name = "expiracao_conta")
	private LocalDateTime expiracaoConta;

	@Column(name = "expiracao_senha")
	private LocalDateTime expiracaoSenha;

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioPerfil> perfilList;

}
