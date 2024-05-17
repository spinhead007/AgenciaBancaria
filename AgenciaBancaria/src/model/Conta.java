package model;

import utils.Utils;

public class Conta {

	private static int accountCounter = 1;
	
	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	private String cpf;
	
	public Conta(Pessoa pessoa) {
		this.numeroConta = Conta.accountCounter;
		this.pessoa = pessoa;
		this.updateSaldo();
		Conta.accountCounter += 1;
	}
		public int getNumeroConta() {
		return numeroConta;
	}
		public Pessoa getClient() {
		return pessoa;
	}
		public void setCpf(Pessoa cpf) {
			this.pessoa = cpf;
	}
		public String getCpf() {
			return cpf;
	}
		
		public void setClient(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
		public Double getSaldo() {
		return saldo;
	}
		public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
		public void updateSaldo() {
			this.saldo = this.getSaldo();
	}
		public String toString() {
		return "\nBank Account: " + this.getNumeroConta() +
				"\nCliente: " + this.pessoa.getName() +
				"\nCPF: " + this.pessoa.getCpf() +
				"\nEmail: " + this.pessoa.getEmail() +
				"\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
				"\n" ;
	}
	
	public void depositar(Double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			System.out.println("Seu depósito foi realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar o depósito!");
		}
	}
	
	public void sacar(Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			System.out.println("Saque realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar o saque!");
		}
		
		
	}
	
	public void transferencia(Conta contaParaDeposito, Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			System.out.println("Transferencia realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar a transferencia!");
		}
	}
	public void transferenciaViaPix(Conta cpfDestinatario, Double valorTransferencia) {
			if(valorTransferencia > 0 && this.getSaldo() >= valorTransferencia) {
				setSaldo(getSaldo() - valorTransferencia);
				cpfDestinatario.saldo = cpfDestinatario.getSaldo() + valorTransferencia;
				System.out.println("Pix Realizado com sucesso!");
			}else {
				System.out.println("Não foi possível realizar o Pix!");
			}
}
}
