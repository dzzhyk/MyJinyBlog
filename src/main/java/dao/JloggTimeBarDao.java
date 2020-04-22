package dao;

import domain.JloggTimeBar;

import java.util.List;

public interface JloggTimeBarDao {
    List<JloggTimeBar> findYearAndMonthList();
}
