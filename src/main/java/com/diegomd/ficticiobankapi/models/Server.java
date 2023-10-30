package com.diegomd.ficticiobankapi.models;

import com.diegomd.ficticiobankapi.database.DataBase;
import com.diegomd.ficticiobankapi.database.SECTIONS_DB;
import com.diegomd.ficticiobankapi.entities.Cliente;
import com.diegomd.ficticiobankapi.entities.Conta;
import com.diegomd.ficticiobankapi.entities.section.SectionModel;
import com.diegomd.ficticiobankapi.models.request.Request;
import com.diegomd.ficticiobankapi.models.response.Response;
import com.google.gson.Gson;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

// CLASSE RESPONSÁVEL PELAS REGRAS DE NEGÓCIO
public class Server {
    static Gson gson = new Gson();
    private static List<SectionModel> sectionModels;

    public static SectionModel createNewSection(UUID atmId) {
        return new SectionModel(atmId, "newSection");
    }

    public static Response handleRequest(Request request) {

        if (request.context.equalsIgnoreCase("newSection")) {

            SectionModel sectionModel = SECTIONS_DB.findByAtmClientId(request.atmClientId);

            if (sectionModel == null)
                return new Response().toNewSectionRequest(
                        SECTIONS_DB.save(createNewSection(request.atmClientId)),request.context);

            else
                return new Response().toNewSectionRequest(sectionModel, "newSection");

        }

        return null;
    }

    public static String importRequest(String jsonReq) {

        try {
            Request reqObj = gson.fromJson(jsonReq, Request.class);
            return gson.toJson(handleRequest(reqObj));

        } catch (Exception e) {
            System.out.println("InternalServerError when tried to parse json to object Error= "+e.getMessage());
        }

        return null;
    }

    public static void menuPrincipal(DataBase db) {
        //view.print("menu-principal");
        int opc = Integer.parseInt(obterEtradaDados("^[1-7]{1}$"));

        switch (opc) {
            case 1 -> criarConta(db);
            case 2 -> listarCorrentistas(db);
            case 3 -> sacar(db);
            case 4 -> depositar(db);
            case 5 -> transferir(db);
            case 6 -> encerrarConta(db);
            default -> System.exit(1);
        }
    }

    static String obterEtradaDados(String regex) {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (Pattern.compile(regex).matcher(entrada).matches())
            return entrada;

        //view.print("entrada-invalida");
        return obterEtradaDados(regex);
    }

    static void criarConta(DataBase db) {
        //view.print("cabecalho");
        //view.print("qual-seu-nome");
        String nome = obterEtradaDados("^\\w+\\s\\w+.*$");

        //view.print("qual-seu-cpf");
        long cpf = Long.parseLong(obterEtradaDados("^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$")
                .replace("-", "").replace(".", ""));

        boolean voidDb = db.getCorrentistas().isEmpty();
        boolean clienteNovo = db.getConta(0, cpf) == null;

        //view.print("cabecalho");
        if (voidDb || clienteNovo) {
            db.addCorrentista(new Conta(new Cliente(nome, cpf)));
            //view.print("operacao-realizada");
        }
        else
            //view.print("ja-possui-conta");
        menuPrincipal(db);
    }

    static void listarCorrentistas(DataBase db) {
        //view.print("cabecalho");
        if (db.getCorrentistas().isEmpty())
            System.out.println("nenhum correntista");
            //view.print("nenhum-correntista");
        else {
            //view.print("correntistas");
            db.getCorrentistas().forEach(System.out::println);
                    //view.imprimirDadosCorrentista(conta));
        }
        menuPrincipal(db);
    }

    static Conta getContaByContaOrCpf(DataBase db) {
        String entrada = obterEtradaDados("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}|\\d{7}$");
        entrada = entrada.replace(".", "").replace("-", "");
        int conta = 0;
        double cpf = 0;

        if (entrada.length() == 7)
            conta = Integer.parseInt(entrada);
        else
            cpf = Double.parseDouble(entrada);
        return db.getConta(conta, cpf);
    }

    static void encerrarConta(DataBase db) {
        //view.print("qual-conta/cpf");
        Conta conta = getContaByContaOrCpf(db);

        //view.print("cabecalho");
        if (conta == null)
            System.out.println("c n c");
            //view.print("correntista-nao-cadastrado");
        else {
            db.getCorrentistas().remove(conta);
            //view.print("operacao-realizada");
        }
        menuPrincipal(db);
    }

    static void sacar(DataBase db) {
        //view.print("qual-sua-conta/cpf");
        Conta minhaConta = getContaByContaOrCpf(db);

        if (minhaConta == null)
            System.out.println("cnc");
            //view.print("correntista-nao-cadastrado");
        else {
            //view.print("que-valor");
            double valor = Double.parseDouble(
                    obterEtradaDados("^-?\\d+((\\.|\\,)\\d+)?$").replace(",","."));
            //view.print("cabecalho");
            if (minhaConta.sacar(valor))
                System.out.println("or");
                //view.print("operacao-realizada");
            else
                System.out.println("on");
                //view.print("operacao-negada");
        }
        menuPrincipal(db);
    }

    static void depositar(DataBase db) {
        //view.print("qual-sua-conta/cpf");
        Conta minhaConta = getContaByContaOrCpf(db);

        if (minhaConta == null) {
            //view.print("cabecalho");
            //view.print("correntista-nao-cadastrado");
        } else {
            //view.print("que-valor");
            double valor = Double.parseDouble(
                    obterEtradaDados("^-?\\d+((\\.|\\,)\\d+)?$").replace(",","."));

            minhaConta.depositar(valor);
            //view.print("cabecalho");
            //view.print("operacao-realizada");
        }
        menuPrincipal(db);
    }

    static void transferir(DataBase db) {
        //view.print("qual-sua-conta/cpf");
        Conta minhaConta = getContaByContaOrCpf(db);

        if (minhaConta == null) {
            //view.print("cabecalho");
            //view.print("correntista-nao-cadastrado");
        }
        else {
            //view.print("qual-conta/cpf-destinatario");
            Conta contaDestinatario = getContaByContaOrCpf(db);

            if (contaDestinatario == null) {
                //view.print("cabecalho");
                //view.print("correntista-nao-cadastrado");
            }
            else {
                //view.print("que-valor");
                double valor = Double.parseDouble(
                        obterEtradaDados("^-?\\d+((\\.|\\,)\\d+)?$").replace(",","."));
                boolean transacaoOk = minhaConta.transferir(valor,contaDestinatario);

                //view.print("cabecalho");
                if (transacaoOk)
                    System.out.println("or");
                    //view.print("operacao-realizada");
                else
                    System.out.println("on");
                    //view.print("operacao-negada");
            }
        }
        menuPrincipal(db);
    }
}

