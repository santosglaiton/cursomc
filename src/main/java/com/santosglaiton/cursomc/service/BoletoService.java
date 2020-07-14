package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.PagamentoComBoletoDomain;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoletoDomain pagto, Date instanteDoPedido){

        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }

}
