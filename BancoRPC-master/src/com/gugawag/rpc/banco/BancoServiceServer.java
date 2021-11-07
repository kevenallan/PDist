package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;

    private List<Conta> contas = new ArrayList<>();
    
    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }
    
    @Override
    public String criarConta(String numeroConta, Double saldo) throws RemoteException {
    	if (!this.saldoContas.containsKey(numeroConta)) {
    		this.saldoContas.put(numeroConta, saldo);
    		return "Conta criado com sucesso.";
    	};
    	
    	return "Esse número de conta já foi usado";
    }
    
    @Override
    public String adicionarConta(String numero, Double saldo) throws RemoteException {
    	Conta contaNova = new Conta(numero,saldo);
    	contas.add(contaNova);
    	return "Conta Criada com sucesso.\n		Numero: " + contaNova.getNumero() + "\n		Saldo: " + contaNova.getSaldo();
    }

}
