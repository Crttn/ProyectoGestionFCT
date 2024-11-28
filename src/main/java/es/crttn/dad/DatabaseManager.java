package es.crttn.dad;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseManager {

    private static final HikariDataSource dataSource;

    // Bloque estático para inicializar el pool
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/gestionfct_db");
        config.setUsername("root");
        config.setPassword("1234"); //Usuario sin contraseña o 1234
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);
    }

    // Metodo para obtener el DataSource
    public static DataSource getDataSource() {
        return dataSource;
    }

    // Metodo para cerrar el pool al finalizar la aplicación
    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
