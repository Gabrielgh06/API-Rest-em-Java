package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos") // Define o nome da Tabela no banco de dados
@Entity(name = "Medico") // Marca como uma entidade JPA
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária  // Define que o valor do "id" será gerado automaticamente pelo banco de dados.
    private Long id;
    private String nome;
    private String crm;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING) // Define que o atributo "especialidade" será armazenado como uma string no banco de dados.
    private Especialidade especialidade;

    @Embedded // Marca o atributo "endereco" como um objeto embutido na entidade "Medico".
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
        this.especialidade = dados.especialidade();
        this.telefone = dados.telefone();

    }

    public void atualizarinformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
