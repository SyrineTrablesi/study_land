package services;

import entities.Formation;

import java.sql.SQLException;
import java.util.List;

public interface IFormation<F> {
    List<Formation> afficherFormation() throws SQLException;
}
