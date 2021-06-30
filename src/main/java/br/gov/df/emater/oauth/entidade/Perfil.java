package br.gov.df.emater.oauth.entidade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "perfil")
public class Perfil {

	@NonNull
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ToString.Include
	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

}
