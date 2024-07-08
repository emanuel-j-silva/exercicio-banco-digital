package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta{
    private static int SEQUENCIAL = 1;
    private static int AGENCIA_PADRAO = 1;
    private int agencia;
    private int numero;
    private double saldo;
    private List<String> log;
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.saldo = 0;
        this.agencia = AGENCIA_PADRAO;
        this.cliente = cliente;
        this.log = new ArrayList<>();
    }

    public void addLog(String operacao, double valor){
        String log = String.format("%s | %s -> %.2f", LocalDateTime.now().
                format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),operacao,valor);
        this.log.add(log);
    }

    @Override
    public void sacar(double valor) {
        if (saldo < valor)
            throw new IllegalArgumentException("Saldo insuficiente!");

        saldo -= valor;
        addLog("Saque", valor);
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        addLog("Depósito",valor);
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public void imprimirExtrato() {
        for(String log:this.log){
            System.out.println(log);
        }

        System.out.println("Salddo atual: " + saldo);
    }
}
