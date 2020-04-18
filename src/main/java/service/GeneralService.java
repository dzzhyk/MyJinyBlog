package service;

import domain.JloggGeneral;

public interface GeneralService {
    JloggGeneral flushGeneral();
    boolean updateGeneral(JloggGeneral general);
}
