package dao;

import domain.JloggGeneral;

public interface JloggGeneralDao {
    JloggGeneral findGeneral();
    boolean updateGeneral(JloggGeneral general);
}
