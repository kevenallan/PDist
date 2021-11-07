package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    String criarConta(String numeroConta, Double saldo) throws RemoteException;
    
   String adicionarConta(String numero, Double saldo) throws RemoteException;
    
}
