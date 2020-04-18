package service.Impl;

import dao.Impl.JloggGeneralDaoImpl;
import dao.JloggGeneralDao;
import domain.JloggGeneral;
import service.GeneralService;

public class GeneralServiceImpl implements GeneralService {
    @Override
    public JloggGeneral flushGeneral() {
        JloggGeneralDao dao = new JloggGeneralDaoImpl();
        return dao.findGeneral();
    }

    @Override
    public boolean updateGeneral(JloggGeneral general) {
        JloggGeneralDao dao = new JloggGeneralDaoImpl();
        return dao.updateGeneral(general);
    }
}
