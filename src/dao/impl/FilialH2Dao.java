package dao.impl;

import dao.ConfiguracaoJdbc;
import dao.IDao;
import model.Estrelas;
import model.Filial;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilialH2Dao implements IDao<Filial> {
    private static final Logger log = Logger.getLogger(FilialH2Dao.class);
    private ConfiguracaoJdbc configuracaoJdbc = new ConfiguracaoJdbc();
    private static final String SQL_BUSCAR_POR_ID = "SELECT * FROM Filial WHERE id = ? ";
    private static final String SQL_CRIACAO = """
            	INSERT INTO Filial(id, nome, rua, numero, cidade, estado, qtdEstrela) VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_EXCLUSAO = "DELETE FROM Filial WHERE id = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM Filial";

    @Override
    public Filial criar(Filial entidade) {
        log.info("Criando nova filial");
        Connection connection = configuracaoJdbc.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_CRIACAO)){
            statement.setString(1,entidade.getId());
            statement.setString(2, entidade.getNome());
            statement.setString(3, entidade.getRua());
            statement.setInt(4, entidade.getNumero());
            statement.setString(5, entidade.getCidade());
            statement.setString(6, entidade.getEstado());
            statement.setString(7, entidade.getQtdEstrela().name());
            statement.execute();
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        log.info("Filial criada: " + entidade.getNome());
        return entidade;
    }

    @Override
    public Filial buscarPorId(String id) {
        log.info("Buscando uma filial por id: " + id);
        Connection connection = configuracaoJdbc.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_BUSCAR_POR_ID)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            Filial filial = null;
            while(resultSet.next()) {
                String filialId = resultSet.getString(1);
                String nomeFilial = resultSet.getString(2);
                String rua = resultSet.getString(3);
                Integer numero = resultSet.getInt(4);
                String cidade = resultSet.getString(5);
                String estado = resultSet.getString(6);
                Estrelas estrelas = Estrelas.valueOf(resultSet.getString(7));
                filial = new Filial(filialId, nomeFilial,rua,numero,cidade,estado,estrelas);
            }
            statement.close();
            log.info("Filial encontrada: " + filial);
            return filial;

        } catch (Exception e) {
            log.error(e);
            return null;
        }

    }

    @Override
    public void excluir(String id) {
        log.info("Excluindo uma filial pelo id: " + id);
        Connection connection = configuracaoJdbc.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_EXCLUSAO)){
            statement.setString(1, id);
            statement.executeUpdate();
            log.info("Filial encontrada foi excluída!");
        } catch (Exception e) {
            log.error(e);
        }

    }

    @Override
    public List<Filial> buscarTodos() {

        log.info("Buscando todas filiais");
        Connection connection = configuracaoJdbc.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_BUSCAR_TODOS)) {
            List<Filial> listaFilial = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String filialId = resultSet.getString(1);
                String nomeFilial = resultSet.getString(2);
                String rua = resultSet.getString(3);
                Integer numero = resultSet.getInt(4);
                String cidade = resultSet.getString(5);
                String estado = resultSet.getString(6);
                Estrelas estrelas = Estrelas.valueOf(resultSet.getString(7));
                Filial filial = new Filial(filialId, nomeFilial,rua,numero,cidade,estado,estrelas);
                listaFilial.add(filial);
            }

            statement.close();
            log.info("Filiais encontradas: " + listaFilial);
            return listaFilial;

        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
}
