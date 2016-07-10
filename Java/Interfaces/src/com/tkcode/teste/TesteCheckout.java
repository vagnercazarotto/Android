package com.tkcode.teste;

import com.tkcode.caixa.Checkout;
import com.tkcode.caixa.Compra;
import com.tkcode.impressao.Impressora;
import com.tkcode.impressao.impressoras.ImpressoraEpson;
import com.tkcode.pagamento.Cartao;
import com.tkcode.pagamento.Operadora;
import com.tkcode.pagamento.operadoras.Cielo;

public class TesteCheckout {

	public static void main(String[] args) {
		Operadora operadora = new Cielo();
		Impressora impressora = new ImpressoraEpson();

		Cartao cartao = new Cartao();
		cartao.setNomeTitular("Joao M Couves");
		cartao.setNumeroCartao("123");

		Compra compra = new Compra();
		compra.setNomeCliente("Joao Mendonca Couves");
		compra.setProduto("Sabonete");
		compra.setValorTotal(2.5);

		Checkout checkout = new Checkout(operadora, impressora);
		checkout.fecharCompra(compra, cartao);

	}
}
