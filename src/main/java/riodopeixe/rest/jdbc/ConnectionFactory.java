package riodopeixe.rest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vagner
 */
public class ConnectionFactory {

    private static ConnectionFactory instance;
    private static Connection connection;

    public ConnectionFactory() {
    }

    public static ConnectionFactory getIntance() throws ConnectionException {

        try {
            if (instance == null) {
                instance = new ConnectionFactory();
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Administrador/Documents/DevZone/SisNota/DADOS-Lite.MDB");
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver não encontrado");
        } catch (SQLException e) {
            throw new ConnectionException("Dados de conexão inválidos");
        }
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConnectionFactory.connection = connection;
    }
}
