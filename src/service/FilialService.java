package service;

import dao.IDao;
import model.Filial;

import java.util.List;

public class FilialService {

    private final IDao<Filial> daoFilial;

    public FilialService(IDao<Filial> daoFilial) {
        this.daoFilial = daoFilial;
    }

    public Filial criarFilial(Filial filial) {
        return daoFilial.criar(filial);
    }
    public Filial buscarFilialPorId(String id) {
        return daoFilial.buscarPorId(id);
    }

    public void excluirFilial(String id) {
        daoFilial.excluir(id);
    }

    public List<Filial> buscarPorTodos() {
        return daoFilial.buscarTodos();
    }

}
