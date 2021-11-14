package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }

    public void inserir(long id, String texto) {
        Mensagem novaMensagem= new Mensagem(id, texto);
        mensagemDao.inserir(novaMensagem);
    }
    
    public Mensagem pesquisarPorId(long id) {
    	return mensagemDao.pesquisarPorId(id);
    }
    
}

