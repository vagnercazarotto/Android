package com.tkcode.pagamento.operadoras;

import com.tkcode.pagamento.Autorizavel;
import com.tkcode.pagamento.Cartao;
import com.tkcode.pagamento.Operadora;

public class Cielo implements Operadora {

	@Override
	public boolean autorizar(Autorizavel autorizavel, Cartao cartao) {
		// TODO Auto-generated method stub
		return cartao.getNumeroCartao().startsWith("123") && autorizavel.getValorTotal() < 100;
	}

}
