package test;

import dao.IDao;
import dao.impl.FilialH2Dao;
import model.Estrelas;
import model.Filial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.FilialService;

import java.util.List;

class FilialServiceTest {

    private FilialService filialService;

    @BeforeEach
    void setup() {
        IDao<Filial> filialIDao = new FilialH2Dao();
        Filial filial01 = new Filial("Amantikyr", "Avenida bps", 51, "Itajuba", "MG", Estrelas.CINCOESTRELAS);
        filialIDao.criar(filial01);
        Filial filial02 = new Filial("Gontijo Hotel", "Rua jk", 357, "Itajuba", "MG", Estrelas.CINCOESTRELAS);
        filialIDao.criar(filial02);
        Filial filial03 = new Filial("Hotel Coroados", "Rua jk", 151, "Itajuba", "MG", Estrelas.TRESESTRELAS);
        filialIDao.criar(filial03);
        Filial filial04 = new Filial("Torreto Hotel", "Rua jk", 331, "Pouso Alegre", "MG", Estrelas.QUATROESTRELAS);
        filialIDao.criar(filial04);
        Filial filial05 = new Filial("Brazucaz", "Rua jk", 39, "Pouso Alegre", "MG", Estrelas.DUASESTRELAS);
        filialIDao.criar(filial05);

        filialService = new FilialService(filialIDao);
    }

    @Test
    @DisplayName("Dado uma consulta, quando chamamos buscarTodos, exibir lista de Filiais")
    void dadoUmaConsulta_quandoChamamosBuscarTodos_entaoRetornarTodasFiliais() {

        List<Filial> filiaisBuscadas = filialService.buscarPorTodos();

        Assertions.assertEquals(5, filiaisBuscadas.size());

    }

    @Test
    @DisplayName("Dado uma novo filial, quando chamamos excluir, então retornar apenas 5 registros")
    void dadoUmaNovaFilial_quandoChamamosExcluir_entaoRetornarCincoFiliais() {
        Filial filial06 = new Filial("Hotel New", "Rua Esperança", 250, "Pouso Alegre", "MG", Estrelas.QUATROESTRELAS);
        filialService.criarFilial(filial06);

        filialService.excluirFilial(filial06.getId());

        List<Filial> filiaisBuscadas = filialService.buscarPorTodos();
        Assertions.assertEquals(5, filiaisBuscadas.size());

    }

    @Test
    @DisplayName("Dado uma nova filial, quando chamamos criar, então retornar 6 registros")
    void dadoUmaNovaFilial_quandoChamamosCriar_entaoRetornarSeisFiliais() {
        Filial filial06 = new Filial("Hotel New", "Rua Esperança", 250, "Pouso Alegre", "MG", Estrelas.QUATROESTRELAS);
        filialService.criarFilial(filial06);

        List<Filial> filiaisBuscadas = filialService.buscarPorTodos();
        Assertions.assertEquals(6, filiaisBuscadas.size());

    }

    @Test
    @DisplayName("Dado uma novo filial, quando chamamos buscar pelo Id, então retornar a filial encontrada")
    void dadoUmaNovaFilial_quandoChamamosBuscarPorId_entaoRetornarAFilialEncontrada() {
        Filial filial06 = new Filial("Hotel New", "Rua Esperança", 250, "Pouso Alegre", "MG", Estrelas.QUATROESTRELAS);
        filialService.criarFilial(filial06);

        Filial filialEncontrada = filialService.buscarFilialPorId(filial06.getId());

        Assertions.assertEquals("Hotel New", filialEncontrada.getNome());

    }

}