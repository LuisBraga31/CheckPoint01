package dao;

import java.util.List;

public interface IDao<E> {
    E criar (E entidade);
    E buscarPorId(String id);
    void excluir(String id);
    List<E> buscarTodos();

}
