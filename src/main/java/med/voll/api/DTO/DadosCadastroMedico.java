package med.voll.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.Especialidade;

public record DadosCadastroMedico(
        @NotBlank String nome, /* Campo não esta vazio nem nulo */
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm, /* \\d -> Digit {Min 4, Max 6} */
        @NotNull Especialidade especialidade,
        @NotNull @Valid DadosEndereco endereco
) {
}
