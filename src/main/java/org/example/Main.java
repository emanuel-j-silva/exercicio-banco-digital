package org.example;

public class Main {
    public static void main(String[] args) {
        Banco brasilBanco = new Banco("Brasil banco");

        Cliente cliente1 = new Cliente("João da Silva", "000.001.900-00");
        Conta cc = new ContaCorrente(cliente1);

        Cliente cliente2 = new Cliente("Ana Maria", "123.321.001-99");
        Conta cp = new ContaPoupanca(cliente2);

        brasilBanco.addConta(cc);
        brasilBanco.addConta(cp);

        cc.depositar(200);
        cc.transferir(100,cp);
        cp.sacar(50);
        cp.depositar(200);

        cc.imprimirExtrato();
        cp.imprimirExtrato();
    }
}