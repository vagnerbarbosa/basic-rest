package vg.sales.weather.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance;
    private static Connection connection;

    public ConnectionFactory() {
    }

    public static ConnectionFactory getIntance() throws ConnectionException {

        try {
            if (instance == null) {
                instance = new ConnectionFactory();
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://43124312:6242/s324ab14m", "l3214", "41234");
                
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver não encontrado");
        } catch (SQLException e) {
            throw new ConnectionException("Dados de conexão inválidos");
        }
        return instance;
    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            throw new NullPointerException("Variavel conexão não iniciada.");
        }
    }

    public static void setConnection(Connection connection) {
        ConnectionFactory.connection = connection;
    }
}
