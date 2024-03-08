package services;

import java.sql.SQLException;
import java.util.List;

public interface IFormation<F> {
    List<Formation> afficherFormation() throws SQLException;
}
