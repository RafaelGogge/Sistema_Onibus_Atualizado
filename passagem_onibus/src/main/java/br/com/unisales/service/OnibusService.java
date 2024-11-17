package br.com.unisales.service;

import br.com.unisales.dao.OnibusDao;
import br.com.unisales.table.Onibus;
import java.util.List;

public class OnibusService {
    private final OnibusDao dao;

    public OnibusService() {
        this.dao = new OnibusDao();
    }

    public String salvar(Onibus onibus) {
        return this.dao.salvar(onibus);
    }

    public String alterar(Onibus onibus) {
        return this.dao.alterar(onibus);
    }

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }

    public List<Onibus> listar() {
        return this.dao.listar();
    }
}
