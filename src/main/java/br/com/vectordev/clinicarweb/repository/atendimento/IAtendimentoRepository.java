package br.com.vectordev.clinicarweb.repository.atendimento;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vectordev.clinicarweb.entidade.atendimento.AtendimentoEntity;

public interface IAtendimentoRepository extends JpaRepository <AtendimentoEntity, Long> {

}
