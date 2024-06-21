package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.DTO.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define a classe como um controlador REST
@RequestMapping("/medicos") // Define o caminho base para todos os endpoints neste controlador
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping // Indica que este método lida com solicitações POST
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { // essa anotação o Spring vai ler o corpo da requisição e mapear os campos dele para o DTO recebido como parâmetro.
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarinformacoes(dados);
    }

    @DeleteMapping("/{id}") // {id} = Parâmetro Dinâmico
    @Transactional
    public void excluir(@PathVariable long id){ // Variável da URL
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
